package com.start.boot.service.impl;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.PdxMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.dao.ajpc.YxDcPdxMapper;
import com.start.boot.dao.ajpc.YxPcJzwjMapper;
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

import java.io.File;
import java.util.*;

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

        }

    }


    @Override
    public String generatePdxDoc(String pcslbm) throws Exception {

        // 获取评定项
        List<YxDcPdx> list= pdxMapper.getSelectedPdx(pcslbm);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            YxDcPdx yxDcPdx = list.get(i);
            // 1.证据采信:物证、书证等证据的形式存在瑕疵,xxxx
            // 2.事实认定:认定的前科劣迹、自首、坦白、立功等影响量刑的事实或情节有遗漏或者错误,认定的案件基本事实要素有遗漏或者有错误
            sb.append(i+ 1).append(".").append(yxDcPdx.getPdxflbm()).append(":").append(yxDcPdx.getPdxmc()).append("\r\n");
        }

       // 获取案件信息
        YX_PC_JBXX yx_pc_jbxx = yxPcJbxxMapper.selectByPrimaryKey(pcslbm);

        // 获取评定报告模板
        String source = SystemConfiguration.wzbsPath + "/Files/json/pcgb/moban/评定报告模板.doc";

        String savePath = "/" + SystemConfiguration.djdwbm + "/" + DateTime.now().getYear() + "/" + yx_pc_jbxx.getPCHDBM() + "/" +yx_pc_jbxx.getPCSLBM()  + "/" + UUID.randomUUID().toString().replaceAll("-","") +".doc";
        String path = SystemConfiguration.pcjzPath + savePath;
        String target = SystemConfiguration.wzbsPath + path;


        if (!CollectionUtils.isEmpty(list)){

            Map<String, String> params = new HashMap<>();
            params.put("${AJMC}",yx_pc_jbxx.getAJMC());
            params.put("${BPC_MC}",yx_pc_jbxx.getBPCMC());
            params.put("${AJLB_MC}",yx_pc_jbxx.getAJLBMC());
            params.put("${PCR_MC}",yx_pc_jbxx.getPCRMC());
            params.put("${PCRQ}",DateTime.now().toString("yyyy年MM月dd日"));
            params.put("${WT}",sb.toString());
            params.put("${PCJL}",yx_pc_jbxx.getPCJL());

            poiUtils.createBg(source, target, params,null);


            Param_Jzwj jzwj = new Param_Jzwj();
            jzwj.setWscflj(savePath);
            jzwj.setWjkzm(".doc");

            insertJzwj(jzwj, yx_pc_jbxx);

        }


        return savePath;


    }

    // 将评定报告插入数据库
    private void insertJzwj(Param_Jzwj jzwj, YX_PC_JBXX yx_pc_jbxx) throws Exception {

        // 移除以前的
        YxPcJzwjExample example = new YxPcJzwjExample();
        example.createCriteria().andPczybmEqualTo(yx_pc_jbxx.getPCSLBM())
                                .andWjlxEqualTo("6");
        YxPcJzwj record = new YxPcJzwj();
        record.setSfsc("Y");
        jzwjMapper.updateByExampleSelective(record, example);



        // 放到个案报告底下
        String jzmlh = SystemConfiguration.djdwbm + yx_pc_jbxx.getPCFLBM() + "003";

        jzwj.setFjzwjbh("-1");
        jzwj.setDwbm(yx_pc_jbxx.getPCDWBM());
        jzwj.setPczybm(yx_pc_jbxx.getPCSLBM());
        jzwj.setWjlx("6");
        jzwj.setJzmlh(jzmlh);
        jzwj.setWjmc("评定报告");
        jzwj.setGxlx("1");

        jzwj.setNzrdwbm(yx_pc_jbxx.getPCRDWBM());
        jzwj.setNzrdwmc(yx_pc_jbxx.getPCRDWMC());
        jzwj.setNzrgh(yx_pc_jbxx.getPCRGH());
        jzwj.setNzrxm(yx_pc_jbxx.getPCRMC());

        pcService.addJzwj(jzwj);

    }
}
