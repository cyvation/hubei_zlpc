package com.start.boot.pojo.vo;


import com.start.boot.domain.Yx_Pc_Pcx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端展示的vo
 * Created by caomin on 2017/11/15.
 */
public class Yx_Pc_PcxFlVo {
    private String pcslbm;

    private String pcxflbm;

    private String pcxflmc;

    private String pcjg;

    private String pcyj;

    private Date jlsj;

    private String pcrdwbm;

    private String pcrdwmc;

    private String pcrgh;

    private String pcrmc;

    private String sm;

    private String pcxflfbm;

    private String pcmbbm;

    private String pcjlbm;

    private Short xh;

    private String sftj;

    private List<Yx_Pc_PcxFlVo> children=new ArrayList<>();
    private List<Yx_Pc_Pcx>pcxList=new ArrayList<>();

    public List<Yx_Pc_PcxFlVo> getChildren() {
        return children;
    }

    public void setChildren(List<Yx_Pc_PcxFlVo> children) {
        this.children = children;
    }

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }

    public String getPcxflbm() {
        return pcxflbm;
    }

    public void setPcxflbm(String pcxflbm) {
        this.pcxflbm = pcxflbm;
    }

    public String getPcxflmc() {
        return pcxflmc;
    }

    public void setPcxflmc(String pcxflmc) {
        this.pcxflmc = pcxflmc;
    }

    public String getPcjg() {
        return pcjg;
    }

    public void setPcjg(String pcjg) {
        this.pcjg = pcjg;
    }

    public String getPcyj() {
        return pcyj;
    }

    public void setPcyj(String pcyj) {
        this.pcyj = pcyj;
    }

    public Date getJlsj() {
        return jlsj;
    }

    public void setJlsj(Date jlsj) {
        this.jlsj = jlsj;
    }

    public String getPcrdwbm() {
        return pcrdwbm;
    }

    public void setPcrdwbm(String pcrdwbm) {
        this.pcrdwbm = pcrdwbm;
    }

    public String getPcrdwmc() {
        return pcrdwmc;
    }

    public void setPcrdwmc(String pcrdwmc) {
        this.pcrdwmc = pcrdwmc;
    }

    public String getPcrgh() {
        return pcrgh;
    }

    public void setPcrgh(String pcrgh) {
        this.pcrgh = pcrgh;
    }

    public String getPcrmc() {
        return pcrmc;
    }

    public void setPcrmc(String pcrmc) {
        this.pcrmc = pcrmc;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getPcxflfbm() {
        return pcxflfbm;
    }

    public void setPcxflfbm(String pcxflfbm) {
        this.pcxflfbm = pcxflfbm;
    }

    public String getPcmbbm() {
        return pcmbbm;
    }

    public void setPcmbbm(String pcmbbm) {
        this.pcmbbm = pcmbbm;
    }

    public String getPcjlbm() {
        return pcjlbm;
    }

    public void setPcjlbm(String pcjlbm) {
        this.pcjlbm = pcjlbm;
    }

    public Short getXh() {
        return xh;
    }

    public void setXh(Short xh) {
        this.xh = xh;
    }

    public String getSftj() {
        return sftj;
    }

    public void setSftj(String sftj) {
        this.sftj = sftj;
    }

    public List<Yx_Pc_Pcx> getPcxList() {
        return pcxList;
    }

    public void setPcxList(List<Yx_Pc_Pcx> pcxList) {
        this.pcxList = pcxList;
    }
}
