package com.start.boot.domain.organization;

/**
 * 单位实体
 * Created by lenovo on 2017/10/25.
 */
public class Unit {

    /**
     * 单位编码
     */
    private String dwbm;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     *  父单位编码
     */
    private String fdwbm;
    /**
     * 单位级别
     */
    private String dwjb;
    /**
     * 是否删除
     */
    private String sfsc;
    /**
     * 单位简称
     */
    private String dwjc;
    /**
     * 单位标识:0普通;1铁检;2林检;3军检
     */
    private String dwsx;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getFdwbm() {
        return fdwbm;
    }

    public void setFdwbm(String fdwbm) {
        this.fdwbm = fdwbm;
    }

    public String getDwjb() {
        return dwjb;
    }

    public void setDwjb(String dwjb) {
        this.dwjb = dwjb;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getDwjc() {
        return dwjc;
    }

    public void setDwjc(String dwjc) {
        this.dwjc = dwjc;
    }

    public String getDwsx() {
        return dwsx;
    }

    public void setDwsx(String dwsx) {
        this.dwsx = dwsx;
    }
}
