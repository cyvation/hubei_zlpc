package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明 三表对象
 */
@ApiModel("表格返回数据")
public class QueryTableVo {

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("单位编码")
    private String dwbm;

    @ApiModelProperty("评查率")
    private String pcl;

    @ApiModelProperty("审结数")
    private Integer sjCount=0;


    @ApiModelProperty("评查数")
    private Integer pcCount=0;

    @ApiModelProperty("重点评查数")
    private Integer zdpcCount=0;

    @ApiModelProperty("常规评查数")
    private Integer cgCount=0;

    @ApiModelProperty("专项评查数")
    private Integer zxCount=0;


    @ApiModelProperty("交叉评查数")
    private Integer jxCount=0;

    @ApiModelProperty("优质评查数")
    private Integer yzCount=0;


    @ApiModelProperty("合格评查数")
    private Integer hgCount=0;


    @ApiModelProperty("瑕疵评查数")
    private Integer xcCount=0;


    @ApiModelProperty("不合格评查数")
    private Integer bhgCount=0;

    @ApiModelProperty("评查问题总数")
    private Integer pcwtzsCount=0;

    @ApiModelProperty("事实认定瑕疵 评查数")
    private Integer ssrdXcCount=0;

    @ApiModelProperty("事实认定错误  评查数")
    private Integer ssrdCuCount=0;

    @ApiModelProperty("证据采信瑕疵 评查数")
    private Integer zjcxXcCount=0;


    @ApiModelProperty("证据采信错误  评查数")
    private Integer zjcxCuCount=0;

    @ApiModelProperty("证据采信 分歧评查数")
    private Integer zjcxfqCount=0;


    @ApiModelProperty("法律适用  瑕疵评查数")
    private Integer flsyXcCount=0;


    @ApiModelProperty("法律适用 错误评查数")
    private Integer flsyCuCount=0;

    @ApiModelProperty("法律适用 分歧评查数")
    private Integer flsyfqCount=0;


    @ApiModelProperty("办案程序 瑕疵评查数")
    private Integer bacxXcCount=0;

    @ApiModelProperty("办案程序 错误评查数")
    private Integer bacxCuCount=0;

    @ApiModelProperty("法律文书 瑕疵评查数")
    private Integer flwsXcCount=0;

    @ApiModelProperty("法律文书 错误评查数")
    private Integer flwsCwCount=0;

    @ApiModelProperty("办案结果 一般评查数")
    private Integer baxgYbCount=0;

    @ApiModelProperty("侦查监督 错误评查数")
    private Integer zcjdCwCount=0;

    @ApiModelProperty("侦查监督 瑕疵评查数")
    private Integer zcjdXcCount=0;

    @ApiModelProperty("系统规范应用 瑕疵数")
    private Integer xtgfyyXcCount = 0;

    @ApiModelProperty("司法责任制落实 错误数")
    private Integer sfzrzlsCwCount = 0;

    @ApiModelProperty("司法责任制落实 瑕疵数")
    private Integer sfzrzlsXcCount = 0;

    @ApiModelProperty("其他情况 错误数")
    private Integer qtqkCwCount = 0;

    @ApiModelProperty("其他情况 瑕疵数")
    private Integer qtqkXcCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public Integer getSjCount() {
        return sjCount;
    }

    public void setSjCount(Integer sjCount) {
        this.sjCount = sjCount;
    }

    public Integer getPcCount() {
        return pcCount;
    }

    public void setPcCount(Integer pcCount) {
        this.pcCount = pcCount;
    }

    public Integer getZdpcCount() {
        return zdpcCount;
    }

    public void setZdpcCount(Integer zdpcCount) {
        this.zdpcCount = zdpcCount;
    }

    public Integer getCgCount() {
        return cgCount;
    }

    public void setCgCount(Integer cgCount) {
        this.cgCount = cgCount;
    }

    public Integer getZxCount() {
        return zxCount;
    }

    public void setZxCount(Integer zxCount) {
        this.zxCount = zxCount;
    }

    public Integer getJxCount() {
        return jxCount;
    }

    public void setJxCount(Integer jxCount) {
        this.jxCount = jxCount;
    }

    public Integer getYzCount() {
        return yzCount;
    }

    public void setYzCount(Integer yzCount) {
        this.yzCount = yzCount;
    }

    public Integer getHgCount() {
        return hgCount;
    }

    public void setHgCount(Integer hgCount) {
        this.hgCount = hgCount;
    }

    public Integer getXcCount() {
        return xcCount;
    }

    public void setXcCount(Integer xcCount) {
        this.xcCount = xcCount;
    }

