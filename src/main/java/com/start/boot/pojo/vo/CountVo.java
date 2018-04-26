package com.start.boot.pojo.vo;

/**
 * 返回给前端的统计对象
 */
public class CountVo {
    private String pcjg;
    private Integer name;
    private Object value;

    public String getPcjg() {
        return pcjg;
    }

    public void setPcjg(String pcjg) {
        this.pcjg = pcjg;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
