package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.start.boot.common.Param_Pager;

/**
 * Created by Administrator on 2017/11/5.
 */

// 评查案件
public class Param_Pcaj extends Param_Pager {

    @JSONField(name = "PCDWBM")
    private String pcdwbm;
    @JSONField(name = "PCFLBM")
    private String pcflbm;
    @JSONField(name = "PCHDBM")
    private String pchdbm;
    @JSONField(name = "PCMBBM")
    private String pcmbbm;
    @JSONField(name = "DWBM")
    private String dwbm;
    @JSONField(name = "BMSAH")
    private String bmsah;
    @JSONField(name = "AJMC")
    private String ajmc;
    @JSONField(name = "AJLBMC")
    private String ajlbmc;
    @JSONField(name = "DWMC")
    private String dwmc;
    @JSONField(name = "CBR")
    private String cbr;
    @JSONField(name = "SLRQ")
    private String slrq;
    @JSONField(name = "WCBZRQ")
    private String wcbzrq;
    @JSONField(name = "SXGZBM")
    private String sxgzbm;
    @JSONField(name = "PCSLBM")
    private String pcslbm;
    @JSONField(name = "PCSAH")
    private String pcsah;
    @JSONField(name = "PCZ_BM")
    private String pcz_bm;
    @JSONField(name = "PCZ_MC")
    private String pcz_mc;
    @JSONField(name = "PCR_DWBM")
    private String pcr_dwbm;
    @JSONField(name = "PCR_DWMC")
    private String pcr_dwmc;
    @JSONField(name = "PCR_GH")
    private String pcr_gh;
    @JSONField(name = "PCR_MC")
    private String pcr_mc;
    @JSONField(name = "PCKSSJ")
    private  String pckssj;
    @JSONField(name = "PCJSSJ")
    private  String pcjssj;


    public String getPckssj() {
        return pckssj;
    }

    public void setPckssj(String pckssj) {
        this.pckssj = pckssj;
    }

    public String getPcjssj() {
        return pcjssj;
    }

    public void setPcjssj(String pcjssj) {
        this.pcjssj = pcjssj;
    }

    public String getPcdwbm() {
        return pcdwbm;
    }

    public void setPcdwbm(String pcdwbm) {
        this.pcdwbm = pcdwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getPcmbbm() {
        return pcmbbm;
    }

    public void setPcmbbm(String pcmbbm) {
        this.pcmbbm = pcmbbm;
    }

    public String getPcz_bm() {
        return pcz_bm;
    }

    public void setPcz_bm(String pcz_bm) {
        this.pcz_bm = pcz_bm;
    }

    public String getPcz_mc() {
        return pcz_mc;
    }

    public void setPcz_mc(String pcz_mc) {
        this.pcz_mc = pcz_mc;
    }

    public String getPcr_dwbm() {
        return pcr_dwbm;
    }

    public void setPcr_dwbm(String pcr_dwbm) {
        this.pcr_dwbm = pcr_dwbm;
    }

    public String getPcr_dwmc() {
        return pcr_dwmc;
    }

    public void setPcr_dwmc(String pcr_dwmc) {
        this.pcr_dwmc = pcr_dwmc;
    }

    public String getPcr_gh() {
        return pcr_gh;
    }

    public void setPcr_gh(String pcr_gh) {
        this.pcr_gh = pcr_gh;
    }

    public String getPcr_mc() {
        return pcr_mc;
    }

    public void setPcr_mc(String pcr_mc) {
        this.pcr_mc = pcr_mc;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getAjlbmc() {
        return ajlbmc;
    }

    public void setAjlbmc(String ajlbmc) {
        this.ajlbmc = ajlbmc;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getCbr() {
        return cbr;
    }

    public void setCbr(String cbr) {
        this.cbr = cbr;
    }

    public String getSlrq() {
        return slrq;
    }

    public void setSlrq(String slrq) {
        this.slrq = slrq;
    }

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }

    public String getPcsah() {
        return pcsah;
    }

    public void setPcsah(String pcsah) {
        this.pcsah = pcsah;
    }

    public String getWcbzrq() {
        return wcbzrq;
    }

    public void setWcbzrq(String wcbzrq) {
        this.wcbzrq = wcbzrq;
    }

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public String getBmsah() {
        return bmsah;
    }

    public void setBmsah(String bmsah) {
        this.bmsah = bmsah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getPcah() {
        return pcah;
    }

    public void setPcah(String pcah) {
        this.pcah = pcah;
    }

    private String pcah;
}
