package com.start.boot.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * 高级查询，前端查询对象
 * @caomin
 * @create 2017-12-14 10:18
 **/
public class AdvanceQueryView {

    @ApiModelProperty("编号")
    private String bh;

    @ApiModelProperty("别名")
    private String bm;

    @ApiModelProperty("字段名")
    private String key;

    @ApiModelProperty("运算符")
    private String ysf;

    @ApiModelProperty("父节点")
    private String parent;

    @ApiModelProperty("值")
    private  String value;

    public String getParent() {
        return parent;
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

    public String getKey() {
        return key;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getYsf() {
        return ysf;
    }

    public void setYsf(String ysf) {
        this.ysf = ysf;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
