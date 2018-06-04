package com.start.boot.service.impl;


import com.start.boot.dao.ajpc.AnalysisMapper;
import com.start.boot.dao.ajpc.AnalysisReportMapper;
import com.start.boot.domain.Param_Ajqkzlfx;
import com.start.boot.pojo.vo.AjqkzlflTreeVo;
import com.start.boot.pojo.vo.AjqkzlflVo;
import com.start.boot.pojo.vo.ErrorAndFlawTreeVo;
import com.start.boot.service.AnalysisReportService;
import com.start.boot.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 湖北专题报告分析：统计表及报告
 */
@Service
@Transactional
public class AnalysisReportServiceImpl implements AnalysisReportService {

    @Autowired
    private AnalysisReportMapper analysisReportMapper;

    @Override
    public List<Map> loadFtlList() throws Exception {
        List<Map> list=analysisReportMapper.loadFtlList();
        return list;
    }
    @Override
    public Map loadFtlDataList(Map map) throws Exception {
        map.put("ftlMb", "".equals(map.get("ftlMb")) ? "" : (map.get("ftlMb") + "").split(","));
        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        int pagenum=(Integer.parseInt(map.get("page")+"")-1)*Integer.parseInt(map.get("row")+"" );
        int row=Integer.parseInt( map.get("row")+"")+pagenum;
        map.put("page",pagenum);
        map.put("row",row);
        List<Map> list=analysisReportMapper.loadFtlDataList(map);
        Map count=analysisReportMapper.loadFtlDataListCount(map);
        Map result=new HashMap<>();
        result.put("total", count.get("NUM"));
        result.put("rows", list);
        return result;
    }
}

