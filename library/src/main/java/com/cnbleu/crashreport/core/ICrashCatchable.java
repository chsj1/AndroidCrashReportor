package com.cnbleu.crashreport.core;

import android.content.Context;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 崩溃捕获能力接口。
 * <br>
 */
public interface ICrashCatchable {

    void init(Context context);

    /**
     * 捕获到Crash。
     *
     * @param params 期望的参数列表。
     */
    void catchCrash(Object... params);

    void setRecordable(IRecordable recordable);
}
