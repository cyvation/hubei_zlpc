package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/11/5.
 */

// 评查员
public class Param_Pcy {
    @JSONField(name = "PCHDBM")
    private String PCHDBM;
    @JSONField(name = "PCZBM")
    private String PCZBM;
    @JSONField(name = "DWBM")
    private String DWBM;
    @JSONField(name = "GH")
    private String GH;
    @JSONField(name = "DWMC")
    private String DWMC;
    @JSONField(name = "MC")
    private String MC;
    @JSONField(name = "JSBM")
    private String JSBM;
    @JSONField(name = "JSMC")
    private String JSMC;
    @JSONField(name = "SM")
    private String SM;

    public String getPCHDBM() {
        return PCHDBM;
    }

    public void setPCHDBM(String PCHDBM) {
        this.PCHDBM = PCHDBM;
    }

    public String getPCZBM() {
        return PCZBM;
    }

    public void setPCZBM(String PCZBM) {
        this.PCZBM = PCZBM;
    }

    public String getDWBM() {
        return DWBM;
    }

    public void setDWBM(String DWBM) {
        this.DWBM = DWBM;
    }

    public String getGH() {
        return GH;
    }

    public void setGH(String GH) {
        this.GH = GH;
    }

    public String getDWMC() {
        return DWMC;
    }

    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getJSBM() {
        return JSBM;
    }

    public void setJSBM(String JSBM) {
        this.JSBM = JSBM;
    }

    public String getJSMC() {
        return JSMC;
    }

    public void setJSMC(String JSMC) {
        this.JSMC = JSMC;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }
}
