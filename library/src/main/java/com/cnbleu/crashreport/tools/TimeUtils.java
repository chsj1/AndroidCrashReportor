package com.cnbleu.crashreport.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 日期相关的工具
 * <br>
 */
public class TimeUtils {

    private TimeUtils() {
        // hide
    }

    /**
     * 获取易于阅读的时间
     *
     * @param time 时间的毫秒数
     *
     * @return
     */
    public static String getReadableTime(long time) {
        return getReadableTime("yyyyMMddHHmmssSSS", time);
    }

    /**
     * 获取易于阅读的时间
     *
     * @param formatter 日期格式化
     * @param time      时间的毫秒数
     *
     * @return
     */
    public static String getReadableTime(String formatter, long time) {
        return new SimpleDateFormat(formatter).format(new Date(time));
    }

}
