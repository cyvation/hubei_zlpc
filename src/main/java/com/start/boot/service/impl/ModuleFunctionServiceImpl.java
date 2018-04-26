package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.ModuleFunctionMapper;
import com.start.boot.domain.Function;
import com.start.boot.domain.FunctionFl;
import com.start.boot.service.ModuleFunctionService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cj on 2018/1/24.
 */

@Service
public class ModuleFunctionServiceImpl implements ModuleFunctionService {

    @Autowired
    private ModuleFunctionMapper moduleFunctionMapper;

    //获取功能定义树
    @Override
    public Map GetGnflCombTree(String dwbm) throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);

        moduleFunctionMapper.GetGnflCombTree(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }

    //新增/更新功能分类
    @Override
    public String addOrUpdateGnfl(FunctionFl functionFl)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_flbm",functionFl.getFlbm());
        map.put("p_flmc",functionFl.getFlmc());
        map.put("p_fflbm",functionFl.getFflbm());
        map.put("p_flxh",functionFl.getFlxh());
        map.put("p_dwbm",functionFl.getDwbm());
        map.put("p_iconcls",functionFl.getIcon());

        moduleFunctionMapper.addOrUpdateGnfl(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }

    //获取功能分类详情
    @Override
    public Map GetGnflInfo(String dwbm, String flbm)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);
        map.put("p_flbm",flbm);

        moduleFunctionMapper.GetGnflInfo(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }

    //删除功能分类信息
    @Override
    public Map DeleteGnfl(String dwbm, String flbm)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);
        map.put("p_flbm",flbm);

        moduleFunctionMapper.DeleteGnfl(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }

    //获取功能定义列表
    @Override
    public Map GetGndyList(Function function, int page, int rows)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm", function.getDwbm());
        map.put("p_flbm", function.getFlbm());
        map.put("p_pagesize",rows);
        map.put("p_pageindex",page);

        moduleFunctionMapper.GetGndyList(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }

    //新增/更新功能定义
    @Override
    public String AddGndy(Function function)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm", function.getDwbm());
        map.put("p_flbm", function.getFlbm());
        map.put("p_gnbm", function.getGnbm());
        map.put("p_gnmc", function.getGnmc());
        map.put("p_gnct", function.getGnct());
        map.put("p_gnxsmc", function.getGnxsmc());
        map.put("p_gnxh", function.getGnxh());
        map.put("p_gnsm", function.getGnsm());
        map.put("p_gncxj", function.getGncxj());
        map.put("p_gncs", function.getGncs());
        map.put("p_cscs", function.getCscs());

        moduleFunctionMapper.AddGndy(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }

    //删除功能定义信息
    @Override
    public String DeleteGndy(String dwbm, String gnbm)  throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm", dwbm);
        map.put("p_gnbm", gnbm);

        moduleFunctionMapper.DeleteGndy(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }
}
