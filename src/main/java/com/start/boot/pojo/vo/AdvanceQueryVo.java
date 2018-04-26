package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 高级查询前端返回实体
 *
 * @caomin
 * @create 2017-12-13 11:36
 **/
@ApiModel("高级查询前端对象")
public class AdvanceQueryVo {

    @ApiModelProperty("组件编号")
    private String bh;


    @ApiModelProperty("单位编码")
    private String dwbm;

    @ApiModelProperty("组件名称")
    private String mc;

    @ApiModelProperty("组件类型")
    private String lx;
    @ApiModelProperty("处理程序名称")
    private String cx;

    @ApiModelProperty("父节点编号")
    private String parent;
    @ApiModelProperty("编号")
    private String bm;
    @ApiModelProperty("关联字段")
    private String gyzd;

    @ApiModelProperty("是否多选（0否，1是）")
    private String sfdx;

    @ApiModelProperty("组件可选的运算操作符号")
    private List<Object> ysf=new ArrayList<>();

    @ApiModelProperty("组件可选的值")
    private Object value=new ArrayList();


    public String getGyzd() {
        return gyzd;
    }

    public void setGyzd(String gyzd) {
        this.gyzd = gyzd;
    }
    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }
    public String getParent() {
        return parent;
    }

    public String getSfdx() {
        return sfdx;
    }

    public void setSfdx(String sfdx) {
        this.sfdx = sfdx;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public List<Object> getYsf() {
        return ysf;
    }

    public void setYsf(List<Object> ysf) {
        this.ysf = ysf;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
