package com.cnbleu.crashreport;

import android.util.Log;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 调试辅助工具
 * <br>
 */
public class CrashDebug {

    public static final String TAG = "crashreportor";

    /** 详细日志等级 */
    public static final boolean VERBOSE = Log.isLoggable(TAG, Log.VERBOSE);
    /** 调试日志等级 */
    public static final boolean DEBUG = Log.isLoggable(TAG, Log.DEBUG);

}
