package com.start.boot.domain;

import com.start.boot.common.Param_Pager;;



/**
 * 消息列表实体
 * Created by Administrator on 2017/11/2.
 */
public class Param_Xxlb extends Param_Pager {

    private  String jsrdwbm;
    private  String jsrgh;
    private  String xxlx;
    private String xxzt;
    private String keyword;
    private String fsrqbeg;
    private String fsrqend;

    public String getJsrdwbm() {
        return jsrdwbm;
    }

    public void setJsrdwbm(String jsrdwbm) {
        this.jsrdwbm = jsrdwbm;
    }

    public String getJsrgh() {
        return jsrgh;
    }

    public void setJsrgh(String jsrgh) {
        this.jsrgh = jsrgh;
    }

    public String getXxlx() {
        return xxlx;
    }

    public void setXxlx(String xxlx) {
        this.xxlx = xxlx;
    }

    public String getXxzt() {
        return xxzt;
    }

    public void setXxzt(String xxzt) {
        this.xxzt = xxzt;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFsrqbeg() {
        return fsrqbeg;
    }

    public void setFsrqbeg(String fsrqbeg) {
        this.fsrqbeg = fsrqbeg;
    }

    public String getFsrqend() {
        return fsrqend;
    }

    public void setFsrqend(String fsrqend) {
        this.fsrqend = fsrqend;
    }
}
