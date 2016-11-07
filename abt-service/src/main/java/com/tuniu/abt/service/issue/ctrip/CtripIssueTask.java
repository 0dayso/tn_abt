package com.tuniu.abt.service.issue.ctrip;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.dto.issue.response.PnrInfo;
import com.tuniu.abt.service.issue.DefaultIssueTask;
import com.tuniu.abt.service.issue.IssueDataSupport;
import com.tuniu.abt.service.issue.IssueResultReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 携程出票
 * Created by huangsizhou on 2016/3/21.
 */
@Service
public class CtripIssueTask extends DefaultIssueTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripIssueTask.class);

    @Resource
    private CtripIssueService ctripIssueService;

    @Resource
    private IssueResultReporter issueResultReporter;

    @Override
    protected IssueResult exec(Map<String, Object> params) {

        IssueRequest issueRequest = IssueDataSupport.getRequest();

        return ctripIssueService.issueTicket(issueRequest);

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
    protected void onSuccess(String callbackServiceName, IssueResult result) {
        success(result);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        super.onException(callbackServiceName, ex);
    }

    public void success(IssueResult result) {

        IssueRequest issueRequest = IssueDataSupport.getRequest();

        if (!result.isSuccess()) {
            IssueResponse issueResponse = new IssueResponse();
            issueResponse.setSuccess(false);
            issueResponse.setSessionId(issueRequest.getSessionId());
            issueResponse.setIntl(0);
            issueResponse.setSystemId(issueRequest.getSystemId());
            issueResponse.setErrorCode(result.getErrorCode());
            issueResponse.setOrderId(issueRequest.getOrderId());
            issueResponse.getData().setFlightItemId(issueRequest.getIssueDetail().getFlightItemId());

            Map<String, InnerIssueResultDto> resultMap = result.getResultMap();
            StringBuilder errorMsg = new StringBuilder();

            for (Map.Entry<String, InnerIssueResultDto> entry : resultMap.entrySet()) {
                PnrInfo pnrInfo = new PnrInfo();
                pnrInfo.setOrderId(entry.getKey());
                pnrInfo.setNewOrderId(entry.getKey());
                pnrInfo.setErrorCode(entry.getValue().getErrorCode());
                pnrInfo.setMsg(entry.getValue().getMsg());
                pnrInfo.setIssueFlag(false);
                issueResponse.addPnrInfo(pnrInfo);
                errorMsg.append(entry.getValue().getMsg()).append(";");
            }
            issueResponse.setMsg(errorMsg.toString());

            issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);
        }
    }

    /*public void exception(Exception ex) {

        IssueRequest issueRequest = IssueDataSupport.getRequest();
        IssueResponse issueResponse = new IssueResponse();

        if (ex instanceof MethodConstraintViolationException) {
            MethodConstraintViolationException validationEx = (MethodConstraintViolationException) ex;
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation violation : validationEx.getConstraintViolations()) {
                errorMsg.append(violation.getMessage()).append(";");
            }
            issueResponse.setMsg(errorMsg.toString());
        } else {
            issueResponse.setMsg(exceptionMessageUtils.findWrappedMessage(ex));
        }

        issueResponse.setSessionId(issueRequest.getSessionId());
        issueResponse.setIntl(0);
        issueResponse.setSystemId(issueRequest.getSystemId());
        issueResponse.setErrorCode(exceptionMessageUtils.findCode(ex));
        issueResponse.setSuccess(false);

        issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);
        //错误的时候要不要存储已反馈状体?TODO HSZ

        super.onException(ex);
    }
*/
}
