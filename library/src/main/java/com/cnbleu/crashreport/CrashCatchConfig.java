package com.cnbleu.crashreport;

import com.cnbleu.crashreport.core.ICrashCatchable;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常捕获控制器配置工具
 * <br>
 */
public class CrashCatchConfig {

    private Builder mBuilder;

    private CrashCatchConfig(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * 获取所有的异常控制器
     *
     * @return {@link ICrashCatchable} list
     */
    public List<ICrashCatchable> getCrashCatchables() {
        return mBuilder.mCrashCatchables;
    }

    /**
     * 异常捕获配置构建类
     */
    public static class Builder {

        private List<ICrashCatchable> mCrashCatchables = new ArrayList<>();


        /**
         * 增加一个异常控制器
         *
         * @param crashCatchable {@link ICrashCatchable}
         *
         * @return
         */
        public Builder addCrashCatchable(ICrashCatchable crashCatchable) {
            this.mCrashCatchables.add(crashCatchable);
            return this;
        }

        /**
         * 构建异常控制器配置类
         *
         * @return {@link CrashCatchConfig}
         */
        public CrashCatchConfig build() {
            return new CrashCatchConfig(this);
        }
    }
}
