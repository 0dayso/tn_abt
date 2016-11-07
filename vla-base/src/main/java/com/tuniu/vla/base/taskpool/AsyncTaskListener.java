package com.tuniu.vla.base.taskpool;

/**
 * 异步执行任务结果，监听器，
 * Created by chengyao on 2016/2/14.
 */
public interface AsyncTaskListener {

    /**
     * 异步执行成功后的回调
     * @param callbackServiceName 通过execute方法传入的回调serviceName
     * @param z 异步执行输出结果
     * @param <Z> 输出结果类
     */
    <Z> void onSuccess(String callbackServiceName, Z z);

    /**
     * 异步执行失败的回调
     * @param callbackServiceName 通过execute方法传入的回调serviceName
     * @param ex 异常
     */
    void onException(String callbackServiceName, Exception ex);

}
