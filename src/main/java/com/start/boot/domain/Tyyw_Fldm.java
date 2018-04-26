package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class Tyyw_Fldm extends Tyyw_FldmKey {
    private String fdm;

    private String mc;

    private String sm;

    private String sfsc;


    private List<Tyyw_Fldm>children=new ArrayList<>();

    public String getFdm() {
        return fdm;
    }

    public void setFdm(String fdm) {
        this.fdm = fdm;
    }

    public List<Tyyw_Fldm> getChildren() {
        return children;
    }

    public void setChildren(List<Tyyw_Fldm> children) {
        this.children = children;
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