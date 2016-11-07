package com.tuniu.vla.base.asyncexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 对单个handler的处理线程，执行对缓冲队列的读取，并合成多条数字，调用handler的执行方法。
 *
 * Created by chengyao on 2015/12/14.
 */
public class AsyncQueuedRunner<T> implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncQueuedRunner.class);

    private AbstractAsyncQueuedHandler abstractAsyncQueuedHandler;

    private BlockingQueue<T> blockingQueue;

    // 是否批量执行
    private boolean batch;

    // 执行一次操作的最大缓冲量
    private int batchSize;

    // 缓冲满前等待多少秒，就执行一次数据操作
    private int batchWaitSec;

    private List<T> buff;

    // 是否已中断
    boolean interrupted = false;
    // 是否已完成所有数据入库
    boolean allFinished = false;

    public AsyncQueuedRunner(AbstractAsyncQueuedHandler abstractAsyncQueuedHandler, BlockingQueue<T> blockingQueue) {
        this.abstractAsyncQueuedHandler = abstractAsyncQueuedHandler;
        this.blockingQueue = blockingQueue;
        this.batch = abstractAsyncQueuedHandler.isBatch();
        this.batchSize = abstractAsyncQueuedHandler.getBatchSize();
        this.batchWaitSec = abstractAsyncQueuedHandler.getBatchWaitSec();
        this.buff = new ArrayList<T>(this.batchSize);
    }

    @Override
    public void run() {
        String settingName = abstractAsyncQueuedHandler.getThreadName();
        if (settingName == null) {
            settingName = abstractAsyncQueuedHandler.getClass().getSimpleName();
        }
        // change thread name
        final Thread currentThread = Thread.currentThread();
        currentThread.setName("AsyncQueueRunner" + "-" + settingName + "-" + currentThread.getId());

        while (!interrupted || !allFinished) {
            try {
                if (this.batch) {
                    takeAndExecuteBatch();
                } else {
                    takeAndExecuteSingle();
                }
            } catch (InterruptedException ex) {
                interrupted = true;
            }
        }

        LOGGER.info("[asyncQueuedExecutor] [asyncQueueRunner] {} Runner 线程已结束.", currentThread.getName());
    }

    /**
     * 从队列获取批量数据，并执行。（异常时日志打印）
     * @throws InterruptedException
     */
    private void takeAndExecuteBatch() throws InterruptedException {
        T t = blockingQueue.poll(this.batchWaitSec, TimeUnit.SECONDS);
        if (t == null) { // 1秒后获取不到新的数据
            if (buff.size() > 0) { // 执行buff中的数据
                executeBatch();
            }
            // 如果已中断，设置数据已全部完成
            if (interrupted) {
                allFinished = true;
            }
        } else {
            buff.add(t);
            if (buff.size() >= this.batchSize) { // 获取到数据插入buff，如果超过最大值则执行
                executeBatch();
            }
        }
    }


    @SuppressWarnings("unchecked")
    private void executeBatch() {
        try {
            abstractAsyncQueuedHandler.executeBatch(buff);
        } catch (Exception ex) {
            LOGGER.error("[asyncQueuedExecutor] [asyncQueueRunner]: {} execute error.", abstractAsyncQueuedHandler.getClass().getSimpleName(), ex);
        } finally {
            buff.clear();
        }
    }

    /**
     * 从队列获取单条数据，并执行。（异常时日志打印）
     * @throws InterruptedException
     */
    private void takeAndExecuteSingle() throws InterruptedException {
        T t = blockingQueue.poll(this.batchWaitSec, TimeUnit.SECONDS);
        if (t == null) {
            // 如果已中断，设置数据已全部完成
            if (interrupted) {
                allFinished = true;
            }
        } else {
            executeSingle(t);
        }
    }

    @SuppressWarnings("unchecked")
    private void executeSingle(T t) {
        try {
            abstractAsyncQueuedHandler.execute(t);
        } catch (Exception ex) {
            LOGGER.error("[asyncQueuedExecutor] [asyncQueueRunner]: {} execute error.", abstractAsyncQueuedHandler.getClass().getSimpleName(), ex);
        }
    }

}
