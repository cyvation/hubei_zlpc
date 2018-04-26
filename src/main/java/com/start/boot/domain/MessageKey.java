package com.start.boot.domain;

public class MessageKey {
    private String dwbm;

    private String xxxh;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm == null ? null : dwbm.trim();
    }

    public String getXxxh() {
        return xxxh;
    }

    public void setXxxh(String xxxh) {
        this.xxxh = xxxh == null ? null : xxxh.trim();
    }
}