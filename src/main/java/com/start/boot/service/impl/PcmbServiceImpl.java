package com.start.boot.service.impl;


import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.PcMapper;
import com.start.boot.dao.ajpc.PcmbMapper;
import com.start.boot.dao.ajpc.PcyMapper;
import com.start.boot.dao.ajpc.YX_PC_HDMapper;
import com.start.boot.domain.*;
import com.start.boot.service.PcAjService;
import com.start.boot.service.PcFzService;
import com.start.boot.service.PcmbService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Service
public class PcmbServiceImpl implements PcmbService {

    @Autowired
    private PcmbMapper pcmbMapper;
    @Autowired
    YX_PC_HDMapper yx_pc_hdMapper;


    @Autowired
    PcyMapper pcyMapper;

    @Autowired
    PcAjService pcAjService;


    @Autowired
    PcFzService pcFzService;

    @Autowired
    PcMapper pcMapper;

    @Override
    public List<Map> getPcmb(String pcflbm) throws Exception {
        String errMsg ="";

        Map map = new HashMap();
        map.put("p_dwbm", SystemConfiguration.djdwbm);
        map.put("p_pcflbm",pcflbm);

        // 操作数据库
        pcmbMapper.getPcmb(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)){
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map,"p_cursor");

    }

    @Override
    public List<Map> getPcmbByPcmbbm(Map<String, Object> params) throws Exception {
        List<Map> list = null;
        try {
            list = pcmbMapper.getPcmbByPcmbbm(params);
        }catch (Exception exception){
            throw exception;
        }
        return list;
    }

    @Override
    public List<YX_PC_HD> query(YX_PC_HD param, Date cjsjStart, Date cjsjend) {
        YX_PC_HDExample example = new YX_PC_HDExample();
        YX_PC_HDExample.Criteria criteria = example.createCriteria();
        criteria.andSfscEqualTo("N");
        //湖北:添加评查活动过滤随机常规活动
        criteria.andSfhdxsEqualTo("Y");
        criteria.andPcdwbmEqualTo(param.getPCDWBM());
        if (!StringUtils.isEmpty(param.getPCFLBM())) {
            criteria.andPcflbmEqualTo(param.getPCFLBM());
        }
        if (cjsjStart != null) {
            criteria.andCjsjGreaterThanOrEqualTo(cjsjStart);
        }
        if (cjsjend != null) {
            criteria.andCjsjLessThanOrEqualTo(cjsjend);
        }
        example.setOrderByClause("CJSJ");
        return yx_pc_hdMapper.selectByExample(example);
    }

    @Override
    public Yx_Pc_Param_Pcfa getPchdInfo(String pchdbm) throws Exception {
        Yx_Pc_Param_Pcfa param_pcfa = new Yx_Pc_Param_Pcfa();
        //查询评查活动编码
        YX_PC_HDExample example = new YX_PC_HDExample();
        example.createCriteria().andPchdbmEqualTo(pchdbm);
        List<YX_PC_HD> yx_pc_hds = yx_pc_hdMapper.selectByExample(example);
        if (!yx_pc_hds.isEmpty()){
            BeanUtils.copyProperties(yx_pc_hds.get(0),param_pcfa);
        }

        List<YX_PC_JBXX> jbxx = pcAjService.getByPchdbw(pchdbm);
        //设置案件信息
        param_pcfa.setPCAJLB(jbxx);
        //查询分组
        List<YX_PC_FZ> pczlist = pcFzService.getListByPchdbm(pchdbm);
        //查询评查员
        pczlist.stream().forEach(fz->{
            List<Param_Pcy> pcyList = pcyMapper.getPcyByPchdbmAndpchdbm(pchdbm, fz.getPCZBM());
            fz.setPCYARR(pcyList);
        });
        param_pcfa.setPCZLB(pczlist);

        // 获取评查方案文书路径
        Map map = new HashMap();
        map.put("p_pchdbm", pchdbm);
        map.put("p_pcflbm","");
        // 操作数据库
        pcMapper.getPcfaws(map);
        String errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)){
            throw new Exception(errMsg);
        }
        param_pcfa.setPCFALJ(DataAccessHelper.getString(map, "p_pcfalj"));

        return param_pcfa;
    }

    @Override
    public List<Map> getPcyInfoList(String pchdbm) {
        return pcyMapper.getPcyInfo(pchdbm);
    }
}
