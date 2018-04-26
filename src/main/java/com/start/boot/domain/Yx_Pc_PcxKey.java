package com.start.boot.domain;

public class Yx_Pc_PcxKey {
    private String pcslbm;

    private String pcxbm;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm == null ? null : pcslbm.trim();
    }

    public String getPcxbm() {
        return pcxbm;
    }

    public void setPcxbm(String pcxbm) {
        this.pcxbm = pcxbm == null ? null : pcxbm.trim();
    }
}