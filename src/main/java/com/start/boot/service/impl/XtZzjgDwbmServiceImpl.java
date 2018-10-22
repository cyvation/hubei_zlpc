package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.XtZzjgDwbmMapper;
import com.start.boot.domain.XtZzjgDwbm;
import com.start.boot.service.XtZzjgDwbmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明
 */
@Service
public class XtZzjgDwbmServiceImpl implements XtZzjgDwbmService {

    @Autowired
    XtZzjgDwbmMapper xtZzjgDwbmMapper;


    @Override
    public List<XtZzjgDwbm> getDwbmTreeList(String dwbm) {
        if (StringUtils.isEmpty(dwbm)){
            throw new RuntimeException("单位编码为空");
        }
         return   xtZzjgDwbmMapper.getDwbmTreeList(dwbm);
    }

    @Override
    public List<Map> getSibligDwbm(String dwbm) {
        if (StringUtils.isEmpty(dwbm)){
            throw new RuntimeException("单位编码为空");
        }
        return   xtZzjgDwbmMapper.getSibligDwbm(dwbm);
    }
}
