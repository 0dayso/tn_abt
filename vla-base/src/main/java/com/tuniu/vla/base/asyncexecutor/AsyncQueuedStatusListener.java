package com.tuniu.vla.base.asyncexecutor;

/**
 * 异步执行队列相应的状态监听。 注意该监听器是按照频率来触发的，不是每次有情况就触发。
 * 多半用于日志的记录及监控
 *
 * Created by chengyao on 2016/2/14.
 */
public interface AsyncQueuedStatusListener {

    /**
     * 队列已满时触发
     * @param handler 处理的handler
     * @param queueName 队列名
     * @param currentQueueSize 当前队列大小
     */
    void onFull(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize);

    /**
     * 队列达到警告阈值时触发
     * @param handler 处理的handler
     * @param queueName 队列名
     * @param currentQueueSize 当前队列大小
     */
    void onAlert(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize);

}
