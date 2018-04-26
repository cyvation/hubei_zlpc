package com.start.boot.constant;

/**
 * 
 * <h3>日期格式</h3>
 *
 * 
 * @author 符黄辰君
 * @since 2017年6月27日
 * 
 */
public interface DateFormat {
	/**
	 * Oracle年月日时分秒格式
	 */
	String ORACLE_DATE_FORMAT = "yyyy-MM-dd HH24:mi:ss";
	/**
	 * [年]
	 */
	String YEAR_FORMAT = "yyyy";
	/**
	 * [年-月-日-时-分-秒]
	 */
	String DATE24_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * [年-月-日]
	 */
	String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * [时-分-秒]
	 */
	String TIME24_FORMAT = "HH:mm:ss";
	/**
	 * 时间戳
	 */
	String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

}
