package com.start.boot.domain;

/**
 * 角色功能分配实体
 * Created by lenovo on 2017/10/27.
 */
public class Jsgnfp {

    //单位编码
    private String dwbm;
   //角色编码
    private String jsbm;
    //功能编码
    private String gnbm;
    //备注
    private String bz;
    //功能参数
    private String gncs;
    //部门编码
    private String bmbm;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getJsbm() {
        return jsbm;
    }

    public void setJsbm(String jsbm) {
        this.jsbm = jsbm;
    }

    public String getGnbm() {
        return gnbm;
    }

    public void setGnbm(String gnbm) {
        this.gnbm = gnbm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGncs() {
        return gncs;
    }

    public void setGncs(String gncs) {
        this.gncs = gncs;
    }

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

}
