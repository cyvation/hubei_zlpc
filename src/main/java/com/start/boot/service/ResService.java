package com.start.boot.service;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.Param_Tzgg;
import com.start.boot.domain.Tzgg;

/**
 * Created by lei on 2017/10/31.
 */
public interface ResService {

    Param_Pager selectTzgg( Param_Tzgg param) throws Exception;

    boolean updTzgg( Tzgg param) throws Exception;

    boolean delTzgg(String bh) throws Exception;

    boolean addTzgg( Tzgg param) throws Exception;
}
