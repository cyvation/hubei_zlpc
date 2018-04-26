package com.start.boot.domain;

import com.start.boot.common.Param_Pager;

/**
 * 评查案件实体类
 * Created by lei on 2017/11/3.
 */
public class Param_Pc extends Param_Pager {
    private String pcdwbm;
    private String pcflbm;
    private String pchdbm;
    private String pcmbbm;
    private String pcslbm;

    private String bmsah;
    private String dwbm;
    private String gh;

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }
    public String getPcmbbm() {
        return pcmbbm;
    }

    public void setPcmbbm(String pcmbbm) {
        this.pcmbbm = pcmbbm;
    }

    public String getPcdwbm() {
        return pcdwbm;
    }

    public void setPcdwbm(String pcdwbm) {
        this.pcdwbm = pcdwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getPchdbm() {
        return pchdbm;
    }

    public void setPchdbm(String pchdbm) {
        this.pchdbm = pchdbm;
    }

    public String getBmsah() {
        return bmsah;
    }

    public void setBmsah(String bmsah) {
        this.bmsah = bmsah;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

}
