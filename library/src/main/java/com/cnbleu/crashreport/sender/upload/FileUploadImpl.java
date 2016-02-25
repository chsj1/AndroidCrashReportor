package com.cnbleu.crashreport.sender.upload;

import android.content.Context;

import com.cnbleu.crashreport.core.ISendable;
import com.cnbleu.crashreport.recordable.RecordBean;
import com.cnbleu.crashreport.sender.upload.service.FileUploadService;
import com.cnbleu.crashreport.tools.FileHelper;

import java.io.File;
import java.util.Arrays;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class FileUploadImpl implements ISendable {
    private Context mContext;

    public FileUploadImpl(Context context) {
        this.mContext = context;
    }

    /**
     * @param record
     */
    @Override
    public void sendRecord(RecordBean record) {
        File file = FileHelper.getRecordDir(mContext);
        if (!file.exists()) {
            return;
        }

        File[] files = null;
        if (file.isDirectory()) {
            files = file.listFiles();
        }

        if (null != files) {
            FileUploadService.uploadFiles(mContext, Arrays.asList(files));
        }
    }
}
