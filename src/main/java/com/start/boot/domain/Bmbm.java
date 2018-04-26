package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.start.boot.common.Param_Pager;

/**
 * 部门编码实体类
 * @author 李治鑫 <br/>//extends Param_Pager
 * 主要属性：单位编码,部门编码,父部门编码,部门名称,部门简称,部门文号简称,<br/>
 * 部门案号简称,是否临时机构,是否承办部门,部门序号,备注,是否删除,部门映射(装的是业务类型编码),<br/>
 * 单位名称,单位简称,各个数据库查询出的数据类型，1：统一业务  2：综合业务
 */
public class Bmbm  {

	/**
	 * 单位编码
	 */
	private String dwbm;

	/**
	 * 部门编码
	 */
	private String bmbm;

	/**
	 * 父部门编码
	 */
	private String fbmbm;

	/**
	 * 部门名称
	 */
	private String bmmc;

	/**
	 * 部门简称
	 */
	private String bmjc;

	/**
	 * 部门文号简称
	 */
	private String bmwhjc;

	/**
	 * 部门案号简称
	 */
	private String bmahjc;

	/**
	 * 是否临时机构
	 */
	private String sflsjg;

	/**
	 * 是否承办部门
	 */
	private String sfcbbm;

	/**
	 * 部门序号
	 */
	private int bmxh;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 是否删除
	 */
	private String sfsc;

	/**
	 * 部门映射,装的是对应的业务类型的编码
	 */
	private String bmys;


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

	public String getFbmbm() {
		return fbmbm;
	}

	public void setFbmbm(String fbmbm) {
		this.fbmbm = fbmbm;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBmjc() {
		return bmjc;
	}

	public void setBmjc(String bmjc) {
		this.bmjc = bmjc;
	}

	public String getBmwhjc() {
		return bmwhjc;
	}

	public void setBmwhjc(String bmwhjc) {
		this.bmwhjc = bmwhjc;
	}

	public String getBmahjc() {
		return bmahjc;
	}

	public void setBmahjc(String bmahjc) {
		this.bmahjc = bmahjc;
	}

	public String getSflsjg() {
		return sflsjg;
	}

	public void setSflsjg(String sflsjg) {
		this.sflsjg = sflsjg;
	}

	public String getSfcbbm() {
		return sfcbbm;
	}

	public void setSfcbbm(String sfcbbm) {
		this.sfcbbm = sfcbbm;
	}

	public int getBmxh() {
		return bmxh;
	}

	public void setBmxh(int bmxh) {
		this.bmxh = bmxh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfsc() {
		return sfsc;
	}

	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}

	public String getBmys() {
		return bmys;
	}

	public void setBmys(String bmys) {
		this.bmys = bmys;
	}
}
