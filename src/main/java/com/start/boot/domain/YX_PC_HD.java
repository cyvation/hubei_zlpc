package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class YX_PC_HD {

    @JSONField(name = "PCHDBM")
    private String PCHDBM;

    @JSONField(name = "PCHDMC")
    private String PCHDMC;

    @JSONField(name = "PCDWBM")
    private String PCDWBM;

    @JSONField(name = "PCFLBM")
    private String PCFLBM;


    @JSONField(name = "PCFLMC")
    private String PCFLMC;

    @JSONField(name = "SFSS")
    private String SFSS;

    @JSONField(name = "SFFY")
    private String SFFY;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")
    @JSONField(name = "PCKSSJ",format = "yyyy-MM-dd")
    private Date PCKSSJ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")
    @JSONField(name = "PCJSSJ",format = "yyyy-MM-dd")
    private Date PCJSSJ;

    @JSONField(name = "XH")
    private Integer XH;

    @JSONField(name = "CJRDWBM")
    private String CJRDWBM;

    @JSONField(name = "CJRGH")
    private String CJRGH;

    @JSONField(name = "CJRMC")
    private String CJRMC;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")
    @JSONField(name = "CJSJ",format = "yyyy-MM-dd")
    private Date CJSJ;

    @JSONField(name = "SFQD")
    private String SFQD;

    @JSONField(name = "QDRDWBM")
    private String QDRDWBM;

    @JSONField(name = "QDRGH")
    private String QDRGH;

    @JSONField(name = "QDRMC")
    private String QDRMC;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")
    @JSONField(name = "QDSJ",format = "yyyy-MM-dd")
    private Date QDSJ;

    @JSONField(name = "SFJS")
    private String SFJS;

    @JSONField(name = "JSRDWBM")
    private String JSRDWBM;

    @JSONField(name = "JSRGH")
    private String JSRGH;

    @JSONField(name = "JSRMC")
    private String JSRMC;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GTM+8")
    @JSONField(name = "JSSJ",format = "yyyy-MM-dd")
    private Date JSSJ;

    @JSONField(name = "SM")
    private String SM;

    @JSONField(name = "PCMBJ")
    private String PCMBJ;

    @JSONField(name = "SFSC")
    private String SFSC;

    @JSONField(name = "SXGZJ")
    private String SXGZJ;

    public String getPCHDBM() {
        return PCHDBM;
    }

    public void setPCHDBM(String PCHDBM) {
        this.PCHDBM = PCHDBM;
    }

    public String getPCHDMC() {
        return PCHDMC;
    }

    public void setPCHDMC(String PCHDMC) {
        this.PCHDMC = PCHDMC;
    }

    public String getPCDWBM() {
        return PCDWBM;
    }

    public void setPCDWBM(String PCDWBM) {
        this.PCDWBM = PCDWBM;
    }

    public String getPCFLBM() {
        return PCFLBM;
    }

    public void setPCFLBM(String PCFLBM) {
        this.PCFLBM = PCFLBM;
    }

    public String getPCFLMC() {
        return PCFLMC;
    }

    public void setPCFLMC(String PCFLMC) {
        this.PCFLMC = PCFLMC;
    }

    public String getSFSS() {
        return SFSS;
    }

    public void setSFSS(String SFSS) {
        this.SFSS = SFSS;
    }

    public String getSFFY() {
        return SFFY;
    }

    public void setSFFY(String SFFY) {
        this.SFFY = SFFY;
    }

    public Date getPCKSSJ() {
        return PCKSSJ;
    }

    public void setPCKSSJ(Date PCKSSJ) {
        this.PCKSSJ = PCKSSJ;
    }

    public Date getPCJSSJ() {
        return PCJSSJ;
    }

    public void setPCJSSJ(Date PCJSSJ) {
        this.PCJSSJ = PCJSSJ;
    }

    public Integer getXH() {
        return XH;
    }

    public void setXH(Integer XH) {
        this.XH = XH;
    }

    public String getCJRDWBM() {
        return CJRDWBM;
    }

    public void setCJRDWBM(String CJRDWBM) {
        this.CJRDWBM = CJRDWBM;
    }

    public String getCJRGH() {
        return CJRGH;
    }

    public void setCJRGH(String CJRGH) {
        this.CJRGH = CJRGH;
    }

    public String getCJRMC() {
        return CJRMC;
    }

    public void setCJRMC(String CJRMC) {
        this.CJRMC = CJRMC;
    }

    public Date getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(Date CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getSFQD() {
        return SFQD;
    }

    public void setSFQD(String SFQD) {
        this.SFQD = SFQD;
    }

    public String getQDRDWBM() {
        return QDRDWBM;
    }

    public void setQDRDWBM(String QDRDWBM) {
        this.QDRDWBM = QDRDWBM;
    }

    public String getQDRGH() {
        return QDRGH;
    }

    public void setQDRGH(String QDRGH) {
        this.QDRGH = QDRGH;
    }

    public String getQDRMC() {
        return QDRMC;
    }

    public void setQDRMC(String QDRMC) {
        this.QDRMC = QDRMC;
    }

    public Date getQDSJ() {
        return QDSJ;
    }

    public void setQDSJ(Date QDSJ) {
        this.QDSJ = QDSJ;
    }

    public String getSFJS() {
        return SFJS;
    }

    public void setSFJS(String SFJS) {
        this.SFJS = SFJS;
    }

    public String getJSRDWBM() {
        return JSRDWBM;
    }

    public void setJSRDWBM(String JSRDWBM) {
        this.JSRDWBM = JSRDWBM;
    }

    public String getJSRGH() {
        return JSRGH;
    }

    public void setJSRGH(String JSRGH) {
        this.JSRGH = JSRGH;
    }

    public String getJSRMC() {
        return JSRMC;
    }

    public void setJSRMC(String JSRMC) {
        this.JSRMC = JSRMC;
    }

    public Date getJSSJ() {
        return JSSJ;
    }

    public void setJSSJ(Date JSSJ) {
        this.JSSJ = JSSJ;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public String getPCMBJ() {
        return PCMBJ;
    }

    public void setPCMBJ(String PCMBJ) {
        this.PCMBJ = PCMBJ;
    }

    public String getSFSC() {
        return SFSC;
    }

    public void setSFSC(String SFSC) {
        this.SFSC = SFSC;
    }

    public String getSXGZJ() {
        return SXGZJ;
    }

    public void setSXGZJ(String SXGZJ) {
        this.SXGZJ = SXGZJ;
    }
}
