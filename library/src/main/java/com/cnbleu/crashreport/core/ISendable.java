package com.cnbleu.crashreport.core;

import com.cnbleu.crashreport.recordable.RecordBean;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常发送能力接口。不同于异常通知能力，该接口侧重于异常信息的外发处理，典型的：将异常信息发送给开发者。
 * <br>
 */
public interface ISendable {

    /**
     * 发送异常日志信息
     *
     * @param record {@link RecordBean}
     */
    void sendRecord(RecordBean record);
}
