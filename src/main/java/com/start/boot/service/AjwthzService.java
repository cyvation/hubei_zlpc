package com.start.boot.service;

import java.util.List;
import java.util.Map;

import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.query.QueryTable;

/**
 * 案件问题汇总服务接口
 * 李志恒 2018-4-19
 */
public interface AjwthzService {

    List<AjpcwtxVo> getAjwthzList(Map query) throws Exception;

    String getDwAjwthzBarData(Map query) throws Exception;

    /**
     * 获取已经评查的 案件基本信息（查ajjbxx）
     * @param query
     * @return
     */
    Map getAjhzjbxx(Map query);

}
