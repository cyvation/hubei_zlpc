package com.start.boot.pojo.dto;



/**
 * Created by lei on 2018/12/20.
 */

public class ZdFxzlfxDto extends ZdFxDto {

    private Integer yzcount; // 优质案件数量
    private Integer hgcount; //  合格数量
    private Integer xccount; //  瑕疵数量
    private Integer bhgcount; // 不合格数量

    private String yzpcl = "0.00"; // 优质评查率
    private String hgpcl = "0.00"; // 合格评查率
    private String xcpcl = "0.00"; // 瑕疵评查率
    private String bhgpcl = "0.00"; // 不合格评查率

    public Integer getYzcount() {
        return yzcount;
    }

    public void setYzcount(Integer yzcount) {
        this.yzcount = yzcount;
    }

    public Integer getHgcount() {
        return hgcount;
    }

    public void setHgcount(Integer hgcount) {
        this.hgcount = hgcount;
    }

    public Integer getXccount() {
        return xccount;
    }

    public void setXccount(Integer xccount) {
        this.xccount = xccount;
    }

    public Integer getBhgcount() {
        return bhgcount;
    }

    public void setBhgcount(Integer bhgcount) {
        this.bhgcount = bhgcount;
    }

    public String getYzpcl() {
        return yzpcl;
    }

    public void setYzpcl(String yzpcl) {
        this.yzpcl = yzpcl;
    }

    public String getHgpcl() {
        return hgpcl;
    }

    public void setHgpcl(String hgpcl) {
        this.hgpcl = hgpcl;
    }

    public String getXcpcl() {
        return xcpcl;
    }

    public void setXcpcl(String xcpcl) {
        this.xcpcl = xcpcl;
    }

    public String getBhgpcl() {
        return bhgpcl;
    }

    public void setBhgpcl(String bhgpcl) {
        this.bhgpcl = bhgpcl;
    }
}
