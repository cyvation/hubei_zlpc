package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtZzjgDwbm;
import com.start.boot.domain.Yx_Pc_PcxFl;
import com.start.boot.query.QueryTableAjJbxx;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface QueryAllMapper {

    /**
     * 根据条件查询评查项分类数量
     * @param query
     * @return
     */
    int getPcxFlCount(Map query);

    /**
     * 获取审结数量
     * @param query
     * @return
     */
    int getSjCount(Map query);



    List<Yx_Pc_PcxFl>getPcxFlbmList(@Param("fbmmc") String fbmmc, @Param("zbmmc") String zbmmc);


    /**
     * 根据条件查询线下评查项分类数量
     * @param query
     * @return
     */
    int getOfflinePcxFlCount(Map query);

    List<XtZzjgDwbm>   getDwbmTreeLists(@Param("dwbm") String dwbm);

    List<XtZzjgDwbm>   getDwbmTreeList(@Param("dwbm") String[] dwbm);

    /**
     * 根据条件查询线下评查项分类数量
     * @param query
     * @return
     */
    int getPcxFlCountOffline(Map query);


    List<Yx_Pc_PcxFl>getPcxFlOfflinebmList(@Param("fbmmc") String fbmmc, @Param("zbmmc") String zbmmc);

    /**
     * 根据查询条件获取 ajjbxx
     * @param query
     * @return
     */
    List<Map> getAjjbxx(QueryTableAjJbxx query);
    /**
     * 根据查询线下条件获取 ajjbxx
     * @param query
     * @return
     */
    List<Map> getAjjbxxOffline(QueryTableAjJbxx query);
}
