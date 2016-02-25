package com.cnbleu.crashreport.demo.ui;

import android.os.Bundle;
import android.view.View;

import com.cnbleu.crashreport.demo.R;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class DemoActivity extends BaseToobarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        findViewById(R.id.demo_crash_main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCrashCatchInMainThread();
            }
        });

        findViewById(R.id.demo_crash_async_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCrashCatchInAsync();
            }
        });
    }

    private void testCrashCatchInMainThread() {
        throw new IllegalArgumentException("主线程崩溃");
    }

    private void testCrashCatchInAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模拟空指针异常
                String str = null;
                str.length();
            }
        }).start();
    }
}
