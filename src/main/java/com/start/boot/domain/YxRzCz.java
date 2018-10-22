package com.start.boot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * YX_RZ_CZ
 * @author 
 */
public class YxRzCz implements Serializable {
    private BigDecimal logid;

    private Date czsj;

    private String czlx;

    private String gnbm;

    private String czsm;

    private String czrdwbm;

    private String czrgh;

    private String czrmc;

    private String ip;

    private String pclbbm;

    private String pcslbm;

    private String gnmc;

    private String czrdwmc;

    private static final long serialVersionUID = 1L;

    public BigDecimal getLogid() {
        return logid;
    }

    public void setLogid(BigDecimal logid) {
        this.logid = logid;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public String getGnbm() {
        return gnbm;
    }

    public void setGnbm(String gnbm) {
        this.gnbm = gnbm;
    }

    public String getCzsm() {
        return czsm;
    }

    public void setCzsm(String czsm) {
        this.czsm = czsm;
    }

    public String getCzrdwbm() {
        return czrdwbm;
    }

    public void setCzrdwbm(String czrdwbm) {
        this.czrdwbm = czrdwbm;
    }

    public String getCzrgh() {
        return czrgh;
    }

    public void setCzrgh(String czrgh) {
        this.czrgh = czrgh;
    }

    public String getCzrmc() {
        return czrmc;
    }

    public void setCzrmc(String czrmc) {
        this.czrmc = czrmc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPclbbm() {
        return pclbbm;
    }

    public void setPclbbm(String pclbbm) {
        this.pclbbm = pclbbm;
    }

    public String getPcslbm() {
        return pcslbm;
    }

    public void setPcslbm(String pcslbm) {
        this.pcslbm = pcslbm;
    }

    public String getGnmc() {
        return gnmc;
    }

    public void setGnmc(String gnmc) {
        this.gnmc = gnmc;
    }

    public String getCzrdwmc() {
        return czrdwmc;
    }

    public void setCzrdwmc(String czrdwmc) {
        this.czrdwmc = czrdwmc;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        YxRzCz other = (YxRzCz) that;
        return (this.getLogid() == null ? other.getLogid() == null : this.getLogid().equals(other.getLogid()))
            && (this.getCzsj() == null ? other.getCzsj() == null : this.getCzsj().equals(other.getCzsj()))
            && (this.getCzlx() == null ? other.getCzlx() == null : this.getCzlx().equals(other.getCzlx()))
            && (this.getGnbm() == null ? other.getGnbm() == null : this.getGnbm().equals(other.getGnbm()))
            && (this.getCzsm() == null ? other.getCzsm() == null : this.getCzsm().equals(other.getCzsm()))
            && (this.getCzrdwbm() == null ? other.getCzrdwbm() == null : this.getCzrdwbm().equals(other.getCzrdwbm()))
            && (this.getCzrgh() == null ? other.getCzrgh() == null : this.getCzrgh().equals(other.getCzrgh()))
            && (this.getCzrmc() == null ? other.getCzrmc() == null : this.getCzrmc().equals(other.getCzrmc()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getPclbbm() == null ? other.getPclbbm() == null : this.getPclbbm().equals(other.getPclbbm()))
            && (this.getPcslbm() == null ? other.getPcslbm() == null : this.getPcslbm().equals(other.getPcslbm()))
            && (this.getGnmc() == null ? other.getGnmc() == null : this.getGnmc().equals(other.getGnmc()))
            && (this.getCzrdwmc() == null ? other.getCzrdwmc() == null : this.getCzrdwmc().equals(other.getCzrdwmc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogid() == null) ? 0 : getLogid().hashCode());
        result = prime * result + ((getCzsj() == null) ? 0 : getCzsj().hashCode());
        result = prime * result + ((getCzlx() == null) ? 0 : getCzlx().hashCode());
        result = prime * result + ((getGnbm() == null) ? 0 : getGnbm().hashCode());
        result = prime * result + ((getCzsm() == null) ? 0 : getCzsm().hashCode());
        result = prime * result + ((getCzrdwbm() == null) ? 0 : getCzrdwbm().hashCode());
        result = prime * result + ((getCzrgh() == null) ? 0 : getCzrgh().hashCode());
        result = prime * result + ((getCzrmc() == null) ? 0 : getCzrmc().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getPclbbm() == null) ? 0 : getPclbbm().hashCode());
        result = prime * result + ((getPcslbm() == null) ? 0 : getPcslbm().hashCode());
        result = prime * result + ((getGnmc() == null) ? 0 : getGnmc().hashCode());
        result = prime * result + ((getCzrdwmc() == null) ? 0 : getCzrdwmc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logid=").append(logid);
        sb.append(", czsj=").append(czsj);
        sb.append(", czlx=").append(czlx);
        sb.append(", gnbm=").append(gnbm);
        sb.append(", czsm=").append(czsm);
        sb.append(", czrdwbm=").append(czrdwbm);
        sb.append(", czrgh=").append(czrgh);
        sb.append(", czrmc=").append(czrmc);
        sb.append(", ip=").append(ip);
        sb.append(", pclbbm=").append(pclbbm);
        sb.append(", pcslbm=").append(pcslbm);
        sb.append(", gnmc=").append(gnmc);
        sb.append(", czrdwmc=").append(czrdwmc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}