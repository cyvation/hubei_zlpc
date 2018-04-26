package com.start.boot.domain;

/**
 * 人员编码实体类
 */
public class Rybm {

    private String action;

    /**
     * 单位编码
     */
    private String dwbm;
    /**
     * 工号
     */
    private String gh;
    /**
     * 登录别名
     */
    private String dlbm;
    /**
     * 口令
     */
    private String kl;
    /**
     * 名称
     */
    private String mc;
    /**
     * 工作证号
     */
    private String gzzh;
    /**
     * 移动电话号码
     */
    private String yddhhm;
    /**
     * 电子邮件
     */
    private String dzyj;
    /**
     * 原单位编码
     */
    private String ydwbm;
    /**
     * 原单位名称
     */
    private String ydwmc;
    /**
     * 是否临时人员
     */
    private String sflsry;
    /**
     * 角色编码
     */
    private String jsbm;
    /**
     * 部门编码
     */
    private String bmbm;
    /**
     * 是否删除
     */
    private String sfsc;
    /**
     * 是否停职
     */
    private String sftz;
    /**
     * 照片
     */
    private String zp;
    /**
     * 性别 0:女 1:男
     */
    private String xb;
    /**
     * CA证号
     */
    private String caid;
    /**
     * 主任检察官工号
     */
    private String zrjcggh;
    /**
     * 电子邮件
     */
    private String  dzyx;
    /**
     *新单位编码
     */
    private String  xdwbm;
    /**
     * 无参构造函数
     */
    public Rybm() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取单位编码
     *
     * @return 单位编码
     */
    public String getDwbm() {
        return dwbm;
    }

    /**
     * 设置单位编码
     *
     * @param dwbm 单位编码
     */
    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    /**
     * @return 角色编码
     */
    public String getJsbm() {
        return jsbm;
    }


    /**
     * @param jsbm 角色编码
     */
    public void setJsbm(String jsbm) {
        this.jsbm = jsbm;
    }

    /**
     * @return 部门编码
     */
    public String getBmbm() {
        return bmbm;
    }


