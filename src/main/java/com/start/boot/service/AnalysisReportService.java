package com.start.boot.service;


import com.start.boot.domain.Param_Ajqkzlfx;
import com.start.boot.pojo.vo.AjqkzlflTreeVo;
import com.start.boot.pojo.vo.AjqkzlflVo;
import com.start.boot.pojo.vo.ErrorAndFlawTreeVo;

import java.util.List;
import java.util.Map;

/**
 * 湖北专题报告分析：统计表及报告
 */
public interface AnalysisReportService {
    /**
     *获取模板下拉列表
     * @return
     * @throws Exception
     */
    List<Map> loadFtlList() throws Exception;
    /**
     *获取模板列表
     * @return
     * @throws Exception
     */
    Map loadFtlDataList(Map map) throws Exception;
}
