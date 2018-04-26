package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.YxPcZdPcMapper;
import com.start.boot.domain.YxPcZdPc;
import com.start.boot.domain.YxPcZdPcKey;
import com.start.boot.service.YxPcZdpcxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author caomin
 * @date 2018/1/21
 * @说明
 */
@Service
public class YxPcZdpcxServiceImpl implements YxPcZdpcxService {

    @Autowired
    YxPcZdPcMapper yxPcZdPcMapper;



    @Override
    public YxPcZdPc save(YxPcZdPc yxPcZdPc) {
        yxPcZdPcMapper.insertSelective(yxPcZdPc);
        return yxPcZdPc;
    }

    @Override
    public YxPcZdPc getYxPcZdPcByKey(String pcxbm, String pcslbm) {
        if (StringUtils.isEmpty(pcxbm)||StringUtils.isEmpty(pcslbm)){
            return null;
        }
        YxPcZdPcKey yxPcZdPcKey = new YxPcZdPcKey();
        yxPcZdPcKey.setPcslBm(pcslbm);
        yxPcZdPcKey.setPcxBm(pcxbm);
        return   yxPcZdPcMapper.selectByPrimaryKey(yxPcZdPcKey);
    }
}
