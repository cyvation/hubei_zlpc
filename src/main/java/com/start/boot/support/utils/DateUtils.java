package com.start.boot.support.utils;


import com.start.boot.constant.DateFormat;
import com.start.boot.constant.Quarter;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class DateUtils {

    /**
     * 日期字符串（yyyy-MM-dd 格式）转化为日期
     *
     * @param dateTime yyyy-MM-dd格式时间串
     * @return 时间对象
     * @throws ParseException
     */
    public static Date converToDate(String dateTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DateFormat.DATE_FORMAT);
        return df.parse(dateTime);
    }

    /**
     * 日期字符串转化为日期
     *
     * @param dateTime 时间串
     * @param format   格式
     * @return 时间对象
     * @throws ParseException
     */
    public static Date converToDate(String dateTime, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(dateTime);
    }

    /**
     * 将日期格式化为字符串（yyyy-MM-dd格式）
     *
     * @param date 日期
     * @return 格式化结果
     */
    public static String formatDate(Date date) {
        return formatDate(date, DateFormat.DATE_FORMAT);
    }

    /**
     * 将日期格式化为字符串，自定义格式
     *
     * @param date       日期
     * @param dateFormat 格式
     * @return 格式化后的日期
     */
    public static String formatDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 将日期格式化为字符串，自定义格式
     *
     * @param date       日期
     * @param dateFormat 格式
     * @return 格式化后的日期
     */
    public static String formatDate(Long date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 将当前日期格式化为字符串（yyyy-MM-dd格式）
     *
     * @return 格式化结果
     */
    public static String formatCurrentDate() {
        return formatCurrentDate(DateFormat.DATE_FORMAT);
    }

    /**
     * 使用自定义格式格式化当前日期
     *
     * @param dateFormat 输出显示的时间格式
     * @return 格式化结果
     */
    public static String formatCurrentDate(String dateFormat) {
        return formatDate(new Date(), dateFormat);
    }

    /**
     * 截取某一时间戳 为yyyy-mm-dd
     *
     * @param dateTime 时间
     * @return
     * @throws ParseException
     */
    public static Date cutTimestampToDate(String dateTime) throws ParseException {
        Date date = converToDate(dateTime, DateFormat.DATE24_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();

    }

    /**
     * 获取当前日期前一天
     *
     * @return 前一天的日期
     */
    public static String lastDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return formatDate(date);
    }

    /**
     * 获取当前日期前n天的日期
     *
     * @param n 前n天
     * @return 前n天的日期
     */
    public static String prevDay(int n) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        date = calendar.getTime();
        return formatDate(date);
    }

    /**
     * 获取当前日期前一周
     *
     * @return 前一周的日期
     */
    public static String lastWeek() {
        return prevDay(7);
    }

    /**
     * 获取当前年
     */
    public static String getCurrentYear() {
        Date date = new Date();
        return new SimpleDateFormat(DateFormat.YEAR_FORMAT).format(date);
    }

    /**
     * 是否在今天
     *
     * @param date 时间
     * @return 返回true代表在今天, 返回false不在今天
     */
    public static Boolean isToday(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat(DateFormat.DATE_FORMAT);
        return fmt.format(date).equals(fmt.format(new Date()));
    }

    /**
     * 是否在今天
     *
     * @param dateTime 时间
     * @return 返回true代表在今天, 返回false不在今天
     * @throws ParseException
     */
    public static Boolean isToday(String dateTime) throws ParseException {
        Date date = converToDate(dateTime);
        return isToday(date);
    }

    /**
     * 获取日期区间 ()
     *
     * @param year    年度
     * @param quarter 季度数
     * @return
     * @throws ParseException
     */
    public static DateInterval getDateInterval(String year, String quarter) throws ParseException {
        DateInterval di = new DateInterval();
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(quarter)) {
            return di;
        }
        String startTime = "";
        String endTime = "";

        if (Quarter.FIRST.getNum().equals(quarter)) {
            startTime = year + "-" + Quarter.FIRST.getStartDate();
            endTime = year + "-" + Quarter.FIRST.getEndDate();
        }
        if (Quarter.SECOND.getNum().equals(quarter)) {
            startTime = year + "-" + Quarter.SECOND.getStartDate();
            endTime = year + "-" + Quarter.SECOND.getEndDate();
        }
        if (Quarter.THIRDLY.getNum().equals(quarter)) {
            startTime = year + "-" + Quarter.THIRDLY.getStartDate();
            endTime = year + "-" + Quarter.THIRDLY.getEndDate();
        }
        if (Quarter.FOURTHLY.getNum().equals(quarter)) {
            startTime = year + "-" + Quarter.FOURTHLY.getStartDate();
            endTime = (Integer.parseInt(year) + 1) + "-" + Quarter.FOURTHLY.getEndDate();
        }
        di.setStart(startTime);
        di.setEnd(endTime);
        di.setStartDate(converToDate(startTime));
        di.setEndDate(converToDate(endTime));

        return di;
    }

    /**
     * 获取日期区间
     *
     * @param year 年度
     * @return
     * @throws ParseException
     */
    public static DateInterval getDateInterval(String year) throws ParseException {
        DateInterval di = new DateInterval();
        if (StringUtils.isEmpty(year)) {
            return di;
        }
        String startTime = year  + "-" + Quarter.FIRST.getStartDate();
        String endTime = (Integer.parseInt(year) + 1)  + "-" +  Quarter.FOURTHLY.getStartDate();
        di.setStart(startTime);
        di.setEnd(endTime);
        return di;
    }

    /**
     * 获得当前的年份和季度
     *
     * @param nowDate
     * @return
     * @throws ParseException
     */
    public static Map<String,Object> getNowQuarter(Date nowDate) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date=calendar.get(Calendar.DATE);

        String quarter = "1";
        int firstMonth=3;
        if (month >= 3 && month <= 5) {
        } else if (month >= 6 && month <= 8) {
            quarter = "2";
            firstMonth=6;
        } else if (month >= 9 && month <= 11) {
            quarter = "3";
            firstMonth=9;
        } else {
            quarter = "4";
            firstMonth=12;
            if (month >= 1 && month <= 2)
                year = year - 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("year", year + "");
        map.put("quarter", quarter);
        map.put("month", month);
        map.put("date", date);
        map.put("firstMonth", firstMonth);
        return map;
    }

    /**
     * 日期区间类
     */
    public static class DateInterval {

        private String start;

        private String end;

        private Date startDate;

        private Date endDate;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

}
