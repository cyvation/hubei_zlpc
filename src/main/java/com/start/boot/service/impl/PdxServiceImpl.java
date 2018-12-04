package com.start.boot.service.impl;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.*;
import com.start.boot.domain.*;
import com.start.boot.query.PdxQuery;
import com.start.boot.service.PcService;
import com.start.boot.service.PdxService;
import com.start.boot.utils.PoiUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lei on 2018/11/13.
 */
@Service
public class PdxServiceImpl implements PdxService {

    @Autowired
    private PdxMapper pdxMapper;

    @Autowired
    private YxDcPdxMapper yxDcPdxMapper;

    @Autowired
    private YX_PC_JBXXMapper yxPcJbxxMapper;

    @Autowired
    private YxPcJzwjMapper jzwjMapper;

    @Autowired
    private Yx_Pc_PcxMapper yx_pc_pcxMapper;

    @Autowired
    private XtZzjgRmbmMapper xtZzjgRmbmMapper;


    @Autowired
    PcService pcService;

    @Autowired
    private PoiUtils poiUtils;

    @Override
    public List<Map> getPdx(PdxQuery query) {
        return pdxMapper.getPdx(query);
    }

    @Override
    @Transactional
    public void savePdx(String pcslbm, List<YxDcPdx> yxDcPdxes) {

        if (!CollectionUtils.isEmpty(yxDcPdxes)){

            YxDcPdxExample example = new YxDcPdxExample();
            example.createCriteria().andPcslbmEqualTo(pcslbm);
            yxDcPdxMapper.deleteByExample(example);

            for (YxDcPdx yxDcPdx : yxDcPdxes) {
                yxDcPdxMapper.insertSelective(yxDcPdx);
            }

            YX_PC_JBXX jbxx = new YX_PC_JBXX();
            jbxx.setPCSLBM(pcslbm);
            jbxx.setPCJL(yxDcPdxes.get(0).getPdjg() + "案件");
            yxPcJbxxMapper.updateByPrimaryKeySelective(jbxx);

        }

    }


    @Override
    @Transactional
    public String generatePdxDoc(String pcslbm) throws Exception {

        // 获取案件信息
        YX_PC_JBXX yx_pc_jbxx = yxPcJbxxMapper.selectByPrimaryKey(pcslbm);

        // 获取评定项
        List<YxDcPdx> list= pdxMapper.getSelectedPdx(pcslbm);

        // 获取评查意见
        List<Yx_Pc_Pcx> pcxList = pdxMapper.getSelectedPcx(pcslbm);


        // 证据采信、事实认定、、、作为key
        // 1.证据采信:物证、书证等证据的形式存在瑕疵(有很多问题)，首次讯问、询问笔录没有记录告知被讯问人、证人、被害人有关权利义务（sdfsd）;
        // 2.事实认定:认定的前科劣迹、自首、坦白、立功等影响量刑的事实或情节有遗漏或者错误,认定的案件基本事实要素有遗漏或者有错误
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            YxDcPdx yxDcPdx = list.get(i);
            sb.append(i+1).append(".").append(yxDcPdx.getPdxflbm()).append(":");
            sb.append(yxDcPdx.getPdyj());
            sb.append(";");
            sb.append("\r");
        }

        //问题项
        StringBuilder wtx = new StringBuilder();
        for (int i = 0; i < pcxList.size(); i++) {
            Yx_Pc_Pcx pcx = pcxList.get(i);
            wtx.append(i+1).append(".").append(pcx.getPcxflbm()).append(":");
            wtx.append(pcx.getPcxmc());
            wtx.append(";");
            wtx.append("\r");
        }



        // 获取评定报告模板
        String source = SystemConfiguration.wzbsPath + "/Files/json/pcgb/moban/评定报告模板.doc";

        String savePath = "/" + SystemConfiguration.djdwbm + "/" + DateTime.now().getYear() + "/" + yx_pc_jbxx.getPCHDBM() + "/" +yx_pc_jbxx.getPCSLBM()  + "/" + UUID.randomUUID().toString().replaceAll("-","") +".doc";
        String path = SystemConfiguration.pcjzPath + savePath;
        String target = SystemConfiguration.wzbsPath + path;

