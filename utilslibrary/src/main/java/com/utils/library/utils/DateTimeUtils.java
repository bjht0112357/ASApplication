package com.utils.library.utils;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    public static final String DATE_FORMAT_DEF = "yyyy-MM-dd";
    public static final String TIME_FORMAT_DEF = "HH:mm";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME_FORMAT_DEF = DATE_FORMAT_DEF + " "
            + TIME_FORMAT_DEF;

    public DateTimeUtils() {
        // TODO Auto-generated constructor stub
    }

    public static String getCurrDateStr() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DEF,
                Locale.CHINA);
        return format.format(new Date());
    }

    public static String getCurrTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_DEF,
                Locale.CHINA);
        return format.format(new Date());
    }

    public static String getCurrDateTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT_DEF,
                Locale.CHINA);
        return format.format(new Date());
    }

    public static String getCurrDateTime() {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT,
                Locale.CHINA);
        return format.format(new Date());
    }

    public static String getDateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DEF,
                Locale.CHINA);
        return format.format(date);
    }

    public static String getCurrDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT,
                Locale.CHINA);
        return format.format(date);
    }

    public static String getTimeStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_DEF,
                Locale.CHINA);
        return format.format(date);
    }

    public static String getDateTimeStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT_DEF,
                Locale.CHINA);
        return format.format(date);
    }

    public static String getDateTimeStr(Date date, String formater) {
        SimpleDateFormat format = new SimpleDateFormat(formater, Locale.CHINA);
        return format.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm default
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateTimeStr(String dateStr, String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT_DEF,
                Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr + " " + timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * yyyy-MM-dd HH:mm default
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateTime(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT,
                Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * yyyy-MM-dd default
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateStr(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DEF,
                Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * yyyy-MM-dd default
     *
     * @param dateStr
     * @return
     */
    public static Date parseDateStr(String dateStr, String formater) {
        SimpleDateFormat format = new SimpleDateFormat(formater, Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String parseDateByLong(long dateLong) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(dateLong * 1000);
        return sdf.format(dt);
    }

    /**
     * HH:mm default
     *
     * @param timeStr
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static Date parseTimeStr(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_DEF);
        Date date = null;
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * TODO:XXXX
     *
     * @return
     */
    public static String getCurrWeekDayStr() {
        final String week[] = {"天", "一", "二", "三", "四", "五", "六"};
        int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return "星期" + week[i - 1];
    }


    public static void showTimeSettingDialog(final Context context, Date date,
                                             final OnTimeSetListener listener) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                listener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }


    public static String getYearMonthStr(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
        return format.format(date);
    }

    public static String getStrBy(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
        return format.format(date);
    }

    public static int getWeekDayOf(int day) {
        switch (day) {
            case 0:
                return Calendar.MONDAY;
            case 1:
                return Calendar.TUESDAY;
            case 2:
                return Calendar.WEDNESDAY;
            case 3:
                return Calendar.THURSDAY;
            case 4:
                return Calendar.FRIDAY;
            case 5:
                return Calendar.SATURDAY;
            case 6:
                return Calendar.SUNDAY;

        }

        return Calendar.MONDAY;
    }

    public static String getRangeBeforeCur(Date date) {
        Calendar cal = Calendar.getInstance();
        long rangeInSec = (cal.getTimeInMillis() - date.getTime()) / 1000;
        long temp = rangeInSec / 60;
        if (temp <= 0) {
            return "刚刚";
        }
        rangeInSec = temp;
        temp = rangeInSec / 60;

        if (temp <= 0) {
            return rangeInSec + "分钟前";
        }
        rangeInSec = temp;
        temp = rangeInSec / 24;

        if (temp <= 0) {
            return rangeInSec + "小时前";
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy年MM月dd日 HH:mm",
                Locale.CHINA);
        return formater.format(date);

    }

    public static String getRangeBeforeCurSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        long rangeInSec = (cal.getTimeInMillis() - date.getTime()) / 1000;
        long min = ((rangeInSec / 60));
        long s = (rangeInSec - min * 60);
        return (int) min + ":" + (int) s;
    }

    public static String convertGMTToLoacale(String gmt) {
        String cc = gmt.substring(0, 19) + gmt.substring(33, 38);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy",
                new Locale("English"));
        try {
            Date date = sdf.parse(cc);
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM");
            String result = dateformat.format(date);
            return result;
        } catch (ParseException e) {
        }
        return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean compareDate(String expiredTime, String currDateTime) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(expiredTime);
            Date dt2 = df.parse(currDateTime);
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }


    @SuppressLint("SimpleDateFormat")
    public static long getLongDate(String datetime) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt = df.parse(datetime);
            return dt.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressLint("SimpleDateFormat")
    public static long getLongDate(String datetime, String format) {

        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date dt = df.parse(datetime);
            return dt.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
