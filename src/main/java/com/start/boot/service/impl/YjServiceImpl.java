package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.YjMapper;
import com.start.boot.domain.Yj;
import com.start.boot.domain.YjExample;
import com.start.boot.service.YjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author caomin
 * @date 2018/3/23
 * @说明
 */
@Service
public class YjServiceImpl implements YjService {

    @Autowired
    YjMapper yjMapper;

    @Override
    public boolean hasCbrFkCount(String pcslbm) {

        YjExample yjExample = new YjExample();
        yjExample.createCriteria().andPcyjlxEqualTo("2").andPcslbmEqualTo(pcslbm).andSfscEqualTo("N");
        List<Yj> yjs = yjMapper.selectByExample(yjExample);
        if (CollectionUtils.isEmpty(yjs)){
            return false;
        }
        return true;
    }

    @Override
    public List<Yj> getYjList(String pcslbm) {
        YjExample yjExample = new YjExample();
        yjExample.createCriteria().andPcslbmEqualTo(pcslbm).andSfscEqualTo("N");
        yjExample.setOrderByClause("ZHXGSJ DESC");
        List<Yj> yjs = yjMapper.selectByExample(yjExample);
        return yjs;
    }
}
