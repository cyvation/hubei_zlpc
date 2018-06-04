package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.XtZzjgBmbm;
import com.start.boot.domain.XtZzjgRmbm;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.pojo.vo.QueryTableVo;
import com.start.boot.query.QueryTable;
import com.start.boot.query.QueryTableAjJbxx;
import com.start.boot.service.*;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.utils.ExportExcelUtils;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/queryAll")
public class QueryAllController {

    @Autowired
    QueryAllService queryallService;

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
    @PostMapping("/getZlpcTableData")
    @Cacheable("getZlpcTableData")
    public MessageResult getZlpcTableData(String json){
        Map query=(Map) JSON.parse(json);
        List<QueryTableVo> list = queryallService.getZlpcTableData(query);
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
    @ApiOperation("获取已评查案件信息 ")
    @PostMapping("/getAjjbxx")
    public String getSjCountAjjbxx(QueryTableAjJbxx query){

        List<Map> pcxFlCountAjjbxx = queryallService.getAjjbxx(query);
        PageInfo page = new PageInfo(pcxFlCountAjjbxx);
        String  s = EasyUIHelper.buildDataGridDataSource(page.getList(),Math.toIntExact(page.getTotal()));
        return s;
    }
    @ApiOperation("单位excel导出")
    @RequestMapping("/exportDwExcel")
    public MessageResult exportDwExcel(String json) throws  Exception {
       // PageHelper.startPage(query.getPage(),query.getRows());
        Map query=(Map) JSON.parse(json);
        List<QueryTableVo> dwTableData = queryallService.getZlpcTableData(query);
        String wzbsPath = configService.getSystemConfigValue("wzbsPath");
        String sourceFilePath = wzbsPath + "/File/monitor/moban/zlpc_hb.xls";
        String fileName="评查统计数据导出--"+ DateTime.now().toString("yyyy年MM月dd日");
        String path = exportExcelUtils.exportExcelToBean(dwTableData,5,fileName,"sheet1","", sourceFilePath);
        return new MessageResult("获取成功",200, path);
    }
}
