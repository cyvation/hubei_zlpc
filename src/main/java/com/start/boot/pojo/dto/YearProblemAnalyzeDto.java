package com.start.boot.pojo.dto;

/**
 * @author caomin
 * @date 2018/1/24
 * @说明 上海年度问题性质分布图Dto
 */
public class YearProblemAnalyzeDto {
    private Integer year;
    private Integer month;
    private Integer count;
    private String pcjl;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }
}
