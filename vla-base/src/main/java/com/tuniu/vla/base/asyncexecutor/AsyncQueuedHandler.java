package com.tuniu.vla.base.asyncexecutor;

import java.util.List;

/**
 * 后台异步队列执行handler，加入队列通过
 * AsyncQueuedExecutor#offer、offerList方法，单条、批量加入。
 * 然后后台线程会拉取数据异步执行
 *
 * Created by chengyao on 2015/12/14.
 */
public interface AsyncQueuedHandler<T> {

     /**
      * 初始化执行，一般用于初始化参数设置
      */
     void init();

     /**
      * 数据执行方法(批量)
      * @param objects 数据对象列表
      */
     void executeBatch(List<T> objects) throws Exception;

     /**
      * 数据执行方法
      * @param object 数据对象
      */
     void execute(T object) throws Exception;
}
