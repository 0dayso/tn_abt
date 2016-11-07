package com.tuniu.vla.base.taskpool;

import com.tuniu.vla.base.taskpool.datashare.DataSharedCallable;
import com.tuniu.vla.base.taskpool.datashare.DataSharedRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.util.Assert;

import java.util.concurrent.*;

/**
 * 异步任务基类， 扩展此类，实现异步任务执行
 * 需实现AsyncTask接口。 调用时执行 execute方法或submit方法。
 *
 * Created by chengyao on 2016/1/8.
 */
public abstract class AbstractAsyncTask<Z, T> extends ExecutorConfigurationSupport
        implements RejectedExecutionHandler {

    private static final long serialVersionUID = -1920359888705003957L;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Object poolSizeMonitor = new Object();

    private int corePoolSize = 1;

    private int maxPoolSize = Integer.MAX_VALUE;

    private int keepAliveSeconds = 60;

    private boolean allowCoreThreadTimeOut = true;

    private int queueCapacity = Integer.MAX_VALUE;

    private ThreadPoolExecutor threadPoolExecutor;

    private AsyncTaskDynamicConfiguration asyncTaskDynamicConfiguration;

    @Override
    public void initialize() {
        asyncTaskDynamicConfiguration.loadRemoteSetting(this);

        checkSetting();

        setRejectedExecutionHandler(this);
        super.initialize();
    }


    /**
     * 异步线程中的具体执行逻辑
     * @param param 入参
     * @return 返回数据，在onSuccess中调用
     * @throws Exception 异常
     */
    protected abstract Z exec(T param) throws Exception;


    protected void beforeExecute(String callbackServiceName, T param) {
    }

    /**
     * 异步执行成功后的回调
     * @param callbackServiceName 入参的回调serviceName
     * @param result 执行返回结果
     */
    protected abstract void onSuccess(String callbackServiceName, Z result);

    /**
     * 异步执行失败后的回调（不包含线程池满了以后的RejectedExecutionException）
     * @param callbackServiceName 入参的回调serviceName
     * @param ex 异常
     */
    protected abstract void onException(String callbackServiceName, Exception ex);

    /**
     * 执行异步工作
     */
    public void execute(final String callbackServiceName, T param) {
        Executor executor = getThreadPoolExecutor();
        try {
            executor.execute(new DataSharedRunner<T>(param) {
                @Override
                public void innerRun() {
                    try {
                        beforeExecute(callbackServiceName, param);
                        Z z = exec(param);
                        onSuccess(callbackServiceName, z);
                    } catch (Exception ex) {
                        onException(callbackServiceName, ex);
                    }
                }
            });
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task", ex);
        }
    }

    /**
     * 执行异步工作
     */
    public void execute(final String callbackServiceName) {
        execute(callbackServiceName, null);
    }

    /**
     * 执行异步工作
     */
    public void execute(T param) {
        execute(null, param);
    }

    public void execute() {
        execute(null, null);
    }

    public Future<Z> submit(T param) {
        ExecutorService executor = getThreadPoolExecutor();
        try {
            return executor.submit(new DataSharedCallable<Z, T>(param) {
                @Override
                protected Z innerCall() {
                    try {
                        beforeExecute(null, param);
                        Z z = exec(param);
                        onSuccess(null, z);
                        return z;
                    } catch (Exception ex) {
                        onException(null, ex);
                    }
                    return null;
                }
            });
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task", ex);
        }
    }

    public Future<Z> submit() {
        return submit(null);
    }

    protected ExecutorService initializeExecutor(
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {

        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(
                this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
                queue, threadFactory, rejectedExecutionHandler);
        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;

        return executor;
    }

    /**
     * Create the BlockingQueue to use for the ThreadPoolExecutor.
     * <p>A LinkedBlockingQueue instance will be created for a positive
     * capacity value; a SynchronousQueue else.
     * @param queueCapacity the specified queue capacity
     * @return the BlockingQueue instance
     * @see LinkedBlockingQueue
     * @see SynchronousQueue
     */
    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<Runnable>(queueCapacity);
        }
        else {
            return new SynchronousQueue<Runnable>();
        }
    }

    /**
     * Return the underlying ThreadPoolExecutor for native access.
     * @return the underlying ThreadPoolExecutor (never <code>null</code>)
     * @throws IllegalStateException if the ThreadPoolTaskExecutor hasn't been initialized yet
     */
    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        throw new RejectedExecutionException("Task " + task.toString() +
                " rejected from " +
                executor.toString());
    }


    /**
     * Set the ThreadPoolExecutor's core pool size.
     * Default is 1.
     * <p><b>This setting can be modified at runtime, for example through JMX.</b>
     */
    public void setCorePoolSize(int corePoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }
        }
    }

    /**
     * Return the ThreadPoolExecutor's core pool size.
     */
    public int getCorePoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.corePoolSize;
        }
    }

    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     * Default is <code>Integer.MAX_VALUE</code>.
     * <p><b>This setting can be modified at runtime, for example through JMX.</b>
     */
    public void setMaxPoolSize(int maxPoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }
        }
    }

    /**
     * Return the ThreadPoolExecutor's maximum pool size.
     */
    public int getMaxPoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.maxPoolSize;
        }
    }

    /**
     * Set the ThreadPoolExecutor's keep-alive seconds.
     * Default is 60.
     * <p><b>This setting can be modified at runtime, for example through JMX.</b>
     */
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        synchronized (this.poolSizeMonitor) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * Return the ThreadPoolExecutor's keep-alive seconds.
     */
    public int getKeepAliveSeconds() {
        synchronized (this.poolSizeMonitor) {
            return this.keepAliveSeconds;
        }
    }

    /**
     * Specify whether to allow core threads to time out. This enables dynamic
     * growing and shrinking even in combination with a non-zero queue (since
     * the max pool size will only grow once the queue is full).
     * <p>Default is "false". Note that this feature is only available on Java 6
     * or above. On Java 5, consider switching to the backport-concurrent
     * version of ThreadPoolTaskExecutor which also supports this feature.
     * @see ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)
     */
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     * Default is <code>Integer.MAX_VALUE</code>.
     * <p>Any positive value will lead to a LinkedBlockingQueue instance;
     * any other value will lead to a SynchronousQueue instance.
     * @see LinkedBlockingQueue
     * @see SynchronousQueue
     */
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    /**
     * 队列长度
     * @return
     */
    public int getQueueCapacity() {
        return queueCapacity;
    }

    /**
     * Return the current pool size.
     * @see ThreadPoolExecutor#getPoolSize()
     */
    public int getPoolSize() {
        return getThreadPoolExecutor().getPoolSize();
    }

    /**
     * Return the number of currently active threads.
     * @see ThreadPoolExecutor#getActiveCount()
     */
    public int getActiveCount() {
        return getThreadPoolExecutor().getActiveCount();
    }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix("AsyncTask-" + threadNamePrefix + "-");
    }

    /**
     * This task executor prefers short-lived work units.
     */
    public boolean prefersShortLivedTasks() {
        return true;
    }

    public void setAsyncTaskDynamicConfiguration(AsyncTaskDynamicConfiguration asyncTaskDynamicConfiguration) {
        this.asyncTaskDynamicConfiguration = asyncTaskDynamicConfiguration;
    }


    // 校验参数设置，防止远端修改后，导致初始化线程池异常。
    private void checkSetting() {
        if (this.corePoolSize < 0) {
            logger.warn("corePoolSize < 0, use 0 for current value.");
            this.corePoolSize = 0;
        }
        if (this.maxPoolSize < 1) {
            logger.warn("maxPoolSize <= 0, use Integer.MAX_VALUE for current value.");
            this.maxPoolSize = Integer.MAX_VALUE;
        }
        if (this.corePoolSize > this.maxPoolSize) {
            logger.warn("corePoolSize > maxPoolSize, use maxPoolSize for current value.");
            this.corePoolSize = this.maxPoolSize;
        }
        if (keepAliveSeconds < 0) {
            logger.warn("keepAliveSeconds < 0, use 60 for current value.");
            keepAliveSeconds = 60;
        }
    }

}
