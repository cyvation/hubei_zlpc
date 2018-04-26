package com.start.boot.task;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.Param_Send;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import com.start.boot.query.Query;
import com.start.boot.service.HandleService;
import com.start.boot.service.JbxxService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.utils.ApplicationContextUtils;
import com.start.boot.utils.QueryUtils;
import com.start.boot.utils.WebServiceUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 30日未反馈，自动评查反馈
 *
 * @caomin
 * @create 2018-01-04 13:50
 **/
@Component
public class PcfkTask {

    @Autowired
    YX_PC_JBXXMapper jbxxMapper;

    @Autowired
    HandleService handleService;

    @Autowired
    private WebServiceUtils webServiceUtils;


    @Autowired
    QueryUtils queryUtils;


    @Autowired
    SystemCoreConfigService systemCoreConfigService;

    private Log logger= LogFactory.getLog(PcfkTask.class);


    @Scheduled(cron = "0 10 6 * * ?")
     public void invokePcwfk(){
        String pcfk_min_days = systemCoreConfigService.getSystemConfigValue("pcfk_min_days");
        Integer days = Integer.valueOf(pcfk_min_days);
        YX_PC_JBXXExample yx_pc_jbxxExample = new YX_PC_JBXXExample();
         Date date = DateTime.now().toLocalDate().minusDays(days).toDate();
         yx_pc_jbxxExample.createCriteria().andPcjdbhEqualTo("008").andZhxgsjLessThanOrEqualTo(date);
         List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(yx_pc_jbxxExample);
         if (!CollectionUtils.isEmpty(yx_pc_jbxxes)){
             yx_pc_jbxxes.stream().forEach(data->{
                 invokeCx(data);
             });
         }
     }



     public void invokeCx(YX_PC_JBXX data){
         DataSourceTransactionManager transactionManager= (DataSourceTransactionManager) ApplicationContextUtils.getBean("ajpcTransactionManager");
         DefaultTransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
         transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
         TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
         try {
             updateFkzt(data);
             updateLzd(data);
             transactionManager.commit(transaction);
         }catch (Exception e){
             e.printStackTrace();
             transactionManager.rollback(transaction);
         }

     }

     /**
      * 更改流转单
      */
    private void updateLzd( YX_PC_JBXX jbxx)  throws Exception{
        String path=null;
        Query query = new Query();
        query.setTableName("YX_PC_JZWJ");
        HashMap<String, String> display = new HashMap<>();
        display.put("WSCFLJ","WSCFLJ");
        query.setDisplayColumnName(display);
        query.createCriteria().andEqualTo("PCZYBM",jbxx.getPCSLBM())
                .andEqualTo("WJLX","2")
                .andEqualTo("SFSC","N");
        query.setOrderByClause("ZHXGSJ DESC");
        List<Map<String,Object>> map = null;

         map = queryUtils.getMap(query);

        if (!CollectionUtils.isEmpty(map)){
            path = (String) map.get(0).get("WSCFLJ");
        }
        if (path==null){
            throw  new RuntimeException("没有找到文件路径");
        }
        // 调用WebService/AppendTableRow生成评查流转单
        Map params = new HashMap();
        String fkyj = systemCoreConfigService.getSystemConfigValue("pcfk_zd_fkyj");
        String docpath = SystemConfiguration.wzbsPath + SystemConfiguration.pcjzPath + path;
        params.put("firstCellValue", "承办检察官意见");
        params.put("markName", "CBJCGYJ");
        params.put("docPath", docpath);
        params.put("luoquan", jbxx.getBPCMC()+" "+DateTime.now().toString("YYYY年MM月dd日"));
        params.put("context", fkyj);
        logger.error("docpath====>>>"+docpath);
        System.out.println(docpath);
        String url = SystemConfiguration.webservice + "/AppendTableRow";
        System.out.println(url);
        Object data = webServiceUtils.post(url, params);
        List<String> result = webServiceUtils.parseXml(data.toString());
        if (!CollectionUtils.isEmpty(result)){
            if (!StringUtils.isEmpty(result.get(0))){
                throw new  RuntimeException("修改失败");
            }
        }


    }

    /**
     * 更新反馈状态
     * @param data
     */
    private void updateFkzt(YX_PC_JBXX data) throws Exception{
        Param_Send param_send = new Param_Send();

        param_send.setPcslbm(data.getPCSLBM());
        param_send.setPcflbm(data.getPCFLBM());
        param_send.setPchdbm(data.getPCHDBM());
        param_send.setBmsah(data.getBMSAH());
        param_send.setBpc_dwbm(data.getBPCDWBM());
        param_send.setBpc_dwmc(data.getBPCDWMC());
        param_send.setBpc_gh(data.getBPCGH());
        param_send.setBpc_mc(data.getBPCMC());
        param_send.setSfyy("N");

        handleService.dealFeedbcak(param_send);

    }


}
