package com.cnbleu.crashreport.catchable.javacatch;

import com.cnbleu.crashreport.core.ICrashCatchable;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public abstract class AbsJavaCrashCatchable implements ICrashCatchable, Thread.UncaughtExceptionHandler {
    private SimpleJavaCrashCatchBuilder mBuilder;

    public AbsJavaCrashCatchable(SimpleJavaCrashCatchBuilder builder) {
        this.mBuilder = builder;
    }


    /**
     * The thread is being terminated by an uncaught exception. Further
     * exceptions thrown in this method are prevent the remainder of the
     * method from executing, but are otherwise ignored.
     *
     * @param thread the thread that has an uncaught exception
     * @param ex     the exception that was thrown
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        catchCrash(thread, ex);
    }
}
