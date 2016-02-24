package com.cnbleu.crashreport.recordable;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常信息封装类
 * <br>
 */
public class RecordBean {
    /** 异常发生时间 */
    public long time;
    /** 设备信息 */
    public String deviceInfo;
    /** 异常堆栈信息 */
    public String stackTrace;

    @Override
    public String toString() {
        return "RecordBean{" +
               "deviceInfo='" + deviceInfo + '\'' +
               ", time=" + time +
               ", stackTrace='" + stackTrace + '\'' +
               '}';
    }
}
