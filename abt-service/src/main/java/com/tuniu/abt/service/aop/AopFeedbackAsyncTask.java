package com.tuniu.abt.service.aop;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.abt.service.change.aop.AopChangeApplyService;
import com.tuniu.abt.service.commons.FeedbackReporter;
import com.tuniu.abt.service.refund.aop.AopRefundApplyService;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import com.tuniu.abt.service.issue.aop.AopIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 处理开放平台操作结果异步服务
 *
 * Created by chengyao on 2016/3/26.
 */
@Service
public class AopFeedbackAsyncTask extends SplitTracerAsyncTask<Object, Map<String, Object>> {

    private static final long serialVersionUID = -3768908266402476451L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AopFeedbackAsyncTask.class);

    public static final String REQUEST_KEY = "AopFeedbackRequest";

    @Resource
    private AopRefundApplyService aopRefundApplyService;
    @Resource
    private AopChangeApplyService aopChangeApplyService;
    @Resource
    private FeedbackReporter feedbackReporter;
    @Resource
    private AopIssueService aopIssueService;

    @Override
    protected Object exec(Map<String, Object> params) {
        AopFeedbackRequest request = (AopFeedbackRequest) params.get(REQUEST_KEY);

        int opType = request.getOpType();

        // 拿到请求，出票、退票、改期结果处理，回调票务结果
        switch (opType) {
            case AopFeedbackRequest.OP_TYPE_REFUND_SUCCESS:
            case AopFeedbackRequest.OP_TYPE_REFUND_FAIL:
                return aopRefundApplyService.workWithFeedback(request);
            case AopFeedbackRequest.OP_TYPE_TICKET_SUCCESS:
            case AopFeedbackRequest.OP_TYPE_TICKET_FAIL:
                return aopIssueService.handleIssueResult(request);
            case AopFeedbackRequest.OP_TYPE_CHANGE_SUCCESS:
            case AopFeedbackRequest.OP_TYPE_CHANGE_FAIL:
                return aopChangeApplyService.workWithFeedback(request);
            default:
                break;
        }

        return null;
    }

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return param.get(REQUEST_KEY);
    }

    @Override protected Object traceOutput(Object result, FlatTraceInfo tracerCommand) {
        return result;
    }

    @Override protected void onSuccess(String callback, Object result) {
        FeedbackReporter.Type type = findType();
        if (FeedbackReporter.Type.ISSUE.equals(type)) {
            feedbackReporter.success(DataSharedSupport.getCallback(), result);
        } else {
            feedbackReporter.success(type, result);
        }
        super.onSuccess(callback, result);
    }

    @Override protected void onException(String callback, Exception ex) {
        super.onException(callback, ex);
        FeedbackReporter.Type type = findType();
        if (FeedbackReporter.Type.ISSUE.equals(type)) {
            feedbackReporter.exception(DataSharedSupport.getCallback(), null, ex);
        } else {
            feedbackReporter.exception(type, null, ex);
        }
    }

    private FeedbackReporter.Type findType() {
        AopFeedbackRequest request = DataSharedSupport.get(REQUEST_KEY);
        int opType = request.getOpType();
        FeedbackReporter.Type type = FeedbackReporter.Type.ISSUE;
        switch (opType) {
        case AopFeedbackRequest.OP_TYPE_REFUND_SUCCESS:
        case AopFeedbackRequest.OP_TYPE_REFUND_FAIL:
            type = FeedbackReporter.Type.REFUND;
            break;
        case AopFeedbackRequest.OP_TYPE_TICKET_SUCCESS:
        case AopFeedbackRequest.OP_TYPE_TICKET_FAIL:
            type = FeedbackReporter.Type.ISSUE;
            break;
        case AopFeedbackRequest.OP_TYPE_CHANGE_SUCCESS:
        case AopFeedbackRequest.OP_TYPE_CHANGE_FAIL:
            type = FeedbackReporter.Type.CHANGE;
            break;
        default:
            break;
        }
        return type;
    }
}