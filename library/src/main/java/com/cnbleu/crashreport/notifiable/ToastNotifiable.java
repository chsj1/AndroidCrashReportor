package com.cnbleu.crashreport.notifiable;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import com.cnbleu.crashreport.core.INotifiable;
import com.cnbleu.crashreport.recordable.RecordBean;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ToastNotifiable implements INotifiable<RecordBean> {

    private Context mContext;

    public ToastNotifiable(Context context) {
        this.mContext = context;
    }

    @Override
    public void notify(RecordBean data) {
        final Looper looper = Looper.myLooper();
        if (null == looper || Looper.getMainLooper() != looper) {
            Looper.prepare();
            innerNotify(data);
            Looper.loop();
        } else {
            // FIXME: 16/2/24 主线程异常捕获后通知能力处理
            android.os.Process.killProcess(Process.myPid());
        }
    }

    private void innerNotify(RecordBean data) {
        Toast.makeText(mContext, data.toString(), Toast.LENGTH_LONG).show();
    }
}
