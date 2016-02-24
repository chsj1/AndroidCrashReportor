package com.cnbleu.crashreport.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class TimeUtils {

    private TimeUtils() {
        // hide
    }

    public static String getReadableTime(long time) {
        return getReadableTime("yyyyMMddHHmmssSSS", time);
    }

    public static String getReadableTime(String formatter, long time) {
        return new SimpleDateFormat(formatter).format(new Date(time));
    }

}
