package com.start.boot.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author caomin
 * @date 2018/1/21
 * @说明 自动评查程序前端查询
 */
@ApiModel("评查项自动评查对象")
public class ZdpccxQuery {

    @ApiModelProperty("评查项名称，必填")
    private String pcxmc;
    @ApiModelProperty("评查项编码，必填")
    private String pcxbm;
    @ApiModelProperty("每项扣分，必填")
    private String mxkf;
    @ApiModelProperty("评查项分值")
    private String pcxfz;
    @ApiModelProperty("评查类别编码，必填")
    private String pclbbm;
    @ApiModelProperty("评查受理编码，必填")
    private String pcslbm;
    @ApiModelProperty("部门受案号，必填")
    private String bmsah;
    @ApiModelProperty("评查项总分")
    private String pcxzf;



    @ApiModelProperty("自动评查程序编码，必填")
    private String zdpccxbm;
    @ApiModelProperty("评查结果")
    private String result;
    @ApiModelProperty("错误消息")
    private String errmsg;

    public String getPcxmc() {
        return pcxmc;
    }

    public String getBmsah() {
        return bmsah;
    }

    public void setBmsah(String bmsah) {
        this.bmsah = bmsah;
    }

    public void setPcxmc(String pcxmc) {
        this.pcxmc = pcxmc;
    }

    public String getPcxbm() {
        return pcxbm;
    }

    public void setPcxbm(String pcxbm) {
        this.pcxbm = pcxbm;
    }

    public String getMxkf() {
        return mxkf;
    }

    public void setMxkf(String mxkf) {
        this.mxkf = mxkf;
    }

    public String getPcxfz() {
        return pcxfz;
    }

    public void setPcxfz(String pcxfz) {
        this.pcxfz = pcxfz;
    }

    public String getPclbbm() {
        return pclbbm;
    }

    public void setPclbbm(String pclbbm) {
        this.pclbbm = pclbbm;
    }

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }

    public String getPcxzf() {
        return pcxzf;
    }

    public void setPcxzf(String pcxzf) {
        this.pcxzf = pcxzf;
    }


    public String getZdpccxbm() {
        return zdpccxbm;
    }

    public void setZdpccxbm(String zdpccxbm) {
        this.zdpccxbm = zdpccxbm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
