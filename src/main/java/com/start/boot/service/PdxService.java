package com.start.boot.service;

import com.start.boot.domain.YxDcPdx;
import com.start.boot.query.PdxQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/11/13.
 */
public interface PdxService {
    List<Map> getPdx(PdxQuery query);

    void savePdx(String pcslbm, List<YxDcPdx> yxDcPdxes);

    String generatePdxDoc(String pcslbm) throws Exception;
}
