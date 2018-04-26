package com.start.boot.domain;

import java.util.List;

/**
 * Created by lenovo on 2017/10/16.
 * 用户信息：
 */
public class User {

    //单位编码
    public String DWBM;
    
    // 单位名称
    public String DWMC;

    // 工号
    public String GH;
    
    // 名称
    public String MC;

    //
    public String TSXX;

    //身份类型
    public String SFLX;

    // 角色集合
    public List<Jsbm> JSBM;

    // 部门编码
    public String BMBM;

    private String username;

    public String getDWBM() {
        return DWBM;
    }

    public void setDWBM(String DWBM) {
        this.DWBM = DWBM;
    }

    public String getDWMC() {
        return DWMC;
    }

    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }

    public String getGH() {
        return GH;
    }

    public void setGH(String GH) {
        this.GH = GH;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getTSXX() {
        return TSXX;
    }

    public void setTSXX(String TSXX) {
        this.TSXX = TSXX;
    }

    public String getSFLX() {
        return SFLX;
    }

    public void setSFLX(String SFLX) {
        this.SFLX = SFLX;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Jsbm> getJSBM() {
        return JSBM;
    }

    public void setJSBM(List<Jsbm> JSBM) {
        this.JSBM = JSBM;
    }

    public String getBMBM() {
        return BMBM;
    }

    public void setBMBM(String BMBM) {
        this.BMBM = BMBM;
    }

}
