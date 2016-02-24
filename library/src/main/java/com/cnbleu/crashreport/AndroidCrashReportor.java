package com.cnbleu.crashreport;

import android.content.Context;
import android.util.Log;

import com.cnbleu.crashreport.catchable.javacatch.SimpleJavaCrashCatchBuilder;
import com.cnbleu.crashreport.core.ICrashCatchable;

import java.util.List;

import static com.cnbleu.crashreport.CrashDebug.TAG;
import static com.cnbleu.crashreport.CrashDebug.VERBOSE;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常捕获控制类。
 * <br>
 */
public class AndroidCrashReportor {

    private Context mContext;

    private AndroidCrashReportor() {
        // hide for single instance
    }

    private static class SingletonHolder {
        final static AndroidCrashReportor INSTANCE = new AndroidCrashReportor();
    }

    public static AndroidCrashReportor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化崩溃捕获系统，默认只初始化默认的Java崩溃捕获系统。
     *
     * @param context applicaiton context
     */
    public void init(Context context) {
        context = context.getApplicationContext();

        final CrashCatchConfig.Builder builder = new CrashCatchConfig.Builder();

        SimpleJavaCrashCatchBuilder javaCrashCatchBuilder = new SimpleJavaCrashCatchBuilder(context);
        builder.addCrashCatchable(javaCrashCatchBuilder.build());

        init(context, builder.build());
    }

    /**
     * 初始化崩溃捕获系统。
     *
     * @param context applicaiton context
     * @param config  {@link CrashCatchConfig}
     */
    public void init(Context context, final CrashCatchConfig config) {
        if (VERBOSE) {
            Log.v(TAG, "AndroidCrashReportor initing..");
        }

        this.mContext = context.getApplicationContext();

        final List<ICrashCatchable> crashCatchables = config.getCrashCatchables();
        if (null == crashCatchables || crashCatchables.isEmpty()) {
            throw new RuntimeException("You should specify at least one 'ICrashCatchable'!!");
        }

        ICrashCatchable crashCatchable;
        for (int i = 0; i < crashCatchables.size(); i++) {
            crashCatchable = crashCatchables.get(i);
            crashCatchable.init(mContext);
        }

    }

}
