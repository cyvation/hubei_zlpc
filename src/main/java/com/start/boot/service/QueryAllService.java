package com.start.boot.service;

import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.vo.QueryTableVo;
import com.start.boot.query.QueryTable;
import com.start.boot.query.QueryTableAjJbxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明 三表Service
 */
public interface QueryAllService {

    /**
     * 根据条件查询评查项分类数量
     * @param query
     * @return
     */
    int getPcxFlCount(Map query);

    /**
     * 获取审结数量
     * @param query
     * @return
     */
    int getSjCount(Map query);

    /**
     * 获取评查表格数据
     * @param query
     * @return
     */
    ArrayList<QueryTableVo> getZlpcTableData(Map query);


    /**
     * 根据条件查询线下评查项分类数量
     * @param query
     * @return
     */
    int getOfflinePcxFlCount(Map query);

    /**
     * 获取已经评查的 案件基本信息（查ajjbxx）
     * @param query
     * @return
     */
    List<Map> getAjjbxx(QueryTableAjJbxx query);
}
