package com.cnbleu.crashreport.catchable.javacatch;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * javactach相关的工具类。
 * <br>
 */
/* package*/class Utils {

    private Utils() {
        // hide
    }

    /**
     * 异常堆栈转化为string
     *
     * @param ex {@link Throwable}
     *
     * @return
     */
    static String stacktraceToString(Throwable ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        writer.flush();
        return writer.toString();
    }

}
