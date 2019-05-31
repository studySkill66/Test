package com.hksy.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    static SimpleDateFormat dateTimeFormatNotMiles = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat dateTimeNotSplitFormat = new SimpleDateFormat("yyyyMMddHHmmss:SSS");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    static SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
    static SimpleDateFormat MMddFormat = new SimpleDateFormat("MM-dd");
    static SimpleDateFormat YYMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat YYMMddHHmmssFormatSlash = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static SimpleDateFormat YY_MM_dd_HHmmssFormatSlash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNowDateTime() {
        Date today = new Date();
        return dateTimeFormat.format(today);
    }

    public static String getNowTime() {
        Date today = new Date();
        return timeFormat.format(today);
    }

    public static String getNowDateTime4NotSplit() {
        Date today = new Date();
        return dateTimeNotSplitFormat.format(today);
    }

    public static String getNowDate() {
        Date today = new Date();
        return dateFormat.format(today);
    }

    public static long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String fromUnixTime(int ctime) {
        Date today = new Date(ctime * 1000L);
        return dateTimeFormat.format(today);
    }

    public static String fromUnixTime(long ctime) {
        Date today = new Date(ctime * 1000L);
        return dateTimeFormat.format(today);
    }

    public static String fromUnixTime2Time(long ctime) {
        Date today = new Date(ctime * 1000L);
        return timeFormat.format(today);
    }

    public static String fromUnixTime2Day(long ctime) {
        Date today = new Date(ctime * 1000L);
        return dayFormat.format(today);
    }

    public static String fromUnixTime2YearMonth(long ctime) {
        Date today = new Date(ctime * 1000L);
        return yearMonthFormat.format(today);
    }

    public static String fromUnixTime3Time(long ctime) {
        Date today = new Date(ctime * 1000L);
        return dateFormat.format(today);
    }

    public static String fromUnixTimeNotMiles(long ctime) {
        Date today = new Date(ctime * 1000L);
        return dateTimeFormatNotMiles.format(today);
    }

    public static String fromUnixTime2MMdd(long ctime) {
        Date today = new Date(ctime * 1000L);
        return MMddFormat.format(today);
    }

    public static String fromUnixTime2YYMMdd(long ctime) {
        Date today = new Date(ctime * 1000L);
        return YYMMddFormat.format(today);
    }

    public static String fromUnixTime2YYMMddHHmmssFormatSlash(long ctime) {
        Date today = new Date(ctime * 1000L);
        return YYMMddHHmmssFormatSlash.format(today);
    }

    public static long getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(11, 0);
        todayStart.set(12, 0);
        todayStart.set(13, 0);
        todayStart.set(14, 0);
        return todayStart.getTime().getTime() / 1000L;
    }

    public static boolean between(String currentTime, String startTime, String endTime) {
        if (startTime.compareTo(endTime) > 0) {
            endTime = String.format("%d%s", new Object[]{
         Integer.valueOf(Integer.valueOf(endTime.substring(0, 2)).intValue() + 24), endTime.substring(2)});
        }

        return (currentTime.compareTo(startTime) >= 0) && (currentTime.compareTo(endTime) <= 0);
    }

    public static int getDayDiff(long starttime, long endtime) {
        starttime = (starttime / 86400000L * 86400000L - TimeZone.getDefault().getRawOffset()) / 1000L;
        endtime = (endtime / 86400000L * 86400000L - TimeZone.getDefault().getRawOffset()) / 1000L;
        Double res = Double.valueOf((endtime - starttime) / 86400L);
        return res.intValue();
    }

    public static boolean isWeekend() {
        String bDate = getNowDate();
        try {
            Date bdate = dateFormat.parse(bDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(bdate);
            return (cal.get(7) == 7) || (cal.get(7) == 1);
        } catch (java.text.ParseException localParseException) {
        }
        return false;
    }

    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(7) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(7) - 1;
        }
        return dayForWeek;
    }


    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd)
            throws Exception {
/* 163 */
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
/* 164 */
        String start = strDateBegin;
/* 165 */
        String end = strDateEnd;
/* 166 */
        String format = sd.format(new Date());
/* 167 */
        Date fo = sd.parse(format);
/* 168 */
        Date str = sd.parse(start);
/* 169 */
        Date en = sd.parse(end);
/* 170 */
        if ((fo.getTime() >= str.getTime()) && (fo.getTime() <= en.getTime())) {
/* 171 */
            return true;
        }
/* 173 */
        return false;
    }

    /**
     * 将时间格式转换成yyyyMM的数字类型格式
     *
     * @param dateTime
     * @return
     */
    public static int formatDateTimeYearAndMonth(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return Integer.valueOf(sdf.format(dateTime));
    }

    public static int formatNow() {
        Date dateTime = new Date();
        return formatDateTimeYearAndMonth(dateTime);
    }

    public static String formatYY_MM_dd_HHmmss(Date date){
        return YY_MM_dd_HHmmssFormatSlash.format(date);
    }

    public static void main(String[] args) throws Exception {
/* 178 */
        System.out.println(formatNow());
    }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/util/TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */