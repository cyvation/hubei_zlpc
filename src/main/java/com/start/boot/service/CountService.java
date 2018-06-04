package com.start.boot.service;


import com.start.boot.domain.ChartNOGroup;
import com.start.boot.domain.ChartWithGroup;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.dto.*;
import com.start.boot.pojo.vo.CountVo;
import com.start.boot.pojo.vo.PcjbGkVo;
import com.start.boot.pojo.vo.PcxVo;
import com.start.boot.query.ShPcjbqkerQuery;
import com.start.boot.query.ShYPNAQuery;
import com.start.boot.query.YearProblemAnalyzeQuery;

import java.util.List;
import java.util.Map;

/**
 * 统计服务
 */

public interface CountService {


   Map<Integer, List<CountDo>> getPcByYear(Integer year, String pcflbm);


   /**
    * 统计本年度 每种结论的占比情况
    * @param year
    * @param pcflbm
    * @return
    */
   List<CountVo> getYearPercentByYearAndPcflbm(Integer year, String pcflbm);


   /**
    * 根据评查结论、年份、模板编码查询该结论下top的小评查项数据
    * @param pcflbm
    * @param year
    * @param
    * @return
    */
  List<PcxVo> getPcxFlByYearAndMbbmAndPcjg(String pcflbm, Integer year) throws Exception;


    /**
     * 小问题分类的饼图
     * @param pcflbm
     * @param year
     * @param
     * @return
     */
    List<PcxVo> getPcxFlByYearAndBinTu(String pcflbm, Integer year, String pcjg) throws Exception;


    /**
     * 案件质量折线图
     * @param pcflbm
     * @param year
     * @param
     * @return
     */
    List<ChartWithGroup> getAjzlLinesByYearAndPcflbm(Integer year,String pcflbm) throws Exception;

    /**
     * 案件质量饼图
     * @param pcflbm
     * @param year
     * @param
     * @return
     */
    List<ChartNOGroup> getAjzlPieByYearAndPcflbm(Integer year, String pcflbm) throws Exception;







    /**
     * 评查基本情况2，获取年月。评查未完成的
     * @param query
     * @return
     */
    List<ShPcjbqkerMothDto>shPcjbqkerGetYearMonthPcjlIsNull(ShPcjbqkerQuery query);

    /**
     * 评查基本情况2，获取年月。评查已经完成的
     * @param query
     * @return
     */
    List<ShPcjbqkerMothDto> shPcjbqkerGetYearMonthPcjlIsNotNull(ShPcjbqkerQuery query);
    /**
     * 评查基本情况2，获取评查完成的，评查受理编码
     * @param query
     * @return
     */
    List<ShPcjbqkerDto > shPcjbqkerGetPcslbmPcjlIsNotNull(ShPcjbqkerQuery query);
    /**
     * 评查基本情况2   获取评查未完成的，评查受理编码
     * @param query
     * @return
     */
    List<ShPcjbqkerDto > shPcjbqkerGetPcslbmPcjlIsNull(ShPcjbqkerQuery query);


    /**
     * 年度问题性质分布图，
     * @param query
     * @return
     */

    List<PcxDto> shYearProlbemNatureAnalyze(ShYPNAQuery query);
    /**
     * 饼图根据条件查询案件列表
     * @param query
     * @return
     */
    List<YX_PC_JBXX>getAjjbxxYearProlbemNatureAnalyze(ShYPNAQuery query);

    /**
     *柱状较长查询案件列表
     * @param query
     * @return
     */
    List<YX_PC_JBXX> shGetYearProblemAnalyzeJbxx(YearProblemAnalyzeQuery query);

  /**
   * 气泡图获取  已评查数据 从 yx_pc_jbxx获取
   * @param query
   * @return
   */
    List<YX_PC_JBXX> shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(ShPcjbqkerQuery query);



  /**
   * 气泡图获取  未评查数据 YX_PC_SXJL sx 获取
   * @param query
   * @return
   */
  List<YX_PC_JBXX>shPcjbqkerGetPcslbmPcjlIsNotNullSxjl(ShPcjbqkerQuery query);


  /**
     * 地图数据,评查基本情况
     * @return
     */
    List shGetMapJbqk(ShYPNAQuery query);

    /**
     * 地图数据,案件评查质量情况
     * @return
     */
    List shGetMapAjZlqk(ShYPNAQuery query);


  /**
   * 检察官办案质量排名
   * @param query
   * @return
   */
  List<PersonPaimin>getPersonPaiMinByPcjlAndRQ(ShYPNAQuery query);
  /**
   * 检察官办案质量排名,获取案件信息
   * @param query
   * @return
   */
  List<YX_PC_JBXX> getPersonPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query);


  /**
   * 部门办案质量排名
   * @param query
   * @return
   */
  List<BmZlPm>bmBanAjZhiLiangPaiMin(ShYPNAQuery query);


    /**
     * 部门办案质量排名获取案件基本信息
     * @param query
     * @return
     */
    List<YX_PC_JBXX>bmBanAjZhiLiangPaiMinAjJbxx(ShYPNAQuery query);

    /**
     * 获取排名
     * @param
     * @return
     */
    List<PmDto>getPm( ShYPNAQuery query);


  /**
   * 获取某个 单位下，所有子单位 的在某个时间段内。评查数，与审结数的散点图
   * @param query
   */
  List<PcjbGkVo>  getPcjbGk(ShYPNAQuery query);

  /**
   * 获取排名 的案件基本信息
   * @param query
   * @return
   */
  List<YX_PC_JBXX>getPmAjJbxx(ShYPNAQuery query);


  /**
   * 地图获取案件信息
   * @param query
   * @return
   */
  List<YX_PC_JBXX>getMapAjJbxx(ShYPNAQuery query);
  /**
   * 地图获取案件信息
   * @param query
   * @return
   */
  List<YX_PC_JBXX>getMapAjJbxxJbqk(ShYPNAQuery query);


  /**
   * 获取单位 排名
   * @param query
   * @return
   */
  List<DwPaimin> getDwPaiMinByPcjlAndRQ(ShYPNAQuery query);

  /**
   * 获取单位下案件信息
   * @param query
   * @return
   */
  List<YX_PC_JBXX> getDWPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query);

    /**
     * 地图获取案件信息
     * @param query
     * @return
     */
    Map countPcqkOrBaqk(Map query);
    /**
     * 地图获取案件信息
     * @param query
     * @return
     */
    List<Map>loadPcInfo(Map query);

    /**
     * 业务条线办案质量排名
     * @param query
     * @return
     */
    List<YwtxPm> ywtxAjZhiLiangPaiMin(ShYPNAQuery query);


    /**
     * 业务条线办案质量排名获取案件基本信息
     * @param query
     * @return
     */
    List<YX_PC_JBXX> ywtxAjZhiLiangPaiMinAjJbxx(ShYPNAQuery query);

}
