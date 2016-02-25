package com.cnbleu.crashreport.core;

import android.content.Context;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 崩溃捕获控制器接口。
 * <br>
 */
public interface ICrashCatchable<T> {

    /**
     * 初始化
     *
     * @param context {@link Context}
     */
    void init(Context context);

    /**
     * 设置错误日志记录接口
     *
     * @param recordable {@link IRecordable}
     */
    void setRecordable(IRecordable<T> recordable);

    /**
     * 设置异常通知接口
     *
     * @param notifiable {@Link INotifiable}
     */
    void setNotifiable(INotifiable<T> notifiable);

    /**
     * 设置日志信息发送接口
     *
     * @param sendable {@link ISendable}
     */
    void setSendable(ISendable sendable);

    /**
     * 捕获到Crash。
     *
     * @param params 期望的参数列表。
     */
    void catchCrash(Object... params);
}
