package com.tuniu.vla.base.taskpool;

import java.util.concurrent.RejectedExecutionHandler;

/**
 * 异步任务接口
 *
 * Created by chengyao on 2016/1/8.
 */
public interface AsyncTask<Z, T> extends RejectedExecutionHandler {

    /**
     * 执行任务，不要在调用时执行！
     * @param param 传入参数
     * @return 输出
     * @throws Exception
     */
    // Z exec(T param) throws Exception;

    /**
     * 异步执行成功后的回调
     * @param callbackServiceName 通过execute方法传入的回调serviceName
     * @param z 异步执行输出结果
     */
    void onSuccess(String callbackServiceName, Z z);

    /**
     * 异步执行失败的回调
     * @param callbackServiceName 通过execute方法传入的回调serviceName
     * @param ex 异常
     */
    void onException(String callbackServiceName, Exception ex);

}
