package com.start.boot.service.impl;


import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.AccountMapper;
import com.start.boot.domain.*;
import com.start.boot.service.AccountService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 登录
     *
     * @param dwbm     单位编码
     * @param username 用户名
     * @param password 口令
     * @return 用户
     */
    @Override
    public User signIn(String dwbm, String username, String password) {

        User user = null;
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return user;

        //给存储过程参数赋值，参数名要与mapper.xml中参数名要对应
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_user", username.trim());
        map.put("p_passwd", DigestUtils.md5DigestAsHex(password.trim().getBytes()));
        accountMapper.userLogin(map);
        //获得用户：
        List<User> list = (List<User>) map.get("p_cursor");

        // 设置用户身份标识
        user = findUserSflx(list);

        return user;
    }

    /**
     * CA认证登陆
     *
     * @param caid
     * @return
     * @throws Exception
     */
    @Override
    public User signByCaid(String caid) throws Exception {
        User user = null;

        if (StringUtils.isEmpty(caid))
            return user;

        Map map = new HashMap();
        map.put("p_caid", caid);

        accountMapper.signByCaid(map);

        String p_errmsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNotEmpty(p_errmsg)) {
            throw new Exception(p_errmsg);
        }

        List<User> list = (List<User>) map.get("p_cursor");

        // 设置用户身份标识
        user = findUserSflx(list);

        return user;
    }

    protected User findUserSflx(List<User> list) {
        User user = null;
        // 获取最高身份标识
        String sflx = "0";
        if (list != null && list.size() >= 1) {
            for (int i = 0; i < list.size(); i++) {
                sflx = list.get(i).getSFLX().compareToIgnoreCase(sflx) > 0 ? list.get(i).getSFLX() : sflx;
            }
        }
        if (list != null && list.size() > 0) {
            user = list.get(0);
            user.setSFLX(sflx);
        }

        return user;
    }

    /**
     * 获取功能列表
     *
     * @param dwbm
     * @param gh
     * @param sflx
     * @return
     * @throws Exception
     */
    @Override
    public List<FunctionFl> getFunction(String dwbm, String gh, String sflx) throws Exception {

        String errMsg = "";

        //获取人员功能权限(输入：单位编码+工号+身份类型;输出功能列表)
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_rygh", gh);
        map.put("p_sflx", sflx);
        accountMapper.getFunction(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        //获取功能分类列表
        List<FunctionFl> functionFlList = (List<FunctionFl>) map.get("p_cursor_fl");
        //获取功能列表
        List<Function> functionList = (List<Function>) map.get("p_cursor_gn");

        //功能分类、功能拼接：
        for (FunctionFl functionFL : functionFlList) {

            for (Function function : functionList) {
                if (function.getFlbm().equalsIgnoreCase(functionFL.getFlbm())) {

                    functionFL.getFunctionList().add(function);
                }
            }
        }
        return functionFlList;
    }

    /**
     * 查询人员快捷菜单信息
     *
     * @param currentDwbm
     * @param gh
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> getKjcd(String currentDwbm, String gh) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", currentDwbm);
        map.put("p_gh", gh);

        accountMapper.getKjcd(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 获取通知公告
    @Override
    public List<Map> getTzgg(String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);//当前的单位编码
        map.put("p_lxdm", "-1");//类型
        map.put("p_ppxx", "");//匹配信息
        map.put("p_fbsjbeg", null);//发布时间
        map.put("p_fbsjend", null);//结束时间
        map.put("p_pagesize", "");//页大小
        map.put("p_pageindex", "");//页索引

        accountMapper.getTzgg(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 新增人员快捷菜单信息
    @Override
    public boolean addKjcd(String dwbm, String gh, List<Param_KJCD> list) throws Exception {

        String errMsg = "";

        // 清空用户快捷功能列表
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        //map.put("p_gnbm", "");

        accountMapper.delKjcd(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        // 遍历添加功能
        map.put("p_gnbm", "");
        map.put("p_xh", "");
        int i = 1;
        for (Param_KJCD param : list) {
            map.replace("p_gnbm", param.getGnbm());
            map.replace("p_xh", i++);

            accountMapper.addKjcd(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);
        }

        return true;
    }

    //修改消息内容
    @Override
    public boolean updXxlb(String dwbm, String xxxhj) throws Exception {

        String errMsg = "";
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        String[] str = xxxhj.split(",");
        for (int i = 0; i < str.length; i++) {
            map.put("p_xxxh", str[i]);
            accountMapper.updXxlb(map);

            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);
        }

        return true;
    }

    /**
     * 修改密码
     *
     * @param dwbm
     * @param gh
     * @param pass_old
     * @param pass_new
     * @return
     */
    @Override
    public boolean updatePassword(String dwbm, String gh, String pass_old, String pass_new) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        map.put("p_oldpwd", DigestUtils.md5DigestAsHex(pass_old.trim().getBytes()));
        map.put("p_newpwd", DigestUtils.md5DigestAsHex(pass_new.trim().getBytes()));

        accountMapper.updatePassword(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNotEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 获取未读消息数量
    @Override
    public Integer getXxsl(String dwbm, String gh) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_jsrdwbm", dwbm);//  接收人单位编码
        map.put("p_jsrgh", gh); //接收人工号
        accountMapper.getXxsl(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getInteger(map, "p_count");
    }

    // 获取消息列表
    @Override
    public Param_Pager getXxlb(Param_Xxlb param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_jsrdwbm", param.getJsrdwbm());//  接收人单位编码
        map.put("p_jsrgh", param.getJsrgh()); //接收人工号
        map.put("p_xxlx", ""); //消息类型
        map.put("p_xxzt", param.getXxzt()); //消息状态
        map.put("p_keyword", param.getKeyword()); //检索关键字
        map.put("p_fsrqbeg", OracleTimeUtils.format(param.getFsrqbeg())); //发送日期
        map.put("p_fsrqend", OracleTimeUtils.format(param.getFsrqend())); //发送日期
        map.put("p_pagesize", param.getRows()); //接收人工号
        map.put("p_pageindex", param.getPage()); //接收人工号

        accountMapper.getXxlb(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        param.setCount(DataAccessHelper.getInteger(map, "p_count"));
        param.setList(DataAccessHelper.getListMap(map, "p_cursor"));

        return param;
    }

    // 获取待办任务数量
    @Override
    public Integer getTaskCount(String dwbm, String gh, String gnbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        map.put("p_gnbm", gnbm);
        accountMapper.getTaskCount(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getInteger(map, "p_count");
    }

    /**
     * 获取用户角色编码
     *  @param dwbm
     * @param gh
     */
    public List<Jsbm> getJsbm(String dwbm, String gh) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        accountMapper.getJsbm(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return (List<Jsbm>) map.get("p_cursor");
    }

    /**
     * 获取用户功能对应的功能参数
     *
     * @param user
     * @return
     */
    @Override//List<Map<"GNBM","xxx">>
    public List<Map> getGncsByDwbm(User user, String gnbm) throws Exception {

        // 调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", user.getDWBM());
        map.put("p_gh", user.getGH());
        // 评接角色编码
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < user.getJSBM().size(); i++) {
            Jsbm jsbm = user.getJSBM().get(i);
            sb.append(jsbm.getJsbm()).append(",");

        }
        map.put("p_jsbm", sb.toString().substring(0, sb.length() - 1));
        map.put("p_gnbm", gnbm);
        accountMapper.getGncsByDwbm(map);

        String errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }
}
