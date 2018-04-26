package com.start.boot.domain;

import java.math.BigDecimal;

public class Wsmb {
    private String wsmbbh;

    private String dwbm;

    private String pcflbm;

    private String wsmbmc;

    private String wsmblb;

    private String wsmblj;

    private BigDecimal wspx;

    private String spbbm;

    private String sfgx;

    private String sm;

    private String jzmlh;

    private byte[] wsmbnr;

    public String getWsmbbh() {
        return wsmbbh;
    }

    public void setWsmbbh(String wsmbbh) {
        this.wsmbbh = wsmbbh == null ? null : wsmbbh.trim();
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm == null ? null : dwbm.trim();
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm == null ? null : pcflbm.trim();
    }

    public String getWsmbmc() {
        return wsmbmc;
    }

    public void setWsmbmc(String wsmbmc) {
        this.wsmbmc = wsmbmc == null ? null : wsmbmc.trim();
    }

    public String getWsmblb() {
        return wsmblb;
    }

    public void setWsmblb(String wsmblb) {
        this.wsmblb = wsmblb == null ? null : wsmblb.trim();
    }

    public String getWsmblj() {
        return wsmblj;
    }

    public void setWsmblj(String wsmblj) {
        this.wsmblj = wsmblj == null ? null : wsmblj.trim();
    }

    public BigDecimal getWspx() {
        return wspx;
    }

    public void setWspx(BigDecimal wspx) {
        this.wspx = wspx;
    }

    public String getSpbbm() {
        return spbbm;
    }

    public void setSpbbm(String spbbm) {
        this.spbbm = spbbm == null ? null : spbbm.trim();
    }

    public String getSfgx() {
        return sfgx;
    }

    public void setSfgx(String sfgx) {
        this.sfgx = sfgx == null ? null : sfgx.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getJzmlh() {
        return jzmlh;
    }

    public void setJzmlh(String jzmlh) {
        this.jzmlh = jzmlh == null ? null : jzmlh.trim();
    }

    public byte[] getWsmbnr() {
        return wsmbnr;
    }

    public void setWsmbnr(byte[] wsmbnr) {
        this.wsmbnr = wsmbnr;
    }
}