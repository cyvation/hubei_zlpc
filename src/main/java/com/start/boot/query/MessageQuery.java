package com.start.boot.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息查询对象
 *
 * @caomin
 * @create 2017-12-12 14:34
 **/
public class MessageQuery extends BaseQuery{

    private String dwbm;
    private String gh;
    private String searchName;
    private String xxzt;

    private Date startTime;

    private Date endTime;
    private List<String>ids=new ArrayList<>();

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
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

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getXxzt() {
        return xxzt;
    }

    public void setXxzt(String xxzt) {
        this.xxzt = xxzt;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
