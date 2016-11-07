package com.tuniu.vla.base.asyncexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 后台异步执行队列服务，
 * 通过offer方法把待异步执行的数据放入后台异步执行。
 * 通过实现 AsyncQueuedRunner 接口，实现数据的执行，
 *
 * Created by chengyao on 2015/12/14.
 */
@ManagedResource(objectName= "com.tuniu.abt:type=AsyncQueuedExecutor,name=Statics" , description= "后台异步执行队列服务" )
public class AsyncQueuedExecutor implements InitializingBean, DisposableBean, BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncQueuedExecutor.class);

    private AsyncQueuedStatusListener asyncQueuedStatusListener;

    /**
     * 线程池
     */
    private ExecutorService pool;

    /**
     * 待处理的数据队列
     * key = 可处理对象的类的simpleName
     */
    private Map<String, BlockingQueue<Object>> taskQueueMap = new HashMap<String, BlockingQueue<Object>>();

    /**
     * 配置map
     */
    private Map<String, AbstractAsyncQueuedHandler> handlerMap = new HashMap<String, AbstractAsyncQueuedHandler>();
    /**
     * 最后一次队列满的警告时间
     */
    private Map<String, Long> lastAlertTimeMap = new HashMap<String, Long>();



    /**
     * 放入数据，等待异步执行
     * @param objects 数据对象列表
     */
    public <T> void offerList(List<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        String key = objects.get(0).getClass().getSimpleName();
        for (T object : objects) {
            offer(key, object);
        }
    }

    /**
     * 放入数据，等待异步执行
     * @param object 数据对象
     */
    public void offer(Object object) {
        if (object == null) {
            return;
        }
        String key = object.getClass().getSimpleName();
        offer(key, object);
    }

    /**
     * 获取所有队列的当前深度
     * @return map
     */
    @ManagedAttribute
    public Map<String, String> getCurrentQueueSize() {
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<String, BlockingQueue<Object>> entry : taskQueueMap.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue().size()));
        }
        return map;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 检测并设置AbstractAsyncQueuedHandler类
        if (bean instanceof AbstractAsyncQueuedHandler) {
            AbstractAsyncQueuedHandler handler = (AbstractAsyncQueuedHandler) bean;
            handler.init();
            String key = handler.getSupportClass().getSimpleName();
            BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<Object>(handler.getQueueSize());
            this.pool.execute(new AsyncQueuedRunner<Object>(handler, blockingQueue));
            this.taskQueueMap.put(key, blockingQueue);
            this.lastAlertTimeMap.put(key, 0l);
            this.handlerMap.put(key, handler);
            LOGGER.info("[asyncQueueExecutor] 初始化执行Handler [supportClass:{}, handler: {}]",
                    key, handler.getClass().getName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void afterPropertiesSet() {
        pool = Executors.newCachedThreadPool();
    }

    // 放入数据，等待异步执行
    private void offer(String key, Object object) {
        BlockingQueue<Object> queue = this.taskQueueMap.get(key);
        if (queue == null) {
            LOGGER.error("[asyncQueueExecutor] 不支持的数据处理类型：{}", key);
            return;
        }
        boolean success = queue.offer(object);
        if (asyncQueuedStatusListener != null) {
            dealAlert(key, success, queue.size());
        }
    }

    // 警报提醒队列已满，队列超过1半的阈值
    private void dealAlert(String key, boolean success, int currentQueueSize) {
        AbstractAsyncQueuedHandler handler = this.handlerMap.get(key);
        // 当插入成功且队列深度阈值小于指定时
        if (success && currentQueueSize < handler.getAlertQueueThreshold()) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long lastAlertTime = this.lastAlertTimeMap.get(key);
        long alertInterval = handler.getAlertInterval();
        if (currentTime - lastAlertTime > alertInterval) {
            this.lastAlertTimeMap.put(key, currentTime);
            // 队列满的情况
            if (!success) {
                asyncQueuedStatusListener.onFull(handler, key, currentQueueSize);
            } else {
                asyncQueuedStatusListener.onAlert(handler, key, currentQueueSize);
            }
        }
    }

    @Override
    public void destroy() {
        shutdownAndAwaitTermination(pool);
    }

    public AsyncQueuedStatusListener getAsyncQueuedStatusListener() {
        return asyncQueuedStatusListener;
    }

    public void setAsyncQueuedStatusListener(AsyncQueuedStatusListener asyncQueuedStatusListener) {
        this.asyncQueuedStatusListener = asyncQueuedStatusListener;
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        LOGGER.info("[asyncQueuedExecutor] 结束线程中...");
        pool.shutdownNow();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                LOGGER.error("[asyncQueuedExecutor] 等待60秒后，仍未结束.");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        LOGGER.info("[asyncQueuedExecutor] 线程池已结束.");
    }
}
