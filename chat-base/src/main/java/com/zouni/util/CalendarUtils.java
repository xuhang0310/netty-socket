package com.zouni.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

    // 用户类中格式化时间显示
    private static SimpleDateFormat sdf_one = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf_two = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf_three = new SimpleDateFormat("yyyyMMdd");

    public CalendarUtils() {
    }

    /**
     *
     * Description:把时间字符串格式化为 yyyy-MM-dd HH:mm:ss
     *
     * @Version1.0 Oct 29, 2008 11:49:58 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param str
     * @return
     * @throws ParseException
     */
    public static Calendar convertCalendarDateTime(String str){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf_one.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * Description:把时间字符串格式化为 yyyy-MM-dd
     *
     * @Version1.0 Oct 29, 2008 11:49:58 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param str
     * @return
     * @throws ParseException
     */
    public static Calendar convertCalendarDate(String str){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf_two.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * Description:把时间字符串格式化为 yyyyMMdd
     *
     * @Version1.0 Nov 5, 2008 11:20:35 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param str
     * @return
     * @throws ParseException
     */
    public static Calendar convertCalendarThree(String str){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf_three.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * Description: 把时间字符串格式化为 pattern 格式化类型
     *
     * @Version1.0 Nov 5, 2008 11:22:28 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param str
     * @param pattern
     *            格式化类型
     * @return
     * @throws ParseException
     */
    public static Calendar convertCalendarPattern(String str, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        if (str==null || "".equals(str)){
            return c;
        }
        try {
            c.setTime(sdf.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * Description:把时间格式化为yyyy-MM-dd HH:mm:ss的字符串
     *
     * @Version1.0 Oct 29, 2008 11:53:08 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @return
     */
    public static String convertStrDateTime(Calendar c) {
        return sdf_one.format(c.getTime());
    }

    /**
     *
     * Description:把时间格式化为yyyy-MM-dd的字符串
     *
     * @Version1.0 Oct 29, 2008 11:53:08 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @return
     */
    public static String convertStrDate(Calendar c) {
        return sdf_two.format(c.getTime());
    }

    /**
     *
     * Description: 把时间格式化为yyyyMMdd的字符串
     *
     * @Version1.0 Nov 5, 2008 11:25:48 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @return
     */
    public static String convertStrThree(Calendar c) {
        return sdf_three.format(c.getTime());
    }

    /**
     *
     * Description: 把时间格式化为pattern 格式的字符串
     *
     * @Version1.0 Nov 5, 2008 11:26:51 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param pattern
     * @return
     */
    public static String convertStrPattern(Calendar c, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    /**
     *
     * Description: 输入时间的字符串，返回零点 主要用在时间段查询的开始时间 格式为yyyy-MM-dd 00:00:00
     * Precondition: date!=null if(date==null) return null;
     *
     * @Version1.0 Oct 29, 2008 11:44:59 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     * @throws ParseException
     */
    public static Calendar convertBeginCalendar(String date){
        Calendar c = Calendar.getInstance();
        try {
            if (date.length() > 10)
                c.setTime(sdf_one.parse(date));
            else
                c.setTime(sdf_two.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    /**
     * Description: 输入时间的字符串，返回时间点末 主要用在时间段查询的结束时间 格式为yyyy-MM-dd 23:59:59
     *
     * @Version1.0 Oct 29, 2008 11:46:35 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     * @throws ParseException
     */
    public static Calendar convertLastCalendar(String date){
        Calendar c = Calendar.getInstance();
        try {
            if (date.length() > 10)
                c.setTime(sdf_one.parse(date));
            else
                c.setTime(sdf_two.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    /**
     * Description: 输入时间的字符串，返回时间点末 主要用在时间段查询的结束时间 格式为yyyy-MM-dd 23:59:59
     * @param date
     * @return
     */
    public static String convertLastCalendarStr(String date){
        Calendar c = convertLastCalendar(date);
        return convertStrDateTime(c);
    }

    /**
     *
     * Description: 返回零点 主要用在时间段查询的开始时间 格式为yyyy-MM-dd 00:00:00 Precondition:
     * date!=null if(date==null) return null;
     *
     * @Version1.0 Oct 29, 2008 11:44:59 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getBeginCalendar(Calendar c){
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    /**
     * Description: 返回时间点末 主要用在时间段查询的结束时间 格式为yyyy-MM-dd 23:59:59
     *
     * @Version1.0 Oct 29, 2008 11:46:35 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getLastCalendar(Calendar c){
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    /**
     *
     * Description: 输入时间和月份 获得该时间前几个月份的时间 主要供任务调度用 Precondition: 一般 1<=period<=12
     * input:2008-11-25,6 return 2008-05-31 23:59:59
     *
     * @Version1.0 Oct 30, 2008 3:51:01 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     *            时间
     * @param
     *
     * @return
     */
    public static Calendar getModifiedCalendar(Calendar c, String premonth){
        String str = convertStrDate(c);
        String temp = str.substring(0, 4);
        String t = str.substring(5, 7);
        int p = Integer.valueOf(premonth);
        if (p < 1 || p > 12)
            p = 6;
        int year = Integer.valueOf(temp);
        int month = Integer.valueOf(t);
        if (month <= p) {
            month = month + 11 - p;
            if (month == 0) {
                month = 12;
                year = year - 1;
            }
            year = year - 1;
        } else
            month = month - p - 1;
        int day = daysInMonth(year, month);
        str = year + "-" + month + "-"
                + day;
        Calendar obj = convertLastCalendar(str);
        return obj;
    }

    /**
     *
     * Description: 输入时间和月份 获得该时间前几个月份的时间 input:2008-11-25,6 return 2008-05-31
     * 23:59:59
     *
     * @Version1.0 Oct 30, 2008 3:51:01 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     *            时间
     * @param
     *
     * @return
     */
    public static Calendar getModifiedCalendar(String date, String premonth){
        String temp = date.substring(0, 4);
        String t = date.substring(5, 7);
        int p = Integer.valueOf(premonth);
        if (p < 1 || p > 12)
            return null;
        int year = Integer.valueOf(temp);
        int month = Integer.valueOf(t);
        if (month <= p) {
            month = month + 11 - p;
            if (month == 0) {
                month = 12;
                year = year - 1;
            }
            year = year - 1;
        } else
            month = month - p - 1;
        int day = daysInMonth(year, month);
        String str = year + "-" + month + "-"
                + day;
        Calendar obj = convertLastCalendar(str);
        return obj;
    }

    /**
     *
     * Description: 获得根据小时时间零点 格式为yyyy-MM-dd 00:00:00 主要是统计用
     * precondition:if(hour>23) hour=23 if(hour<0) hour=0 bool is true return
     * yyyy-MM-dd 00:00:00 else yyyy-MM-dd 00:59:59
     *
     * @Version1.0 Nov 4, 2008 6:04:58 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param hour
     * @param
     * @return
     */
    public static Calendar getHourCalendar(String date, int hour, boolean isbegin){
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (hour > 23)
            hour = 23;
        if (hour < 0)
            hour = 0;
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, hour);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, hour);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description:
     *
     * @Version1.0 May 31, 2009 11:19:19 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param obj
     * @param hour
     * @param isbegin
     * @return
     */
    public static Calendar getHourCalendar(Calendar obj, int hour, boolean isbegin){
        if (hour > 23)
            hour = 23;
        if (hour < 0)
            hour = 0;
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, hour);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, hour);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description: 按照指定的格式 格式化传入的date对象
     *
     * @Version1.0 Oct 31, 2008 2:35:31 PM by 郝军（haojun@d-heaven.com）创建
     * @param format
     * @param date
     * @return
     */
    public static String convertDateToString(String format, Date date) {
        if (format == null || date == null) {
            return null;
        }
        SimpleDateFormat sdfs = new SimpleDateFormat(format);
        return sdfs.format(date.getTime());
    }

    /**
     *
     * Description: 根据days返回本周的星期日 1:周一 一次类推 0<days or days>7 返回周一
     *
     * @Version1.0 Nov 4, 2008 6:48:37 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param days
     * @return
     */
    public static Calendar getDayOfWeek(String date, int days){
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        int datInWeek = obj.get(Calendar.DAY_OF_WEEK);
        if (days <= 0 || days > 7 || days == 1)
            datInWeek--;
        else
            datInWeek = datInWeek - days;

        obj.add(Calendar.DAY_OF_MONTH, -(datInWeek - 1));
        return obj;
    }

    /**
     *
     * Description: 根据weeks days返回该月第几周第几天 1:周一 一次类推 0<days or days>7 返回周一 0<weeks<6
     * else weeks=1
     *
     * @Version1.0 Nov 4, 2008 6:48:37 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param weeks
     * @param days
     * @return
     */
    public static Calendar getFirstDayOfWeekOfMonth(String date, int weeks,	int days) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (weeks < 1 || weeks > 6)
            weeks = 1;
        obj.set(Calendar.WEEK_OF_MONTH, weeks);
        int datInWeek = obj.get(Calendar.DAY_OF_WEEK);
        if (days <= 0 || days > 7 || days == 1)
            datInWeek--;
        else
            datInWeek = datInWeek - days;

        obj.add(Calendar.DAY_OF_MONTH, -(datInWeek - 1));
        return obj;
    }

    /**
     *
     * Description: 根据weeks 返回当前月所在周在当前月的第一天
     *
     * @Version1.0 Nov 5, 2008 9:37:44 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param weeks
     * @return
     */
    public static Calendar getFirstDayInWeekOfMonth(String date, int weeks) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (weeks < 1 || weeks > 6)
            weeks = 1;
        int month = obj.get(Calendar.MONTH);
        // System.out.println(month);
        // 得到该周 返回该周周一
        obj.set(Calendar.WEEK_OF_MONTH, weeks);
        // System.out.println(obj.get(Calendar.MONTH));
        if (weeks == 1) {
            // 如果本月第一周地一天不是属于该月 则返回该周所在月的第一天
            if (month != obj.get(Calendar.MONTH)) {
                obj.set(Calendar.MONTH, month);
                obj.set(Calendar.DAY_OF_MONTH, 1);
            } else
                obj.set(Calendar.DAY_OF_MONTH, 1);
        } else
            obj.set(Calendar.DAY_OF_WEEK, 2);
        return obj;
    }

    /**
     *
     * Description: 根据weeks 返回当前月所在周在当前月的最后一天
     *
     * @Version1.0 Nov 5, 2008 9:37:44 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param weeks
     * @return
     */
    public static Calendar getLastDayInWeekOfMonth(String date, int weeks) {
        Calendar obj, c;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (weeks < 1 || weeks > 6)
            weeks = 1;
        int month = obj.get(Calendar.MONTH);
        // 得到该周 返回该周周一
        obj.set(Calendar.WEEK_OF_MONTH, weeks);
        // 获得该周的最后一天
        String temp = convertStrDate(obj);
        c = getDayOfWeek(temp, 7);
        // 如果该周的最后一天不是属于该月 则返回该周所在月的最后一天
        if (month != c.get(Calendar.MONTH)) {
            obj.set(Calendar.DAY_OF_MONTH, obj.getActualMaximum(Calendar.DATE));
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
            return obj;
        } else {
            c.set(Calendar.DAY_OF_WEEK, 8);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            return c;
        }
    }

    /**
     *
     * Description: 根据days返回本周的时间点 1:周一 依次类推 0<days or days>7 返回周一 格式
     * yyyy-MM-dd 00:00:00 isbegin 是则返回零点 否则返回末点
     *
     * @Version1.0 Nov 4, 2008 7:08:13 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param days
     * @return
     */
    public static Calendar getDayInWeek(String date, int days, boolean isbegin) {
        Calendar obj = getDayOfWeek(date, days);
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, 0);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description: 获得该日期当前月的周数
     *
     * @Version1.0 Nov 4, 2008 7:39:28 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @return
     */
    public static int getWeeksOfMonth(String date) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        int weeklist = obj.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int month = obj.get(Calendar.MONTH) + 1;
        // 如果是2月份 恰好2月1号是周一 不是闰年
        if (month == 2) {
            obj.set(Calendar.DAY_OF_MONTH, 1);
            int day = obj.get(Calendar.DAY_OF_WEEK);
            if (day == 2)
                weeklist = 4;
        }
        if (weeklist == 6)
            weeklist--;
        return weeklist;
    }

    /**
     *
     * Description: 返回该年第几月的第一天 月从1--12分别表示一月份....
     *
     * @Version1.0 Nov 5, 2008 2:19:33 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param isbegin
     *            是否零点
     * @return
     */
    public static Calendar getFirstDayOfMonth(String date, int month, boolean isbegin) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (month < 1)
            month = 1;
        if (month > 12)
            month = 12;

        obj.set(Calendar.MONTH, month - 1);
        obj.set(Calendar.DAY_OF_MONTH, 1);
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, 0);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description: 返回当前月的最后一天 月从1--12分别表示一月份....
     *
     * @Version1.0 Nov 5, 2008 2:19:33 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param isbegin
     *            是否零点
     * @return
     */
    public static Calendar getLastDayOfMonth(String date, int month, boolean isbegin){
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        if (month < 1)
            month = 1;
        if (month > 12)
            month = 12;

        obj.set(Calendar.MONTH, month - 1);
        obj.set(Calendar.DAY_OF_MONTH, obj.getActualMaximum(Calendar.DATE));
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, 0);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description: 返回该年的第一天
     *
     * @Version1.0 Jun 19, 2009 11:34:43 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param isbegin
     * @return
     */
    public static Calendar getFirstDayOfYear(String date, boolean isbegin) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        obj.set(Calendar.DAY_OF_YEAR, 1);
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, 0);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description: 返回该年的最后一天
     *
     * @Version1.0 Jun 19, 2009 11:36:53 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param isbegin
     * @return
     */
    public static Calendar getLastDayOfYear(String date, boolean isbegin) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        obj.set(Calendar.MONTH, 11);
        obj.set(Calendar.DAY_OF_MONTH, 31);
        if (isbegin) {
            obj.set(Calendar.HOUR_OF_DAY, 0);
            obj.set(Calendar.MINUTE, 0);
            obj.set(Calendar.SECOND, 0);
        } else {
            obj.set(Calendar.HOUR_OF_DAY, 23);
            obj.set(Calendar.MINUTE, 59);
            obj.set(Calendar.SECOND, 59);
        }
        return obj;
    }

    /**
     *
     * Description:获得该月第几周的第一天的时间零点 格式yyyy-MM-dd 00:00:00 主要是供查询统计用
     *
     * @Version1.0 Nov 4, 2008 8:14:08 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param weeks
     * @return
     */
    public static Calendar getFirstDayOfWeekInMonth(String date, int weeks) {
        Calendar obj = getFirstDayInWeekOfMonth(date, weeks);
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        return obj;
    }

    /**
     *
     * Description:获得该月第几周的第一天的时间末点 格式yyyy-MM-dd 23:59:59 主要是供查询统计用
     *
     * @Version1.0 Nov 4, 2008 8:14:08 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param weeks
     * @return
     */
    public static Calendar getLastDayOfWeekInMonth(String date, int weeks) {
        Calendar obj = getLastDayInWeekOfMonth(date, weeks);
        obj.set(Calendar.HOUR_OF_DAY, 23);
        obj.set(Calendar.MINUTE, 59);
        obj.set(Calendar.SECOND, 59);
        return obj;
    }

    /**
     * Description: 获得新增年的第一天
     *
     * @Version1.0 May 31, 2009 10:57:39 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getFirstDayInYear(String begin, int addyear) {
        Calendar obj = convertBeginCalendar(begin);
        obj.add(Calendar.YEAR, addyear);
        obj.set(Calendar.MONTH, 0);
        obj.set(Calendar.DAY_OF_MONTH, 1);
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        return obj;
    }

    /**
     * Description: 获得新增月的第一天
     *
     * @Version1.0 May 31, 2009 10:57:39 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getFirstDayInMonth(String begin, int addmonth) {
        Calendar obj = convertBeginCalendar(begin);
        obj.add(Calendar.MONTH, addmonth);
        obj.set(Calendar.DAY_OF_MONTH, 1);
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        return obj;
    }

    /**
     *
     * Description:
     *
     * @Version1.0 Jun 5, 2009 3:22:22 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param last
     * @return
     */
    public static Calendar setPreDay(Calendar c, boolean last) {
        int day = c.get(Calendar.DAY_OF_YEAR);
        c.set(Calendar.DAY_OF_YEAR, day - 1);
        if (last) {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
        } else {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }
        return c;
    }

    /**
     *
     * Description:
     *
     * @Version1.0 Jun 5, 2009 3:48:15 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param last
     * @return
     */
    public static Calendar getNextDay(Calendar c, boolean last) {
        int day = c.get(Calendar.DAY_OF_YEAR);
        c.set(Calendar.DAY_OF_YEAR, day + 1);

        if (last) {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
        } else {
            c.set(Calendar.HOUR_OF_DAY, -12);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }
        return c;
    }

    /**
     * Description: 获得新增周的第一天
     *
     * @Version1.0 May 31, 2009 10:57:39 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getFirstDayInWeek(String begin, int addweek) {
        Calendar obj = convertBeginCalendar(begin);
        obj.add(Calendar.WEEK_OF_YEAR, addweek);
        obj.set(Calendar.DAY_OF_WEEK, 2);
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        return obj;
    }

    /**
     * Description: 获得新增天的零点
     *
     * @Version1.0 May 31, 2009 10:57:39 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param
     * @return
     */
    public static Calendar getFirstTimeInDay(String begin, int addday) {
        Calendar obj = convertBeginCalendar(begin);
        obj.add(Calendar.DAY_OF_MONTH, addday);
        obj.set(Calendar.HOUR_OF_DAY, 0);
        obj.set(Calendar.MINUTE, 0);
        obj.set(Calendar.SECOND, 0);
        return obj;
    }

    /**
     *
     * Description: 在时间的基础上再加几天
     *
     * @Version1.0 Dec 3, 2008 2:30:56 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param day
     * @return
     */
    public static Calendar getAddDay(Calendar c, int day) {
        c.add(Calendar.DAY_OF_MONTH, day);
        return c;
    }

    /**
     *
     * Description: 在时间的基础上再加几天
     *
     * @Version1.0 Dec 3, 2008 2:30:56 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static Calendar getAddDay(String date, int day){
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        obj.add(Calendar.DAY_OF_MONTH, day);
        return obj;
    }

    /**
     *
     * Description: 在时间的基础上再加几天
     *
     * @Version1.0 Dec 3, 2008 2:30:56 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param hour
     * @return
     */
    public static Calendar getAddHour(Calendar c, int hour) {
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c;
    }

    /**
     *
     * Description: 在时间的基础上再加几天
     *
     * @Version1.0 Dec 3, 2008 2:30:56 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param hour
     * @return
     */
    public static Calendar getAddHour(String date, int hour) {
        Calendar obj;
        if (date.length() > 10)
            obj = convertCalendarDateTime(date);
        else
            obj = convertCalendarDate(date);
        obj.add(Calendar.HOUR_OF_DAY, hour);
        return obj;
    }

    /**
     *
     * Description: 增加年
     *
     * @Version1.0 Feb 13, 2009 3:49:21 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param year
     * @return
     */
    public static Calendar getAddYear(Calendar c, int year) {
        c.add(Calendar.YEAR, year);
        return c;
    }

    /**
     *
     * Description: 增加年
     *
     * @Version1.0 Feb 13, 2009 3:50:11 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param year
     * @return
     */
    public static Calendar getAddYear(String date, int year) {
        Calendar c;
        if (date.length() > 10)
            c = convertCalendarDateTime(date);
        else
            c = convertCalendarDate(date);
        c.add(Calendar.YEAR, year);
        return c;
    }

    /**
     *
     * Description: 增加年
     *
     * @Version1.0 Feb 13, 2009 3:49:21 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param c
     * @param month
     * @return
     */
    public static Calendar getAddMonth(Calendar c, int month) {
        c.add(Calendar.MONTH, month);
        return c;
    }

    /**
     *
     * Description: 增加年
     *
     * @Version1.0 Feb 13, 2009 3:50:11 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param date
     * @param month
     * @return
     */
    public static Calendar getAddMonth(String date, int month) {
        Calendar c;
        if (date.length() > 10)
            c = convertCalendarDateTime(date);
        else
            c = convertCalendarDate(date);
        c.add(Calendar.MONTH, month);
        return c;
    }

    /**
     *
     * Description:计算两个时间差的天数
     *
     * @Version1.0 Jan 15, 2009 4:47:54 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static int getInterval(String begin, String end) {
        Calendar first = convertCalendarDate(begin);
        Calendar last = convertCalendarDate(end);
        return getInterval(first, last);
    }

    /**
     *
     * Description:计算两个时间差的天数
     *
     * @Version1.0 Jan 15, 2009 4:54:27 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static int getInterval(Calendar begin, Calendar end) {
        long first = begin.getTimeInMillis();
        long last = end.getTimeInMillis();
        long day = 1000 * 60 * 60 * 24;
        boolean bool = false;
        long days = 0;
        if (first > last) {
            days = (first - last) / day;
            bool = true;
        } else {
            days = (last - first) / day;
        }
        if (bool)
            return (int) days * -1;
        else
            return (int) days;
    }

    /**
     *
     * Description: 获得时间差 以分手表示
     *
     * @Version 1.0 Jan 29, 2012 6:50:55 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static int getIntervalAsMinute(Calendar begin, Calendar end) {
        long first = begin.getTimeInMillis();
        long last = end.getTimeInMillis();
        long day = 1000 * 60;
        boolean bool = false;
        long days = 0;
        if (first > last) {
            days = (first - last) / day;
            bool = true;
        } else {
            days = (last - first) / day;
        }
        if (bool)
            return (int) days * -1;
        else
            return (int) days;
    }

    /**
     *
     * Description:计算两个时间差的天数
     *
     * @Version1.0 Jan 15, 2009 4:54:27 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static String getIntervalAsString(Calendar begin, Calendar end) {
        String str = "";
        long first = begin.getTimeInMillis();
        long last = end.getTimeInMillis();
        if (first >= last)
            return "0";
        long temp = 0;
        long total = last - first;
        long day = 1000 * 60 * 60 * 24;
        long days = total / day;
        str += days + "天";

        total = total - day * days;
        temp = total % day;
        day = 1000 * 60 * 60;
        days = temp / day;
        str += days + "小时";

        total = total - day * days;
        temp = total % day;
        day = 1000 * 60;
        days = temp / day;
        str += days + "分";

        total = total - day * days;
        temp = total % day;
        day = 1000;
        days = temp / day;
        str += days + "秒";

        return str;
    }

    /**
     *
     * Description:
     *
     * @Version1.0 Sep 9, 2009 2:18:58 PM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static int getExactitudeInterval(Calendar begin, Calendar end) {
        long first = begin.getTimeInMillis();
        long last = end.getTimeInMillis();
        long day = 1000 * 60 * 60 * 24;
        long days = 0;
        days = (last - first) / day;
        return (int) days;
    }

    /**
     *
     * Description:根据输入的时间差 自动返回以年或月或日或时的处理函数
     *
     * @Version1.0 May 31, 2009 10:49:00 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param begin
     * @param end
     * @return
     */
    public static Calendar[] getCalendars(String begin, String end) {
        Calendar[] calendars = null;
        Calendar[] cs = null;
        Calendar first = convertBeginCalendar(begin);
        Calendar last = convertLastCalendar(end);
        Calendar temp = null;
        int length = 0;
        int fyear = first.get(Calendar.YEAR);
        int lyear = last.get(Calendar.YEAR);

        // 年化处理
        if (fyear != lyear) {
            length = lyear - fyear + 2;
            calendars = new Calendar[length];
            calendars[0] = first;
            for (int i = 1; i < length - 1; i++) {
                temp = getFirstDayInYear(begin, i);
                temp = setPreDay(temp, true);
                if (temp.compareTo(last) < 0)
                    calendars[i] = temp;
            }
            calendars[length - 1] = last;
        } else {
            // 月化处理
            fyear = first.get(Calendar.MONTH);
            lyear = last.get(Calendar.MONTH);
            if (fyear != lyear) {
                length = lyear - fyear + 2;
                calendars = new Calendar[length];
                calendars[0] = first;
                for (int i = 1; i < length - 1; i++) {
                    temp = getFirstDayInMonth(begin, i);
                    temp = setPreDay(temp, true);
                    if (temp.compareTo(last) < 0)
                        calendars[i] = temp;
                }
                calendars[length - 1] = last;
            } else {
                // 周化处理
                fyear = first.get(Calendar.WEEK_OF_YEAR);
                lyear = last.get(Calendar.WEEK_OF_YEAR);
                if (lyear < fyear)
                    lyear += 52;
                int b = first.get(Calendar.DAY_OF_YEAR);
                int e = last.get(Calendar.DAY_OF_YEAR);
                if (fyear != lyear && e - b > 6) {
                    length = lyear - fyear + 2;
                    cs = new Calendar[length];
                    cs[0] = first;
                    for (int i = 1; i < length - 1; i++) {
                        temp = getFirstDayInWeek(begin, i);
                        temp = setPreDay(temp, true);
                        if (temp.compareTo(last) >= 0) {
                            cs[i] = last;
                        } else
                            cs[i] = temp;
                    }
                    if (cs[length - 2].compareTo(last) >= 0) {
                        calendars = new Calendar[length - 1];
                        int j = 0;
                        for (int i = 0; i < cs.length; i++) {
                            if (cs[i] != null) {
                                calendars[j] = cs[i];
                                j++;
                            }
                        }
                    } else {
                        cs[length - 1] = last;
                        calendars = cs;
                    }
                } else {
                    // 日化处理
                    fyear = b;
                    lyear = e;
                    length = lyear - fyear + 2;
                    // System.out.println(length + ".........");
                    calendars = new Calendar[length];
                    calendars[0] = first;
                    for (int i = 1; i < length - 1; i++) {
                        temp = getFirstTimeInDay(begin, i);
                        temp = setPreDay(temp, true);
                        if (temp.compareTo(last) < 0)
                            calendars[i] = temp;
                    }
                    calendars[length - 1] = last;
                }
            }
        }
        return calendars;
    }

    /**
     *
     * Description:给个年,给个月。我告诉你在该年中这个月有几天哦！
     *
     * @Version1.0 Oct 29, 2008 11:56:05 AM by 林金良（linjinliang@d-heaven.com）创建
     * @param year
     * @param month
     * @return
     */
    public static int daysInMonth(int year, int month) {
        int[] monthDay = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
                30, 31 };
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            monthDay[1] = 29;
        }
        int monthDayNum = monthDay[month - 1];
        return monthDayNum;
    }

    /**
     *
     * Description:判断是否是周末
     *
     * @Version1.0 Aug 29, 2008 10:03:43 AM 林金良（v-linjinliang@d-heaven.com）创建
     * @param c
     * @return 0标识是周末,1正常工作日
     */
    public static int isWeekend(Calendar c) {
        int _wd = c.get(Calendar.DAY_OF_WEEK);
        if (_wd == Calendar.SUNDAY || _wd == Calendar.SATURDAY) {
            return 0;
        }
        return 1;
    }

    /**
     *
     * Description: 一个月的第一天是星期几呢？ 1234567,一次标识周一到周日
     *
     * @Version1.0 Aug 29, 2008 10:38:44 AM 林金良（v-linjinliang@d-heaven.com）创建
     * @param c
     * @return
     */
    public static int firstMonthDayofWeek(Calendar c) {
        String[] week = { "", "7", "1", "2", "3", "4", "5", "6" };
        int _timeObj = Integer.parseInt(week[c.get(Calendar.DAY_OF_WEEK)]);
        return _timeObj;
    }

    /**
     *
     * Description: 根据年月生成路径
     *
     * @Version 1.0 Aug 18, 2010 5:05:22 PM by 林金良（linjinliang@d-heaven.com）创建
     * @return
     */
    public static String getPathByYearMonth(String path) {
        String str = convertStrThree(Calendar.getInstance());
        if (path == null || path.equals(""))
            return str.substring(0, 6) + "/";
        if (path.endsWith("/"))
            return path + str.substring(0, 6) + "/";
        else
            return path + "/" + str.substring(0, 6) + "/";
    }
}
