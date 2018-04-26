package com.start.boot.service;


import com.start.boot.domain.Param_Pcaj;
import com.start.boot.domain.YX_PC_JBXX;

import java.util.List;
import java.util.Map;

/**
 * 评查案件基本信息
 * Created by caomin on 2017/11/8.
 */
public interface PcAjService {


    List<YX_PC_JBXX> getByPchdbw(String pchdbw);

    Param_Pcaj getPcaj(Param_Pcaj pcajParam) throws Exception;

    boolean delPcaj(String pcslbm,String pcflbm) throws Exception;

    List<Map> getPcz(String pchdbm, String pcflbm) throws Exception;

    List<Map> getPczry(String pczbm) throws Exception;

    boolean updPcr(YX_PC_JBXX jxbbParam) throws Exception;
}
