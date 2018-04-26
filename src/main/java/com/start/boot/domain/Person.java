package com.start.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("案件")
public class Person {

    private String id;
    @ApiModelProperty("资料类型")
    private Integer zllx;
    @ApiModelProperty("关联资源")
    private String gyzy;
    @ApiModelProperty("资料名称")
    private String zlmc;


    @ApiModelProperty("收藏时间")
    private Date cjsj;
    @ApiModelProperty("单位编码")
    private String dwbm;
    @ApiModelProperty("工号")
    private String gh;
    @ApiModelProperty("是否删除")
    private String sfsc;
    @ApiModelProperty("共享编码")
    private String gxbm;
    @ApiModelProperty("最后修改时间")
    private Date zhxgsj;
    @ApiModelProperty("推荐指数")
    private String tjzs;
    @ApiModelProperty("推荐理由")
    private String tjly;

    public String getTjzs() {
        return tjzs;
    }

    public void setTjzs(String tjzs) {
        this.tjzs = tjzs;
    }

    public String getTjly() {
        return tjly;
    }

    public void setTjly(String tjly) {
        this.tjly = tjly;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getZllx() {
        return zllx;
    }

    public void setZllx(Integer zllx) {
        this.zllx = zllx;
    }

    public String getGyzy() {
        return gyzy;
    }

    public void setGyzy(String gyzy) {
        this.gyzy = gyzy;
    }

    public String getZlmc() {
        return zlmc;
    }

    public void setZlmc(String zlmc) {
        this.zlmc = zlmc;
    }



    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
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

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getGxbm() {
        return gxbm;
    }

    public void setGxbm(String gxbm) {
        this.gxbm = gxbm;
    }

    public Date getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(Date zhxgsj) {
        this.zhxgsj = zhxgsj;
    }
}