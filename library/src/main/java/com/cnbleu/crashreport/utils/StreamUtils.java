package com.cnbleu.crashreport.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class StreamUtils {

    private StreamUtils() {
        // hide
    }

    public static void close(Closeable closeable) {
        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            // ignore
        }
    }
}
