package com.cnbleu.crashreport.recordable;

import android.content.Context;
import android.util.Log;

import com.cnbleu.crashreport.core.IRecordable;
import com.cnbleu.crashreport.utils.FileHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.cnbleu.crashreport.CrashDebug.TAG;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class FileRecordableImpl implements IRecordable<RecordBean> {

    private Context mContext;

    public FileRecordableImpl(Context context) {
        this.mContext = context;
    }

    /**
     * 记录数据。
     *
     * @param data 期望的数据
     */
    @Override
    public void record(RecordBean data) {
        File file = FileHelper.getRecordDir(mContext);

        if (!file.exists()) {
            boolean exists = file.mkdirs();
            // 目录创建失败时需要再次校验目录是否存在
            if (!exists && !file.exists()) {
                Log.w(TAG, "Error occured when create record directory");
                return;
            }
        }

        final String fileName = FileHelper.getRecordFileName(mContext, data.time);
        file = new File(file, fileName);

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.println(data.time);
            writer.println(data.deviceInfo);
            writer.println(data.stackTrace);
            writer.close();
        } catch (IOException e) {
            Log.w(TAG, "Error occured when write record to file. file path: " + file.getAbsolutePath());
        }
    }
}
