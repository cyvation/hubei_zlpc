package com.start.boot.query;

import com.start.boot.common.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by lei on 2018/6/21.
 * 综合报告前端查询对象
 */
public class ReportQuery extends PageInfo {

    private List<String> dwbm;

    private String nzr_dwbm;

    private String nzr_gh;

    private String pcflbm;

    private String pcjdbh;

    private String wsmbbh;

    private String startDate;

    private String endDate;
    // 是否审批（0-未审批,1-已审批）
    private String sfsp;

    // 报送状态（市院9，下级院：0.未报送，1.已报送）
    private String bszt;

    public List<String> getDwbm() {
        return dwbm;
    }

    public void setDwbm(List<String> dwbm) {
        this.dwbm = dwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getPcjdbh() {
        return pcjdbh;
    }

    public void setPcjdbh(String pcjdbh) {
        this.pcjdbh = pcjdbh;
    }

    public String getWsmbbh() {
        return wsmbbh;
    }

    public void setWsmbbh(String wsmbbh) {
        this.wsmbbh = wsmbbh;
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

        if (StringUtils.isNotEmpty(endDate)){
                DateTime parse = DateTime.parse(endDate);
                endDate = parse.plusDays(1).toString("yyyy-MM-dd");
        }

        this.endDate = endDate;
    }

    public String getSfsp() {
        return sfsp;
    }

    public void setSfsp(String sfsp) {
        this.sfsp = sfsp;
    }

    public String getBszt() {
        return bszt;
    }

    public void setBszt(String bszt) {
        this.bszt = bszt;
    }

    public String getNzr_dwbm() {
        return nzr_dwbm;
    }

    public void setNzr_dwbm(String nzr_dwbm) {
        this.nzr_dwbm = nzr_dwbm;
    }

    public String getNzr_gh() {
        return nzr_gh;
    }

    public void setNzr_gh(String nzr_gh) {
        this.nzr_gh = nzr_gh;
    }
}
