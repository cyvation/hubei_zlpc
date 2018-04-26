package com.start.boot.domain;

import org.springframework.beans.factory.annotation.Value;

public class DataDictionary extends DataDictionaryKey {
    private String fdm;

    private String mc;

    private String sm;

    @Value("N")
    private String sfsc;

    public String getFdm() {
        return fdm;
    }

    public void setFdm(String fdm) {
        this.fdm = fdm == null ? null : fdm.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc == null ? null : sfsc.trim();
    }
}