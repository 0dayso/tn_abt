package com.tuniu.abt.service.change;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.service.commons.FeedbackReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 退票异步执行任务
 * Created by chengyao on 2016/3/25.
 */
@Service
public class ChangeAsyncTask extends SplitTracerAsyncTask<Object, Map<String, Object>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeAsyncTask.class);

    private static final long serialVersionUID = 2802616440276511716L;

    @Resource
    private ChangeService changeService;
    @Resource
    private ChangePrepareService changePrepareService;
    @Resource
    private FeedbackReporter feedbackReporter;

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return ChangeDataSupport.getData();
    }

    @Override
    protected Object traceOutput(Object result, FlatTraceInfo tracerCommand) {
        return result;
    }

    @Override
    protected Object exec(Map<String, Object> param) throws Exception {
        ProcChangeData changeData = ChangeDataSupport.getData();

        changePrepareService.checkAndPrepareData(changeData.getReqChange());

        return changeService.applyChange(ChangeDataSupport.getData());
    }

    @Override
    protected void onSuccess(String callbackServiceName, Object result) {
        feedbackReporter.success(FeedbackReporter.Type.CHANGE, result);
        super.onSuccess(callbackServiceName, result);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        super.onException(callbackServiceName, ex);
        feedbackReporter.exception(FeedbackReporter.Type.CHANGE, null, ex);
    }

}
