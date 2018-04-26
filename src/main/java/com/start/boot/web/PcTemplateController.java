package com.start.boot.web;


import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.SystemCoreConfig;
import com.start.boot.service.PcmbService;
import com.start.boot.support.utils.EasyUIHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.ognl.ObjectArrayPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评查模板库控制器
 * Created by lei on 2017/10/30.
 */
@RestController
@RequestMapping("/template")
public class PcTemplateController extends ArchivesSystemBaseController{

    @Autowired
    private PcmbService pcmbService;


    /**
     * 获取评查标准模板
     * @param pcflbm
     * @return
     */
    @RequestMapping("/template")
    public String getPcmb(String pcflbm) {
        //设置相应内容：
        String result ="";

        try {
            List<Map> list = pcmbService.getPcmb(pcflbm);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCMBBM", "PCFLBM", "PCMBMC", pcflbm);
        } catch (Exception e) {
            super.errMsg("获取评查标准模板失败","pcflbm"+pcflbm,e);
        }

        return  result;
    }

    /**
     * 获取评查标准模板
     * @param pcflbm
     * @return
     */
    @RequestMapping("/templateByPcmbbm")
    public String getPcmbByPcmbbm(String pcflbm, String pcmbbm) {
        //设置相应内容：
        String result ="";
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dwbm", SystemConfiguration.djdwbm);
            params.put("pcflbm", pcflbm);
            params.put("pcmbbm", pcmbbm);
            List<Map> list = pcmbService.getPcmbByPcmbbm(params);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCMBBM", "PCFLBM", "PCMBMC", pcflbm);
        } catch (Exception e) {
            super.errMsg("获取评查标准模板失败","pcflbm"+pcflbm,e);
        }

        return  result;
    }




}
