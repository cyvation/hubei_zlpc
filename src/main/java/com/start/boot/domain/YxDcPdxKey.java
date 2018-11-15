package com.start.boot.domain;

import java.io.Serializable;

/**
 * YX_DC_PDX
 * @author 
 */
public class YxDcPdxKey implements Serializable {
    private String pcslbm;

    private String pdxbm;

    private static final long serialVersionUID = 1L;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }

    public String getPdxbm() {
        return pdxbm;
    }

    public void setPdxbm(String pdxbm) {
        this.pdxbm = pdxbm;
    }
}