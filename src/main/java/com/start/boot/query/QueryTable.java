package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/25
 * @说明 三表 查询对象
 */
@ApiModel
public class QueryTable extends PageInfo {
    @ApiModelProperty("评查分类编码")
    private String pcflbm;
    @ApiModelProperty("单位编码")
    private String dwbm;
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("评查结论")
    private String pcjl;
    @ApiModelProperty("部门编码")
    private String bmbm;
    @ApiModelProperty("工号")
    private String gh;
    @ApiModelProperty("评查模板编码")
    private String pcmbbm;
    @ApiModelProperty("评查活动编码")
    private String pchdbm;
    @ApiModelProperty("评查项分类编码List")
    private List<String> pcxflbmList;

    public List<String> getPcxflbmList() {
        return pcxflbmList;
    }

    public void setPcxflbmList(List<String> pcxflbmList) {
        this.pcxflbmList = pcxflbmList;
    }

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

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
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

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }

    public String getPcmbbm(){ return pcmbbm;};

    public void setPcmbbm(String pcmbbm){
        this.pcmbbm = pcmbbm;
    }

    public void setPchdbm(String pchdbm){
        this.pchdbm = pchdbm;
    }

    public String getPchdbm(){ return pchdbm;};
}