        Map<String, String> params = new HashMap<>();
        params.put("${AJMC}",yx_pc_jbxx.getAJMC());
        params.put("${BPC_MC}",yx_pc_jbxx.getBPCMC());
        params.put("${AJLB_MC}",yx_pc_jbxx.getAJLBMC());
        params.put("${PCR_MC}",yx_pc_jbxx.getPCRMC());
        params.put("${PCRQ}",DateTime.now().toString("yyyy年MM月dd日"));
        params.put("${BMSAH}",yx_pc_jbxx.getBMSAH());
        params.put("${WT}",wtx.toString());
        params.put("${YJ}",sb.toString());
        params.put("${PCJL}", StringUtils.isEmpty(yx_pc_jbxx.getPCJL()) ? "": yx_pc_jbxx.getPCJL());

        poiUtils.createBg(source, target, params,null);


        Param_Jzwj jzwj = new Param_Jzwj();
        jzwj.setWscflj(savePath);
        jzwj.setWjmc("评定报告");
        jzwj.setWsmbbh("420000000007");
        jzwj.setWjkzm(".doc");

        insertJzwj(jzwj, yx_pc_jbxx);


        return savePath;


    }

    // 将评定报告插入数据库
    private void insertJzwj(Param_Jzwj jzwj, YX_PC_JBXX yx_pc_jbxx) throws Exception {

        // 移除以前的
        YxPcJzwjExample example = new YxPcJzwjExample();
        example.createCriteria().andPczybmEqualTo(yx_pc_jbxx.getPCSLBM())
                                .andWjlxEqualTo("3").andWsmbbhEqualTo(jzwj.getWsmbbh());
        YxPcJzwj record = new YxPcJzwj();
        record.setSfsc("Y");
        jzwjMapper.updateByExampleSelective(record, example);



        // 放到个案报告底下
        String jzmlh = SystemConfiguration.djdwbm + yx_pc_jbxx.getPCFLBM() + "003";

        jzwj.setFjzwjbh("-1");
        jzwj.setDwbm(yx_pc_jbxx.getPCDWBM());
        jzwj.setPczybm(yx_pc_jbxx.getPCSLBM());
        jzwj.setWjlx("3");
        jzwj.setJzmlh(jzmlh);
     //   jzwj.setWjmc("评定报告");
        jzwj.setGxlx("1");

        jzwj.setNzrdwbm(yx_pc_jbxx.getPCRDWBM());
        jzwj.setNzrdwmc(yx_pc_jbxx.getPCRDWMC());
        jzwj.setNzrgh(yx_pc_jbxx.getPCRGH());
        jzwj.setNzrxm(yx_pc_jbxx.getPCRMC());

        pcService.addJzwj(jzwj);

    }

    /**
     * 生成   案件质量评查意见书
     * 一。被评查案件基本情况
     * 二。评查工作开展情况
     * 三。评查过程中发现的问题 -- 有则填、无则空
     * 四、评查小组联席会议情况 -- 暂空
     * 五、结果等次建议及评定依据
     * 六、其他需要说明的问题 -- 暂空
     * @param pcslbm
     * @return
     */
    @Override
    @Transactional
    public String generateAjpcDoc(String pcslbm) throws Exception {

        // 案件信息
        YX_PC_JBXX yx_pc_jbxx = yxPcJbxxMapper.selectByPrimaryKey(pcslbm);
        // 获取评定项
        List<YxDcPdx> list= pdxMapper.getSelectedPdx(pcslbm);

        // 获取检察官身份
        String cbr_duty = "";
        XtZzjgRmbmExample example = new XtZzjgRmbmExample();
        example.createCriteria().andDwbmEqualTo(yx_pc_jbxx.getBPCDWBM()).andGhEqualTo(yx_pc_jbxx.getBPCGH());
        List<XtZzjgRmbm> xtZzjgRmbms = xtZzjgRmbmMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(xtZzjgRmbms)){
            XtZzjgRmbm xtZzjgRmbm = xtZzjgRmbms.get(0);
            String sfmc = xtZzjgRmbm.getSfmc();
            if (StringUtils.isEmpty(sfmc)){
                cbr_duty = yx_pc_jbxx.getBPCBMMC() + yx_pc_jbxx.getBPCMC();
            }else {
                cbr_duty = yx_pc_jbxx.getBPCBMMC() + sfmc;
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("${BMSAH}",yx_pc_jbxx.getPCSAH()); // 换成评查受案号
        params.put("${AJMC}",yx_pc_jbxx.getAJMC());
        params.put("${BPC_DWMC}",yx_pc_jbxx.getBPCDWMC());
        params.put("${BPC_MC}",yx_pc_jbxx.getBPCMC());

        // 人员职务
        params.put("${CBR_DUTY}",cbr_duty);

        String pcflmc = "";
        switch (yx_pc_jbxx.getPCFLBM()){
            case "001": pcflmc = "随机筛选";
            break;
            case "008":pcflmc = "重点筛选";
            break;
            case "003":pcflmc = "专项筛选";
            break;
        }
        params.put("${PCFLMC}",pcflmc);


        //评定项 拼凑评查过程中发现的问题
        String[] pdxflmc = {"证据采信","事实认定","法律适用","办案程序","法律文书","释法说理","办案效果","司法责任落实","统一业务系统规范使用"};
        String[] preWt =new String[9];
        Arrays.fill(preWt,"未发现问题");
        for (int i = 0; i < pdxflmc.length; i++) {
          List<YxDcPdx> pdxList =   pdxMapper.getPdxWt(pcslbm, pdxflmc[i]);

          // 1. 违反管辖规定受理、办理案件: 备注
          // 2. 法定量刑情节认定不当: 备注
          if (!CollectionUtils.isEmpty(pdxList)){
              StringBuilder sb = new StringBuilder();
              for (int index = 0; i < pdxList.size(); i++) {
                  YxDcPdx yxDcPdx = pdxList.get(i);
                  sb.append(index+1).append(".").append(yxDcPdx.getPdxmc()).append(":");
                  sb.append(yxDcPdx.getPdyj());
                  sb.append(";");
                  sb.append("\n");
              }
              preWt[i] = sb.toString();
          }

        }

        params.put("${一}",preWt[0]);
        params.put("${二}",preWt[1]);
        params.put("${三}",preWt[2]);
        params.put("${四}",preWt[3]);
        params.put("${五}",preWt[4]);
        params.put("${六}",preWt[5]);
        params.put("${七}",preWt[6]);
        params.put("${八}",preWt[7]);
        params.put("${九}",preWt[8]);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            YxDcPdx yxDcPdx = list.get(i);
            sb.append(i+1).append(".").append(yxDcPdx.getPdxflbm()).append(":");
            sb.append(yxDcPdx.getPdyj());
            sb.append(";");
            sb.append("\r");
        }
        params.put("${PDYJ}",sb.toString());

        params.put("${PCJL}",StringUtils.isEmpty(yx_pc_jbxx.getPCJL()) ? "": yx_pc_jbxx.getPCJL());
        params.put("${PCR_MC}",yx_pc_jbxx.getPCRMC());
        params.put("${CJSJ}",DateTime.now().toString("yyyy年MM月dd日"));

        // 获取评定报告模板
        String source = SystemConfiguration.wzbsPath + "/Files/json/pcgb/moban/评查意见书.doc";

        String savePath = "/" + SystemConfiguration.djdwbm + "/" + DateTime.now().getYear() + "/" + yx_pc_jbxx.getPCHDBM() + "/" +yx_pc_jbxx.getPCSLBM()  + "/" + UUID.randomUUID().toString().replaceAll("-","") +".doc";
        String path = SystemConfiguration.pcjzPath + savePath;
        String target = SystemConfiguration.wzbsPath + path;

        poiUtils.createBg(source, target, params,null);


        Param_Jzwj jzwj = new Param_Jzwj();
        jzwj.setWscflj(savePath);
        jzwj.setWjmc("评查意见书");
        jzwj.setWsmbbh("420000000008");
        jzwj.setWjkzm(".doc");


        insertJzwj(jzwj, yx_pc_jbxx);


        return savePath;
    }
}