    public Integer getBhgCount() {
        return bhgCount;
    }

    public void setBhgCount(Integer bhgCount) {
        this.bhgCount = bhgCount;
    }

    public Integer getPcwtzsCount() {
        return pcwtzsCount;
    }

    public void setPcwtzsCount(Integer pcwtzsCount) {
        this.pcwtzsCount = pcwtzsCount;
    }

    public Integer getSsrdXcCount() {
        return ssrdXcCount;
    }

    public void setSsrdXcCount(Integer ssrdXcCount) {
        this.ssrdXcCount = ssrdXcCount;
    }

    public Integer getSsrdCuCount() {
        return ssrdCuCount;
    }

    public void setSsrdCuCount(Integer ssrdCuCount) {
        this.ssrdCuCount = ssrdCuCount;
    }

    public Integer getZjcxXcCount() {
        return zjcxXcCount;
    }

    public void setZjcxXcCount(Integer zjcxXcCount) {
        this.zjcxXcCount = zjcxXcCount;
    }

    public Integer getZjcxCuCount() {
        return zjcxCuCount;
    }

    public void setZjcxCuCount(Integer zjcxCuCount) {
        this.zjcxCuCount = zjcxCuCount;
    }

    public Integer getZjcxfqCount() {
        return zjcxfqCount;
    }

    public void setZjcxfqCount(Integer zjcxfqCount) {
        this.zjcxfqCount = zjcxfqCount;
    }

    public Integer getFlsyXcCount() {
        return flsyXcCount;
    }

    public void setFlsyXcCount(Integer flsyXcCount) {
        this.flsyXcCount = flsyXcCount;
    }

    public Integer getFlsyCuCount() {
        return flsyCuCount;
    }

    public void setFlsyCuCount(Integer flsyCuCount) {
        this.flsyCuCount = flsyCuCount;
    }

    public Integer getFlsyfqCount() {
        return flsyfqCount;
    }

    public void setFlsyfqCount(Integer flsyfqCount) {
        this.flsyfqCount = flsyfqCount;
    }

    public Integer getBacxXcCount() {
        return bacxXcCount;
    }

    public void setBacxXcCount(Integer bacxXcCount) {
        this.bacxXcCount = bacxXcCount;
    }

    public Integer getBacxCuCount() {
        return bacxCuCount;
    }

    public void setBacxCuCount(Integer bacxCuCount) {
        this.bacxCuCount = bacxCuCount;
    }

    public Integer getFlwsXcCount() {
        return flwsXcCount;
    }

    public void setFlwsXcCount(Integer flwsXcCount) {
        this.flwsXcCount = flwsXcCount;
    }

    public Integer getBaxgYbCount() {
        return baxgYbCount;
    }

    public void setBaxgYbCount(Integer baxgYbCount) {
        this.baxgYbCount = baxgYbCount;
    }

    public Integer getXtgfyyXcCount() {
        return xtgfyyXcCount;
    }

    public void setXtgfyyXcCount(Integer xtgfyyXcCount) {
        this.xtgfyyXcCount = xtgfyyXcCount;
    }

    public Integer getSfzrzlsCwCount() {
        return sfzrzlsCwCount;
    }

    public void setSfzrzlsCwCount(Integer sfzrzlsCwCount) {
        this.sfzrzlsCwCount = sfzrzlsCwCount;
    }

    public Integer getSfzrzlsXcCount() {
        return sfzrzlsXcCount;
    }

    public void setSfzrzlsXcCount(Integer sfzrzlsXcCount) {
        this.sfzrzlsXcCount = sfzrzlsXcCount;
    }

    public Integer getQtqkCwCount() {
        return qtqkCwCount;
    }

    public void setQtqkCwCount(Integer qtqkCwCount) {
        this.qtqkCwCount = qtqkCwCount;
    }

    public Integer getQtqkXcCount() {
        return qtqkXcCount;
    }

    public void setQtqkXcCount(Integer qtqkXcCount) {
        this.qtqkXcCount = qtqkXcCount;
    }

    public Integer getFlwsCwCount() {
        return flwsCwCount;
    }

    public void setFlwsCwCount(Integer flwsCwCount) {
        this.flwsCwCount = flwsCwCount;
    }

    public Integer getZcjdCwCount() {
        return zcjdCwCount;
    }

    public void setZcjdCwCount(Integer zcjdCwCount) {
        this.zcjdCwCount = zcjdCwCount;
    }

    public Integer getZcjdXcCount() {
        return zcjdXcCount;
    }

    public void setZcjdXcCount(Integer zcjdXcCount) {
        this.zcjdXcCount = zcjdXcCount;
    }
}
