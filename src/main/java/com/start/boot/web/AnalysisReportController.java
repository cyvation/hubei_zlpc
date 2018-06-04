package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.Param_Ajqkzlfx;
import com.start.boot.pojo.vo.*;
import com.start.boot.service.AnalysisReportService;
import com.start.boot.service.AnalysisService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.HttpContextUtils;
import com.start.boot.utils.ExportExcelUtils;
import com.start.boot.utils.ExportWordUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 湖北专题报告分析：统计表及报告
 */
@RestController
@RequestMapping("/analysisReport")
public class AnalysisReportController extends ArchivesSystemBaseController{
    @Autowired
    private AnalysisReportService analysisReportService;



    @Autowired
    private ExportWordUtils exportWordUtils;
    @Autowired
    private SystemCoreConfigService configService;
    @ApiOperation("获取word报告模板")
    @GetMapping("/loadFtlList")
    public String loadFtlList() throws  Exception{
        String result = "";
        try {
            List<Map> list=analysisReportService.loadFtlList();
            result =  success(list,"获取word报告模板成功");
        } catch (Exception e) {
            super.errMsg("获取word报告模板失败", "word报告模板", e);
            result = failure(e.getMessage(), "获取word报告模板失败");
        }
        return result;
    }
    @ApiOperation("获取word报告模板列表")
    @RequestMapping("/loadFtlDataList")
    public String loadFtlDataList(String json) {
        String result="";
        try {
            Map map= (Map)JSON.parse(json);
            map.put("page",getParameter("page"));
            map.put("row",getParameter("rows"));
            Map data=analysisReportService.loadFtlDataList(map);
            result = success(data,"获取列表成功");
        } catch (Exception e) {
            super.errMsg("获取列表失败", json, e);
            result = failure(e.getMessage(), "获取信息列表失败");
        }
        return result;
    }

    @ApiOperation("根据ftl模板生成word")
    @GetMapping("/generateWord")
    public MessageResult generateWord(String json){
        String path = "";
        Map map= (Map)JSON.parse(json);
        try {
            String wzbsPath = configService.getSystemConfigValue("wzbsPath");
            String sourceFilePath=  wzbsPath + "/template/ftlFile";
            WordVo wordVo=new WordVo();
           // wordVo.setData(map);
            wordVo.setFileName(map.get("wordName")+"");
            wordVo.setFtl(map.get("ftlFile")+"");
            wordVo.setFtlPath(sourceFilePath);
            path =  exportWordUtils.exportMillCertificateWord(getRequest(),HttpContextUtils.getHttpServletResponse(),wordVo);
        }catch (Exception e){
            return new MessageResult("导出案件质量情况地区分析错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, path);
    }
}
