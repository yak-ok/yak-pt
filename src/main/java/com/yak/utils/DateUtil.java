package com.yak.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date str2DateTime(String str) {
        if (StringUtils.isNotBlank(str)) {
            if (str.split(":").length < 2) {
                str = str + " 00:00:00";
            }
            else if (str.split(":").length == 2) {
                str = str + ":00";
            }
            SimpleDateFormat dfs = new SimpleDateFormat(DATE_TIME_FORMAT);
            try {
                return dfs.parse(str);
            }
            catch (ParseException e) {
            }
        }
        return null;
    }

    public static Date str2Date(String str) {
        return str2Date(str, DATE_FORMAT);
    }


    public static String getAllDifDate(String start, String end) {
        if(StringUtils.isBlank(start) || StringUtils.isBlank(end)) {
            return "0";
        }
        try {
            Date begin = str2DateTime(start);
            Date endDate = str2DateTime(end);
            long day = (endDate.getTime() - begin.getTime()) / 1000 / 3600 / 24;
            return day + "";
        }
        catch (Exception e) {
            return "0";
        }
    }

    /**
     * 获得两个时间的差值,不减工作时间
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回分钟
     */
    public static String getAllDifTime(String startTime, String endTime) {
        try {
            Date begin = str2DateTime(startTime);
            Date end = str2DateTime(endTime);
            Long between = (end.getTime() - begin.getTime());
            if (between < 0) {
                return "-1";
            }
            between = between / 1000 / 60;
            return Long.toString(between);
        }
        catch (Exception e) {
            return "0";
        }
    }

    /**
     * 比较开始时间是否小于结束时间
     *
     * @param startTime 开始时间字符串
     * @param endTime 结束时间字符串
     * @return 返回true表示成立, 开始时间小于结束时间，false表示开始时间大于结束时间
     */
    public static boolean lessTime(String startTime, String endTime) {
        try {
            Date begin = str2DateTime(startTime);
            Date end = str2DateTime(endTime);
            Long between = (end.getTime() - begin.getTime());
            if (between > 0) {
                return true;
            }
        }
        catch (Exception e) {
        }
        return false;
    }


    /**
     * 获得现在的时间
     *
     * @return 返回指定格式的时间字符串
     */
    public static String getNow(String dateFormat) {
        DateFormat insDateFormat = new SimpleDateFormat(dateFormat);
        return (String) insDateFormat.format(new Date());
    }

    /**
     * 获得现在的时间
     *
     * @return 返回标准的如：2013-01-03 12:09
     */
    public static String getNow() {
        DateFormat insDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return (String) insDateFormat.format(new Date());
    }

    /**
     * 得到当前时间对像
     *
     * @return 返回Date对像
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getDateTime(String timeStr) {
        try {
            if (timeStr.split(":").length < 2) {
                timeStr = timeStr + " 00:00:00";
            }
            else if (timeStr.split(":").length == 2) {
                timeStr = timeStr + ":00";
            }
            return getDate(timeStr, DATE_TIME_FORMAT);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(String timeStr) {
        try {
            return getDate(timeStr, DATE_FORMAT);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转为时间Date对像
     *
     * @param timeStr 要转换的字符串
     * @param timeFormat 时间格式，传null默认为yyyy-MM-dd HH:mm:ss
     * @return 返回Date对像
     */
    public static Date getDate(String timeStr, String timeFormat) {
        try {
            if (timeFormat == null) {
                timeFormat = DATE_TIME_FORMAT;
            }
            SimpleDateFormat dfs = new SimpleDateFormat(timeFormat);
            return dfs.parse(timeStr);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 指定的millis得到时间
     *
     * @param millis
     * @return
     */
    public static Date getDate(long millis) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(millis);
        return canlendar.getTime();
    }

    public static long getMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * 格式化时间为指定格式的字符串
     *
     * @param date 时间对像
     * @param formate 格式化字符串如：yyyy-MM-dd HH:mm:ss
     * @return 返回字符串
     */
    public static String formatDate(Date date, String formate) {
        try {
            SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
            return simpleDateFormate.format(date);
        }
        catch (Exception e) {
        }
        return "";
    }

    public static String formatDateTime(Date date) {
        try {
            SimpleDateFormat simpleDateFormate = new SimpleDateFormat(DATE_TIME_FORMAT);
            return simpleDateFormate.format(date);
        }
        catch (Exception e) {
        }
        return "";
    }

    public static String formatDate(Date date) {
        try {
            SimpleDateFormat simpleDateFormate = new SimpleDateFormat(DATE_FORMAT);
            return simpleDateFormate.format(date);
        }
        catch (Exception e) {
        }
        return "";
    }

    /**
     * 根据指定格式,把字符串转成日期
     *
     * @param sDate
     * @param format
     * @return
     */
    public static Date str2Date(String sDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(sDate);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得现在的日期转换为数字格式
     *
     * @return
     */
    public static String getDateNum() {
        DateFormat insDateFormat = new SimpleDateFormat("yyyyMMdd");
        return insDateFormat.format(new Date());
    }

    /**
     * 获得现在的日期和时间转换为数字格式
     *
     * @return
     */
    public static String getDateTimeNum() {
        DateFormat insDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return insDateFormat.format(new Date());
    }

    /**
     * 计算两个时间对像的时间差(时间的毫秒数),可以得到指定的毫秒数,秒数,分钟数,天数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dataType [part可选值["D","H","M","S","MS"] @return[endDate-startDate]
     */
    public static double getDifTwoTime(Date endDate, Date startDate, String dataType) {
        if (endDate != null && startDate != null) {
            return DateUtil.getDifTwoTime(endDate.getTime(), startDate.getTime(), dataType);
        }
        return 0;
    }

    /**
     * 两个长整型的时间相差(时间的毫秒数),可以得到指定的毫秒数,秒数,分钟数,天数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dateType [part可选值["D","H","M","S","MS"] @return[endDate-startDate]
     */
    public static double getDifTwoTime(long endDate, long startDate, String dateType) {
        double temp = 1;
        if ("S".equalsIgnoreCase(dateType)) {
            temp = 1000;
        }
        else if ("M".equalsIgnoreCase(dateType)) {
            temp = 1000 * 60;
        }
        else if ("H".equalsIgnoreCase(dateType)) {
            temp = 1000 * 60 * 60;
        }
        else if ("D".equalsIgnoreCase(dateType)) {
            temp = 1000 * 60 * 60 * 24;
        }
        return (endDate - startDate) / temp;
    }

    /**
     * 从日期中得到指定部分(YYYY/MM/DD/HH/SS/SSS)数字
     *
     * @param date 日期
     * @param part Calendar的属性
     * @return
     */
    public static int getPartOfTime(Date date, int part) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(part);
    }
}
