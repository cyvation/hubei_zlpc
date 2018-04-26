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
public interface QueryTableService {

    /**
     * 根据条件查询评查项分类数量
     * @param query
     * @return
     */
    int getPcxFlCount(QueryTable query);
    /**
     * 根据条件查询评查项分类数量   对应的案件信息
     * @param query
     * @return
     */
    List<YX_PC_JBXX> getPcxFlCountAjjbxx(QueryTable query);

    /**
     * 获取审结数量
     * @param query
     * @return
     */
    int getSjCount(QueryTable query);

    /**
     * 获取已经评查的 案件基本信息（查ajjbxx）
     * @param query
     * @return
     */
    List<Map> getAjjbxx(QueryTableAjJbxx query);

    /**
     * 获取已经审结但未评查的 案件基本信息(查ajsx)
     * @param query
     * @return
     */
    List<Map> getWpcAjjbxx(QueryTableAjJbxx query);

    /**
     * 获取审结 案件基本信息
     * @param query
     * @return
     */
    List<Map> getSjCountAjjbxx(QueryTableAjJbxx query);


    /**
     * 获取单位表格数据
     * @param query
     * @return
     */
    ArrayList<QueryTableVo> getDwTableData(QueryTable query);


    /**
     * 获取部门表格数据
     * @param query
     * @return
     */
    ArrayList<QueryTableVo> getBmTableData(QueryTable query);



    /**
     * 获取检察官表格数据
     * @param query
     * @return
     */
    ArrayList<QueryTableVo> getJcgTableData(QueryTable query);

}
