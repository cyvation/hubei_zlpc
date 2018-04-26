package com.start.boot.domain;

import com.start.boot.common.Param_Pager;

public class Tongji extends Param_Pager {

    String dwbm;
    String bmbm;
    String gh;
    String time_start;
    String time_end;
    // 统计分类
    String type;
    // 统计方式
    String fanshi;

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFanshi() {
        return fanshi;
    }

    public void setFanshi(String fanshi) {
        this.fanshi = fanshi;
    }
}
