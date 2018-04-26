package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.XtDmFldmMapper;
import com.start.boot.domain.XtDmFldm;
import com.start.boot.domain.XtDmFldmExample;
import com.start.boot.service.XtDmFldmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/21
 * @说明
 */
@Service
public class XtDmFldmServiceImpl implements XtDmFldmService {
    @Autowired
    XtDmFldmMapper xtDmFldmMapper;

    @Override
    public List<XtDmFldm> getXtDmFldmBy(String lbbm) {
        if (StringUtils.isEmpty(lbbm)){
            return null;
        }
        XtDmFldmExample xtDmFldmExample = new XtDmFldmExample();
        xtDmFldmExample.createCriteria().andLbbmEqualTo(lbbm).andSfscEqualTo("N");
        return   xtDmFldmMapper.selectByExample(xtDmFldmExample);

    }
}
