package com.start.boot.pojo.dto;

/**
 * @author caomin
 * @date 2018/3/27
 * @说明 规则vo
 */
public class GzbmDto {

    private String sxgzbm;

    private Integer ypc=0;
    private Integer wpc=0;

    public GzbmDto(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public GzbmDto(){}

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public Integer getYpc() {
        return ypc;
    }

    public void setYpc(Integer ypc) {
        this.ypc = ypc;
    }

    public Integer getWpc() {
        return wpc;
    }

    public void setWpc(Integer wpc) {
        this.wpc = wpc;
    }
}
