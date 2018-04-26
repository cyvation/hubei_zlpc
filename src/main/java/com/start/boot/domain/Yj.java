package com.start.boot.domain;

import java.util.Date;

public class Yj extends YjKey {
    private String pcyjlx;

    private String pcyjjl;

    private Date yjtcsj;

    private String tcrdwbm;

    private String tcrdwmc;

    private String tcrgh;

    private String tcrmc;

    private Date cjsj;

    private Date zhxgsj;

    private String sfsc;

    private String pcyj;

    public String getPcyjlx() {
        return pcyjlx;
    }

    public void setPcyjlx(String pcyjlx) {
        this.pcyjlx = pcyjlx == null ? null : pcyjlx.trim();
    }

    public String getPcyjjl() {
        return pcyjjl;
    }

    public void setPcyjjl(String pcyjjl) {
        this.pcyjjl = pcyjjl == null ? null : pcyjjl.trim();
    }

    public Date getYjtcsj() {
        return yjtcsj;
    }

    public void setYjtcsj(Date yjtcsj) {
        this.yjtcsj = yjtcsj;
    }

    public String getTcrdwbm() {
        return tcrdwbm;
    }

    public void setTcrdwbm(String tcrdwbm) {
        this.tcrdwbm = tcrdwbm == null ? null : tcrdwbm.trim();
    }

    public String getTcrdwmc() {
        return tcrdwmc;
    }

    public void setTcrdwmc(String tcrdwmc) {
        this.tcrdwmc = tcrdwmc == null ? null : tcrdwmc.trim();
    }

    public String getTcrgh() {
        return tcrgh;
    }

    public void setTcrgh(String tcrgh) {
        this.tcrgh = tcrgh == null ? null : tcrgh.trim();
    }

    public String getTcrmc() {
        return tcrmc;
    }

    public void setTcrmc(String tcrmc) {
        this.tcrmc = tcrmc == null ? null : tcrmc.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(Date zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc == null ? null : sfsc.trim();
    }

    public String getPcyj() {
        return pcyj;
    }

    public void setPcyj(String pcyj) {
        this.pcyj = pcyj == null ? null : pcyj.trim();
    }
}