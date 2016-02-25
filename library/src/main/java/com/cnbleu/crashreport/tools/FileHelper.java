package com.cnbleu.crashreport.tools;

import android.content.Context;

import java.io.File;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 文件相关的工具类。
 * <br>
 */
public class FileHelper {
    /** 错误日志文件名前缀 */
    private static final String CRASH_CACHED_NAME_PREFIX = "crash_cache";
    /** 错误日志文件名后缀 */
    private static final String CRASH_CACHED_NAME_SUFFIX = ".crash";

    private FileHelper() {
        // hide
    }

    /**
     * 获取日志文件存放目录。
     * <p/>
     * 此处使用{@link Context#getFilesDir()}目录为日志存放目录，原因：
     * 1. 该目录位于app data目录下，相对比较安全，且无需判断存储卡状态
     * 2. 该目录不需要声明文件写入和读取权限
     *
     * @param context {@link Context}
     *
     * @return {@link File} 日志记录文件目录
     */
    public static File getRecordDir(Context context) {
        return context.getFilesDir();
    }

    /**
     * 获取日志文件名称
     *
     * @param context {@link Context}
     *
     * @return {@link String} 文件名
     */
    public static String getRecordFileName(Context context, String times) {
        final String packageName = context.getPackageName();

        return String.format("%s_%s_%s%s",
                             CRASH_CACHED_NAME_PREFIX,
                             times,
                             packageName,
                             CRASH_CACHED_NAME_SUFFIX);
    }
}
