package com.start.boot.pojo.dto;


public class ZdFxDto {

    public String id;
    private String name; // treegrdi要显示的名称
    public String pid;
    private Integer bjcount; // 办结数
    private Integer pccount; // 评查数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getBjcount() {
        return bjcount;
    }

    public void setBjcount(Integer bjcount) {
        this.bjcount = bjcount;
    }

    public Integer getPccount() {
        return pccount;
    }

    public void setPccount(Integer pccount) {
        this.pccount = pccount;
    }
}
