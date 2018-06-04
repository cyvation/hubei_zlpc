package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/25
 * @说明 评查概览查询对象
 */
@ApiModel
public class PcglQuery extends PageInfo {

    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("单位编码，检察官办案排名查询必填值")
    private String dwbm;
    @ApiModelProperty("评查分类编码")
    private String pcflbm;
    @ApiModelProperty("筛选规则编码")
    private String sxgzbm;
    @ApiModelProperty("单位编码数组")
    private String[] dwbms;

    private String wpc;

    public String getWpc() {
        return wpc;
    }

    public void setWpc(String wpc) {
        this.wpc = wpc;
    }

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

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

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String[] getDwbms() {
        return dwbms;
    }

    public void setDwbms(String[] dwbms) {
        this.dwbms = dwbms;
    }
}
