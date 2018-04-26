package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.start.boot.domain.Param_Pcy;

import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

// 评查组
public class Param_Pcz {

    @JSONField(name = "PCHDBM")
    private String pchdbm;
    @JSONField(name = "PCHDMC")
    private String pchdmc;
    @JSONField(name = "PCZBM")
    private String pczbm;
    @JSONField(name = "PCZMC")
    private String pczmc;
    @JSONField(name = "SM")
    private String sm;
    @JSONField(name = "PCYARR")
    private List<Param_Pcy> pcyarr;

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getPchdmc() {
        return pchdmc;
    }

    public void setPchdmc(String pchdmc) {
        this.pchdmc = pchdmc;
    }

    public String getPczbm() {
        return pczbm;
    }

    public void setPczbm(String pczbm) {
        this.pczbm = pczbm;
    }

    public String getPczmc() {
        return pczmc;
    }

    public void setPczmc(String pczmc) {
        this.pczmc = pczmc;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public List<Param_Pcy> getPcyarr() {
        return pcyarr;
    }

    public void setPcyarr(List<Param_Pcy> pcyarr) {
        this.pcyarr = pcyarr;
    }
}
