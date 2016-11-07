package com.tuniu.abt.service.issue.travelsky;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
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
 * Created by chengyao on 2016/2/26.
 */
@Service
public class TravelSkyIssueTask extends DefaultIssueTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyIssueTask.class);

    @Resource
    private TravelSkyIssueService travelSkyIssueService;

    @Resource
    private IssueResultReporter issueResultReporter;

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Override
    protected IssueResult exec(Map<String, Object> params) {
        IssueRequest issueRequest = IssueDataSupport.getRequest();
        return travelSkyIssueService.issueTicket(issueRequest);
    }

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return IssueDataSupport.getRequest();
    }

    @Override
    protected Object traceOutput(IssueResult result, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return IssueDataSupport.getData();
    }

    @Override
    protected void onSuccess(String callbackServiceName, IssueResult result) {

        try {
            if (result.isSuccess()) {
                success();
            }
            AbtTicketRequest abtTicketRequest = result.getInnerData();

            abtTicketRequestDao.updateStatusAndCallbackStatusById(AbtTicketRequest.ISSUE_SUCCESS, BizzConstants.ISSUE_CALLBACK_YES, abtTicketRequest.getId());
        } catch (Exception e) {
            LOGGER.error("处理航信出票返回失败", e);
        }
    }

    public void success() {
        IssueRequest issueRequest = IssueDataSupport.getRequest();

        IssueResponse issueResponse = IssueDataSupport.getData();

        try {
            issueResultReporter.processCallback(issueRequest.getCallback(), issueResponse);
        } catch (Exception ex) {
            LOGGER.error("反馈失败", ex);
        }

    }

}
