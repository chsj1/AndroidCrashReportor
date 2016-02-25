package com.cnbleu.crashreport.core;

/**
 * <b>Project:</b> AndroidCrashReportor<br>
 * <b>Create Date:</b> 16/2/24<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 异常通知能力。
 * <br>
 */
public interface INotifiable<T> {

    /**
     * 异常产生之后被调用。与具体的控制器实现有关。
     *
     * @param data {@link T}
     */
    void notify(T data);
}
