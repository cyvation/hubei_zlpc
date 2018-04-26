package com.start.boot.common;

import com.start.boot.constant.EasyUIConstant;
import com.start.boot.constant.RequestStatus;
import com.start.boot.support.structure.RequestResult;
import com.start.boot.support.utils.FastJsonUtils;
import com.start.boot.support.utils.HttpContextUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <h3>所有controller的基类</h3>
 *
 * @author 符黄辰君
 * @since 2017年6月15日
 */
public abstract class BaseController {


    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return HttpContextUtils.getHttpServletRequest();
    }

    /**
     * 获取session
     *
     * @return
     */
    protected HttpSession getSession() {
        return HttpContextUtils.getSession();
    }


    /**
     * 获取参数
     *
     * @param name 参数名
     * @return
     */
    protected String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取路径
     *
     * @param path 路径
     * @return
     */
    protected String getRealPath(String path) {
        if(!StringUtils.isEmpty(path)){
            return getSession().getServletContext().getRealPath(path);
        }
        return path;
    }

    /**
     * 解析页数
     *
     * @return
     */
    protected int parsePage() {
        String page = getParameter(EasyUIConstant.BUILTIN_DATAGRID_PAGE);// page参数easyui的
        return parseRows(page);
    }


    /**
     * 解析页数
     *
     * @param page 页
     * @return
     */
    protected int parsePage(String page) {
        final int defaultValue = 1;
        return StringUtils.isEmpty(page) ? defaultValue : Integer.parseInt(page);
    }


    /**
     * 解析页长（每页多少条记录）
     *
     * @return
     */
    protected int parseRows() {
        String rows = getParameter(EasyUIConstant.BUILTIN_DATAGRID_ROWS);// rows参数是easyui的
        return parseRows(rows);
    }


    /**
     * 解析页长（每页多少条记录）
     *
     * @param rows 行
     * @return
     */
    protected int parseRows(String rows) {
        final int defaultValue = 20;
        return StringUtils.isEmpty(rows) ? defaultValue : Integer.parseInt(rows);
    }


    /**
     * 获取起始记录行号
     *
     * @param page 页
     * @param rows 行
     * @return
     */
    protected int getBeginNum(int page, int rows) {
        return (page - 1) * rows + 1;
    }

    /**
     * 获取起始记录行号
     *
     * @param page 页
     * @param rows 行
     * @return
     */
    protected int getBeginNum(String page, String rows) {
        return getBeginNum(parsePage(page), parseRows(rows));
    }

    /**
     * 获取结束记录行号
     *
     * @param page 页
     * @param rows 行
     * @return
     */
    protected int getEndNum(int page, int rows) {
        return page * rows;
    }

    /**
     * 获取结束记录行号
     *
     * @param page 页
     * @param rows 行
     * @return
     */
    protected int getEndNum(String page, String rows) {
        return parsePage(page) * parseRows(rows);
    }


    /**
     * 请求成功
     *
     * @param value 业务值
     * @param note  说明
     * @return
     */
    protected String success(Object value, String note) {
        String result = FastJsonUtils.toString(createRequestResult(RequestStatus.OK, value, note));
        return result;
    }

    /**
     * 请求失败
     *
     * @param value 业务值
     * @param note  说明
     * @return
     */
    protected String failure(Object value, String note) {
        return FastJsonUtils.toString(createRequestResult(RequestStatus.ERROR, value, note));
    }

    /**
     * 请求拒绝
     *
     * @param value 业务值
     * @param note  说明
     * @return
     */
    protected String reject(Object value, String note) {
        return FastJsonUtils.toString(createRequestResult(RequestStatus.REJECT, value, note));
    }

    /**
     * 创建请求结果对象
     *
     * @param requestStatus
     * @param value
     * @param note
     * @return
     */
    private RequestResult createRequestResult(RequestStatus requestStatus, Object value, String note) {
        RequestResult result = new RequestResult();
        result.setStatus(requestStatus.getCode());
        result.setValue(value);
        result.setNote(note);
        return result;
    }

 /*   *//**
     * 解决日期转换
     * @param binder
     *//*
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(long.class,new LongEditor());
    }*/
}
