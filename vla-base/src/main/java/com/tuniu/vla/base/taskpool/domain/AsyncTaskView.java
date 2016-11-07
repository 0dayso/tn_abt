package com.tuniu.vla.base.taskpool.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 异步任务池状态展示类
 *
 * Created by chengyao on 2016/1/11.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsyncTaskView implements Serializable {

    private static final long serialVersionUID = -482775153691151841L;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 主动执行任务的近似线程数。
     */
    private Integer activeCount;

    /**
     * 核心线程数。
     */
    private Integer corePoolSize;

    /**
     * 线程保持活动的时间，该时间就是超过核心池大小的线程可以在终止前保持空闲的时间值。
     */
    private Integer keepAliveSeconds;

    /**
     * 允许的最大线程数。
     */
    private Integer maxPoolSize;

    /**
     * 池中的当前线程数。
     */
    private Integer poolSize;

    /**
     * 队列长度
     */
    private Integer queueSize;

    /**
     * 可用队列长度
     */
    private Integer queueCapacity;

    /**
     * 已完成执行的近似任务总数。
     */
    private Long completedTaskCount;

    /**
     * 曾经同时位于池中的最大线程数。
     */
    private Integer largestPoolSize;

    /**
     * 曾计划执行的近似任务总数。
     */
    private Long taskCount;

    /**
     * 如果此池允许核心线程超时和终止，如果在 keepAlive 时间内没有任务到达，新任务到达时正在替换（如果需要），则返回 true。
     */
    private Boolean allowsCoreThreadTimeOut;

    /**
     * 如果此执行程序已关闭，则返回 true。
     */
    private Boolean shutdown;

    /**
     * 如果关闭后所有任务都已完成，则返回 true。
     */
    private Boolean terminated;

    /**
     * 如果此执行程序处于在 shutdown 或 shutdownNow 之后正在终止但尚未完全终止的过程中，则返回 true。
     */
    private Boolean terminating;

    /**
     * 线程组名
     */
    private String groupName;

    /**
     * 线程前缀
     */
    private String threadNamePrefix;

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public Long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(Long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public Integer getLargestPoolSize() {
        return largestPoolSize;
    }

    public void setLargestPoolSize(Integer largestPoolSize) {
        this.largestPoolSize = largestPoolSize;
    }

    public Long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Long taskCount) {
        this.taskCount = taskCount;
    }

    public Boolean getAllowsCoreThreadTimeOut() {
        return allowsCoreThreadTimeOut;
    }

    public void setAllowsCoreThreadTimeOut(Boolean allowsCoreThreadTimeOut) {
        this.allowsCoreThreadTimeOut = allowsCoreThreadTimeOut;
    }

    public Boolean getShutdown() {
        return shutdown;
    }

    public void setShutdown(Boolean shutdown) {
        this.shutdown = shutdown;
    }

    public Boolean getTerminated() {
        return terminated;
    }

    public void setTerminated(Boolean terminated) {
        this.terminated = terminated;
    }

    public Boolean getTerminating() {
        return terminating;
    }

    public void setTerminating(Boolean terminating) {
        this.terminating = terminating;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}
