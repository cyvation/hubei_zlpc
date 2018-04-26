package com.start.boot.domain;

public class YjKey {
    private String pcslbm;

    private String pcyjbh;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm == null ? null : pcslbm.trim();
    }

    public String getPcyjbh() {
        return pcyjbh;
    }

    public void setPcyjbh(String pcyjbh) {
        this.pcyjbh = pcyjbh == null ? null : pcyjbh.trim();
    }
}