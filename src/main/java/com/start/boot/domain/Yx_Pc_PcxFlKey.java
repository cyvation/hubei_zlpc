package com.start.boot.domain;

public class Yx_Pc_PcxFlKey {
    private String pcslbm;

    private String pcxflbm;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm == null ? null : pcslbm.trim();
    }

    public String getPcxflbm() {
        return pcxflbm;
    }

    public void setPcxflbm(String pcxflbm) {
        this.pcxflbm = pcxflbm == null ? null : pcxflbm.trim();
    }
}