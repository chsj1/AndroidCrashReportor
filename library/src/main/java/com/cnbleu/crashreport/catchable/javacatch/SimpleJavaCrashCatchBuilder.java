package com.cnbleu.crashreport.catchable.javacatch;

import android.content.Context;

import com.cnbleu.crashreport.core.CrashCatchBuilder;
import com.cnbleu.crashreport.recordable.RecordBean;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * <br>
 */
public class SimpleJavaCrashCatchBuilder extends CrashCatchBuilder<SimpleJavaCrashCatchImpl, RecordBean> {

    private Context mContext;

    public SimpleJavaCrashCatchBuilder(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override
    public SimpleJavaCrashCatchImpl build() {
        return new SimpleJavaCrashCatchImpl(this);
    }
}