package com.cnbleu.crashreport.demo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cnbleu.crashreport.demo.R;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        findViewById(R.id.demo_crash_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCrashCatchInMainThread();
            }
        });
    }

    private void testCrashCatchInMainThread() {
        throw new RuntimeException("主线程崩溃");
    }
}
