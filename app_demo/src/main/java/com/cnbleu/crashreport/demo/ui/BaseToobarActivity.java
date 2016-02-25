package com.cnbleu.crashreport.demo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.cnbleu.crashreport.demo.R;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/25<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class BaseToobarActivity extends AppCompatActivity {


    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onSetupToolbar(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        onSetupToolbar(view);
    }

    protected void onSetupToolbar(View view) {
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (null != actionBar) {
                actionBar.setTitle(getTitle());
            }
        }
    }

}
