package com.start.boot.domain;



import java.util.List;

/**
 * Created by lei on 2018/10/16.
 */
public class JxpcAj {

    private String dwbm;
    private String dwmc;
    private String pcflbm;

    private String czr_dwbm;
    private String czr_dwmc;
    private String czr_gh;
    private String czr_mc;

    private String jsdw;
    private String jsdwmc;
    private List<String> bmsahlist;

    private List<Aj> ajList;

    private String sfzdy ;
    private String sm;


    public static class Aj{

        private String bmsah;
        private String dwbm;
        private String dwmc;
        private String ajmc;
        private String pcflbm;
        private String ajlb_bm;
        private String ajlb_mc;

        public Aj(String bmsah, String dwbm, String dwmc, String ajmc, String pcflbm, String ajlb_bm, String ajlb_mc) {
            this.bmsah = bmsah;
            this.dwbm = dwbm;
            this.dwmc = dwmc;
            this.ajmc = ajmc;
            this.pcflbm = pcflbm;
            this.ajlb_bm = ajlb_bm;
            this.ajlb_mc = ajlb_mc;
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

        public String getDwmc() {
            return dwmc;
        }

        public void setDwmc(String dwmc) {
            this.dwmc = dwmc;
        }

        public String getAjmc() {
            return ajmc;
        }

        public void setAjmc(String ajmc) {
            this.ajmc = ajmc;
        }

        public String getPcflbm() {
            return pcflbm;
        }

        public void setPcflbm(String pcflbm) {
            this.pcflbm = pcflbm;
        }

        public String getAjlb_bm() {
            return ajlb_bm;
        }

        public void setAjlb_bm(String ajlb_bm) {
            this.ajlb_bm = ajlb_bm;
        }

        public String getAjlb_mc() {
            return ajlb_mc;
        }

        public void setAjlb_mc(String ajlb_mc) {
            this.ajlb_mc = ajlb_mc;
        }
    }


    public String getDwbm() {
        return dwbm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public String getCzr_dwbm() {
        return czr_dwbm;
    }

    public void setCzr_dwbm(String czr_dwbm) {
        this.czr_dwbm = czr_dwbm;
    }

    public String getCzr_dwmc() {
        return czr_dwmc;
    }

    public void setCzr_dwmc(String czr_dwmc) {
        this.czr_dwmc = czr_dwmc;
    }

    public String getCzr_gh() {
        return czr_gh;
    }

    public void setCzr_gh(String czr_gh) {
        this.czr_gh = czr_gh;
    }

    public String getCzr_mc() {
        return czr_mc;
    }

    public void setCzr_mc(String czr_mc) {
        this.czr_mc = czr_mc;
    }

    public String getJsdw() {
        return jsdw;
    }

    public void setJsdw(String jsdw) {
        this.jsdw = jsdw;
    }

    public String getJsdwmc() {
        return jsdwmc;
    }

    public void setJsdwmc(String jsdwmc) {
        this.jsdwmc = jsdwmc;
    }

    public List<String> getBmsahlist() {
        return bmsahlist;
    }

    public void setBmsahlist(List<String> bmsahlist) {
        this.bmsahlist = bmsahlist;
    }

    public List<Aj> getAjList() {
        return ajList;
    }

    public void setAjList(List<Aj> ajList) {
        this.ajList = ajList;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getSfzdy() {
        return sfzdy;
    }

    public void setSfzdy(String sfzdy) {
        this.sfzdy = sfzdy;
    }
}
