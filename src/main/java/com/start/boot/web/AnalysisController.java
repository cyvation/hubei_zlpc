package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.start.boot.common.MessageResult;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.pojo.vo.*;
import com.start.boot.query.ZdFxQuery;
import com.start.boot.service.AnalysisService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.structure.EasyUIDatagrid;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.HttpContextUtils;
import com.start.boot.utils.ExportExcelUtils;
import com.start.boot.utils.ExportWordUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import com.start.boot.domain.Param_Ajqkzlfx;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 湖北专题报告分析：统计表及报告
 */
@RestController
@RequestMapping("/analysis")
@SuppressWarnings("all")
public class AnalysisController extends ArchivesSystemBaseController{
    @Autowired
    private AnalysisService analysisService;

    @Autowired
    ExportExcelUtils excelUtils;

    @ApiOperation("获取错误/瑕疵项目时间分析")
    @RequestMapping("/loadDateData")
    public MessageResult loadDateData(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<ErrorAndFlawTreeVo> list=analysisService.loadDateData(map);
        String ajqks = "";
        PageInfo page = new PageInfo(list);
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("rows", list);
        jsonObjec.put("total", page.getTotal());
        ajqks = jsonObjec.toJSONString();
        return new MessageResult("获取成功",200,ajqks);
    }

    @ApiOperation("获取案件质量情况年度分析失败")
    @PostMapping("/getAjzlqkfxByNd")
    public MessageResult getAjzlqkfxByNd(Param_Ajqkzlfx param){
        String ajqks = "";
        try {
            List<AjqkzlflVo> list = analysisService.getAjqkzlfxByNf(param);
            PageInfo page = new PageInfo(list);
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("rows", list);
            jsonObjec.put("total", page.getTotal());
            ajqks = jsonObjec.toJSONString();
        }catch (Exception e){
            return new MessageResult("案件质量情况年度分析失败",400, e.getStackTrace());
        }
        return new MessageResult("获取成功",200, ajqks);
    }

    @ApiOperation("获取案件质量情况地区分析")
    @PostMapping("/getAjzlqkfxByDq")
    public MessageResult getAjzlqkfxByDq(Param_Ajqkzlfx param){
        String ajqks = "";
        try {
            List<AjqkzlflTreeVo> list = analysisService.getAjzlqkfxByDq(param);
            PageInfo page = new PageInfo(list);
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("rows", list);
            jsonObjec.put("total", page.getTotal());
            ajqks = jsonObjec.toJSONString();
        }catch (Exception e){
            return  new MessageResult("案件质量情况地区分析失败",400, e.getStackTrace());
        }
        return new MessageResult("获取成功",200, ajqks);
    }

