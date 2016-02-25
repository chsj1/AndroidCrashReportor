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

    /**
     * 配置默认异常记录模块
     *
     * @param recordable {@link IRecordable}
     *
     * @return
     */
    public AbsCrashCatchBuilder defaultRecordable(IRecordable<D> recordable) {
        AbsCrashCatchBuilder.sDefaultRecordable = recordable;
        return this;
    }

    /**
     * 返回默认的异常记录模块
     *
     * @return {@link IRecordable}
     */
    public IRecordable<D> getDefaultRecordable() {
        return AbsCrashCatchBuilder.sDefaultRecordable;
    }

    /**
     * 配置异常记录模块
     *
     * @param recordable {@link IRecordable}
     *
     * @return
     */
    public AbsCrashCatchBuilder setRecordable(IRecordable<D> recordable) {
        this.mRecordable = recordable;
        return this;
    }

    /**
     * 配置异常通知模块
     *
     * @param notifiable {@link INotifiable}
     *
     * @return
     */
    public AbsCrashCatchBuilder setNotifiable(INotifiable<D> notifiable) {
        this.mNotifiable = notifiable;
        return this;
    }

    /**
     * 配置异常发送模块
     *
     * @param sendable {@link ISendable}
     *
     * @return
     */
    public AbsCrashCatchBuilder setSendable(ISendable sendable){
        this.mSendable = sendable;
        return this;
    }

    /**
     * 获取异常通知模块
     *
     * @return {@link INotifiable}
     */
    public INotifiable<D> getNotifiable(){
        return this.mNotifiable;
    }

    /**
     * 获取异常记录模块
     *
     * @return {@link IRecordable}
     */
    public IRecordable<D> getRecordable() {
        return this.mRecordable;
    }

    /**
     * 获取异常发送模块
     *
     * @return {@link ISendable}
     */
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

    /**
     * 是否允许默认的异常处理器
     *
     * @return
     */
    public boolean enableDefaultCrashHandler(){
        return this.enableDefaultCrashHandler;
    }

    /**
     * 构建异常控制器并返回
     *
     * @return {@link T}
     */
    public abstract T build();
}
