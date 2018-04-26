package com.start.boot.pojo.vo;

/**
 * 返回给前端显示的待办任务DTo
 *
 * @caomin
 * @create 2017-12-05 16:48
 **/
public class DbrwVo {

    private String gnbm;

    private String gnmc;

    /**
     * 待办数量
     */
    private Integer count;

    public String getGnbm() {
        return gnbm;
    }

    public void setGnbm(String gnbm) {
        this.gnbm = gnbm;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
