package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


public class YX_PC_FZ extends com.start.boot.domain.YX_PC_FZKey {
    @JSONField(name = "PCZMC")
    private String PCZMC;
    @JSONField(name = "SM")
    private String SM;
    @JSONField(name = "PCYARR")
    private List<com.start.boot.domain.Param_Pcy> PCYARR;

    public String getPCZMC() {
        return PCZMC;
    }

    public void setPCZMC(String PCZMC) {
        this.PCZMC = PCZMC;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public List<com.start.boot.domain.Param_Pcy> getPCYARR() {
        return PCYARR;
    }

    public void setPCYARR(List<com.start.boot.domain.Param_Pcy> PCYARR) {
        this.PCYARR = PCYARR;
    }
}