package com.cnbleu.crashreport.core;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 使用构建器构建异常控制器。
 * <br>
 */
public abstract class AbsCrashCatchable<T> implements ICrashCatchable<T> {

    private CrashCatchBuilder mCrashCatchBuilder;

    public AbsCrashCatchable(CrashCatchBuilder builder) {
        this.mCrashCatchBuilder = builder;
    }

    protected CrashCatchBuilder getCrashCatchBuilder() {
        return this.mCrashCatchBuilder;
    }



}
