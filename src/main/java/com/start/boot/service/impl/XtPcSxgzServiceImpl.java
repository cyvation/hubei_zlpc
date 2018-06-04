package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.XtPcSxgzMapper;
import com.start.boot.domain.XtPcSxgz;
import com.start.boot.domain.XtPcSxgzExample;
import com.start.boot.service.XtPcSxgzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author caomin
 * @date 2018/3/27
 * @说明
 */
@Service
public class XtPcSxgzServiceImpl implements XtPcSxgzService {

    @Autowired
    XtPcSxgzMapper xtPcSxgzMapper;

    @Autowired
    XtPcSxgzMapper sxgzMapper;

    @Override
    public List<XtPcSxgz> getAll() {
      return   xtPcSxgzMapper.selectByExample(new XtPcSxgzExample());
    }

    @Override
    public List<XtPcSxgz> getSxgz(String pcflbm) {
        if (StringUtils.isEmpty(pcflbm)){
            return null;
        }
        XtPcSxgzExample xtPcSxgzExample = new XtPcSxgzExample();
        xtPcSxgzExample.createCriteria().andPcflbmEqualTo(pcflbm);
        xtPcSxgzExample.setOrderByClause("xh asc");
        return sxgzMapper.selectByExample(xtPcSxgzExample);

    }
}
