package com.start.boot.web;


import com.start.boot.common.BaseController;
import com.start.boot.constant.SessionNames;
import com.start.boot.support.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <h3>司法档案系统 controller的基类</h3>
 *
 * @author 符黄辰君
 * @since 2017年6月15日
 */
public abstract class ArchivesSystemBaseController extends BaseController {


    /**
     * 获取当前用户
     *
     * @return
     */
    protected Object getCurrentUser() {
        HttpSession session = getSession();
        return session == null ? null : session.getAttribute(SessionNames.SESSION_KEY_USER);
    }

    /**
     * 获取当前工号
     *
     * @return
     */
    protected String getCurrentGh() {
        HttpSession session = getSession();
        return session == null ? "" : (String) session.getAttribute(SessionNames.SESSION_KEY_GH);
    }

    /**
     * 获取当前名称
     *
     * @return
     */
    protected String getCurrentMC() {
        HttpSession session = getSession();
        return session == null ? "" : (String) session.getAttribute(SessionNames.SESSION_KEY_MC);
    }

    /**
     * 获取当前登录单位编码
     *
     * @return
     */
    protected String getCurrentDwbm() {
        HttpSession session = getSession();
        return session == null ? "" : (String) session.getAttribute(SessionNames.SESSION_KEY_DWBM);
    }

    /**
     * 获取当前登录单位名称
     *
     * @return
     */
    protected String getCurrentDwmc() {
        HttpSession session = getSession();
        return session == null ? "" : (String) session.getAttribute(SessionNames.SESSION_KEY_DWMC);
    }

    /**
     * 获取当前操作人IP地址
     *
     * @return
     */
    protected String getOperatorIp() {
        HttpServletRequest request= getRequest();
        return request == null ? "" : IPUtils.getIpAddr(request);
    }


    /**
     * 获取当前审批角色
     *
     * @return
     */
    protected Map<String, String> getCurrentSpjs() {
        return (Map<String, String>) getSession().getAttribute(SessionNames.SESSION_KEY_SPJS);
    }

    /**
     * 获取当前身份类型
     *
     * @return
     */
    protected String getCurrentSflx() {
        return (String) getSession().getAttribute(SessionNames.SESSION_KEY_SFLX);
    }
    /**
     * 当前用户是否存在审批角色 (AND)
     *
     * @param spjss 审批角色列表
     *
     * @return
     */
    protected Boolean containsSpjsAnd(String... spjss) {
        Map<String, String> currentSpjsMap = getCurrentSpjs();
        String roleOfAudit = currentSpjsMap.get("roleOfAudit");

        int length = spjss.length;

        int count = 0;
        for (String spjs : spjss) {
            if (roleOfAudit.contains(spjs)) {
                count++;
            }
        }

        return count == length;

    }

    /**
     * 当前用户是否存在审批角色 (OR)
     *
     * @param spjss 审批角色列表
     *
     * @return
     */
    protected Boolean containsSpjsOr(String... spjss) {
        Map<String, String> currentSpjsMap = getCurrentSpjs();
        String roleOfAudit = currentSpjsMap.get("roleOfAudit");


        Boolean res = false;
        for (String spjs : spjss) {
            if (roleOfAudit.contains(spjs)) {
                res = true;
                break;
            }
        }

        return res;

    }


    /**
     * 日志对象
     */
    private final static Logger logger = LoggerFactory.getLogger(ArchivesSystemBaseController.class);
    //类似登录、添加等操作
    protected  void errMsg(String describle, String params, Exception ex) {
        logger.error(String.format("描述信息：【%s】 传入参数：【%s】 用户信息：【%s】 ", describle, params, getUserInfo()), ex);
    }
    private String getUserInfo(){
        return  String.format("单位编码：%s 单位名称：%s 工号：%s 姓名：%s 操作IP: %s", getCurrentDwbm(), getCurrentDwmc(), getCurrentGh(), getCurrentMC(),getOperatorIp());
    }

    /**
     * 日志对象
     */
    private static final Logger test = LoggerFactory.getLogger("System");
    //类似登录、添加等操作
    protected  void testLog(String describle, String params, Exception ex) {
        logger.error(String.format("描述信息：【%s】 传入参数：【%s】 用户信息：【%s】 ", describle, params, getUserInfo()), ex);
    }



}
