package com.tuniu.vla.base.taskpool.datashare;

import java.util.Map;

/**
 * 数据共享runner，在初始化时从创建该runner的线程（父线程）获取共享数据sharedData，存为对象属性
 * 在runner线程跑起来的时候，再次把对象放入ThreadLocal, 通过这样的机制，
 * 保证从某个线程以后创建的runner线程里都是公用同一个共享对象。
 *
 * Created by chengyao on 2016/2/2.
 */
public abstract class DataSharedRunner<T> implements Runnable {

    /**
     * 线程相关的独立入参
     */
    protected T param;

    /**
     * 线程间共享对象
     */
    private Map<String, Object> sharedData;

    public DataSharedRunner(T param) {
        this();
        this.param = param;
    }

    public DataSharedRunner() {
        // 从调用地线程获取共享数据
        sharedData = DataSharedSupport.getModel();
    }

    /**
     * 变更为该方法，需要覆写该innerRun，替换run
     */
    protected abstract void innerRun();

    @Override
    public final void run() {
        try {
            // 设置run线程共享数据
            DataSharedSupport.putData(sharedData);
            innerRun();
        } finally {
            // 删除关联ThreadLocal引用
            DataSharedSupport.removeData();
        }
    }

}
