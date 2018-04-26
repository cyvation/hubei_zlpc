package com.start.boot.query;

/**
 * 待办任务的查询对象
 *
 * @caomin
 * @create 2017-12-04 22:23
 **/
public class DbrwQuery {
    /**
     * 单位编码
     */
    private String dwbm;
    /**
     * 工号
     */
    private String gh;

    public DbrwQuery(String currentDwbm, String currentGh) {
        this.dwbm=currentDwbm;
        this.gh=currentGh;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getDwbm() {

        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }
}
