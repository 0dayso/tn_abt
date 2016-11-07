package com.tuniu.vla.base.asyncexecutor;

import org.springframework.jmx.export.annotation.ManagedAttribute;

/**
 * 异步单线程队列执行模式配置
 *
 * Created by chengyao on 2016/2/1.
 */
public class AsyncQueuedExecutorSetting {

    /**
     * 默认警告间隔时间，避免日志大量抛错。（5分钟）
     */
    private static final long ALERT_INTERVAL = 300000;

    /**
     * 默认的队列大小
     */
    private static final int QUEUE_SIZE = 10000;


    /**
     * 任务队列大小
     */
    private int queueSize = QUEUE_SIZE;

    /**
     * 是否批处理，如果是批处理，则数据不是立刻执行，而是等数据达到batchSize或者距上次执行超过batchWaitSec秒后再一次性执行。
     */
    private boolean batch = true;

    /**
     * 批处理的缓冲值，默认100。参考batch说明
     */
    private int batchSize = 100;

    /**
     * 批处理等待, 默认1秒
     */
    private int batchWaitSec = 1;

    /**
     * 是否邮件告警
     */
    private boolean emailAlert = false;

    /**
     * 邮件告警收件人列表
     */
    private String emailTo;

    /**
     * 告警间隔时间
     */
    private long alertInterval = ALERT_INTERVAL;

    /**
     * 队列数量警告阈值
     */
    private int alertQueueThreshold = QUEUE_SIZE / 2;

    /**
     * 队列满后是否抛错
     */
    private boolean exceptionWhenQueueFull = false;

    /**
     * 线程名，不指定的话使用Handler名
     */
    private String threadName;

    /**
     * 支持的数据类
     */
    private Class supportClass;
    @ManagedAttribute
    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    @ManagedAttribute
    public boolean isBatch() {
        return batch;
    }

    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    @ManagedAttribute
    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @ManagedAttribute
    public int getBatchWaitSec() {
        return batchWaitSec;
    }

    public void setBatchWaitSec(int batchWaitSec) {
        this.batchWaitSec = batchWaitSec;
    }

    @ManagedAttribute
    public boolean isEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(boolean emailAlert) {
        this.emailAlert = emailAlert;
    }

    @ManagedAttribute
    public long getAlertInterval() {
        return alertInterval;
    }

    public void setAlertInterval(long alertInterval) {
        this.alertInterval = alertInterval;
    }

    @ManagedAttribute
    public Class getSupportClass() {
        return supportClass;
    }

    public void setSupportClass(Class supportClass) {
        this.supportClass = supportClass;
    }

    @ManagedAttribute
    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    @ManagedAttribute
    public boolean isExceptionWhenQueueFull() {
        return exceptionWhenQueueFull;
    }

    public void setExceptionWhenQueueFull(boolean exceptionWhenQueueFull) {
        this.exceptionWhenQueueFull = exceptionWhenQueueFull;
    }

    @ManagedAttribute
    public int getAlertQueueThreshold() {
        return alertQueueThreshold;
    }

    public void setAlertQueueThreshold(int alertQueueThreshold) {
        this.alertQueueThreshold = alertQueueThreshold;
    }

    @ManagedAttribute
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
