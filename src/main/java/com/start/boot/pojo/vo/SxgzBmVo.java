package com.start.boot.pojo.vo;

import java.util.Objects;

/**
 * @author caomin
 * @date 2018/3/29
 * @说明 筛选规则vo
 */
public class SxgzBmVo {
    private String sxgzbm;
    private String gzmc;

    public SxgzBmVo(String sxgzbm, String gzmc) {
        this.sxgzbm = sxgzbm;
        this.gzmc = gzmc;
    }

    public SxgzBmVo() {
    }

    public SxgzBmVo(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SxgzBmVo sxgzBmVo = (SxgzBmVo) o;
        return Objects.equals(sxgzbm, sxgzBmVo.sxgzbm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sxgzbm);
    }

    public String getSxgzbm() {
        return sxgzbm;
    }

    public void setSxgzbm(String sxgzbm) {
        this.sxgzbm = sxgzbm;
    }

    public String getGzmc() {
        return gzmc;
    }

    public void setGzmc(String gzmc) {
        this.gzmc = gzmc;
    }
}
