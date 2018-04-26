package com.start.boot.service;

import com.start.boot.domain.SystemCoreConfig;

/**
 * 系统配置
 *
 * @caomin
 * @create 2017-12-11 17:07
 **/
public interface SystemCoreConfigService {

    /**
     * 根据配置key获取 一项系统配置
     * @param key
     * @return
     */
    SystemCoreConfig getSystemCoreConfigByKey(String key);

    /**
     * 根据key获取配置的值
     * @param key
     * @return
     */
    String getSystemConfigValue(String key);
}
