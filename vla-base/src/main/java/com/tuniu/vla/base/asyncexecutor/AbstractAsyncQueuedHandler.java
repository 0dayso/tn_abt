package com.tuniu.vla.base.asyncexecutor;

import java.util.List;

/**
 * AbstractAsyncQueuedHandler 扩展此类，实现某个单线程异步执行功能。
 * 适用于日志存储等和业务逻辑不相关的功能，提高执行效率
 *
 * Created by chengyao on 2016/2/1.
 */
public abstract class AbstractAsyncQueuedHandler<T> extends AsyncQueuedExecutorSetting implements AsyncQueuedHandler<T> {

    @Override
    public void init() {
    }

    @Override
    public void execute(T object) throws Exception{
        throw new IllegalAccessError("未实现该功能，核实batch参数是否正确。");
    }

    @Override
    public void executeBatch(List<T> objects) throws Exception {
        throw new IllegalAccessError("未实现该功能，核实batch参数是否正确。");
    }
}
