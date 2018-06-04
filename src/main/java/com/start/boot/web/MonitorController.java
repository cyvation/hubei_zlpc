package com.start.boot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.pojo.dto.GzbmDto;
import com.start.boot.pojo.dto.PcglDto;
import com.start.boot.pojo.dto.PcglViewDto;
import com.start.boot.pojo.vo.ExcelVo;
import com.start.boot.query.MonitoryPcjsQuery;
import com.start.boot.query.PcglQuery;
import com.start.boot.service.MonitorService;
import com.start.boot.support.structure.EasyUITree;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.utils.ExportExcelUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caomin
 * @date 2018/3/20
 * @说明 监控
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {


    @Autowired
    MonitorService  monitorService;

    @Autowired
    ExportExcelUtils exportExcelUtils;


    @ApiOperation("获取评查概览数据")
    @GetMapping("/getpcyl")
    public MessageResult getPcyl(PcglQuery query)throws  Exception{
        PcglViewDto pcgl = monitorService.pcgl(query);
        return  new MessageResult("成功",200,pcgl);
    }

    @ApiOperation("获取评查概览数据案件基本信息")
    @RequestMapping("/getPcylAjJbxx")
    public MessageResult getPcylAjJbxx(PcglQuery query)throws  Exception{
        PageHelper.startPage(query.getPage(),query.getRows());
        List<Map> wpcAjJbxx =null;
        if ("true".equalsIgnoreCase(query.getWpc())){
            wpcAjJbxx= monitorService.getWpcAjJbxx(query);
        }else{
            wpcAjJbxx=monitorService.getYpcAjJbxx(query);
        }
        PageInfo pageInfo=new PageInfo(wpcAjJbxx);

        String s = EasyUIHelper.buildDataGridDataSource(wpcAjJbxx,Math.toIntExact(pageInfo.getTotal()));
        return new MessageResult("获取成功",200,s);
    }


    @ApiOperation("评查概览excel导出")
    @RequestMapping("/pcglExportExcel")
    public MessageResult pcglExportExcel(PcglQuery query)throws Exception{
       // PageHelper.startPage(query.getPage(),query.getRows());
        PcglViewDto pcgl = monitorService.pcgl(query);
        ExcelVo excelVo=new ExcelVo();
        List<String> headers = pcgl.getHeader().stream().map(t -> t.getGzmc()).collect(Collectors.toList());
        headers.add(0,"单位名称");
        excelVo.setHeader(headers);
        List<PcglDto> data = pcgl.getData();
        List<List<String>>excelData=new ArrayList<>();
        data.stream().forEach(t->{
            ArrayList<String> rowData = new ArrayList<>();
            rowData.add(t.getDwmc()) ;
            List<GzbmDto> gzbmDtoList =
                    t.getGzbmDtoList();
            gzbmDtoList.stream().forEach(gz->{
                rowData.add(gz.getYpc().toString());
            });
            excelData.add(rowData);
        });
        excelVo.setData(excelData);
        excelVo.setFileName("评查概览统计");

        String s = exportExcelUtils.exportExcelData(excelVo);
        return new MessageResult("获取成功",200,s);
    }
    @ApiOperation("获取评查概览数据案件基本信息")
    @RequestMapping("/getDwGroup")
    public MessageResult getDwGroup(MonitoryPcjsQuery monitoryPcjsQuery)throws  Exception{

        List<EasyUITree> dwGroup = monitorService.getDwGroup(monitoryPcjsQuery);

        return new MessageResult("获取成功",200,dwGroup);
    }



}
