package com.start.boot.query;

import com.start.boot.common.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caomin
 * @date 2018/3/5
 * @说明 查询案件基本信息
 */
public class QueryTableAjJbxx extends PageInfo {
    @ApiModelProperty("评查分类编码")
    private String pcflbm;
    @ApiModelProperty("评查模板编码")
    private String pcmbbm;
    @ApiModelProperty("评查活动编码")
    private String pchdbm;
    @ApiModelProperty("单位编码")
    private List<String> dwbmList=new ArrayList<>() ;
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("评查结论")
    private String pcjl;
    @ApiModelProperty("部门编码")
    private List<String> bmbmList=new ArrayList<>();
    @ApiModelProperty("工号")
    private List<String> ghList=new ArrayList<>();


    @ApiModelProperty("评查项分类名称")
    private String pcxflmc;
    @ApiModelProperty("总名称")
    private String zmc;

    @ApiModelProperty("评查项分类编码List")
    private List<String> pcxflbmList=new ArrayList<>();

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public List<String> getDwbmList() {
        if(dwbmList==null){
            dwbmList=new ArrayList<>();
        }
        return dwbmList;
    }

    public void setDwbmList(List<String> dwbmList) {
        this.dwbmList = dwbmList;
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

    public List<String> getBmbmList() {
        if(bmbmList==null){
            bmbmList=new ArrayList<>();
        }
        return bmbmList;
    }

    public void setBmbmList(List<String> bmbmList) {
        this.bmbmList = bmbmList;
    }

    public List<String> getGhList() {
        return ghList;
    }

    public void setGhList(List<String> ghList) {
        this.ghList = ghList;
    }

    public String getPcxflmc() {
        return pcxflmc;
    }

    public void setPcxflmc(String pcxflmc) {
        this.pcxflmc = pcxflmc;
    }

    public String getZmc() {
        return zmc;
    }

    public void setZmc(String zmc) {
        this.zmc = zmc;
    }
    public String getPcmbbm() {
        return pcmbbm;
    }

    public void setPcmbbm(String pcmbbm) {
        this.pcmbbm = pcmbbm;
    }

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public List<String> getPcxflbmList() {
        if (pcxflbmList==null){
            pcxflbmList=new ArrayList<>();
        }
        return pcxflbmList;
    }

    public void setPcxflbmList(List<String> pcxflbmList) {
        this.pcxflbmList = pcxflbmList;
    }
}
