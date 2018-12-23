package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.List;

/**
 * 案件质量情况分析参数类
 * 李志恒 2018年5月14日
 */
public class Param_Ajqkzlfx {

    @JSONField(name = "DWBM")
    private String dwbm;

    @JSONField(name = "PCFLBM")
    private String pcflbm;

    @JSONField(name = "YWTX")
    private String ywtx;

    @JSONField(name = "YWTXMC")
    private String ywtxmc;

    @JSONField(name = "WCRQNF")
    private String wcrqnf;

    private String stajbs;

    private String sfld;

    private String startDate;

    private String endDate;

    private String pcstartDate;

    private String pcendDate;

    private List<String> gzbmj;

    @JSONField(name = "TJTYPE")
    private String tjType;

    @JSONField(name = "CBRSF")
    private String cbrsf;

    @JSONField(name = "WTTYPE")
    private  String wtType;

    @JSONField(name = "PCJL")
    private  String pcjl;

    @JSONField(name = "AJLBBM")
    private String ajlbbm;

    @JSONField(name = "AJTJLB")
    private String ajtjlb;

    @JSONField(name = "SM")
    private String sm;

    @JSONField(name = "XTDM")
    private String xtdm;

    @JSONField(name = "FFLXTDM")
    private String fflxtdm;

    @JSONField(name = "PAGE")
    private int page;

    @JSONField(name = "ROWS")
    private int rows;



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

    public String getYwtx() {
        return ywtx;
    }

    public void setYwtx(String ywtx) {
        this.ywtx = ywtx;
    }

    public String getWcrqnf() {
        return wcrqnf;
    }

    public void setWcrqnf(String wcrqnf) {
        this.wcrqnf = wcrqnf;
    }

    public String getTjType() {
        return tjType;
    }

    public void setTjType(String tjType) {
        this.tjType = tjType;
    }

    public String getCbrsf() {
        return cbrsf;
    }

    public void setCbrsf(String cbrsf) {
        this.cbrsf = cbrsf;
    }

    public String getYwtxmc() {
        return ywtxmc;
    }

    public void setYwtxmc(String ywtxmc) {
        this.ywtxmc = ywtxmc;
    }

    public String getWtType() {
        return wtType;
    }

    public void setWtType(String wtType) {
        this.wtType = wtType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }

    public String getAjlbbm() {
        return ajlbbm;
    }

    public void setAjlbbm(String ajlbbm) {
        this.ajlbbm = ajlbbm;
    }

    public String getAjtjlb() {
        return ajtjlb;
    }

    public void setAjtjlb(String ajtjlb) {
        this.ajtjlb = ajtjlb;
    }

    public String getXtdm() {
        return xtdm;
    }

    public void setXtdm(String xtdm) {
        this.xtdm = xtdm;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getFflxtdm() {
        return fflxtdm;
    }

    public void setFflxtdm(String fflxtdm) {
        this.fflxtdm = fflxtdm;
    }

    public String getStajbs() {
        return stajbs;
    }

    public void setStajbs(String stajbs) {
        this.stajbs = stajbs;
    }

    public String getSfld() {
        return sfld;
    }

    public void setSfld(String sfld) {
        this.sfld = sfld;
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
        if (!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
        }
        this.endDate = endDate;
    }

    public String getPcstartDate() {
        return pcstartDate;
    }

    public void setPcstartDate(String pcstartDate) {
        this.pcstartDate = pcstartDate;
    }

    public String getPcendDate() {
        return pcendDate;
    }

    public void setPcendDate(String pcendDate) {
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate =  DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
        }
        this.pcendDate = pcendDate;
    }

    public List<String> getGzbmj() {
        return gzbmj;
    }

    public void setGzbmj(List<String> gzbmj) {
        this.gzbmj = gzbmj;
    }


}
