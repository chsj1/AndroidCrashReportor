package com.cnbleu.crashreport.sender.upload.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cnbleu.crashreport.tools.permission.PermissionConstants;
import com.cnbleu.crashreport.tools.permission.PermissionHelper;
import com.cnbleu.crashreport.tools.permission.PermissionUpdatedReceiver;
import com.cnbleu.crashreport.tools.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.cnbleu.crashreport.CrashDebug.TAG;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 文件上传服务
 * <br>
 */
public class FileUploadService extends Service implements PermissionConstants {
    /** 日志文件保存的目录 */
    private static final String SAVE_PATH = "crash_log";

    private static final String EXTRA_DATA = "ext_data";
    private static final String EXTRA_STARTID = "ext_startid";

    /** 必需的系统权限 */
    private static final String PERMISSION_WANTED = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String[] PERMISSIONS = new String[]{PERMISSION_WANTED};
    /** 权限请求ID */
    private static final int PERMISSION_REQUEST_CODE = 0x350;

    private volatile Looper mServiceLooper;
    private volatile ServiceHandler mServiceHandler;


    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            final Bundle bundle = (Bundle) msg.obj;
            final int startId = bundle.getInt(EXTRA_STARTID);

            onHandleIntent(bundle);
            stopSelf(startId);
        }
    }

    private PermissionUpdatedReceiver mPermissionUpdatedReceiver = new PermissionUpdatedReceiver() {

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String[] permissions,
                                               int[] grantResults,
                                               Bundle extras) {
            Log.w("perm", "requestCode: " + requestCode);
            if (PERMISSION_REQUEST_CODE == requestCode) {
                if (grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                    startWork(extras);
                }
            }
        }
    };


    public static void uploadFiles(Context context, List<File> files) {
        Intent intent = new Intent(context, FileUploadService.class);
        intent.putExtra(EXTRA_DATA, (Serializable) files);
        context.startService(intent);
    }

    public FileUploadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册权限更新广播，在权限状态更新时继续之前的任务
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(mPermissionUpdatedReceiver, intentFilter);

        HandlerThread thread = new HandlerThread("upload", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (!isStorageReady()) {
            return;
        }

        final Bundle bundle = intent.getExtras();
        bundle.putInt(EXTRA_STARTID, startId);

        final Context context = getApplicationContext();
        // 如果没有权限，则向系统请求权限
        if (!PermissionHelper.checkPermission(context, PERMISSION_WANTED)) {
            // 权限的请求是一个异步过程，为了方便处理，需要携带本次service启动时传递的数据
            PermissionHelper.requestPermissions(context,
                                                PERMISSIONS,
                                                PERMISSION_REQUEST_CODE,
                                                bundle);
        } else {
            startWork(bundle);
        }
    }

    private void startWork(Bundle bundle) {
        Message msg = mServiceHandler.obtainMessage();
        msg.obj = bundle;
        mServiceHandler.sendMessage(msg);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onStart(intent, startId);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mPermissionUpdatedReceiver);
        mServiceLooper.quit();
        super.onDestroy();
    }


    @SuppressWarnings("unchecked")
    protected void onHandleIntent(Bundle bundle) {
        final List<File> files = (List<File>) bundle.getSerializable(EXTRA_DATA);
        if (null == files) {
            return;
        }

        // 在此检查存储器状态，因为权限请求时间不可控制，期间用户有可能弹出存储器
        if (!isStorageReady()) {
            return;
        }

        final List<File> uploadedFiles = new ArrayList<>();

        File path = new File(Environment.getExternalStorageDirectory().getPath(), SAVE_PATH);
        // 目标路径不存在并且创建失败时终止任务
        if (!path.exists() && !path.mkdirs()) {
            Log.w(TAG, "create directory error, directory: " + path.getAbsolutePath());
            return;
        }

        for (File file : files) {
            if (!file.exists()) {
                continue;
            }

            if (doUploadFile(path, file)) {
                uploadedFiles.add(file);
            }
        }

        // 如果文件已经上传，则删除文件
        if (!uploadedFiles.isEmpty()) {
            File file;
            for (int i = 0; i < uploadedFiles.size(); i++) {
                file = uploadedFiles.get(i);
                file.delete();
            }
        }
    }

    /**
     * 上传文件到服务器
     *
     * @param file
     *
     * @return
     */
    private boolean doUploadFile(File path, File file) {
        // FIXME: 16/2/24 此处以文件的移动来代替文件的上传
        return moveFile(file, new File(path.getPath(), file.getName()));
    }

    private boolean moveFile(File from, File to) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);

            byte[] bytes = new byte[1024];
            int count;
            while (-1 != (count = fis.read(bytes))) {
                fos.write(bytes, 0, count);
            }
            fos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamUtils.close(fos);
            StreamUtils.close(fis);
        }

        return false;
    }

    /**
     * SD卡是否可以使用
     *
     * @return
     */
    private boolean isStorageReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
