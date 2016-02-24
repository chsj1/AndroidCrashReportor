package com.cnbleu.crashreport.catchable.javacatch;

import android.content.Context;

import com.cnbleu.crashreport.core.AbsCrashCatchable;
import com.cnbleu.crashreport.core.IRecordable;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Java异常捕获控制类的默认实现。
 * <br>
 */
public class SimpleJavaCrashCatchImpl extends AbsCrashCatchable {

    private AbsJavaCrashCatchable mCatchable;
    private IRecordable mRecordable;

    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    public SimpleJavaCrashCatchImpl(SimpleJavaCrashCatchBuilder builder) {
        super(builder);
        if (null == builder) {
            throw new IllegalArgumentException(
                    "SimpleJavaCrashCatchImpl must have an instance of 'SimpleJavaCrashCatchBuilder'");
        }

        this.mRecordable = builder.getRecordable();
        if (null == mRecordable) {
            this.mRecordable = builder.getDefaultRecordable();
        }

        if (null == mRecordable) {
            throw new IllegalArgumentException("'IRecordalbe must not be null.'");
        }
    }

    @Override
    public void init(Context context) {
        // TODO: 16/2/24 实现Java的异常捕获
    }

    /**
     * 捕获到Crash。
     *
     * @param params 期望的参数列表。
     */
    @Override
    public void catchCrash(Object... params) {
    }

    @Override
    public void setRecordable(IRecordable recordable) {

    }
}
