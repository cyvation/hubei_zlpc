package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.dao.ajpc.Yx_Pc_PcxFlMapper;
import com.start.boot.dao.ajpc.Yx_Pc_PcxMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.PcxVoList;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import com.start.boot.service.FilterService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 筛选控制器
 * Created by lei on 2017/11/2.
 */
@RestController
@RequestMapping("/filter")
public class FilterController extends ArchivesSystemBaseController {

    @Autowired
    private FilterService filterService;
    @Autowired
    Yx_Pc_PcxMapper yx_pc_pcxMapper;

    @Autowired
    Yx_Pc_PcxFlMapper yx_pc_pcxFlMapper;

    @Autowired
    YX_PC_JBXXMapper yx_pc_jbxxMapper;


    /**
     * 评查筛选规则列表
     */
    @RequestMapping("/getSxgz")
    public String getSxgz(String pcflbm, String sslb) {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getSxgz(SystemConfiguration.djdwbm, pcflbm, sslb);

            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "GZBM", "FGZBM", "GZMC", "-1");
        } catch (Exception e) {
            super.errMsg("评查筛选规则列表获取失败", pcflbm, e);
        }

        return result;
    }

    /**
     * 评查筛选规则列表
     */
    @RequestMapping("/getSxgzByPcmbbm")
    public String getSxgzByPcmbbm(String pcflbm, String sslb,String ywtx) {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getSxgz(SystemConfiguration.djdwbm, pcflbm, sslb);
            List<Map> resultList = new ArrayList<Map>();
            for (Map sxgz : list) {
                String ywtxbm = sxgz.get("YWTX").toString();
                if(ywtxbm.equals(ywtx)){
                    resultList.add(sxgz);
                }
//                String gzbm = sxgz.get("GZBM").toString();
//                if(gzbm .substring(gzbm.length()-3).equals(pcmbbm.substring(pcmbbm.length()-3))){
//                    resultList.add(sxgz);
//                    break;
//                }
            }
            result = EasyUIHelper.buildTreeListDataSource(list,"GZBM","FGZBM","GZMC","ICON","");
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(resultList, "GZBM", "FGZBM", "GZMC", "-1");
        } catch (Exception e) {
            super.errMsg("评查筛选规则列表获取失败", pcflbm, e);
        }

        return result;
    }


    /**
     * 评查筛选规则列表（监控用）
     */
    @RequestMapping("/getSxgzMonitor")
    public String getSxgzMonitor(String pcflbm) {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getSxgzMonitor(SystemConfiguration.djdwbm, pcflbm);

            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "GZBM", "FGZBM", "GZMC", "-1");
        } catch (Exception e) {
            super.errMsg("评查筛选规则列表获取失败", pcflbm, e);
        }

        return result;
    }

    /**
     * 评查活动绑定的筛选规则列表
     */
    @RequestMapping("/getHdsxgz")
    public String getHdsxgz(String pcflbm, String pchdbm) {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getHdsxgz(SystemConfiguration.djdwbm, pcflbm, pchdbm);

            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "GZBM", "FGZBM", "GZMC", "-1");
        } catch (Exception e) {
            super.errMsg("评查活动绑定的筛选规则列表获取失败", pchdbm, e);
        }

        return result;
    }

    /**
     * 随机评查案件筛选（自定义）
     */
    @RequestMapping("/getSjsx")
    public String getSjsx(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
            param.setGzdwbm(SystemConfiguration.djdwbm);
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));

            ParamSx data = filterService.getSjsx(param);

            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("随机评查案件筛选（自定义）获取失败", json, e);
            result = failure(e.getMessage(), "随机评查案件筛选（部门）获取失败");
        }

        return result;
    }




    /**
     * 随机评查案件筛选（部门）
     */
    @RequestMapping("/get_sjsx_bm")
    public String get_sjsx_bm(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);

            ParamSx data = filterService.get_sjsx_bm(param);
            result = success(data, "随机评查案件筛选（部门)成功");
        } catch (Exception e) {
            super.errMsg("随机评查案件筛选（部门）获取失败", json, e);
            result = failure(e.getMessage(), "随机评查案件筛选（部门）获取失败");
        }

        return result;
    }

    /**
     * 随机评查案件筛选（检察官）
     */
    @RequestMapping("/get_sjsx_jcg")
    public String get_sjsx_jcg(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            ParamSx data = filterService.get_sjsx_jcg(param);
            result = success(data, "随机评查案件筛选（检察官)成功");
        } catch (Exception e) {
            super.errMsg("随机评查案件筛选（检察官）获取失败", json, e);
            result = failure(e.getMessage(), "随机评查案件筛选（检察官）获取失败");
        }

        return result;
    }

    /**
     * 随机评查案件筛选（检察官）
     */
    @RequestMapping("/getSjsxJcgGetExecel")
    public MessageResult getSjsxJcgGetExecel(String json) {
        ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
        ParamSx data = null;
        try {
            data = filterService.get_sjsx_jcg(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResult("随机评查案件筛选（检察官）导出失败", 500);
        }
        // Excel表头
        List<String> headerList = new ArrayList<>();
        headerList.add("检察官");
        headerList.add("评查率(%)");
        headerList.add("评查数(件)");
        // 已包含的表头
        Map<String, String> hasHeaderMap = new HashMap<>();
        // 各类案件办理数据，以工号标识的是谁的信息（key=GH）
        Map<String, Map<String, String>> valueMap = new HashMap<>();
        // 表头以下的数据，标识每个人的名称、评查率、评查数、、、等所有数据，以Key=GH为主键
        Map<String, Map<Integer, String>> mapMap = new HashMap<>();

        // 装表头名称的集合
        List<Map> heardList = data.getListcols();
        // 装人员的集合
        List<Map> personList = data.getListrows();
        // 装各类案件处理数量的数据
        List<Map> valueList = data.getListdata();

        // 遍历人员信息，将人员信息装入mapMap中，主键为GH
        for (Map<String, String> map2 : personList) {
            Map<Integer, String> map = new HashMap<>();
            //检察官
            map.put(0, map2.get("MC"));
            //以工号作为主键
            mapMap.put(map2.get("GH"), map);
        }
        // 遍历各类案件处理数量的数据，WPC、ZS、YPC为BigDecimal数据，所以需要转，算出百分比，结果集保留2位小数，然后根据数据的工号装入mapMap中，
        for (Map<String, Object> map3 : valueList) {
            // 未评查
            BigDecimal wpc = (BigDecimal) map3.get("WPC");
            // 已评查
            BigDecimal ypc = (BigDecimal) map3.get("YPC");
            // 总数
            BigDecimal zs = (BigDecimal) map3.get("ZS");

            // 当为评查总数时
            if ("PCZS".equals(map3.get("GZBM"))) {
                // 评查数
                mapMap.get(map3.get("GH")).put(2, ypc + "/" + wpc);
                // 百分比 已评查/总数 截取4位有效数字 然后 *100
                String bfb = String.valueOf(ypc.divide(zs, 4, RoundingMode.HALF_UP).doubleValue() * 100);
                // 作为保险起见，判断转换的String是否超长，是则截取包括小数点在内的5位有效文字
                if (bfb.length() > 5) {
                    bfb = bfb.substring(0, 5);
                }
                // 评查率
                mapMap.get(map3.get("GH")).put(1, String.valueOf(bfb));
            } else {
                // 添加需要显示的表头，当数据里有这个案件类型，则装起来，待用
                if (hasHeaderMap.get(map3.get("GZBM")) == null) {
                    hasHeaderMap.put(map3.get("GZBM").toString(), "");
                }
                // 将各类案件处理数量的数据，根据工号组装起来，GH作为主KEY，获取工号内数据也是个map，案件类型编码为key记录数据
                if (valueMap.get(map3.get("GH")) == null) {
                    valueMap.put(map3.get("GH").toString(), new HashMap<>());
                    valueMap.get(map3.get("GH")).put(map3.get("GZBM").toString(), ypc + "/" + wpc);
                } else {
                    valueMap.get(map3.get("GH")).put(map3.get("GZBM").toString(), ypc + "/" + wpc);
                }
            }

        }
        // 前3个表头已经固定，所以这num从3开始
        int num = 3;
        // 遍历表头数据
        for (Map<String, String> map1 : heardList) {
            // 判断数据中时候有这个表头的数据，有的话才进行数据组装
            if (hasHeaderMap.get(map1.get("GZBM")) != null) {
                // 导出Excel的表头数据
                headerList.add(map1.get("GZMC"));
                // 遍历mapMap
                for (Map.Entry<String, Map<Integer, String>> entry : mapMap.entrySet()) {
                    // 如果valueMap有数据，则将数据装入mapMap中，没有则设为0/0
                    if (valueMap.get(entry.getKey()).get(map1.get("GZBM")) == null) {
                        mapMap.get(entry.getKey()).put(num, "0/0");
                    } else {
                        mapMap.get(entry.getKey()).put(num, valueMap.get(entry.getKey()).get(map1.get("GZBM")));
                    }
                }
                num++;
            }
        }
        // 由于涉及到排序，后续增加了mapList作为最后导出Excel数据
        List<Map<Integer, String>> mapList = new ArrayList<>();
        // 遍历mapMap，将数据装入mapList
        for (Map.Entry<String, Map<Integer, String>> entry : mapMap.entrySet()) {
            mapList.add(mapMap.get(entry.getKey()));
        }
        // 根据要求，按照评查率从小到大排序，将小的挤到前面，不是把小的和大的尽心交换
        for (int m = 0; m < mapList.size(); m++) {
            for (int n = 0; n < mapList.size(); n++) {
                if (Double.valueOf(mapList.get(m).get(1)) > Double.valueOf(mapList.get(n).get(1)) && m < n) {
                    mapList.add(m, mapList.get(n));
                    mapList.remove(n + 1);
                }
            }
        }
        // 创建Excel
        Workbook workbook = new HSSFWorkbook();
        // 设置工作薄的内容垂直居中、水平居中
        HSSFCellStyle allCellStyle = (HSSFCellStyle) workbook.createCellStyle(); // 样式对象
        // 居中显示
        allCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        allCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        // 边框加线
        allCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        allCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
        allCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框 
        allCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        // 字体
        HSSFFont textFont = (HSSFFont) workbook.createFont();
        textFont.setFontName("黑体");
        textFont.setFontHeightInPoints((short) 14);
        allCellStyle.setFont(textFont);
        // 自动换行
        allCellStyle.setWrapText(true);
        // 数据样式
        HSSFCellStyle valueStyle = (HSSFCellStyle) workbook.createCellStyle();
        valueStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 表头样式
        HSSFCellStyle titleStyle = (HSSFCellStyle) workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont titleFont = (HSSFFont) workbook.createFont();
        titleFont.setFontName("黑体");
        titleFont.setFontHeightInPoints((short) 13);
        titleFont.setBoldweight((short) 400);
        titleStyle.setFont(titleFont);
        // 生成一个(带标题)工作薄
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("检察官筛选表");
        // 标题行
        Row titleRow = sheet.createRow((short) 0);

        // 设置表头数据和样式
        for (int i = 0; i < headerList.size(); i++) {
            titleRow.createCell(i).setCellValue(headerList.get(i));
            HSSFCell titleCell = (HSSFCell) titleRow.getCell((short) i);
            titleCell.setCellStyle(titleStyle);
            // 根据页面布局，我将前3个表头设置短点，后面设置长点，以保持和页面大致相同
            if (i < 3) {
                sheet.setColumnWidth(i, 4000);
            } else {
                sheet.setColumnWidth(i, 8000);
            }
        }
        // 遍历mapList数据，将数据填充至Excel
        for (int m = 0; m < mapList.size(); m++) {
            Row row = sheet.createRow((short) m + 1);
            for (int n = 0; n < mapList.get(m).size(); n++) {
                row.createCell(n).setCellValue(n == 1 ? mapList.get(m).get(n) + "%" : mapList.get(m).get(n));
                row.getCell((short) n).setCellStyle(valueStyle);
            }

        }

        // 设置存储路径
        String wzbsPath = SystemConfiguration.wzbsPath;
        String filePath = "/File/";
        String path = wzbsPath + filePath;
        // 设置文件名，以  什么表+时间 进行命名
        String fileName = "检察官筛选表(" + new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date()) + ")";
        try {
            // 创建路劲和文件
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path + fileName + ".xls");
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MessageResult(200, filePath + fileName + ".xls");
    }

    /**
     * 评查监控
     */
    @RequestMapping("/getPcjk")
    public String getPcjk(String json) {

        //响应到页面封装
        String result = "";
        try {
            Param_Pcjk pcjkParam = FastJsonUtils.toObject(Param_Pcjk.class, json);
            pcjkParam.setDwbm(getCurrentDwbm());
            pcjkParam.setGh(getCurrentGh());
            pcjkParam.setPage(parsePage(getParameter("page")));
            pcjkParam.setRows(parseRows(getParameter("rows")));

            Param_Pager data = filterService.getPcjk(pcjkParam);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("评查监控获取失败", json, e);
            result = failure(e.getMessage(), "获取评查监控失败");
        }

        return result;
    }

    /**
     * 获取评查结论
     */
    @RequestMapping("/getPcjl")
    public String getPcjl() {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getPcjl();
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCJLBM", "FBM", "PCJL", "-1");
        } catch (Exception e) {
            super.errMsg("获取评查结论失败", null, e);
            result = failure(e.getMessage(), "获取评查结论失败");
        }

        return result;
    }

    /**
     * 获取评查状态
     */
    @RequestMapping("/getPczt")
    public String getPczt() {

        //响应到页面封装
        String result = "";

        try {
            List<Map> list = filterService.getPczt();
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCZTBM", "FBM", "PCZT", "-1");
        } catch (Exception e) {
            super.errMsg("获取评查状态失败", null, e);
            result = failure(e.getMessage(), "获取评查状态失败");
        }

        return result;
    }

    // 部门已经评查/未评查列表
    @RequestMapping("/getBmwpcList")
    public String getBmwpcList(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));
            if (StringUtils.isEmpty(param.getCbdwbm())) {
                param.setCbdwbm(getCurrentDwbm());
            }
            Param_Pager data = filterService.getBmwpcList(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("部门已经评查/未评查列表获取失败", json, e);
            result = failure(e.getMessage(), "部门已经评查/未评查列表获取失败");
        }

        return result;
    }

    // 检察官已经评查/未评查列表
    @RequestMapping("/getCbrwpcList")
    public String getCbrwpcList(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));
            if (StringUtils.isEmpty(param.getCbdwbm())) {
                param.setCbdwbm(getCurrentDwbm());
            }

            Param_Pager data = filterService.getCbrwpcList(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("检察官已经评查/未评查列表获取失败", json, e);
            result = failure(e.getMessage(), "检察官已经评查/未评查列表获取失败");
        }

        return result;
    }

    // 随机、重点评查
    @RequestMapping("/uptSjpcsx")
    public String uptSjpcsx(String json) {
        String result = "";

        try {
            Param_Ajsx param = FastJsonUtils.toObject(Param_Ajsx.class, json);

            param.setPcdwbm(getCurrentDwbm());
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());
            if (StringUtils.isEmpty(param.getPcdwbm())) {
                param.setPcr_dwbm(getCurrentDwbm());
                param.setPcr_dwmc(getCurrentDwmc());
                param.setPcr_gh(getCurrentGh());
                param.setPcr_mc(getCurrentMC());
            }
            param.setPcsah("");
            param.setPcslbm("");
            param = filterService.uptSjpcsx(param);
            result = success(param, "随机/重点筛选案件成功");
        } catch (Exception e) {
            super.errMsg("随机/重点筛选案件失败", json, e);
            result = failure(e.getMessage(), "随机/重点筛选案件失败");
        }

        return result;
    }

    // 重点评查
    @RequestMapping("/uptZxpcsx")
    public String uptZxpcsx(String json) {
        String result = "";

        try {
            Param_Ajsx param = FastJsonUtils.toObject(Param_Ajsx.class, json);

            param.setPcdwbm(getCurrentDwbm());
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());
            param.setPcsah("");
            param.setPcslbm("");
            param = filterService.uptZxpcsx(param);
            //湖北:重点案件筛选时更新评查节点为待评查，否则该案件数据会在分配评查人员后无法办理
            YX_PC_JBXX jbxx = new YX_PC_JBXX();
            jbxx.setPCSLBM(param.getPcslbm());
            jbxx.setPCJDBH("005");
            jbxx.setPCJDMS("待评查");
            int count = yx_pc_jbxxMapper.updateByPrimaryKeySelective(jbxx);
            result = success(param, "重点评查筛选案件成功");
        } catch (Exception e) {
            super.errMsg("重点评查筛选案件失败", json, e);
            result = failure(e.getMessage(), "重点评查筛选案件失败");
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/getEvalCards")
    public Object getEvalCards(String pcslbm, String pcxflbm) {

        try {
            PcxVoList yxPcFl = filterService.getYxPcFl(pcslbm, pcxflbm);
            return yxPcFl;
        } catch (Exception e) {
            super.errMsg("获取评查案卡数据失败", pcslbm, e);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping("/saveEvalCards")
    public String saveEvalCards(String json, String jbxx) {
        String result = "";

        try {
            PcxVoList param = FastJsonUtils.toObject(PcxVoList.class, json);
            List<Yx_Pc_PcxFlVo> pcxFlVos = param.getPcxFlVos();
            if (pcxFlVos.isEmpty()) {
                return failure("400", "提交数据为空，保存失败");
            }
            pcxFlVos.stream().forEach(data -> {
                try {
                    update(data);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            YX_PC_JBXX pcxx = FastJsonUtils.toObject(YX_PC_JBXX.class, jbxx);
            yx_pc_jbxxMapper.updateByPrimaryKey(pcxx);

            result = success(param, "保存评查案卡数据成功");

        } catch (Exception e) {
            super.errMsg("保存评查案卡数据失败", json, e);
            result = failure(e.getMessage(), "保存评查案卡数据失败");
        }
        return result;
    }

    private void update(Yx_Pc_PcxFlVo vo) throws InvocationTargetException, IllegalAccessException {
        Yx_Pc_PcxFl yx_pc_pcxFl = new Yx_Pc_PcxFl();
        BeanUtils.copyProperties(vo, yx_pc_pcxFl);
        yx_pc_pcxFlMapper.updateByPrimaryKeySelective(yx_pc_pcxFl);
        List<Yx_Pc_Pcx> pcxList = vo.getPcxList();
        if (!pcxList.isEmpty()) {
            pcxList.stream().forEach(pcx -> {
                yx_pc_pcxMapper.updateByPrimaryKeySelective(pcx);
            });
        }
        List<Yx_Pc_PcxFlVo> children = vo.getChildren();
        if (!children.isEmpty()) {
            children.stream().forEach(data -> {
                try {
                    update(data);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @RequestMapping("/updateJbxx")
    public String updateJBxx(YX_PC_JBXX jbxx) {
        if (jbxx == null) {
            return failure("400", "提交数据为空，修改失败");
        }
        try {
            yx_pc_jbxxMapper.updateByPrimaryKeySelective(jbxx);
            return success("200", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return failure("400", "修改失败，修改失败");
        }
    }

    @PostMapping("/updateJbxxByPcslbm")
    public String updateJbxxByPcslbm(String json) {
         Map map = (Map) JSON.parse(json);
        YX_PC_JBXX jbxx = new YX_PC_JBXX();
        jbxx.setPCSLBM(map.get("PCSLBM").toString());
        jbxx.setSFLDBA(map.get("SFLDBA").toString());
        if (jbxx == null) {
            return failure("400", "提交数据为空，修改失败");
        }
        try {
            yx_pc_jbxxMapper.updateByPrimaryKeySelective(jbxx);
            return success("200", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return failure("400", "修改失败");
        }
    }

    /**
     * 获取部门编码
     *
     * @param
     * @return
     */
    @RequestMapping("/getBmList")
    public String getBmList(String pcflbm) {
        //响应到页面封装
        String result = "";

        String dwbm = getCurrentDwbm();

        try {
            List<Map> bmList = filterService.getBmList(dwbm, pcflbm);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(bmList, "BMBM", "FBMBM", "BMMC", "-1");

        } catch (Exception e) {
            e.printStackTrace();
            super.errMsg("获取部门编码失败", dwbm, e);
        }

        return result;

    }


    /**
     * 获取所有部门编码
     *
     * @return
     */
    @RequestMapping("/getAllBm")
    public String getAllBm(String dwbm) {
        //响应到页面封装
        String result = "";

        try {
            if (StringUtils.isEmpty(dwbm))
                dwbm = getCurrentDwbm();
            List<Map> bmList = filterService.getAllBm(dwbm);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(bmList, "BMBM", "FBMBM", "BMMC", "-1");

        } catch (Exception e) {
            super.errMsg("获取所有部门编码失败", dwbm, new Exception("获取所有部门编码失败"));
        }
        return result;
    }

    /**
     * 获取评查模板集合：
     */
    @RequestMapping("/getPcmbj")
    public String getPcbmj(String json) {

        // 响应到页面：
        String result = "";
        try {
            ParamSx paramSx = FastJsonUtils.toObject(ParamSx.class, json);
            List<Map> list = filterService.getPcbmj(paramSx);
            result = success(list, "获取评查模板集合成功");
        } catch (Exception e) {
            result = failure(e, "获取评查模板集合失败");
        }

        return result;
    }

    @RequestMapping("/getAjxxByBmsah")
    public String getAjxxByBmsah(String bmsah) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("bmsah", bmsah);
            Map map = filterService.getAjxxByBmsah(param);
            if(map != null){
                return failure("400", "案件已被"+map.get("DWMC").toString()+"评查中!");
            }
            Map tyywAjMap = filterService.getTyywAjxxByBmsah(param);
            if(tyywAjMap != null &&(!tyywAjMap.containsKey("WCRQ") || tyywAjMap.get("WCRQ").toString().isEmpty())){
                return failure("400", "该案件在统一业务系统当中流程未结束!");
            }
            return  success("200", "查询成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return failure("400", "查询失败");
        }
    }

    /**
     * 随机评查案件筛选（进阶版）
     */
    @RequestMapping("/getSjsxAdvance")
    public String getSjsxAdvance(String json) {

        //响应到页面封装
        String result = "";

        try {
            ParamSx param = FastJsonUtils.toObject(ParamSx.class, json);
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));

            ParamSx data = filterService.getSjsxAdvance(param);

            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("随机评查案件筛选（自定义）获取失败", json, e);
            result = failure(e.getMessage(), "随机评查案件筛选（部门）获取失败");
        }

        return result;
    }

    /**
     * 评查筛选规则列表
     */
    @RequestMapping("/getSxgzByPcflbmAndYwtx")
    public String getSxgzByPcflbmAndYwtx(String pcflbm, String sslb,String ywtx) {

        //响应到页面封装
        String result = "";

        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("dwbm", SystemConfiguration.djdwbm);
            param.put("pcflbm", pcflbm);
            param.put("ywtx", ywtx);
            List<Map> list = filterService.getSxgzByPcflbmAndYwtx(param);
            //result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list,"GZBM","FGZBM","GZMC","420000008002");
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list,"GZBM","FGZBM","GZMC","-1");
        } catch (Exception e) {
            super.errMsg("评查筛选规则列表获取失败", pcflbm, e);
        }
        return result;
    }

    /**
     * 获取系统中的所有重点案件
     */
    @RequestMapping("/getZdAj")
    public String getZdAj(String json) {

        //响应到页面封装
        String result = "";
        try {
            Param_Pcjk pcjkParam = FastJsonUtils.toObject(Param_Pcjk.class, json);
            pcjkParam.setDwbm(getCurrentDwbm());
            pcjkParam.setGh(getCurrentGh());
            pcjkParam.setPage(parsePage(getParameter("page")));
            pcjkParam.setRows(parseRows(getParameter("rows")));
            PageHelper.startPage(pcjkParam.getPage(),pcjkParam.getRows());
            Param_Pager data = filterService.getZdAj(pcjkParam);
            PageInfo  pageInfo = new PageInfo<>(data.getList());
            result = EasyUIHelper.buildDataGridDataSource(pageInfo.getList(), Math.toIntExact(pageInfo.getTotal()));
        } catch (Exception e) {
            super.errMsg("评查监控获取失败", json, e);
            result = failure(e.getMessage(), "获取评查监控失败");
        }

        return result;
    }

}
