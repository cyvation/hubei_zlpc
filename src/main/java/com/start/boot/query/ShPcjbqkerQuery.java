package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/25
 * @说明 基本评查情况统计二
 */
@ApiModel("评查基本情况二，查询对象")
public class ShPcjbqkerQuery extends PageInfo {

    @ApiModelProperty("年份，获取案件基本信息必传")
    private Integer year;
    @ApiModelProperty("单位编码，获取案件基本信息必传")
    private String dwbm;
    @ApiModelProperty("月份，获取案件基本信息必传")
    private Integer month;

    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;

    @ApiModelProperty("完成状态，获取案件基本信息：评查传 IS NULL,审结传 IS NOT NULL")
    private String wczt;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWczt() {
        return wczt;
    }

    public void setWczt(String wczt) {
        this.wczt = wczt;
    }

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

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }
}
