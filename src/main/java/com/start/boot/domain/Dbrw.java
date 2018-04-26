package com.start.boot.domain;

public class Dbrw {
    private String gnbm;

    private String gnmc;

    private String validatename;

    public String getGnbm() {
        return gnbm;
    }

    public void setGnbm(String gnbm) {
        this.gnbm = gnbm == null ? null : gnbm.trim();
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc == null ? null : gnmc.trim();
    }

    public String getValidatename() {
        return validatename;
    }

    public void setValidatename(String validatename) {
        this.validatename = validatename == null ? null : validatename.trim();
    }
}