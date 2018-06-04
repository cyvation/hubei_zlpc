package com.start.boot.pojo.vo;

import com.start.boot.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/3/20
 * @说明 评查概览vo
 */
public class PcglVo {
    @ExcelProperty(name = "单位名称",order = 0)
    private String dwmc;
    private String dwbm;
    @ExcelProperty(name = "未评查案件数量",order = 2)
    private int wpc;
    @ExcelProperty(name = "已评查案件数量",order = 1)
    private int ypc;
    @ApiModelProperty("筛选规则编码")
    private String sxgzbm;
    @ApiModelProperty("筛选规则名称")
    private String sxgzmc;

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public String getSxgzmc() {
        return sxgzmc;
    }

    public void setSxgzmc(String sxgzmc) {
        this.sxgzmc = sxgzmc;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
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
}
