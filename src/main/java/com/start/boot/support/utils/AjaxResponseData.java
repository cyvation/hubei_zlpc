package com.start.boot.support.utils;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ajax 调用 servlet 返回结果
 * @author Administrator
 * @param <T> 自定义类
 */

public class AjaxResponseData<T> {

	@JSONField(name = "ErrMsg")
	private String ErrMsg;


	@JSONField(name = "Data")
	private T Data;

	public AjaxResponseData(String ErrMsg, T Data){
		this.ErrMsg = ErrMsg == null ? "" : ErrMsg;
		this.Data = Data;
	}

	/**
	 * @return 异常信息
	 */
	public String getErrMsg() {
		return ErrMsg;
	}

	/**
	 * @param errMsg
	 */

	public void setErrMsg(String errMsg) {
		this.ErrMsg = ErrMsg;
	}

	/**
	 * @return 数据
	 */
	public T getData() {
		return Data;
	}


	public void setData(T Data) {
		this.Data = Data;
	}
}
