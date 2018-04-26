package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.service.JbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @caomin
 * @create 2017-12-05 14:21
 **/
@Service
public class JbxxServiceImpl implements JbxxService {

    @Autowired
    YX_PC_JBXXMapper jbxxMapper;


    @Override
    public YX_PC_JBXX getJbxx(String pcslbm) {
      return   jbxxMapper.selectByPrimaryKey(pcslbm);
    }
}
