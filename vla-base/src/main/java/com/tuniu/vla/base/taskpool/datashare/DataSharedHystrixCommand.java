package com.tuniu.vla.base.taskpool.datashare;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

import java.util.Map;

/**
 * Created by chengyao on 2016/3/5.
 */
public abstract class DataSharedHystrixCommand<R> extends HystrixCommand<R> {

    /**
     * 线程间共享对象
     */
    protected Map<String, Object> sharedData;

    protected DataSharedHystrixCommand(HystrixCommandGroupKey group) {
        super(group);
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    protected DataSharedHystrixCommand(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
        super(group, threadPool);
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    protected DataSharedHystrixCommand(HystrixCommandGroupKey group,
            int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, executionIsolationThreadTimeoutInMilliseconds);
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    protected DataSharedHystrixCommand(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool,
            int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, threadPool, executionIsolationThreadTimeoutInMilliseconds);
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    protected DataSharedHystrixCommand(Setter setter) {
        super(setter);
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    protected abstract R innerRun() throws Exception;

    @Override
    protected R run() throws Exception {
        try {
            // 设置run线程共享数据
            DataSharedSupport.putData(sharedData);
            return innerRun();
        } finally {
            // 删除关联ThreadLocal引用
            DataSharedSupport.removeData();
        }
    }

}
