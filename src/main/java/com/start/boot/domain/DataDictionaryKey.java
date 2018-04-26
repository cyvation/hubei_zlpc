package com.start.boot.domain;

public class DataDictionaryKey {
    private String dm;

    private String lbbm;

    public String getDm() {
        return dm;
    }

   // public void setDm(String dm) {
//        this.dm = dm == null ? null : dm.trim();
//    }


    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getLbbm() {
        return lbbm;
    }

    public void setLbbm(String lbbm) {
        this.lbbm = lbbm == null ? null : lbbm.trim();
    }
}