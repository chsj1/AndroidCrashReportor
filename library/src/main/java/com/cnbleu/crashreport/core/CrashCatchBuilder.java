package com.cnbleu.crashreport.core;

import com.cnbleu.crashreport.recordable.RecordBean;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 基本的{@link ICrashCatchable}构造器。
 * <br>
 */
public abstract class CrashCatchBuilder<T extends ICrashCatchable, D extends RecordBean> {
    private static IRecordable sDefaultRecordable;

    /** 默认允许执行系统默认的异常捕获， 默认不允许 */
    private boolean enableDefaultCrashHandler = false;

    private IRecordable<D> mRecordable;

    public CrashCatchBuilder defaultRecordable(IRecordable<D> recordable) {
        CrashCatchBuilder.sDefaultRecordable = recordable;
        return this;
    }

    public IRecordable<D> getDefaultRecordable() {
        return CrashCatchBuilder.sDefaultRecordable;
    }

    public CrashCatchBuilder setRecordable(IRecordable<D> recordable) {
        this.mRecordable = recordable;
        return this;
    }

    public IRecordable<D> getRecordable() {
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

    public boolean enableDefaultCrashHandler(){
        return this.enableDefaultCrashHandler;
    }

    public abstract T build();
}
