package com.start.boot.domain;

import com.start.boot.common.Param_Pager;

/**
 * 人员编码页面传入实体类
 * Created by lenovo on 2017/10/27.
 *  obj.DWBM = dwbm;
 obj.BMBM = bmbm;
 obj.JSBM = jsbm;
 obj.GH = gh;
 obj.GZZH = gzzh;
 obj.XM = encodeURI(xm)
 */
public class Param_rybm extends Param_Pager {
    //单位编码
    private String dwbm;
    //部门编码
    private String bmbm;
    //角色编码
    private String jsbm;
    //工号
    private String gh;
    //工作证号
    private String gzzh;
    //姓名
    private String xm;

    //原单位编码
    private String ydwbm;
    //原单位名称
    private String ydwmc;
    //原部门编码
    private String ybmbm;
    //原部门名称
    private String ybmmc;
    //原角色编码
    private String yjsbm;
    //原角色名称
    private String yjsmc;

    public String getYdwbm() {
        return ydwbm;
    }

    public void setYdwbm(String ydwbm) {
        this.ydwbm = ydwbm;
    }

    public String getYdwmc() {
        return ydwmc;
    }

    public void setYdwmc(String ydwmc) {
        this.ydwmc = ydwmc;
    }

    public String getYbmbm() {
        return ybmbm;
    }

    public void setYbmbm(String ybmbm) {
        this.ybmbm = ybmbm;
    }

    public String getYbmmc() {
        return ybmmc;
    }

    public void setYbmmc(String ybmmc) {
        this.ybmmc = ybmmc;
    }

    public String getYjsbm() {
        return yjsbm;
    }

    public void setYjsbm(String yjsbm) {
        this.yjsbm = yjsbm;
    }

    public String getYjsmc() {
        return yjsmc;
    }

    public void setYjsmc(String yjsmc) {
        this.yjsmc = yjsmc;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getJsbm() {
        return jsbm;
    }

    public void setJsbm(String jsbm) {
        this.jsbm = jsbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getGzzh() {
        return gzzh;
    }

    public void setGzzh(String gzzh) {
        this.gzzh = gzzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
}
