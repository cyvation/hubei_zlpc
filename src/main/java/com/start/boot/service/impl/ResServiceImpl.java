package com.start.boot.service.impl;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.ResMapper;
import com.start.boot.domain.Param_Tzgg;
import com.start.boot.domain.Tzgg;
import com.start.boot.service.ResService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lei on 2017/10/31.
 */
@Service
@Transactional
public class ResServiceImpl  implements ResService {

    @Autowired
    private ResMapper resMapper;

    @Override
    public Param_Pager selectTzgg(Param_Tzgg param) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", param.getDwbm());
        map.put("p_lxdm", "");
        map.put("p_ppxx", param.getKeyWord());
        map.put("p_fbsjbeg", OracleTimeUtils.format(param.getFBSJBeg()));
        map.put("p_fbsjend", OracleTimeUtils.format(param.getFBSJEnd()));
        map.put("p_pagesize", param.getRows());
        map.put("p_pageindex", param.getPage());

        resMapper.getTzgg(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        param.setCount(DataAccessHelper.getInteger(map, "p_count"));
        param.setList(DataAccessHelper.getListMap(map, "p_cursor"));

        return  param;
    }

    @Override
    public boolean updTzgg(Tzgg param) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_bh", param.getBh());
        map.put("p_bt", param.getBt());
        map.put("p_nr", param.getNr());
        map.put("p_ghsj", OracleTimeUtils.format(param.getGhsj()));
        map.put("p_sfjykj", param.getSFJYKJ());

        resMapper.updTzgg(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return  true;
    }

    @Override
    public boolean delTzgg(String bh) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_bh", bh);

        resMapper.delTzgg(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return  true;
    }

    @Override
    public boolean addTzgg(Tzgg param) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_fbrdwbm",param.getFbrdwbm());//单位编码
        map.put("p_fbrdwmc", param.getFbrdwmc());//单位名称
        map.put("p_fbrgh", param.getFbrgh());//工号
        map.put("p_fbrxm", param.getFbrxm());//姓名
        map.put("p_lxdm", "");//类型编码
        map.put("p_lxmc", "");//类型名称
        map.put("p_bt", param.getBt());//标题
        map.put("p_nr", param.getNr());//内容
        map.put("p_ghsj", OracleTimeUtils.format(param.getGhsj()));//公告时间
        map.put("p_sfjykj", param.getSFJYKJ());//是否下级院可见

        resMapper.addTzgg(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return  true;
    }
}
