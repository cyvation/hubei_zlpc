package com.start.boot.query;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author caomin
 * @date 2018/4/4
 * @说明 评查检索分组查询对象
 */
public class MonitoryPcjsQuery    {
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;

    private List<String>pcDwbmList;
    private List<String>bpcBmbmList;
    private List<String>pcflbmList;
    private List<String>sxgzbmList;
    private List<String>pcjlList;
    private List<String>pcjdbhList;



    private String pcrmc;
    private String bpcmc;

    private String type;
    private String qx_type;
    private String dwbm;
    private String gh;


    public List<String> getPcjdbhList() {
        return pcjdbhList;
    }

    public void setPcjdbhList(List<String> pcjdbhList) {
        this.pcjdbhList = pcjdbhList;
    }

    public String getPcrmc() {
        return pcrmc;
    }

    public void setPcrmc(String pcrmc) {
        this.pcrmc = pcrmc;
    }

    public String getBpcmc() {
        return bpcmc;
    }

    public void setBpcmc(String bpcmc) {
        this.bpcmc = bpcmc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getPcDwbmList() {
        return pcDwbmList;
    }

    public void setPcDwbmList(List<String> pcDwbmList) {
        this.pcDwbmList = pcDwbmList;
    }

    public List<String> getBpcBmbmList() {
        return bpcBmbmList;
    }

    public void setBpcBmbmList(List<String> bpcBmbmList) {
        this.bpcBmbmList = bpcBmbmList;
    }

    public List<String> getPcflbmList() {
        return pcflbmList;
    }

    public void setPcflbmList(List<String> pcflbmList) {
        this.pcflbmList = pcflbmList;
    }

    public List<String> getSxgzbmList() {
        return sxgzbmList;
    }

    public void setSxgzbmList(List<String> sxgzbmList) {
        this.sxgzbmList = sxgzbmList;
    }

    public List<String> getPcjlList() {
        return pcjlList;
    }

    public void setPcjlList(List<String> pcjlList) {
        this.pcjlList = pcjlList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQx_type() {
        return qx_type;
    }

    public void setQx_type(String qx_type) {
        this.qx_type = qx_type;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }
}
