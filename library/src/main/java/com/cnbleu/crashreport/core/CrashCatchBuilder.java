package com.cnbleu.crashreport.core;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 基本的{@link ICrashCatchable}构造器。
 * <br>
 */
public abstract class CrashCatchBuilder<T extends ICrashCatchable> {
    private static IRecordable sDefaultRecordable;
    // TODO: 16/2/24 默认实现一个IReadcordable

    /** 默认允许执行系统默认的异常捕获， 默认允许 */
    private boolean enableDefaultCrashHandler = true;

    private IRecordable mRecordable;

    public CrashCatchBuilder defaultRecordable(IRecordable recordable) {
        CrashCatchBuilder.sDefaultRecordable = recordable;
        return this;
    }

    public IRecordable getDefaultRecordable() {
        return CrashCatchBuilder.sDefaultRecordable;
    }

    public CrashCatchBuilder setRecordable(IRecordable recordable) {
        this.mRecordable = recordable;
        return this;
    }

    public IRecordable getRecordable() {
        return this.mRecordable;
    }


    /**
     * 是否允许执行系统默认的异常捕获
     *
     * @param enable true，允许；false，不允许
     *
     * @return
     */
    public CrashCatchBuilder enableDefaultCrashHandler(boolean enable) {
        this.enableDefaultCrashHandler = enable;
        return this;
    }

    public abstract T build();
}
