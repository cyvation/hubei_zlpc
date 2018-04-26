package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.XtPcLbMapper;
import com.start.boot.domain.XtPcLb;
import com.start.boot.domain.XtPcLbExample;
import com.start.boot.service.XtPcLbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明
 */
@Service
public class XtPcLbServiceImpl implements XtPcLbService {

    @Autowired
    XtPcLbMapper xtPcLbMapper;

    @Override
    public List<XtPcLb> getAllXtPcLb() {
        XtPcLbExample example = new XtPcLbExample();
        example.createCriteria().andSfqyEqualTo("Y");
        return xtPcLbMapper.selectByExample(example);
    }
}
