package com.tuniu.abt.base;

import com.tuniu.abt.base.mail.SendEmail;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.vla.base.asyncexecutor.AbstractAsyncQueuedHandler;
import com.tuniu.vla.base.asyncexecutor.AsyncQueuedStatusListener;
import com.tuniu.vla.base.constants.SysEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台异步执行队列服务， 队列满或超过阈值后的处理类
 *
 * Created by chengyao on 2016/2/14.
 */
@Service
public class AsyncQueuedStatusLogger implements AsyncQueuedStatusListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncQueuedStatusLogger.class);

    @Resource
    SendEmail sendEmail;

    @Override
    public void onFull(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize) {
        String alertMessage = "[asyncQueueExecutor] 队列已满! 队列名:" + queueName +
            ", 处理Handler:" + handler.getClass().getName() +
            ", 当前队列大小：" + currentQueueSize;
        LOGGER.warn(alertMessage);
        emailAlert(handler, alertMessage);

        if (handler.isExceptionWhenQueueFull()) {
            throw new BizzException(SysEx.ASYNC_QUEUE_EXECUTOR_FULL, "[asyncQueueExecutor] 队列已满! 队列名:" + queueName +
                    ", 处理Handler:" + handler.getClass().getName() +
                    ", 当前队列大小：" + currentQueueSize);
        }
    }

    @Override
    public void onAlert(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize) {
        String alertMessage = "[asyncQueueExecutor] 队列深度超过阈值! 队列名:" + queueName +
            ", 处理Handler:" + handler.getClass().getName() +
            ", 当前队列大小：" + currentQueueSize +
            ", 阈值：" + handler.getAlertQueueThreshold();
        LOGGER.warn(alertMessage);
        emailAlert(handler, alertMessage);
    }

    private void emailAlert(AbstractAsyncQueuedHandler handler, String message) {
        boolean isEmailAlert = handler.isEmailAlert();
        if (isEmailAlert) {
            sendEmail.sendEmail("asyncQueueExecutor队列溢出警告", message, handler.getEmailTo(), null);
        }
    }
}
