package com.start.boot.pojo.vo;

/**
 * 案件情况质量分析实体类
 * 李志恒 2018年5月15日
 */
public class AjqkzlflVo {

    private String name; //名称

    private int bjs; //办结数

    private int pcajs; //评查案件数

    private String pcajZb; //评查案件占比

    private int yzajs; //优质案件数

    private String yzajZb; //优质案件占比

    private int hgajs; //合格案件数

    private String hgajZb;//合格案件占比

    private int xcajs;//瑕疵案件数

    private String xcajZb;//瑕疵案件占比

    private int bhgajs;//不合格案件数

    private String bhgajZb;//不合格案件占比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBjs() {
        return bjs;
    }

    public void setBjs(int bjs) {
        this.bjs = bjs;
    }

    public int getPcajs() {
        return pcajs;
    }

    public void setPcajs(int pcajs) {
        this.pcajs = pcajs;
    }

    public String getPcajZb() {
        return pcajZb;
    }

    public void setPcajZb(String pcajZb) {
        this.pcajZb = pcajZb;
    }

    public int getYzajs() {
        return yzajs;
    }

    public void setYzajs(int yzajs) {
        this.yzajs = yzajs;
    }

    public String getYzajZb() {
        return yzajZb;
    }

    public void setYzajZb(String yzajZb) {
        this.yzajZb = yzajZb;
    }

    public int getHgajs() {
        return hgajs;
    }

    public void setHgajs(int hgajs) {
        this.hgajs = hgajs;
    }

    public String getHgajZb() {
        return hgajZb;
    }

    public void setHgajZb(String hgajZb) {
        this.hgajZb = hgajZb;
    }

    public int getXcajs() {
        return xcajs;
    }

    public void setXcajs(int xcajs) {
        this.xcajs = xcajs;
    }

    public String getXcajZb() {
        return xcajZb;
    }

    public void setXcajZb(String xcajZb) {
        this.xcajZb = xcajZb;
    }

    public int getBhgajs() {
        return bhgajs;
    }

    public void setBhgajs(int bhgajs) {
        this.bhgajs = bhgajs;
    }

    public String getBhgajZb() {
        return bhgajZb;
    }

    public void setBhgajZb(String bhgajZb) {
        this.bhgajZb = bhgajZb;
    }
}


