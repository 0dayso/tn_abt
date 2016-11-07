package com.tuniu.abt.controller.console;

import com.tuniu.vla.base.taskpool.AsyncTaskManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 异步任务线程池管理
 * Created by chengyao on 2016/3/7.
 */
@RestController
@RequestMapping("/console/")
public class AsyncTaskManagerController {

    @Resource
    private AsyncTaskManager asyncTaskManager;

    // 所有异步任务池状态
    @RequestMapping(value = "/async/tasks")
    public Object asyncTaskAll() {
        return asyncTaskManager.getViews();
    }

    // 单个异步任务池状态，id=扩展AbstractAsyncTask的类名，例如 BookAsyncTask
    @RequestMapping(value = "/async/task/{id}")
    public Object asyncTask(@PathVariable String id) {
        return asyncTaskManager.getView(id);
    }

    @RequestMapping(value = "/async/tasks/status")
    public Object asyncTaskStatus() {
        return asyncTaskManager.getStatus();
    }

    @RequestMapping(value = "/async/tasks/status/log")
    public Object asyncTaskStatusLog() {
        return asyncTaskManager.getStatusLog();
    }

}
