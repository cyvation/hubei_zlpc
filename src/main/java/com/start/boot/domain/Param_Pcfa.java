package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

// 评查方案
public class Param_Pcfa {

    @JSONField(name = "PCDWBM")
    private String pcdwbm;
    @JSONField(name = "PCFLBM")
    private String pcflbm;
    @JSONField(name = "PCHDBM")
    private String pchdbm;
    @JSONField(name = "PCHDMC")
    private String pchdmc;
    @JSONField(name = "SXGZJ")
    private String sxgzj;
    @JSONField(name = "PCMBJ")
    private String pcmbj;
    @JSONField(name = "SM")
    private String sm;
    @JSONField(name = "SFSS")
    private String sfss;
    @JSONField(name = "SFFY")
    private String sffy;
    @JSONField(name = "PCKSSJ")
    private String pckssj;
    @JSONField(name = "PCJSSJ")
    private String pcjssj;
    @JSONField(name = "CJRDWBM")
    private String cjrdwbm;
    @JSONField(name = "CJRDWMC")
    private String cjrdwmc;
    @JSONField(name = "CJRGH")
    private String cjrgh;
    @JSONField(name = "CJRMC")
    private String cjrmc;
    @JSONField(name = "CJSJ")
    private String cjsj;
    @JSONField(name = "PCZLB")
    private List<Param_Pcz> pczlb;
    @JSONField(name = "PCAJLB")
    private List<Param_Pcaj> pcajlb;

    public String getSxgzj() {
        return sxgzj;
    }

    public void setSxgzj(String sxgzj) {
        this.sxgzj = sxgzj;
    }

    public String getSfss() {
        return sfss;
    }

    public void setSfss(String sfss) {
        this.sfss = sfss;
    }

    public String getSffy() {
        return sffy;
    }

    public void setSffy(String sffy) {
        this.sffy = sffy;
    }

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

    public String getCjrdwbm() {
        return cjrdwbm;
    }

    public void setCjrdwbm(String cjrdwbm) {
        this.cjrdwbm = cjrdwbm;
    }

    public String getCjrdwmc() {
        return cjrdwmc;
    }

    public void setCjrdwmc(String cjrdwmc) {
        this.cjrdwmc = cjrdwmc;
    }

    public String getCjrgh() {
        return cjrgh;
    }

    public void setCjrgh(String cjrgh) {
        this.cjrgh = cjrgh;
    }

    public String getCjrmc() {
        return cjrmc;
    }

    public void setCjrmc(String cjrmc) {
        this.cjrmc = cjrmc;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
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

    public String getPchdmc() {
        return pchdmc;
    }

    public void setPchdmc(String pchdmc) {
        this.pchdmc = pchdmc;
    }

    public String getPcmbj() {
        return pcmbj;
    }

    public void setPcmbj(String pcmbj) {
        this.pcmbj = pcmbj;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public List<Param_Pcz> getPczlb() {
        return pczlb;
    }

    public void setPczlb(List<Param_Pcz> pczlb) {
        this.pczlb = pczlb;
    }

    public List<Param_Pcaj> getPcajlb() {
        return pcajlb;
    }

    public void setPcajlb(List<Param_Pcaj> pcajlb) {
        this.pcajlb = pcajlb;
    }
}
