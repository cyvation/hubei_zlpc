package com.start.boot.pojo.dto;

/**
 * @author caomin
 * @date 2018/1/27
 * @说明 排名Dto
 */
public class PmDto {
    private String dwbm;
    private String dwmc;
    private Integer count;
    private String bmbm;

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