    /**
     * @param bmbm 部门编码
     */
    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }
    /**
     * 获取工号
     *
     * @return 工号
     */
    public String getGh() {
        return gh;
    }

    /**
     * 设置工号
     *
     * @param gh 工号
     */
    public void setGh(String gh) {
        this.gh = gh;
    }

    /**
     * 获取登录别名
     *
     * @return 登录别名
     */
    public String getDlbm() {
        return dlbm;
    }

    /**
     * 设置登录别名
     *
     * @param dlbm 登录别名
     */
    public void setDlbm(String dlbm) {
        this.dlbm = dlbm;
    }

    /**
     * 获取口令
     *
     * @return 口令
     */
    public String getKl() {
        return kl;
    }

    /**
     * 设置口令
     *
     * @param kl 口令
     */
    public void setKl(String kl) {
        this.kl = kl;
    }

    /**
     * 获取名字
     *
     * @return 名字
     */
    public String getMc() {
        return mc;
    }

    /**
     * 设置名字
     *
     * @param mc 名字
     */
    public void setMc(String mc) {
        this.mc = mc;
    }

    /**
     * 获取工作证号
     *
     * @return 工作证号
     */
    public String getGzzh() {
        return gzzh;
    }

    /**
     * 设置工作证号
     *
     * @param gzzh 工作证号
     */
    public void setGzzh(String gzzh) {
        this.gzzh = gzzh;
    }

    /**
     * 获取移动电话号码
     *
     * @return 移动电话号码
     */
    public String getYddhhm() {
        return yddhhm;
    }

    /**
     * 设置移动电话号码
     *
     * @param yddhhm 移动电话号码
     */
    public void setYddhhm(String yddhhm) {
        this.yddhhm = yddhhm;
    }

    /**
     * 获取电子邮件
     *
     * @return 电子邮件
     */
    public String getDzyj() {
        return dzyj;
    }

    /**
     * 设置电子邮件
     *
     * @param dzyj 电子邮件
     */
    public void setDzyj(String dzyj) {
        this.dzyj = dzyj;
    }

    /**
     * 获取原单位编码
     *
     * @return 原单位编码
     */
    public String getYdwbm() {
        return ydwbm;
    }

    /**
     * 设置原单位编码
     *
     * @param ydwbm 原单位编码
     */
    public void setYdwbm(String ydwbm) {
        this.ydwbm = ydwbm;
    }

    /**
     * 获取原单位名称
     *
     * @return 原单位名称
     */
    public String getYdwmc() {
        return ydwmc;
    }

    /**
     * 设置原单位名称
     *
     * @param ydwmc 原单位名称
     */
    public void setYdwmc(String ydwmc) {
        this.ydwmc = ydwmc;
    }

    /**
     * 获取是否临时人员
     *
     * @return 是否临时人员
     */
    public String getSflsry() {
        return sflsry;
    }

    /**
     * 设置是否是临时人员
     *
     * @param sflsry 是否临时人员
     */
    public void setSflsry(String sflsry) {
        this.sflsry = sflsry;
    }

    /**
     * 获取是否删除
     *
     * @return 是否删除
     */
    public String getSfsc() {
        return sfsc;
    }

    /**
     * 设置是否删除
     *
     * @param sfsc 是否删除
     */
    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    /**
     * 获取是否停职
     *
     * @return 是否停职
     */
    public String getSftz() {
        return sftz;
    }

    /**
     * 设置是否停职
     *
     * @param sftz 是否停职
     */
    public void setSftz(String sftz) {
        this.sftz = sftz;
    }

    /**
     * 获取照片
     *
     * @return 照片
     */
    public String getZp() {
        return zp;
    }

    /**
     * 设置照片
     *
     * @param zp 照片
     */
    public void setZp(String zp) {
        this.zp = zp;
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    public String getXb() {
        return xb;
    }

    /**
     * 设置性别
     *
     * @param xb 性别
     */
    public void setXb(String xb) {
        this.xb = xb;
    }

    /**
     * 获取CA账号
     *
     * @return CA账号
     */
    public String getCaid() {
        return caid;
    }

    /**
     * 设置CA账号
     *
     * @param caid CA账号
     */
    public void setCaid(String caid) {
        this.caid = caid;
    }

    /**
     * 获取主任检察官工号
     *
     * @return 主任检察官工号
     */
    public String getZrjcggh() {
        return zrjcggh;
    }

    /**
     * 设置主任检察官工号
     *
     * @param zrjcggh 主任检察官工号
     */
    public void setZrjcggh(String zrjcggh) {
        this.zrjcggh = zrjcggh;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getXdwbm() {
        return xdwbm;
    }

    public void setXdwbm(String xdwbm) {
        this.xdwbm = xdwbm;
    }

    @Override
    public String toString() {
        return "Rybm{" +
                "action='" + action + '\'' +
                ", dwbm='" + dwbm + '\'' +
                ", gh='" + gh + '\'' +
                ", dlbm='" + dlbm + '\'' +
                ", kl='" + kl + '\'' +
                ", mc='" + mc + '\'' +
                ", gzzh='" + gzzh + '\'' +
                ", yddhhm='" + yddhhm + '\'' +
                ", dzyj='" + dzyj + '\'' +
                ", ydwbm='" + ydwbm + '\'' +
                ", ydwmc='" + ydwmc + '\'' +
                ", sflsry='" + sflsry + '\'' +
                ", jsbm='" + jsbm + '\'' +
                ", bmbm='" + bmbm + '\'' +
                ", sfsc='" + sfsc + '\'' +
                ", sftz='" + sftz + '\'' +
                ", zp='" + zp + '\'' +
                ", xb='" + xb + '\'' +
                ", caid='" + caid + '\'' +
                ", zrjcggh='" + zrjcggh + '\'' +
                ", dzyx='" + dzyx + '\'' +
                ", xdwbm='" + xdwbm + '\'' +
                '}';
    }
}
