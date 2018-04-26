package com.start.boot.constant;

/**
 *
 * <h3>请求状态</h3>
 *
 *
 * @author 符黄辰君
 * @since 2017年4月14日
 *
 */
public enum RequestStatus {
	/**
	 * 请求已成功，请求所希望的响应头或数据体将随此响应返回。
	 */
	OK(200),
	/**
	 * 服务器的程序码出错。
	 */
	ERROR(500),
	/**
	 * 服务器已接受请求，但尚未处理
	 */
	NOT_HANDLE(202),
	/**
	 * 拒绝请求。
	 */
	REJECT(415);

	private Integer code;

	RequestStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
