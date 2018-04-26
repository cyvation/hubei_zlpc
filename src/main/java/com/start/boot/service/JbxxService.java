package com.start.boot.service;

import com.start.boot.domain.YX_PC_JBXX;

import java.util.List;

/**
 * 基本信息service
 *
 * @caomin
 * @create 2017-12-05 14:21
 **/
public interface JbxxService {

    /**
     * 根据pcslbm获取基本信息
     * @param pcslbm
     * @return
     */
    YX_PC_JBXX getJbxx(String pcslbm);
}
