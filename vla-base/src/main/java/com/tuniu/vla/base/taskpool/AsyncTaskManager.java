package com.tuniu.vla.base.taskpool;

import com.google.common.collect.Maps;
import com.tuniu.vla.base.taskpool.domain.AsyncTaskView;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 管理异步执行任务类。目前是提供查看任务线程池状态。
 *
 * 搜索所有已实现的AbstractASyncTask类，预先存入。
 *
 * Created by chengyao on 2016/1/11.
 */
public class AsyncTaskManager implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskManager.class);

    Map<String, AbstractAsyncTask> tasks = new TreeMap<String, AbstractAsyncTask>();

    private ArrayBlockingQueue<Map<String, List<Object[]>>> statusLog = new ArrayBlockingQueue<Map<String, List<Object[]>>>(128);

    private AsyncTaskDynamicConfiguration asyncTaskDynamicConfiguration;

    public void setAsyncTaskDynamicConfiguration(AsyncTaskDynamicConfiguration asyncTaskDynamicConfiguration) {
        this.asyncTaskDynamicConfiguration = asyncTaskDynamicConfiguration;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 检测并设置AsyncQueuedHandler接口实现类
        if (bean instanceof AbstractAsyncTask) {
            AbstractAsyncTask task = (AbstractAsyncTask) bean;
            ((AbstractAsyncTask) bean).setAsyncTaskDynamicConfiguration(asyncTaskDynamicConfiguration);
            tasks.put(task.getClass().getSimpleName(), task);
        }
        this.asyncTaskDynamicConfiguration.tasks = tasks;
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public List<AsyncTaskView> getViews() {
        List<AsyncTaskView> list = new ArrayList<AsyncTaskView>();
        for (Map.Entry<String, AbstractAsyncTask> entry : tasks.entrySet()) {
            list.add(getView(entry.getKey()));
        }
        return list;
    }

    public Map<String, List<Object[]>> getStatus() {
        List<Object[]> item = new ArrayList<Object[]>();
        for (Map.Entry<String, AbstractAsyncTask> entry : tasks.entrySet()) {
            AbstractAsyncTask task = entry.getValue();
            ThreadPoolExecutor threadPoolExecutor = task.getThreadPoolExecutor();
            Object[] t = new Object[] { task.getActiveCount(),
                    task.getPoolSize(),
                    threadPoolExecutor.getQueue().size(),
                    threadPoolExecutor.getCompletedTaskCount()};
            //t = new Object[] { new Random().nextInt(10), new Random().nextInt(10),new Random().nextInt(10),new Random().nextInt(
            //        10) };

            item.add(t);
        }
        Map<String, List<Object[]>> result = Maps.newHashMap();
        result.put(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()), item);
        return result;
    }

    public ArrayBlockingQueue<Map<String, List<Object[]>>> getStatusLog() {
        return statusLog;
    }


    public AsyncTaskView getView(String id) {
        AbstractAsyncTask task = tasks.get(id);
        if (task == null) {
            return null;
        }
        AsyncTaskView asyncTaskView = new AsyncTaskView();
        ThreadPoolExecutor threadPoolExecutor = task.getThreadPoolExecutor();

        asyncTaskView.setId(id);
        asyncTaskView.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
        asyncTaskView.setMaxPoolSize(threadPoolExecutor.getMaximumPoolSize());
        asyncTaskView.setKeepAliveSeconds(task.getKeepAliveSeconds());
        asyncTaskView.setAllowsCoreThreadTimeOut(task.getThreadPoolExecutor().allowsCoreThreadTimeOut());
        asyncTaskView.setGroupName(task.getThreadGroup() == null ? null : task.getThreadGroup().getName());
        asyncTaskView.setThreadNamePrefix(task.getThreadNamePrefix());
        asyncTaskView.setQueueSize(task.getThreadPoolExecutor().getQueue().size());
        asyncTaskView.setQueueCapacity(task.getQueueCapacity());

        asyncTaskView.setPoolSize(task.getPoolSize());
        asyncTaskView.setActiveCount(task.getActiveCount());
        asyncTaskView.setCompletedTaskCount(task.getThreadPoolExecutor().getCompletedTaskCount());
        asyncTaskView.setLargestPoolSize(task.getThreadPoolExecutor().getLargestPoolSize());
        asyncTaskView.setTaskCount(task.getThreadPoolExecutor().getTaskCount());
        asyncTaskView.setShutdown(task.getThreadPoolExecutor().isShutdown());
        asyncTaskView.setTerminated(task.getThreadPoolExecutor().isTerminated());
        asyncTaskView.setTerminating(task.getThreadPoolExecutor().isTerminating());
        return asyncTaskView;
    }

    public void statusLog() {
        synchronized (this) {
            Map<String, List<Object[]>> singleTimeStatus = getStatus();
            statusLog.offer(singleTimeStatus);
            if (statusLog.size() > 100) {
                statusLog.remove(statusLog.poll());
            }
        }
    }
}
