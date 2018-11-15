package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxDcPdx;
import com.start.boot.query.PdxQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/11/13.
 */
@Repository
public interface PdxMapper {
    List<Map> getPdx(PdxQuery query);

    List<YxDcPdx> getSelectedPdx(@Param("pcslbm") String pcslbm);
}
