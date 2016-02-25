package com.cnbleu.crashreport.recordable;

import android.content.Context;
import android.util.Log;

import com.cnbleu.crashreport.core.IRecordable;
import com.cnbleu.crashreport.tools.FileHelper;
import com.cnbleu.crashreport.tools.TimeUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.cnbleu.crashreport.CrashDebug.TAG;
import static com.cnbleu.crashreport.CrashDebug.VERBOSE;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 基于文件存储的异常记录能力实现。异常信息文件存储在/data/data/package-name/files目录下
 * <br>
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
        // 使用单文件记录单个错误日志
        // 1. 可以避免多线程及多进程环境下同时对单个文件进行同时操作的同步性能开销
        // 2. 降低单个文件在并发情况下处理的复杂度

        File file = FileHelper.getRecordDir(mContext);
        if (VERBOSE) {
            Log.v(TAG, "record crash data to file. file directory: " + file.getAbsolutePath());
        }

        if (!file.exists()) {
            boolean exists = file.mkdirs();
            // 目录创建失败时需要再次校验目录是否存在
            if (!exists && !file.exists()) {
                Log.w(TAG, "Error occured when create record directory");
                return;
            }
        }

        final String time = TimeUtils.getReadableTime(data.time);
        final String fileName = FileHelper.getRecordFileName(mContext, time);
        file = new File(file, fileName);

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.println(time);
            writer.println(data.deviceInfo);
            writer.println(data.stackTrace);
            writer.close();

            if (VERBOSE) {
                Log.v(TAG, "crash data saved to: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            Log.w(TAG, "Error occured when write record to file. file path: " + file.getAbsolutePath());
        }
    }
}
