package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * 湖北专题报告分析：统计表及报告
 */
@Repository
public interface AnalysisMapper {
    /**
     * 获取所有分类
     * @return
     */
    List<Map> loadDateFl();

    /**
     * 获取评查数
     * @param map
     * @return
     */
    List<Map> loadDatePcCount(Map map);

    /**
     * 获取存在错误/瑕疵的案件数
     * @return
     */
    List<Map> loadDateErrPcCount(Map map);

    /**
     * 获取错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadDateErrCount(Map map);

    /**
     * 获取每个分类的存在的错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadDatePcFlCount(Map map);
    /**
     * 获取评查数
     * @param map
     * @return
     */
    List<Map> loadTxPcCount(Map map);

    /**
     * 获取存在错误/瑕疵的案件数
     * @return
     */
    List<Map> loadTxErrPcCount(Map map);

    /**
     * 获取错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadTxErrCount(Map map);

    /**
     * 获取每个分类的存在的错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadTxPcFlCount(Map map);
    /**
     * 获取评查数
     * @param map
     * @return
     */
    List<Map> loadDqPcCount(Map map);

    /**
     * 获取存在错误/瑕疵的案件数
     * @return
     */
    List<Map> loadDqErrPcCount(Map map);

    /**
     * 获取错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadDqErrCount(Map map);

    /**
     * 获取每个分类的存在的错误/瑕疵项个数
     * @param map
     * @return
     */
    List<Map> loadDqPcFlCount(Map map);
    /**
     * 获取年度办结案件量
     * @param params
     * @return 办结案件量
     */
    Integer getNdBjAjCount(Map params);

    /**
     * 获取年度评查案件量
     * @param params
     * @return 评查案件量
     */
    Integer getNdPcAjCount(Map params);

    /**
     * 根据评查结论获取年度评查案件量
     * @param params
     * @return 评查案件量
     */
    Integer getNdPcAjCountByPcjl(Map params);

    /**
     * 根据代码类别获取系统代码
     * @param params
     * @return 评查结论集合
     */
    List<Map> getXtdmByLbbm(Map params);

    /**
     * 获取评查总体时间分析
     * @param map
     * @return
     */
    List<Map> loadDateGeneralBa(Map map);
    List<Map> loadDateGeneralBj(Map map);
    List<Map> loadDateGeneralPc(Map map);
    List<Map> loadDateGeneralPca(Map map);
    /**
     * 获取评查总体地区分析
     * @param map
     * @return
     */
    List<Map> loadDqGeneralBa(Map map);
    List<Map> loadDqGeneralBj(Map map);
    List<Map> loadDqGeneralPc(Map map);
    List<Map> loadDqGeneralPca(Map map);
    /**
     * 获取评查总体条线分析
     * @param map
     * @return
     */
    List<Map> loadTxGeneralBa(Map map);
    List<Map> loadTxGeneralBj(Map map);
    List<Map> loadTxGeneralPc(Map map);
    List<Map> loadTxGeneralPca(Map map);
    List<Map> loadTxGeneralAll(Map map);

    /**
     * 根据父代码获取评查代码
     * @param params
     * @return 评查代码集合
     */
    List<Map> getPcdmByFdm(Map params);

    /**
     * 获取单位列表集合
     * @param params 查询条件
     * @return 单位列表
     */
    List<Map> getDwbmList(Map params);

    /**
     * 根据业务条线获取案件类别列表
     * @param params 查询条件
     * @return 案件类别列表
     */
    List<Map> getAjlbByYwtx(Map params);
    /**
     * 获取单位列表集合
     * @param params 查询条件
     * @return 单位列表
     */
    List<Map> getDwbmLists(Map params);

    /**
     * 根据分类系统代码获取评查项
     * @param params 查询条件
     * @return 评查项列表
     */
    List<Map> getPcxByFlxtdm(Map params);

    /**
     * 根据系统代码获取案件评查项数量
     * @param params 查询条件
     * @return 案件集合
     */
    List<Map> getAjPcxCountByXtdm(Map params);


    /**
     * 获取所有评查项
     * @return
     */
    List<Map> getPcxLists(Map map);


    /**
     * 根据系统代码获取评查项备注说明
     * @param map
     * @return
     */
    List<Map> getPcxBzByXtdm(Map map);


    /**
     * 根据业务条线获取案件质量评查分析数据
     * @param map
     * @return
     */
    List<Map> getAjzlpcfxByYwtx(Map map);

    /**
     * 获取总体情况办结案件列表
     * @param map
     * @return
     */
    List<Map> getAJBjlb(Map map);
    Map getAJBjlbCount(Map map);
    /**
     * 获取总体情况评查案件列表
     * @param map
     * @return
     */
    List<Map> getAJPclb(Map map);
    Map getAJPclbCount(Map map);

    /**
     * 获取瑕疵、错误评查案件列表
     * @param map
     * @return
     */
    List<Map> getErrAJPclb(Map map);
    Map getErrAJPclbCount(Map map);

    /**
     * 获取评查案件信息
     * @param map
     * @return 案件信息列表
     */
    List<Map> getPcAjxxByParams(Map map);

    /**
     * 获取突出错误/瑕疵评查案件信息
     * @param map
     * @return 案件信息列表
     */
    List<Map> getTccwxxPcAjxxByParams(Map map);

    /**
     * 获取评查人、办结人总数
     * @param map
     * @return
     */
    Map loadGeneralPersonalNum(Map map);
    /**
     * 获取单位数
     * @param map
     * @return
     */
    List<Map> loadGeneralDwNum(Map map);
}
