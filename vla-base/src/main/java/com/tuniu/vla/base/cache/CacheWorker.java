package com.tuniu.vla.base.cache;

import java.util.List;

/**
 * Cache Worker接口
 *
 * Created by chengyao on 2016/1/6.
 */
public interface CacheWorker<T, Z> {

    /**
     * 获取锁失败后的休眠时间，单位毫秒，100MS
     */
    long SLEEP_TIME = 100;

    /**
     * 执行单条查询的方法
     *
     * @param z 输入参数
     * @return 结果
     */
    T find(Z z);

    /**
     * 执行批量查询的方法
     *
     * @param list 输入参数
     * @return 结果
     */
    List<T> batchFind(List<Z> list);

}
