package com.tuniu.vla.base.taskpool.datashare;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 数据共享Callable， 内部存储一个sessionId, 在初始化时从创建该Callable的线程（父线程）获取
 * 在Callable线程跑起来的时候，修改ThreadLocal值为sessionId, 通过这样的机制，
 * 保证从某个线程以后创建的Callable线程里都是公用同一个sessionId。
 *
 * Created by chengyao on 2016/2/2.
 */
public abstract class DataSharedCallable<V, T> implements Callable<V> {

    /**
     * 线程相关的独立入参
     */
    protected T param;

    /**
     * 线程间共享对象
     */
    private Map<String, Object> sharedData;

    public DataSharedCallable(T param) {
        this();
        this.param = param;
    }

    public DataSharedCallable() {
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    /**
     * 变更为该方法，需要覆写该innerCall，替换call
     */
    protected abstract V innerCall();

    @Override
    public V call() throws Exception {
        try {
            // 设置run线程共享数据
            DataSharedSupport.putData(sharedData);
            return innerCall();
        } finally {
            // 删除关联ThreadLocal引用
            DataSharedSupport.removeData();
        }
    }

}
