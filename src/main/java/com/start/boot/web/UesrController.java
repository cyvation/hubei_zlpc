package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.start.boot.domain.Rybm;
import com.start.boot.service.UserService;
import com.start.boot.support.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * Created by lenovo on 2017/10/25.
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class UesrController extends ArchivesSystemBaseController{


    @Autowired
    private UserService userService;


    // 获取单位树
    @RequestMapping("/GetDwbmCombTree")
    public String GetDwbmCombTree(@RequestParam String dwbm) {

        String result = "";

        if (StringUtils.isEmpty(dwbm)) {
            super.errMsg("获取单位树形下拉框失败",dwbm,new Exception("获取单位树形下拉框失败"));
        }

        try {
            List<Map> list = userService.GetDwbmCombTree(dwbm);
            if (list != null) {
                //遍历集合，找到本单位的父单位
                String topBM = GetDw.topBM(list, dwbm);
                result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM);

            }
            result = success(result, "获取单位树形下拉框成功");

        } catch (Exception e) {
            super.errMsg("获取单位树形下拉框失败", dwbm, e);
            result = failure(e.getMessage(), "获取单位树形下拉框失败");
        }
        return result ;
    }


    // 获取统一业务新增人员信息列表
    @RequestMapping("/TyywNewUserList")
    public String TyywNewUserList(@RequestParam String dwbm, @RequestParam String mc ,int page, int rows) {

        String result = "";

        try {
            Map map = userService.TyywNewUserList(dwbm, mc, rows, page);
            List<Map> list = (List<Map>) map.get("p_cursor");
            Integer total = (Integer) map.get("p_count");
            result = EasyUIHelper.buildDataGridDataSource(list, total);
            result = success(result, "获取统一业务新增人员信息列表成功");

        } catch (Exception e) {
            super.errMsg("获取统一业务新增人员信息列表失败", dwbm, e);
            result = failure(e.getMessage(), "获取统一业务新增人员信息列表失败");
        }
        return result ;
    }

    // 获取人员信息列表
    @RequestMapping("/GetUserList")
    public String GetUserList(String mc, String gh, String dwbm,int page, int rows) {

        String result = "";

        try {
            Map map = userService.GetUserList(dwbm, gh, mc, rows, page);
            List<Map> list = (List<Map>) map.get("p_cursor");
            Integer p_count = (Integer) map.get("p_count");
            result = EasyUIHelper.buildDataGridDataSource(list,p_count);
            result = success(result, "获取人员信息列表成功");

        } catch (Exception e) {
            super.errMsg("获取人员信息列表发生错误", dwbm, e);
            result = failure(e.getMessage(), "获取人员信息列表发生错误");
        }
        return result ;
    }


    // 添加/更新人员信息
    @RequestMapping("/AddOrUpdateRybm")
    public String AddOrUpdateRybm(Rybm rybm) {

        String result = "";

        try {
            String errmsg = userService.addOrUpdateRybm(rybm);
            if (errmsg != null) {
                super.errMsg("更新/添加用户失败",rybm.toString(),new Exception("更新/添加用户失败"));
            }
            result = (errmsg == null?"操作成功":errmsg);
            result = success(result, "更新/添加用户成功");

        } catch (Exception e) {
            super.errMsg("更新/添加用户失败", rybm.getDwbm(), e);
            result = failure(e.getMessage(), "更新/添加用户失败");
        }
        return result ;
    }


    // 根据工号获取人员信息详情
    @RequestMapping("/GetUserByGh")
    public String GetUserByGh(String gh, String dwbm) {

        String result = "";

        try {
            Map<String, Object> map = userService.GetUserByGh(dwbm, gh);
            StringBuilder sb = (StringBuilder) map.get("sbMsg");
            String errmsg = DataAccessHelper.getString(map, "p_errmsg");
            result = (errmsg == null?sb.toString():"根据工号获取人员信息详情失败");
            result = success(result,"根据工号获取人员信息详情成功");

        } catch (Exception e) {
            super.errMsg("根据工号获取人员信息详情失败", dwbm, e);
            result = failure(e.getMessage(), "根据工号获取人员信息详情失败");
        }
        return result ;
    }


    // 删除人员信息
    @RequestMapping("/DeleteUser")
    public String DeleteUser(String dwbm, String ghj) {

        String result = "";

        try {
            String errmsg = userService.DeleteUser(dwbm, ghj);
            if (errmsg != null) {
                super.errMsg("删除人员信息失败",errmsg,new Exception("删除人员信息失败"));
            }
            result = (errmsg == null?"删除人员信息成功":errmsg);
            result = success(result,"删除人员信息成功");
        } catch (Exception e) {
            super.errMsg("删除人员信息失败", dwbm, e);
            result = failure(e.getMessage(), "删除人员信息失败");
        }
        return result ;
    }

    // 人员重置密码
    @RequestMapping("/ResetUserPwd")
    public String ResetUserPwd(String dwbm, String ghj) {

        String result = "";

        try {
            String errmsg = userService.ResetUserPwd(dwbm, ghj);
            if (errmsg != null) {
                super.errMsg("人员重置密码失败",errmsg,new Exception("人员重置密码失败"));
            }
            result = (errmsg == null?"密码重置成功":"人员重置密码失败");
            result = success(result,"人员重置密码成功");
        } catch (Exception e) {
            super.errMsg("人员重置密码失败", dwbm, e);
            result = failure(e.getMessage(), "人员重置密码失败");
        }
        return result ;
    }


    // 人员同步
    @RequestMapping("/UserSync")
    public String UserSync(String dwbm, String gh) {

        String result = "";

        try {
            String errmsg = userService.UserSync(dwbm, gh);
            boolean isSuccess = true;
            if (errmsg != null) {
                isSuccess = false;
                super.errMsg("人员同步失败",errmsg,new Exception("人员同步失败"));
            }
            result = success(errmsg, "人员同步成功");
        } catch (Exception e) {
            super.errMsg("人员同步失败", dwbm, e);
            result = failure(e.getMessage(), "人员同步失败");
        }
        return result ;
    }
}
