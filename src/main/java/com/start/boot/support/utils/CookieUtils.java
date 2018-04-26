package com.start.boot.support.utils;

import javax.servlet.http.Cookie;

/**
 * <h3>Cookie工具类</h3>
 *
 * @author 符黄辰君
 * @since 2017/8/9.
 */
public class CookieUtils {


    private CookieUtils() {
    }

    /**
     * 添加cookie
     *
     * @param name   名称
     * @param value  值
     * @param maxAge 过期时间
     */
    public static void addCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        HttpContextUtils.getHttpServletResponse().addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param name 名称
     */
    public static void removeCookie(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        HttpContextUtils.getHttpServletResponse().addCookie(cookie);
    }

    /**
     * 获取cookie值
     *
     * @param name 名称
     * @return
     */
    public static String getCookie(String name) {
        Cookie cookies[] = HttpContextUtils.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
