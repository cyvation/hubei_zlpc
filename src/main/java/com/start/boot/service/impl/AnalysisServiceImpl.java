package com.start.boot.service.impl;


import com.github.pagehelper.PageHelper;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.AnalysisMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.dto.ZdFxZtqkDto;
import com.start.boot.pojo.dto.ZdFxzlfxDto;
import com.start.boot.pojo.vo.*;
import com.start.boot.query.ZdFxQuery;
import com.start.boot.service.AnalysisService;
import com.start.boot.support.utils.DateUtils;
import com.start.boot.utils.ExportExcelUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 湖北专题报告分析：统计表及报告
 */
@Service
@Transactional
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Autowired
    ExportExcelUtils excelUtils;

    @Override
    public List<ErrorAndFlawTreeVo> loadDateData(Map seach) throws Exception {
       // seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));

        String endDate = (String) seach.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("endDate",endDate);
        }

        String pcendDate = (String) seach.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("pcdate_end",pcendDate);
        }

        seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
        seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
        seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
        List<Map> fl = analysisMapper.loadDateFl();
        List<Map> pcCountList = analysisMapper.loadDatePcCount(seach);
        List<Map> errPcCountList = analysisMapper.loadDateErrPcCount(seach);
        List<Map> errCountList = analysisMapper.loadDateErrCount(seach);
        List<Map> pcFlList = analysisMapper.loadDatePcFlCount(seach);
        putMatchPcCount(pcCountList, errPcCountList, errCountList, fl);
        getMatchPcxData(pcCountList, pcFlList);
        sumPcxDatas(pcCountList);
        ErrorAndFlawTreeVo treevo = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> voList = treevo.dataMapToBean(pcCountList);
        ErrorAndFlawTreeVo.formatTree(voList, null);
        return voList;
    }

    private void putMatchPcCount(List<Map> pcCountList, List<Map> errPcCountList, List<Map> errCountList, List<Map> fl) {
        for (int i = 0; i < pcCountList.size(); i++) {
            Map map = pcCountList.get(i);
            int count = 0;
            if (errPcCountList.size() == 0) {
                map.put("errorAllCount", "0");
            } else {
                for (int a = 0; a < errPcCountList.size(); a++) {
                    Map errPc = errPcCountList.get(a);
                    if (map.get("ID").equals(errPc.get("ID"))) {
                        map.put("errorAllCount", errPc.get("NUM"));
                    } else {
                        if (count == errPcCountList.size() - 1)
                            map.put("errorAllCount", "0");
                        count++;
                    }
                }
            }
            if (errCountList.size() == 0) {
                map.put("errorCount", "0");
            } else {
                count = 0;
                for (int a = 0; a < errCountList.size(); a++) {
                    Map errNum = errCountList.get(a);
                    if (map.get("ID").equals(errNum.get("ID"))) {
                        map.put("errorCount", errNum.get("NUM"));
                    } else {
                        if (count == errCountList.size() - 1)
                            map.put("errorCount", "0");
                        count++;
                    }
                }
            }
            putMatchPcxFlName(map, fl);
        }
    }

    private void putMatchPcCountTx(List<Map> pcCountList, List<Map> errPcCountList, List<Map> errCountList, List<Map> fl) {
        for (int i = 0; i < pcCountList.size(); i++) {
            Map map = pcCountList.get(i);
            int count = 0;
            if (errPcCountList.size() == 0) {
                map.put("errorAllCount", "0");
            } else {
                for (int a = 0; a < errPcCountList.size(); a++) {
                    Map errPc = errPcCountList.get(a);
                    if (map.get("ID").equals(errPc.get("ID")) && map.get("PID").equals(errPc.get("PID"))) {
                        map.put("errorAllCount", errPc.get("NUM"));
                    } else {
                        if (count == errPcCountList.size() - 1)
                            map.put("errorAllCount", "0");
                        count++;
                    }
                }
            }
            if (errCountList.size() == 0) {
                map.put("errorCount", "0");
            } else {
                count = 0;
                for (int a = 0; a < errCountList.size(); a++) {
                    Map errNum = errCountList.get(a);
                    if (map.get("ID").equals(errNum.get("ID")) && map.get("PID").equals(errNum.get("PID"))) {
                        map.put("errorCount", errNum.get("NUM"));
                    } else {
                        if (count == errCountList.size() - 1)
                            map.put("errorCount", "0");
                        count++;
                    }
                }
            }
            putMatchPcxFlName(map, fl);
        }
    }

    private void putMatchPcxFlName(Map m, List<Map> fl) {
        String name = "";
        for (int i = 0; i < fl.size(); i++) {
            Map map = fl.get(i);
            if (map.get("DM").equals("20001"))//证据采信
                name = "zjcx";
            else if (map.get("DM").equals("20002")) //事实认定
                name = "ssrd";
            else if (map.get("DM").equals("20003"))//法律适用
                name = "flsy";
            else if (map.get("DM").equals("20004"))//办案程序
                name = "bacx";
            else if (map.get("DM").equals("20008")) //法律文书
                name = "flws";
            else if (map.get("DM").equals("20010"))//司法责任制落实
                name = "sf";
            else if (map.get("DM").equals("20011"))//系统规范应用
                name = "xtgf";
            else if (map.get("DM").equals("20020"))//其他情况
                name = "qt";
            else if (map.get("DM").equals("20009"))//侦查监督
                name = "zcjd";
            else if (map.get("DM").equals("20006") && map.get("YWTX").equals("10003"))//出席二审法庭
                name = "cxesg";
            else if (map.get("DM").equals("20005")) //特别程序
                name = "tb";
            else if (map.get("DM").equals("20006") && map.get("YWTX").equals("10017"))//出席二审法庭
                name = "cxesw";
            else if (map.get("DM").equals("20007")) //法律监督
                name = "fljd";
            m.put(name + "Hj", "0");
            m.put(name + "Bl", "0.00%");
        }
    }

    private void getMatchPcxData(List<Map> pc, List<Map> pcFlList) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < pc.size(); i++) {
            Map map = pc.get(i);
            if (pcFlList.size() > 0) {
                int count = 0;
                for (int a = 0; a < pcFlList.size(); a++) {
                    Map m = pcFlList.get(a);
                    String name="";
                    if (map.get("ID").equals(m.get("ID"))) {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                        if (m.get("DM").equals("20001")) {//证据采信
                            name="zjcx";
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            name="ssrd";
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            name="flsy";
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            name="bacx";
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            name="flws";
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            name="sf";
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            name="xtgf";
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            name="qt";
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            name="zcjd";
                        } else if (m.get("DM").equals("20006") && (m.get("ZYYWTX")+"").contains("10003")) {//出席二审法庭
                            name="cxesg";
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            name="tb";
                        } else if (m.get("DM").equals("20006") && (m.get("ZYYWTX")+"").contains("10017")) {//出席二审法庭
                            name="cxesw";
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            name="fljd";
                        }
                        map.put(name+"Hj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get(name+"Hj") + ""));
                    } else {
                        if (count == pcFlList.size() - 1) {
                            map.put("id", map.get("ID"));
                            map.put("pid", map.get("PID"));
                            map.put("name", map.get("NAME"));
                        }
                        count++;
                    }
                }
            } else {
                map.put("zjcxHj", "0");
                map.put("zjcxBl", "0.00%");
                map.put("ssrdHj", "0");
                map.put("ssrdBl", "0.00%");
                map.put("flsyHj", "0");
                map.put("flsyBl", "0.00%");
                map.put("bacxHj", "0");
                map.put("bacxBl", "0.00%");
                map.put("flwsHj", "0");
                map.put("flwsBl", "0.00%");
                map.put("sfHj", "0");
                map.put("sfBl", "0.00%");
                map.put("xtgfHj", "0");
                map.put("xtgfBl", "0.00%");
                map.put("qtHj", "0");
                map.put("qtBl", "0.00%");
                map.put("zcjdHj", "0");
                map.put("zcjdBl", "0.00%");
                map.put("cxesgHj", "0");
                map.put("cxesgBl", "0.00%");
                map.put("tbHj", "0");
                map.put("tbBl", "0.00%");
                map.put("cxeswHj", "0");
                map.put("cxeswBl", "0.00%");
                map.put("fljdHj", "0");
                map.put("fljdBl", "0.00%");
                map.put("id", map.get("ID"));
                map.put("pid", map.get("PID"));
                map.put("name", map.get("NAME"));
            }
        }
    }

    private void getMatchPcxDataTx(List<Map> pc, List<Map> pcFlList) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < pc.size(); i++) {
            Map map = pc.get(i);
            if (pcFlList.size() > 0) {
                int count = 0;
                for (int a = 0; a < pcFlList.size(); a++) {
                    Map m = pcFlList.get(a);
                    if (map.get("ID").equals(m.get("ID")) && map.get("PID").equals(m.get("PID"))) {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                        if (m.get("DM").equals("20001")) {//证据采信
                            map.put("zjcxHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("zjcxHj") + ""));
                            map.put("zjcxBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("zjcxHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            map.put("ssrdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("ssrdHj") + ""));
                            map.put("ssrdBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("ssrdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            map.put("flsyHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flsyHj") + ""));
                            map.put("flsyBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("flsyHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            map.put("bacxHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("bacxHj") + ""));
                            map.put("bacxBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("bacxHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            map.put("flwsHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flwsHj") + ""));
                            map.put("flwsBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("flwsHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            map.put("sfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("sfHj") + ""));
                            map.put("sfBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("sfHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            map.put("xtgfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("xtgfHj") + ""));
                            map.put("xtgfBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("xtgfHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            map.put("qtHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("qtHj") + ""));
                            map.put("qtBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("qtHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            map.put("zcjdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("zcjdHj") + ""));
                            map.put("zcjdBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("zcjdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && (m.get("ZYYWTX")+"").contains("10003")) {//出席二审法庭
                            map.put("cxesgHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxesgHj") + ""));
                            map.put("cxesgBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("cxesgHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            map.put("tbHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("tbHj") + ""));
                            map.put("tbBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("tbHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && (m.get("ZYYWTX")+"").contains("10017")) {//出席二审法庭
                            map.put("cxeswHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxeswHj") + ""));
                            map.put("cxeswBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("cxeswHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            map.put("fljdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("fljdHj") + ""));
                            map.put("fljdBl", decimalFormat.format(map.get("errorCount").equals("0") ? 0 : (Double.valueOf(map.get("fljdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
                        }
                    } else {
                        if (count == pcFlList.size() - 1) {
                            map.put("id", map.get("ID"));
                            map.put("pid", map.get("PID"));
                            map.put("name", map.get("NAME"));
                        }
                        count++;
                    }
                }
            } else {
                map.put("zjcxHj", "0");
                map.put("zjcxBl", "0.00%");
                map.put("ssrdHj", "0");
                map.put("ssrdBl", "0.00%");
                map.put("flsyHj", "0");
                map.put("flsyBl", "0.00%");
                map.put("bacxHj", "0");
                map.put("bacxBl", "0.00%");
                map.put("flwsHj", "0");
                map.put("flwsBl", "0.00%");
                map.put("sfHj", "0");
                map.put("sfBl", "0.00%");
                map.put("xtgfHj", "0");
                map.put("xtgfBl", "0.00%");
                map.put("qtHj", "0");
                map.put("qtBl", "0.00%");
                map.put("zcjdHj", "0");
                map.put("zcjdBl", "0.00%");
                map.put("cxesgHj", "0");
                map.put("cxesgBl", "0.00%");
                map.put("tbHj", "0");
                map.put("tbBl", "0.00%");
                map.put("cxeswHj", "0");
                map.put("cxeswBl", "0.00%");
                map.put("fljdHj", "0");
                map.put("fljdBl", "0.00%");
                map.put("id", map.get("ID"));
                map.put("pid", map.get("PID"));
                map.put("name", map.get("NAME"));
            }
        }
    }

    private void sumPcxDatas(List<Map> list) {
        ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            assignmentToPcx(num, map);
        }
        Map m = new HashMap<>();
        calculationNumToPcx(num, m);
        m.put("name", "合计");
        m.put("pid", "-1");
        m.put("id", "10000");
        list.add(0, m);
    }

    @Override
    public List<AjqkzlflVo> getAjqkzlfxByNf(Param_Ajqkzlfx params) throws Exception {
        List<AjqkzlflVo> list = new ArrayList<AjqkzlflVo>();//案件质量情况分析集合
        List<Map> xtdms = null;//系统代码集合
        //办结案件数，评查案件数，优质案件数，合格案件数，不合格案件数，瑕疵案件数
        int bjajs, pcajs, yzajs, hgajs, bhgajs, xcajs;
        String nf, pcjlmc, pczb = null; //年份，评查结论名称, 评查占比
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        AjqkzlflVo ajqkzlflVo = null; //案件情况
        Map<String, Object> pcjlMap = null; //评查结论
        map.put("dwbm", params.getDwbm());
        map.put("pcflbm", params.getPcflbm());
        map.put("ywtx", params.getYwtx());
        map.put("cbrsf", params.getCbrsf());
        map.put("ajtjlb", params.getAjtjlb());
        try {
            xtdms = getXtdmByLbbm("9102");//获取评查结论
//            String[] wcrqnfs = params.getWcrqnf().split(",");
//            String[] wcrqnfs = params.getStartDate().split(","); // 由于以前根据案件年份分开算，现在兼容
            int st_nf=Integer.parseInt(params.getStartDate().substring(0,4));
            int ed_nf=st_nf;
            //后台处理将结束日期+1，年份需要还原
            if (!StringUtils.isEmpty(params.getEndDate())){
                ed_nf = DateTime.parse(params.getEndDate()).plusDays(-1).getYear();
            }
            for (int inf = st_nf; inf <= ed_nf; inf++) {
                // nf = wcrqnfs[i];
                // map.put("wcrqnf", nf);
//                map.put("startDate", params.getStartDate());
                map.put("startDate", inf+"-01-01");
                if(DateUtils.converToDate((inf+1)+"-01-01","yyyy-MM-dd")
                        .compareTo(DateUtils.converToDate(params.getEndDate(),"yyyy-MM-dd"))<0)
                {
                    map.put("endDate", (inf+1)+"-01-01");//因为没有附上 23:59:59
                } else{
                    map.put("endDate", params.getEndDate());
                }
                map.put("pcstartDate", params.getPcstartDate());
                map.put("pcendDate", params.getPcendDate());
                ajqkzlflVo = new AjqkzlflVo();
                bjajs = analysisMapper.getNdBjAjCount(map);
                pcajs = analysisMapper.getNdPcAjCount(map);
//                ajqkzlflVo.setName(nf.split("-")[0]);
                ajqkzlflVo.setName(String.valueOf(inf));
                ajqkzlflVo.setBjs(bjajs);
                ajqkzlflVo.setPcajs(pcajs);
                ajqkzlflVo.setPcajZb(calcPercent(pcajs, bjajs));
                for (int j = 0; j < xtdms.size(); j++) {
                    pcjlMap = xtdms.get(j);
                    pcjlmc = pcjlMap.get("MC").toString();
                    map.put("pcjl", pcjlmc);
                    if ("优质案件".equals(pcjlmc)) {
                        yzajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(yzajs, pcajs);
                        ajqkzlflVo.setYzajs(yzajs);
                        ajqkzlflVo.setYzajZb(pczb);
                    } else if ("合格案件".equals(pcjlmc)) {
                        hgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(hgajs, pcajs);
                        ajqkzlflVo.setHgajs(hgajs);
                        ajqkzlflVo.setHgajZb(pczb);
                    } else if ("瑕疵案件".equals(pcjlmc)) {
                        xcajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(xcajs, pcajs);
                        ajqkzlflVo.setXcajs(xcajs);
                        ajqkzlflVo.setXcajZb(pczb);
                    } else {
                        bhgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(bhgajs, pcajs);
                        ajqkzlflVo.setBhgajs(bhgajs);
                        ajqkzlflVo.setBhgajZb(pczb);
                    }
                }
                list.add(ajqkzlflVo);
            }
            bjajs = 0;
            pcajs = 0;
            yzajs = 0;
            hgajs = 0;
            xcajs = 0;
            bhgajs = 0;
            for (int i = 0; i < list.size(); i++) {
                ajqkzlflVo = list.get(i);
                bjajs += ajqkzlflVo.getBjs();
                pcajs += ajqkzlflVo.getPcajs();
                yzajs += ajqkzlflVo.getYzajs();
                hgajs += ajqkzlflVo.getHgajs();
                xcajs += ajqkzlflVo.getXcajs();
                bhgajs += ajqkzlflVo.getBhgajs();
            }
            ajqkzlflVo = new AjqkzlflVo();
            list.add(0, ajqkzlflVo);
            ajqkzlflVo.setName("合计");
            ajqkzlflVo.setBjs(bjajs);
            ajqkzlflVo.setPcajs(pcajs);
            ajqkzlflVo.setPcajZb(calcPercent(pcajs, bjajs));
            ajqkzlflVo.setYzajs(yzajs);
            ajqkzlflVo.setYzajZb(calcPercent(yzajs, pcajs));
            ajqkzlflVo.setHgajs(hgajs);
            ajqkzlflVo.setHgajZb(calcPercent(hgajs, pcajs));
            ajqkzlflVo.setXcajs(xcajs);
            ajqkzlflVo.setXcajZb(calcPercent(xcajs, pcajs));
            ajqkzlflVo.setBhgajs(bhgajs);
            ajqkzlflVo.setBhgajZb(calcPercent(bhgajs, pcajs));
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * 根据代码类别获取系统代码
     *
     * @param lbbm 类别编码
     * @return 代码列表
     */
    private List<Map> getXtdmByLbbm(String lbbm) {

        Map params = new HashMap<String, String>();
        params.put("lbbm", lbbm);

        return analysisMapper.getXtdmByLbbm(params);
    }

    /**
     * 计算百分比
     *
     * @param fz 分子
     * @param fm 分母
     * @return 百分比
     */
    private String calcPercent(double fz, double fm) {
        if (fm == 0) {
            return "0%";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        double percent = (fz / fm) * 100;
        return df.format(percent) + "%";
    }

    /**
     * 匹配计算数值
     */
    public void getMatchList(List<Map> bj, List<Map> ba, List<Map> pc, List<Map> pca) {
        for (int i = 0; i < bj.size(); i++) {
            Map m = bj.get(i);
            m.put("id", m.get("ID"));
            m.put("name", m.get("NAME"));
            m.put("bjrNum", 0);
            m.put("bjajNum", m.get("NUM"));
            m.put("pcrNum", 0);
            m.put("pcajNum", 0);
            int count = 0;
            for (int a = 0; a < ba.size(); a++) {
                Map map = ba.get(a);
                if (m.get("id").equals(map.get("ID"))) {
                    m.put("bjrNum", map.get("NUM"));
                } else {
                    if (count == ba.size() - 1)
                        m.put("bjrNum", 0);
                    count++;
                }
            }
            count = 0;
            for (int a = 0; a < pc.size(); a++) {
                Map map = pc.get(a);
                if (m.get("id").equals(map.get("ID"))) {
                    m.put("pcrNum", map.get("NUM"));
                } else {
                    if (count == pc.size() - 1)
                        m.put("pcrNum", 0);
                    count++;
                }
            }
            count = 0;
            for (int a = 0; a < pca.size(); a++) {
                Map map = pca.get(a);
                if (m.get("id").equals(map.get("ID"))) {
                    m.put("pcajNum", map.get("NUM"));
                } else {
                    if (count == pca.size() - 1)
                        m.put("pcajNum", 0);
                    count++;
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Double pcajNum = Double.valueOf(m.get("pcajNum") + "");
            Double bjajNum = Double.valueOf(m.get("bjajNum") + "");
            Double bjrNum = Double.valueOf(m.get("bjrNum") + "");
            Double pcrNum = Double.valueOf(m.get("pcrNum") + "");
            if (pcajNum == 0) {
                m.put("pcbl", "0.00%");
                m.put("bpcAvgNum", "0.00");
                m.put("avgNum", "0.00");
            } else {
                m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
            }
        }
    }

    /**
     * 匹配计算数值
     */
    public void getMatchListTx(List<Map> bj, List<Map> ba, List<Map> pc, List<Map> pca) {
        for (int i = 0; i < bj.size(); i++) {
            Map m = bj.get(i);
            m.put("id", m.get("ID"));
            m.put("name", m.get("NAME"));
            m.put("bjrNum", 0);
            m.put("bjajNum", m.get("NUM"));
            m.put("pcrNum", 0);
            m.put("pcajNum", 0);
            int count = 0;
            for (int a = 0; a < ba.size(); a++) {
                Map map = ba.get(a);
                if (m.get("id").equals(map.get("ID")) && m.get("PID").equals(map.get("PID"))) {
                    m.put("bjrNum", map.get("NUM"));
                } else {
                    if (count == ba.size() - 1)
                        m.put("bjrNum", 0);
                    count++;
                }
            }
            count = 0;
            for (int a = 0; a < pc.size(); a++) {
                Map map = pc.get(a);
                if (m.get("id").equals(map.get("ID")) && m.get("PID").equals(map.get("PID"))) {
                    m.put("pcrNum", map.get("NUM"));
                } else {
                    if (count == pc.size() - 1)
                        m.put("pcrNum", 0);
                    count++;
                }
            }
            count = 0;
            for (int a = 0; a < pca.size(); a++) {
                Map map = pca.get(a);
                if (m.get("id").equals(map.get("ID")) && m.get("PID").equals(map.get("PID"))) {
                    m.put("pcajNum", map.get("NUM"));
                } else {
                    if (count == pca.size() - 1)
                        m.put("pcajNum", 0);
                    count++;
                }
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Double pcajNum = Double.valueOf(m.get("pcajNum") + "");
            Double bjajNum = Double.valueOf(m.get("bjajNum") + "");
            Double bjrNum = Double.valueOf(m.get("bjrNum") + "");
            Double pcrNum = Double.valueOf(m.get("pcrNum") + "");
            if (pcajNum == 0) {
                m.put("pcbl", "0.00%");
                m.put("bpcAvgNum", "0.00");
                m.put("avgNum", "0.00");
            } else {
                m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100));
                m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
            }
        }
    }


    @Override
    public List<Map> loadDateGeneral(Map map) {

//        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
        String endDate = (String) map.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("endDate",endDate);
        }

       String pcendDate = (String) map.get("pcendDate");
       if (!StringUtils.isEmpty(pcendDate)){
           pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
           map.put("pcendDate",pcendDate);
       }

        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));

        List<Map> ba = analysisMapper.loadDateGeneralBa(map);
        List<Map> bj = analysisMapper.loadDateGeneralBj(map);
        List<Map> pc = analysisMapper.loadDateGeneralPc(map);
        List<Map> pca = analysisMapper.loadDateGeneralPca(map);
        Map count=analysisMapper.loadGeneralPersonalNum(map);
        getMatchList(bj, ba, pc, pca);
        sumData(bj,count);
        return bj;
    }

    @Override
    public Map getPclbAjJbxx(Map map) {
        // map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));

        String endDate = (String) map.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("endDate",endDate);
        }

        String pcendDate = (String) map.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("pcendDate",pcendDate);
        }

        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));
        int pagenum = (Integer.parseInt(map.get("page") + "") - 1) * Integer.parseInt(map.get("row") + "");
        int row = Integer.parseInt(map.get("row") + "") + pagenum;
        map.put("page", pagenum);
        map.put("row", row);
        Map dataMap = new HashMap();
        if (map.get("type").equals(0)) {
            List<Map> list = analysisMapper.getAJBjlb(map);
            Map count = analysisMapper.getAJBjlbCount(map);
            dataMap.put("total", count.get("NUM"));
            dataMap.put("rows", list);
        } else if (map.get("type").equals(1)) {
            List<Map> list = analysisMapper.getAJPclb(map);
            Map count = analysisMapper.getAJPclbCount(map);
            dataMap.put("total", count.get("NUM"));
            dataMap.put("rows", list);
        }
        return dataMap;
    }

    @Override
    public List<ErrorAndFlawTreeVo> loadDqGeneral(Map map) throws Exception {
        String endDate = (String) map.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("endDate",endDate);
        }

        String pcendDate = (String) map.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("pcendDate",pcendDate);
        }


//        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));
        List<Map> ba = analysisMapper.loadDqGeneralBa(map);
        List<Map> bj = analysisMapper.loadDqGeneralBj(map);
        List<Map> pc = analysisMapper.loadDqGeneralPc(map);
        List<Map> pca = analysisMapper.loadDqGeneralPca(map);
        getMatchList(bj, ba, pc, pca);
        List<Map> dwbms = analysisMapper.getDwbmLists(map);
        List<Map> lists = sumDqGeneralData(bj, dwbms);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for(int i=0;i<lists.size();i++){
            Map m=lists.get(i);
            if((m.get("name")+"").contains("(合计)")){
                String dwbm=m.get("id")+"";
                dwbm = dwbm.substring(0, 6);
                String dw="";
                Map in=new HashMap<>();
                in.put("dwbm",dwbm);
                List<Map> dwlist = analysisMapper.loadGeneralDwNum(in);
                for(int a=0;a<dwlist.size();a++){
                    Map m1=dwlist.get(a);
                    for(int b=0;b<dwbms.size();b++) {
                        Map m2=dwbms.get(b);
                        if (m1.get("DWBM").equals(m2.get("ID"))) {
                            dw+=m1.get("DWBM")+",";
                        }
                    }
                }
                map.put("dwbm", "".equals(dw) ? "" : dw.split(","));
                Map counts=analysisMapper.loadGeneralPersonalNum(map);
                m.put("bjrNum", counts==null?"0":counts.get("BJR")+"");
                m.put("pcrNum", counts==null?"0":counts.get("PCR")+"");
                Double pcajNum = Double.valueOf( m.get("pcajNum") + "");
                Double bjajNum = Double.valueOf( m.get("bjajNum") + "");
                Double bjrNum = Double.valueOf( m.get("bjrNum") + "");
                Double pcrNum = Double.valueOf( m.get("pcrNum") + "");
                if (pcajNum == 0) {
                    m.put("pcbl", "0.00%");
                    m.put("bpcAvgNum", "0.00");
                    m.put("avgNum", "0.00");
                } else {
                    m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                    m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                    m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
                }
            }
        }
        ErrorAndFlawTreeVo treevo = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> voList = treevo.dataMapToBeanG(lists);
        List<ErrorAndFlawTreeVo> vos = ErrorAndFlawTreeVo.formatTreeDq(voList);
        return vos;
    }

    private List<Map> sumDqGeneralData(List<Map> lists, List<Map> list) {
        List<Map> returnList = new ArrayList<>();
        List<Map<String, String>> hjKeys = new ArrayList<Map<String, String>>(); //合计记录
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            int count = 0;
            if(lists.size()==0){
                m.put("bjrNum", 0);
                m.put("bjajNum", 0);
                m.put("pcrNum", 0);
                m.put("pcajNum", 0);
                m.put("pcbl", "0.00%");
                m.put("bpcAvgNum", "0.00");
                m.put("avgNum", "0.00");
                m.put("id", m.get("ID"));
                m.put("pid", m.get("PID"));
                m.put("name", m.get("DWMC"));
                continue;
            }
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("ID").equals(map.get("id"))) {
                    m.put("bjrNum", map.get("bjrNum"));
                    m.put("bjajNum", map.get("bjajNum"));
                    m.put("pcrNum", map.get("pcrNum"));
                    m.put("pcajNum", map.get("pcajNum"));
                    m.put("pcbl", map.get("pcbl"));
                    m.put("bpcAvgNum", map.get("bpcAvgNum"));
                    m.put("avgNum", map.get("avgNum"));
                    m.put("id", m.get("ID"));
                    m.put("pid", m.get("PID"));
                    m.put("name", m.get("DWMC"));
                } else {
                    if (count == lists.size() - 1) {
                        m.put("bjrNum", 0);
                        m.put("bjajNum", 0);
                        m.put("pcrNum", 0);
                        m.put("pcajNum", 0);
                        m.put("pcbl", "0.00%");
                        m.put("bpcAvgNum", "0.00");
                        m.put("avgNum", "0.00");
                        m.put("id", m.get("ID"));
                        m.put("pid", m.get("PID"));
                        m.put("name", m.get("DWMC"));
                    }
                    count++;
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Map ms = list.get(i);
            if ("Y".equals(ms.get("SFHJ").toString())) {
                Map<String, String> hjkey = new HashMap<>();
                hjKeys.add(hjkey);
                try {
                    String id = ms.get("ID").toString();
                    String pid = ms.get("PID").toString();
                    hjkey.put("dwbm", id);
                    hjkey.put("fdwbm", id + pid);
                    Map map = new HashMap<>();
                    map.put("id", id + pid);
                    map.put("pid", pid);
                    if ("2".equals(ms.get("DWJB").toString())) {
                        map.put("name", "湖北" + "(合计)");
                    } else if (ms.get("DWMC").toString().indexOf("铁") > 0) {
                        map.put("name", "铁检" + "(合计)");
                    } else {
                        map.put("name", ms.get("DWMC").toString().substring(0, 2) + "(合计)");
                    }
                    returnList.add(map);
                    for (int j = 0; j < hjKeys.size(); j++) {
                        if (ms.get("ID").toString().equals(hjKeys.get(j).get("dwbm").toString())) {
                            ms.put("pid", hjKeys.get(j).get("fdwbm").toString());
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else {
                ms.put("id", ms.get("ID"));
                ms.put("pid", ms.get("PID"));
                ms.put("name", ms.get("DWMC"));
            }
        }
        //sumDatas(list, list);
        sumDatas(returnList, list);
        sumDataAllsDqGeneral(returnList);
        List<Map> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            for (int a = 0; a < returnList.size(); a++) {
                Map map = returnList.get(a);
                if (map.get("id").equals(m.get("pid"))) {
                    newList.add(map);
                }
            }
            newList.add(m);
        }
        return newList;
    }

    private void sumDataAllsDqGeneral(List<Map> list) {
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
            for (int a = 0; a < list.size(); a++) {
                Map map = list.get(a);
                String id = m.get("id") + "";
                id = id.substring(0, 6);
                String ids = map.get("id") + "";
                ids = ids.substring(0, 6);
                String pid = map.get("pid") + "";
                if (id.equals(pid) || id.equals(ids)) {
                    assignmentToGeneral(num, map);
                }
            }
            calculationNumToGeneral(num, m);
        }
    }

    public void assignmentToGeneral(ErrorAndFlawTreeVo num, Map map) {
        num.setBjajNum(Integer.valueOf(map.get("bjajNum") + "") + num.getBjajNum());
        num.setBjrNum(Integer.valueOf(map.get("bjrNum") + "") + num.getBjrNum());
        num.setPcrNum(Integer.valueOf(map.get("pcrNum") + "") + num.getPcrNum());
        num.setPcajNum(Integer.valueOf(map.get("pcajNum") + "") + num.getPcajNum());
    }

    public void calculationNumToGeneral(ErrorAndFlawTreeVo num, Map m) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        m.put("bjrNum", num.getBjrNum());
        m.put("bjajNum", num.getBjajNum());
        m.put("pcrNum", num.getPcrNum());
        m.put("pcajNum", num.getPcajNum());
        Double pcajNum = Double.valueOf(m.get("pcajNum") + "");
        Double bjajNum = Double.valueOf(m.get("bjajNum") + "");
        Double bjrNum = Double.valueOf(m.get("bjrNum") + "");
        Double pcrNum = Double.valueOf(m.get("pcrNum") + "");
        if (pcajNum == 0) {
            m.put("pcbl", "0.00%");
            m.put("bpcAvgNum", "0.00");
            m.put("avgNum", "0.00");
        } else {
            m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
            m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
            m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
        }
    }

    @Override
    public List<ErrorAndFlawTreeVo> loadTxGeneral(Map map) throws Exception {
//        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));
      /*  List<Map> ba=analysisMapper.loadTxGeneralBa(map);
        List<Map> bj=analysisMapper.loadTxGeneralBj(map);
        List<Map> pc=analysisMapper.loadTxGeneralPc(map);
        List<Map> pca=analysisMapper.loadTxGeneralPca(map);*/
       /* getMatchListTx(bj,ba,pc,pca);
        List<Map> lists=new ArrayList<>();
        sumTxGeneral(bj,lists);*/
        List<Map> list = analysisMapper.loadTxGeneralAll(map);
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);

            if (m.get("sfhj").equals("Y")) {
                m.put("id", m.get("YWTX").equals("-1") ? "10000" : m.get("YWTX"));
                m.put("pid", m.get("AJLB_BM"));
                m.put("name", m.get("YWTX_MC"));
            } else {
                m.put("id", m.get("AJLB_BM"));
                m.put("pid", m.get("YWTX"));
                m.put("name", m.get("AJLB_MC"));
            }
        }
        Map count=analysisMapper.loadGeneralPersonalNum(map);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if(list.size()>0) {
            list.get(0).put("bjrNum", count == null ? "0" : count.get("BJR") + "");
            list.get(0).put("pcrNum", count == null ? "0" : count.get("PCR") + "");
            Double pcajNum = Double.valueOf(list.get(0).get("pcajNum") + "");
            Double bjajNum = Double.valueOf(list.get(0).get("bjajNum") + "");
            Double bjrNum = Double.valueOf(list.get(0).get("bjrNum") + "");
            Double pcrNum = Double.valueOf(list.get(0).get("pcrNum") + "");
            if (pcajNum == 0) {
                list.get(0).put("pcbl", "0.00%");
                list.get(0).put("bpcAvgNum", "0.00");
                list.get(0).put("avgNum", "0.00");
            } else {
                list.get(0).put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                list.get(0).put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                list.get(0).put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
            }
            for (int i = 1; i < list.size(); i++) {
                Map m = list.get(i);
                if ("Y".equals(m.get("sfhj"))) {
                    map.put("ywtx", (m.get("YWTX") + "").split(","));
                    Map counts = analysisMapper.loadGeneralPersonalNum(map);
                    m.put("bjrNum", counts.get("BJR") + "");
                    m.put("pcrNum", counts.get("PCR") + "");
                    pcajNum = Double.valueOf(m.get("pcajNum") + "");
                    bjajNum = Double.valueOf(m.get("bjajNum") + "");
                    bjrNum = Double.valueOf(m.get("bjrNum") + "");
                    pcrNum = Double.valueOf(m.get("pcrNum") + "");
                    if (pcajNum == 0) {
                        m.put("pcbl", "0.00%");
                        m.put("bpcAvgNum", "0.00");
                        m.put("avgNum", "0.00");
                    } else {
                        m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                        m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                        m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
                    }
                }
            }
        }
        ErrorAndFlawTreeVo treevo = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> voList = treevo.dataMapToBeanG(list);
        List<ErrorAndFlawTreeVo> vo = ErrorAndFlawTreeVo.formatTreeDq(voList);
        return vo;
    }

    private void sumTxGeneral(List<Map> lists, List<Map> list) {
      /*  if (list.size() == 0) {
            Map m = lists.get(0);
            Map inMap = new HashMap<>();
            inMap.put("id", m.get("PID"));
            inMap.put("pid", "-1");
            inMap.put("name", m.get("MC"));
            list.add(inMap);
            m.put("pid", m.get("PID"));
        }
        a:
        for (int i = 0; i < lists.size(); i++) {
            Map map = lists.get(i);
            map.put("pid", map.get("PID"));
            int count = 0;
            for (int a = 0; a < list.size(); a++) {
                Map m = list.get(a);
                if (m.get("id").equals(map.get("pid")) || m.get("id").equals(map.get("PID"))) {
                    continue a;
                } else {
                    if (count == list.size() - 1) {
                        Map inMap = new HashMap<>();
                        inMap.put("id", map.get("PID"));
                        inMap.put("pid", "-1");
                        inMap.put("name", map.get("MC"));
                        list.add(inMap);
                        continue a;
                    }
                    count++;
                }
            }
        }
        sumData(list, lists);
        sumData(list);*/
    }

    private void sumDatas(List<Map> list, List<Map> lists) {
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
           /* if(m.get("id").equals("420000"))
                continue;*/
            ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("id").equals(map.get("pid")) || m.get("id").equals(map.get("id"))) {
                    assignmentToGeneral(num, map);
                    for (int b = 0; b < lists.size(); b++) {
                        Map mapb = lists.get(b);
                        if (map.get("id").equals(mapb.get("pid"))) {
                            assignmentToGeneral(num, mapb);
                        }
                    }
                }
            }
            calculationNumToGeneral(num, m);
        }
    }

    private void sumData(List<Map> list, List<Map> lists) {
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("id").equals(map.get("pid"))) {
                    assignmentToGeneral(num, map);
                }
            }
            calculationNumToGeneral(num, m);
        }
    }

    private void sumData(List<Map> list,Map count) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
        if(list.size()>0){
            for (int a = 0; a < list.size(); a++) {
                Map map = list.get(a);
                assignmentToGeneral(num, map);
            }
            Map m = new HashMap<>();
            calculationNumToGeneral(num, m);
            m.put("bjrNum", count.get("BJR")+"");
            m.put("pcrNum", count.get("PCR")+"");
            Double pcajNum = Double.valueOf(m.get("pcajNum") + "");
            Double bjajNum = Double.valueOf(m.get("bjajNum") + "");
            Double bjrNum = Double.valueOf(m.get("bjrNum") + "");
            Double pcrNum = Double.valueOf(m.get("pcrNum") + "");
            if (pcajNum == 0) {
                m.put("pcbl", "0.00%");
                m.put("bpcAvgNum", "0.00");
                m.put("avgNum", "0.00");
            } else {
                m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
            }
            m.put("name", "合计");
            m.put("pid", "-1");
            m.put("id", "10000");
            list.add(0, m);
        }
    }

    @Override
    public List<Map> getPcdmByFdm(Map params) throws Exception {
        List<Map> list = null;
        try {
            list = analysisMapper.getPcdmByFdm(params);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<AjqkzlflTreeVo> getAjzlqkfxByDq(Param_Ajqkzlfx params) throws Exception {
        List<AjqkzlflTreeVo> list = new ArrayList<AjqkzlflTreeVo>();//案件质量情况分析集合
        List<Map> xtdms = null;//系统代码集合
        List<Map> dwbms = null;//单位编码集合
        //办结案件数，评查案件数，优质案件数，合格案件数，不合格案件数，瑕疵案件数
        int bjajs, pcajs, yzajs, hgajs, bhgajs, xcajs;
        String nf, pcjlmc, pczb = null; //年份，评查结论名称, 评查占比
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        AjqkzlflTreeVo ajqkzlflVo = null; //案件情况
        Map<String, Object> pcjlMap = null; //评查结论
        map.put("pcflbm", params.getPcflbm());
        map.put("ywtx", params.getYwtx());
        map.put("cbrsf", params.getCbrsf());
        map.put("ajtjlb", params.getAjtjlb());
        List<Map<String, String>> hjKeys = new ArrayList<Map<String, String>>(); //合计记录
        try {
            xtdms = getXtdmByLbbm("9102");//获取评查结论
            Map dwbmsParams = new HashMap<String, Object>();
            dwbmsParams.put("dwbm", params.getDwbm());
            dwbms = analysisMapper.getDwbmList(dwbmsParams);
            for (int i = 0; i < dwbms.size(); i++) {
                dwbmsParams = dwbms.get(i);
              //  map.put("wcrqnf", params.getWcrqnf());
                map.put("dwbm", dwbmsParams.get("ID").toString());
                ajqkzlflVo = new AjqkzlflTreeVo();
                ajqkzlflVo.setId(dwbmsParams.get("ID").toString());
                if ("Y".equals(dwbmsParams.get("SFHJ").toString())) {
                    list.add(createHjAjqkzlfxTreeVo(params, dwbmsParams, xtdms, hjKeys));
                    for (int j = 0; j < hjKeys.size(); j++) {
                        if (dwbmsParams.get("ID").toString().equals(hjKeys.get(j).get("dwbm").toString())) {
                            ajqkzlflVo.setPid(hjKeys.get(j).get("fdwbm").toString());
                        }
                    }
                } else {
                    ajqkzlflVo.setPid(dwbmsParams.get("PID").toString());
                }
                bjajs = analysisMapper.getNdBjAjCount(map);
                pcajs = analysisMapper.getNdPcAjCount(map);
                ajqkzlflVo.setName(dwbmsParams.get("DWMC").toString());
                ajqkzlflVo.setBjs(bjajs);
                ajqkzlflVo.setPcajs(pcajs);
                ajqkzlflVo.setPcajZb(calcPercent(pcajs, bjajs));
                for (int j = 0; j < xtdms.size(); j++) {
                    pcjlMap = xtdms.get(j);
                    pcjlmc = pcjlMap.get("MC").toString();
                    map.put("pcjl", pcjlmc);
                    if ("优质案件".equals(pcjlmc)) {
                        yzajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(yzajs, pcajs);
                        ajqkzlflVo.setYzajs(yzajs);
                        ajqkzlflVo.setYzajZb(pczb);
                    } else if ("合格案件".equals(pcjlmc)) {
                        hgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(hgajs, pcajs);
                        ajqkzlflVo.setHgajs(hgajs);
                        ajqkzlflVo.setHgajZb(pczb);
                    } else if ("瑕疵案件".equals(pcjlmc)) {
                        xcajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(xcajs, pcajs);
                        ajqkzlflVo.setXcajs(xcajs);
                        ajqkzlflVo.setXcajZb(pczb);
                    } else {
                        bhgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(bhgajs, pcajs);
                        ajqkzlflVo.setBhgajs(bhgajs);
                        ajqkzlflVo.setBhgajZb(pczb);
                    }
                }
                list.add(ajqkzlflVo);
            }
            list = AjqkzlflTreeVo.formatTree(list);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }


    /**
     * 创建合计案件情况质量分析
     *
     * @param params 查询条件
     * @param dwbm   需要合计的单位编码
     * @param xtdms  评查结论
     * @return 合计案件情况质量分析对象
     */
    private AjqkzlflTreeVo createHjAjqkzlfxTreeVo(Param_Ajqkzlfx params, Map dwbm, List<Map> xtdms, List<Map<String, String>> hjkeys) throws Exception {
        AjqkzlflTreeVo ajqkzlflTreeVo = new AjqkzlflTreeVo();
        //办结案件数，评查案件数，优质案件数，合格案件数，不合格案件数，瑕疵案件数
        int bjajs, pcajs, yzajs, hgajs, bhgajs, xcajs;
        String nf, pcjlmc, pczb = null; //年份，评查结论名称, 评查占比
        Map<String, Object> pcjlMap = null; //评查结论
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        Map<String, String> hjkey = new HashMap<>();
        hjkeys.add(hjkey);
        try {
            String id = dwbm.get("ID").toString();
            String pid = dwbm.get("PID").toString();
            hjkey.put("dwbm", id);
            hjkey.put("fdwbm", id + pid);
            map.put("pcflbm", params.getPcflbm());
            map.put("ywtx", params.getYwtx());
            map.put("cbrsf", params.getCbrsf());
            map.put("wcrqnf", params.getWcrqnf());
            map.put("ajtjlb", params.getAjtjlb());
            map.put("fdwbm", id);
            map.put("bm", params.getDwbm());
            ajqkzlflTreeVo = new AjqkzlflTreeVo();
            ajqkzlflTreeVo.setId(id + pid);
            ajqkzlflTreeVo.setPid(pid);
            ajqkzlflTreeVo.setState("closed");
            bjajs = analysisMapper.getNdBjAjCount(map);
            pcajs = analysisMapper.getNdPcAjCount(map);
            if ("2".equals(dwbm.get("DWJB").toString())) {
                ajqkzlflTreeVo.setName("湖北" + "(合计)");
            } else if (dwbm.get("DWMC").toString().indexOf("铁") > 0) {
                ajqkzlflTreeVo.setName("铁检" + "(合计)");
            } else {
                ajqkzlflTreeVo.setName(dwbm.get("DWMC").toString().substring(0, 2) + "(合计)");
            }
            ajqkzlflTreeVo.setBjs(bjajs);
            ajqkzlflTreeVo.setPcajs(pcajs);
            ajqkzlflTreeVo.setPcajZb(calcPercent(pcajs, bjajs));
            for (int j = 0; j < xtdms.size(); j++) {
                pcjlMap = xtdms.get(j);
                pcjlmc = pcjlMap.get("MC").toString();
                map.put("pcjl", pcjlmc);
                if ("优质案件".equals(pcjlmc)) {
                    yzajs = analysisMapper.getNdPcAjCountByPcjl(map);
                    pczb = calcPercent(yzajs, pcajs);
                    ajqkzlflTreeVo.setYzajs(yzajs);
                    ajqkzlflTreeVo.setYzajZb(pczb);
                } else if ("合格案件".equals(pcjlmc)) {
                    hgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                    pczb = calcPercent(hgajs, pcajs);
                    ajqkzlflTreeVo.setHgajs(hgajs);
                    ajqkzlflTreeVo.setHgajZb(pczb);
                } else if ("瑕疵案件".equals(pcjlmc)) {
                    xcajs = analysisMapper.getNdPcAjCountByPcjl(map);
                    pczb = calcPercent(xcajs, pcajs);
                    ajqkzlflTreeVo.setXcajs(xcajs);
                    ajqkzlflTreeVo.setXcajZb(pczb);
                } else {
                    bhgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                    pczb = calcPercent(bhgajs, pcajs);
                    ajqkzlflTreeVo.setBhgajs(bhgajs);
                    ajqkzlflTreeVo.setBhgajZb(pczb);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ajqkzlflTreeVo;
    }


    @Override
    public List<AjqkzlflTreeVo> getAjzlqkfxByTx(Param_Ajqkzlfx params) throws Exception {
        List<AjqkzlflTreeVo> list = new ArrayList<AjqkzlflTreeVo>();//案件质量情况分析集合
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        List<Map> ajs = null;
        map.put("pcflbm", params.getPcflbm());
        map.put("cbrsf", params.getCbrsf());
        map.put("dwbm", params.getDwbm());
       // map.put("wcrqnf", params.getWcrqnf());
        map.put("startDate",params.getStartDate());
        map.put("endDate",params.getEndDate());
        map.put("pcstartDate",params.getPcstartDate());
        map.put("pcendDate",params.getPcendDate());
        map.put("ywtx", params.getYwtx());
        map.put("ajtjlb", params.getAjtjlb());
        AjqkzlflTreeVo ajqkzlflVo = null; //案件情况
        Map ajMap = null;
        try {
            ajs = this.analysisMapper.getAjzlpcfxByYwtx(map);
            for (int i = 0; i < ajs.size(); i++) {
                Map m = ajs.get(i);
                if (m.get("SFHJ").equals("Y")) {
                    m.put("ID", m.get("YWTX").equals("-1") ? "10000" : m.get("YWTX"));
                    m.put("PID", m.get("AJLB_BM"));
                    m.put("NAME", m.get("YWTX_MC"));
                } else {
                    m.put("ID", m.get("AJLB_BM"));
                    m.put("PID", m.get("YWTX"));
                    m.put("NAME", m.get("AJLB_MC"));
                }
            }
            for (int i = 0; i < ajs.size(); i++) {
                ajMap = ajs.get(i);
                ajqkzlflVo = new AjqkzlflTreeVo();
                ajqkzlflVo.setId(ajMap.get("ID").toString());
                ajqkzlflVo.setPid(ajMap.get("PID").toString());
                ajqkzlflVo.setName(ajMap.get("NAME").toString());
                ajqkzlflVo.setBjs(Integer.parseInt(ajMap.get("BJAJS").toString()));
                ajqkzlflVo.setPcajs(Integer.parseInt(ajMap.get("PCAJS").toString()));
                ajqkzlflVo.setPcajZb(ajMap.get("PCZB").toString());
                ajqkzlflVo.setYzajs(Integer.parseInt(ajMap.get("YZAJS").toString()));
                ajqkzlflVo.setYzajZb(ajMap.get("YZAJZB").toString());
                ajqkzlflVo.setHgajs(Integer.parseInt(ajMap.get("HGAJS").toString()));
                ajqkzlflVo.setHgajZb(ajMap.get("HGAJZB").toString());
                ajqkzlflVo.setXcajs(Integer.parseInt(ajMap.get("XCAJS").toString()));
                ajqkzlflVo.setXcajZb(ajMap.get("XCAJZB").toString());
                ajqkzlflVo.setBhgajs(Integer.parseInt(ajMap.get("BHGAJS").toString()));
                ajqkzlflVo.setBhgajZb(ajMap.get("BHGAJZB").toString());
                list.add(ajqkzlflVo);
            }
            list = AjqkzlflTreeVo.formatTree(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    @Override
    public List<ErrorAndFlawTreeVo> loadTxData(Map seach) throws Exception {
        String endDate = (String) seach.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            String s = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("endDate",s);
        }

        String pcendDate = (String) seach.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            String s = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("pcendDate",s);
        }

      //  seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
        seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
        seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
        seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
        List<Map> fl = analysisMapper.loadDateFl();
        List<Map> pcCountList = analysisMapper.loadTxPcCount(seach);
        List<Map> errPcCountList = analysisMapper.loadTxErrPcCount(seach);
        List<Map> errCountList = analysisMapper.loadTxErrCount(seach);
        List<Map> pcFlList = analysisMapper.loadTxPcFlCount(seach);
        putMatchPcCountTx(pcCountList, errPcCountList, errCountList, fl);
        getMatchPcxDataTx(pcCountList, pcFlList);
        List<Map> list = new ArrayList<>();
        sumPcxData(pcCountList, list);
        ErrorAndFlawTreeVo treevo = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> voList = treevo.dataMapToBean(list);
        List<ErrorAndFlawTreeVo> voLists = treevo.dataMapToBean(pcCountList);
        List<ErrorAndFlawTreeVo> vo = ErrorAndFlawTreeVo.formatTree(voList, voLists);
        return vo;
    }

    @Override
    public List<ErrorAndFlawTreeVo> loadDqData(Map seach) throws Exception {
        String endDate = (String) seach.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            String s = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("endDate",s);
        }

        String pcendDate = (String) seach.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            String s = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("pcendDate",s);
        }

        // seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
        seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
        seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
        seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
        List<Map> dwbms = analysisMapper.getDwbmLists(seach);
        List<Map> fl = analysisMapper.loadDateFl();
        List<Map> pcCountList = analysisMapper.loadDqPcCount(seach);
        List<Map> errPcCountList = analysisMapper.loadDqErrPcCount(seach);
        List<Map> errCountList = analysisMapper.loadDqErrCount(seach);
        List<Map> pcFlList = analysisMapper.loadDqPcFlCount(seach);
        putMatchPcCount(pcCountList, errPcCountList, errCountList, fl);
        getMatchPcxData(pcCountList, pcFlList);
        List<Map> list = sumPcxDataDq(pcCountList, dwbms);
        ErrorAndFlawTreeVo treevo = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> voList = treevo.dataMapToBean(list);
        List<ErrorAndFlawTreeVo> vo = ErrorAndFlawTreeVo.formatTreeDq(voList);
        return vo;
    }

    private List<Map> sumPcxDataDq(List<Map> lists, List<Map> list) {
        List<Map> returnList = new ArrayList<>();
        List<Map<String, String>> hjKeys = new ArrayList<Map<String, String>>(); //合计记录
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            int count = 0;
            if(lists.size()==0){
                m.put("PCALLCOUNT", "0");
                m.put("errorAllCount", "0");
                m.put("errorCount", "0");
                m.put("zjcxHj", "0");
                m.put("zjcxBl", "0.00%");
                m.put("ssrdHj", "0");
                m.put("ssrdBl", "0.00%");
                m.put("flsyHj", "0");
                m.put("flsyBl", "0.00%");
                m.put("bacxHj", "0");
                m.put("bacxBl", "0.00%");
                m.put("flwsHj", "0");
                m.put("flwsBl", "0.00%");
                m.put("sfHj", "0");
                m.put("sfBl", "0.00%");
                m.put("xtgfHj", "0");
                m.put("xtgfBl", "0.00%");
                m.put("qtHj", "0");
                m.put("qtBl", "0.00%");
                m.put("zcjdHj", "0");
                m.put("zcjdBl", "0.00%");
                m.put("cxesgHj", "0");
                m.put("cxesgBl", "0.00%");
                m.put("tbHj", "0");
                m.put("tbBl", "0.00%");
                m.put("cxeswHj", "0");
                m.put("cxeswBl", "0.00%");
                m.put("fljdHj", "0");
                m.put("fljdBl", "0.00%");
                m.put("id", m.get("ID"));
                m.put("pid", m.get("PID"));
                m.put("name", m.get("DWMC"));
                continue;
            }
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("ID").equals(map.get("id"))) {
                    m.put("PCALLCOUNT", map.get("PCALLCOUNT"));
                    m.put("errorAllCount", map.get("errorAllCount"));
                    m.put("errorCount", map.get("errorCount"));
                    m.put("zjcxHj", map.get("zjcxHj"));
                    m.put("ssrdHj", map.get("ssrdHj"));
                    m.put("flsyHj", map.get("flsyHj"));
                    m.put("bacxHj", map.get("bacxHj"));
                    m.put("flwsHj", map.get("flwsHj"));
                    m.put("sfHj", map.get("sfHj"));
                    m.put("xtgfHj", map.get("xtgfHj"));
                    m.put("qtHj", map.get("qtHj"));
                    m.put("zcjdHj", map.get("zcjdHj"));
                    m.put("cxesgHj", map.get("cxesgHj"));
                    m.put("tbHj", map.get("tbHj"));
                    m.put("cxeswHj", map.get("cxeswHj"));
                    m.put("fljdHj", map.get("fljdHj"));
                    m.put("zjcxBl", map.get("zjcxBl"));
                    m.put("ssrdBl", map.get("ssrdBl"));
                    m.put("flsyBl", map.get("flsyBl"));
                    m.put("bacxBl", map.get("bacxBl"));
                    m.put("flwsBl", map.get("flwsBl"));
                    m.put("sfBl", map.get("sfBl"));
                    m.put("xtgfBl", map.get("xtgfBl"));
                    m.put("qtBl", map.get("qtBl"));
                    m.put("zcjdBl", map.get("zcjdBl"));
                    m.put("cxesgBl", map.get("cxesgBl"));
                    m.put("tbBl", map.get("tbBl"));
                    m.put("cxeswBl", map.get("cxeswBl"));
                    m.put("fljdBl", map.get("fljdBl"));
                    m.put("id", m.get("ID"));
                    m.put("pid", m.get("PID"));
                    m.put("name", m.get("DWMC"));
                } else {
                    if (count == lists.size() - 1) {
                        m.put("PCALLCOUNT", "0");
                        m.put("errorAllCount", "0");
                        m.put("errorCount", "0");
                        m.put("zjcxHj", "0");
                        m.put("zjcxBl", "0.00%");
                        m.put("ssrdHj", "0");
                        m.put("ssrdBl", "0.00%");
                        m.put("flsyHj", "0");
                        m.put("flsyBl", "0.00%");
                        m.put("bacxHj", "0");
                        m.put("bacxBl", "0.00%");
                        m.put("flwsHj", "0");
                        m.put("flwsBl", "0.00%");
                        m.put("sfHj", "0");
                        m.put("sfBl", "0.00%");
                        m.put("xtgfHj", "0");
                        m.put("xtgfBl", "0.00%");
                        m.put("qtHj", "0");
                        m.put("qtBl", "0.00%");
                        m.put("zcjdHj", "0");
                        m.put("zcjdBl", "0.00%");
                        m.put("cxesgHj", "0");
                        m.put("cxesgBl", "0.00%");
                        m.put("tbHj", "0");
                        m.put("tbBl", "0.00%");
                        m.put("cxeswHj", "0");
                        m.put("cxeswBl", "0.00%");
                        m.put("fljdHj", "0");
                        m.put("fljdBl", "0.00%");
                        m.put("id", m.get("ID"));
                        m.put("pid", m.get("PID"));
                        m.put("name", m.get("DWMC"));
                    }
                    count++;
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Map ms = list.get(i);
            if ("Y".equals(ms.get("SFHJ").toString())) {
                Map<String, String> hjkey = new HashMap<>();
                hjKeys.add(hjkey);
                try {
                    String id = ms.get("ID").toString();
                    String pid = ms.get("PID").toString();
                    hjkey.put("dwbm", id);
                    hjkey.put("fdwbm", id + pid);
                    Map map = new HashMap<>();
                    map.put("id", id + pid);
                    map.put("pid", pid);
                    if ("2".equals(ms.get("DWJB").toString())) {
                        map.put("name", "湖北" + "(合计)");
                    } else if (ms.get("DWMC").toString().indexOf("铁") > 0) {
                        map.put("name", "铁检" + "(合计)");
                    } else {
                        map.put("name", ms.get("DWMC").toString().substring(0, 2) + "(合计)");
                    }
                    returnList.add(map);
                    for (int j = 0; j < hjKeys.size(); j++) {
                        if (ms.get("ID").toString().equals(hjKeys.get(j).get("dwbm").toString())) {
                            ms.put("pid", hjKeys.get(j).get("fdwbm").toString());
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else {
                ms.put("id", ms.get("ID"));
                ms.put("pid", ms.get("PID"));
                ms.put("name", ms.get("DWMC"));
            }
        }
      //  sumPcxDataAll(list, list);
        sumPcxDataAll(returnList, list);
        sumPcxDataAllsDq(returnList);
        List<Map> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            for (int a = 0; a < returnList.size(); a++) {
                Map map = returnList.get(a);
                if (map.get("id").equals(m.get("pid"))) {
                    newList.add(map);
                }
            }
            newList.add(m);
        }
        return newList;
    }

    private void sumPcxData(List<Map> lists, List<Map> list) {
        if (list.size() == 0) {
            Map m = lists.get(0);
            Map inMap = new HashMap<>();
            inMap.put("id", m.get("pid"));
            inMap.put("pid", "-1");
            inMap.put("name", m.get("MC") + "(合计)");
            list.add(inMap);
        }
        a:
        for (int i = 0; i < lists.size(); i++) {
            Map map = lists.get(i);
            int count = 0;
            for (int a = 0; a < list.size(); a++) {
                Map m = list.get(a);
                if (m.get("id").equals(map.get("pid"))) {
                    continue a;
                } else {
                    if (count == list.size() - 1) {
                        Map inMap = new HashMap<>();
                        inMap.put("id", map.get("pid"));
                        inMap.put("pid", "-1");
                        inMap.put("name", map.get("MC") + "(合计)");
                        list.add(inMap);
                        continue a;
                    }
                    count++;
                }
            }
        }
        sumPcxDataAll(list, lists);
        sumPcxDatas(list);
    }

    public void assignmentToPcx(ErrorAndFlawTreeVo num, Map map) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Double pcAllCount = Double.valueOf(map.get("PCALLCOUNT") + "");
        Double errorAllCount = Double.valueOf(map.get("errorAllCount") + "");
        Double errorCount = Double.valueOf(map.get("errorCount") + "");
        num.setPcajs(pcAllCount + num.getPcajs());
        num.setCwajs(errorAllCount + num.getCwajs());
        num.setCws(errorCount+num.getCws());
        num.setZjcxHj(Integer.valueOf(map.get("zjcxHj") + "") + num.getZjcxHj());
        num.setSsrdHj(Integer.valueOf(map.get("ssrdHj") + "") + num.getSsrdHj());
        num.setFlsyHj(Integer.valueOf(map.get("flsyHj") + "") + num.getFlsyHj());
        num.setBacxHj(Integer.valueOf(map.get("bacxHj") + "") + num.getBacxHj());
        num.setFlwsHj(Integer.valueOf(map.get("flwsHj") + "") + num.getFlwsHj());
        num.setSfHj(Integer.valueOf(map.get("sfHj") + "") + num.getSfHj());
        num.setXtgfHj(Integer.valueOf(map.get("xtgfHj") + "") + num.getXtgfHj());
        num.setQtHj(Integer.valueOf(map.get("qtHj") + "") + num.getQtHj());
        num.setZcjdHj(Integer.valueOf(map.get("zcjdHj") + "") + num.getZcjdHj());
        num.setCxesgHj(Integer.valueOf(map.get("cxesgHj") + "") + num.getCxesgHj());
        num.setTbHj(Integer.valueOf(map.get("tbHj") + "") + num.getTbHj());
        num.setCxeswHj(Integer.valueOf(map.get("cxeswHj") + "") + num.getCxeswHj());
        num.setFljdHj(Integer.valueOf(map.get("fljdHj") + "") + num.getFljdHj());
        map.put("zjcxBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("zjcxHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("ssrdBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("ssrdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("flsyBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("flsyHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("bacxBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("bacxHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("flwsBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("flwsHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("sfBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("sfHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("xtgfBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("xtgfHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("qtBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("qtHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("zcjdBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("zcjdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("cxesgBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("cxesgHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("tbBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("tbHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("cxeswBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("cxeswHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        map.put("fljdBl", decimalFormat.format(Double.valueOf(map.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(map.get("fljdHj") + "") / Double.valueOf(map.get("errorCount") + "") * 100)) + "%");
        Double errorBl = pcAllCount == 0 ? 0 : (errorAllCount / pcAllCount) * 100;
        Double errorAvgCount = pcAllCount == 0 ? 0 : (errorCount / pcAllCount) * 100;
        Double errorAvgNum = errorAllCount == 0 ? 0 : (errorCount / errorAllCount);
        map.put("errorBl", decimalFormat.format(errorBl) + "%");
        map.put("errorAvgCount", decimalFormat.format(errorAvgCount) + "%");
        map.put("errorAvgNum", decimalFormat.format(errorAvgNum));
    }

    public void calculationNumToPcx(ErrorAndFlawTreeVo num, Map m) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        m.put("PCALLCOUNT", num.getPcajs());
        m.put("errorAllCount", num.getCwajs());
        m.put("errorCount", num.getCws());
        m.put("zjcxHj", num.getZjcxHj());
        m.put("ssrdHj", num.getSsrdHj());
        m.put("flsyHj", num.getFlsyHj());
        m.put("bacxHj", num.getBacxHj());
        m.put("flwsHj", num.getFlwsHj());
        m.put("sfHj", num.getSfHj());
        m.put("xtgfHj", num.getXtgfHj());
        m.put("qtHj", num.getQtHj());
        m.put("zcjdHj", num.getZcjdHj());
        m.put("cxesgHj", num.getCxesgHj());
        m.put("tbHj", num.getTbHj());
        m.put("cxeswHj", num.getCxeswHj());
        m.put("fljdHj", num.getFljdHj());
        m.put("zjcxBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("zjcxHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("ssrdBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("ssrdHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("flsyBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("flsyHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("bacxBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("bacxHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("flwsBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("flwsHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("sfBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("sfHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("xtgfBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("xtgfHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("qtBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("qtHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("zcjdBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("zcjdHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("cxesgBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("cxesgHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("tbBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("tbHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("cxeswBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("cxeswHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        m.put("fljdBl", decimalFormat.format(Double.valueOf(m.get("errorCount") + "") == 0 ? 0 : (Double.valueOf(m.get("fljdHj") + "") / Double.valueOf(m.get("errorCount") + "") * 100)) + "%");
        Double pcAllCount = Double.valueOf(m.get("PCALLCOUNT") + "");
        Double errorAllCount = Double.valueOf(m.get("errorAllCount") + "");
        Double errorCount = Double.valueOf(m.get("errorCount") + "");
        Double errorBlNum = pcAllCount == 0 ? 0 : (errorAllCount / pcAllCount) * 100;
        Double errorAvgCountNum = pcAllCount == 0 ? 0 : (errorCount / pcAllCount) * 100;
        Double errorAvgNums = errorAllCount == 0 ? 0 : (errorCount / errorAllCount);
        m.put("errorBl", decimalFormat.format(errorBlNum) + "%");
        m.put("errorAvgCount", decimalFormat.format(errorAvgCountNum) + "%");
        m.put("errorAvgNum", decimalFormat.format(errorAvgNums));
    }

    private void sumPcxDataAll(List<Map> list, List<Map> lists) {
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
           /* if(m.get("id").equals("420000"))
                continue;*/
            ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("id").equals(map.get("pid")) || m.get("id").equals(map.get("id"))) {
                    assignmentToPcx(num, map);
                    for (int b = 0; b < lists.size(); b++) {
                        Map mapb = lists.get(b);
                        if (map.get("id").equals(mapb.get("pid"))) {
                            assignmentToPcx(num, mapb);
                        }
                    }
                }
            }
            calculationNumToPcx(num, m);
        }
    }

    private void sumPcxDataAllsDq(List<Map> list) {
        a:
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
            for (int a = 0; a < list.size(); a++) {
                Map map = list.get(a);
                String id = m.get("id") + "";
                id = id.substring(0, 6);
                String ids = map.get("id") + "";
                ids = ids.substring(0, 6);
                String pid = map.get("pid") + "";
                if (id.equals(pid) || id.equals(ids)) {
                    assignmentToPcx(num, map);
                }
            }
            calculationNumToPcx(num, m);
        }
    }

    @Override
    public List<Map> getTcfx(Param_Ajqkzlfx params) throws Exception {
        List<Map> list = new ArrayList<Map>();//案件评查项统计集合
        List<Map> pcxs = null;//系统代码集合
        Map pcx = null; //评查项
        //评查案件数，案件数
        int pcajs, ajs;
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        Map<String, Object> pcxMap = null; //评查项
        map.put("pcflbm", params.getPcflbm());
        map.put("cbrsf", params.getCbrsf());
        map.put("dwbm", params.getDwbm());
        map.put("ywtx", params.getYwtx());
        map.put("startDate",params.getStartDate());
        map.put("endDate",params.getEndDate());
        map.put("pcstartDate",params.getPcstartDate());
        map.put("pcendDate",params.getPcendDate());

      //  map.put("wcrqnf", params.getWcrqnf());
        map.put("ajtjlb", params.getAjtjlb());
        try {
            map.put("flxtdm", params.getWtType());
            pcxs = this.analysisMapper.getAjPcxCountByXtdm(map);
            //评查案件总数
//            pcajs = this.analysisMapper.getNdPcAjCount(map);
            //存在错误/瑕疵的案件总数
            int cxajSum = pcxs.stream().mapToInt(a -> Integer.parseInt(a.get("CNT").toString())).sum();

            for (int i = 0; i < pcxs.size(); i++) {
                pcx = pcxs.get(i);
                pcxMap = new HashMap<String, Object>();
                ajs = Integer.parseInt(pcx.get("CNT").toString());
                pcxMap.put("xtdm", pcx.get("DM").toString());
                pcxMap.put("name", pcx.get("MC").toString());
                pcxMap.put("cnt", ajs);
                pcxMap.put("pczb", calcPercent(ajs, cxajSum));
                list.add(pcxMap);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /*******************************************************导出excel数据************************************************/
    @Override
    public List<List<String>> excel_export_data(Map seach) throws Exception {
        //seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
        String endDate = (String) seach.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            String s = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("endDate",s);
        }

        String pcendDate = (String) seach.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            String s = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("pcendDate",s);
        }

        seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
        seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
        seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
        List<Map> pxc = analysisMapper.getPcxLists(seach);
        if ((seach.get("type") + "").equals("0")) {
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadDatePcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadDateErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadDateErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadDatePcFlCount(seach);
            putMatchPcCount(pcCountList, errPcCountList, errCountList, fl);
            getMatchPcxData(pcCountList, pcFlList);
            sumPcxDatas(pcCountList);
            List<List<String>> data = resultExcelList(pcCountList);
            return data;
        } else if ((seach.get("type") + "").equals("1")) {
            List<Map> dwbms = analysisMapper.getDwbmLists(seach);
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadDqPcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadDqErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadDqErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadDqPcFlCount(seach);
            putMatchPcCount(pcCountList, errPcCountList, errCountList, fl);
            getMatchPcxData(pcCountList, pcFlList);
            List<Map> list = sumPcxDataDq(pcCountList, dwbms);
            List<List<String>> data = resultExcelList(list);
            return data;
        } else {
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadTxPcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadTxErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadTxErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadTxPcFlCount(seach);
            putMatchPcCountTx(pcCountList, errPcCountList, errCountList, fl);
            getMatchPcxDataTx(pcCountList, pcFlList);
            List<Map> list = new ArrayList<>();
            sumPcxData(pcCountList, list);
            List<Map> newList = dataMatchExcel(list, pcCountList);
            List<List<String>> data = resultExcelList(newList);
            return data;
        }
        //return null;
    }

    @Override
    public List<List<String>> excel_export_dataPc(Map seach) throws Exception {
        String endDate = (String) seach.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            String s = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("endDate",s);
        }

        String pcendDate = (String) seach.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            String s = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            seach.put("pcendDate",s);
        }

        if ((seach.get("type") + "").equals("0")) {
            List<Map> list = loadDateGeneral(seach);
            List<List<String>> data = resultExcelListZt(list);
            return data;
        } else if ((seach.get("type") + "").equals("1")) {
            //seach.put("wcrqnf", "".equals(seach.get("wcrqnf")) ? "" : (seach.get("wcrqnf") + "").split(","));
            seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
            seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
            seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
            List<Map> ba = analysisMapper.loadDqGeneralBa(seach);
            List<Map> bj = analysisMapper.loadDqGeneralBj(seach);
            List<Map> pc = analysisMapper.loadDqGeneralPc(seach);
            List<Map> pca = analysisMapper.loadDqGeneralPca(seach);
            getMatchList(bj, ba, pc, pca);
            List<Map> dwbms = analysisMapper.getDwbmLists(seach);
            List<Map> list = sumDqGeneralData(bj, dwbms);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            for(int i=0;i<list.size();i++){
                Map m=list.get(i);
                if((m.get("name")+"").contains("(合计)")){
                    String dwbm=m.get("id")+"";
                    dwbm = dwbm.substring(0, 6);
                    String dw="";
                    Map in=new HashMap<>();
                    in.put("dwbm",dwbm);
                    List<Map> dwlist = analysisMapper.loadGeneralDwNum(in);
                    for(int a=0;a<dwlist.size();a++){
                        Map m1=dwlist.get(a);
                        for(int b=0;b<dwbms.size();b++) {
                            Map m2=dwbms.get(b);
                            if (m1.get("DWBM").equals(m2.get("ID"))) {
                                dw+=m1.get("DWBM")+",";
                            }
                        }
                    }
                    seach.put("dwbm", "".equals(dw) ? "" : dw.split(","));
                    Map counts=analysisMapper.loadGeneralPersonalNum(seach);
                    m.put("bjrNum", counts==null?"0":counts.get("BJR")+"");
                    m.put("pcrNum", counts==null?"0":counts.get("PCR")+"");
                    Double pcajNum = Double.valueOf( m.get("pcajNum") + "");
                    Double bjajNum = Double.valueOf( m.get("bjajNum") + "");
                    Double bjrNum = Double.valueOf( m.get("bjrNum") + "");
                    Double pcrNum = Double.valueOf( m.get("pcrNum") + "");
                    if (pcajNum == 0) {
                        m.put("pcbl", "0.00%");
                        m.put("bpcAvgNum", "0.00");
                        m.put("avgNum", "0.00");
                    } else {
                        m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                        m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                        m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
                    }
                }
            }
            List<List<String>> data = resultExcelListZt(list);
            return data;
        } else {
          //  seach.put("wcrqnf", "".equals(seach.get("wcrqnf")) ? "" : (seach.get("wcrqnf") + "").split(","));
            seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
            seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
            seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
            List<Map> list = analysisMapper.loadTxGeneralAll(seach);
            for (int i = 0; i < list.size(); i++) {
                Map m = list.get(i);
                if (m.get("sfhj").equals("Y")) {
                    m.put("id", m.get("YWTX").equals("-1") ? "10000" : m.get("YWTX"));
                    m.put("pid", m.get("AJLB_BM"));
                    m.put("name", m.get("YWTX_MC"));
                } else {
                    m.put("id", m.get("AJLB_BM"));
                    m.put("pid", m.get("YWTX"));
                    m.put("name", m.get("AJLB_MC"));
                }
            }
            Map count=analysisMapper.loadGeneralPersonalNum(seach);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            if(list.size()>0) {
                list.get(0).put("bjrNum", count.get("BJR") + "");
                list.get(0).put("pcrNum", count.get("PCR") + "");
                Double pcajNum = Double.valueOf(list.get(0).get("pcajNum") + "");
                Double bjajNum = Double.valueOf(list.get(0).get("bjajNum") + "");
                Double bjrNum = Double.valueOf(list.get(0).get("bjrNum") + "");
                Double pcrNum = Double.valueOf(list.get(0).get("pcrNum") + "");
                if (pcajNum == 0) {
                    list.get(0).put("pcbl", "0.00%");
                    list.get(0).put("bpcAvgNum", "0.00");
                    list.get(0).put("avgNum", "0.00");
                } else {
                    list.get(0).put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                    list.get(0).put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                    list.get(0).put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
                }
                for (int i = 1; i < list.size(); i++) {
                    Map m = list.get(i);
                    if ("Y".equals(m.get("sfhj"))) {
                        seach.put("ywtx", (m.get("YWTX") + "").split(","));
                        Map counts = analysisMapper.loadGeneralPersonalNum(seach);
                        m.put("bjrNum", counts.get("BJR") + "");
                        m.put("pcrNum", counts.get("PCR") + "");
                        pcajNum = Double.valueOf(m.get("pcajNum") + "");
                        bjajNum = Double.valueOf(m.get("bjajNum") + "");
                        bjrNum = Double.valueOf(m.get("bjrNum") + "");
                        pcrNum = Double.valueOf(m.get("pcrNum") + "");
                        if (pcajNum == 0) {
                            m.put("pcbl", "0.00%");
                            m.put("bpcAvgNum", "0.00");
                            m.put("avgNum", "0.00");
                        } else {
                            m.put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                            m.put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                            m.put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
                        }
                    }
                }
            }
            List<List<String>> data = resultExcelListZt(list);
            return data;
        }
        //return null;
    }

    private List<Map> dataMatchExcel(List<Map> list, List<Map> lists) {
        List<Map> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            newList.add(m);
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                if (m.get("id").equals(map.get("pid")) || m.get("id").equals(map.get("id"))) {
                    newList.add(map);
                }
            }
        }
        return newList;
    }

    public List<List<String>> resultExcelList(List<Map> list) {
        List<List<String>> data = new ArrayList<List<String>>();
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            List<String> sigleData = new ArrayList<>();
            sigleData.add(String.valueOf(m.get("name")));
            sigleData.add(String.valueOf(m.get("PCALLCOUNT")));
            sigleData.add(String.valueOf(m.get("errorAllCount")));
            sigleData.add(String.valueOf(m.get("errorBl")));
            sigleData.add(String.valueOf(m.get("errorCount")));
            sigleData.add(String.valueOf(m.get("errorAvgCount")));
            sigleData.add(String.valueOf(m.get("errorAvgNum")));
            sigleData.add(String.valueOf(m.get("zjcxHj")));
            sigleData.add(String.valueOf(m.get("zjcxBl")));
            sigleData.add(String.valueOf(m.get("ssrdHj")));
            sigleData.add(String.valueOf(m.get("ssrdBl")));
            sigleData.add(String.valueOf(m.get("flsyHj")));
            sigleData.add(String.valueOf(m.get("flsyBl")));
            sigleData.add(String.valueOf(m.get("bacxHj")));
            sigleData.add(String.valueOf(m.get("bacxBl")));
            sigleData.add(String.valueOf(m.get("flwsHj")));
            sigleData.add(String.valueOf(m.get("flwsBl")));
            sigleData.add(String.valueOf(m.get("sfHj")));
            sigleData.add(String.valueOf(m.get("sfBl")));
            sigleData.add(String.valueOf(m.get("xtgfHj")));
            sigleData.add(String.valueOf(m.get("xtgfBl")));
            sigleData.add(String.valueOf(m.get("qtHj")));
            sigleData.add(String.valueOf(m.get("qtBl")));
            sigleData.add(String.valueOf(m.get("zcjdHj")));
            sigleData.add(String.valueOf(m.get("zcjdBl")));
            sigleData.add(String.valueOf(m.get("cxesgHj")));
            sigleData.add(String.valueOf(m.get("cxesgBl")));
            sigleData.add(String.valueOf(m.get("tbHj")));
            sigleData.add(String.valueOf(m.get("tbBl")));
            sigleData.add(String.valueOf(m.get("cxeswHj")));
            sigleData.add(String.valueOf(m.get("cxeswBl")));
            sigleData.add(String.valueOf(m.get("fljdHj")));
            sigleData.add(String.valueOf(m.get("fljdBl")));
            data.add(sigleData);
        }
        return data;
    }


    public List<List<String>> resultExcelListZt(List<Map> list) {
        List<List<String>> data = new ArrayList<List<String>>();
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            List<String> sigleData = new ArrayList<>();
            sigleData.add(String.valueOf(m.get("name")));
            sigleData.add(String.valueOf(m.get("bjrNum")));
            sigleData.add(String.valueOf(m.get("bjajNum")));
            sigleData.add(String.valueOf(m.get("pcrNum")));
            sigleData.add(String.valueOf(m.get("pcajNum")));
            sigleData.add(String.valueOf(m.get("pcbl")));
            sigleData.add(String.valueOf(m.get("bpcAvgNum")));
            sigleData.add(String.valueOf(m.get("avgNum")));
            data.add(sigleData);
        }
        return data;
    }

    @Override
    public List<Map> getPcxBzByXtdm(Map map) throws Exception {
        List<Map> list = null;
        try {

            String endDate = (String) map.get("endDate");
            if (!StringUtils.isEmpty(endDate)){
                endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
                map.put("endDate",endDate);
            }

            String pcendDate = (String) map.get("pcendDate");
            if (!StringUtils.isEmpty(pcendDate)){
                pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
                map.put("pcendDate",pcendDate);
            }

            list = analysisMapper.getPcxBzByXtdm(map);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public Map getErrPclbAjJbxx(Map map) {

        String endDate = (String) map.get("endDate");
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("endDate",endDate);
        }

        String pcendDate = (String) map.get("pcendDate");
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
            map.put("pcendDate",pcendDate);
        }

      //  map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));
        int pagenum = (Integer.parseInt(map.get("page") + "") - 1) * Integer.parseInt(map.get("row") + "");
        int row = Integer.parseInt(map.get("row") + "") + pagenum;
        map.put("page", pagenum);
        map.put("row", row);
        Map dataMap = new HashMap();
        List<Map> list = analysisMapper.getErrAJPclb(map);
        Map count = analysisMapper.getErrAJPclbCount(map);
        dataMap.put("total", count.get("NUM"));
        dataMap.put("rows", list);
        return dataMap;
    }

    @Override
    public List<List<String>> getAjzlqkfxByNdToExcel(Param_Ajqkzlfx param) {
        List<List<String>> list = null;
        List<AjqkzlflVo> ajqkzlflList = null;//案件质量情况分析集合
        AjqkzlflVo ajqkzlflVo = null;
        try {
            ajqkzlflList = getAjqkzlfxByNf(param);
            list = new ArrayList<List<String>>();
            for (int i = 0; i < ajqkzlflList.size(); i++) {
                ajqkzlflVo = ajqkzlflList.get(i);
                List<String> sigleData = new ArrayList<>();
                sigleData.add(ajqkzlflVo.getName());
                sigleData.add(Integer.toString(ajqkzlflVo.getBjs()));
                sigleData.add(Integer.toString(ajqkzlflVo.getPcajs()));
                sigleData.add(ajqkzlflVo.getPcajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getYzajs()));
                sigleData.add(ajqkzlflVo.getYzajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getHgajs()));
                sigleData.add(ajqkzlflVo.getHgajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getXcajs()));
                sigleData.add(ajqkzlflVo.getXcajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getBhgajs()));
                sigleData.add(ajqkzlflVo.getBhgajZb());
                list.add(sigleData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<List<String>> getAjzlqkfxByDqToExcel(Param_Ajqkzlfx params) {
        List<AjqkzlflTreeVo> list = new ArrayList<AjqkzlflTreeVo>();//案件质量情况分析集合
        List<Map> xtdms = null;//系统代码集合
        List<Map> dwbms = null;//单位编码集合
        //办结案件数，评查案件数，优质案件数，合格案件数，不合格案件数，瑕疵案件数
        int bjajs, pcajs, yzajs, hgajs, bhgajs, xcajs;
        String nf, pcjlmc, pczb = null; //年份，评查结论名称, 评查占比
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        AjqkzlflTreeVo ajqkzlflVo = null; //案件情况
        Map<String, Object> pcjlMap = null; //评查结论
        map.put("pcflbm", params.getPcflbm());
        map.put("ywtx", params.getYwtx());
        map.put("cbrsf", params.getCbrsf());
        map.put("ajtjlb", params.getAjtjlb());
        List<Map<String, String>> hjKeys = new ArrayList<Map<String, String>>(); //合计记录
        List<List<String>> resultList = new ArrayList<List<String>>();
        try {
            xtdms = getXtdmByLbbm("9102");//获取评查结论
            Map dwbmsParams = new HashMap<String, Object>();
            dwbmsParams.put("dwbm", params.getDwbm());
            dwbms = analysisMapper.getDwbmList(dwbmsParams);
            for (int i = 0; i < dwbms.size(); i++) {
                dwbmsParams = dwbms.get(i);
               // map.put("wcrqnf", params.getWcrqnf());
                map.put("startDate", params.getStartDate());
                map.put("endDate", params.getEndDate());
                map.put("pcstartDate", params.getPcstartDate());
                map.put("pcendDate", params.getPcendDate());

                map.put("dwbm", dwbmsParams.get("ID").toString());
                ajqkzlflVo = new AjqkzlflTreeVo();
                ajqkzlflVo.setId(dwbmsParams.get("ID").toString());
                if ("Y".equals(dwbmsParams.get("SFHJ").toString())) {
                    list.add(createHjAjqkzlfxTreeVo(params, dwbmsParams, xtdms, hjKeys));
                    for (int j = 0; j < hjKeys.size(); j++) {
                        if (dwbmsParams.get("ID").toString().equals(hjKeys.get(j).get("dwbm").toString())) {
                            ajqkzlflVo.setPid(hjKeys.get(j).get("fdwbm").toString());
                        }
                    }
                } else {
                    ajqkzlflVo.setPid(dwbmsParams.get("PID").toString());
                }
                bjajs = analysisMapper.getNdBjAjCount(map);
                pcajs = analysisMapper.getNdPcAjCount(map);
                ajqkzlflVo.setName(dwbmsParams.get("DWMC").toString());
                ajqkzlflVo.setBjs(bjajs);
                ajqkzlflVo.setPcajs(pcajs);
                ajqkzlflVo.setPcajZb(calcPercent(pcajs, bjajs));
                for (int j = 0; j < xtdms.size(); j++) {
                    pcjlMap = xtdms.get(j);
                    pcjlmc = pcjlMap.get("MC").toString();
                    map.put("pcjl", pcjlmc);
                    if ("优质案件".equals(pcjlmc)) {
                        yzajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(yzajs, pcajs);
                        ajqkzlflVo.setYzajs(yzajs);
                        ajqkzlflVo.setYzajZb(pczb);
                    } else if ("合格案件".equals(pcjlmc)) {
                        hgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(hgajs, pcajs);
                        ajqkzlflVo.setHgajs(hgajs);
                        ajqkzlflVo.setHgajZb(pczb);
                    } else if ("瑕疵案件".equals(pcjlmc)) {
                        xcajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(xcajs, pcajs);
                        ajqkzlflVo.setXcajs(xcajs);
                        ajqkzlflVo.setXcajZb(pczb);
                    } else {
                        bhgajs = analysisMapper.getNdPcAjCountByPcjl(map);
                        pczb = calcPercent(bhgajs, pcajs);
                        ajqkzlflVo.setBhgajs(bhgajs);
                        ajqkzlflVo.setBhgajZb(pczb);
                    }
                }
                list.add(ajqkzlflVo);
            }
            for (int i = 0; i < list.size(); i++) {
                ajqkzlflVo = list.get(i);
                List<String> sigleData = new ArrayList<>();
                sigleData.add(ajqkzlflVo.getName());
                sigleData.add(Integer.toString(ajqkzlflVo.getBjs()));
                sigleData.add(Integer.toString(ajqkzlflVo.getPcajs()));
                sigleData.add(ajqkzlflVo.getPcajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getYzajs()));
                sigleData.add(ajqkzlflVo.getYzajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getHgajs()));
                sigleData.add(ajqkzlflVo.getHgajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getXcajs()));
                sigleData.add(ajqkzlflVo.getXcajZb());
                sigleData.add(Integer.toString(ajqkzlflVo.getBhgajs()));
                sigleData.add(ajqkzlflVo.getBhgajZb());
                resultList.add(sigleData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<List<String>> getAjzlqkfxByTxToExcel(Param_Ajqkzlfx param) {
        List<List<String>> list = null;
        Map<String, Object> map = new HashMap<String, Object>(); //查询条件
        List<Map> ajs = null;
        map.put("pcflbm", param.getPcflbm());
        map.put("cbrsf", param.getCbrsf());
        map.put("dwbm", param.getDwbm());
       // map.put("wcrqnf", param.getWcrqnf());
        map.put("startDate", param.getStartDate());
        map.put("endDate", param.getEndDate());
        map.put("pcstartDate", param.getPcstartDate());
        map.put("pcendDate", param.getPcendDate());

        map.put("ywtx", param.getYwtx());
        map.put("ajtjlb", param.getAjtjlb());
        Map ajMap = null;
        try {
            ajs = this.analysisMapper.getAjzlpcfxByYwtx(map);
            list = new ArrayList<List<String>>();
            for (int i = 0; i < ajs.size(); i++) {
                ajMap = ajs.get(i);
                List<String> sigleData = new ArrayList<>();
                sigleData.add("-1".equals(ajMap.get("AJLB_BM"))?ajMap.get("YWTX_MC").toString()+"（合计）":ajMap.get("AJLB_MC").toString());
                sigleData.add(ajMap.get("BJAJS").toString());
                sigleData.add(ajMap.get("PCAJS").toString());
                sigleData.add(ajMap.get("PCZB").toString());
                sigleData.add(ajMap.get("YZAJS").toString());
                sigleData.add(ajMap.get("YZAJZB").toString());
                sigleData.add(ajMap.get("HGAJS").toString());
                sigleData.add(ajMap.get("HGAJZB").toString());
                sigleData.add(ajMap.get("XCAJS").toString());
                sigleData.add(ajMap.get("XCAJZB").toString());
                sigleData.add(ajMap.get("BHGAJS").toString());
                sigleData.add(ajMap.get("BHGAJZB").toString());
                list.add(sigleData);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<Map> getPcAjxxByParams(Map map) throws Exception {
        List<Map> list = null;
        try {
            PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
            list = this.analysisMapper.getPcAjxxByParams(map);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<Map> getTccwxxPcAjxxByParams(Map map) throws Exception {
        List<Map> list = null;
        try {
            PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
            list = this.analysisMapper.getTccwxxPcAjxxByParams(map);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<Map> getZdSxgz(String djdwbm, String pcflbm) {

        return analysisMapper.getZdSxgz(djdwbm, pcflbm);
    }

    /**
     * 获取重点案件分析总体情况
     * @param
     * @return
     */
    @Override
    public List<ZdFxTreeVo> loadZdZtqk(ZdFxQuery zdFxQuery) {
        // 办结案件数、评查案件数、办案人数、评查人数、
        List<ZdFxZtqkDto> list = analysisMapper.getZdqk(zdFxQuery);

        List<ZdFxTreeVo> nodes = calcAllNode(list);

        List<ZdFxTreeVo> treeNodes = ZdFxTreeVo.buildTree(nodes);
//        List<ZdFxTreeVo> treeNodes  = ZdFxTreeVo.formatToTree(nodes);

        return treeNodes;
    }

    private List<ZdFxTreeVo> calcAllNode(List<ZdFxZtqkDto> list) {

        List<ZdFxTreeVo> result = new ArrayList<>();

        if (CollectionUtils.isEmpty(list)){
            return result;
        }

        list.stream().forEach(zdFxZtqkDto -> {
            ZdFxTreeVo node = new ZdFxTreeVo();
            node.setId(zdFxZtqkDto.getId());
            node.setName(zdFxZtqkDto.getName());
            node.setPid(zdFxZtqkDto.getPid());

            node.setCbrcount(zdFxZtqkDto.getCbrcount());
            node.setBjcount(zdFxZtqkDto.getBjcount());
            node.setPcrcount(zdFxZtqkDto.getPcrcount());
            node.setPccount(zdFxZtqkDto.getPccount());

            result.add(node);
        });


        // 构造‘合计’节点：
 /*       ZdFxTreeVo rootNode = new ZdFxTreeVo();
        rootNode.setId("-1");
        rootNode.setPid("-1");
        rootNode.setName("合计");

        Integer Cbrcount = list.stream().map(childNode -> childNode.getCbrcount()).reduce(0, (a, b) -> a + b);
        Integer Bjcount = list.stream().map(childNode -> childNode.getBjcount()).reduce(0, (a, b) -> a + b);
        Integer Pcrcount = list.stream().map(childNode -> childNode.getPcrcount()).reduce(0, (a, b) -> a + b);
        Integer Pccount = list.stream().map(childNode -> childNode.getPccount()).reduce(0, (a, b) -> a + b);

        rootNode.setCbrcount(Cbrcount);
        rootNode.setBjcount(Bjcount);
        rootNode.setPcrcount(Pcrcount);
        rootNode.setPccount(Pccount);

        result.add(0,rootNode);*/

        // 父项合并子项
//        result.stream().filter(parentNode-> parentNode.getPid().equals("-1") && !parentNode.getId().equals("-1") )
//                .forEach(node->{
//
//                    List<ZdFxZtqkDto> allchildNode = list.stream().filter(childNode -> childNode.getPid().equals(node.getId())).collect(Collectors.toList());
//                    if (!CollectionUtils.isEmpty(allchildNode)){
//
//                        Integer childCbrcount = allchildNode.stream().map(childNode -> childNode.getCbrcount()).reduce(0, (a, b) -> a + b);
//                        Integer chilBjcount = allchildNode.stream().map(childNode -> childNode.getBjcount()).reduce(0, (a, b) -> a + b);
//                        Integer childPcrcount = allchildNode.stream().map(childNode -> childNode.getPcrcount()).reduce(0, (a, b) -> a + b);
//                        Integer childPccount = allchildNode.stream().map(childNode -> childNode.getPccount()).reduce(0, (a, b) -> a + b);
//
//                        node.setCbrcount(node.getCbrcount() + childCbrcount);
//                        node.setBjcount(node.getBjcount() + chilBjcount);
//                        node.setPcrcount(node.getPcrcount() + childPcrcount);
//                        node.setPccount(node.getPccount() + childPccount);
//
//                    }
//
//                });


        // 计算比率
        result.stream().forEach(res -> {
            // 评查比例
            res.setPcl(calcPercent(res.getPccount(), res.getBjcount()));

            // 承办人被评查案件数量
            res.setCbrbpcl(calcPercent(res.getPccount(), res.getCbrcount()));

            // 评查人评查案件数量
            res.setPcrpcl(calcPercent(res.getPccount(), res.getPcrcount()));

        });


        return result;

    }

    /**
     * 获取重点案件分析质量分析
     * @param zdFxQuery
     * @return
     */
    @Override
    public List<ZdFxTreeVo> loadZdZlfx(ZdFxQuery zdFxQuery) {

        //办结数、评查数、优质数、合格数、瑕疵数、不合格数
        List<ZdFxzlfxDto> list = analysisMapper.getZdzlfx(zdFxQuery);

        List<ZdFxTreeVo> nodes = calcZlfx(list);

        List<ZdFxTreeVo> treeNodes = ZdFxTreeVo.buildTree(nodes);

        return treeNodes;
    }

    private List<ZdFxTreeVo> calcZlfx(List<ZdFxzlfxDto> list) {
        List<ZdFxTreeVo> result = new ArrayList<>();

        if (CollectionUtils.isEmpty(list)){
            return result;
        }

        list.stream().forEach(zdFxZtqkDto -> {

            ZdFxTreeVo node = new ZdFxTreeVo();

            try {
                BeanUtils.copyProperties(node,zdFxZtqkDto);
            } catch (Exception e) {
                e.getMessage();
                System.out.println("缺少某个成员");
            }

            result.add(node);
        });


        // 构造‘合计’节点：
   /*     ZdFxTreeVo rootNode = new ZdFxTreeVo();
        rootNode.setId("-1");
        rootNode.setPid("-1");
        rootNode.setName("合计");

        Integer Bjcount = list.stream().map(childNode -> childNode.getBjcount()).reduce(0, (a, b) -> a + b);
        Integer Pccount = list.stream().map(childNode -> childNode.getPccount()).reduce(0, (a, b) -> a + b);
        Integer Yzcount = list.stream().map(childNode -> childNode.getYzcount()).reduce(0, (a, b) -> a + b);
        Integer Hgcount = list.stream().map(childNode -> childNode.getHgcount()).reduce(0, (a, b) -> a + b);
        Integer Xccount = list.stream().map(childNode -> childNode.getXccount()).reduce(0, (a, b) -> a + b);
        Integer Bhgcount = list.stream().map(childNode -> childNode.getBhgcount()).reduce(0, (a, b) -> a + b);

        rootNode.setBjcount(Bjcount);
        rootNode.setPccount(Pccount);
        rootNode.setYzcount(Yzcount);
        rootNode.setHgcount(Hgcount);
        rootNode.setXccount(Xccount);
        rootNode.setBhgcount(Bhgcount);

        result.add(0,rootNode);*/

        result.stream().forEach(res -> {
            // 评查比例
            res.setPcl(calcPercent(res.getPccount(), res.getBjcount()));

            // 优质占比
            res.setYzpcl(calcPercent(res.getYzcount(), res.getPccount()));

            // 合格占比
            res.setHgpcl(calcPercent(res.getHgcount(), res.getPccount()));

            //瑕疵占比
            res.setXcpcl(calcPercent(res.getXccount(), res.getPccount()));

            // 不合格占比
            res.setBhgpcl(calcPercent(res.getBhgcount(), res.getPccount()));

        });

        return result;
    }

    @Override
    public List<Map> getZdAjJbxx(ZdFxQuery zdFxQuery) {
        PageHelper.startPage(zdFxQuery.getPage(), zdFxQuery.getRows());
        return analysisMapper.getZdAjJbxx(zdFxQuery);
    }

    @Override
    public String exportZdFxExcel(ZdFxQuery zdFxQuery) throws IOException {

        String filePath ="";

        switch (zdFxQuery.getExporttype()){
            case "ztqk": filePath =  exportZtqk(zdFxQuery);break;
            case "zlfx": filePath =  exportZlfx(zdFxQuery);break;
        }

        return filePath;
    }

    private String exportZlfx(ZdFxQuery zdFxQuery) throws IOException {

        List<ZdFxzlfxDto> list = analysisMapper.getZdzlfx(zdFxQuery);
        List<ZdFxTreeVo> nodes = calcZlfx(list);
        ArrayList<List<String>> data = new ArrayList<>();

        nodes.stream().forEach(node->{
            ArrayList<String> singleData = new ArrayList<>();
            singleData.add(node.getName() +"");
            singleData.add(node.getBjcount() + "");
            singleData.add(node.getPccount() + "");
            singleData.add(node.getPcl() + "");
            singleData.add(node.getYzcount() + "");
            singleData.add(node.getYzpcl() + "");
            singleData.add(node.getHgcount() + "");
            singleData.add(node.getHgpcl() + "");
            singleData.add(node.getXccount() + "");
            singleData.add(node.getXcpcl() + "");
            singleData.add(node.getBhgcount() + "");
            singleData.add(node.getBhgpcl() + "");

            data.add(singleData);
        });


        String fileName = "重点案件质量分析";
        String sourceFile = SystemConfiguration.wzbsPath + "/File/moban/重点案件质量分析.xls";
//        String sourceFile = "D:\\dev\\hubei_zlpc\\src\\main\\resources\\static\\File\\moban\\重点案件质量分析.xls";


        ExcelWriteToFile excelVo = new ExcelWriteToFile();
        excelVo.setFileName(fileName);
        excelVo.setData(data);
        excelVo.setSourcefile(sourceFile);
        excelVo.setStartLine(2);

        return  excelUtils.exportExcelDataWriteLineNumber(excelVo);
    }

    private String exportZtqk(ZdFxQuery zdFxQuery) {
        
        List<ZdFxZtqkDto> list = analysisMapper.getZdqk(zdFxQuery);
        List<ZdFxTreeVo> nodes = calcAllNode(list);
        ArrayList<List<String>> data = new ArrayList<>();

        nodes.stream().forEach(node->{
            ArrayList<String> singleData = new ArrayList<>();
            singleData.add(node.getName() +"");
            singleData.add(node.getCbrcount() + "");
            singleData.add(node.getBjcount() + "");
            singleData.add(node.getPcrcount() +"");
            singleData.add(node.getPccount() + "");
            singleData.add(node.getPcl() + "");
            singleData.add(node.getCbrbpcl() + "");
            singleData.add(node.getPcrpcl() + "");

            data.add(singleData);
        });

        String fileName = "重点案件总体情况分析";

        List<String> header = Arrays.asList("筛选规则", "1办案人数", "2办结案件数", "3评查人员数", "4评查案件数", "评查比例（4/2）", "承办人平均被评查案件数（4/1）件", "评查员平均评查案件数（4/3）件");

        ExcelVo excelVo = new ExcelVo();
        excelVo.setFileName(fileName);
        excelVo.setHeader(header);
        excelVo.setData(data);

        return  excelUtils.exportExcelData(excelVo);
    }
}

