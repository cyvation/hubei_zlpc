package com.start.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 角色编码实体类
 *
 * @author 李治鑫
 * @since 2017-5-9
 */
public class Jsbm implements Serializable{

	/**
	 * 角色编号
	 */
	@JSONField(name = "JSBM")
	private String jsbm;
	/**
	 * 单位编码
	 */
	@JSONField(name = "DWBM")
	private String dwbm;
	/**
	 * 角色名称
	 */
	@JSONField(name = "JSMC")
	private String jsmc;
	/**
	 * 部门编码
	 */
	@JSONField(name = "BMBM")
	private String bmbm;
	/**
	 * 角色序号
	 */
	@JSONField(name = "JSXH")
	private int jsxh;
	/**
	 * 审批角色编码
	 */
	@JSONField(name = "SPJSBM")
	private String spjsbm;

	public String getJsbm() {
		return jsbm;
	}

	public void setJsbm(String jsbm) {
		this.jsbm = jsbm;
	}

	public String getDwbm() {
		return dwbm;
	}

	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	public String getBmbm() {
		return bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public int getJsxh() {
		return jsxh;
	}

	public void setJsxh(int jsxh) {
		this.jsxh = jsxh;
	}

	public String getSpjsbm() {
		return spjsbm;
	}

	public void setSpjsbm(String spjsbm) {
		this.spjsbm = spjsbm;
	}
}
