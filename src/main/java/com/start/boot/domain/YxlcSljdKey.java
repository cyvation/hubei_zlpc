package com.start.boot.domain;

public class YxlcSljdKey {
    private String pcslbm;

    private String lcslbh;

    private String lcsljdbh;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm == null ? null : pcslbm.trim();
    }

    public String getLcslbh() {
        return lcslbh;
    }

    public void setLcslbh(String lcslbh) {
        this.lcslbh = lcslbh == null ? null : lcslbh.trim();
    }

    public String getLcsljdbh() {
        return lcsljdbh;
    }

    public void setLcsljdbh(String lcsljdbh) {
        this.lcsljdbh = lcsljdbh == null ? null : lcsljdbh.trim();
    }
}