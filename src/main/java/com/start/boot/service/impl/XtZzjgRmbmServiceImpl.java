package com.start.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.start.boot.common.PageInfo;
import com.start.boot.dao.ajpc.XtZzjgRmbmMapper;
import com.start.boot.domain.XtZzjgRmbm;
import com.start.boot.domain.XtZzjgRmbmExample;
import com.start.boot.domain.XtQxRyJsfpExample;
import com.start.boot.domain.XtQxRyJsfp;
import com.start.boot.service.XtZzjgRmbmService;
import com.start.boot.dao.ajpc.XtQxRyJsfpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明
 */
@Service
public class XtZzjgRmbmServiceImpl implements XtZzjgRmbmService {

    @Autowired
    XtZzjgRmbmMapper xtZzjgRmbmMapper;

    @Autowired
    XtQxRyJsfpMapper xtQxRyJsfpMapper;

    @Override
    public List<XtZzjgRmbm> getDwRmTreeList(String dwbm) {
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        XtZzjgRmbmExample example = new XtZzjgRmbmExample();
        example.createCriteria().andDwbmEqualTo(dwbm).andSfscEqualTo("N");
        return xtZzjgRmbmMapper.selectByExample(example);
    }

    @Override
    public List<XtZzjgRmbm> getDwBmRmTreeList(String dwbm, String bmbm, PageInfo query) {

        if (StringUtils.isEmpty(dwbm)||StringUtils.isEmpty(bmbm)){
            return null;
        }
        XtQxRyJsfpExample example=new XtQxRyJsfpExample();
        example.createCriteria().andDwbmEqualTo(dwbm).andBmbmEqualTo(bmbm);
        List<XtQxRyJsfp> xtQxRyJsfps = xtQxRyJsfpMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(xtQxRyJsfps)){
            List<String> ghList = xtQxRyJsfps.stream().map(XtQxRyJsfp::getGh).distinct().collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(ghList)){
                XtZzjgRmbmExample ryExample = new XtZzjgRmbmExample();
                example.createCriteria().andDwbmEqualTo(dwbm).andGhIn(ghList);
                PageHelper.startPage(query.getPage(), query.getRows());
                return xtZzjgRmbmMapper.selectByExample(ryExample);
            }
        }
        return null;
    }

}
