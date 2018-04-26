package com.start.boot.service;

import com.start.boot.adquery.AdQueryRequest;
import com.start.boot.domain.AdvancedQuery;
import com.start.boot.pojo.vo.AdvanceQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 高级查询接口
 *
 * @caomin
 * @create 2017-12-13 11:35
 **/
public interface AdvanceQueryService {

    /**
     * 获取所有可选择条件
     * @return
     */
    List<AdvanceQueryVo>getAllCondition()throws Exception;

    /**
     * 根据编号获取查询对象
     * @param bh
     * @return
     */
    AdvancedQuery getByBh(String bh)throws Exception;

    /**
     * 开始查询
     * @param request
     * @param currentDwbm
     * @return
     * @throws Exception
     */
    String startQuery(AdQueryRequest request, String currentDwbm) throws Exception;


     List<Map> getReuslt(String sql)throws Exception;
    /**
     * 获取单个组件的值
     * @param bh
     * @return
     */
    Object getSingleCondition(String bh)throws Exception;
}
