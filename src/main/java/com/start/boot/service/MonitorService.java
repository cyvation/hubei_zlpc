package com.start.boot.service;

import com.start.boot.domain.XtPcSxgz;
import com.start.boot.pojo.dto.PcglViewDto;
import com.start.boot.query.MonitoryPcjsQuery;
import com.start.boot.query.PcglQuery;
import com.start.boot.support.structure.EasyUITree;

import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/3/20
 * @说明 监控service
 */
public interface MonitorService {

    /**
     * 获取评查概览数据
     * @param query
     * @return
     */
    PcglViewDto pcgl(PcglQuery query);

    /**
     * 获取未评查 案件列表
     * @param
     * @return
     */
    List<Map> getWpcAjJbxx(PcglQuery query);

    /**
     * 获取已评查 案件列表
     * @param
     * @return
     */
    List<Map> getYpcAjJbxx(PcglQuery query);


    /**
     * 评查检索，获取分组数据(单位、检察官、部门、评查员、评查状态、评查结论)
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getDwGroup(MonitoryPcjsQuery monitoryPcjsQuery);

}
