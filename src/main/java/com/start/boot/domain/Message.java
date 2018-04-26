package com.start.boot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("消息实体")
public class Message extends MessageKey {
    private Date fsrq;
    @ApiModelProperty("接收人单位编码")
    private String jsrdwbm;
    @ApiModelProperty("接收人工号")
    private String jsrgh;
    @ApiModelProperty
            ("消息类型： 0评查结束的消息通知，1.待审批通知，2.审批结果通知，" +
            "3.评查结果待反馈  4.承办人消息反馈通知,6。部门领导待审批 7.送审案件被退回")
    private String xxlx;
    @ApiModelProperty("消息标题")
    private String xxbt;
    @ApiModelProperty("消息内容")
    private String xxnr;

    @ApiModelProperty("关联部门受案号")
    private String glbmsah;
    @ApiModelProperty("消息状态 0未读，1已读")
    private String xxzt;

    @ApiModelProperty("是否删除 Y已删除，N未删除")
    private String sfsc;

    private Date zhxgsj;
    @ApiModelProperty("说明")
    private String sm;
    @ApiModelProperty("是否发送 0，未发送。1已发送")
    private String sffs;

    public Date getFsrq() {
        return fsrq;
    }

    public void setFsrq(Date fsrq) {
        this.fsrq = fsrq;
    }

    public String getJsrdwbm() {
        return jsrdwbm;
    }

    public void setJsrdwbm(String jsrdwbm) {
        this.jsrdwbm = jsrdwbm == null ? null : jsrdwbm.trim();
    }

    public String getJsrgh() {
        return jsrgh;
    }

    public void setJsrgh(String jsrgh) {
        this.jsrgh = jsrgh == null ? null : jsrgh.trim();
    }

    public String getXxlx() {
        return xxlx;
    }

    public void setXxlx(String xxlx) {
        this.xxlx = xxlx == null ? null : xxlx.trim();
    }

    public String getXxbt() {
        return xxbt;
    }

    public void setXxbt(String xxbt) {
        this.xxbt = xxbt == null ? null : xxbt.trim();
    }

    public String getXxnr() {
        return xxnr;
    }

    public void setXxnr(String xxnr) {
        this.xxnr = xxnr == null ? null : xxnr.trim();
    }

    public String getGlbmsah() {
        return glbmsah;
    }

    public void setGlbmsah(String glbmsah) {
        this.glbmsah = glbmsah == null ? null : glbmsah.trim();
    }

    public String getXxzt() {
        return xxzt;
    }

    public void setXxzt(String xxzt) {
        this.xxzt = xxzt == null ? null : xxzt.trim();
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc == null ? null : sfsc.trim();
    }

    public Date getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(Date zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getSffs() {
        return sffs;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs == null ? null : sffs.trim();
    }
}