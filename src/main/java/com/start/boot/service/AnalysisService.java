package com.start.boot.service;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.AjqkzlflTreeVo;
import com.start.boot.pojo.vo.AjqkzlflVo;
import com.start.boot.pojo.vo.ErrorAndFlawTreeVo;

import java.util.List;
import java.util.Map;

/**
 * 湖北专题报告分析：统计表及报告
 */
public interface AnalysisService {
    /**
     * 获取错误/瑕疵时间分析
     * @param map
     * @return
     * @throws Exception
     */
    List<ErrorAndFlawTreeVo> loadDateData(Map map) throws Exception;
    Map getPclbAjJbxx(Map map) throws Exception;

    /**
     * 获取评查总体时间分析
     * @param map
     * @return
     * @throws Exception
     */
    List<Map> loadDateGeneral(Map map) throws Exception;
    /**
     * 获取评查总体地区分析
     * @param map
     * @return
     * @throws Exception
     */
    List<ErrorAndFlawTreeVo> loadDqGeneral(Map map) throws Exception;
    /**
     * 根据统计类型获取案件质量情况分析
     * @param params
     * @return 案件质量情况分析集合
     * @throws Exception 异常信息
     */
    List<AjqkzlflVo> getAjqkzlfxByNf(Param_Ajqkzlfx params) throws  Exception;

    /**
     * 根据父代码获取评查代码
     * @param params
     * @return 评查代码集合
     * @Exception  异常信息
     */
    List<Map> getPcdmByFdm(Map params) throws Exception;

    /**
     * 获取评查总体条线分析
     * @param map
     * @return
     * @throws Exception
     */
    List<ErrorAndFlawTreeVo> loadTxGeneral(Map map) throws Exception;

    /**
     * 获取案件质量情况地区分析
     * @param params 查询条件
     * @return 案件质量情况分析集合
     * @throws Exception 异常信息
     */
    List<AjqkzlflTreeVo> getAjzlqkfxByDq(Param_Ajqkzlfx params) throws  Exception;

    /**
     * 获取案件质量情况条线分析
     * @param params 查询条件
     * @return 案件质量情况分析集合
     * @throws Exception 异常信息
     */
    List<AjqkzlflTreeVo> getAjzlqkfxByTx(Param_Ajqkzlfx params) throws  Exception;

    /**
     * 获取错误/瑕疵条线分析
     * @param map
     * @return
     * @throws Exception
     */
    List<ErrorAndFlawTreeVo>  loadTxData(Map map) throws Exception;

    /**
     * 获取错误/瑕疵地区分析
     * @param map
     * @return
     * @throws Exception
     */
    List<ErrorAndFlawTreeVo>  loadDqData(Map map) throws Exception;

    /**
     * 获取案件错误项目分析
     * @param params 条件参数
     * @return 错误项目列表
     * @throws Exception 异常对象
     */
    List<Map> getTcfx(Param_Ajqkzlfx params) throws Exception;


    /***************************************************************************数据导出***********************************/



    /**
     * 获取案件瑕疵项目分析
     * @param map 条件参数
     * @return 瑕疵项目列表
     * @throws Exception 异常对象
     */
    List<List<String>> excel_export_data(Map map) throws Exception;

    List<List<String>> excel_export_dataPc(Map map) throws Exception;

    /**
     * 根据系统代码获取评查项备注说明
     * @param map
     * @return
     */
    List<Map> getPcxBzByXtdm(Map map) throws Exception;

    /**
     * 获取瑕疵、错误的评查列表
     * @param map
     * @return
     * @throws Exception
     */
    Map getErrPclbAjJbxx(Map map) throws Exception;
    /**
     * 导出案件质量情况年度分析
     * @param param
     * @return
     */
    List<List<String>> getAjzlqkfxByNdToExcel(Param_Ajqkzlfx param);

    /**
     * 导出案件质量情况地区分析
     * @param param
     * @return
     */
    List<List<String>> getAjzlqkfxByDqToExcel(Param_Ajqkzlfx param) throws Exception;

    /**
     * 导出案件质量情况条线分析
     * @param param
     * @return
     */
    List<List<String>> getAjzlqkfxByTxToExcel(Param_Ajqkzlfx param);


    List<Map> getPcAjxxByParams(Map map) throws Exception;

    List<Map> getTccwxxPcAjxxByParams(Map map) throws  Exception;

}
