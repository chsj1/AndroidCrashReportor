package com.cnbleu.crashreport;

import com.cnbleu.crashreport.core.ICrashCatchable;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class CrashCatchConfig {

    private Builder mBuilder;

    private CrashCatchConfig(Builder builder) {
        this.mBuilder = builder;
    }

    public List<ICrashCatchable> getCrashCatchables() {
        return mBuilder.mCrashCatchables;
    }

    /**
     * 异常捕获配置构建类
     */
    public static class Builder {

        private List<ICrashCatchable> mCrashCatchables = new ArrayList<>();


        public <P> Builder addCrashCatchable(ICrashCatchable crashCatchable) {
            this.mCrashCatchables.add(crashCatchable);
            return this;
        }


        public CrashCatchConfig build() {
            return new CrashCatchConfig(this);
        }
    }
}
