package com.start.boot.pojo.dto;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明 地图数据
 */
public class MapDto {
    private String name;
    private Integer value;
    private String pcl;
    private String dwbm;
    private String pcflbm;

    private String pclbmc;


    private Integer wpc;
    private Integer ypc;

    public String getPclbmc() {
        return pclbmc;
    }

    public void setPclbmc(String pclbmc) {
        this.pclbmc = pclbmc;
    }

    public Integer getWpc() {
        return wpc;
    }

    public void setWpc(Integer wpc) {
        this.wpc = wpc;
    }

    public Integer getYpc() {
        return ypc;
    }

    public void setYpc(Integer ypc) {
        this.ypc = ypc;
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

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
