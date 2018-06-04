package com.start.boot.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.XtZzjgBmbm;
import com.start.boot.domain.XtZzjgRmbm;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.pojo.vo.QueryTableVo;
import com.start.boot.query.QueryTable;
import com.start.boot.service.*;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.utils.ExportExcelUtils;
import com.start.boot.query.QueryTableAjJbxx;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明 三表Controller
 */
@RestController
@RequestMapping("/queryTable")
public class QueryTableController {

    @Autowired
    QueryTableService queryTableService;

    @Autowired
    XtZzjgBmbmService xtZzjgBmbmService;

    @Autowired
    XtZzjgRmbmService zzjgRmbmService;

    @Autowired
    private ExportExcelUtils exportExcelUtils;

    @Autowired
    SystemCoreConfigService configService;

    @Autowired
    private AjwthzService ajwthzService;

    @ApiOperation(" 获取单位表格数据 ")
    @PostMapping("/getDwTableData")
    @org.springframework.cache.annotation.Cacheable("getDwTableData")
    public MessageResult getDwTableData(QueryTable query){
        PageHelper.startPage(query.getPage(), query.getRows());
        List<QueryTableVo> list = queryTableService.getDwTableData(query);
        ArrayList<Map> resultMap = new ArrayList<>();
        list.stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,s);
    }

    @ApiOperation(" 获取部门表格数据 ")
    @PostMapping("/getBmTableData")
    @Cacheable("getBmTableData")
    public MessageResult getBmTableData(QueryTable query){
        PageHelper.startPage(query.getPage(), query.getRows());
        List<QueryTableVo> list = queryTableService.getBmTableData(query);
        PageInfo page = new PageInfo(list);
        if (!CollectionUtils.isEmpty(page.getList())){
            ArrayList<Map> resultMap = new ArrayList<>();
            list.stream().forEach(message -> {
                try {
                    Map describe = BeanUtils.describe(message);
                    resultMap.add(describe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(list.size()));
            return new MessageResult("获取成功",200,s);
        }
        return new MessageResult("没有数据",500,"");
    }

    @ApiOperation(" 获取检察官表格数据 ")
    @GetMapping("/getJcgTableData")
    @Cacheable("getJcgTableData")
    public MessageResult getJcgTableData(QueryTable query){
        List<QueryTableVo> list = queryTableService.getJcgTableData(query);
        PageInfo page = new PageInfo(list);
        if (!CollectionUtils.isEmpty(page.getList())){
            ArrayList<Map> resultMap = new ArrayList<>();
            page.getList().stream().forEach(message -> {
                try {
                    Map describe = BeanUtils.describe(message);
                    resultMap.add(describe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(page.getTotal()));
            return new MessageResult("获取成功",200,s);
        }
        return new MessageResult("没有数据",500,"");
    }

    @ApiOperation("获取已评查案件信息 ")
    @PostMapping("/getAjjbxx")
    public String getSjCountAjjbxx(QueryTableAjJbxx query){

        List<Map> pcxFlCountAjjbxx = queryTableService.getAjjbxx(query);
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);
        String  s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }

    @ApiOperation("获取部门下已评查案件信息 ")
    @PostMapping("/getAjjbxxByBm")
    public String getAjjbxxByBm(QueryTableAjJbxx query){

        if (CollectionUtils.isEmpty(query.getBmbmList())) {
            // 查出该单位下所有部门
            List<XtZzjgBmbm> dwbmTreeList = xtZzjgBmbmService.getbmbmTreeList(query.getDwbmList().get(0));
            List<String> collect = dwbmTreeList.stream().map(XtZzjgBmbm::getBmbm).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(collect)) {
                query.setBmbmList(collect);
            }
        }

        List<Map> pcxFlCountAjjbxx = queryTableService.getAjjbxx(query);
        ArrayList<Map> resultMap = new ArrayList<>();
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);

        String s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }

    @ApiOperation("获取检察官下已评查部门案件信息 ")
    @PostMapping("/getAjjbxxByJcg")
    public String getAjjbxxByJcg( QueryTableAjJbxx query){

        if (CollectionUtils.isEmpty(query.getGhList())) {
            // 查出该单位、部门下所有检察官
            List<XtZzjgRmbm> dwBmRmTreeList = zzjgRmbmService.getDwBmRmTreeList(query.getDwbmList().get(0), query.getBmbmList().get(0),query);
            List<String> collect = dwBmRmTreeList.stream().map(XtZzjgRmbm::getGh).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(collect)) {
                query.setGhList(collect);
            }
        }

        List<Map> pcxFlCountAjjbxx = queryTableService.getAjjbxx(query);
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);

        String s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }

    @ApiOperation("获取已审结未评查案件信息 ")
    @PostMapping("/getWpcAjjbxx")
    public String getWpcAjjbxx(QueryTableAjJbxx query){
        PageHelper.startPage(query.getPage(), query.getRows());
        List<Map> pcxFlCountAjjbxx = queryTableService.getWpcAjjbxx(query);
        ArrayList<Map> resultMap = new ArrayList<>();
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);
        String s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }

    @ApiOperation(" 查询评查的案件基本信息，通用接口 ")
    @GetMapping("/getPcxFlCountAjjbxx")
    public MessageResult getPcxFlCountAjjbxx(QueryTable query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> pcxFlCountAjjbxx = queryTableService.getPcxFlCountAjjbxx(query);
        ArrayList<Map> resultMap = new ArrayList<>();
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);
        page.getList().stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(page.getTotal()));
        return new MessageResult("获取成功",200,s);
    }

    @ApiOperation("单位excel导出")
    @RequestMapping("/exportDwExcel")
    public MessageResult exportDwExcel(QueryTable query) throws  Exception {
        PageHelper.startPage(query.getPage(),query.getRows());
        ArrayList<QueryTableVo> dwTableData = queryTableService.getDwTableData(query);
        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
        String sourceFilePath = wzbsPath + "/File/monitor/moban/1.xls";
        String fileName="评查统计单位数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
        String path = exportExcelUtils.exportExcelToBean(dwTableData,5,fileName,"sheet1","", sourceFilePath);
        return new MessageResult("获取成功",200, path);
    }

    @ApiOperation(" 获取单位案件问题汇总数据 ")
    @PostMapping("/getDwAjwthzTableData")
    @org.springframework.cache.annotation.Cacheable("getDwAjwthzTableData")
    public MessageResult getDwAjwthzTableData(QueryTable query){
        PageHelper.startPage(query.getPage(), query.getRows());
        List<AjpcwtxVo> list = null;
        try {
           list = ajwthzService.getAjwthzList(query);
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Map> resultMap = new ArrayList<>();
        list.stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,s);
    }

    @ApiOperation(" 获取单位线下评查案件问题汇总数据 ")
    @PostMapping("/getDwOfflineAjwthzTableData")
    @org.springframework.cache.annotation.Cacheable("getDwOfflineAjwthzTableData")
    public MessageResult getDwOfflineAjwthzTableData(QueryTable query){
        PageHelper.startPage(query.getPage(), query.getRows());
        List<AjpcwtxVo> list = null;
        try {
            list = ajwthzService.getOfflineAjwthzList(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<Map> resultMap = new ArrayList<>();
        list.stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(list.size()));
        return new MessageResult("获取成功",200,s);
    }

    @ApiOperation("获取已评查案件信息")
    @PostMapping("/getAjwthzjbxx")
    public String getAjwthzjbxx(QueryTableAjJbxx query){
        List<Map> ajjbxxs = queryTableService.getAjwthzjbxx(query);
        PageInfo page = new PageInfo(ajjbxxs);
        String  s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }

    @ApiOperation("获取线下已评查案件信息")
    @PostMapping("/getOfflineAjwthzjbxx")
    public String getOfflineAjwthzjbxx(QueryTableAjJbxx query){
        List<Map> ajjbxxs = queryTableService.getOfflineAjwthzjbxx(query);
        PageInfo page = new PageInfo(ajjbxxs);
        String  s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }


    @ApiOperation("单位案件问题汇总数据excel导出")
    @RequestMapping("/exportDwAjwthzTableDataExcel")
    public MessageResult exportDwAjwthzTableDataExcel(QueryTable query) throws  Exception {
        PageHelper.startPage(query.getPage(),query.getRows());
        List<AjpcwtxVo> dwTableData = ajwthzService.getAjwthzList(query);;
        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
        String sourceFilePath = wzbsPath + "/File/monitor/moban/案件问题汇总.xls";
        String fileName="案件问题汇总数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
        String path = exportExcelUtils.exportExcelToBean(dwTableData,2,fileName,"sheet1","", sourceFilePath);
        return new MessageResult("获取成功",200, path);
    }

    @ApiOperation("单位案件问题汇总柱状图数据")
    @RequestMapping("/getDwAjwthzBarData")
    public String getDwAjwthzBarData(QueryTable query){
        String result = "";
        try {
            result = ajwthzService.getDwAjwthzBarData(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("线下案件问题汇总柱状图数据")
    @RequestMapping("/getOfflineDwAjwthzBarData")
    public String getOfflineDwAjwthzBarData(QueryTable query){
        String result = "";
        try {
            result = ajwthzService.getDwOfflineAjwthzBarData(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("线下案件问题汇总数据excel导出")
    @RequestMapping("/getDwOfflineAjwthzTableDataExcel")
    public MessageResult getDwOfflineAjwthzTableDataExcel(QueryTable query) throws  Exception {
        PageHelper.startPage(query.getPage(),query.getRows());
        List<AjpcwtxVo> dwTableData = ajwthzService.getOfflineAjwthzList(query);;
        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
        String sourceFilePath = wzbsPath + "/File/monitor/moban/案件问题汇总.xls";
        String fileName="线下案件问题汇总数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
        String path = exportExcelUtils.exportExcelToBean(dwTableData,2,fileName,"sheet1","", sourceFilePath);
        return new MessageResult("获取成功",200, path);
    }

//    @ApiOperation("部门excel导出")
//    @RequestMapping("/exportBmExcel")
//    public MessageResult exportBmExcel(QueryTable query) throws  Exception {
//        PageHelper.startPage(query.getPage(),query.getRows());
//        ArrayList<QueryTableVo> dwTableData = queryTableService.getBmTableData(query);
//        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
//        String sourceFilePath = wzbsPath + "/File/monitor/moban/2.xls";
//        String fileName="评查统计部门数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
//        String path = exportExcelUtils.exportExcelToBean(dwTableData,5,fileName,"sheet1","", sourceFilePath);
//        return new MessageResult("获取成功",200,path);
//    }

//    @ApiOperation("检察官excel导出")
//    @RequestMapping("/exportJcgExcel")
//    public MessageResult exportJcgExcel(QueryTable query) throws  Exception {
//        PageHelper.startPage(query.getPage(),query.getRows());
//        ArrayList<QueryTableVo> dwTableData = queryTableService.getJcgTableData(query);
//        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
//        String sourceFilePath = wzbsPath + "/File/monitor/moban/3.xls";
//        String fileName="评查统计检察官数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
//        String path = exportExcelUtils.exportExcelToBean(dwTableData,5,fileName,"sheet1","", sourceFilePath);
//        return new MessageResult("获取成功",200,path);
//    }

//    @ApiOperation("查询审结的案件基本信息，通用接口 ")
//    @GetMapping("/getSjCountAjjbxx")
//    public MessageResult getSjCountAjjbxx(QueryTableAjJbxx query){
//        PageHelper.startPage(query.getPage(),query.getRows());
//        List<Map> pcxFlCountAjjbxx = queryTableService.getSjCountAjjbxx(query);
//        ArrayList<Map> resultMap = new ArrayList<>();
//        PageInfo page = new PageInfo(pcxFlCountAjjbxx);
//        page.getList().stream().forEach(message -> {
//            try {
//                Map describe = BeanUtils.describe(message);
//                resultMap.add(describe);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        String s = EasyUIHelper.buildDataGridDataSource(resultMap,Math.toIntExact(page.getTotal()));
//        return new MessageResult("获取成功",200,s);
//    }
}
