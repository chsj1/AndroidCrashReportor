package com.cnbleu.crashreport.catchable.javacatch;

import com.cnbleu.crashreport.core.AbsCrashCatchable;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/23<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Java异常捕获控制类的抽象实现。
 * <br>
 */
public abstract class AbsJavaCrashCatchable<T> extends AbsCrashCatchable<T> implements Thread.UncaughtExceptionHandler {

    public AbsJavaCrashCatchable(SimpleJavaCrashCatchBuilder builder) {
        super(builder);
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
