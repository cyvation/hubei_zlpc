package com.start.boot.service;


import com.start.boot.domain.YX_PC_HD;
import com.start.boot.domain.Yx_Pc_Param_Pcfa;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
public interface PcmbService {

    List<Map> getPcmb(String pcflbm) throws Exception;

    List<Map> getPcmbByPcmbbm(Map<String, Object> params) throws Exception;

    List<YX_PC_HD>query(YX_PC_HD param, Date cjsjStart, Date cjsjend);

    Yx_Pc_Param_Pcfa getPchdInfo(String pchdbm) throws Exception;

    List<Map>getPcyInfoList(String pchdbm);



}
