package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/25
 * @说明 上海年度问题性质分布图 查询对象
 */
@ApiModel
public class ShYPNAQuery extends PageInfo {
    @ApiModelProperty("评查结论，检察官办案排名查询必填值")
    private String pcjl;
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("单位编码，检察官办案排名查询必填值")
    private String dwbm;
    @ApiModelProperty("评查分类编码")
    private String pcflbm;
    @ApiModelProperty("评查项名称")
    private String pcxmc;
    @ApiModelProperty("工号，检察官办案排名获取案件信息必填")
    private String gh;

    @ApiModelProperty("部门编码，部门办案排名获取案件信息必填")
    private String bmbm;

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getPcxmc() {
        return pcxmc;
    }

    public void setPcxmc(String pcxmc) {
        this.pcxmc = pcxmc;
    }

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
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
}
