package com.start.boot.domain;


import com.start.boot.common.Param_Pager;

/**
 * 人员库实体
 * Created by lei on 2017/11/4.
 */
public class Param_Ryk extends Param_Pager {
    // 人员库单位编码
    private String rykdwbm;
    // 人员所在单位编码
    private String dwbm_ry;
    // 名称
    private String pcr_gh;
    // 名称
    private String pcr_mc;
    // 业务部门
    private String ywbm;
    // 查询参数
    private String type;
    // 部门编码
    private String bmbm;
    // 部门名称
    private String bmmc;
    // 角色编码
    private String jsbm;
    // 角色名称
    private String jsmc;
    //人员移动电话号码
    private String yddhhm;
    //电子邮件
    private String dzyj;

    public String getYddhhm() {
        return yddhhm;
    }

    public void setYddhhm(String yddhhm) {
        this.yddhhm = yddhhm;
    }

    public String getDzyj() {
        return dzyj;
    }

    public void setDzyj(String dzyj) {
        this.dzyj = dzyj;
    }

    public String getRykdwbm() {
        return rykdwbm;
    }

    public void setRykdwbm(String rykdwbm) {
        this.rykdwbm = rykdwbm;
    }

    public String getDwbm_ry() {
        return dwbm_ry;
    }

    public void setDwbm_ry(String dwbm_ry) {
        this.dwbm_ry = dwbm_ry;
    }

    public String getPcr_gh() {
        return pcr_gh;
    }

    public void setPcr_gh(String pcr_gh) {
        this.pcr_gh = pcr_gh;
    }

    public String getPcr_mc() {
        return pcr_mc;
    }

    public void setPcr_mc(String pcr_mc) {
        this.pcr_mc = pcr_mc;
    }

    public String getYwbm() {
        return ywbm;
    }

    public void setYwbm(String ywbm) {
        this.ywbm = ywbm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getJsbm() {
        return jsbm;
    }

    public void setJsbm(String jsbm) {
        this.jsbm = jsbm;
    }

    public String getJsmc() {
        return jsmc;
    }

    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
}
