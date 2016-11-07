package com.tuniu.abt.service.cancel;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import com.tuniu.abt.service.commons.FeedbackReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 取消占位 任务
 * Created by chengyao on 2016/1/29.
 */
@Service
public class CancelAsyncTask extends SplitTracerAsyncTask<Object, Map<String, Object>> {

    private static final long serialVersionUID = 5507694798936794601L;

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelAsyncTask.class);

    @Resource
    private CancelService cancelService;
    @Resource
    private FeedbackReporter feedbackReporter;

    @Override
    protected Object exec(Map<String, Object> params) {
        ProcCancelData cancelData = CancelDataSupport.getData();
        return cancelService.cancel(cancelData);
    }

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return CancelDataSupport.getData();
    }

    @Override
    protected Object traceOutput(Object result, FlatTraceInfo tracerCommand) {
        return result;
    }

    @Override
    protected void onSuccess(String callbackServiceName, Object result) {
        super.onSuccess(callbackServiceName, result);
        feedbackReporter.success(callbackServiceName, result);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        feedbackReporter.exception(callbackServiceName, null, ex);
        super.onException(callbackServiceName, ex);
    }

}
