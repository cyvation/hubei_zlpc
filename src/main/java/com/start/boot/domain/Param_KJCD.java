package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/11/2.
 */
public class Param_KJCD {
    @JSONField(name = "GNBM")
    private String gnbm;
    @JSONField(name = "GNMC")
    private String gnmc;

    public String getGnbm() {
        return gnbm;
    }

    public void setGnbm(String gnbm) {
        this.gnbm = gnbm;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }

}
