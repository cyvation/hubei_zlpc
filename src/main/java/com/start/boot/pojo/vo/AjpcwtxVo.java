package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @说明 案件评查问题项
 * @author 李志恒
 * @date 2018-4-17
 *
 */
@ApiModel("案件评查问题项")
public class AjpcwtxVo {

    @ApiModelProperty("评查项分类父编码")
    private String pcxflfbm;

    @ApiModelProperty("评查项分类父名称")
    private String pcxflfmc;

    @ApiModelProperty("评查分类编码")
    private String pcxflbm;

    @ApiModelProperty("评查分类名称")
    private String pcxflmc;

    @ApiModelProperty("评查项编码")
    private String pcxbm;

    @ApiModelProperty("评查项名称")
    private String pcxmc;

    @ApiModelProperty("问题数")
    private Integer wts = 0;

    @ApiModelProperty("总分")
    private Double zf = 0.0;

    @ApiModelProperty("序号")
    private Integer xh = 0;

    public String getPcxflfbm() {
        return pcxflfbm;
    }

    public void setPcxflfbm(String pcxflfbm) {
        this.pcxflfbm = pcxflfbm;
    }

    public String getPcxflfmc() {
        return pcxflfmc;
    }

    public void setPcxflfmc(String pcxflfmc) {
        this.pcxflfmc = pcxflfmc;
    }

    public String getPcxflbm() {
        return pcxflbm;
    }

    public void setPcxflbm(String pcxflbm) {
        this.pcxflbm = pcxflbm;
    }

    public String getPcxflmc() {
        return pcxflmc;
    }

    public void setPcxflmc(String pcxflmc) {
        this.pcxflmc = pcxflmc;
    }

    public String getPcxbm() {
        return pcxbm;
    }

    public void setPcxbm(String pcxbm) {
        this.pcxbm = pcxbm;
    }

    public String getPcxmc() {
        return pcxmc;
    }

    public void setPcxmc(String pcxmc) {
        this.pcxmc = pcxmc;
    }

    public Integer getWts() {
        return wts;
    }

    public void setWts(Integer wts) {
        this.wts = wts;
    }

    public Double getZf() {
        return zf;
    }

    public void setZf(Double zf) {
        this.zf = zf;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

}
