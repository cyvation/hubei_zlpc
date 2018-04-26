package com.start.boot.domain;

/**
 * 功能实体（功能定义）
 * Created by lenovo on 2017/10/21.
 */
public class Function {

    //功能编码（单位编码 + 4位数值）
    private String gnbm;

    //功能名称
    private String gnmc;

    //父节点
    private String flbm;

    //功能程序集
    private String gncxj;

    //功能窗体
    private String gnct;

    //功能说明
    private String gnsm;

    //功能序号
    private Integer gnxh;

    //功能显示名称
    private String gnxsmc;

    //功能参数
    private String gncs;

    //单位编码
    private String dwbm;

    //初始参数
    private String cscs;

    //是否删除（‘N’默认不删除，‘Y’删除）
    private String sfsc;

    //是否模态窗口（‘N’默认不是，‘Y’是）
    private String sfmtck;

    //图标
    private String icon;

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

    public String getFlbm() {
        return flbm;
    }

    public void setFlbm(String flbm) {
        this.flbm = flbm;
    }

    public String getGncxj() {
        return gncxj;
    }

    public void setGncxj(String gncxj) {
        this.gncxj = gncxj;
    }

    public String getGnct() {
        return gnct;
    }

    public void setGnct(String gnct) {
        this.gnct = gnct;
    }

    public String getGnsm() {
        return gnsm;
    }

    public void setGnsm(String gnsm) {
        this.gnsm = gnsm;
    }

    public Integer getGnxh() {
        return gnxh;
    }

    public void setGnxh(Integer gnxh) {
        this.gnxh = gnxh;
    }

    public String getGnxsmc() {
        return gnxsmc;
    }

    public void setGnxsmc(String gnxsmc) {
        this.gnxsmc = gnxsmc;
    }

    public String getGncs() {
        return gncs;
    }

    public void setGncs(String gncs) {
        this.gncs = gncs;
    }

    public String getDwbm() {
        return dwbm;
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getCscs() {
        return cscs;
    }

    public void setCscs(String cscs) {
        this.cscs = cscs;
    }

    public String isSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String isSfmtck() {
        return sfmtck;
    }

    public void setSfmtck(String sfmtck) {
        this.sfmtck = sfmtck;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Function{" +
                "gnbm='" + gnbm + '\'' +
                ", gnmc='" + gnmc + '\'' +
                ", flbm='" + flbm + '\'' +
                ", gncxj='" + gncxj + '\'' +
                ", gnct='" + gnct + '\'' +
                ", gnsm='" + gnsm + '\'' +
                ", gnxh=" + gnxh +
                ", gnxsmc='" + gnxsmc + '\'' +
                ", gncs='" + gncs + '\'' +
                ", dwbm='" + dwbm + '\'' +
                ", cscs='" + cscs + '\'' +
                ", sfsc='" + sfsc + '\'' +
                ", sfmtck='" + sfmtck + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
