package com.start.boot.support.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lei on 2017/11/2.
 */
public class OracleTimeUtils {

    /**
     * 将字符串转换为数据库需要的形式
     */
    public static java.sql.Date format(String str)  {
        java.sql.Date date = null;

        if (StringUtils.isEmpty(str))
            return date;

        try{

            date = new java.sql.Date(DateUtils.converToDate(str).getTime());
        }catch (Exception e){

        }

        return date;
    }
}
