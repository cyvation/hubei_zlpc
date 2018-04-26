package com.start.boot.dao.ajpc;

import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.Yx_Pc_PcxFl;
import com.start.boot.query.QueryTable;
import com.start.boot.query.QueryTableAjJbxx;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface QueryTableMapper {

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
     * 获取审结 案件基本信息
     * @param query
     * @return
     */
    List<Map> getSjCountAjjbxx(QueryTableAjJbxx query);

    /**
     * 根据查询条件获取 ajjbxx
     * @param query
     * @return
     */
    List<Map> getAjjbxx(QueryTableAjJbxx query);


    List<Yx_Pc_PcxFl>getPcxFlbmList(@Param("fbmmc")String fbmmc,@Param("zbmmc")String zbmmc);
}
