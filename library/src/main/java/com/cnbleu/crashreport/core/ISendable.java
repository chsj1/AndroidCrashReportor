package com.cnbleu.crashreport.core;

import com.cnbleu.crashreport.recordable.RecordBean;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface ISendable {

    /**
     * @param record
     */
    void sendRecord(RecordBean record);
}
