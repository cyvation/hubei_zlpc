package com.start.boot.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.common.Param_Pager;
import com.start.boot.constant.SessionNames;
import com.start.boot.dao.ajpc.QueryMapper;
import com.start.boot.domain.Param_Pcjk;
import com.start.boot.domain.XtDmFldm;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.dto.*;
import com.start.boot.pojo.vo.*;
import com.start.boot.query.ShPcjbqkerQuery;
import com.start.boot.query.ShYPNAQuery;
import com.start.boot.query.YearProblemAnalyzeQuery;
import com.start.boot.service.CountService;
import com.start.boot.service.FilterService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.service.XtDmFldmService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import com.start.boot.support.utils.HttpContextUtils;
import com.start.boot.utils.ExportExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DeflaterOutputStream;

@Api("统计接口")
@RestController
@RequestMapping("/count")
public class CountController {

    private  static Log logger= LogFactory.getLog(CountController.class);

    @Autowired
    FilterService filterService ;

    @Autowired
    CountService countService;

    @Autowired
    QueryMapper queryMapper;

    @Autowired
    XtDmFldmService xtDmFldmService;

    @Autowired
    ExportExcelUtils excelUtils;

    @Autowired
    SystemCoreConfigService systemCoreConfigService;

    @ApiOperation("案件质量年度趋势图")
    @GetMapping("/getCount")
    public Object getCount(Integer year,String pcflbm){
        CountDo countDo=new CountDo();
        List<String> pljgType = queryMapper.getPljgType();
        List<CountDo> init = countDo.init(pljgType);
        try {
            Map<Integer, List<CountDo>> pcByYear = countService.getPcByYear(year, pcflbm);
            Set<CountVo> voArrayList = new HashSet<>();
            if (pcByYear!=null){
                Set<Map.Entry<Integer, List<CountDo>>> entries = pcByYear.entrySet();
                entries.stream().forEach(data->{
                    for (CountDo aDo : init) {
                        if (!data.getValue().contains(aDo)){
                            data.getValue().add(aDo);
                        }
                    }
                });
                entries.stream().forEach(data-> {
                    CountVo vo = new CountVo();
                    vo.setName(data.getKey());
                    vo.setValue(data.getValue());
                    voArrayList.add(vo);
                });
            }
            List<CountVo> collect = voArrayList.stream().sorted(Comparator.comparing(CountVo::getName)).collect(Collectors.toList());
            return collect;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @ApiOperation("案件质量年度饼图")
    @GetMapping("/getYearCount")
    public Object getYearCount(Integer year,String pcflbm){
        try {
            return countService.getYearPercentByYearAndPcflbm(year,pcflbm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation("问题性质分析统计分析数据表格")
    @GetMapping("/getPcxFlByYearAndMbbmAndPcjg")
    public Object getPcxFlByYearAndMbbmAndPcjg(String pcflbm,Integer year){
        try {
            return countService.getPcxFlByYearAndMbbmAndPcjg(pcflbm, year);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation("问题性质分析统计分析饼图")
    @GetMapping("/getPcxFlByYearAndBinTu")
    public Object getPcxFlByYearAndBinTu(String pcflbm,Integer year,String pcjg){
        try {
            return countService.getPcxFlByYearAndBinTu( pcflbm, year,pcjg);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ApiOperation("问题性质分析统计分析饼图")
    @GetMapping("/getAjzlLinesByYearAndPcflbm")
    public Object getAjzlLinesByYearAndPcflbm(Integer year,String pcflbm){
        try {
            return countService.getAjzlLinesByYearAndPcflbm(year, pcflbm);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    @ApiOperation("问题性质分析统计分析饼图")
    @GetMapping("/getAjzlPieByYearAndPcflbm")
    public Object getAjzlPieByYearAndPcflbm(Integer year,String pcflbm){
        try {
            return countService.getAjzlPieByYearAndPcflbm(year, pcflbm);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    //=======================上海
    @ApiOperation("评查基本情况2")
    @GetMapping("/shPcjbqker")
    public Map<String, Object> shPcjbqker(ShPcjbqkerQuery query){
        //评查
        List<ShPcjbqkerMothDto> shPcjbqkerMothDtos = countService.shPcjbqkerGetYearMonthPcjlIsNull(query);
        List<ShPcjbqkerVo>pcColData=new ArrayList<>();

        Integer[] pcBgData = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};

        shPcjbqkerMothDtos.stream().forEach(t->{
            query.setMonth(t.getMonth());
            List<ShPcjbqkerDto> pcslbmList = countService.shPcjbqkerGetPcslbmPcjlIsNull(query);
            ShPcjbqkerVo shPcjbqkerVo = new ShPcjbqkerVo();
            shPcjbqkerVo.setMonth(t.getMonth());
            shPcjbqkerVo.setCount(pcslbmList.size());
            shPcjbqkerVo.setPcslbmList(pcslbmList);
            pcColData.add(shPcjbqkerVo);
            pcBgData[t.getMonth()-1]=pcslbmList.size();
        });
        //审结
        Integer[] sjBgData = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};
        List<ShPcjbqkerVo>sjColData=new ArrayList<>();

        List<ShPcjbqkerMothDto> shPcjbqkerMothDtos1 = countService.shPcjbqkerGetYearMonthPcjlIsNotNull(query);

        shPcjbqkerMothDtos1.stream().forEach(t->{
            query.setMonth(t.getMonth());
            List<ShPcjbqkerDto> pcslbmList = countService.shPcjbqkerGetPcslbmPcjlIsNotNull(query);
            ShPcjbqkerVo shPcjbqkerVo = new ShPcjbqkerVo();
            shPcjbqkerVo.setMonth(t.getMonth());
            shPcjbqkerVo.setCount(pcslbmList.size());
            shPcjbqkerVo.setPcslbmList(pcslbmList);
            sjColData.add(shPcjbqkerVo);
            sjBgData[t.getMonth()-1]=pcslbmList.size();
        });

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("sjColData",sjColData);
        resultMap.put("sjBgData",sjBgData);
        resultMap.put("pcColData",pcColData);
        resultMap.put("pcBgData",pcBgData);

        return resultMap;
    }




    @ApiOperation("年度问题性质分布图，饼图")
    @GetMapping("/shYearProlbemNatureAnalyze")
    public Object shYearProlbemNatureAnalyze(ShYPNAQuery query){
       return countService.shYearProlbemNatureAnalyze(query);
    }


    @ApiOperation("根据条件查询案件列表，饼图")
    @GetMapping("/getAjjbxxYearProlbemNatureAnalyze")
    public MessageResult getAjjbxxYearProlbemNatureAnalyze(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> ajjbxxYearProlbemNatureAnalyze = countService.getAjjbxxYearProlbemNatureAnalyze(query);
        PageInfo page = new PageInfo(ajjbxxYearProlbemNatureAnalyze);
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


    @ApiOperation("案件质量年度趋势图，柱状")
    @GetMapping("/shGetYearProblemAnalyze")
    public  List<YearProblemAnalyzeResultVo> shGetYearProblemAnalyze(YearProblemAnalyzeQuery query) {

        List<YearProblemAnalyzeResultVo> zxResult=new ArrayList<>();
        List<YearProblemAnalyzeDto>yearMonthList=new ArrayList<>();

        int startYear=0;
        int startMonth=0;
        int endYear=0;
        int endMonth=0;

        DateTime now = DateTime.now();
        String parse = query.getWcrqStart();
        DateTime wcrqStart=null;
        String parse1 = query.getWcrqEnd();
        DateTime wcrqEnd=null;
        if (!StringUtils.isEmpty(parse1)){
            wcrqEnd= DateTime.parse(parse1);
        }
        if (!StringUtils.isEmpty(parse)){
            wcrqStart  = DateTime.parse(parse);
        }
        if (wcrqStart ==null){
            startYear=now.getYear();
            startMonth=1;
            query.setWcrqStart(startYear+"/"+startMonth+"/1");
        }else {
            startYear=wcrqStart.getYear();
            startMonth=wcrqStart.getMonthOfYear();
        }
        if (wcrqEnd ==null){
            endYear  = now.getYear();
            endMonth = 12;
        }else {
            endYear  = wcrqEnd.getYear();
            endMonth = wcrqEnd.getMonthOfYear();
        }
        List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy("9102");

        //获取  年 月 评查结论 数量
        List<YearProblemAnalyzeDto> result = queryMapper.shGetYearProblemAnalyze(query);
        if (CollectionUtils.isEmpty(result)){
            return null;
        }
        //处理初始化数据
        if (startYear==endYear){
            for (int i = startMonth; i <= endMonth; i++) {
                addDto(yearMonthList, startYear, i);
            }
        }else {
            for (int i = startMonth; i <= 12; i++) {
                addDto(yearMonthList, startYear, i);
            }
            for (int i  = startYear+1; i < endYear ; i++) {
                for (int j = 1; j <= 12; j++) {
                    addDto(yearMonthList, i, j);
                }
            }
            for (int i = 1; i <= endMonth; i++) {
                addDto(yearMonthList, endYear, i);
            }
        }

        //准备空数据，构造
        List<Integer> temp = yearMonthList.stream().map(YearProblemAnalyzeDto::getCount).collect(Collectors.toList());

        List<YearProblemAnalyzeDto> tempList=yearMonthList;
        xtDmFldmBy.stream().forEach(t->{
            tempList.stream().forEach(t1->{t1.setCount(0);});
            query.setPcjl(t.getMc());
            //查评查结论为xx的结果
            List<YearProblemAnalyzeDto> pcjlResult = queryMapper.shGetYearProblemAnalyzeResult(query);
            tempList.stream().forEach(y->{
                    pcjlResult.stream().forEach(r->{
                    if (r.getYear().equals(y.getYear())&&r.getMonth().equals(y.getMonth())){
                        y.setCount(r.getCount());
                        y.setPcjl(r.getPcjl());
                    }
                });
            });
            List<Integer> zResult = tempList.stream().map(YearProblemAnalyzeDto::getCount).collect(Collectors.toList());
            YearProblemAnalyzeResultVo vo=new YearProblemAnalyzeResultVo();
            vo.setData(zResult);
            vo.setPcjl(t.getMc());
            Set<String> collect = zxResult.stream().map(YearProblemAnalyzeResultVo::getPcjl).collect(Collectors.toSet());
            if (!collect.contains(t.getMc())){
                zxResult.add(vo);
            }
        });

        //构造评查结论为空的数据
        Set<String> collect1 = xtDmFldmBy.stream().map(XtDmFldm::getMc).collect(Collectors.toSet());
        Set<String> collect = zxResult.stream().map(YearProblemAnalyzeResultVo::getPcjl).collect(Collectors.toSet());
        collect1.forEach(t->{
            if (collect.add(t)){
                YearProblemAnalyzeResultVo vo=new YearProblemAnalyzeResultVo();
                vo.setData(temp);
                vo.setPcjl(t);
                zxResult.add(vo);
            }
        });
        return zxResult;
    }



    @ApiOperation("柱状图获取案件基本信息")
    @GetMapping("/shGetYearProblemAnalyzeJbxx")
    public MessageResult shGetYearProblemAnalyzeJbxx(YearProblemAnalyzeQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> yx_pc_jbxxes = countService.shGetYearProblemAnalyzeJbxx(query);
        PageInfo page = new PageInfo(yx_pc_jbxxes);
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


    @ApiOperation("根据条件查询案件列表，评查基本情况二")
    @GetMapping("/shPcjbqkerGetPcslbmPcjlIsNotNullJbxx")
    public MessageResult shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(ShPcjbqkerQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> ajjbxxYearProlbemNatureAnalyze=null;
        if(query.getWczt().endsWith("sjs")){
            ajjbxxYearProlbemNatureAnalyze = countService.shPcjbqkerGetPcslbmPcjlIsNotNullSxjl(query);
            List<YX_PC_JBXX> yx_pc_jbxxes = countService.shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(query);
            ajjbxxYearProlbemNatureAnalyze.addAll(yx_pc_jbxxes);
        }else {
            ajjbxxYearProlbemNatureAnalyze=countService.shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(query);
        }
        PageInfo page = new PageInfo(ajjbxxYearProlbemNatureAnalyze);
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

    @ApiOperation("地图数据,评查基本情况 ")
    @GetMapping("/shGetMap")
    public MessageResult shGetMapJbqk(ShYPNAQuery query){
        List list = countService.shGetMapJbqk(query);
        return new MessageResult("获取成功",200,list);
    }



    @ApiOperation("地图数据,案件评查质量情况 ")
    @GetMapping("/shGetMapAjZlqk")
    public MessageResult shGetMapAjZlqk(ShYPNAQuery query){
        List list =countService.shGetMapAjZlqk(query);
        return new MessageResult("获取成功",200,list);
    }


    @ApiOperation("检察官办案质量排名 ")
    @GetMapping("/getPersonPaiMinByPcjlAndRQ")
    public MessageResult getPersonPaiMinByPcjlAndRQ(ShYPNAQuery query){
        PageHelper.startPage(1,10);
        List<PersonPaimin> personPaiMinByPcjlAndRQ = countService.getPersonPaiMinByPcjlAndRQ(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQ);
        return new MessageResult("获取成功",200,page.getList());
    }

    @ApiOperation("检察官办案质量排名获取案件基本信息")
    @GetMapping("/getPersonPaiMinByPcjlAndRQAjJbxx")
    public MessageResult getPersonPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> personPaiMinByPcjlAndRQAjJbxx = countService.getPersonPaiMinByPcjlAndRQAjJbxx(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQAjJbxx);
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



    @ApiOperation("部门办案质量排名 ")
    @GetMapping("/bmBanAjZhiLiangPaiMin")
    public MessageResult bmBanAjZhiLiangPaiMin(ShYPNAQuery query){
        PageHelper.startPage(1,10);
        List<BmZlPm> bmZlPms = countService.bmBanAjZhiLiangPaiMin(query);
        PageInfo page = new PageInfo(bmZlPms);
        return new MessageResult("获取成功",200,page.getList());
    }

    @ApiOperation("部门办案质量排名 获取案件基本信息")
    @GetMapping("/bmBanAjZhiLiangPaiMinAjJbxx")
    public MessageResult bmBanAjZhiLiangPaiMinAjJbxx(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> personPaiMinByPcjlAndRQAjJbxx = countService.bmBanAjZhiLiangPaiMinAjJbxx(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQAjJbxx);
        ArrayList<Map> resultMap = new ArrayList<>();
        page.getList().stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap, Math.toIntExact(page.getTotal()));
        return new MessageResult("获取成功",200,s);
    }


    @ApiOperation("不合格案件和瑕疵案件排名 ")
    @GetMapping("/getPm")
    public MessageResult getPm(ShYPNAQuery query){
        List<PmDto> pm = countService.getPm(query);
        return new MessageResult("获取成功",200,pm);
    }

    @ApiOperation("排名 获取案件基本信息")
    @GetMapping("/getPmAjJbxx")
    public MessageResult getPmAjJbxx(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> personPaiMinByPcjlAndRQAjJbxx = countService.getPmAjJbxx(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQAjJbxx);
        ArrayList<Map> resultMap = new ArrayList<>();
        page.getList().stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap, Math.toIntExact(page.getTotal()));
        return new MessageResult("获取成功",200,s);
    }



    @ApiOperation("散点图 ")
    @GetMapping("/getPcjbGk")
    public MessageResult getPcjbGk(ShYPNAQuery query){
        List<PcjbGkVo> pcjbGk = countService.getPcjbGk(query);
        Integer sj = pcjbGk.stream().max(Comparator.comparing(PcjbGkVo::getSj)).get().getSj();
        Integer pc = pcjbGk.stream().max(Comparator.comparing(PcjbGkVo::getPc)).get().getPc();
        HashMap<String, Object> result = new HashMap<>();
        result.put("sjMax",sj);
        result.put("pcMax",pc);
        result.put("data",pcjbGk);
        return new MessageResult("获取成功",200,result);
    }

    @ApiOperation("地图，案件质量分析 获取案件基本信息")
    @GetMapping("/getMapAjJbxx")
    public MessageResult getMapAjJbxx(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> personPaiMinByPcjlAndRQAjJbxx = countService.getMapAjJbxx(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQAjJbxx);
        ArrayList<Map> resultMap = new ArrayList<>();
        page.getList().stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap, Math.toIntExact(page.getTotal()));
        return new MessageResult("获取成功",200,s);
    }



     @ApiOperation("地图， 评查基本情况 获取案件基本信息")
    @GetMapping("/getMapAjJbxxJbqk")
    public MessageResult getMapAjJbxxJbqk(ShYPNAQuery query){
        PageHelper.startPage(query.getPage(),query.getRows());
        List<YX_PC_JBXX> personPaiMinByPcjlAndRQAjJbxx = countService.getMapAjJbxxJbqk(query);
        PageInfo page = new PageInfo(personPaiMinByPcjlAndRQAjJbxx);
        ArrayList<Map> resultMap = new ArrayList<>();
        page.getList().stream().forEach(message -> {
            try {
                Map describe = BeanUtils.describe(message);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap, Math.toIntExact(page.getTotal()));
        return new MessageResult("获取成功",200,s);
    }




















    private void addDto(List<YearProblemAnalyzeDto> yearMonthList, int startYear, int i) {
        YearProblemAnalyzeDto dto=new YearProblemAnalyzeDto();
        dto.setYear(startYear);
        dto.setMonth(i);
        dto.setCount(0);
        yearMonthList.add(dto);
    }


    @ApiOperation("获取评查结论 传 9102")
    @GetMapping("/getPcjl")
    public MessageResult getPcjl(String lbbm){
        List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy(lbbm);
        List<String> collect = xtDmFldmBy.stream().map(XtDmFldm::getMc).collect(Collectors.toList());
        return new MessageResult("获取成功",200,collect);
    }




// ********************************************************************************案件质量年度趋势图导出相关

    // 实体类转换成map
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>();

        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] propertyDescriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i<propertyDescriptors.length; i++) {
                String name = propertyDescriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 导出地图数据
     * @return
     */
    @ApiOperation("导出地图数据-案件质量年度趋势图")
    @PostMapping("/exportMapExcel")
    public MessageResult exportMapExcel(ShYPNAQuery query) {

        MessageResult messageResult;

        try{
            List list =countService.shGetMapJbqk(query);

            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("案件质量年度趋势图地图数据");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("单位");
            List<List<String>> data = new ArrayList<List<String>>();
            List<List<String>> tmpdatas = new ArrayList<List<String>>();
            Set<List<String>> dataset = new HashSet<>();

            Set<String> nameset = new HashSet<>();

            // 拼接Excel所需格式：
            for (int i=0; i<list.size(); i++) {
                Map<String,Object> map = (Map<String, Object>) list.get(i);
                header.add((String)map.get("name")); // 案件类型

                List<Object> listData = (List<Object>) map.get("data");
                for (int j=0; j<listData.size(); j++) {
                    List<String> rowlist = new ArrayList<>();
                    Map describe = BeanUtils.describe(listData.get(j));
                    String name = (String)describe.get("name");
                    String count = (String) describe.get("value");
                    rowlist.add(name);
                    rowlist.add(count);
                    tmpdatas.add(rowlist);
                    nameset.add(name);
                }
            }

            nameset.stream().forEach(name ->{
                List<String> list1 = new ArrayList<>();
                list1.add(name);
                tmpdatas.stream().forEach(tmpdata ->{
                    System.out.println(tmpdata);
                    if (tmpdata.get(0).equals(name)) {
                        list1.add(tmpdata.get(1));
                    }
                });
                data.add(list1);
            });

            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header);
            String fileName  = excelUtils.exportExcelData(excelVo);

            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }


    /**
     * 导出检察官办案质量排名
     * @return
     */
    @ApiOperation("导出检察官办案质量排名Excel")
    @PostMapping("/exportJcg")
    public MessageResult exportJcg(ShYPNAQuery query) {

        MessageResult messageResult;

       try{
           PageHelper.startPage(1,10);
           String pcjl = query.getPcjl();
           List<PersonPaimin> personPaiMinByPcjlAndRQ = countService.getPersonPaiMinByPcjlAndRQ(query);

           ExcelVo excelVo = new ExcelVo();
           excelVo.setFileName("检察官办案质量排名" + "(" + pcjl +")");
           //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
           //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
           //excelVo.setFilePath(fileAdress);

           List<String> header = new ArrayList<>();
           header.add("姓名");
           header.add("数量");

           List<List<String>> data = new ArrayList<List<String>>();

           // 拼接Excel所需格式：
           for (int i=0; i<personPaiMinByPcjlAndRQ.size(); i++) {
               Map<String,Object> map = beanToMap(personPaiMinByPcjlAndRQ.get(i));
               String tmpdwmc = (String) map.get("mc");
               String tmpcount = map.get("count").toString();
               List<String> list = new ArrayList<>();
               list.add(tmpdwmc);
               list.add(tmpcount);
               data.add(list);
           }

           logger.error("开始导出数据");
           excelVo.setData(data);
           excelVo.setHeader(header); //表格头
           String fileName  = excelUtils.exportExcelData(excelVo);


           messageResult = new MessageResult(200,fileName);
       }catch (Exception e) {
           messageResult = new MessageResult(e.getMessage(),500);
       }
       return messageResult;
    }



    /**
     * 导出案件质量年度趋势图排名
     * @return
     */
    @ApiOperation("案件质量年度趋势图排名")
    @PostMapping("/exportPm")
    public MessageResult exportPm(ShYPNAQuery query) {

        MessageResult messageResult;

        try{
            List<PmDto> pm = countService.getPm(query);
            Map<String, Object> map1 = beanToMap(pm.get(0));


            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("案件质量年度趋势图排名");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("部门");
            header.add("数量");
            ArrayList<String> colHeader = new ArrayList<>();

            List<List<String>> data = new ArrayList<List<String>>();

            // 拼接Excel所需格式：
            for (int i=0; i<pm.size(); i++) {
                Map<String,Object> map = beanToMap(pm.get(i));
                String tmpdwmc = (String) map.get("dwmc");
                String tmpcount = map.get("count").toString();
                List<String> list = new ArrayList<>();
                list.add(tmpdwmc);
                list.add(tmpcount);
                data.add(list);
            }


            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header); //表格头
            String fileName  = excelUtils.exportExcelData(excelVo);

            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }


    /**
     * 导出部门办案质量
     * @return
     */
    @ApiOperation("导出部门办案质量")
    @PostMapping("/exportDept")
    public MessageResult exportDept(ShYPNAQuery query) {

        MessageResult messageResult;
        String pcjl = query.getPcjl();

        try{
            List<BmZlPm> bmZlPms = countService.bmBanAjZhiLiangPaiMin(query);

            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("部门办案质量排名" + "(" + pcjl +")");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("部门");
            header.add("数量");
            ArrayList<String> colHeader = new ArrayList<>();

            List<List<String>> data = new ArrayList<List<String>>();

            // 拼接Excel所需格式：
            for (int i=0; i<bmZlPms.size(); i++) {
                Map<String,Object> map = beanToMap(bmZlPms.get(i));
                String tmpdwmc = (String) map.get("bmmc");
                String tmpcount = map.get("count").toString();
                List<String> list = new ArrayList<>();
                list.add(tmpdwmc);
                list.add(tmpcount);
                data.add(list);
            }


            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header); //表格头
            String fileName  = excelUtils.exportExcelData(excelVo);

            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }


    /**
     * 导出质量问题分布图 -- 不合格
     * @return
     */
    @ApiOperation("导出质量问题（不合格）分布图")
    @PostMapping("/exportQuantity")
    public MessageResult exportQuantity(ShYPNAQuery query) {

        MessageResult messageResult;

        try{
            List<PcxDto> pcxDtos = countService.shYearProlbemNatureAnalyze(query);

            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("质量问题分布图（不合格）");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("质量问题点");
            header.add("数量");
            List<List<String>> data = new ArrayList<List<String>>();

            // 拼接Excel所需格式：
            for (int i=0; i<pcxDtos.size(); i++) {
                Map<String,Object> map = beanToMap(pcxDtos.get(i));
                String tmpdwmc = (String) map.get("pcxmc");
                String tmpcount = map.get("COUNT").toString();
                List<String> list = new ArrayList<>();
                list.add(tmpdwmc);
                list.add(tmpcount);
                data.add(list);
            }

            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header); //表格头
            String fileName  = excelUtils.exportExcelData(excelVo);


            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }

    /**
     * 导出质量问题分布图 -- 瑕疵
     * @return
     */
    @ApiOperation("导出质量问题（瑕疵）分布图")
    @PostMapping("/exportQuantityFlaw")
    public MessageResult exportQuantityFlaw(ShYPNAQuery query) {

        MessageResult messageResult;

        try{
            List<PcxDto> pcxDtos = countService.shYearProlbemNatureAnalyze(query);

            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("质量问题分布图（瑕疵）");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //String fileAdress = "F:\\workspace_idea\\sh_zlpc\\src\\main\\resources\\static\\";
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("质量问题点");
            header.add("数量");
            List<List<String>> data = new ArrayList<List<String>>();

            // 拼接Excel所需格式：
            for (int i=0; i<pcxDtos.size(); i++) {
                Map<String,Object> map = beanToMap(pcxDtos.get(i));
                String tmpdwmc = (String) map.get("pcxmc");
                String tmpcount = map.get("COUNT").toString();
                List<String> list = new ArrayList<>();
                list.add(tmpdwmc);
                list.add(tmpcount);
                data.add(list);
            }

            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header); //表格头
            String fileName  = excelUtils.exportExcelData(excelVo);


            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }

// ********************************************************************************案件质量年度趋势图导出相关

    /**
     * 导出评查工作基本情况
     * @return
     */
    @ApiOperation("导出评查工作基本情况地图数据")
    @PostMapping("/exportSituation")
    public MessageResult exportSituation(ShYPNAQuery query) {

        MessageResult messageResult;

        try{
            List list =countService.shGetMapAjZlqk(query);

            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("地图数据");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //excelVo.setFilePath(fileAdress);
            List<String> header = new ArrayList<>();
                header.add("单位");
                List<List<String>> data = new ArrayList<List<String>>();
                List<List<String>> tmpdatas = new ArrayList<List<String>>();
                Set<List<String>> dataset = new HashSet<>();

                Set<String> nameset = new HashSet<>();
                // 拼接Excel所需格式：
                List<List> ypcDatas = new ArrayList<>();
                List<List> wpcDatas = new ArrayList<>();
                for (int i=0; i<list.size(); i++) {
                    Map<String,Object> map = (Map<String, Object>) list.get(i);
                    header.add((String)map.get("name")); // 案件类型
                    List<Object> listData = (List<Object>) map.get("data");
                    List<Double> ypcData = new ArrayList<>();
                    List<Double> wpcData = new ArrayList<>();

                    for (int j=0; j<listData.size(); j++) {
                        List<String> rowlist = new ArrayList<>();
                        Map describe = BeanUtils.describe(listData.get(j));
                        String name = (String)describe.get("name");
                        String count = (String) describe.get("value");
                        //每一个评查的数据
                        Double ypc = Double.parseDouble((String)describe.get("ypc"));
                        Double wpc = Double.parseDouble((String)describe.get("wpc"));
                        ypcData.add(ypc);
                        wpcData.add(wpc);
                        rowlist.add(name);
                        rowlist.add(count);
                        tmpdatas.add(rowlist);
                        nameset.add(name);

                    }
                    ypcDatas.add(ypcData);
                    wpcDatas.add(wpcData);
                }
                List ypcList =new ArrayList();
                List wpcList =new ArrayList();
                for(int i = 0; i < ypcDatas.get(0).size(); i++){
                    Double ypcTotal = 0.0;
                    Double wpcTotal = 0.0;
                    for(int k = 0; k < ypcDatas.size(); k++){
                        ypcTotal+=(Double)ypcDatas.get(k).get(i);
                        wpcTotal+=(Double) wpcDatas.get(k).get(i);
                    }
                    ypcList.add(ypcTotal);
                    wpcList.add(wpcTotal);
                }
            for(int j = 0; j < nameset.size(); j++){
                List<String> list1 = new ArrayList<>();
                list1.add((String)nameset.toArray()[j]);
                String name = (String)nameset.toArray()[j];
                tmpdatas.stream().forEach(tmpdata ->{
                    if (tmpdata.get(0).equals(name)) {
                        list1.add(tmpdata.get(1));
                    }
                });
                String pcl = Math.round((Double)ypcList.get(j)/((Double)wpcList.get(j)+ (Double)ypcList.get(j)))*100+"%";
                list1.add(pcl);
                data.add(list1);
            }
            logger.error("开始导出数据");
            header.add("评查率");
            excelVo.setData(data);
            excelVo.setHeader(header);

            String fileName  = excelUtils.exportExcelData(excelVo);
            messageResult = new MessageResult(200,fileName);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }

    @ApiOperation("评查工作基本概况散点图数据")
    @PostMapping("/exportSituationScatter")
    public MessageResult exportSituationScatter(ShYPNAQuery query) {

        MessageResult messageResult;

        try {
            List list = countService.getPcjbGk(query);
            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("评查工作概况");
            //String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
            //excelVo.setFilePath(fileAdress);

            List<String> header = new ArrayList<>();
            header.add("单位");
            List<List<String>> data = new ArrayList<>();
            header.add("评查数");
            header.add("审结数");
            header.add("评查率");
            list.stream().forEach(x->{
                try {
                    List<String> sigleData = new ArrayList<>();
                    Map describe = BeanUtils.describe(x);
                    sigleData.add((String)describe.get("dwmc"));
                    sigleData.add((String)describe.get("pc"));
                    sigleData.add((String)describe.get("sj"));
                    sigleData.add(Double.parseDouble((String)describe.get("pcl"))*100+"%");
                    data.add(sigleData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header);
            String fileName  = excelUtils.exportExcelData(excelVo);
            messageResult = new MessageResult(200,fileName);
        } catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }


    @ApiOperation("导出评查成效概况图曲线图数据")
    @PostMapping("/exportQualityLine")
    public MessageResult exportQualityLine(YearProblemAnalyzeQuery query){
        MessageResult messageResult;

        try {
            List<YearProblemAnalyzeResultVo> yearProblemAnalyzeResultVos = shGetYearProblemAnalyze(query);
            if(yearProblemAnalyzeResultVos==null || yearProblemAnalyzeResultVos.size()==0){
                return new MessageResult("暂无数据",500);
            }
            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("评查成效概况");
//            String fileAdress = systemCoreConfigService.getSystemConfigValue("tongji_file_adress");
//            excelVo.setFilePath(fileAdress);

            List<String> header ;
            header = Arrays.asList("月份","一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月");
            List<List<String>> data = new ArrayList<List<String>>();

            yearProblemAnalyzeResultVos.stream().forEach(x->{
                List singleData = new ArrayList();
                singleData.add(x.getPcjl());
                x.getData().stream().forEach(d->singleData.add(d+""));
                data.add(singleData);
            });
            logger.error("开始导出数据");

            excelVo.setData(data);
            excelVo.setHeader(header);
            String fileName  = excelUtils.exportExcelData(excelVo);

            messageResult = new MessageResult(200,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }

    @ApiOperation("导出评查监控数据")
    @PostMapping("/exportPCmonitor")
    public MessageResult exportPCmonitor(String json){
        MessageResult messageResult;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HttpSession session = HttpContextUtils.getSession();
        try {
            Param_Pcjk pcjkParam = FastJsonUtils.toObject(Param_Pcjk.class, json);
            pcjkParam.setDwbm(session == null ? "" :(String) session.getAttribute(SessionNames.SESSION_KEY_DWBM));
            pcjkParam.setGh(session == null ? "" : (String) session.getAttribute(SessionNames.SESSION_KEY_GH));
           /* pcjkParam.setPage(parsePage(getParameter("page")));
            pcjkParam.setRows(parseRows(getParameter("rows")));*/

            Param_Pager datas = filterService.getPcjk(pcjkParam);
            if(datas==null || datas.getList().size()==0){
                return new MessageResult("暂无数据",500);
            }
            ExcelVo excelVo = new ExcelVo();
            excelVo.setFileName("评查监控数据");
            List<String> header ;
            header = Arrays.asList("评查案号","评查分类名称", "案件名称", "部门受案号", "承办单位", "承办部门", "承办检察官", "评查员单位", "评查员", "评查日期", "评查状态", "筛选规则", "评查结论");
            List<List<String>> data = new ArrayList<List<String>>();
            List<Map> list=datas.getList();
            for(int i=0;i<list.size();i++){
                Map m=list.get(i);
                List<String> sigleData = new ArrayList<>();
               /* sigleData.add((String)m.get("PCSLBM"));
                sigleData.add((String)m.get("PCFLBM"));*/
                sigleData.add((String)m.get("PCSAH"));
                sigleData.add((String)m.get("PCFLMC"));
                sigleData.add((String)m.get("AJMC"));
                sigleData.add((String)m.get("BMSAH"));
                sigleData.add((String)m.get("BPC_DWMC"));
                sigleData.add((String)m.get("BPC_BMMC"));
                sigleData.add((String)m.get("BPC_MC"));
                sigleData.add((String)m.get("PCR_DWMC"));
                sigleData.add((String)m.get("PCR_MC"));
                sigleData.add((m.get("CJSJ")+"").split(" ")[0]);
                sigleData.add((String)m.get("PCJDMS"));
                sigleData.add((String)m.get("SXGZMC"));
                sigleData.add((String)m.get("PCJL"));
                data.add(sigleData);
            }
            logger.error("开始导出数据");
            excelVo.setData(data);
            excelVo.setHeader(header);
            String fileName  = excelUtils.exportExcelData(excelVo);

            messageResult = new MessageResult(200,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult(e.getMessage(),500);
        }
        return messageResult;
    }
}
