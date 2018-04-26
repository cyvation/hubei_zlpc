package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能分类
 * Created by lenovo on 2017/10/21.
 */
public class FunctionFl {


    //分类编码（单位编码 + 4位数值）
    private String flbm;

    //分类名称
    private String flmc;

    //父分类编号
    private String fflbm;

    //父分类名称
    private String fflmc;

    //分类序号
    private Integer flxh;

    //单位编码
    private String dwbm;

    //是否删除(‘N’默认不删除，‘Y’删除)
    private String sfsc;

    //图标
    private String icon;

    //类型
    private String lx;

    //功能集合：
    private List<com.start.boot.domain.Function> functionList = new ArrayList<com.start.boot.domain.Function>();

    public String getFlbm() {
        return flbm;
    }

    public void setFlbm(String flbm) {
        this.flbm = flbm;
    }

    public String getFlmc() {
        return flmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public String getFflbm() {
        return fflbm;
    }

    public void setFflbm(String fflbm) {
        this.fflbm = fflbm;
    }

    public String getFflmc() {
        return fflmc;
    }

    public void setFflmc(String fflmc) {
        this.fflmc = fflmc;
    }

    public Integer getFlxh() {
        return flxh;
    }

    public void setFlxh(Integer flxh) {
        this.flxh = flxh;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLx() { return lx; }

    public void setLx(String lx) { this.lx = lx; }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }
}
