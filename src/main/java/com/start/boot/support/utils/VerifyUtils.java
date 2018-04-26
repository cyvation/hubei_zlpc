package com.start.boot.support.utils;


import com.start.boot.constant.PatternRules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zsq
 *         创建日期： 2017年6月28日
 *         验证工具 主要是正则表达式的验证
 */
public class VerifyUtils {


    /**
     * 验证
     *
     * @param value        被验证的值
     * @param patternRules 验证规则 在patternRules接口中可以找到
     * @return true or false
     */
    public static boolean verify(String value, String patternRules) {
        return verify(value, Pattern.compile(patternRules));
    }

    /**
     * 验证
     *
     * @param value   被验证的值
     * @param pattern 验证规则
     * @return
     */
    public static boolean verify(String value, Pattern pattern) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 是否是邮箱格式
     *
     * @param value 被验证的值
     * @return
     */
    public static boolean isEmail(String value) {
        return verify(value, PatternRules.PATTERN_EMAIL);
    }

    /**
     * 是否是数字
     *
     * @param value 被验证的值
     * @return
     */
    public static boolean isNumber(String value) {
        return verify(value, PatternRules.PATTERN_INT);
    }

    /**
     * 是否是手机号
     *
     * @param value 被验证的值
     * @return
     */
    public static boolean isMobile(String value) {
        return verify(value, PatternRules.PATTERN_MOBILE);
    }

    /**
     * 是否是域名
     *
     * @param value 被验证的值
     * @return
     */
    public static boolean isDomainName(String value) {
        return verify(value, PatternRules.PATTERN_DOMAIN);
    }
}
