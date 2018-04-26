package com.start.boot.email;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.email.Xt_User_RelationMapper;
import com.start.boot.email.webService.CesMessageService;
import com.start.boot.pojo.vo.ExcelVo;
import com.start.boot.query.Query;
import com.start.boot.service.MessageService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.utils.ExportExcelUtils;
import com.start.boot.utils.QueryUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmailScheduTask {
    private  static Log logger= LogFactory.getLog(EmailScheduTask.class);

    @Autowired
    MessageService messageService;


    @Autowired
    QueryUtils queryUtils;

    @Autowired
    Xt_User_RelationMapper user_relationMapper;

    @Autowired
    SystemCoreConfigService systemCoreConfigService;

    @Autowired
    ExportExcelUtils excelUtils;

    /**
     * 秒 分钟 小时 天 月 年
     *
     *
     * 0 * " 0/1" * * * ?
     * 从任意时间开始，间隔频率为1分钟执行一次
     *
     *
     *每天的8点执行
     * 0 0 8 * * ？
     *每天的8点10分执行
     * 0 10 8 * * ？
     *
     */
   @Scheduled(cron = "0 10 6 * * ?")
    public void sendEmail() throws Exception {
        logger.info("定时发送邮件服务=====>");
        sendEmailUnit();
    }

    void sendEmailUnit() throws Exception {
        List<Map<String,Object>>  message = messageService.getMessage();
        if (!CollectionUtils.isEmpty(message)){
            List<List<String>> messageData = new ArrayList<>();
            //写短信记录
            String fileName = DateTime.now().toString("YYYYMMdd");
               for (Map<String, Object> data : message) {
                   StringBuffer content=new StringBuffer();
                   String dwbm = (String) data.get("JSRDWBM");
                   String gh = (String) data.get("JSRGH");
                   Object emailcount = data.get("EMAILCOUNT");
                   String xxlx = (String) data.get("XXLX");
                   Integer count=null;
                   if (emailcount instanceof BigDecimal){
                       count=((BigDecimal) emailcount).intValue();
                   }
                   else if (emailcount instanceof Integer){
                       count= (Integer) emailcount;
                   }
                   if (!StringUtils.isEmpty(dwbm)&& !StringUtils.isEmpty(gh)){
                       //获取邮箱地址
                       String appid = getAppid(dwbm, gh);
                       String openEmail = systemCoreConfigService.getSystemConfigValue("openEmail");
                       if ("false".equalsIgnoreCase(openEmail)){
                           appid="15339";
                       }
                       logger.error("获取邮箱地址成功"+appid);
                       //获取人的姓名
                       String mc = getMc(dwbm, gh);
                       //获取微信赂
                       String weixin = getWeixin(appid);
                       logger.error("获取微信账号为"+weixin);
                       if (StringUtils.isEmpty(mc)){
                           continue;
                       }
                       content.append(mc+",您好！</br>");
                       if ("0".equalsIgnoreCase(xxlx)){
                           continue;
                       }else if ("1".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件评查需要审批");
                           getExcelLineData(messageData, content, mc, weixin);

                       }else if ("2".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件评查已有审批");
                           //写excel数据
                           getExcelLineData(messageData, content, mc, weixin);
                       }else  if ("3".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件经评查需反馈");
                           //写excel数据
                           getExcelLineData(messageData, content, mc, weixin);
                       }else  if ("4".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件评查已有反馈");
                           //写excel数据
                           getExcelLineData(messageData, content, mc, weixin);
                       }else  if ("6".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件评查待审批");
                           //写excel数据
                           getExcelLineData(messageData, content, mc, weixin);
                       }else  if ("7".equalsIgnoreCase(xxlx)){
                           content.append("您已有"+count+"个案件评查被退回");
                       }else {
                           continue;
                       }
                       //发送邮件
                       SendMessage(content,appid);
                       //更新发送消息状态
                       messageService.updateMessageFszt(xxlx,dwbm,gh);
                   }
               }
               exportExcelMessage(messageData,fileName);

        }
    }

    private void getExcelLineData(List<List<String>> messageData, StringBuffer content, String mc, String weixin) {
        //写excel数据
        ArrayList<String> rowData = new ArrayList<>();
        rowData.add(mc);
        rowData.add(weixin);
        rowData.add(content.toString());
        messageData.add(rowData);
    }

    private void SendMessage(StringBuffer content,String appid) throws IOException {


        logger.error("开始发送邮件");
        logger.error("发送内容为："+content.toString());
        try {
            CesMessageService msg = new CesMessageService();
            //状态位1桌面，2邮件，3桌面+邮件
            msg.getCesMessage()
                    .addMessage(Long.valueOf("18360"), "案件质量评查系统",
                            "", content.toString(), content.toString(), SystemConfiguration.mail_url, "2",
                            Long.valueOf(appid), SystemConfiguration.mail_from);
            logger.error("发送邮件成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("发送邮件消息失败");
        }
    }

    /**
     * 根据单位编码 和工号获取统一业务中的appid
     * @param dwbm
     * @param gh
     * @return
     */
    private   String  getAppid(String dwbm,String gh){
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dwbm)&& org.apache.commons.lang3.StringUtils.isNotEmpty(gh)){
            String appId = user_relationMapper.getAppId(dwbm, gh);
            return appId;
        }
        return null;
    }

    /**
     * 导出excel微信信息
     * @param data 要导出的数据
     * @param fileName 文件名
     */
    private void  exportExcelMessage(List<List<String>>data,String fileName){
        logger.error("开始导出数据");
        ExcelVo excelVo = new ExcelVo();
        String fileAdress = systemCoreConfigService.getSystemConfigValue("message_file_adress");
        excelVo.setFilePath(fileAdress);
        excelVo.setFileName(fileName);
        ArrayList<String> header = new ArrayList<>();
        header.add("姓名");
        header.add("账号");
        header.add("通知内容");
        excelVo.setHeader(header);
        excelVo.setData(data);
        excelUtils.exportExcelData(excelVo);
    }
   /* @Scheduled(cron = "0 0/1 * * * ?")
    public void test(){
        ArrayList<String> rowdata = new ArrayList<>();
        ArrayList<List<String>> data = new ArrayList<>();
        rowdata.add("caomin");
        rowdata.add(null);
        rowdata.add("test");
        data.add(rowdata);
        String fileName = DateTime.now().toString("YYYY-MM-dd");
        exportExcelMessage(data,fileName);
    }*/

    /**
     * 获取用户的姓名
     * @param dwbm
     * @param gh
     * @return
     * @throws Exception
     */
    public  String getMc(String dwbm,String gh) throws Exception {
        Query query = new Query();
        query.setTableName("XT_ZZJG_RYBM");
        HashMap<String, String> map = new HashMap<>();
        map.put("mc","MC");
        query.setDisplayColumnName(map);
        query.createCriteria().andEqualTo("DWBM",dwbm)
                .andEqualTo("GH",gh);

        List<Map<String,Object>>  map1 = queryUtils.getMap(query.build());
        if (!CollectionUtils.isEmpty(map1)){
            return (String) map1.get(0).get("MC");
        }
        return null;
    }

    /**
     * 获取微信账号
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public  String getWeixin(String appid) throws Exception {
       return user_relationMapper.getWeixin(appid);
    }


}
