package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.UserMapper;
import com.start.boot.domain.Rybm;
import com.start.boot.service.UserService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/16.
 * 用户逻辑层：
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 获取单位树
    @Override
    public List<Map> GetDwbmCombTree(String dwbm) throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);

        userMapper.GetDwbmCombTree(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        List<Map> list = DataAccessHelper.getListMap(map, "p_cursor");

        return list;
    }

    // 获取统一业务新增人员信息列表
    @Override
    public Map TyywNewUserList(String dwbm, String mc, int rows, int page) throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);
        map.put("p_mc",mc);
        map.put("p_pagesize",rows);
        map.put("p_pageindex",page);

        userMapper.TyywNewUserList(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }

    // 获取人员信息列表
    @Override
    public Map GetUserList(String dwbm, String gh, String mc, int rows, int page) throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);
        map.put("p_gh",gh);
        map.put("p_mc",mc);
        map.put("p_pagesize",rows);
        map.put("p_pageindex",page);

        userMapper.GetUserList(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }


    // 添加/更新人员信息
    @Override
    @Transactional
    public String addOrUpdateRybm(Rybm rybm) throws Exception {

        String errMsg = "";

        Map<String,String> map = new HashMap<>();
        map.put("action",rybm.getAction());
        map.put("p_mc",rybm.getMc());
        map.put("p_dlbm",rybm.getDlbm());
        map.put("p_gzzh",rybm.getGzzh());
        map.put("p_xb",rybm.getXb());
        map.put("p_sflsry",rybm.getSflsry());
        map.put("p_yddhhm",rybm.getYddhhm());
        map.put("p_dzyj",rybm.getDzyj());
        map.put("p_caid",rybm.getCaid());
        map.put("p_gh",rybm.getGh());
        map.put("p_dwbm",rybm.getDwbm());
        map.put("p_xdwbm",rybm.getXdwbm());

        //与数据库统一
        map.put("p_kl","");
        map.put("p_ydwbm","");
        map.put("p_ydwmc","");
        map.put("p_sftz","");
        map.put("p_sfsc","");

        if("AddUser".equals(map.get("action"))){
            map.remove("p_xdwbm");
            userMapper.AddUser(map);

            errMsg = DataAccessHelper.getString(map, "p_errmsg");
        }else {
            userMapper.UpdateUser(map);
            String temerrmsg = map.get("p_errmsg");
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
        }

        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }


    // 根据工号获取人员信息详情
    @Override
    public Map GetUserByGh(String dwbm, String gh) throws Exception {

        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm",dwbm);
        map.put("p_gh",gh);

        userMapper.GetUserByGh(map);

        List<Rybm> p_cursor = (List<Rybm>) map.get("p_cursor");
        Rybm rybm=null;
        if (p_cursor !=null) {
            rybm = p_cursor.get(0);
            rybm.setGh(StringUtils.isEmpty(rybm.getGh()) ? "" : rybm.getGh());
            rybm.setDwbm(StringUtils.isEmpty(rybm.getDwbm()) ? "" : rybm.getDwbm());
            rybm.setMc(StringUtils.isEmpty(rybm.getMc()) ? "" : rybm.getMc());
            rybm.setDlbm(StringUtils.isEmpty(rybm.getDlbm()) ? "" : rybm.getDlbm());
            rybm.setGzzh(StringUtils.isEmpty(rybm.getGzzh()) ? "" : rybm.getGzzh());
            rybm.setXb(StringUtils.isEmpty(rybm.getXb()) ? "" : rybm.getXb());
            rybm.setSflsry(StringUtils.isEmpty(rybm.getSflsry()) ? "" : rybm.getSflsry());
            rybm.setYddhhm(StringUtils.isEmpty(rybm.getYddhhm()) ? "" : rybm.getYddhhm());
            rybm.setDzyj(StringUtils.isEmpty(rybm.getDzyj()) ? "" : rybm.getDzyj());
            rybm.setCaid(StringUtils.isEmpty(rybm.getCaid()) ? "" : rybm.getCaid());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("gh:"+rybm.getGh()).append(",dwbm:"+rybm.getDwbm()).append
                (",mc:"+rybm.getMc()).append(",dlbm:"+rybm.getDlbm()).append(",gzzh:"+rybm.getGzzh()).append
                (",xb:"+rybm.getXb()).append(",sflsry:"+rybm.getSflsry()).append(",yddhhm:"+rybm.getYddhhm()).
                append(",dzyj:"+rybm.getDzyj()).append(",caid:" + rybm.getCaid());


        map.put("sbMsg", sb);

        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return map;
    }


    // 删除人员信息
    @Override
    public String DeleteUser(String dwbm, String ghj) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",dwbm);
        map.put("p_ghj",ghj);

        userMapper.DeleteUser(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }


    // 人员重置密码
    @Override
    public String ResetUserPwd(String dwbm, String ghj) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",dwbm);
        map.put("p_ghj",ghj);

        userMapper.ResetUserPwd(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }


    // 人员同步
    @Override
    public String UserSync(String dwbm, String gh) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",dwbm);
        map.put("p_gh",gh);

        userMapper.UserSync(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return errMsg;
    }

}
