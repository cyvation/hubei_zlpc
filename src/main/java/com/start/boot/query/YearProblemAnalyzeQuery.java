package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/24
 * @说明 上海年度问题分析，查询对象
 */
@ApiModel("柱状图")
public class YearProblemAnalyzeQuery extends PageInfo {
    @ApiModelProperty("单位编码,获取案件基本信息时必传")
    private String dwbm;
    /**
     * 完成日期，起始日期
     */
    @ApiModelProperty(" 完成日期，起始日期")
    private String wcrqStart;
    /**
     * 完成日期，结束日期
     */
    @ApiModelProperty(" 完成日期，结束日期")
    private String wcrqEnd;

    @ApiModelProperty("评查结论,获取案件基本信息时必传")
    private String pcjl;

    @ApiModelProperty("年份,获取案件基本信息时必传")
    private Integer year;

    @ApiModelProperty("月份，获取案件基本信息时必传")
    private Integer month;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getWcrqStart() {
        return wcrqStart;
    }

    public void setWcrqStart(String wcrqStart) {
        this.wcrqStart = wcrqStart;
    }

    public String getWcrqEnd() {
        return wcrqEnd;
    }

    public void setWcrqEnd(String wcrqEnd) {
        this.wcrqEnd = wcrqEnd;
    }
}
