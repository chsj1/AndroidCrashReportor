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
public abstract class AbsCrashCatchBuilder<T extends ICrashCatchable, D extends RecordBean> {
    private static IRecordable sDefaultRecordable;

    /** 执行系统默认的异常捕获， 默认不允许 */
    private boolean enableDefaultCrashHandler = false;

    private IRecordable<D> mRecordable;
    private INotifiable<D> mNotifiable;
    private ISendable mSendable;

    public AbsCrashCatchBuilder defaultRecordable(IRecordable<D> recordable) {
        AbsCrashCatchBuilder.sDefaultRecordable = recordable;
        return this;
    }

    public IRecordable<D> getDefaultRecordable() {
        return AbsCrashCatchBuilder.sDefaultRecordable;
    }

    public AbsCrashCatchBuilder setRecordable(IRecordable<D> recordable) {
        this.mRecordable = recordable;
        return this;
    }

    public AbsCrashCatchBuilder setNotifiable(INotifiable<D> notifiable) {
        this.mNotifiable = notifiable;
        return this;
    }

    public AbsCrashCatchBuilder setSendable(ISendable sendable){
        this.mSendable = sendable;
        return this;
    }

    public INotifiable<D> getNotifiable(){
        return this.mNotifiable;
    }

    public IRecordable<D> getRecordable() {
        return this.mRecordable;
    }

    public ISendable getSendable(){
        return this.mSendable;
    }

    /**
     * 是否允许执行系统默认的异常捕获
     *
     * @param enable true，允许；false，不允许
     *
     * @return
     */
    public AbsCrashCatchBuilder enableDefaultCrashHandler(boolean enable) {
        this.enableDefaultCrashHandler = enable;
        return this;
    }

    public boolean enableDefaultCrashHandler(){
        return this.enableDefaultCrashHandler;
    }

    public abstract T build();
}