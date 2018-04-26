package com.start.boot.domain;

public class MenuButtonWithBLOBs extends MenuButton {
    private String clcxcs;

    private String xycxcs;

    public String getClcxcs() {
        return clcxcs;
    }

    public void setClcxcs(String clcxcs) {
        this.clcxcs = clcxcs == null ? null : clcxcs.trim();
    }

    public String getXycxcs() {
        return xycxcs;
    }

    public void setXycxcs(String xycxcs) {
        this.xycxcs = xycxcs == null ? null : xycxcs.trim();
    }
}