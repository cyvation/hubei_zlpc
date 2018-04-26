package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.WsmbMapper;
import com.start.boot.domain.Wsmb;
import com.start.boot.domain.WsmbExample;
import com.start.boot.service.WsmbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WsmbServiceImpl implements WsmbService {

    @Autowired
    WsmbMapper wsmbMapper;

    @Override
    public List<Wsmb> getWsbmList(String pcflbm, String wsmblb) {
        WsmbExample wsmbExample = new WsmbExample();
        WsmbExample.Criteria criteria = wsmbExample.createCriteria();
        criteria.andPcflbmEqualTo(pcflbm);
        if (StringUtils.isNotEmpty(wsmblb)){
            criteria.andWsmblbEqualTo(wsmblb);
        }
        wsmbExample.setOrderByClause("WSPX");
        return wsmbMapper.selectByExample(wsmbExample);
    }
}
