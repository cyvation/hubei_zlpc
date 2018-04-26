package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by lei on 2017/11/12.
 */

// 评查审批实体类
public class Param_Pcsp {

    // 评查活动编码
    @JSONField(name = "PCHDBM")
    private String pchdbm;

    // 评查审批编码
    @JSONField(name = "PCSPBM")
    private String pcspbm;

    // 审批文件类型
    @JSONField(name = "SPWJLX")
    private String spwjlx;

    // 评查受理编码/评查报告编号/评查活动编码
    @JSONField(name = "SPWJBM")
    private String spwjbm;

    // 送审人单位编码
    @JSONField(name = "SSRDWBM")
    private String ssrdwbm;

    // 送审人单位名称
    @JSONField(name = "SSRDWMC")
    private String ssrdwmc;

    // 送审人工号
    @JSONField(name = "SSRGH")
    private String ssrgh;

    // 送审人姓名
    @JSONField(name = "SSRXM")
    private String ssrxm;

    // 送审开始日期
    @JSONField(name = "SSKSRQ")
    private String ssksrq;

    // 送审结束日期
    @JSONField(name = "SSJSRQ")
    private String ssjsrq;

    // 审批人单位编码
    @JSONField(name = "SPRDWBM")
    private String sprdwbm;

    // 审批人单位名称
    @JSONField(name = "SPRDWMC")
    private String sprdwmc;

    // 审批人工号
    @JSONField(name = "SPRGH")
    private String sprgh;

    // 审批人姓名
    @JSONField(name = "SPRXM")
    private String sprxm;

    // 审批开始日期
    @JSONField(name = "SPKSRQ")
    private String spksrq;

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getPcspbm() {
        return pcspbm;
    }

    public void setPcspbm(String pcspbm) {
        this.pcspbm = pcspbm;
    }

    public String getSpwjlx() {
        return spwjlx;
    }

    public void setSpwjlx(String spwjlx) {
        this.spwjlx = spwjlx;
    }

    public String getSpwjbm() {
        return spwjbm;
    }

    public void setSpwjbm(String spwjbm) {
        this.spwjbm = spwjbm;
    }

    public String getSsrdwbm() {
        return ssrdwbm;
    }

    public void setSsrdwbm(String ssrdwbm) {
        this.ssrdwbm = ssrdwbm;
    }

    public String getSsrdwmc() {
        return ssrdwmc;
    }

    public void setSsrdwmc(String ssrdwmc) {
        this.ssrdwmc = ssrdwmc;
    }

    public String getSsrgh() {
        return ssrgh;
    }

    public void setSsrgh(String ssrgh) {
        this.ssrgh = ssrgh;
    }

    public String getSsrxm() {
        return ssrxm;
    }

    public void setSsrxm(String ssrxm) {
        this.ssrxm = ssrxm;
    }

    public String getSsksrq() {
        return ssksrq;
    }

    public void setSsksrq(String ssksrq) {
        this.ssksrq = ssksrq;
    }

    public String getSsjsrq() {
        return ssjsrq;
    }

    public void setSsjsrq(String ssjsrq) {
        this.ssjsrq = ssjsrq;
    }

    public String getSprdwbm() {
        return sprdwbm;
    }

    public void setSprdwbm(String sprdwbm) {
        this.sprdwbm = sprdwbm;
    }

    public String getSprdwmc() {
        return sprdwmc;
    }

    public void setSprdwmc(String sprdwmc) {
        this.sprdwmc = sprdwmc;
    }

    public String getSprgh() {
        return sprgh;
    }

    public void setSprgh(String sprgh) {
        this.sprgh = sprgh;
    }

    public String getSprxm() {
        return sprxm;
    }

    public void setSprxm(String sprxm) {
        this.sprxm = sprxm;
    }

    public String getSpksrq() {
        return spksrq;
    }

    public void setSpksrq(String spksrq) {
        this.spksrq = spksrq;
    }

    public String getSpjsrq() {
        return spjsrq;
    }

    public void setSpjsrq(String spjsrq) {
        this.spjsrq = spjsrq;
    }

    public String getSpjl() {
        return spjl;
    }

    public void setSpjl(String spjl) {
        this.spjl = spjl;
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }

    public String getCjkssj() {
        return cjkssj;
    }

    public void setCjkssj(String cjkssj) {
        this.cjkssj = cjkssj;
    }

    public String getCjjssj() {
        return cjjssj;
    }

    public void setCjjssj(String cjjssj) {
        this.cjjssj = cjjssj;
    }

    // 审批结束日期
    @JSONField(name = "SPJSRQ")
    private String spjsrq;

    // 审批结论
    @JSONField(name = "SPJL")
    private String spjl;

    // 审批意见
    @JSONField(name = "SPYJ")
    private String spyj;

    // 创建开始时间
    @JSONField(name = "CJKSSJ")
    private String cjkssj;

    // 创建结束时间
    @JSONField(name = "CJJSSJ")
    private String cjjssj;

}
