package com.tuniu.abt.service.issue;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.dto.issue.response.IssueResultDetail;
import com.tuniu.abt.intf.dto.issue.response.PnrInfo;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.commons.FeedbackReporter;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by huangsizhou on 16/4/6.
 */
public abstract class DefaultIssueTask extends SplitTracerAsyncTask<IssueResult, Map<String, Object>> {

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private FeedbackReporter feedbackReporter;

    @Override
    protected abstract IssueResult exec(Map<String, Object> param) throws Exception;

    @Override
    protected void onSuccess(String callbackServiceName, IssueResult result) {

    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {

        IssueRequest issueRequest = IssueDataSupport.getRequest();

        IssueResultDetail issueResultDetail = new IssueResultDetail();
        issueResultDetail.setFlightItemId(issueRequest.getIssueDetail().getFlightItemId());

        for (OrderInfo orderId : issueRequest.getIssueDetail().getOrderIds()) {
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setIssueFlag(false);
            pnrInfo.setErrorCode(exceptionMessageUtils.findCode(ex));
            pnrInfo.setMsg(exceptionMessageUtils.findWrappedMessage(ex));
            pnrInfo.setOrderId(orderId.getPnr());
            pnrInfo.setNewOrderId(orderId.getPnr());
            pnrInfo.setVendorId(issueRequest.getVendorId());
            issueResultDetail.getPnrInfoList().add(pnrInfo);
        }

        try {
            feedbackReporter.exception(issueRequest.getCallback(), issueResultDetail, ex);
        } finally {
            super.onException(callbackServiceName, ex);
            abtTicketRequestDao.updateStatusAndCallbackStatus(AbtTicketRequest.ISSUE_FAIL, BizzConstants.ISSUE_CALLBACK_YES, issueRequest.getSessionId(), issueRequest.getIssueDetail().getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
        }

    }

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return IssueDataSupport.getRequest();
    }

    @Override
    protected Object traceOutput(IssueResult result, FlatTraceInfo tracerCommand) {
        return IssueDataSupport.getData();
    }

    @Override
    protected void beforeExecute(String callbackServiceName, Map<String, Object> param) {
        super.beforeExecute(callbackServiceName, param);
    }
}
