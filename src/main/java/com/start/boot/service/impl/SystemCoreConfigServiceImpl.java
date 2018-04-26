package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.SystemCoreConfigMapper;
import com.start.boot.domain.SystemCoreConfig;
import com.start.boot.domain.SystemCoreConfigExample;
import com.start.boot.service.SystemCoreConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @caomin
 * @create 2017-12-11 17:07
 **/
@Service
public class SystemCoreConfigServiceImpl implements SystemCoreConfigService {

    @Autowired
    SystemCoreConfigMapper systemCoreConfigMapper;

    @Override
    public SystemCoreConfig getSystemCoreConfigByKey(String key) {
        SystemCoreConfigExample systemCoreConfigExample = new SystemCoreConfigExample();
        systemCoreConfigExample.createCriteria().andKeyEqualTo(key).andOpenEqualTo("1");

        List<SystemCoreConfig> systemCoreConfigs = systemCoreConfigMapper.selectByExample(systemCoreConfigExample);
        if (!CollectionUtils.isEmpty(systemCoreConfigs)){
            return systemCoreConfigs.get(0);
        }
        return null;
    }

    @Override
    public String getSystemConfigValue(String key) {
        SystemCoreConfigExample systemCoreConfigExample = new SystemCoreConfigExample();
        systemCoreConfigExample.createCriteria().andKeyEqualTo(key).andOpenEqualTo("1");

        List<SystemCoreConfig> systemCoreConfigs = systemCoreConfigMapper.selectByExample(systemCoreConfigExample);
        if (!CollectionUtils.isEmpty(systemCoreConfigs)){
            return systemCoreConfigs.get(0).getValue();
        }
        return null;
    }
}
