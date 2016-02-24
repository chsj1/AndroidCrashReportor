package com.cnbleu.crashreport.demo;

import android.app.Application;

import com.cnbleu.crashreport.AndroidCrashReportor;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class DemoApplicaiton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidCrashReportor.getInstance().init(this);
    }
}
