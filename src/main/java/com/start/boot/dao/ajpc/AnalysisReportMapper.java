package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 湖北专题报告分析：报告模板编辑
 */
@Repository
public interface AnalysisReportMapper {
    /**
     * 获取模板下拉列表信息
     * @return
     */
    List<Map> loadFtlList();
    /**
     * 获取模板列表信息
     * @return
     */
    List<Map> loadFtlDataList(Map map);
    /**
     * 获取模板总数
     * @return
     */
    Map loadFtlDataListCount(Map map);
}
