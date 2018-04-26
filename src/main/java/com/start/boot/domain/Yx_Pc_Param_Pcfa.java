package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */
public class Yx_Pc_Param_Pcfa {

    @JSONField(name = "PCDWBM")
    private String PCDWBM;
    @JSONField(name = "PCFLBM")
    private String PCFLBM;
    @JSONField(name = "PCHDBM")
    private String PCHDBM;
    @JSONField(name = "PCHDMC")
    private String PCHDMC;
    @JSONField(name = "SXGZJ")
    private String SXGZJ;
    @JSONField(name = "PCMBJ")
    private String PCMBJ;
    @JSONField(name = "SM")
    private String SM;
    @JSONField(name = "SFSS")
    private String SFSS;
    @JSONField(name = "SFFY")
    private String sffy;
    @JSONField(name = "PCKSSJ")
    private String PCKSSJ;
    @JSONField(name = "PCJSSJ")
    private String PCJSSJ;
    @JSONField(name = "CJRDWBM")
    private String CJRDWBM;
    @JSONField(name = "CJRDWMC")
    private String CJRDWMC;
    @JSONField(name = "CJRGH")
    private String CJRGH;
    @JSONField(name = "CJRMC")
    private String CJRMC;
    @JSONField(name = "CJSJ")
    private String CJSJ;

    public String getPCFALJ() {
        return PCFALJ;
    }

    public void setPCFALJ(String PCFALJ) {
        this.PCFALJ = PCFALJ;
    }

    @JSONField(name = "PCFALJ")
    private String PCFALJ;
    @JSONField(name = "PCZLB")
    private List<YX_PC_FZ> PCZLB;
    @JSONField(name = "PCAJLB")
    private List<YX_PC_JBXX> PCAJLB;

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

    public String getSXGZJ() {
        return SXGZJ;
    }

    public void setSXGZJ(String SXGZJ) {
        this.SXGZJ = SXGZJ;
    }

    public String getPCMBJ() {
        return PCMBJ;
    }

    public void setPCMBJ(String PCMBJ) {
        this.PCMBJ = PCMBJ;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public String getSFSS() {
        return SFSS;
    }

    public void setSFSS(String SFSS) {
        this.SFSS = SFSS;
    }

    public String getSffy() {
        return sffy;
    }

    public void setSffy(String sffy) {
        this.sffy = sffy;
    }

    public String getPCKSSJ() {
        return PCKSSJ;
    }

    public void setPCKSSJ(String PCKSSJ) {
        this.PCKSSJ = PCKSSJ;
    }

    public String getPCJSSJ() {
        return PCJSSJ;
    }

    public void setPCJSSJ(String PCJSSJ) {
        this.PCJSSJ = PCJSSJ;
    }

    public String getCJRDWBM() {
        return CJRDWBM;
    }

    public void setCJRDWBM(String CJRDWBM) {
        this.CJRDWBM = CJRDWBM;
    }

    public String getCJRDWMC() {
        return CJRDWMC;
    }

    public void setCJRDWMC(String CJRDWMC) {
        this.CJRDWMC = CJRDWMC;
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

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public List<YX_PC_FZ> getPCZLB() {
        return PCZLB;
    }

    public void setPCZLB(List<YX_PC_FZ> PCZLB) {
        this.PCZLB = PCZLB;
    }

    public List<YX_PC_JBXX> getPCAJLB() {
        return PCAJLB;
    }

    public void setPCAJLB(List<YX_PC_JBXX> PCAJLB) {
        this.PCAJLB = PCAJLB;
    }
}
