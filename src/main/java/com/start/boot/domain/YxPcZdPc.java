package com.start.boot.domain;

import java.math.BigDecimal;

public class YxPcZdPc extends YxPcZdPcKey {
    private String pclbBm;

    private String pcxMc;

    private BigDecimal pcxFz;

    private String pcly;

    private String sm;

    public String getPclbBm() {
        return pclbBm;
    }

    public void setPclbBm(String pclbBm) {
        this.pclbBm = pclbBm == null ? null : pclbBm.trim();
    }

    public String getPcxMc() {
        return pcxMc;
    }

    public void setPcxMc(String pcxMc) {
        this.pcxMc = pcxMc == null ? null : pcxMc.trim();
    }

    public BigDecimal getPcxFz() {
        return pcxFz;
    }

    public void setPcxFz(BigDecimal pcxFz) {
        this.pcxFz = pcxFz;
    }

    public String getPcly() {
        return pcly;
    }

    public void setPcly(String pcly) {
        this.pcly = pcly == null ? null : pcly.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }
}