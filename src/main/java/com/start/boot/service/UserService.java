package com.start.boot.service;

import com.start.boot.domain.Rybm;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/31.
 */
public interface UserService {

    /**
     * 获取单位树
     * @param dwbm
     * @return
     */
    List<Map> GetDwbmCombTree(String dwbm) throws Exception;

    /**
     * 获取统一业务新增人员信息列表
     * @param dwbm
     * @param mc
     * @return
     */
    Map TyywNewUserList(String dwbm, String mc, int rows, int page) throws Exception;

    /**
     * 获取人员信息列表
     * @param mc
     * @param gh
     * @param dwbm
     * @return
     */
    Map GetUserList(String dwbm, String gh, String mc, int rows, int page) throws Exception;

    /**
     * 添加/更新人员信息
     * @param
     * @param rybm
     * @return
     */
    String addOrUpdateRybm(Rybm rybm) throws Exception;

    /**
     * 根据工号获取人员信息详情
     * @param gh
     * @param dwbm
     * @return
     */
    Map GetUserByGh(String dwbm, String gh) throws Exception;

    /**
     * 删除人员信息
     * @param dwbm
     * @param ghj
     * @return
     */
    String DeleteUser(String dwbm, String ghj) throws Exception;

    /**
     * 人员重置密码
     * @param dwbm
     * @param ghj
     * @return
     */
    String ResetUserPwd(String dwbm, String ghj) throws Exception;

    /**
     * 人员同步
     * @param dwbm
     * @param gh
     * @return
     */
    String UserSync(String dwbm, String gh) throws Exception;
}
