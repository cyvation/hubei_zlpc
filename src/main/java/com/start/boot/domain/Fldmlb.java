package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 分类代码类别
 *
 *
 */
public class Fldmlb {

	private String lbbm;//类别编码

	private String lbmc;//类别名称

	private String lbbz; //类别标准

	private String lbgbdm; //类别国标代码

	private String sfsc; //是否删除

	private String sfzdydm; //是否自定义代码

	private String dmsslb; //分类代码所属类别

	private String fbm;


	public String getLbbm() {
		return lbbm;
	}

	public void setLbbm(String lbbm) {
		this.lbbm = lbbm;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getLbbz() {
		return lbbz;
	}

	public void setLbbz(String lbbz) {
		this.lbbz = lbbz;
	}

	public String getLbgbdm() {
		return lbgbdm;
	}

	public void setLbgbdm(String lbgbdm) {
		this.lbgbdm = lbgbdm;
	}

	public String getSfsc() {
		return sfsc;
	}

	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}

	public String getSfzdydm() {
		return sfzdydm;
	}

	public void setSfzdydm(String sfzdydm) {
		this.sfzdydm = sfzdydm;
	}

	public String getDmsslb() {
		return dmsslb;
	}

	public void setDmsslb(String dmsslb) {
		this.dmsslb = dmsslb;
	}

	public String getFbm() {
		return fbm;
	}

	public void setFbm(String fbm) {
		this.fbm = fbm;
	}
}
