package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class YX_PC_FZKey {
    @JSONField(name = "PCHDBM")
    private String PCHDBM;
    @JSONField(name = "PCZBM")
    private String PCZBM;

    public String getPCHDBM() {
        return PCHDBM;
    }

    public void setPCHDBM(String PCHDBM) {
        this.PCHDBM = PCHDBM;
    }

    public String getPCZBM() {
        return PCZBM;
    }

    public void setPCZBM(String PCZBM) {
        this.PCZBM = PCZBM;
    }
}