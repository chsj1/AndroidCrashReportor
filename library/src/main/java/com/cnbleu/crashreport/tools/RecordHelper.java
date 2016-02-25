package com.cnbleu.crashreport.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 日志记录辅助工具
 * <br>
 */
public class RecordHelper {
    private static final String LINE = System.getProperty("line.separator");

    private RecordHelper() {
        // hide
    }

    public static String getDeviceInfo(Context context) {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
                                                               PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            pInfo = null;
        }

        StringBuilder builder = new StringBuilder();
        // app版本信息
        if (null != pInfo) {
            builder.append("app_version_name: ");
            builder.append(pInfo.versionName);
            builder.append(",");
            builder.append(LINE);

            builder.append("app_version_code: ");
            builder.append(pInfo.versionCode);
            builder.append(",");
            builder.append(LINE);
        }

        // android版本号
        builder.append("os_version: ");
        builder.append(Build.VERSION.RELEASE);
        builder.append("_");
        builder.append(Build.VERSION.SDK_INT);
        builder.append(",");
        builder.append(LINE);

        // 制造商
        builder.append("vendor: ");
        builder.append(Build.MANUFACTURER);
        builder.append(",");
        builder.append(LINE);

        // 手机型号
        builder.append("model: ");
        builder.append(Build.MODEL);
        builder.append(",");
        builder.append(LINE);

        // cpu架构
        builder.append("cpu_abi: ");
        builder.append(Build.CPU_ABI);
        builder.append(",");
        builder.append(LINE);

        return builder.toString();
    }

}
