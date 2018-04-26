package com.start.boot.constant;

/**
 * <p>
 * Title:Quarter
 * </p>
 * <p>
 * Description: 季度
 * </p>
 * author 朱春雨 date 2017年7月20日 下午2:38:14
 */
public enum Quarter {
	/**
	 * 第一季度
	 */
	FIRST("1","03-01", "05-31"),
	/**
	 * 第二季度
	 */
	SECOND("2","06-01", "08-31"),
	/**
	 * 第三季度
	 */
	THIRDLY("3","09-01", "11-30"),
	/**
	 * 第四季度
	 */
	FOURTHLY("4","12-01", "02-28");

	private String num;
	private String startDate;
	private String endDate;

	Quarter(String num,String startDate, String endDate) {
		this.num = num;
		this.startDate = startDate;
		this.endDate = endDate;
	}
    
	public String getNum() {
		return num;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

}
