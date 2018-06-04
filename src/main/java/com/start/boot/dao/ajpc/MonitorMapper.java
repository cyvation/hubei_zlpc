package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtPcSxgz;
import com.start.boot.pojo.vo.PcglVo;
import com.start.boot.query.MonitoryPcjsQuery;
import com.start.boot.query.PcglQuery;
import com.start.boot.support.structure.EasyUITree;

import java.util.List;
import java.util.Map;

public interface MonitorMapper {
    /**
     * 评查概览  获取未评查数量
     * @param
     * @return
     */
    List<PcglVo> getWpc(PcglQuery query);

    /**
     * 评查概览  获取已评查数量
     * @param
     * @return
     */
    List<PcglVo> getYpc(PcglQuery query);
    /**
     * 评查概览  获取已评查数量
     * @param
     * @return
     */
    List<PcglVo> getYpcOffline(PcglQuery query);

    /**
     * 评查概览  获 取未评查 案件列表
     * @param
     * @return
     */
    List<Map> getWpcAjJbxx(PcglQuery query);

    /**
     *评查概览   获取已评查 案件列表
     * @param
     * @return
     */
    List<Map> getYpcAjJbxx(PcglQuery query);

    /**
     *评查概览   获取已评查 案件列表
     * @param
     * @return
     */
    List<Map> getYpcAjJbxxOffline(PcglQuery query);


    /**
     * 获取单位分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getDwGroup(MonitoryPcjsQuery monitoryPcjsQuery);
    /**
     * 获取检察官分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getJcgGroup(MonitoryPcjsQuery monitoryPcjsQuery);
    /**
     * 获取评查员分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getPcyGroup(MonitoryPcjsQuery monitoryPcjsQuery);

    /**
     * 获取部门分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getBmGroup(MonitoryPcjsQuery monitoryPcjsQuery);

    /**
     * 获取评查状态分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getPcztGroup(MonitoryPcjsQuery monitoryPcjsQuery);

    /**
     * 获取评查结论分组数据
     * @param monitoryPcjsQuery
     * @return
     */
    List<EasyUITree>  getPcjlGroup(MonitoryPcjsQuery monitoryPcjsQuery);


    /**
     * 获取评查案件数量
     * @param monitoryPcjsQuery
     * @return
     */
    int getPcajCount(MonitoryPcjsQuery monitoryPcjsQuery);

    List<XtPcSxgz> getMbbm(PcglQuery query);
}