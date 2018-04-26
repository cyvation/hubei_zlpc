package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/11/14.
 */
public class Param_Pchdzt {

    // 评查活动编码
    @JSONField(name = "PCHDBM")
    private String pchdbm;

    @JSONField(name = "HDZT_BM")

    private String hdzt_bm;

    @JSONField(name = "HDZT_MS")
    private String hdzt_ms;

    @JSONField(name = "SPJL")
    private String spjl;

    @JSONField(name = "SSRDWBM")
    private String ssrdwbm;

    @JSONField(name = "SSRGH")
    private String ssrgh;

    @JSONField(name = "SPRDWBM")
    private String sprdwbm;

    @JSONField(name = "SPRGH")
    private String sprgh;

    @JSONField(name = "PCSPBM")
    private String pcspbm;

    @JSONField(name = "SFQD")
    private String sfqd;

    @JSONField(name = "SFJS")
    private String sfjs;

    @JSONField(name = "QDR_DWBM")
    private String qdr_dwbm;

    @JSONField(name = "QDR_GH")
    private String qdr_gh;

    @JSONField(name = "JSR_DWBM")
    private String jsr_dwbm;

    @JSONField(name = "JSR_GH")
    private String jsr_gh;


    public String getSfqd() {
        return sfqd;
    }

    public void setSfqd(String sfqd) {
        this.sfqd = sfqd;
    }

    public String getSfjs() {
        return sfjs;
    }

    public void setSfjs(String sfjs) {
        this.sfjs = sfjs;
    }

    public String getQdr_dwbm() {
        return qdr_dwbm;
    }

    public void setQdr_dwbm(String qdr_dwbm) {
        this.qdr_dwbm = qdr_dwbm;
    }

    public String getQdr_gh() {
        return qdr_gh;
    }

    public void setQdr_gh(String qdr_gh) {
        this.qdr_gh = qdr_gh;
    }

    public String getJsr_dwbm() {
        return jsr_dwbm;
    }

    public void setJsr_dwbm(String jsr_dwbm) {
        this.jsr_dwbm = jsr_dwbm;
    }

    public String getJsr_gh() {
        return jsr_gh;
    }

    public void setJsr_gh(String jsr_gh) {
        this.jsr_gh = jsr_gh;
    }

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getHdzt_bm() {
        return hdzt_bm;
    }

    public void setHdzt_bm(String hdzt_bm) {
        this.hdzt_bm = hdzt_bm;
    }

    public String getHdzt_ms() {
        return hdzt_ms;
    }

    public void setHdzt_ms(String hdzt_ms) {
        this.hdzt_ms = hdzt_ms;
    }

    public String getSpjl() {
        return spjl;
    }

    public void setSpjl(String spjl) {
        this.spjl = spjl;
    }

    public String getSprdwbm() {
        return sprdwbm;
    }

    public void setSprdwbm(String sprdwbm) {
        this.sprdwbm = sprdwbm;
    }

    public String getSprgh() {
        return sprgh;
    }

    public void setSprgh(String sprgh) {
        this.sprgh = sprgh;
    }

    public String getPcspbm() {
        return pcspbm;
    }

    public void setPcspbm(String pcspbm) {
        this.pcspbm = pcspbm;
    }

    public String getSsrdwbm() {
        return ssrdwbm;
    }

    public void setSsrdwbm(String ssrdwbm) {
        this.ssrdwbm = ssrdwbm;
    }

    public String getSsrgh() {
        return ssrgh;
    }

    public void setSsrgh(String ssrgh) {
        this.ssrgh = ssrgh;
    }

}
