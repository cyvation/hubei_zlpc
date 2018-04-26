package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 通知公告
 * Created by lei on 2017/10/31.
 */
public class Tzgg {

    //编号，年份4位+6位顺序号
    @JSONField(name = "BH")
    private String bh ;
    //单位编码
    @JSONField(name = "FBRDWBM")
    private String fbrdwbm;
    //单位名称
    @JSONField(name = "FBRDWMC")
    private String fbrdwmc;
    //工号
    @JSONField(name = "FBRGH")
    private String fbrgh;
    //姓名
    @JSONField(name = "FBRXM")
    private String fbrxm;
    //类型编码
    @JSONField(name = "LXDM")
    private String lxdm;
    //类型名称
    @JSONField(name = "LXMC")
    private String lxmc;
    //标题
    @JSONField(name = "BT")
    private String bt;
    //内容
    @JSONField(name = "NR")
    private String nr;
    //创建时间
    @JSONField(name = "CJSJ")
    private String cjsj;
    //最后修改时间
    @JSONField(name = "ZHXGSJ")
    private String zhxgsj;
    //是否删除
    @JSONField(name = "SFSC")
    private String sfsc ;

    //公告时间
    @JSONField(name = "GHSJ")
    private String ghsj;
    //是否下级院可见
    @JSONField(name = "SFJYKJ")
    private String SFJYKJ;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getFbrdwbm() {
        return fbrdwbm;
    }

    public void setFbrdwbm(String fbrdwbm) {
        this.fbrdwbm = fbrdwbm;
    }

    public String getFbrdwmc() {
        return fbrdwmc;
    }

    public void setFbrdwmc(String fbrdwmc) {
        this.fbrdwmc = fbrdwmc;
    }

    public String getFbrgh() {
        return fbrgh;
    }

    public void setFbrgh(String fbrgh) {
        this.fbrgh = fbrgh;
    }

    public String getFbrxm() {
        return fbrxm;
    }

    public void setFbrxm(String fbrxm) {
        this.fbrxm = fbrxm;
    }

    public String getLxdm() {
        return lxdm;
    }

    public void setLxdm(String lxdm) {
        this.lxdm = lxdm;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getGhsj() {
        return ghsj;
    }

    public void setGhsj(String ghsj) {
        this.ghsj = ghsj;
    }

    public String getSFJYKJ() {
        return SFJYKJ;
    }

    public void setSFJYKJ(String SFJYKJ) {
        this.SFJYKJ = SFJYKJ;
    }
}
