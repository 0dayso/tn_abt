package com.tuniu.abt.service.issue.airline;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.intf.airline.AirDirectConnectTicketingResponse;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.service.issue.IssueDataSupport;
import com.tuniu.abt.service.issue.IssueService;
import com.tuniu.abt.service.issue.common.ErrorIssueResponseConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 2016/03/09.
 */
@Service
public class AirlineIssueService implements IssueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineIssueService.class);

    @Resource
    private AirlineIssueProcessor airlineIssueProcessor;

    @Resource
    private ConversionService conversionService;

    @CommandTrace(name = TracerCmdEnum.ACTION_ASYNC, type = "出票", input = "#issueRequest", output = "T(com.tuniu.abt.service.confirm.ConfirmDataSupport).getData()")
    @Override
    public IssueResult issueTicket(IssueRequest issueRequest) {
        IssueResult issueResult = airlineIssueProcessor.issue(issueRequest);

        handleIssueResult(issueRequest, issueResult);

        return issueResult;
    }

    private void handleIssueResult(IssueRequest issueRequest, IssueResult issueResult) {

        IssueResponse issueResponse;
        if (!issueResult.isSuccess()) {
            issueResponse = ErrorIssueResponseConverter.convertFrom(issueRequest, issueResult);
        } else {
            AirDirectConnectTicketingResponse airDirectConnectTicketingResponse = issueResult.getInnerData();
            issueResponse = conversionService.convert(airDirectConnectTicketingResponse, IssueResponse.class);
            issueResponse.getData().setFlightItemId(issueRequest.getIssueDetail().getFlightItemId());
            issueResponse.setIntl(0);
            issueResponse.setSessionId(issueRequest.getSessionId());
            issueResponse.setSystemId(issueRequest.getSystemId());
            issueResponse.setSuccess(true);
        }

        IssueDataSupport.setData(issueResponse);
    }
}
