package com.start.boot.service.impl;


import com.github.pagehelper.PageHelper;
import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.AnalysisMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.AjqkzlflTreeVo;
import com.start.boot.pojo.vo.AjqkzlflVo;
import com.start.boot.pojo.vo.ErrorAndFlawTreeVo;
import com.start.boot.service.AnalysisService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 湖北专题报告分析：统计表及报告
 */
@Service
@Transactional
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<ErrorAndFlawTreeVo> loadDateData(Map seach) throws Exception {
        seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
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
                    if (map.get("ID").equals(m.get("ID"))) {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                        if (m.get("DM").equals("20001")) {//证据采信
                            map.put("zjcxHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("zjcxHj") + ""));
                            map.put("zjcxBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("zjcxHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            map.put("ssrdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("ssrdHj") + ""));
                            map.put("ssrdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("ssrdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            map.put("flsyHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flsyHj") + ""));
                            map.put("flsyBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("flsyHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            map.put("bacxHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("bacxHj") + ""));
                            map.put("bacxBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("bacxHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            map.put("flwsHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flwsHj") + ""));
                            map.put("flwsBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("flwsHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            map.put("sfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("sfHj") + ""));
                            map.put("sfBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("sfHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            map.put("xtgfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("xtgfHj") + ""));
                            map.put("xtgfBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("xtgfHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            map.put("qtHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("qtHj") + ""));
                            map.put("qtBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("qtHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            map.put("zcjdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("zcjdHj") + ""));
                            map.put("zcjdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("zcjdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10003")) {//出席二审法庭
                            map.put("cxesgHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxesgHj") + ""));
                            map.put("cxesgBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("cxesgHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            map.put("tbHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("tbHj") + ""));
                            map.put("tbBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("tbHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10017")) {//出席二审法庭
                            map.put("cxeswHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxeswHj") + ""));
                            map.put("cxeswBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("cxeswHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            map.put("fljdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("fljdHj") + ""));
                            map.put("fljdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("fljdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
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
                            map.put("zjcxBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("zjcxHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            map.put("ssrdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("ssrdHj") + ""));
                            map.put("ssrdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("ssrdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            map.put("flsyHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flsyHj") + ""));
                            map.put("flsyBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("flsyHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            map.put("bacxHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("bacxHj") + ""));
                            map.put("bacxBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("bacxHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            map.put("flwsHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("flwsHj") + ""));
                            map.put("flwsBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("flwsHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            map.put("sfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("sfHj") + ""));
                            map.put("sfBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("sfHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            map.put("xtgfHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("xtgfHj") + ""));
                            map.put("xtgfBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("xtgfHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            map.put("qtHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("qtHj") + ""));
                            map.put("qtBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("qtHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            map.put("zcjdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("zcjdHj") + ""));
                            map.put("zcjdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("zcjdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10003")) {//出席二审法庭
                            map.put("cxesgHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxesgHj") + ""));
                            map.put("cxesgBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("cxesgHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            map.put("tbHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("tbHj") + ""));
                            map.put("tbBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("tbHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10017")) {//出席二审法庭
                            map.put("cxeswHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("cxeswHj") + ""));
                            map.put("cxeswBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("cxeswHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            map.put("fljdHj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf(map.get("fljdHj") + ""));
                            map.put("fljdBl", decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf(map.get("fljdHj") + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
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
            String[] wcrqnfs = params.getWcrqnf().split(",");
            for (int i = 0; i < wcrqnfs.length; i++) {
                nf = wcrqnfs[i];
                map.put("wcrqnf", nf);
                ajqkzlflVo = new AjqkzlflVo();
                bjajs = analysisMapper.getNdBjAjCount(map);
                pcajs = analysisMapper.getNdPcAjCount(map);
                ajqkzlflVo.setName(nf);
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
        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
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
        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
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
        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
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
        returnList.addAll(list);
        return returnList;
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
        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
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
        list.get(0).put("bjrNum", count.get("BJR")+"");
        list.get(0).put("pcrNum", count.get("PCR")+"");
        Double pcajNum = Double.valueOf( list.get(0).get("pcajNum") + "");
        Double bjajNum = Double.valueOf( list.get(0).get("bjajNum") + "");
        Double bjrNum = Double.valueOf( list.get(0).get("bjrNum") + "");
        Double pcrNum = Double.valueOf( list.get(0).get("pcrNum") + "");
        if (pcajNum == 0) {
            list.get(0).put("pcbl", "0.00%");
            list.get(0).put("bpcAvgNum", "0.00");
            list.get(0).put("avgNum", "0.00");
        } else {
            list.get(0).put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
            list.get(0).put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
            list.get(0).put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
        }
        for(int i=1;i<list.size();i++){
            Map m=list.get(i);
            if("Y".equals(m.get("sfhj"))){
                map.put("ywtx",(m.get("YWTX") + "").split(","));
                Map counts=analysisMapper.loadGeneralPersonalNum(map);
                m.put("bjrNum", counts.get("BJR")+"");
                m.put("pcrNum", counts.get("PCR")+"");
                pcajNum = Double.valueOf( m.get("pcajNum") + "");
                bjajNum = Double.valueOf( m.get("bjajNum") + "");
                bjrNum = Double.valueOf( m.get("bjrNum") + "");
                pcrNum = Double.valueOf( m.get("pcrNum") + "");
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
                map.put("wcrqnf", params.getWcrqnf());
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
        map.put("wcrqnf", params.getWcrqnf());
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
            throw e;
        }
        return list;
    }

    @Override
    public List<ErrorAndFlawTreeVo> loadTxData(Map seach) throws Exception {
        seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
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
        seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
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
        returnList.addAll(list);
        return returnList;
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
        num.setCws(errorCount + num.getCws());
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
        m.put("zjcxBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("zjcxHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("ssrdBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("ssrdHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("flsyBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("flsyHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("bacxBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("bacxHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("flwsBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("flwsHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("sfBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("sfHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("xtgfBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("xtgfHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("qtBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("qtHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("zcjdBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("zcjdHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("cxesgBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("cxesgHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("tbBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("tbHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("cxeswBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("cxeswHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
        m.put("fljdBl", decimalFormat.format(Double.valueOf(m.get("errorAllCount") + "") == 0 ? 0 : (Double.valueOf(m.get("fljdHj") + "") / Double.valueOf(m.get("errorAllCount") + "") * 100)) + "%");
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
        map.put("wcrqnf", params.getWcrqnf());
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
        seach.put("date", "".equals(seach.get("date")) ? "" : (seach.get("date") + "").split(","));
        seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
        seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
        seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
        List<Map> pxc = analysisMapper.getPcxLists(seach);
        if ((seach.get("type") + "").equals("0")) {
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadDatePcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadDateErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadDateErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadDatePcFlCountExcel(seach);
            putMatchPcCountExcelDate(pcCountList, errPcCountList, errCountList, fl, pxc);
            getMatchPcxDataExcelDate(pcCountList, pcFlList, pxc);
            sumPcxDatasExcel(pcCountList, seach.get("flxtdm") + "");
            List<List<String>> data = resultExcelList(pcCountList, seach.get("flxtdm") + "");
            return data;
        } else if ((seach.get("type") + "").equals("1")) {
            List<Map> dwbms = analysisMapper.getDwbmLists(seach);
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadDqPcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadDqErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadDqErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadDqPcFlCountExcel(seach);
            putMatchPcCountExcelDate(pcCountList, errPcCountList, errCountList, fl, pxc);
            getMatchPcxDataExcelDate(pcCountList, pcFlList, pxc);
            List<Map> newList = sumPcxDataDqExcel(pcCountList, dwbms, seach.get("flxtdm") + "");
            List<List<String>> data = resultExcelList(newList, seach.get("flxtdm") + "");
            return data;
        } else {
            List<Map> fl = analysisMapper.loadDateFl();
            List<Map> pcCountList = analysisMapper.loadTxPcCount(seach);
            List<Map> errPcCountList = analysisMapper.loadTxErrPcCount(seach);
            List<Map> errCountList = analysisMapper.loadTxErrCount(seach);
            List<Map> pcFlList = analysisMapper.loadTxPcFlCountExcel(seach);
            putMatchPcCountExcel(pcCountList, errPcCountList, errCountList, fl, pxc);
            getMatchPcxDataExcel(pcCountList, pcFlList, pxc);
            List<Map> list = new ArrayList<>();
            sumPcxDataExcel(pcCountList, list, seach.get("flxtdm") + "");
            List<Map> newList = dataMatchExcel(list, pcCountList);
            List<List<String>> data = resultExcelList(newList, seach.get("flxtdm") + "");
            return data;
        }
        //return null;
    }

    @Override
    public List<List<String>> excel_export_dataPc(Map seach) throws Exception {
        if ((seach.get("type") + "").equals("0")) {
            List<Map> list = loadDateGeneral(seach);
            List<List<String>> data = resultExcelListZt(list);
            return data;
        } else if ((seach.get("type") + "").equals("1")) {
            seach.put("wcrqnf", "".equals(seach.get("wcrqnf")) ? "" : (seach.get("wcrqnf") + "").split(","));
            seach.put("dwbm", "".equals(seach.get("dwbm")) ? "" : (seach.get("dwbm") + "").split(","));
            seach.put("pcflbm", "".equals(seach.get("pcflbm")) ? "" : (seach.get("pcflbm") + "").split(","));
            seach.put("ywtx", "".equals(seach.get("ywtx")) ? "" : (seach.get("ywtx") + "").split(","));
            List<Map> ba = analysisMapper.loadDqGeneralBa(seach);
            List<Map> bj = analysisMapper.loadDqGeneralBj(seach);
            List<Map> pc = analysisMapper.loadDqGeneralPc(seach);
            List<Map> pca = analysisMapper.loadDqGeneralPca(seach);
            getMatchList(bj, ba, pc, pca);
            List<Map> dwbms = analysisMapper.getDwbmLists(seach);
            List<Map> list = sumDqGeneralExcelData(bj, dwbms);
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
            seach.put("wcrqnf", "".equals(seach.get("wcrqnf")) ? "" : (seach.get("wcrqnf") + "").split(","));
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
            list.get(0).put("bjrNum", count.get("BJR")+"");
            list.get(0).put("pcrNum", count.get("PCR")+"");
            Double pcajNum = Double.valueOf( list.get(0).get("pcajNum") + "");
            Double bjajNum = Double.valueOf( list.get(0).get("bjajNum") + "");
            Double bjrNum = Double.valueOf( list.get(0).get("bjrNum") + "");
            Double pcrNum = Double.valueOf( list.get(0).get("pcrNum") + "");
            if (pcajNum == 0) {
                list.get(0).put("pcbl", "0.00%");
                list.get(0).put("bpcAvgNum", "0.00");
                list.get(0).put("avgNum", "0.00");
            } else {
                list.get(0).put("pcbl", decimalFormat.format((pcajNum / bjajNum) * 100) + "%");
                list.get(0).put("bpcAvgNum", decimalFormat.format((pcajNum / bjrNum)));
                list.get(0).put("avgNum", decimalFormat.format((pcajNum / pcrNum)));
            }
            for(int i=1;i<list.size();i++){
                Map m=list.get(i);
                if("Y".equals(m.get("sfhj"))){
                    seach.put("ywtx",(m.get("YWTX") + "").split(","));
                    Map counts=analysisMapper.loadGeneralPersonalNum(seach);
                    m.put("bjrNum", counts.get("BJR")+"");
                    m.put("pcrNum", counts.get("PCR")+"");
                    pcajNum = Double.valueOf( m.get("pcajNum") + "");
                    bjajNum = Double.valueOf( m.get("bjajNum") + "");
                    bjrNum = Double.valueOf( m.get("bjrNum") + "");
                    pcrNum = Double.valueOf( m.get("pcrNum") + "");
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
        }
        //return null;
    }

    private void putMatchPcCountExcelDate(List<Map> pcCountList, List<Map> errPcCountList, List<Map> errCountList, List<Map> fl, List<Map> pcx) {
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
            count = 0;
            if (errCountList.size() == 0) {
                map.put("errorCount", "0");
            } else {
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
            putMatchPcxFlNameExcel(map, fl, pcx);
        }
    }

    private void putMatchPcCountExcel(List<Map> pcCountList, List<Map> errPcCountList, List<Map> errCountList, List<Map> fl, List<Map> pcx) {
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
            count = 0;
            if (errCountList.size() == 0) {
                map.put("errorCount", "0");
            } else {
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
            putMatchPcxFlNameExcel(map, fl, pcx);
        }
    }

    private void putMatchPcxFlNameExcel(Map m, List<Map> fl, List<Map> pcx) {
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
            {
                name = "fljd";
            }
            m.put(name + "Hj", "0/0.00%");
            for (int a = 0; a < pcx.size(); a++) {
                if (!map.get("DM").equals("20006")) {
                    Map pcxMap = pcx.get(a);
                    if (pcxMap.get("DM").equals(map.get("DM"))) {
                        m.put(name + pcxMap.get("XTDM"), "0/0.00%");
                    }
                } else {
                    Map pcxMap = pcx.get(a);
                    if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10017")) {
                        m.put(name + pcxMap.get("XTDM"), "0/0.00%");
                    } else if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10003")) {
                        m.put(name + pcxMap.get("XTDM"), "0/0.00%");
                    }
                }
            }

        }
    }

    private void getMatchPcxDataExcel(List<Map> pc, List<Map> pcFlList, List<Map> pcx) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < pc.size(); i++) {
            Map map = pc.get(i);
            if (pcFlList.size() > 0) {
                for (int a = 0; a < pcFlList.size(); a++) {
                    Map m = pcFlList.get(a);
                    String name = "";
                    if (map.get("ID").equals(m.get("ID")) && map.get("PID").equals(m.get("PID"))) {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                        if (m.get("DM").equals("20001")) {//证据采信
                            name = "zjcx";
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            name = "ssrd";
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            name = "flsy";
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            name = "bacx";
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            name = "flws";
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            name = "sf";
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            name = "xtgf";
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            name = "qt";
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            name = "zcjd";
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10003")) {//出席二审法庭
                            name = "cxesg";
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            name = "tb";
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10017")) {//出席二审法庭
                            name = "cxesw";
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            name = "fljd";
                        }
                        if ("".equals(name))
                            continue;
                        map.put(name + m.get("XTDM"), Integer.valueOf(m.get("NUM") + "") + Integer.valueOf((map.get(name + m.get("XTDM")) + "").split("/")[0] + "") + "/"
                                + decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf((map.get(name + m.get("XTDM")) + "").split("/")[0] + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        map.put(name + "Hj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf((map.get(name + "Hj") + "").split("/")[0] + "") + "/"
                                + decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf((map.get(name + "Hj") + "").split("/")[0] + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                    } else {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                    }
                }
            } else {
                String name = "";
                if (map.get("DM").equals("20001")) {//证据采信
                    name = "zjcx";
                } else if (map.get("DM").equals("20002")) { //事实认定
                    name = "ssrd";
                } else if (map.get("DM").equals("20003")) {//法律适用
                    name = "flsy";
                } else if (map.get("DM").equals("20004")) {//办案程序
                    name = "bacx";
                } else if (map.get("DM").equals("20008")) { //法律文书
                    name = "flws";
                } else if (map.get("DM").equals("20010")) {//司法责任制落实
                    name = "sf";
                } else if (map.get("DM").equals("20011")) {//系统规范应用
                    name = "xtgf";
                } else if (map.get("DM").equals("20020")) {//其他情况
                    name = "qt";
                } else if (map.get("DM").equals("20009")) {//侦查监督
                    name = "zcjd";
                } else if (map.get("DM").equals("20006") && map.get("ZYYWTX").equals("10003")) {//出席二审法庭
                    name = "cxesg";
                } else if (map.get("DM").equals("20005")) { //特别程序
                    name = "tb";
                } else if (map.get("DM").equals("20006") && map.get("ZYYWTX").equals("10017")) {//出席二审法庭
                    name = "cxesw";
                } else if (map.get("DM").equals("20007")) { //法律监督
                    name = "fljd";
                }
                map.put(name + "Hj", "0/0.00%");
                for (int a = 0; a < pcx.size(); a++) {
                    if (!map.get("DM").equals("20006")) {
                        Map pcxMap = pcx.get(a);
                        if (pcxMap.get("DM").equals(map.get("DM"))) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        }
                    } else {
                        Map pcxMap = pcx.get(a);
                        if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10017")) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        } else if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10003")) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        }
                    }
                }
                map.put("id", map.get("ID"));
                map.put("pid", map.get("PID"));
                map.put("name", map.get("NAME"));
            }
        }
    }

    private void sumPcxDataExcel(List<Map> lists, List<Map> list, String flxtdm) {
        if (list.size() == 0) {
            Map m = lists.get(0);
            Map inMap = new HashMap<>();
            inMap.put("id", m.get("pid"));
            inMap.put("pid", "-1");
            inMap.put("name", m.get("MC"));
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
                        inMap.put("name", map.get("MC"));
                        list.add(inMap);
                        continue a;
                    }
                    count++;
                }
            }
        }
        sumPcxDataAllExcel(list, lists, "0", flxtdm);
        sumPcxDatasExcel(list, flxtdm);
    }

    private void getMatchPcxDataExcelDate(List<Map> pc, List<Map> pcFlList, List<Map> pcx) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < pc.size(); i++) {
            Map map = pc.get(i);
            if (pcFlList.size() > 0) {
                for (int a = 0; a < pcFlList.size(); a++) {
                    Map m = pcFlList.get(a);
                    String name = "";
                    if (map.get("ID").equals(m.get("ID"))) {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                        if (m.get("DM").equals("20001")) {//证据采信
                            name = "zjcx";
                        } else if (m.get("DM").equals("20002")) { //事实认定
                            name = "ssrd";
                        } else if (m.get("DM").equals("20003")) {//法律适用
                            name = "flsy";
                        } else if (m.get("DM").equals("20004")) {//办案程序
                            name = "bacx";
                        } else if (m.get("DM").equals("20008")) { //法律文书
                            name = "flws";
                        } else if (m.get("DM").equals("20010")) {//司法责任制落实
                            name = "sf";
                        } else if (m.get("DM").equals("20011")) {//系统规范应用
                            name = "xtgf";
                        } else if (m.get("DM").equals("20020")) {//其他情况
                            name = "qt";
                        } else if (m.get("DM").equals("20009")) {//侦查监督
                            name = "zcjd";
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10003")) {//出席二审法庭
                            name = "cxesg";
                        } else if (m.get("DM").equals("20005")) { //特别程序
                            name = "tb";
                        } else if (m.get("DM").equals("20006") && m.get("ZYYWTX").equals("10017")) {//出席二审法庭
                            name = "cxesw";
                        } else if (m.get("DM").equals("20007")) { //法律监督
                            name = "fljd";
                        }
                        if ("".equals(name))
                            continue;
                        map.put(name + m.get("XTDM"), Integer.valueOf(m.get("NUM") + "") + Integer.valueOf((map.get(name + m.get("XTDM")) + "").split("/")[0] + "") + "/"
                                + decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf((map.get(name + m.get("XTDM")) + "").split("/")[0] + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                        map.put(name + "Hj", Integer.valueOf(m.get("NUM") + "") + Integer.valueOf((map.get(name + "Hj") + "").split("/")[0] + "") + "/"
                                + decimalFormat.format(map.get("errorAllCount").equals("0") ? 0 : (Double.valueOf((map.get(name + "Hj") + "").split("/")[0] + "") / Double.valueOf(map.get("errorAllCount") + "") * 100)) + "%");
                    } else {
                        map.put("id", map.get("ID"));
                        map.put("pid", map.get("PID"));
                        map.put("name", map.get("NAME"));
                    }
                }
            } else {
                String name = "";
                if (map.get("DM").equals("20001")) {//证据采信
                    name = "zjcx";
                } else if (map.get("DM").equals("20002")) { //事实认定
                    name = "ssrd";
                } else if (map.get("DM").equals("20003")) {//法律适用
                    name = "flsy";
                } else if (map.get("DM").equals("20004")) {//办案程序
                    name = "bacx";
                } else if (map.get("DM").equals("20008")) { //法律文书
                    name = "flws";
                } else if (map.get("DM").equals("20010")) {//司法责任制落实
                    name = "sf";
                } else if (map.get("DM").equals("20011")) {//系统规范应用
                    name = "xtgf";
                } else if (map.get("DM").equals("20020")) {//其他情况
                    name = "qt";
                } else if (map.get("DM").equals("20009")) {//侦查监督
                    name = "zcjd";
                } else if (map.get("DM").equals("20006") && map.get("ZYYWTX").equals("10003")) {//出席二审法庭
                    name = "cxesg";
                } else if (map.get("DM").equals("20005")) { //特别程序
                    name = "tb";
                } else if (map.get("DM").equals("20006") && map.get("ZYYWTX").equals("10017")) {//出席二审法庭
                    name = "cxesw";
                } else if (map.get("DM").equals("20007")) { //法律监督
                    name = "fljd";
                }
                map.put(name + "Hj", "0/0.00%");
                for (int a = 0; a < pcx.size(); a++) {
                    if (!map.get("DM").equals("20006")) {
                        Map pcxMap = pcx.get(a);
                        if (pcxMap.get("DM").equals(map.get("DM"))) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        }
                    } else {
                        Map pcxMap = pcx.get(a);
                        if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10017")) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        } else if (pcxMap.get("DM").equals(map.get("DM")) && pcxMap.get("YWTX").equals("10003")) {
                            map.put(name + pcxMap.get("XTDM"), "0/0.00%");
                        }
                    }
                }
                map.put("id", map.get("ID"));
                map.put("pid", map.get("PID"));
                map.put("name", map.get("NAME"));
            }
        }
    }

    private List<Map> sumPcxDataDqExcel(List<Map> lists, List<Map> list, String flxtdm) {
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
                    Iterator entries = map.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = String.valueOf(entry.getKey());
                        String value = String.valueOf(entry.getValue());
                        m.put(key, value);
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    Double pcAllCount = Double.valueOf(m.get("PCALLCOUNT") + "");
                    Double errorAllCount = Double.valueOf(m.get("errorAllCount") + "");
                    Double errorCount = Double.valueOf(m.get("errorCount") + "");
                    Double errorBl = pcAllCount == 0 ? 0 : (errorAllCount / pcAllCount) * 100;
                    Double errorAvgCount = pcAllCount == 0 ? 0 : (errorCount / pcAllCount) * 100;
                    Double errorAvgNum = errorAllCount == 0 ? 0 : (errorCount / errorAllCount);
                    m.put("errorBl", decimalFormat.format(errorBl) + "%");
                    m.put("errorAvgCount", decimalFormat.format(errorAvgCount) + "%");
                    m.put("errorAvgNum", decimalFormat.format(errorAvgNum));
                    m.put("id", m.get("ID"));
                    m.put("pid", m.get("PID"));
                    m.put("name", m.get("DWMC"));
                } else {
                    if (count == lists.size() - 1) {
                        Map maps = lists.get(0);
                        Iterator entries = maps.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry) entries.next();
                            String key = String.valueOf(entry.getKey());
                            String value = String.valueOf(entry.getValue());
                            m.put(key, "0/0.00%");
                        }
                        m.put("PCALLCOUNT", "0");
                        m.put("errorAllCount", "0");
                        m.put("errorCount", "0");
                        m.put("errorBl", "0.00%");
                        m.put("errorAvgCount",  "0.00%");
                        m.put("errorAvgNum","0");
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
        //sumPcxDataAllExcel(list, list, "0", flxtdm);
        sumPcxDataAllExcel(returnList, list, "0", flxtdm);
        sumPcxDataAllExcel(returnList, returnList, "1", flxtdm);
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
     public void assignmentToExcel(ErrorAndFlawTreeVo num, Map map, String flxtdm) {
         DecimalFormat decimalFormat = new DecimalFormat("0.00");
         Double pcAllCount = Double.valueOf(map.get("PCALLCOUNT") + "");
         Double errorAllCount = Double.valueOf(map.get("errorAllCount") + "");
         Double errorCount = Double.valueOf(map.get("errorCount") + "");
         num.setPcAllnum(Double.valueOf(map.get("PCALLCOUNT") + "")+num.getPcAllnum());
         num.setErrorAllNum(Double.valueOf(map.get("errorAllCount") + "")+num.getErrorAllNum());
         num.setErrorNum(Double.valueOf(map.get("errorCount") + "")+num.getErrorNum());
         num.setZjcxHj(Integer.valueOf((map.get("zjcxHj") + "").split("/")[0])+num.getZjcxHj());
         num.setSsrdHj(Integer.valueOf((map.get("ssrdHj") + "").split("/")[0])+num.getSsrdHj());
         num.setFlsyHj (Integer.valueOf((map.get("flsyHj") + "").split("/")[0])+num.getFlsyHj());
         num.setBacxHj(Integer.valueOf((map.get("bacxHj") + "").split("/")[0])+num.getBacxHj());
         num.setFlwsHj(Integer.valueOf((map.get("flwsHj") + "").split("/")[0])+num.getFlwsHj());
         num.setSfHj(Integer.valueOf((map.get("sfHj") + "").split("/")[0])+num.getSfHj());
         num.setXtgfHj(Integer.valueOf((map.get("xtgfHj") + "").split("/")[0])+num.getXtgfHj());
         num.setQtHj(Integer.valueOf((map.get("qtHj") + "").split("/")[0])+num.getQtHj());
         num.setZcjdHj(Integer.valueOf((map.get("zcjdHj") + "").split("/")[0])+num.getZcjdHj());
         num.setCxesgHj(Integer.valueOf((map.get("cxesgHj") + "").split("/")[0])+num.getCxesgHj());
         num.setTbHj(Integer.valueOf((map.get("tbHj") + "").split("/")[0])+num.getTbHj());
         num.setCxeswHj(Integer.valueOf((map.get("cxeswHj") + "").split("/")[0])+num.getCxeswHj());
         num.setFljdHj(Integer.valueOf((map.get("fljdHj") + "").split("/")[0])+num.getFljdHj());
         if (flxtdm.equals("30002")) {
             num.setZjcx40000(Integer.valueOf((map.get("zjcx40000") + "").split("/")[0])+num.getZjcx40000());
             num.setZjcx40007(Integer.valueOf((map.get("zjcx40007") + "").split("/")[0])+num.getZjcx40007());
             num.setZjcx40010(Integer.valueOf((map.get("zjcx40010") + "").split("/")[0])+num.getZjcx40010());
             num.setZjcx40066(Integer.valueOf((map.get("zjcx40066") + "").split("/")[0])+num.getZjcx40066());
             num.setZjcx40067(Integer.valueOf((map.get("zjcx40067") + "").split("/")[0])+num.getZjcx40067());
             num.setZjcx40068(Integer.valueOf((map.get("zjcx40068") + "").split("/")[0])+num.getZjcx40068());
             num.setZjcx40069(Integer.valueOf((map.get("zjcx40069") + "").split("/")[0])+num.getZjcx40069());
             num.setZjcx40071(Integer.valueOf((map.get("zjcx40071") + "").split("/")[0])+num.getZjcx40071());
             num.setZjcx40074(Integer.valueOf((map.get("zjcx40074") + "").split("/")[0])+num.getZjcx40074());
             num.setZjcx40081(Integer.valueOf((map.get("zjcx40081") + "").split("/")[0])+num.getZjcx40081());
             num.setZjcx40136(Integer.valueOf((map.get("zjcx40136") + "").split("/")[0])+num.getZjcx40136());
             num.setZjcx40137(Integer.valueOf((map.get("zjcx40137") + "").split("/")[0])+num.getZjcx40137());
             num.setZjcx40138(Integer.valueOf((map.get("zjcx40138") + "").split("/")[0])+num.getZjcx40138());
             num.setZjcx40139(Integer.valueOf((map.get("zjcx40139") + "").split("/")[0])+num.getZjcx40139());
             num.setZjcx40140(Integer.valueOf((map.get("zjcx40140") + "").split("/")[0])+num.getZjcx40140());
             num.setZjcx40141(Integer.valueOf((map.get("zjcx40141") + "").split("/")[0])+num.getZjcx40141());
             num.setZjcx40214(Integer.valueOf((map.get("zjcx40214") + "").split("/")[0])+num.getZjcx40214());
             num.setZjcx40215(Integer.valueOf((map.get("zjcx40215") + "").split("/")[0])+num.getZjcx40215());
             num.setZjcx40216(Integer.valueOf((map.get("zjcx40216") + "").split("/")[0])+num.getZjcx40216());
             num.setZjcx40229(Integer.valueOf((map.get("zjcx40229") + "").split("/")[0])+num.getZjcx40229());
             num.setZjcx40289(Integer.valueOf((map.get("zjcx40289") + "").split("/")[0])+num.getZjcx40289());
             num.setZjcx40290(Integer.valueOf((map.get("zjcx40290") + "").split("/")[0])+num.getZjcx40290());
             num.setZjcx40291(Integer.valueOf((map.get("zjcx40291") + "").split("/")[0])+num.getZjcx40291());
             num.setSsrd40000(Integer.valueOf((map.get("ssrd40000") + "").split("/")[0])+ num.getSsrd40000());
             num.setSsrd40005(Integer.valueOf((map.get("ssrd40005") + "").split("/")[0])+ num.getSsrd40005());
             num.setSsrd40025(Integer.valueOf((map.get("ssrd40025") + "").split("/")[0])+ num.getSsrd40025());
             num.setSsrd40026(Integer.valueOf((map.get("ssrd40026") + "").split("/")[0])+ num.getSsrd40026());
             num.setSsrd40029(Integer.valueOf((map.get("ssrd40029") + "").split("/")[0])+ num.getSsrd40029());
             num.setSsrd40030(Integer.valueOf((map.get("ssrd40030") + "").split("/")[0])+ num.getSsrd40030());
             num.setSsrd40031(Integer.valueOf((map.get("ssrd40031") + "").split("/")[0])+ num.getSsrd40031());
             num.setSsrd40032(Integer.valueOf((map.get("ssrd40032") + "").split("/")[0])+ num.getSsrd40032());
             num.setSsrd40033(Integer.valueOf((map.get("ssrd40033") + "").split("/")[0])+ num.getSsrd40033());
             num.setSsrd40075(Integer.valueOf((map.get("ssrd40075") + "").split("/")[0])+ num.getSsrd40075());
             num.setSsrd40076(Integer.valueOf((map.get("ssrd40076") + "").split("/")[0])+ num.getSsrd40076());
             num.setSsrd40088(Integer.valueOf((map.get("ssrd40088") + "").split("/")[0])+ num.getSsrd40088());
             num.setSsrd40089(Integer.valueOf((map.get("ssrd40089") + "").split("/")[0])+ num.getSsrd40089());
             num.setSsrd40090(Integer.valueOf((map.get("ssrd40090") + "").split("/")[0])+ num.getSsrd40090());
             num.setSsrd40131(Integer.valueOf((map.get("ssrd40131") + "").split("/")[0])+ num.getSsrd40131());
             num.setSsrd40170(Integer.valueOf((map.get("ssrd40170") + "").split("/")[0])+ num.getSsrd40170());
             num.setSsrd40207(Integer.valueOf((map.get("ssrd40207") + "").split("/")[0])+ num.getSsrd40207());
             num.setSsrd40210(Integer.valueOf((map.get("ssrd40210") + "").split("/")[0])+ num.getSsrd40210());
             num.setSsrd40224(Integer.valueOf((map.get("ssrd40224") + "").split("/")[0])+ num.getSsrd40224());
             num.setSsrd40227(Integer.valueOf((map.get("ssrd40227") + "").split("/")[0])+ num.getSsrd40227());
             num.setSsrd40274(Integer.valueOf((map.get("ssrd40274") + "").split("/")[0])+ num.getSsrd40274());
             num.setSsrd40276(Integer.valueOf((map.get("ssrd40276") + "").split("/")[0])+ num.getSsrd40276());
             num.setSsrd40277(Integer.valueOf((map.get("ssrd40277") + "").split("/")[0])+ num.getSsrd40277());
             num.setSsrd40278(Integer.valueOf((map.get("ssrd40278") + "").split("/")[0])+ num.getSsrd40278());
             num.setSsrd40280(Integer.valueOf((map.get("ssrd40280") + "").split("/")[0])+ num.getSsrd40280());
             num.setFlsy40000(Integer.valueOf((map.get("flsy40000") + "").split("/")[0])+num.getFlsy40000());
             num.setFlsy40208(Integer.valueOf((map.get("flsy40208") + "").split("/")[0])+num.getFlsy40208());
             num.setFlsy40258(Integer.valueOf((map.get("flsy40258") + "").split("/")[0])+num.getFlsy40258());
             num.setFlsy40259(Integer.valueOf((map.get("flsy40259") + "").split("/")[0])+num.getFlsy40259());
             num.setFlsy40260(Integer.valueOf((map.get("flsy40260") + "").split("/")[0])+num.getFlsy40260());
             num.setFlsy40261(Integer.valueOf((map.get("flsy40261") + "").split("/")[0])+num.getFlsy40261());
             num.setFlsy40262(Integer.valueOf((map.get("flsy40262") + "").split("/")[0])+num.getFlsy40262());
             num.setFlsy40263(Integer.valueOf((map.get("flsy40263") + "").split("/")[0])+num.getFlsy40263());
             num.setFlsy40264(Integer.valueOf((map.get("flsy40264") + "").split("/")[0])+num.getFlsy40264());
             num.setFlsy40265(Integer.valueOf((map.get("flsy40265") + "").split("/")[0])+num.getFlsy40265());
             num.setFlsy40266(Integer.valueOf((map.get("flsy40266") + "").split("/")[0])+num.getFlsy40266());
             num.setBacx40000(Integer.valueOf((map.get("bacx40000") + "").split("/")[0])+num.getBacx40000());
             num.setBacx40001(Integer.valueOf((map.get("bacx40001") + "").split("/")[0])+num.getBacx40001());
             num.setBacx40008(Integer.valueOf((map.get("bacx40008") + "").split("/")[0])+num.getBacx40008());
             num.setBacx40042(Integer.valueOf((map.get("bacx40042") + "").split("/")[0])+num.getBacx40042());
             num.setBacx40059(Integer.valueOf((map.get("bacx40059") + "").split("/")[0])+num.getBacx40059());
             num.setBacx40062(Integer.valueOf((map.get("bacx40062") + "").split("/")[0])+num.getBacx40062());
             num.setBacx40070(Integer.valueOf((map.get("bacx40070") + "").split("/")[0])+num.getBacx40070());
             num.setBacx40082(Integer.valueOf((map.get("bacx40082") + "").split("/")[0])+num.getBacx40082());
             num.setBacx40102(Integer.valueOf((map.get("bacx40102") + "").split("/")[0])+num.getBacx40102());
             num.setBacx40103(Integer.valueOf((map.get("bacx40103") + "").split("/")[0])+num.getBacx40103());
             num.setBacx40112(Integer.valueOf((map.get("bacx40112") + "").split("/")[0])+num.getBacx40112());
             num.setBacx40118(Integer.valueOf((map.get("bacx40118") + "").split("/")[0])+num.getBacx40118());
             num.setBacx40120(Integer.valueOf((map.get("bacx40120") + "").split("/")[0])+num.getBacx40120());
             num.setBacx40129(Integer.valueOf((map.get("bacx40129") + "").split("/")[0])+num.getBacx40129());
             num.setBacx40135(Integer.valueOf((map.get("bacx40135") + "").split("/")[0])+num.getBacx40135());
             num.setBacx40160(Integer.valueOf((map.get("bacx40160") + "").split("/")[0])+num.getBacx40160());
             num.setBacx40220(Integer.valueOf((map.get("bacx40220") + "").split("/")[0])+num.getBacx40220());
             num.setBacx40231(Integer.valueOf((map.get("bacx40231") + "").split("/")[0])+num.getBacx40231());
             num.setBacx40251(Integer.valueOf((map.get("bacx40251") + "").split("/")[0])+num.getBacx40251());
             num.setBacx40252(Integer.valueOf((map.get("bacx40252") + "").split("/")[0])+num.getBacx40252());
             num.setBacx40253(Integer.valueOf((map.get("bacx40253") + "").split("/")[0])+num.getBacx40253());
             num.setBacx40254(Integer.valueOf((map.get("bacx40254") + "").split("/")[0])+num.getBacx40254());
             num.setBacx40268(Integer.valueOf((map.get("bacx40268") + "").split("/")[0])+num.getBacx40268());
             num.setBacx40272(Integer.valueOf((map.get("bacx40272") + "").split("/")[0])+num.getBacx40272());
             num.setBacx40283(Integer.valueOf((map.get("bacx40283") + "").split("/")[0])+num.getBacx40283());
             num.setFlws40037(Integer.valueOf((map.get("flws40037") + "").split("/")[0])+num.getFlws40037());
             num.setFlws40098(Integer.valueOf((map.get("flws40098") + "").split("/")[0])+num.getFlws40098());
             num.setFlws40155(Integer.valueOf((map.get("flws40155") + "").split("/")[0])+num.getFlws40155());
             num.setSf40000(Integer.valueOf((map.get("sf40000") + "").split("/")[0])+num.getSf40000());
             num.setSf40163(Integer.valueOf((map.get("sf40163") + "").split("/")[0])+num.getSf40163());
             num.setZcjd40056(Integer.valueOf((map.get("zcjd40056") + "").split("/")[0])+num.getZcjd40056());
             num.setZcjd40099(Integer.valueOf((map.get("zcjd40099") + "").split("/")[0])+num.getZcjd40099());
             num.setZcjd40249(Integer.valueOf((map.get("zcjd40249") + "").split("/")[0])+num.getZcjd40249());
             num.setZcjd40255(Integer.valueOf((map.get("zcjd40255") + "").split("/")[0])+num.getZcjd40255());
             num.setTb40180(Integer.valueOf((map.get("tb40180") + "").split("/")[0])+num.getTb40180());
             num.setTb40212(Integer.valueOf((map.get("tb40212") + "").split("/")[0])+num.getTb40212());
             num.setTb40286(Integer.valueOf((map.get("tb40286") + "").split("/")[0])+num.getTb40286());
         } else {
             num.setZjcx40000( Integer.valueOf((map.get("zjcx40000") + "").split("/")[0])+num.getZjcx40000());
             num.setZjcx40072( Integer.valueOf((map.get("zjcx40072") + "").split("/")[0])+num.getZjcx40072());
             num.setZjcx40073( Integer.valueOf((map.get("zjcx40073") + "").split("/")[0])+num.getZjcx40073());
             num.setZjcx40116( Integer.valueOf((map.get("zjcx40116") + "").split("/")[0])+num.getZjcx40116());
             num.setZjcx40213( Integer.valueOf((map.get("zjcx40213") + "").split("/")[0])+num.getZjcx40213());
             num.setZjcx40235( Integer.valueOf((map.get("zjcx40235") + "").split("/")[0])+num.getZjcx40235());
             num.setZjcx40236( Integer.valueOf((map.get("zjcx40236") + "").split("/")[0])+num.getZjcx40236());
             num.setZjcx40237( Integer.valueOf((map.get("zjcx40237") + "").split("/")[0])+num.getZjcx40237());
             num.setZjcx40238( Integer.valueOf((map.get("zjcx40238") + "").split("/")[0])+num.getZjcx40238());
             num.setZjcx40239( Integer.valueOf((map.get("zjcx40239") + "").split("/")[0])+num.getZjcx40239());
             num.setZjcx40240( Integer.valueOf((map.get("zjcx40240") + "").split("/")[0])+num.getZjcx40240());
             num.setZjcx40241( Integer.valueOf((map.get("zjcx40241") + "").split("/")[0])+num.getZjcx40241());
             num.setZjcx40246( Integer.valueOf((map.get("zjcx40246") + "").split("/")[0])+num.getZjcx40246());
             num.setZjcx40288( Integer.valueOf((map.get("zjcx40288") + "").split("/")[0])+num.getZjcx40288());
             num.setZjcx40292( Integer.valueOf((map.get("zjcx40292") + "").split("/")[0])+num.getZjcx40292());
             num.setSsrd40000( Integer.valueOf((map.get("ssrd40000") + "").split("/")[0])+num.getSsrd40000());
             num.setSsrd40024( Integer.valueOf((map.get("ssrd40024") + "").split("/")[0])+num.getSsrd40024());
             num.setSsrd40025( Integer.valueOf((map.get("ssrd40025") + "").split("/")[0])+num.getSsrd40025());
             num.setSsrd40026( Integer.valueOf((map.get("ssrd40026") + "").split("/")[0])+num.getSsrd40026());
             num.setSsrd40027( Integer.valueOf((map.get("ssrd40027") + "").split("/")[0])+num.getSsrd40027());
             num.setSsrd40028( Integer.valueOf((map.get("ssrd40028") + "").split("/")[0])+num.getSsrd40028());
             num.setSsrd40034( Integer.valueOf((map.get("ssrd40034") + "").split("/")[0])+num.getSsrd40034());
             num.setSsrd40035( Integer.valueOf((map.get("ssrd40035") + "").split("/")[0])+num.getSsrd40035());
             num.setSsrd40041( Integer.valueOf((map.get("ssrd40041") + "").split("/")[0])+num.getSsrd40041());
             num.setSsrd40091( Integer.valueOf((map.get("ssrd40091") + "").split("/")[0])+num.getSsrd40091());
             num.setSsrd40096( Integer.valueOf((map.get("ssrd40096") + "").split("/")[0])+num.getSsrd40096());
             num.setSsrd40134( Integer.valueOf((map.get("ssrd40134") + "").split("/")[0])+num.getSsrd40134());
             num.setSsrd40228( Integer.valueOf((map.get("ssrd40228") + "").split("/")[0])+num.getSsrd40228());
             num.setSsrd40275( Integer.valueOf((map.get("ssrd40275") + "").split("/")[0])+num.getSsrd40275());
             num.setSsrd40281( Integer.valueOf((map.get("ssrd40281") + "").split("/")[0])+num.getSsrd40281());
             num.setSsrd40282( Integer.valueOf((map.get("ssrd40282") + "").split("/")[0])+num.getSsrd40282());
             num.setFlsy40000( Integer.valueOf((map.get("flsy40000") + "").split("/")[0])+num.getFlsy40000());
             num.setFlsy40125( Integer.valueOf((map.get("flsy40125") + "").split("/")[0])+num.getFlsy40125());
             num.setFlsy40126( Integer.valueOf((map.get("flsy40126") + "").split("/")[0])+num.getFlsy40126());
             num.setFlsy40127( Integer.valueOf((map.get("flsy40127") + "").split("/")[0])+num.getFlsy40127());
             num.setFlsy40128( Integer.valueOf((map.get("flsy40128") + "").split("/")[0])+num.getFlsy40128());
             num.setFlsy40209( Integer.valueOf((map.get("flsy40209") + "").split("/")[0])+num.getFlsy40209());
             num.setFlsy40267( Integer.valueOf((map.get("flsy40267") + "").split("/")[0])+num.getFlsy40267());
             num.setFlsy40270( Integer.valueOf((map.get("flsy40270") + "").split("/")[0])+num.getFlsy40270());
             num.setFlsy40271( Integer.valueOf((map.get("flsy40271") + "").split("/")[0])+num.getFlsy40271());
             num.setFlsy40284( Integer.valueOf((map.get("flsy40284") + "").split("/")[0])+num.getFlsy40284());
             num.setBacx40000( Integer.valueOf((map.get("bacx40000") + "").split("/")[0])+num.getBacx40000());
             num.setBacx40003( Integer.valueOf((map.get("bacx40003") + "").split("/")[0])+num.getBacx40003());
             num.setBacx40004( Integer.valueOf((map.get("bacx40004") + "").split("/")[0])+num.getBacx40004());
             num.setBacx40006( Integer.valueOf((map.get("bacx40006") + "").split("/")[0])+num.getBacx40006());
             num.setBacx40009( Integer.valueOf((map.get("bacx40009") + "").split("/")[0])+num.getBacx40009());
             num.setBacx40011( Integer.valueOf((map.get("bacx40011") + "").split("/")[0])+num.getBacx40011());
             num.setBacx40012( Integer.valueOf((map.get("bacx40012") + "").split("/")[0])+num.getBacx40012());
             num.setBacx40013( Integer.valueOf((map.get("bacx40013") + "").split("/")[0])+num.getBacx40013());
             num.setBacx40014( Integer.valueOf((map.get("bacx40014") + "").split("/")[0])+num.getBacx40014());
             num.setBacx40015( Integer.valueOf((map.get("bacx40015") + "").split("/")[0])+num.getBacx40015());
             num.setBacx40016( Integer.valueOf((map.get("bacx40016") + "").split("/")[0])+num.getBacx40016());
             num.setBacx40017( Integer.valueOf((map.get("bacx40017") + "").split("/")[0])+num.getBacx40017());
             num.setBacx40019( Integer.valueOf((map.get("bacx40019") + "").split("/")[0])+num.getBacx40019());
             num.setBacx40020( Integer.valueOf((map.get("bacx40020") + "").split("/")[0])+num.getBacx40020());
             num.setBacx40021( Integer.valueOf((map.get("bacx40021") + "").split("/")[0])+num.getBacx40021());
             num.setBacx40022( Integer.valueOf((map.get("bacx40022") + "").split("/")[0])+num.getBacx40022());
             num.setBacx40023( Integer.valueOf((map.get("bacx40023") + "").split("/")[0])+num.getBacx40023());
             num.setBacx40036( Integer.valueOf((map.get("bacx40036") + "").split("/")[0])+num.getBacx40036());
             num.setBacx40045( Integer.valueOf((map.get("bacx40045") + "").split("/")[0])+num.getBacx40045());
             num.setBacx40046( Integer.valueOf((map.get("bacx40046") + "").split("/")[0])+num.getBacx40046());
             num.setBacx40047( Integer.valueOf((map.get("bacx40047") + "").split("/")[0])+num.getBacx40047());
             num.setBacx40051( Integer.valueOf((map.get("bacx40051") + "").split("/")[0])+num.getBacx40051());
             num.setBacx40052( Integer.valueOf((map.get("bacx40052") + "").split("/")[0])+num.getBacx40052());
             num.setBacx40055( Integer.valueOf((map.get("bacx40055") + "").split("/")[0])+num.getBacx40055());
             num.setBacx40058( Integer.valueOf((map.get("bacx40058") + "").split("/")[0])+num.getBacx40058());
             num.setBacx40060( Integer.valueOf((map.get("bacx40060") + "").split("/")[0])+num.getBacx40060());
             num.setBacx40061( Integer.valueOf((map.get("bacx40061") + "").split("/")[0])+num.getBacx40061());
             num.setBacx40064( Integer.valueOf((map.get("bacx40064") + "").split("/")[0])+num.getBacx40064());
             num.setBacx40065( Integer.valueOf((map.get("bacx40065") + "").split("/")[0])+num.getBacx40065());
             num.setBacx40077( Integer.valueOf((map.get("bacx40077") + "").split("/")[0])+num.getBacx40077());
             num.setBacx40079( Integer.valueOf((map.get("bacx40079") + "").split("/")[0])+num.getBacx40079());
             num.setBacx40080( Integer.valueOf((map.get("bacx40080") + "").split("/")[0])+num.getBacx40080());
             num.setBacx40087( Integer.valueOf((map.get("bacx40087") + "").split("/")[0])+num.getBacx40087());
             num.setBacx40093( Integer.valueOf((map.get("bacx40093") + "").split("/")[0])+num.getBacx40093());
             num.setBacx40094( Integer.valueOf((map.get("bacx40094") + "").split("/")[0])+num.getBacx40094());
             num.setBacx40101( Integer.valueOf((map.get("bacx40101") + "").split("/")[0])+num.getBacx40101());
             num.setBacx40104( Integer.valueOf((map.get("bacx40104") + "").split("/")[0])+num.getBacx40104());
             num.setBacx40107( Integer.valueOf((map.get("bacx40107") + "").split("/")[0])+num.getBacx40107());
             num.setBacx40111( Integer.valueOf((map.get("bacx40111") + "").split("/")[0])+num.getBacx40111());
             num.setBacx40114( Integer.valueOf((map.get("bacx40114") + "").split("/")[0])+num.getBacx40114());
             num.setBacx40119( Integer.valueOf((map.get("bacx40119") + "").split("/")[0])+num.getBacx40119());
             num.setBacx40121( Integer.valueOf((map.get("bacx40121") + "").split("/")[0])+num.getBacx40121());
             num.setBacx40122( Integer.valueOf((map.get("bacx40122") + "").split("/")[0])+num.getBacx40122());
             num.setBacx40123( Integer.valueOf((map.get("bacx40123") + "").split("/")[0])+num.getBacx40123());
             num.setBacx40124( Integer.valueOf((map.get("bacx40124") + "").split("/")[0])+num.getBacx40124());
             num.setBacx40130( Integer.valueOf((map.get("bacx40130") + "").split("/")[0])+num.getBacx40130());
             num.setBacx40142( Integer.valueOf((map.get("bacx40142") + "").split("/")[0])+num.getBacx40142());
             num.setBacx40143( Integer.valueOf((map.get("bacx40143") + "").split("/")[0])+num.getBacx40143());
             num.setBacx40144( Integer.valueOf((map.get("bacx40144") + "").split("/")[0])+num.getBacx40144());
             num.setBacx40145( Integer.valueOf((map.get("bacx40145") + "").split("/")[0])+num.getBacx40145());
             num.setBacx40146( Integer.valueOf((map.get("bacx40146") + "").split("/")[0])+num.getBacx40146());
             num.setBacx40147( Integer.valueOf((map.get("bacx40147") + "").split("/")[0])+num.getBacx40147());
             num.setBacx40148( Integer.valueOf((map.get("bacx40148") + "").split("/")[0])+num.getBacx40148());
             num.setBacx40149( Integer.valueOf((map.get("bacx40149") + "").split("/")[0])+num.getBacx40149());
             num.setBacx40150( Integer.valueOf((map.get("bacx40150") + "").split("/")[0])+num.getBacx40150());
             num.setBacx40151( Integer.valueOf((map.get("bacx40151") + "").split("/")[0])+num.getBacx40151());
             num.setBacx40152( Integer.valueOf((map.get("bacx40152") + "").split("/")[0])+num.getBacx40152());
             num.setBacx40153( Integer.valueOf((map.get("bacx40153") + "").split("/")[0])+num.getBacx40153());
             num.setBacx40159( Integer.valueOf((map.get("bacx40159") + "").split("/")[0])+num.getBacx40159());
             num.setBacx40161( Integer.valueOf((map.get("bacx40161") + "").split("/")[0])+num.getBacx40161());
             num.setBacx40164( Integer.valueOf((map.get("bacx40164") + "").split("/")[0])+num.getBacx40164());
             num.setBacx40165( Integer.valueOf((map.get("bacx40165") + "").split("/")[0])+num.getBacx40165());
             num.setBacx40166( Integer.valueOf((map.get("bacx40166") + "").split("/")[0])+num.getBacx40166());
             num.setBacx40167( Integer.valueOf((map.get("bacx40167") + "").split("/")[0])+num.getBacx40167());
             num.setBacx40168( Integer.valueOf((map.get("bacx40168") + "").split("/")[0])+num.getBacx40168());
             num.setBacx40169( Integer.valueOf((map.get("bacx40169") + "").split("/")[0])+num.getBacx40169());
             num.setBacx40171( Integer.valueOf((map.get("bacx40171") + "").split("/")[0])+num.getBacx40171());
             num.setBacx40174( Integer.valueOf((map.get("bacx40174") + "").split("/")[0])+num.getBacx40174());
             num.setBacx40175( Integer.valueOf((map.get("bacx40175") + "").split("/")[0])+num.getBacx40175());
             num.setBacx40176( Integer.valueOf((map.get("bacx40176") + "").split("/")[0])+num.getBacx40176());
             num.setBacx40179( Integer.valueOf((map.get("bacx40179") + "").split("/")[0])+num.getBacx40179());
             num.setBacx40181( Integer.valueOf((map.get("bacx40181") + "").split("/")[0])+num.getBacx40181());
             num.setBacx40182( Integer.valueOf((map.get("bacx40182") + "").split("/")[0])+num.getBacx40182());
             num.setBacx40183( Integer.valueOf((map.get("bacx40183") + "").split("/")[0])+num.getBacx40183());
             num.setBacx40185( Integer.valueOf((map.get("bacx40185") + "").split("/")[0])+num.getBacx40185());
             num.setBacx40188( Integer.valueOf((map.get("bacx40188") + "").split("/")[0])+num.getBacx40188());
             num.setBacx40190( Integer.valueOf((map.get("bacx40190") + "").split("/")[0])+num.getBacx40190());
             num.setBacx40191( Integer.valueOf((map.get("bacx40191") + "").split("/")[0])+num.getBacx40191());
             num.setBacx40192( Integer.valueOf((map.get("bacx40192") + "").split("/")[0])+num.getBacx40192());
             num.setBacx40193( Integer.valueOf((map.get("bacx40193") + "").split("/")[0])+num.getBacx40193());
             num.setBacx40194( Integer.valueOf((map.get("bacx40194") + "").split("/")[0])+num.getBacx40194());
             num.setBacx40195( Integer.valueOf((map.get("bacx40195") + "").split("/")[0])+num.getBacx40195());
             num.setBacx40197( Integer.valueOf((map.get("bacx40197") + "").split("/")[0])+num.getBacx40197());
             num.setBacx40199( Integer.valueOf((map.get("bacx40199") + "").split("/")[0])+num.getBacx40199());
             num.setBacx40204( Integer.valueOf((map.get("bacx40204") + "").split("/")[0])+num.getBacx40204());
             num.setBacx40205( Integer.valueOf((map.get("bacx40205") + "").split("/")[0])+num.getBacx40205());
             num.setBacx40217( Integer.valueOf((map.get("bacx40217") + "").split("/")[0])+num.getBacx40217());
             num.setBacx40218( Integer.valueOf((map.get("bacx40218") + "").split("/")[0])+num.getBacx40218());
             num.setBacx40219( Integer.valueOf((map.get("bacx40219") + "").split("/")[0])+num.getBacx40219());
             num.setBacx40221( Integer.valueOf((map.get("bacx40221") + "").split("/")[0])+num.getBacx40221());
             num.setBacx40222( Integer.valueOf((map.get("bacx40222") + "").split("/")[0])+num.getBacx40222());
             num.setBacx40223( Integer.valueOf((map.get("bacx40223") + "").split("/")[0])+num.getBacx40223());
             num.setBacx40225( Integer.valueOf((map.get("bacx40225") + "").split("/")[0])+num.getBacx40225());
             num.setBacx40226( Integer.valueOf((map.get("bacx40226") + "").split("/")[0])+num.getBacx40226());
             num.setBacx40230( Integer.valueOf((map.get("bacx40230") + "").split("/")[0])+num.getBacx40230());
             num.setBacx40233( Integer.valueOf((map.get("bacx40233") + "").split("/")[0])+num.getBacx40233());
             num.setBacx40234( Integer.valueOf((map.get("bacx40234") + "").split("/")[0])+num.getBacx40234());
             num.setBacx40244( Integer.valueOf((map.get("bacx40244") + "").split("/")[0])+num.getBacx40244());
             num.setBacx40245( Integer.valueOf((map.get("bacx40245") + "").split("/")[0])+num.getBacx40245());
             num.setBacx40247( Integer.valueOf((map.get("bacx40247") + "").split("/")[0])+num.getBacx40247());
             num.setBacx40248( Integer.valueOf((map.get("bacx40248") + "").split("/")[0])+num.getBacx40248());
             num.setBacx40250( Integer.valueOf((map.get("bacx40250") + "").split("/")[0])+num.getBacx40250());
             num.setBacx40256( Integer.valueOf((map.get("bacx40256") + "").split("/")[0])+num.getBacx40256());
             num.setBacx40257( Integer.valueOf((map.get("bacx40257") + "").split("/")[0])+num.getBacx40257());
             num.setBacx40269( Integer.valueOf((map.get("bacx40269") + "").split("/")[0])+num.getBacx40269());
             num.setBacx40273( Integer.valueOf((map.get("bacx40273") + "").split("/")[0])+num.getBacx40273());
             num.setBacx40279( Integer.valueOf((map.get("bacx40279") + "").split("/")[0])+num.getBacx40279());
             num.setBacx40287( Integer.valueOf((map.get("bacx40287") + "").split("/")[0])+num.getBacx40287());
             num.setFlws40000( Integer.valueOf((map.get("flws40000") + "").split("/")[0])+num.getFlws40000());
             num.setFlws40043( Integer.valueOf((map.get("flws40043") + "").split("/")[0])+num.getFlws40043());
             num.setFlws40044( Integer.valueOf((map.get("flws40044") + "").split("/")[0])+num.getFlws40044());
             num.setFlws40156( Integer.valueOf((map.get("flws40156") + "").split("/")[0])+num.getFlws40156());
             num.setFlws40157( Integer.valueOf((map.get("flws40157") + "").split("/")[0])+num.getFlws40157());
             num.setFlws40186( Integer.valueOf((map.get("flws40186") + "").split("/")[0])+num.getFlws40186());
             num.setFlws40189( Integer.valueOf((map.get("flws40189") + "").split("/")[0])+num.getFlws40189());
             num.setFlws40242( Integer.valueOf((map.get("flws40242") + "").split("/")[0])+num.getFlws40242());
             num.setSf40000( Integer.valueOf((map.get("sf40000") + "").split("/")[0])+num.getSf40000());
             num.setSf40038( Integer.valueOf((map.get("sf40038") + "").split("/")[0])+num.getSf40038());
             num.setSf40184( Integer.valueOf((map.get("sf40184") + "").split("/")[0])+num.getSf40184());
             num.setSf40198( Integer.valueOf((map.get("sf40198") + "").split("/")[0])+num.getSf40198());
             num.setXtgf40000( Integer.valueOf((map.get("xtgf40000") + "").split("/")[0])+num.getXtgf40000());
             num.setXtgf40018( Integer.valueOf((map.get("xtgf40018") + "").split("/")[0])+num.getXtgf40018());
             num.setXtgf40097( Integer.valueOf((map.get("xtgf40097") + "").split("/")[0])+num.getXtgf40097());
             num.setXtgf40110( Integer.valueOf((map.get("xtgf40110") + "").split("/")[0])+num.getXtgf40110());
             num.setXtgf40162( Integer.valueOf((map.get("xtgf40162") + "").split("/")[0])+num.getXtgf40162());
             num.setXtgf40177( Integer.valueOf((map.get("xtgf40177") + "").split("/")[0])+num.getXtgf40177());
             num.setXtgf40178( Integer.valueOf((map.get("xtgf40178") + "").split("/")[0])+num.getXtgf40178());
             num.setXtgf40196( Integer.valueOf((map.get("xtgf40196") + "").split("/")[0])+num.getXtgf40196());
             num.setQt40000( Integer.valueOf((map.get("qt40000") + "").split("/")[0])+num.getQt40000());
             num.setQt40049( Integer.valueOf((map.get("qt40049") + "").split("/")[0])+num.getQt40049());
             num.setQt40050( Integer.valueOf((map.get("qt40050") + "").split("/")[0])+num.getQt40050());
             num.setQt40154( Integer.valueOf((map.get("qt40154") + "").split("/")[0])+num.getQt40154());
             num.setZcjd40002( Integer.valueOf((map.get("zcjd40002") + "").split("/")[0])+num.getZcjd40002());
             num.setZcjd40039( Integer.valueOf((map.get("zcjd40039") + "").split("/")[0])+num.getZcjd40039());
             num.setZcjd40040( Integer.valueOf((map.get("zcjd40040") + "").split("/")[0])+num.getZcjd40040());
             num.setZcjd40053( Integer.valueOf((map.get("zcjd40053") + "").split("/")[0])+num.getZcjd40053());
             num.setZcjd40054( Integer.valueOf((map.get("zcjd40054") + "").split("/")[0])+num.getZcjd40054());
             num.setZcjd40057( Integer.valueOf((map.get("zcjd40057") + "").split("/")[0])+num.getZcjd40057());
             num.setZcjd40063( Integer.valueOf((map.get("zcjd40063") + "").split("/")[0])+num.getZcjd40063());
             num.setZcjd40078( Integer.valueOf((map.get("zcjd40078") + "").split("/")[0])+num.getZcjd40078());
             num.setZcjd40084( Integer.valueOf((map.get("zcjd40084") + "").split("/")[0])+num.getZcjd40084());
             num.setZcjd40085( Integer.valueOf((map.get("zcjd40085") + "").split("/")[0])+num.getZcjd40085());
             num.setZcjd40086( Integer.valueOf((map.get("zcjd40086") + "").split("/")[0])+num.getZcjd40086());
             num.setZcjd40100( Integer.valueOf((map.get("zcjd40100") + "").split("/")[0])+num.getZcjd40100());
             num.setZcjd40132( Integer.valueOf((map.get("zcjd40132") + "").split("/")[0])+num.getZcjd40132());
             num.setZcjd40133( Integer.valueOf((map.get("zcjd40133") + "").split("/")[0])+num.getZcjd40133());
             num.setZcjd40158( Integer.valueOf((map.get("zcjd40158") + "").split("/")[0])+num.getZcjd40158());
             num.setZcjd40173( Integer.valueOf((map.get("zcjd40173") + "").split("/")[0])+num.getZcjd40173());
             num.setCxesg40000( Integer.valueOf((map.get("cxesg40000") + "").split("/")[0])+num.getCxesg40000());
             num.setCxesg40105( Integer.valueOf((map.get("cxesg40105") + "").split("/")[0])+num.getCxesg40105());
             num.setCxesg40115( Integer.valueOf((map.get("cxesg40115") + "").split("/")[0])+num.getCxesg40115());
             num.setCxesg40172( Integer.valueOf((map.get("cxesg40172") + "").split("/")[0])+num.getCxesg40172());
             num.setCxesg40204( Integer.valueOf((map.get("cxesg40204") + "").split("/")[0])+num.getCxesg40204());
             num.setTb40000( Integer.valueOf((map.get("tb40000") + "").split("/")[0])+num.getTb40000());
             num.setTb40048( Integer.valueOf((map.get("tb40048") + "").split("/")[0])+num.getTb40048());
             num.setTb40083( Integer.valueOf((map.get("tb40083") + "").split("/")[0])+num.getTb40083());
             num.setTb40092( Integer.valueOf((map.get("tb40092") + "").split("/")[0])+num.getTb40092());
             num.setTb40095( Integer.valueOf((map.get("tb40095") + "").split("/")[0])+num.getTb40095());
             num.setTb40106( Integer.valueOf((map.get("tb40106") + "").split("/")[0])+num.getTb40106());
             num.setTb40108( Integer.valueOf((map.get("tb40108") + "").split("/")[0])+num.getTb40108());
             num.setTb40109( Integer.valueOf((map.get("tb40109") + "").split("/")[0])+num.getTb40109());
             num.setTb40113( Integer.valueOf((map.get("tb40113") + "").split("/")[0])+num.getTb40113());
             num.setTb40117( Integer.valueOf((map.get("tb40117") + "").split("/")[0])+num.getTb40117());
             num.setTb40187( Integer.valueOf((map.get("tb40187") + "").split("/")[0])+num.getTb40187());
             num.setTb40206( Integer.valueOf((map.get("tb40206") + "").split("/")[0])+num.getTb40206());
             num.setTb40211( Integer.valueOf((map.get("tb40211") + "").split("/")[0])+num.getTb40211());
             num.setTb40232( Integer.valueOf((map.get("tb40232") + "").split("/")[0])+num.getTb40232());
             num.setTb40243( Integer.valueOf((map.get("tb40243") + "").split("/")[0])+num.getTb40243());
             num.setTb40285( Integer.valueOf((map.get("tb40285") + "").split("/")[0])+num.getTb40285());
             num.setCxesw40000( Integer.valueOf((map.get("cxesw40000") + "").split("/")[0])+num.getCxesw40000());
             num.setCxesw40105( Integer.valueOf((map.get("cxesw40105") + "").split("/")[0])+num.getCxesw40105());
             num.setCxesw40115( Integer.valueOf((map.get("cxesw40115") + "").split("/")[0])+num.getCxesw40115());
             num.setCxesw40172( Integer.valueOf((map.get("cxesw40172") + "").split("/")[0])+num.getCxesw40172());
             num.setCxesw40204( Integer.valueOf((map.get("cxesw40204") + "").split("/")[0])+num.getCxesw40204());
             num.setFljd40000( Integer.valueOf((map.get("fljd40000") + "").split("/")[0])+num.getFljd40000());
             num.setFljd40200( Integer.valueOf((map.get("fljd40200") + "").split("/")[0])+num.getFljd40200());
             num.setFljd40201( Integer.valueOf((map.get("fljd40201") + "").split("/")[0])+num.getFljd40201());
             num.setFljd40202( Integer.valueOf((map.get("fljd40202") + "").split("/")[0])+num.getFljd40202());
             num.setFljd40203( Integer.valueOf((map.get("fljd40203") + "").split("/")[0])+num.getFljd40203());
        }
         Double errorBl = pcAllCount == 0 ? 0 : (errorAllCount / pcAllCount) * 100;
         Double errorAvgCount = pcAllCount == 0 ? 0 : (errorCount / pcAllCount) * 100;
         Double errorAvgNum = errorAllCount == 0 ? 0 : (errorCount / errorAllCount);
         map.put("errorBl", decimalFormat.format(errorBl) + "%");
         map.put("errorAvgCount", decimalFormat.format(errorAvgCount) + "%");
         map.put("errorAvgNum", decimalFormat.format(errorAvgNum));
     }
     public void calculationNumToExcel(ErrorAndFlawTreeVo num, Map m, String flxtdm) {
         DecimalFormat decimalFormat = new DecimalFormat("0.00");
         m.put("bjrNum", num.getBjrNum());
         m.put("bjajNum", num.getBjajNum());
         m.put("pcrNum", num.getPcrNum());
         m.put("pcajNum", num.getPcajNum());
         m.put("PCALLCOUNT", num.getPcAllnum());m.put("errorAllCount",num.getErrorAllNum());m.put("errorCount", num.getErrorNum());
         m.put("zjcxHj", num.getZjcxHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getZjcxHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("ssrdHj", num.getSsrdHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getSsrdHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("flsyHj",  num.getFlsyHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getFlsyHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("bacxHj",  num.getBacxHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getBacxHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("flwsHj",  num.getFlwsHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getFlwsHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("sfHj",  num.getSfHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getSfHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("xtgfHj",  num.getXtgfHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getXtgfHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("qtHj",  num.getQtHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getQtHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("zcjdHj",  num.getZcjdHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getZcjdHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("cxesgHj",  num.getCxesgHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getCxesgHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("tbHj",  num.getTbHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getTbHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("cxeswHj",  num.getCxeswHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getCxeswHj() / num.getErrorAllNum() * 100)) + "%");
         m.put("fljdHj",  num.getFljdHj() + "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : ( num.getFljdHj() / num.getErrorAllNum() * 100)) + "%");
         if (flxtdm.equals("30002")) {
             m.put("zjcx40000",num.getZjcx40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40007",num.getZjcx40007()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40007()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40010",num.getZjcx40010()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40010()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40066",num.getZjcx40066()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40066()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40067",num.getZjcx40067()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40067()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40068",num.getZjcx40068()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40068()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40069",num.getZjcx40069()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40069()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40071",num.getZjcx40071()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40071()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40074",num.getZjcx40074()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40074()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40081",num.getZjcx40081()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40081()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40136",num.getZjcx40136()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40136()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40137",num.getZjcx40137()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40137()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40138",num.getZjcx40138()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40138()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40139",num.getZjcx40139()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40139()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40140",num.getZjcx40140()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40140()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40141",num.getZjcx40141()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40141()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40214",num.getZjcx40214()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40214()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40215",num.getZjcx40215()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40215()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40216",num.getZjcx40216()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40216()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40229",num.getZjcx40229()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40229()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40289",num.getZjcx40289()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40289()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40290",num.getZjcx40290()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40290()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40291",num.getZjcx40291()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40291()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40000",num.getSsrd40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40005",num.getSsrd40005()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40005()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40025",num.getSsrd40025()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40025()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40026",num.getSsrd40026()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40026()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40029",num.getSsrd40029()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40029()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40030",num.getSsrd40030()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40030()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40031",num.getSsrd40031()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40031()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40032",num.getSsrd40032()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40032()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40033",num.getSsrd40033()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40033()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40075",num.getSsrd40075()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40075()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40076",num.getSsrd40076()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40076()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40088",num.getSsrd40088()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40088()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40089",num.getSsrd40089()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40089()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40090",num.getSsrd40090()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40090()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40131",num.getSsrd40131()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40131()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40170",num.getSsrd40170()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40170()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40207",num.getSsrd40207()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40207()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40210",num.getSsrd40210()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40210()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40224",num.getSsrd40224()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40224()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40227",num.getSsrd40227()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40227()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40274",num.getSsrd40274()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40274()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40276",num.getSsrd40276()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40276()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40277",num.getSsrd40277()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40277()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40278",num.getSsrd40278()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40278()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40280",num.getSsrd40280()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40280()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40000",num.getFlsy40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40208",num.getFlsy40208()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40208()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40258",num.getFlsy40258()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40258()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40259",num.getFlsy40259()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40259()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40260",num.getFlsy40260()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40260()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40261",num.getFlsy40261()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40261()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40262",num.getFlsy40262()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40262()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40263",num.getFlsy40263()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40263()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40264",num.getFlsy40264()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40264()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40265",num.getFlsy40265()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40265()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40266",num.getFlsy40266()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40266()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40000",num.getBacx40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40001",num.getBacx40001()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40001()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40008",num.getBacx40008()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40008()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40042",num.getBacx40042()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40042()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40059",num.getBacx40059()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40059()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40062",num.getBacx40062()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40062()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40070",num.getBacx40070()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40070()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40082",num.getBacx40082()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40082()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40102",num.getBacx40102()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40102()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40103",num.getBacx40103()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40103()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40112",num.getBacx40112()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40112()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40118",num.getBacx40118()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40118()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40120",num.getBacx40120()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40120()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40129",num.getBacx40129()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40129()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40135",num.getBacx40135()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40135()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40160",num.getBacx40160()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40160()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40220",num.getBacx40220()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40220()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40231",num.getBacx40231()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40231()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40251",num.getBacx40251()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40251()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40252",num.getBacx40252()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40252()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40253",num.getBacx40253()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40253()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40254",num.getBacx40254()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40254()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40268",num.getBacx40268()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40268()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40272",num.getBacx40272()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40272()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40283",num.getBacx40283()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40283()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40037",num.getFlws40037()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40037()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40098",num.getFlws40098()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40098()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40155",num.getFlws40155()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40155()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40000",num.getSf40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40163",num.getSf40163()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40163()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40056",num.getZcjd40056()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40056()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40099",num.getZcjd40099()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40099()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40249",num.getZcjd40249()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40249()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40255",num.getZcjd40255()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40255()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40180",num.getTb40180()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40180()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40212",num.getTb40212()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40212()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40286",num.getTb40286()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40286()/ num.getErrorAllNum() * 100)) + "%");
         } else {
             m.put("zjcx40000",num.getZjcx40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40072",num.getZjcx40072()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40072()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40073",num.getZjcx40073()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40073()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40116",num.getZjcx40116()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40116()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40213",num.getZjcx40213()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40213()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40235",num.getZjcx40235()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40235()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40236",num.getZjcx40236()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40236()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40237",num.getZjcx40237()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40237()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40238",num.getZjcx40238()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40238()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40239",num.getZjcx40239()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40239()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40240",num.getZjcx40240()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40240()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40241",num.getZjcx40241()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40241()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40246",num.getZjcx40246()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40246()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40288",num.getZjcx40288()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40288()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zjcx40292",num.getZjcx40292()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZjcx40292()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40000",num.getSsrd40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40024",num.getSsrd40024()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40024()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40025",num.getSsrd40025()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40025()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40026",num.getSsrd40026()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40026()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40027",num.getSsrd40027()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40027()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40028",num.getSsrd40028()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40028()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40034",num.getSsrd40034()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40034()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40035",num.getSsrd40035()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40035()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40041",num.getSsrd40041()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40041()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40091",num.getSsrd40091()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40091()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40096",num.getSsrd40096()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40096()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40134",num.getSsrd40134()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40134()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40228",num.getSsrd40228()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40228()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40275",num.getSsrd40275()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40275()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40281",num.getSsrd40281()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40281()/ num.getErrorAllNum() * 100)) + "%");
             m.put("ssrd40282",num.getSsrd40282()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSsrd40282()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40000",num.getFlsy40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40125",num.getFlsy40125()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40125()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40126",num.getFlsy40126()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40126()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40127",num.getFlsy40127()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40127()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40128",num.getFlsy40128()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40128()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40209",num.getFlsy40209()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40209()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40267",num.getFlsy40267()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40267()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40270",num.getFlsy40270()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40270()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40271",num.getFlsy40271()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40271()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flsy40284",num.getFlsy40284()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlsy40284()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40000",num.getBacx40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40003",num.getBacx40003()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40003()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40004",num.getBacx40004()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40004()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40006",num.getBacx40006()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40006()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40009",num.getBacx40009()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40009()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40011",num.getBacx40011()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40011()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40012",num.getBacx40012()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40012()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40013",num.getBacx40013()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40013()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40014",num.getBacx40014()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40014()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40015",num.getBacx40015()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40015()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40016",num.getBacx40016()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40016()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40017",num.getBacx40017()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40017()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40019",num.getBacx40019()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40019()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40020",num.getBacx40020()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40020()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40021",num.getBacx40021()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40021()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40022",num.getBacx40022()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40022()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40023",num.getBacx40023()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40023()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40036",num.getBacx40036()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40036()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40045",num.getBacx40045()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40045()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40046",num.getBacx40046()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40046()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40047",num.getBacx40047()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40047()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40051",num.getBacx40051()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40051()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40052",num.getBacx40052()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40052()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40055",num.getBacx40055()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40055()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40058",num.getBacx40058()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40058()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40060",num.getBacx40060()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40060()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40061",num.getBacx40061()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40061()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40064",num.getBacx40064()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40064()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40065",num.getBacx40065()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40065()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40077",num.getBacx40077()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40077()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40079",num.getBacx40079()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40079()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40080",num.getBacx40080()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40080()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40087",num.getBacx40087()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40087()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40093",num.getBacx40093()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40093()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40094",num.getBacx40094()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40094()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40101",num.getBacx40101()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40101()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40104",num.getBacx40104()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40104()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40107",num.getBacx40107()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40107()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40111",num.getBacx40111()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40111()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40114",num.getBacx40114()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40114()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40119",num.getBacx40119()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40119()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40121",num.getBacx40121()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40121()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40122",num.getBacx40122()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40122()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40123",num.getBacx40123()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40123()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40124",num.getBacx40124()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40124()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40130",num.getBacx40130()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40130()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40142",num.getBacx40142()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40142()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40143",num.getBacx40143()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40143()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40144",num.getBacx40144()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40144()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40145",num.getBacx40145()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40145()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40146",num.getBacx40146()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40146()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40147",num.getBacx40147()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40147()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40148",num.getBacx40148()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40148()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40149",num.getBacx40149()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40149()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40150",num.getBacx40150()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40150()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40151",num.getBacx40150()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40150()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40152",num.getBacx40152()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40152()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40153",num.getBacx40153()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40153()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40159",num.getBacx40159()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40159()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40161",num.getBacx40161()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40161()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40164",num.getBacx40164()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40164()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40165",num.getBacx40165()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40165()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40166",num.getBacx40166()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40166()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40167",num.getBacx40167()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40167()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40168",num.getBacx40168()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40168()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40169",num.getBacx40169()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40169()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40171",num.getBacx40171()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40171()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40174",num.getBacx40174()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40174()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40175",num.getBacx40175()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40175()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40176",num.getBacx40176()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40176()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40179",num.getBacx40179()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40179()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40181",num.getBacx40181()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40181()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40182",num.getBacx40182()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40182()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40183",num.getBacx40183()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40183()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40185",num.getBacx40185()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40185()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40188",num.getBacx40188()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40188()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40190",num.getBacx40190()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40190()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40191",num.getBacx40191()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40191()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40192",num.getBacx40192()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40192()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40193",num.getBacx40193()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40193()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40194",num.getBacx40194()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40194()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40195",num.getBacx40195()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40195()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40197",num.getBacx40197()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40197()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40199",num.getBacx40199()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40199()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40204",num.getBacx40204()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40204()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40205",num.getBacx40205()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40205()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40217",num.getBacx40217()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40217()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40218",num.getBacx40218()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40218()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40219",num.getBacx40219()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40219()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40221",num.getBacx40221()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40221()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40222",num.getBacx40222()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40222()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40223",num.getBacx40223()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40223()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40225",num.getBacx40225()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40225()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40226",num.getBacx40226()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40226()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40230",num.getBacx40230()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40230()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40233",num.getBacx40233()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40233()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40234",num.getBacx40234()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40234()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40244",num.getBacx40244()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40244()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40245",num.getBacx40245()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40245()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40247",num.getBacx40247()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40247()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40248",num.getBacx40248()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40248()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40250",num.getBacx40250()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40250()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40256",num.getBacx40256()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40256()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40257",num.getBacx40257()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40257()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40269",num.getBacx40269()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40269()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40273",num.getBacx40273()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40273()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40279",num.getBacx40279()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40279()/ num.getErrorAllNum() * 100)) + "%");
             m.put("bacx40287",num.getBacx40287()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getBacx40287()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40000",num.getFlws40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40043",num.getFlws40043()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40043()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40044",num.getFlws40044()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40044()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40156",num.getFlws40156()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40156()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40157",num.getFlws40157()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40157()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40186",num.getFlws40186()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40186()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40189",num.getFlws40189()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40189()/ num.getErrorAllNum() * 100)) + "%");
             m.put("flws40242",num.getFlws40242()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFlws40242()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40000",num.getSf40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40038",num.getSf40038()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40038()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40184",num.getSf40184()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40184()/ num.getErrorAllNum() * 100)) + "%");
             m.put("sf40198",num.getSf40198()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getSf40198()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40000",num.getXtgf40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40018",num.getXtgf40018()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40018()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40097",num.getXtgf40097()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40097()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40110",num.getXtgf40110()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40110()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40162",num.getXtgf40162()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40162()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40177",num.getXtgf40177()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40177()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40178",num.getXtgf40178()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40178()/ num.getErrorAllNum() * 100)) + "%");
             m.put("xtgf40196",num.getXtgf40196()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getXtgf40196()/ num.getErrorAllNum() * 100)) + "%");
             m.put("qt40000", num.getQt40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getQt40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("qt40049", num.getQt40049()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getQt40049()/ num.getErrorAllNum() * 100)) + "%");
             m.put("qt40050", num.getQt40050()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getQt40050()/ num.getErrorAllNum() * 100)) + "%");
             m.put("qt40154", num.getQt40154()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getQt40154()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40002",num.getZcjd40002()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40002()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40039",num.getZcjd40039()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40039()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40040",num.getZcjd40040()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40040()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40053",num.getZcjd40053()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40053()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40054",num.getZcjd40054()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40054()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40057",num.getZcjd40057()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40057()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40063",num.getZcjd40063()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40063()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40078",num.getZcjd40078()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40078()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40084",num.getZcjd40084()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40084()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40085",num.getZcjd40085()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40085()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40086",num.getZcjd40086()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40086()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40100",num.getZcjd40100()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40100()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40132",num.getZcjd40132()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40132()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40133",num.getZcjd40133()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40133()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40158",num.getZcjd40158()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40158()/ num.getErrorAllNum() * 100)) + "%");
             m.put("zcjd40173",num.getZcjd40173()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getZcjd40173()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesg40000", num.getCxesg40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesg40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesg40105", num.getCxesg40105()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesg40105()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesg40115", num.getCxesg40115()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesg40115()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesg40172", num.getCxesg40172()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesg40172()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesg40204", num.getCxesg40204()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesg40204()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40000",num.getTb40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40048",num.getTb40048()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40048()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40083",num.getTb40083()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40083()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40092",num.getTb40092()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40092()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40095",num.getTb40095()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40095()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40106",num.getTb40106()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40106()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40108",num.getTb40108()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40108()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40109",num.getTb40109()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40109()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40113",num.getTb40113()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40113()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40117",num.getTb40117()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40117()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40187",num.getTb40187()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40187()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40206",num.getTb40206()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40206()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40211",num.getTb40211()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40211()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40232",num.getTb40232()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40232()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40243",num.getTb40243()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40243()/ num.getErrorAllNum() * 100)) + "%");
             m.put("tb40285",num.getTb40285()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getTb40285()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesw40000", num.getCxesw40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesw40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesw40105", num.getCxesw40105()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesw40105()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesw40115", num.getCxesw40115()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesw40115()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesw40172", num.getCxesw40172()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesw40172()/ num.getErrorAllNum() * 100)) + "%");
             m.put("cxesw40204", num.getCxesw40204()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getCxesw40204()/ num.getErrorAllNum() * 100)) + "%");
             m.put("fljd40000", num.getFljd40000()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFljd40000()/ num.getErrorAllNum() * 100)) + "%");
             m.put("fljd40200", num.getFljd40200()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFljd40200()/ num.getErrorAllNum() * 100)) + "%");
             m.put("fljd40201", num.getFljd40201()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFljd40201()/ num.getErrorAllNum() * 100)) + "%");
             m.put("fljd40202", num.getFljd40202()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFljd40202()/ num.getErrorAllNum() * 100)) + "%");
             m.put("fljd40203", num.getFljd40202()+ "/" + decimalFormat.format((num.getErrorAllNum() == 0) ? 0 : (num.getFljd40203()/ num.getErrorAllNum() * 100)) + "%");
         }
         Double pcAllCount = Double.valueOf(m.get("PCALLCOUNT") + "");Double errorAllCount = Double.valueOf(m.get("errorAllCount") + "");
         Double errorCount = Double.valueOf(m.get("errorCount") + ""); Double errorBlNum = pcAllCount == 0 ? 0 : (errorAllCount / pcAllCount) * 100;
         Double errorAvgCountNum = pcAllCount == 0 ? 0 : (errorCount / pcAllCount) * 100;Double errorAvgNums = errorAllCount == 0 ? 0 : (errorCount / errorAllCount);
         m.put("errorBl", decimalFormat.format(errorBlNum) + "%");m.put("errorAvgCount", decimalFormat.format(errorAvgCountNum) + "%"); m.put("errorAvgNum", decimalFormat.format(errorAvgNums));
     }
    private void sumPcxDataAllExcel(List<Map> list, List<Map> lists, String numType, String flxtdm) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            ErrorAndFlawTreeVo num=new ErrorAndFlawTreeVo();
            for (int a = 0; a < lists.size(); a++) {
                Map map = lists.get(a);
                String id = m.get("id") + "";
                id = numType.equals("0") ? id : id.substring(0, 6);
                    String ids = map.get("id") + "";
                    ids = numType.equals("0") ? ids : ids.substring(0, 6);
                    String pid = map.get("pid") + "";
                    if (id.equals(pid) || id.equals(ids)) {
                    assignmentToExcel( num,  map, flxtdm);
                    for (int b = 0; b < lists.size(); b++) {
                        Map mapb = lists.get(b);
                        if (map.get("id").equals(mapb.get("pid"))) {
                            assignmentToExcel( num,  mapb, flxtdm);
                        }
                    }
                }
            }
            calculationNumToExcel( num,  m,  flxtdm);
        }
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

    private void sumPcxDatasExcel(List<Map> list, String flxtdm) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        ErrorAndFlawTreeVo num = new ErrorAndFlawTreeVo();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            assignmentToExcel( num,  map, flxtdm);
        }
        Map m = new HashMap<>();
        calculationNumToExcel( num,  m,  flxtdm);
        m.put("name", "合计");
        m.put("pid", "-1");
        m.put("id", "10000");
        list.add(0, m);
    }


    public List<List<String>> resultExcelList(List<Map> list, String flxtdm) {
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
            if (flxtdm.equals("30002")) {
                sigleData.add(String.valueOf(m.get("zjcxHj")));
                sigleData.add(String.valueOf(m.get("zjcx40007")));
                sigleData.add(String.valueOf(m.get("zjcx40010")));
                sigleData.add(String.valueOf(m.get("zjcx40066")));
                sigleData.add(String.valueOf(m.get("zjcx40067")));
                sigleData.add(String.valueOf(m.get("zjcx40068")));
                sigleData.add(String.valueOf(m.get("zjcx40069")));
                sigleData.add(String.valueOf(m.get("zjcx40071")));
                sigleData.add(String.valueOf(m.get("zjcx40074")));
                sigleData.add(String.valueOf(m.get("zjcx40081")));
                sigleData.add(String.valueOf(m.get("zjcx40136")));
                sigleData.add(String.valueOf(m.get("zjcx40137")));
                sigleData.add(String.valueOf(m.get("zjcx40138")));
                sigleData.add(String.valueOf(m.get("zjcx40139")));
                sigleData.add(String.valueOf(m.get("zjcx40140")));
                sigleData.add(String.valueOf(m.get("zjcx40141")));
                sigleData.add(String.valueOf(m.get("zjcx40214")));
                sigleData.add(String.valueOf(m.get("zjcx40215")));
                sigleData.add(String.valueOf(m.get("zjcx40216")));
                sigleData.add(String.valueOf(m.get("zjcx40229")));
                sigleData.add(String.valueOf(m.get("zjcx40289")));
                sigleData.add(String.valueOf(m.get("zjcx40290")));
                sigleData.add(String.valueOf(m.get("zjcx40291")));
                sigleData.add(String.valueOf(m.get("zjcx40000")));
                sigleData.add(String.valueOf(m.get("ssrdHj")));
                sigleData.add(String.valueOf(m.get("ssrd40005")));
                sigleData.add(String.valueOf(m.get("ssrd40025")));
                sigleData.add(String.valueOf(m.get("ssrd40026")));
                sigleData.add(String.valueOf(m.get("ssrd40029")));
                sigleData.add(String.valueOf(m.get("ssrd40030")));
                sigleData.add(String.valueOf(m.get("ssrd40031")));
                sigleData.add(String.valueOf(m.get("ssrd40032")));
                sigleData.add(String.valueOf(m.get("ssrd40033")));
                sigleData.add(String.valueOf(m.get("ssrd40075")));
                sigleData.add(String.valueOf(m.get("ssrd40076")));
                sigleData.add(String.valueOf(m.get("ssrd40088")));
                sigleData.add(String.valueOf(m.get("ssrd40089")));
                sigleData.add(String.valueOf(m.get("ssrd40090")));
                sigleData.add(String.valueOf(m.get("ssrd40131")));
                sigleData.add(String.valueOf(m.get("ssrd40170")));
                sigleData.add(String.valueOf(m.get("ssrd40207")));
                sigleData.add(String.valueOf(m.get("ssrd40210")));
                sigleData.add(String.valueOf(m.get("ssrd40224")));
                sigleData.add(String.valueOf(m.get("ssrd40227")));
                sigleData.add(String.valueOf(m.get("ssrd40274")));
                sigleData.add(String.valueOf(m.get("ssrd40276")));
                sigleData.add(String.valueOf(m.get("ssrd40277")));
                sigleData.add(String.valueOf(m.get("ssrd40278")));
                sigleData.add(String.valueOf(m.get("ssrd40280")));
                sigleData.add(String.valueOf(m.get("ssrd40000")));
                sigleData.add(String.valueOf(m.get("flsyHj")));
                sigleData.add(String.valueOf(m.get("flsy40208")));
                sigleData.add(String.valueOf(m.get("flsy40258")));
                sigleData.add(String.valueOf(m.get("flsy40259")));
                sigleData.add(String.valueOf(m.get("flsy40260")));
                sigleData.add(String.valueOf(m.get("flsy40261")));
                sigleData.add(String.valueOf(m.get("flsy40262")));
                sigleData.add(String.valueOf(m.get("flsy40263")));
                sigleData.add(String.valueOf(m.get("flsy40264")));
                sigleData.add(String.valueOf(m.get("flsy40265")));
                sigleData.add(String.valueOf(m.get("flsy40266")));
                sigleData.add(String.valueOf(m.get("flsy40000")));
                sigleData.add(String.valueOf(m.get("bacxHj")));
                sigleData.add(String.valueOf(m.get("bacx40001")));
                sigleData.add(String.valueOf(m.get("bacx40008")));
                sigleData.add(String.valueOf(m.get("bacx40042")));
                sigleData.add(String.valueOf(m.get("bacx40059")));
                sigleData.add(String.valueOf(m.get("bacx40062")));
                sigleData.add(String.valueOf(m.get("bacx40070")));
                sigleData.add(String.valueOf(m.get("bacx40082")));
                sigleData.add(String.valueOf(m.get("bacx40102")));
                sigleData.add(String.valueOf(m.get("bacx40103")));
                sigleData.add(String.valueOf(m.get("bacx40112")));
                sigleData.add(String.valueOf(m.get("bacx40118")));
                sigleData.add(String.valueOf(m.get("bacx40120")));
                sigleData.add(String.valueOf(m.get("bacx40129")));
                sigleData.add(String.valueOf(m.get("bacx40135")));
                sigleData.add(String.valueOf(m.get("bacx40160")));
                sigleData.add(String.valueOf(m.get("bacx40220")));
                sigleData.add(String.valueOf(m.get("bacx40231")));
                sigleData.add(String.valueOf(m.get("bacx40251")));
                sigleData.add(String.valueOf(m.get("bacx40252")));
                sigleData.add(String.valueOf(m.get("bacx40253")));
                sigleData.add(String.valueOf(m.get("bacx40254")));
                sigleData.add(String.valueOf(m.get("bacx40268")));
                sigleData.add(String.valueOf(m.get("bacx40272")));
                sigleData.add(String.valueOf(m.get("bacx40283")));
                sigleData.add(String.valueOf(m.get("bacx40000")));
                sigleData.add(String.valueOf(m.get("flwsHj")));
                sigleData.add(String.valueOf(m.get("flws40037")));
                sigleData.add(String.valueOf(m.get("flws40098")));
                sigleData.add(String.valueOf(m.get("flws40155")));
                sigleData.add(String.valueOf(m.get("sfHj")));
                sigleData.add(String.valueOf(m.get("sf40163")));
                sigleData.add(String.valueOf(m.get("sf40000")));
                sigleData.add(String.valueOf(m.get("xtgfHj")));
                sigleData.add(String.valueOf(m.get("qtHj")));
                sigleData.add(String.valueOf(m.get("zcjdHj")));
                sigleData.add(String.valueOf(m.get("zcjd40056")));
                sigleData.add(String.valueOf(m.get("zcjd40099")));
                sigleData.add(String.valueOf(m.get("zcjd40249")));
                sigleData.add(String.valueOf(m.get("zcjd40255")));
                sigleData.add(String.valueOf(m.get("cxesgHj")));
                sigleData.add(String.valueOf(m.get("tbHj")));
                sigleData.add(String.valueOf(m.get("tb40180")));
                sigleData.add(String.valueOf(m.get("tb40212")));
                sigleData.add(String.valueOf(m.get("tb40286")));
                sigleData.add(String.valueOf(m.get("cxeswHj")));
                sigleData.add(String.valueOf(m.get("fljdHj")));
            } else {
                sigleData.add(String.valueOf(m.get("zjcxHj")));
                sigleData.add(String.valueOf(m.get("zjcx40072")));
                sigleData.add(String.valueOf(m.get("zjcx40073")));
                sigleData.add(String.valueOf(m.get("zjcx40116")));
                sigleData.add(String.valueOf(m.get("zjcx40213")));
                sigleData.add(String.valueOf(m.get("zjcx40235")));
                sigleData.add(String.valueOf(m.get("zjcx40236")));
                sigleData.add(String.valueOf(m.get("zjcx40237")));
                sigleData.add(String.valueOf(m.get("zjcx40238")));
                sigleData.add(String.valueOf(m.get("zjcx40239")));
                sigleData.add(String.valueOf(m.get("zjcx40240")));
                sigleData.add(String.valueOf(m.get("zjcx40241")));
                sigleData.add(String.valueOf(m.get("zjcx40246")));
                sigleData.add(String.valueOf(m.get("zjcx40288")));
                sigleData.add(String.valueOf(m.get("zjcx40292")));
                sigleData.add(String.valueOf(m.get("zjcx40000")));
                sigleData.add(String.valueOf(m.get("ssrdHj")));
                sigleData.add(String.valueOf(m.get("ssrd40024")));
                sigleData.add(String.valueOf(m.get("ssrd40025")));
                sigleData.add(String.valueOf(m.get("ssrd40026")));
                sigleData.add(String.valueOf(m.get("ssrd40027")));
                sigleData.add(String.valueOf(m.get("ssrd40028")));
                sigleData.add(String.valueOf(m.get("ssrd40034")));
                sigleData.add(String.valueOf(m.get("ssrd40035")));
                sigleData.add(String.valueOf(m.get("ssrd40041")));
                sigleData.add(String.valueOf(m.get("ssrd40091")));
                sigleData.add(String.valueOf(m.get("ssrd40096")));
                sigleData.add(String.valueOf(m.get("ssrd40134")));
                sigleData.add(String.valueOf(m.get("ssrd40228")));
                sigleData.add(String.valueOf(m.get("ssrd40275")));
                sigleData.add(String.valueOf(m.get("ssrd40281")));
                sigleData.add(String.valueOf(m.get("ssrd40282")));
                sigleData.add(String.valueOf(m.get("ssrd40000")));
                sigleData.add(String.valueOf(m.get("flsyHj")));
                sigleData.add(String.valueOf(m.get("flsy40125")));
                sigleData.add(String.valueOf(m.get("flsy40126")));
                sigleData.add(String.valueOf(m.get("flsy40127")));
                sigleData.add(String.valueOf(m.get("flsy40128")));
                sigleData.add(String.valueOf(m.get("flsy40209")));
                sigleData.add(String.valueOf(m.get("flsy40267")));
                sigleData.add(String.valueOf(m.get("flsy40270")));
                sigleData.add(String.valueOf(m.get("flsy40271")));
                sigleData.add(String.valueOf(m.get("flsy40284")));
                sigleData.add(String.valueOf(m.get("flsy40000")));
                sigleData.add(String.valueOf(m.get("bacxHj")));
                sigleData.add(String.valueOf(m.get("bacx40003")));
                sigleData.add(String.valueOf(m.get("bacx40004")));
                sigleData.add(String.valueOf(m.get("bacx40006")));
                sigleData.add(String.valueOf(m.get("bacx40009")));
                sigleData.add(String.valueOf(m.get("bacx40011")));
                sigleData.add(String.valueOf(m.get("bacx40012")));
                sigleData.add(String.valueOf(m.get("bacx40013")));
                sigleData.add(String.valueOf(m.get("bacx40014")));
                sigleData.add(String.valueOf(m.get("bacx40015")));
                sigleData.add(String.valueOf(m.get("bacx40016")));
                sigleData.add(String.valueOf(m.get("bacx40017")));
                sigleData.add(String.valueOf(m.get("bacx40019")));
                sigleData.add(String.valueOf(m.get("bacx40020")));
                sigleData.add(String.valueOf(m.get("bacx40021")));
                sigleData.add(String.valueOf(m.get("bacx40022")));
                sigleData.add(String.valueOf(m.get("bacx40023")));
                sigleData.add(String.valueOf(m.get("bacx40036")));
                sigleData.add(String.valueOf(m.get("bacx40045")));
                sigleData.add(String.valueOf(m.get("bacx40046")));
                sigleData.add(String.valueOf(m.get("bacx40047")));
                sigleData.add(String.valueOf(m.get("bacx40051")));
                sigleData.add(String.valueOf(m.get("bacx40052")));
                sigleData.add(String.valueOf(m.get("bacx40055")));
                sigleData.add(String.valueOf(m.get("bacx40058")));
                sigleData.add(String.valueOf(m.get("bacx40060")));
                sigleData.add(String.valueOf(m.get("bacx40061")));
                sigleData.add(String.valueOf(m.get("bacx40064")));
                sigleData.add(String.valueOf(m.get("bacx40065")));
                sigleData.add(String.valueOf(m.get("bacx40077")));
                sigleData.add(String.valueOf(m.get("bacx40079")));
                sigleData.add(String.valueOf(m.get("bacx40080")));
                sigleData.add(String.valueOf(m.get("bacx40087")));
                sigleData.add(String.valueOf(m.get("bacx40093")));
                sigleData.add(String.valueOf(m.get("bacx40094")));
                sigleData.add(String.valueOf(m.get("bacx40101")));
                sigleData.add(String.valueOf(m.get("bacx40104")));
                sigleData.add(String.valueOf(m.get("bacx40107")));
                sigleData.add(String.valueOf(m.get("bacx40111")));
                sigleData.add(String.valueOf(m.get("bacx40114")));
                sigleData.add(String.valueOf(m.get("bacx40119")));
                sigleData.add(String.valueOf(m.get("bacx40121")));
                sigleData.add(String.valueOf(m.get("bacx40122")));
                sigleData.add(String.valueOf(m.get("bacx40123")));
                sigleData.add(String.valueOf(m.get("bacx40124")));
                sigleData.add(String.valueOf(m.get("bacx40130")));
                sigleData.add(String.valueOf(m.get("bacx40142")));
                sigleData.add(String.valueOf(m.get("bacx40143")));
                sigleData.add(String.valueOf(m.get("bacx40144")));
                sigleData.add(String.valueOf(m.get("bacx40145")));
                sigleData.add(String.valueOf(m.get("bacx40146")));
                sigleData.add(String.valueOf(m.get("bacx40147")));
                sigleData.add(String.valueOf(m.get("bacx40148")));
                sigleData.add(String.valueOf(m.get("bacx40149")));
                sigleData.add(String.valueOf(m.get("bacx40150")));
                sigleData.add(String.valueOf(m.get("bacx40151")));
                sigleData.add(String.valueOf(m.get("bacx40152")));
                sigleData.add(String.valueOf(m.get("bacx40153")));
                sigleData.add(String.valueOf(m.get("bacx40159")));
                sigleData.add(String.valueOf(m.get("bacx40161")));
                sigleData.add(String.valueOf(m.get("bacx40164")));
                sigleData.add(String.valueOf(m.get("bacx40165")));
                sigleData.add(String.valueOf(m.get("bacx40166")));
                sigleData.add(String.valueOf(m.get("bacx40167")));
                sigleData.add(String.valueOf(m.get("bacx40168")));
                sigleData.add(String.valueOf(m.get("bacx40169")));
                sigleData.add(String.valueOf(m.get("bacx40171")));
                sigleData.add(String.valueOf(m.get("bacx40174")));
                sigleData.add(String.valueOf(m.get("bacx40175")));
                sigleData.add(String.valueOf(m.get("bacx40176")));
                sigleData.add(String.valueOf(m.get("bacx40179")));
                sigleData.add(String.valueOf(m.get("bacx40181")));
                sigleData.add(String.valueOf(m.get("bacx40182")));
                sigleData.add(String.valueOf(m.get("bacx40183")));
                sigleData.add(String.valueOf(m.get("bacx40185")));
                sigleData.add(String.valueOf(m.get("bacx40188")));
                sigleData.add(String.valueOf(m.get("bacx40190")));
                sigleData.add(String.valueOf(m.get("bacx40191")));
                sigleData.add(String.valueOf(m.get("bacx40192")));
                sigleData.add(String.valueOf(m.get("bacx40193")));
                sigleData.add(String.valueOf(m.get("bacx40194")));
                sigleData.add(String.valueOf(m.get("bacx40195")));
                sigleData.add(String.valueOf(m.get("bacx40197")));
                sigleData.add(String.valueOf(m.get("bacx40199")));
                sigleData.add(String.valueOf(m.get("bacx40204")));
                sigleData.add(String.valueOf(m.get("bacx40205")));
                sigleData.add(String.valueOf(m.get("bacx40217")));
                sigleData.add(String.valueOf(m.get("bacx40218")));
                sigleData.add(String.valueOf(m.get("bacx40219")));
                sigleData.add(String.valueOf(m.get("bacx40221")));
                sigleData.add(String.valueOf(m.get("bacx40222")));
                sigleData.add(String.valueOf(m.get("bacx40223")));
                sigleData.add(String.valueOf(m.get("bacx40225")));
                sigleData.add(String.valueOf(m.get("bacx40226")));
                sigleData.add(String.valueOf(m.get("bacx40230")));
                sigleData.add(String.valueOf(m.get("bacx40233")));
                sigleData.add(String.valueOf(m.get("bacx40234")));
                sigleData.add(String.valueOf(m.get("bacx40244")));
                sigleData.add(String.valueOf(m.get("bacx40245")));
                sigleData.add(String.valueOf(m.get("bacx40247")));
                sigleData.add(String.valueOf(m.get("bacx40248")));
                sigleData.add(String.valueOf(m.get("bacx40250")));
                sigleData.add(String.valueOf(m.get("bacx40256")));
                sigleData.add(String.valueOf(m.get("bacx40257")));
                sigleData.add(String.valueOf(m.get("bacx40269")));
                sigleData.add(String.valueOf(m.get("bacx40273")));
                sigleData.add(String.valueOf(m.get("bacx40279")));
                sigleData.add(String.valueOf(m.get("bacx40287")));
                sigleData.add(String.valueOf(m.get("bacx40000")));
                sigleData.add(String.valueOf(m.get("flwsHj")));
                sigleData.add(String.valueOf(m.get("flws40043")));
                sigleData.add(String.valueOf(m.get("flws40044")));
                sigleData.add(String.valueOf(m.get("flws40156")));
                sigleData.add(String.valueOf(m.get("flws40157")));
                sigleData.add(String.valueOf(m.get("flws40186")));
                sigleData.add(String.valueOf(m.get("flws40189")));
                sigleData.add(String.valueOf(m.get("flws40242")));
                sigleData.add(String.valueOf(m.get("flws40000")));
                sigleData.add(String.valueOf(m.get("sfHj")));
                sigleData.add(String.valueOf(m.get("sf40038")));
                sigleData.add(String.valueOf(m.get("sf40184")));
                sigleData.add(String.valueOf(m.get("sf40198")));
                sigleData.add(String.valueOf(m.get("sf40000")));
                sigleData.add(String.valueOf(m.get("xtgfHj")));
                sigleData.add(String.valueOf(m.get("xtgf40018")));
                sigleData.add(String.valueOf(m.get("xtgf40097")));
                sigleData.add(String.valueOf(m.get("xtgf40110")));
                sigleData.add(String.valueOf(m.get("xtgf40162")));
                sigleData.add(String.valueOf(m.get("xtgf40177")));
                sigleData.add(String.valueOf(m.get("xtgf40178")));
                sigleData.add(String.valueOf(m.get("xtgf40196")));
                sigleData.add(String.valueOf(m.get("xtgf40000")));
                sigleData.add(String.valueOf(m.get("qtHj")));
                sigleData.add(String.valueOf(m.get("qt40049")));
                sigleData.add(String.valueOf(m.get("qt40050")));
                sigleData.add(String.valueOf(m.get("qt40154")));
                sigleData.add(String.valueOf(m.get("qt40000")));
                sigleData.add(String.valueOf(m.get("zcjdHj")));
                sigleData.add(String.valueOf(m.get("zcjd40002")));
                sigleData.add(String.valueOf(m.get("zcjd40039")));
                sigleData.add(String.valueOf(m.get("zcjd40040")));
                sigleData.add(String.valueOf(m.get("zcjd40053")));
                sigleData.add(String.valueOf(m.get("zcjd40054")));
                sigleData.add(String.valueOf(m.get("zcjd40057")));
                sigleData.add(String.valueOf(m.get("zcjd40063")));
                sigleData.add(String.valueOf(m.get("zcjd40078")));
                sigleData.add(String.valueOf(m.get("zcjd40084")));
                sigleData.add(String.valueOf(m.get("zcjd40085")));
                sigleData.add(String.valueOf(m.get("zcjd40086")));
                sigleData.add(String.valueOf(m.get("zcjd40100")));
                sigleData.add(String.valueOf(m.get("zcjd40132")));
                sigleData.add(String.valueOf(m.get("zcjd40133")));
                sigleData.add(String.valueOf(m.get("zcjd40158")));
                sigleData.add(String.valueOf(m.get("zcjd40173")));
                sigleData.add(String.valueOf(m.get("cxesgHj")));
                sigleData.add(String.valueOf(m.get("cxesg40105")));
                sigleData.add(String.valueOf(m.get("cxesg40115")));
                sigleData.add(String.valueOf(m.get("cxesg40172")));
                sigleData.add(String.valueOf(m.get("cxesg40204")));
                sigleData.add(String.valueOf(m.get("cxesg40000")));
                sigleData.add(String.valueOf(m.get("tbHj")));
                sigleData.add(String.valueOf(m.get("tb40048")));
                sigleData.add(String.valueOf(m.get("tb40083")));
                sigleData.add(String.valueOf(m.get("tb40092")));
                sigleData.add(String.valueOf(m.get("tb40095")));
                sigleData.add(String.valueOf(m.get("tb40106")));
                sigleData.add(String.valueOf(m.get("tb40108")));
                sigleData.add(String.valueOf(m.get("tb40109")));
                sigleData.add(String.valueOf(m.get("tb40113")));
                sigleData.add(String.valueOf(m.get("tb40117")));
                sigleData.add(String.valueOf(m.get("tb40187")));
                sigleData.add(String.valueOf(m.get("tb40206")));
                sigleData.add(String.valueOf(m.get("tb40211")));
                sigleData.add(String.valueOf(m.get("tb40232")));
                sigleData.add(String.valueOf(m.get("tb40243")));
                sigleData.add(String.valueOf(m.get("tb40285")));
                sigleData.add(String.valueOf(m.get("tb40000")));
                sigleData.add(String.valueOf(m.get("cxeswHj")));
                sigleData.add(String.valueOf(m.get("cxesw40105")));
                sigleData.add(String.valueOf(m.get("cxesw40115")));
                sigleData.add(String.valueOf(m.get("cxesw40172")));
                sigleData.add(String.valueOf(m.get("cxesw40204")));
                sigleData.add(String.valueOf(m.get("cxesw40000")));
                sigleData.add(String.valueOf(m.get("fljdHj")));
                sigleData.add(String.valueOf(m.get("fljd40200")));
                sigleData.add(String.valueOf(m.get("fljd40201")));
                sigleData.add(String.valueOf(m.get("fljd40202")));
                sigleData.add(String.valueOf(m.get("fljd40203")));
                sigleData.add(String.valueOf(m.get("fljd40000")));
            }
            data.add(sigleData);
        }
        return data;
    }

    private List<Map> sumDqGeneralExcelData(List<Map> lists, List<Map> list) {
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
     //   sumDatas(list, list);
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
            list = analysisMapper.getPcxBzByXtdm(map);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public Map getErrPclbAjJbxx(Map map) {
        map.put("wcrqnf", "".equals(map.get("wcrqnf")) ? "" : (map.get("wcrqnf") + "").split(","));
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
                map.put("wcrqnf", params.getWcrqnf());
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
        map.put("wcrqnf", param.getWcrqnf());
        map.put("ywtx", param.getYwtx());
        map.put("ajtjlb", param.getAjtjlb());
        Map ajMap = null;
        try {
            ajs = this.analysisMapper.getAjzlpcfxByYwtx(map);
            list = new ArrayList<List<String>>();
            for (int i = 0; i < ajs.size(); i++) {
                ajMap = ajs.get(i);
                List<String> sigleData = new ArrayList<>();
                sigleData.add("-1".equals(ajMap.get("AJLB_BM"))?ajMap.get("YWTX_MC").toString():ajMap.get("AJLB_MC").toString());
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
}

