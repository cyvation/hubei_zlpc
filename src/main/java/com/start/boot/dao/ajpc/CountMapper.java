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
public interface CountMapper {

    /**
     * 根据条件查询评查项分类数量
     * @param query
     * @return
     */
    List<Map> getPcxFlInfo(Map query);

    /**
     * 根据条件查询线下评查项分类数量
     * @param query
     * @return
     */
    List<Map> getOfflinePcxFlInfo(Map query);

    /**
     * 根据条件查询评查项分类数量
     * @param query
     * @return
     */
    List<Map> getJbxxCount(Map query);

    /**
     * 根据条件查询线下评查项分类数量
     * @param query
     * @return
     */
    Map getJbxxCountNum(Map query);

}
