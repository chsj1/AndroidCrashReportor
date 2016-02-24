package com.cnbleu.crashreport.core;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 崩溃记录能力。
 * <br>
 */
public interface IRecordable<T> {

    /**
     * 记录数据。
     *
     * @param data 期望的数据
     */
    void record(T data);
}
