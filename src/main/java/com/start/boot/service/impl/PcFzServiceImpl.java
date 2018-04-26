package com.start.boot.service.impl;


import com.start.boot.dao.ajpc.YX_PC_FZMapper;
import com.start.boot.domain.YX_PC_FZ;
import com.start.boot.domain.YX_PC_FZExample;
import com.start.boot.service.PcAjService;
import com.start.boot.service.PcFzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caomin on 2017/11/8.
 */
@Service
public class PcFzServiceImpl implements PcFzService {
    @Autowired
    YX_PC_FZMapper yx_pc_fzMapper;


    @Autowired
    PcAjService pcAjService;


    @Override
    public List<YX_PC_FZ> getListByPchdbmAndPczbm(String pchdbm, String pczbm) {
        YX_PC_FZExample yx_pc_fzExample = new YX_PC_FZExample();
        yx_pc_fzExample.createCriteria().andPchdbmEqualTo(pchdbm).andPczbmEqualTo(pchdbm);
        return  yx_pc_fzMapper.selectByExample(yx_pc_fzExample);
    }


    @Override
    public List<YX_PC_FZ> getListByPchdbm(String pchdbm) {
        YX_PC_FZExample yx_pc_fzExample = new YX_PC_FZExample();
        yx_pc_fzExample.createCriteria().andPchdbmEqualTo(pchdbm);
        return  yx_pc_fzMapper.selectByExample(yx_pc_fzExample);
    }
}
