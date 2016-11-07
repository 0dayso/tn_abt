package com.tuniu.vla.base.cache;

import com.tuniu.vla.base.redis.JedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 带进程内同步锁的redis缓存读写操作框架类<br>
 * 引用该类防止大量线程同时请求时造成同时回源，增加系统负担。<br>
 * 基本流程：<br>
 * 1) 读取 -(未找到)-> 回源 -> 写入 -> 返回<br>
 * 2) 读取 -(找到)-> 返回
 *
 * @param <T> 输出类型
 * @param <Z> 读写的输入参数类型
 */
public abstract class SyncCacheWorker<T, Z> implements CacheWorker<T, Z> {

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    protected int defaultCacheTime = 24 * 60 * 60; // ONE_DAY

    @Resource
    protected JedisTemplate jedisTemplate;

    public void setDefaultCacheTime(int defaultCacheTime) {
        this.defaultCacheTime = defaultCacheTime;
    }

    /**
     * 读Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出读取结果
     */
    protected T read(Z z) { // NOSONAR
        throw new IllegalArgumentException("read 方法未定义");
    }

    /**
     * 批量读redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出读取结果
     */
    protected List<T> batchRead(List<Z> z) { // NOSONAR
        throw new IllegalArgumentException("batchRead 方法未定义");
    }

    /**
     * 回源并写Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出回源结果
     */
    protected T write(Z z) { // NOSONAR
        throw new IllegalArgumentException("write 方法未定义");
    }

    /**
     * 批量回源并写Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出回源结果
     */
    protected List<T> batchWrite(List<Z> z, List<Integer> pos) { // NOSONAR
        throw new IllegalArgumentException("batchWrite 方法未定义");
    }

    /**
     * 单条回源后，立即执行方法入口
     *
     * @param t 输入对象
     * @return 输出对象
     */
    protected T beforeReturn(T t) {
        return t;
    }

    /**
     * 批量回源后，立即执行方法入口
     *
     * @param list 输入对象
     * @return 输出对象
     */
    protected List<T> beforeBatchReturn(List<T> list) {
        return list;
    }

    /**
     * 执行批量查询的方法
     *
     * @param list 输入参数
     * @return 结果
     */
    @SuppressWarnings("all")
    @Override
    public final List<T> batchFind(List<Z> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<T>();
        }
        boolean lockedFail = false;
        do {
            if (lockedFail) {
                lockedFail = false;
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    return Collections.emptyList();
                }
            }
            r.lock();
            try {
                List<T> tList = batchRead(list);
                List<Integer> posList = nullPos(tList);
                if (!posList.isEmpty()) {
                    r.unlock();
                    w.lock();
                    try {
                        tList = batchRead(list);
                        posList = nullPos(tList);
                        if (!posList.isEmpty()) {
                            if (jedisTemplate.acquireLock(getLockKey())) {
                                try {
                                    List<T> tmpList = batchWrite(list, posList);
                                    for (int i = 0; i < posList.size(); i++) {
                                        int pos = posList.get(i);
                                        tList.set(pos, tmpList.get(i));
                                    }
                                } finally {
                                    jedisTemplate.releaseLock(getLockKey());
                                }
                            } else {
                                lockedFail = true;
                                continue;
                            }
                        }
                    } finally {
                        r.lock();
                        w.unlock();
                    }
                }
                return beforeBatchReturn(tList);
            } finally {
                r.unlock();
            }
        } while (true);
    }

    /**
     * 执行单条查询的方法
     *
     * @param z 输入参数
     * @return 结果
     */
    @SuppressWarnings("all")
    @Override
    public final T find(Z z) {
        if (z == null) {
            return null;
        }
        boolean lockedFail = false;
        do {
            if (lockedFail) {
                lockedFail = false;
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException ex) {
                    return null;
                }
            }
            r.lock();
            try {
                T t = read(z);
                if (t == null) {
                    r.unlock();
                    w.lock();
                    try {
                        t = read(z);
                        if (t == null) {
                            if (jedisTemplate.acquireLock(getLockKey())) {
                                try {
                                    t = write(z);
                                } finally {
                                    jedisTemplate.releaseLock(getLockKey());
                                }
                            } else {
                                lockedFail = true;
                                continue;
                            }
                        }
                    } finally {
                        r.lock();
                        w.unlock();
                    }
                }
                return beforeReturn(t);
            } finally {
                r.unlock();
            }
        } while (true);
    }

    /**
     * 计算列表中为null的各元素位置
     *
     * @param t 列表
     * @return null元素位置列表
     */
    private List<Integer> nullPos(List<T> t) {
        List<Integer> posList = new ArrayList<Integer>();
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) == null) {
                posList.add(i);
            }
        }
        return posList;
    }

    private String getLockKey() {
        return "lock_".concat(this.getClass().getSimpleName());
    }

}
