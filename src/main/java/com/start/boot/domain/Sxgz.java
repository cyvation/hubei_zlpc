package com.start.boot.domain;

/**
 * 筛选规则表
 * Created by lei on 2017/11/2.
 */
public class Sxgz {

    //规则编码（单位编码 + 评查分类编码 + 3位数值）
    private String gzbm;
    //单位编码
    private String dwbm;
    //评查分类编码
    private String pcflbm;
    //规则名称
    private String gzmc;
    //筛选程序
    private String sxcx;
    //筛选程序参数
    private String cxcs;
    //筛选程序说明
    private String sm;
    //所属类别，比如业务编码等
    private String sslb;
    //序号
    private String xh;

    public String getGzbm() {
        return gzbm;
    }

    public void setGzbm(String gzbm) {
        this.gzbm = gzbm;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getGzmc() {
        return gzmc;
    }

    public void setGzmc(String gzmc) {
        this.gzmc = gzmc;
    }

    public String getSxcx() {
        return sxcx;
    }

    public void setSxcx(String sxcx) {
        this.sxcx = sxcx;
    }

    public String getCxcs() {
        return cxcs;
    }

    public void setCxcs(String cxcs) {
        this.cxcs = cxcs;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getSslb() {
        return sslb;
    }

    public void setSslb(String sslb) {
        this.sslb = sslb;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }
}
