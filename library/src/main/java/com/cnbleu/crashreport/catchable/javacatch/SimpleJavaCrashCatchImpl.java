package com.cnbleu.crashreport.catchable.javacatch;

import android.content.Context;
import android.util.Log;

import com.cnbleu.crashreport.CrashDebug;
import com.cnbleu.crashreport.core.IRecordable;
import com.cnbleu.crashreport.notifiable.INotifiable;
import com.cnbleu.crashreport.recordable.RecordBean;
import com.cnbleu.crashreport.utils.RecordHelper;

import static com.cnbleu.crashreport.CrashDebug.TAG;
import static com.cnbleu.crashreport.CrashDebug.VERBOSE;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Java异常捕获控制类的默认实现。
 * <br>
 */
public class SimpleJavaCrashCatchImpl extends AbsJavaCrashCatchable<RecordBean> {

    private IRecordable<RecordBean> mRecordable;
    private INotifiable<RecordBean> mNotifiable;

    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    private boolean enableDefaultCrashHandler;
    private Context mContext;

    public SimpleJavaCrashCatchImpl(SimpleJavaCrashCatchBuilder builder) {
        super(builder);
        if (null == builder) {
            throw new IllegalArgumentException("SimpleJavaCrashCatchBuilder must not be null.");
        }

        this.mContext = builder.getContext();
        this.enableDefaultCrashHandler = builder.enableDefaultCrashHandler();

        this.mRecordable = builder.getRecordable();
        if (null == mRecordable) {
            this.mRecordable = builder.getDefaultRecordable();
        }
        this.mNotifiable = builder.getNotifiable();
    }

    @Override
    public void init(Context context) {
        if (VERBOSE) {
            Log.v(TAG, "JavaCrashCatchImpl initing...");
        }

        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 捕获到Crash。
     *
     * @param params 期望的参数列表。
     */
    @Override
    public void catchCrash(Object... params) {
        if (VERBOSE) {
            Log.v(TAG, "JavaCrashCatchImpl catched crash.");
        }

        final Thread thread = (Thread) params[0];
        final Throwable ex = (Throwable) params[1];

        final RecordBean bean = new RecordBean();
        // 异常产生时间
        bean.time = System.currentTimeMillis();

        // 产生异常的设备信息
        bean.deviceInfo = RecordHelper.getDeviceInfo(mContext);

        // 封装异常信息
        if (null == ex) {
            bean.stackTrace = "unknown error";
        } else {
            bean.stackTrace = Utils.stacktraceToString(ex);
        }

        if (VERBOSE) {
            Log.v(CrashDebug.TAG, "crash info: " + bean);
        }

        // 记录异常日志
        if (null != mRecordable) {
            mRecordable.record(bean);
        }

        // TODO: 16/2/24 日志上传功能

        // 触发系统默认的异常处理机制
        if (enableDefaultCrashHandler && null != mDefaultUncaughtExceptionHandler) {
            mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        } else {
            if (null != mNotifiable) {
                mNotifiable.notify(bean);
            }
        }
    }

    @Override
    public void setRecordable(IRecordable<RecordBean> recordable) {
        this.mRecordable = recordable;
    }

    /**
     * 设置异常通知接口
     *
     * @param notifiable {@Link INotifiable}
     */
    @Override
    public void setNotifiable(INotifiable<RecordBean> notifiable) {
        this.mNotifiable = notifiable;
    }
}
