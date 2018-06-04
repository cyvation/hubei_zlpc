package com.start.boot.dao.ajpc;

import com.start.boot.domain.ChartNOGroup;
import com.start.boot.domain.ChartWithGroup;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.pojo.dto.*;
import com.start.boot.query.ShPcjbqkerQuery;
import com.start.boot.query.ShYPNAQuery;
import com.start.boot.query.YearProblemAnalyzeQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询mapper
 * Created by caomin on 2017/11/14
 */
@Repository
public interface QueryMapper {
    /**
     * 折线图 根据年份，评查方式。根据年月评查结果分组统计数量
     * @param year
     * @param pcflbm
     * @return
     */
    List<CountDo> query(@Param("year") Integer year, @Param("pcflbm") String pcflbm);

    /**
     * 折线图，统计每个月的评查数量。为了计算每个项占该月的百分比。
     * @param year
     * @param pcflbm
     * @return
     */
    List<MonthDo> querySum(@Param("year") Integer year, @Param("pcflbm") String pcflbm);


    /**
     * 折线图 查询评查结论不为空的所有结论类型
     * @return
     */
    List<String> getPljgType();


    /**
     * 按年份、评查分类编码统计、各个评论结果的数量
     * @param year
     * @param pcflbm
     * @return
     */
    List<CountDo> getYearPcjgCount(@Param("year") Integer year, @Param("pcflbm") String pcflbm);


    /**
     *折线图 统计一年的总是
     * @param year
     * @param pcflbm
     * @return
     */
    Integer getYearCountByYearAndPcflbm(@Param("year") Integer year, @Param("pcflbm") String pcflbm);

    /**
     * 饼 图，查询
     * @param pcxbmList
     * @param pcslbmList
     * @return
     */
    List<PcxDto>getPcyByPcxbm(@Param("pcxbmList") String pcxbmList, @Param("pcslbmList") String pcslbmList);



    List<JbxxDto> getPcslbmListByYearAndMonth(@Param("year") Integer year);

    List<String> getPcslbmListByYear(@Param("year") Integer year);


    /**
     * 案件质量折线图
     * @param year
     * @param pcflbm
     * @return
     */
    List<ChartWithGroup>  getAjzlLinesByYearAndPcflbm(@Param("year") Integer year, @Param("pcflbm") String pcflbm);


    /**
     * 案件质量饼图
     * @param year
     * @param pcflbm
     * @return
     */
    List<ChartNOGroup> getAjzlPieByYearAndPcflbm(@Param("year") Integer year, @Param("pcflbm") String pcflbm);

    void getJbpctotal(Map map);


    /**
     * 年度问题趋势
     * @param query
     * @return
     */
    List<YearProblemAnalyzeDto>shGetYearProblemAnalyze(YearProblemAnalyzeQuery query);


    /**
     * 年度问题趋势每月
     * @param query
     * @return
     */
    List<YearProblemAnalyzeDto>shGetYearProblemAnalyzeResult(YearProblemAnalyzeQuery query);

    /**
     *
     柱状图获取案件信息
     */
    List<YX_PC_JBXX> shGetYearProblemAnalyzeJbxx(YearProblemAnalyzeQuery query);



    /**
     * 评查基本情况2，获取年月。评查未完成的
     * @param query
     * @return
     */
   List<ShPcjbqkerMothDto> shPcjbqkerGetYearMonthPcjlIsNull(ShPcjbqkerQuery query);



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
   List<ShPcjbqkerDto> shPcjbqkerGetPcslbmPcjlIsNotNull(ShPcjbqkerQuery query);
    /**
     * 评查基本情况2   获取评查未完成的，评查受理编码
     * @param query
     * @return
     */
   List<ShPcjbqkerDto> shPcjbqkerGetPcslbmPcjlIsNull(ShPcjbqkerQuery query);


    /**
     * 年度问题性质分布图，获取pcslbm和bmsah
     * @param query
     * @return
     */
    List<PcxDto>  shYearProlbemNatureAnalyze(ShYPNAQuery query);

    /**
     *  根据条件查询案件列表，饼图"
     * @param query
     * @return
     */
    List<YX_PC_JBXX>getAjjbxxYearProlbemNatureAnalyze(ShYPNAQuery query);
   /**
     * 气泡图获取  已评查数据 从 yx_pc_jbxx获取
     * @param query
     * @return
     */
    List<YX_PC_JBXX>shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(ShPcjbqkerQuery query);

    /**
     * 气泡图获取  未评查数据 YX_PC_SXJL sx 获取
     * @param query
     * @return
     */
    List<YX_PC_JBXX>shPcjbqkerGetPcslbmPcjlIsNotNullSxjl(ShPcjbqkerQuery query);

    /**
     * 获取地图数据
     * @param
     * @param
     * @return
     */
    Integer shGetMapJbqk(ShYPNAQuery query);



    /**
     * 获取地图数据
     * @param
     * @param
     * @return
     */
    Integer getMapVaulePcflbm (ShYPNAQuery query);


    /**
     * 检察官办案质量排名
     * @param query
     * @return
     */
    List<PersonPaimin>getPersonPaiMinByPcjlAndRQ(ShYPNAQuery query);
    /**
     * 检察官办案质量排名获取案件详情
     * @param query
     * @return
     */
    List<YX_PC_JBXX>getPersonPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query);

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
     * 获取未评查数量
     * @param
     * @return
     */
    Integer getWpc( ShYPNAQuery query);

    /**
     * 获取已评查数量
     * @param
     * @return
     */
    Integer getYpc(ShYPNAQuery query);

    /**
     * 获取排名
     * @param
     * @return
     */
    List<PmDto>getPm(ShYPNAQuery query);

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



    List<YX_PC_JBXX>getMapAjJbxxJbqk(ShYPNAQuery query);

    List<DwPaimin> getDwPaiMinByPcjlAndRQ(ShYPNAQuery query);

    List<YX_PC_JBXX> getDWPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query);

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
