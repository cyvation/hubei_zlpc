package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.XtZzjgBmbmMapper;
import com.start.boot.domain.XtZzjgBmbm;
import com.start.boot.domain.XtZzjgBmbmExample;
import com.start.boot.service.XtZzjgBmbmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明
 */
@Service
public class XtZzjgBmbmServiceImpl implements XtZzjgBmbmService {

    @Autowired
    XtZzjgBmbmMapper xtZzjgBmbmMapper;


    @Override
    public List<XtZzjgBmbm> getDwbmTreeList(String dwbm) {
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        XtZzjgBmbmExample example = new XtZzjgBmbmExample();
        example.createCriteria().andDwbmEqualTo(dwbm).andSfscEqualTo("N");
        return xtZzjgBmbmMapper.selectByExample(example);
    }

    @Override
    public List<XtZzjgBmbm> getbmbmTreeList(String dwbm) {
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        XtZzjgBmbmExample example = new XtZzjgBmbmExample();
        example.createCriteria().andDwbmEqualTo(dwbm).andSfscEqualTo("N");
        return xtZzjgBmbmMapper.selectByExample(example);
    }
}
