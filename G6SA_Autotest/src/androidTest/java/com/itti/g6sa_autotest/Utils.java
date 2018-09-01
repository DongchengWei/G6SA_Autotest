package com.itti.g6sa_autotest;

import java.text.SimpleDateFormat;

/**
 * Created by uidq0460 on 2018/5/30.
 */

public class Utils {
    /**
     * 把毫秒数转成时分秒格式
     * @param time 毫秒数
     * @return 时分秒的字符串
     * @Date 2017-06-03
     */
    /*把毫秒转成时间hh:mi:ss.xxx*/
    public static String millisToTime(long time) {
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        long millisecond = 0;
        if (time <= 0)
            return "00:00:00.000";
        else {
            second = time /1000;
            minute = second / 60;
            millisecond = time % 1000;
            if (second < 60) {

                timeStr = "00:00:" + unitFormat(second) + "." + unitFormat2(millisecond);
            }else if (minute < 60) {
                second = second % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second) + "." + unitFormat2(millisecond);
            }else{//数字>=3600 000的时候
                hour = minute /60;
                minute = minute % 60;
                second = second - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second) + "." + unitFormat2(millisecond);
            }
        }
        return timeStr;
    }

    public static String unitFormat(long i) {//时分秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String unitFormat2(long i) {//毫秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "00" + Long.toString(i);
        else if (i >=10 && i < 100) {
            retStr = "0" + Long.toString(i);
        }
        else
            retStr = "" + i;
        return retStr;
    }


}
