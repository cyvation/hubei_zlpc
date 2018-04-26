package com.start.boot.domain;

public class YxPcZdPcKey {
    private String pcslBm;

    private String pcxBm;

    public String getPcslBm() {
        return pcslBm;
    }

    public void setPcslBm(String pcslBm) {
        this.pcslBm = pcslBm == null ? null : pcslBm.trim();
    }

    public String getPcxBm() {
        return pcxBm;
    }

    public void setPcxBm(String pcxBm) {
        this.pcxBm = pcxBm == null ? null : pcxBm.trim();
    }
}