package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.start.boot.common.Param_Pager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评查页面传接受类
 * Created by lei on 2017/11/2.
 */
public class ParamSx extends Param_Pager implements Serializable {

    @JSONField(name = "Columns")
    private List<Map> listcols=new ArrayList<>();
    @JSONField(name = "Rows")
    private List<Map> listrows =new ArrayList<>();
    @JSONField(name = "KeyValues")
    private List<Map> listdata=new ArrayList<>();
    //评查单位编码
    private String pcdwbm;
    //评查分类编码
    private String pcflbm;
    //评查活动编码
    private String pchdbm;
    //筛选规则所属单位编码
    private String gzdwbm;
    //筛选规则编码
    private String sxgzbm;
    //承办单位编码
    private String cbdwbm;
    //承办部门编码
    private String cbbmbm;
    //案件类别编码集合
    private String ajlb;
    //部门受案号
    private String bmsah;
    //案件名称
    private String ajmc;
    //承办人姓名
    private String cbrxm;
    //承办人工号
    private String cbrgh;
    //案由

    public String getCbrgh() {
        return cbrgh;
    }

    public void setCbrgh(String cbrgh) {
        this.cbrgh = cbrgh;
    }

    private String ay;

    //日期----
    //受理日期开始时间
    private String slrqbng;
    //受理日期结束时间
    private String slrqend;
    //办结日期开始时间
    private String bjrqbng;
    //办结日期结束时间
    private String bjrqend;
    //完成日期开始时间
    private String wcrqbng;
    //完成日期结束时间
    private String wcrqend;
    //归档日期开始时间
    private String gdrqbng;
    //归档日期结束时间
    private String gdrqend;
    //筛选规则匹配日期开始时间
    private String gzrqbng;
    //筛选规则匹配日期结束时间
    private String gzrqend;
    //自定义查询条件
    private String zdycxtj;
    // 查询类型
    private String type;

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

    public String getGzdwbm() {
        return gzdwbm;
    }

    public void setGzdwbm(String gzdwbm) {
        this.gzdwbm = gzdwbm;
    }

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public String getCbdwbm() {
        return cbdwbm;
    }

    public void setCbdwbm(String cbdwbm) {
        this.cbdwbm = cbdwbm;
    }

    public String getCbbmbm() {
        return cbbmbm;
    }

    public void setCbbmbm(String cbbmbm) {
        this.cbbmbm = cbbmbm;
    }

    public String getAjlb() {
        return ajlb;
    }

    public void setAjlb(String ajlb) {
        this.ajlb = ajlb;
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

    public String getCbrxm() {
        return cbrxm;
    }

    public void setCbrxm(String cbrxm) {
        this.cbrxm = cbrxm;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getSlrqbng() {
        return slrqbng;
    }

    public void setSlrqbng(String slrqbng) {
        this.slrqbng = slrqbng;
    }

    public String getSlrqend() {
        return slrqend;
    }

    public void setSlrqend(String slrqend) {
        this.slrqend = slrqend;
    }

    public String getBjrqbng() {
        return bjrqbng;
    }

    public void setBjrqbng(String bjrqbng) {
        this.bjrqbng = bjrqbng;
    }

    public String getBjrqend() {
        return bjrqend;
    }

    public void setBjrqend(String bjrqend) {
        this.bjrqend = bjrqend;
    }

    public String getWcrqbng() {
        return wcrqbng;
    }

    public void setWcrqbng(String wcrqbng) {
        this.wcrqbng = wcrqbng;
    }

    public String getWcrqend() {
        return wcrqend;
    }

    public void setWcrqend(String wcrqend) {
        this.wcrqend = wcrqend;
    }

    public String getGdrqbng() {
        return gdrqbng;
    }

    public void setGdrqbng(String gdrqbng) {
        this.gdrqbng = gdrqbng;
    }

    public String getGdrqend() {
        return gdrqend;
    }

    public void setGdrqend(String gdrqend) {
        this.gdrqend = gdrqend;
    }

    public String getGzrqbng() {
        return gzrqbng;
    }

    public void setGzrqbng(String gzrqbng) {
        this.gzrqbng = gzrqbng;
    }

    public String getGzrqend() {
        return gzrqend;
    }

    public void setGzrqend(String gzrqend) {
        this.gzrqend = gzrqend;
    }

    public String getZdycxtj() {
        return zdycxtj;
    }

    public void setZdycxtj(String zdycxtj) {
        this.zdycxtj = zdycxtj;
    }

    public List<Map> getListcols() {
        return listcols;
    }

    public void setListcols(List<Map> listcols) {
        this.listcols = listcols;
    }

    public List<Map> getListrows() {
        return listrows;
    }

    public void setListrows(List<Map> listrows) {
        this.listrows = listrows;
    }

    public List<Map> getListdata() {
        return listdata;
    }

    public void setListdata(List<Map> listdata) {
        this.listdata = listdata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
