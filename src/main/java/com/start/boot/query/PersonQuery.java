package com.start.boot.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by caomin on 2017/11/13.
 */
@ApiModel("我的收藏查询")
public class PersonQuery extends BaseQuery{

    @ApiModelProperty("单位编码")
    private String dwbm;

    @ApiModelProperty("工号")
    private String gh;

    @ApiModelProperty("资料名称")
    private String zlmc;
    @ApiModelProperty("资料类型，文件为1，案件为2")
    private Integer zllx;

    public String getZlmc() {
        return zlmc;
    }

    public void setZlmc(String zlmc) {
        this.zlmc = zlmc;
    }

    public Integer getZllx() {
        return zllx;
    }

    public void setZllx(Integer zllx) {
        this.zllx = zllx;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getDwbm() {

        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }
}
