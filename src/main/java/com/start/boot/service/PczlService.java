package com.start.boot.service;

import com.start.boot.domain.YX_PC_PCZL;
import com.start.boot.query.PczlQuery;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/1/27.
 */
public interface PczlService {

    YX_PC_PCZL insertPczl(YX_PC_PCZL pczl);

    List<Map> getPczl(PczlQuery pczlQuery) throws ParseException;

    boolean delPczl(String bh);

    YX_PC_PCZL updatePczl(YX_PC_PCZL pczl);

    YX_PC_PCZL getPczlByBh(String bh);

    YX_PC_PCZL savePczl(YX_PC_PCZL pczl);
}
