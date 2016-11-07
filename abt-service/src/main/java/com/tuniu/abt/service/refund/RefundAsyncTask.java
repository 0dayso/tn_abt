package com.tuniu.abt.service.refund;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
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
public class RefundAsyncTask extends SplitTracerAsyncTask<Object, Map<String, Object>> {

    private static final long serialVersionUID = 5507694798936794601L;

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundAsyncTask.class);

    @Resource
    private RefundService refundService;
    @Resource
    private RefundPrepareService refundPrepareService;
    @Resource
    private FeedbackReporter feedbackReporter;

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return RefundDataSupport.getData();
    }

    @Override
    protected Object traceOutput(Object result, FlatTraceInfo tracerCommand) {
        return result;
    }

    @Override
    protected Object exec(Map<String, Object> param) throws Exception {
        ProcRefundData refundData = RefundDataSupport.getData();

        refundPrepareService.checkAndPrepareData(refundData.getReqRefund());

        return refundService.applyRefund(RefundDataSupport.getData());
    }

    @Override
    protected void onSuccess(String callbackServiceName, Object result) {
        feedbackReporter.success(FeedbackReporter.Type.REFUND, result);
        super.onSuccess(callbackServiceName, result);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        super.onException(callbackServiceName, ex);
        feedbackReporter.exception(FeedbackReporter.Type.REFUND, null, ex);
    }

}
