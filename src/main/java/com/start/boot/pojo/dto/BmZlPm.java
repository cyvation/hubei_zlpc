package com.start.boot.pojo.dto;

/**
 * @author caomin
 * @date 2018/1/27
 * @说明 部门办案质量排名
 */
public class BmZlPm {
    private String dwbm;
    private String bmmc;
    private String bmbm;
    private Integer count;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
