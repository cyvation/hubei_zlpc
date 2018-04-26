package com.start.boot.web;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.constant.SessionNames;
import com.start.boot.domain.*;
import com.start.boot.service.AccountService;
import com.start.boot.service.OriganizationService;
import com.start.boot.support.utils.CookieUtils;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import com.start.boot.support.utils.HttpContextUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统相关控制器
 * Created by lei on 2017/10/30.
 */
@RestController
@RequestMapping("/account")
public class AccountController extends ArchivesSystemBaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OriganizationService origanizationService;


    @RequestMapping("/loginDwbmTree")
    public String getGetDwbmTree() {

        //获取单位编码
        String dwbm = SystemConfiguration.djdwbm;

        //传入单位编码，返回结果集
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        List<Map> list = null;
        try {
            list = origanizationService.getDwbm(dwbm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list == null) {
            super.errMsg("获取单位树失败", null, new Exception("获取单位树失败"));
            return null;
        }

        String topBM = "";
        //遍历集合，找到本单位的父单位
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> resultMap = list.get(i);
            for (String key : resultMap.keySet()) {
                if (key.equals("BM") && resultMap.get(key).equals(dwbm)) {
                    topBM = resultMap.get("FBM");
                    break;
                }
            }
        }


        //转为EasyUI结构：
        return EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM);
    }


    /**
     * 用户登录
     *
     * @param dwbm     单位编码
     * @param username 登录名
     * @param password 密码
     * @return
     */
    @RequestMapping("/signIn")
    public String signIn(String dwbm, String username, String password) {

        //设置返回值
        String result = "";

        try {
            User user = accountService.signIn(dwbm, username, password);
            if (user != null) {
                // todo:获取角色编码
                List<Jsbm> jsbm = accountService.getJsbm(dwbm, user.getGH());
                user.setJSBM(jsbm);
                user.setUsername(username);
            }
            result = saveUserInfo(user);
        } catch (Exception e) {
            result = failure(e.getMessage(), "用户名或密码错误!");
            super.errMsg("登录验证失败。", String.format("dwbm:%s username:%s password:%s", dwbm, username, password), e);
        }

        return result;
    }

    /**
     * CA认证登陆
     *
     * @param caid ca账号
     * @return
     */
    @RequestMapping("/signByCaid")
    public String signByCaid(String caid) {

        // 返回页面信息
        String result = "";
        try {
            User user = accountService.signByCaid(caid);
            result = saveUserInfo(user);
        } catch (Exception e) {
            result = failure(e.getMessage(), "ca错误!");
            super.errMsg("登录验证失败。", String.format("caid:%s ", caid), e);

        }

        return result;

    }

    protected String saveUserInfo(User user) {
        String result = "";

        if (user != null) {

            CookieUtils.addCookie("dwbm", user.getDWBM(), Integer.MAX_VALUE);
            CookieUtils.addCookie("username", user.getUsername(), Integer.MAX_VALUE);

            //关键信息存到session中
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_USER, user);//存用户实体
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_DWBM, user.getDWBM());//存单位编码
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_DWMC, user.getDWMC());//存单位名称
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_GH, user.getGH());//存工号
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_MC, user.getMC());//存工号
            HttpContextUtils.setSession(SessionNames.SESSION_KEY_SFLX, user.getSFLX());//存身份信息

            result = success(user, "登录成功");
        } else {
            result = failure("登录失败", "用户名或密码错误!");
        }

        return result;

    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/signOut")
    public String signOut(HttpSession session) {

        //设置返回值
        String result = "";

        try {

            session.invalidate();
            result = success(null, "退出登录成功");

        } catch (Exception e) {
            super.errMsg("退出登录失败", null, e);
            result = failure(null, "退出登录失败");
        }

        return result;
    }

    /**
     * 获取当前登陆用户的信息
     *
     * @return
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo() {

        //设置返回值
        String result = "";

        Object user = getCurrentUser();
        if (user == null) {
            result = failure("获取用户信息失败", "获取用户信息失败");
            ;
            super.errMsg("获取用户信息失败", "param为空", new Exception("获取用户信息失败"));

        } else {
            result = success((User) user, "获取用户信息成功。");
        }

        return result;
    }

    /**
     * 获取当前登陆用户的功能列表
     *
     * @return
     */
    @RequestMapping("/functionList")
    public String getFunction() {

        // 设置返回值
        String result = "";

        try {
            // Ajax传入参数
            String currentDwbm = getCurrentDwbm();
            String currentGh = (String) getCurrentGh();
            String currentSflx = getCurrentSflx();

            // 数据库操作
            List<FunctionFl> functionFlList = accountService.getFunction(currentDwbm, currentGh, currentSflx);

            result = success(functionFlList, "获取功能列表成功");

        } catch (Exception e) {
            result = failure(e.getMessage(), "查询人员功能列表失败");
            super.errMsg("获取功能列表失败", "", e);
        }

        return result;
    }

    /**
     * 获取通知公告
     *
     * @return
     */
    @RequestMapping("/getTzgg")
    public String getAnnounce() {

        // 设置返回值
        String result = "";

        try {

            String dwbm = getCurrentDwbm();

            List<Map> list = accountService.getTzgg(dwbm);

            result = success(list, "");

        } catch (Exception e) {
            result = failure(e.getMessage(), "获取通知公告失败");
            super.errMsg("获取通知公告失败", "", e);
        }

        return result;
    }

    /**
     * 查询人员快捷菜单信息
     *
     * @return
     */
    @RequestMapping("/getKjcd")
    public String getKjcd() {

        // 响应内容
        String result = "";

        try {

            String dwbm = getCurrentDwbm();
            String gh = getCurrentGh();

            List<Map> list = accountService.getKjcd(dwbm, gh);

            result = success(list, "查询人员快捷菜单信息成功");

        } catch (Exception e) {
            result = failure(e.getMessage(), "查询人员快捷菜单信息失败");
            super.errMsg("查询人员快捷菜单信息失败", "", e);
        }

        return result;
    }

    /**
     * 新增人员快捷菜单信息
     *
     * @return
     */
    @RequestMapping("/addKjcd")
    public String addKjcd(String json) {

        // 响应内容
        String result = "";

        try {

            // 获取Ajax参数
            String dwbm = getCurrentDwbm();
            String gh = getCurrentGh();
            List<Param_KJCD> list = FastJsonUtils.toList(Param_KJCD.class, json);

            // 执行数据库操作
            boolean isSuccess = accountService.addKjcd(dwbm, gh, list);

            // 返回值
            result = success(list, "新增人员快捷菜单信息成功");

        } catch (Exception ex) {
            super.errMsg("新增人员快捷菜单信息失败", json, ex);
            result = failure(ex.getMessage(), "新增人员快捷菜单信息失败");
        }

        return result;
    }

    /**
     * 修改消息内容
     *
     * @return
     */
    @RequestMapping("/updXxlb")
    public String updXxlb(String xxxhj) {

        // 响应内容
        String result = "";

        try {

            String dwbm = getCurrentDwbm();
            boolean isSuccess = accountService.updXxlb(dwbm, xxxhj);
            result = success(isSuccess, "修改消息内容成功");
        } catch (Exception e) {
            super.errMsg("修改消息内容失败", "xxxh:" + xxxhj, e);
            result = failure(e.getMessage(), "修改消息内容失败");
        }

        return result;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("/UpdatePassword")
    public String UpdatePassword(String pass_old, String pass_new) {

        // 响应内容
        String result = "";

        String dwbm = getCurrentDwbm();
        String gh = getCurrentGh();

        try {
            boolean isSuccess = accountService.updatePassword(dwbm, gh, pass_old, pass_new);
            result = success(isSuccess, "修改密码成功");
        } catch (Exception e) {
            super.errMsg("修改密码失败", String.format("password_old:%s password_new:%", pass_old, pass_new), e);
            result = failure(e.getMessage(), "修改密码失败");
        }

        return result;
    }

    /**
     * 获取待办任务数
     *
     * @return
     */
    @RequestMapping("/getTaskCount")
    public String getTaskCount(String gnbm) {

        // 响应内容
        String result = "";

        try {
            //session中获取当前登陆人的单位编码与工号
            String dwbm = getCurrentDwbm();
            String gh = getCurrentGh();

            Integer count = accountService.getTaskCount(dwbm, gh, gnbm);
            result = success(count, "获取待办任务数成功");

        } catch (Exception e) {
            result = failure(e.getMessage(), "获取待办任务数失败");
            super.errMsg("获取待办任务数失败", "", e);
        }

        return result;
    }

    @RequestMapping("/getJsbm")
    public String getJsbm() {
        // 设置返回值
        String result = "";
        try {
            // Ajax传入参数
            String currentDwbm = getCurrentDwbm();
            String currentGh = (String) getCurrentGh();
            //数据库操作
            List<Jsbm> jsbmList = accountService.getJsbm(currentDwbm, currentGh);
            result = success(jsbmList, "");
        } catch (Exception e) {
            result = failure(e.getMessage(), "查询角色编码失败");
            super.errMsg("获取用户信息失败", "", e);
        }
        return result;
    }

    @ApiOperation("获取用户功能参数")
    @PostMapping("/getGncsByDwbm")
    public String getGncsByDwbm(String gnbm) {
        // 设置返回值
        String result = "";
        try {
            User user = (User) getCurrentUser();
            List<Map> list = accountService.getGncsByDwbm(user, gnbm);

            List<String> rightLst = new ArrayList<>();
            if(list != null && list.size() >= 1){
                list.forEach(item -> {
                    rightLst.add(item.get("GNCS").toString());
                });
            }

            result = success(rightLst, "获取用户功能参数成功");
        } catch (Exception e) {
            super.errMsg("获取用户功能参数失败", "gnbm：" + gnbm, e);
            result = failure(e.getMessage(), "获取用户功能参数失败");
        }

        return result;
    }
}