    @ApiOperation("获取评查模板")
    @RequestMapping("/getPcdmByFdm")
    public String getPcdmByFdm(){
        String resultStr = "";
        try {
            Map params = new HashMap<String, Object>();
            params.put("fdm", "1");
            List<Map> list = analysisService.getPcdmByFdm(params);
            resultStr = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "DM", "FDM", "MC", "1");
        }catch (Exception e){
            super.errMsg("获取评查模板列表失败", null, e);
        }
        return resultStr;
    }

    @ApiOperation("获取评查总体时间分析")
    @RequestMapping("/loadDateGeneral")
    public MessageResult loadDateGeneral(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<Map> list=analysisService.loadDateGeneral(map);
        String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,s);
    }

    @ApiOperation("获取评查总体分析评查列表")
    @RequestMapping("/getPclbAjJbxx")
    public String getPclbAjJbxx(String json)throws  Exception{
        String result="";
        try {
            Map map= (Map)JSON.parse(json);
            map.put("page",getParameter("page"));
            map.put("row",getParameter("rows"));
            Map data=analysisService.getPclbAjJbxx(map);
            result = success(data,"获取列表成功");
        } catch (Exception e) {
            super.errMsg("获取列表失败", json, e);
            result = failure(e.getMessage(), "获取信息列表失败");
        }
        return result;
    }

    @ApiOperation("获取评查总体地区分析")
    @RequestMapping("/loadDqGeneral")
    public MessageResult loadDqGeneral(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<ErrorAndFlawTreeVo> list=analysisService.loadDqGeneral(map);
        String ajqks = "";
        PageInfo page = new PageInfo(list);
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("rows", list);
        jsonObjec.put("total", page.getTotal());
        ajqks = jsonObjec.toJSONString();
        // String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,ajqks);
    }

    @ApiOperation("获取评查总体地区分析")
    @RequestMapping("/loadTxGeneral")
    public MessageResult loadTxGeneral(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<ErrorAndFlawTreeVo> list=analysisService.loadTxGeneral(map);
        String ajqks = "";
        PageInfo page = new PageInfo(list);
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("rows", list);
        jsonObjec.put("total", page.getTotal());
        ajqks = jsonObjec.toJSONString();
        //String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,ajqks);
    }

    @ApiOperation("获取错误/瑕疵项目条线分析")
    @RequestMapping("/loadTxData")
    public MessageResult loadTxData(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<ErrorAndFlawTreeVo>  list=analysisService.loadTxData(map);
        String ajqks = "";
        PageInfo page = new PageInfo(list);
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("rows", list);
        jsonObjec.put("total", page.getTotal());
        ajqks = jsonObjec.toJSONString();
        //String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,ajqks);
    }
    @ApiOperation("获取错误/瑕疵项目地区分析")
    @RequestMapping("/loadDqdata")
    public MessageResult loadDqData(String json)throws  Exception{
        Map map= (Map)JSON.parse(json);
        List<ErrorAndFlawTreeVo> list=analysisService.loadDqData(map);
        String ajqks = "";
        PageInfo page = new PageInfo(list);
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("rows", list);
        jsonObjec.put("total", page.getTotal());
        ajqks = jsonObjec.toJSONString();
        return new MessageResult("获取成功",200,ajqks);
    }

    @ApiOperation("获取案件质量情况条线分析")
    @PostMapping("/getAjzlqkfxByTx")
    public MessageResult getAjzlqkfxByTx(Param_Ajqkzlfx param){
        String ajqks = "";
        try {
            List<AjqkzlflTreeVo> list = analysisService.getAjzlqkfxByTx(param);
            PageInfo page = new PageInfo(list);
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("rows", list);
            jsonObjec.put("total", page.getTotal());
            ajqks = jsonObjec.toJSONString();
        }catch (Exception e){
            return  new MessageResult("案件质量情况条线分析失败",400, e.getStackTrace());
        }
        return new MessageResult("获取成功",200, ajqks);
    }

    @ApiOperation("获取案件错误项目分析")
    @PostMapping("/getTcfx")
    public MessageResult getTcfx(Param_Ajqkzlfx param){
        String ajqks = "";
        try {
            List<Map> list = analysisService.getTcfx(param);
            PageInfo page = new PageInfo(list);
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("rows", list);
            jsonObjec.put("total", page.getTotal());
            ajqks = jsonObjec.toJSONString();
        }catch (Exception e){
            return   new MessageResult("突出问题项目分析错误",400, e.getStackTrace());
        }
        return new MessageResult("获取成功",200, ajqks);
    }
    @Autowired
    private ExportExcelUtils exportExcelUtils;
    @Autowired
    SystemCoreConfigService configService;
    @ApiOperation("错误瑕疵数据excel导出")
    @RequestMapping("/excel_export_data")
    public MessageResult excel_export_data(String json) throws  Exception {
        Map query=(Map) JSON.parse(json);
        List<List<String>> data = analysisService.excel_export_data(query);
        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
        String sourceFilePath="";
        if(query.get("flxtdm").equals("30002")){//错误模板
            if((query.get("type")+"").equals("0")){
                sourceFilePath = wzbsPath + "/File/monitor/moban/errItem_date.xls";
            }else if((query.get("type")+"").equals("1")){
                sourceFilePath = wzbsPath + "/File/monitor/moban/errItem_dq.xls";
            }else{
                sourceFilePath = wzbsPath + "/File/monitor/moban/errItem_tx.xls";
            }
        }else{
            if ((query.get("type")+"").equals("0")) {
                sourceFilePath = wzbsPath + "/File/monitor/moban/flawItem_date.xls";
            } else if ((query.get("type")+"").equals("1")) {
                sourceFilePath = wzbsPath + "/File/monitor/moban/flawItem_dq.xls";
            } else {
                sourceFilePath = wzbsPath + "/File/monitor/moban/flawItem_tx.xls";
            }
        }
        String fileName=query.get("excelName")+"";
        ExcelWriteToFile excelVo = new ExcelWriteToFile();
        excelVo.setFileName(fileName);
        excelVo.setStartLine(4);
        excelVo.setSourcefile(sourceFilePath);
        excelVo.setData(data);
        String path =  exportExcelUtils.exportExcelDataWriteLineNumber(excelVo);
        return new MessageResult("获取成功",200, path);
    }
    @ApiOperation("评查总体情况数据excel导出")
    @RequestMapping("/excel_export_dataPc")
    public MessageResult excel_export_dataPc(String json) throws  Exception {
        Map query=(Map) JSON.parse(json);
        List<List<String>> data = analysisService.excel_export_dataPc(query);
        List<String> header ;
        if((query.get("type")+"").equals("0")){
            header = Arrays.asList("年份", "1办案人数", "2办结案件数", "3评查人员数", "4评查案件数", "评查比例（4/2）","承办人平均被评查案件数（4/1）件", "评查员平均评查案件数（4/3）件");
        }else if((query.get("type")+"").equals("1")){
            header = Arrays.asList("地区", "1办案人数", "2办结案件数", "3评查人员数", "4评查案件数", "评查比例（4/2）","承办人平均被评查案件数（4/1）件", "评查员平均评查案件数（4/3）件");
        }else{
            header = Arrays.asList("条线", "1办案人数", "2办结案件数", "3评查人员数", "4评查案件数", "评查比例（4/2）","承办人平均被评查案件数（4/1）件", "评查员平均评查案件数（4/3）件");
        }
        String fileName=query.get("excelName")+"";
        ExcelVo excelVo = new ExcelVo();
        excelVo.setFileName(fileName);
        excelVo.setHeader(header);
        excelVo.setData(data);
        String path =  exportExcelUtils.exportExcelData(excelVo);
        return new MessageResult("获取成功",200, path);
    }

    @ApiOperation("获取案件评查项备注说明")
    @PostMapping("/getPcxBzByXtdm")
    public MessageResult getPcxBzByXtdm(String json){
        Map map = (Map)JSON.parse(json);
        String resultStr = "";
        try {
            List<Map> list = analysisService.getPcxBzByXtdm(map);
            PageInfo page = new PageInfo(list);
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("rows", list);
            jsonObjec.put("total", page.getTotal());
            resultStr = jsonObjec.toJSONString();
        }catch (Exception e){
            return  new MessageResult("突出问题项目分析错误",400, e.getStackTrace());
        }
        return new MessageResult("获取成功",200, resultStr);
    }

    @ApiOperation("获取案件评查项备注说明")
    @PostMapping("/getPcxBzByXtdmToExcel")
    public MessageResult getPcxBzByXtdmToExcel(String json){
        Map map = (Map)JSON.parse(json);
        String fileName = "";
        try {
            List<Map> list = analysisService.getPcxBzByXtdm(map);
            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("备注列表");
            List<String> header =  Arrays.asList("备注说明(已过滤重复内容和空备注)");
            List<List<String>> data = new ArrayList<List<String>>();
            for(int i=0;i<list.size();i++){
                Map m= list.get(i);
                List<String> sigleData = new ArrayList<>();
                sigleData.add((String)m.get("SM"));
                data.add(sigleData);
            }
            excelVo.setData(data);
            excelVo.setHeader(header);
            fileName = excelUtils.exportExcelData(excelVo);

        }catch (Exception e){
            return  new MessageResult("导出案件评查项备注错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, fileName);
    }

    @ApiOperation("获取瑕疵、错误分析评查列表")
    @RequestMapping("/getErrPclbAjJbxx")
    public String getErrPclbAjJbxx(String json)throws  Exception{
        String result="";
        try {
            Map map= (Map)JSON.parse(json);
            map.put("page",getParameter("page"));
            map.put("row",getParameter("rows"));
            Map data=analysisService.getErrPclbAjJbxx(map);
            result = success(data,"获取列表成功");
        } catch (Exception e) {
            super.errMsg("获取列表失败", json, e);
            result = failure(e.getMessage(), "获取信息列表失败");
        }
        return result;
    }



    @ApiOperation("导出案件质量情况年度分析")
    @PostMapping("/getAjzlqkfxByNdToExcel")
    public MessageResult getAjzlqkfxByNdToExcel(Param_Ajqkzlfx param){
        String path = "";
        try {
            List<List<String>> data = analysisService.getAjzlqkfxByNdToExcel(param);
            String wzbsPath = configService.getSystemConfigValue("wzbsPath");
            String sourceFilePath=  wzbsPath + "/File/monitor/moban/案件质量情况年度分析.xls";
            ExcelWriteToFile excelVo = new ExcelWriteToFile();
            excelVo.setFileName("案件质量情况年度分析");
            excelVo.setStartLine(2);
            excelVo.setSourcefile(sourceFilePath);
            excelVo.setData(data);
            path =  exportExcelUtils.exportExcelDataWriteLineNumber(excelVo);
        }catch (Exception e){
            return new MessageResult("导出案件质量情况年度分析错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, path);
    }

    @ApiOperation("导出案件质量情况条线分析")
    @PostMapping("/getAjzlqkfxByTxToExcel")
    public MessageResult getAjzlqkfxByTxToExcel(Param_Ajqkzlfx param){
        String path = "";
        try {
            List<List<String>> data = analysisService.getAjzlqkfxByTxToExcel(param);
            String wzbsPath = configService.getSystemConfigValue("wzbsPath");
            String sourceFilePath=  wzbsPath + "/File/monitor/moban/案件质量情况条线分析.xls";
            ExcelWriteToFile excelVo = new ExcelWriteToFile();
            excelVo.setFileName("案件质量情况条线分析");
            excelVo.setStartLine(2);
            excelVo.setSourcefile(sourceFilePath);
            excelVo.setData(data);
            path =  exportExcelUtils.exportExcelDataWriteLineNumber(excelVo);
        }catch (Exception e){
            return new MessageResult("导出案件质量情况条线分析错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, path);
    }

    @ApiOperation("导出案件质量情况地区分析")
    @PostMapping("/getAjzlqkfxByDqToExcel")
    public MessageResult getAjzlqkfxByDqToExcel(Param_Ajqkzlfx param){
        String path = "";
        try {
            List<List<String>> data = analysisService.getAjzlqkfxByDqToExcel(param);
            String wzbsPath = configService.getSystemConfigValue("wzbsPath");
            String sourceFilePath=  wzbsPath + "/File/monitor/moban/案件质量情况地区分析.xls";
            ExcelWriteToFile excelVo = new ExcelWriteToFile();
            excelVo.setFileName("案件质量情况地区分析");
            excelVo.setStartLine(2);
            excelVo.setSourcefile(sourceFilePath);
            excelVo.setData(data);
            path =  exportExcelUtils.exportExcelDataWriteLineNumber(excelVo);
        }catch (Exception e){
            return new MessageResult("导出案件质量情况地区分析错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, path);
    }
    @Autowired
    private ExportWordUtils exportWordUtils;
    @ApiOperation("")
    @GetMapping("/test")
    public MessageResult test(){
        String path = "";
        try {
            String wzbsPath = configService.getSystemConfigValue("wzbsPath");
            String sourceFilePath=  wzbsPath + "/template/ftlFile";
            Map map=new HashMap<>();
            map.put("name","大苏打撒");
            WordVo wordVo=new WordVo();
            wordVo.setData(map);
            wordVo.setFileName("text.doc");
            wordVo.setFtl("11.ftl");
            wordVo.setFtlPath(sourceFilePath);
            path =  exportWordUtils.exportMillCertificateWord(getRequest(),HttpContextUtils.getHttpServletResponse(),wordVo);
        }catch (Exception e){
            return new MessageResult("导出案件质量情况地区分析错误",400, e.getStackTrace());
        }
        return new MessageResult("导出成功",200, path);
    }

    @ApiOperation("获取评查案件信息")
    @PostMapping("/getPcAjxxByParams")
    public String getPcAjxxByParams(Param_Ajqkzlfx param){
        Map<String, Object> map = new HashMap<String, Object>();
        String resultStr = "";
        try {
            map.put("dwbm", param.getDwbm());
            map.put("cbrsf", param.getCbrsf());
//            map.put("wcrqnf", param.getWcrqnf());
            map.put("startDate", param.getStartDate());
            map.put("endDate", param.getEndDate());
            map.put("pcstartDate", param.getPcstartDate());
            map.put("pcendDate", param.getPcendDate());

            map.put("pcjl", param.getPcjl());
            map.put("ywtx", param.getYwtx());
            map.put("pcflbm", param.getPcflbm());
            map.put("ajlbbm", param.getAjlbbm());
            map.put("ajtjlb", param.getAjtjlb());
            map.put("page", param.getPage());
            map.put("rows", param.getRows());
            List<Map> list = analysisService.getPcAjxxByParams(map);
            PageInfo page = new PageInfo(list);
            resultStr = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        }catch (Exception e){
            return "";
        }
        return resultStr;
    }

    @ApiOperation("获取突出错误/瑕疵评查案件信息")
    @PostMapping("/getTccwxxPcAjxxByParams")
    public String getTccwxxPcAjxxByParams(Param_Ajqkzlfx param){
        Map<String, Object> map = new HashMap<String, Object>();
        String resultStr = "";
        try {
            map.put("dwbm", param.getDwbm());
            map.put("cbrsf", param.getCbrsf());

           // map.put("wcrqnf", param.getWcrqnf());
            map.put("startDate", param.getStartDate());
            map.put("endDate", param.getEndDate());
            map.put("pcstartDate", param.getPcstartDate());
            map.put("pcendDate", param.getPcendDate());

            map.put("ywtx", param.getYwtx());
            map.put("pcflbm", param.getPcflbm());
            map.put("ajtjlb", param.getAjtjlb());
            map.put("sm", param.getSm());
            map.put("xtdm", param.getXtdm());
            map.put("flxtdm", param.getWtType());
            map.put("fflxtdm", param.getFflxtdm());
            map.put("page", param.getPage());
            map.put("rows", param.getRows());
            List<Map> list = analysisService.getTccwxxPcAjxxByParams(map);
            PageInfo page = new PageInfo(list);
            resultStr = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        }catch (Exception e){
            return e.toString();
        }
        return resultStr;
    }

    @ApiOperation("获取重点案件分析下的筛选规则")
    @GetMapping("/getSxgz")
    public MessageResult getSxgz(String pcflbm){
        MessageResult result = null;

        try {
            String djdwbm = SystemConfiguration.djdwbm;
            List<Map> list = analysisService.getZdSxgz(djdwbm,pcflbm);
            String s = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", "-1");
            result = new MessageResult(200, s);
        }catch (Exception e){
            result = new MessageResult(500,e.getMessage());
        }

        return result;
    }


    @ApiOperation("获取重点案件总体情况")
    @RequestMapping("/loadZdZtqk")
    public MessageResult loadZdZtqk( ZdFxQuery zdFxQuery)throws  Exception{

        List<ZdFxTreeVo> list=analysisService.loadZdZtqk(zdFxQuery);

        //String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,list);
    }

    @ApiOperation("获取重点案件质量分析")
    @RequestMapping("/loadZdZlfx")
    public MessageResult loadZdZlfx( ZdFxQuery zdFxQuery)throws  Exception{

        List<ZdFxTreeVo> list=analysisService.loadZdZlfx(zdFxQuery);

        //String s = EasyUIHelper.buildDataGridDataSource(list,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,list);
    }

    @ApiOperation("获取重点案件详细信息")
    @RequestMapping("/getZdAjJbxx")
    public MessageResult getZdAjJbxx( ZdFxQuery zdFxQuery)throws  Exception{

        List<Map> list=analysisService.getZdAjJbxx(zdFxQuery);

        PageInfo pageInfo = new PageInfo(list);
        EasyUIDatagrid<Map> datagrid = new EasyUIDatagrid<>();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(pageInfo.getList());

        return new MessageResult("获取成功",200,datagrid);
    }

    @ApiOperation("导出重点案件分析")
    @RequestMapping("/exportZdFxExcel")
    public MessageResult exportZdFxExcel( ZdFxQuery zdFxQuery)throws  Exception{

        MessageResult messageResult =null;
        try {
            String filePath = analysisService.exportZdFxExcel(zdFxQuery);

            messageResult = new MessageResult("导出成功", 200, filePath);

        }catch (Exception e){
            messageResult = new MessageResult("导出失败", 500, e.getMessage() );
        }

        return messageResult;
    }


}
