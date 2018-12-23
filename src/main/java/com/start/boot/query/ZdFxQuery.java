package com.start.boot.query;

import com.start.boot.common.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.List;

public class ZdFxQuery  extends PageInfo{
    /**
     * 承办单位
     */
    private List<String> dwbm;
    /**
     * 评查方式
     */
    private List<String> pcflbm;
    /**
     * 案件完成开始时间
     */
    private String startDate;
    /**
     * 案件完成结束时间
     */
    private String endDate;
    /**
     * 开始评查时间
     */
    private String pcstartDate;
    /**
     * 开始评查结束时间
     */
    private String pcendDate;
    /**
     * 承办人身份
     */
    private List<String> cbrsf;

    /**
     * 案件统计类别
     */
    private List<String> ajtjlb;
    /**
     * 筛选规则
     */
    private List<String> gzbmj;
    /**
     * 案件结论
     */
    private String pcjl;

    /**
     * 导出类型
     */
    private String exporttype = "";

    public List<String> getDwbm() {
        return dwbm;
    }

    public void setDwbm(List<String> dwbm) {
        this.dwbm = dwbm;
    }

    public List<String> getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(List<String> pcflbm) {
        this.pcflbm = pcflbm;
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
        if(!StringUtils.isEmpty(endDate)){
            endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
        }
        this.endDate = endDate;
    }

    public String getPcstartDate() {
        return pcstartDate;
    }

    public void setPcstartDate(String pcstartDate) {
        this.pcstartDate = pcstartDate;
    }

    public String getPcendDate() {
        return pcendDate;
    }

    public void setPcendDate(String pcendDate) {
        if (!StringUtils.isEmpty(pcendDate)){
            pcendDate = DateTime.parse(pcendDate).toString("yyyy-MM-dd");
        }
        this.pcendDate = pcendDate;
    }

    public List<String> getCbrsf() {
        return cbrsf;
    }

    public void setCbrsf(List<String> cbrsf) {
        this.cbrsf = cbrsf;
    }

    public List<String> getAjtjlb() {
        return ajtjlb;
    }

    public void setAjtjlb(List<String> ajtjlb) {
        this.ajtjlb = ajtjlb;
    }

    public List<String> getGzbmj() {
        return gzbmj;
    }

    public void setGzbmj(List<String> gzbmj) {
        this.gzbmj = gzbmj;
    }

    public String getExporttype() {
        return exporttype;
    }

    public void setExporttype(String exporttype) {
        this.exporttype = exporttype;
    }

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }
}
