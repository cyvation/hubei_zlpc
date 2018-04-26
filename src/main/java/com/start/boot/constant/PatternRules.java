package com.start.boot.constant;

import java.util.regex.Pattern;

/**
 * @author zsq
 *         创建日期： 2017年6月28日
 *         正则表达式规则
 */
public interface PatternRules {

    //【数字】 正则表达式
    Pattern PATTERN_INT = Pattern.compile("\\d+");

    //【yyyy-MM-dd】 正则表达式
    Pattern PATTERN_DATE = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");

    // 【邮箱】正则表达式
    Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    // 【手机号】正则表达式
    Pattern PATTERN_MOBILE = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");

    // 【汉字】正则表达式
    Pattern PATTERN_CHINESE = Pattern.compile("^[\u4e00-\u9fa5]{0,}$");

    // 【域名】正则表达式
    Pattern PATTERN_DOMAIN = Pattern.compile("^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?");

}
