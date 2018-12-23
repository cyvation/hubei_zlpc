package com.start.boot.pojo.dto;


public class ZdFxZtqkDto extends ZdFxDto {

    private Integer cbrcount; // 办案人数
    private Integer pcrcount; // 评查人数

    private String pcl = "0.00"; // 评查比例
    private String cbrbpcl = "0.00"; // 承办人平均被评查件数
    private String pcrpcl = "0.00"; // 评查人平均评查数

    public Integer getCbrcount() {
        return cbrcount;
    }

    public void setCbrcount(Integer cbrcount) {
        this.cbrcount = cbrcount;
    }

    public Integer getPcrcount() {
        return pcrcount;
    }

    public void setPcrcount(Integer pcrcount) {
        this.pcrcount = pcrcount;
    }

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public String getCbrbpcl() {
        return cbrbpcl;
    }

    public void setCbrbpcl(String cbrbpcl) {
        this.cbrbpcl = cbrbpcl;
    }

    public String getPcrpcl() {
        return pcrpcl;
    }

    public void setPcrpcl(String pcrpcl) {
        this.pcrpcl = pcrpcl;
    }
}
