package com.tuniu.abt.service.issue.airline;

import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
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
 * Created by huangsizhou on 2016/2/26.
 */
@Service
public class AirlineIssueTask extends DefaultIssueTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineIssueTask.class);

    @Resource
    private IssueResultReporter issueResultReporter;

    @Resource
    private AirlineIssueService airlineIssueService;

    @Override
    protected IssueResult exec(Map<String, Object> params) {

        IssueRequest issueRequest = IssueDataSupport.getRequest();

        return airlineIssueService.issueTicket(issueRequest);

    }

    @Override
    protected void onSuccess(String callbackServiceName, IssueResult result) {

        if (result.isSuccess()) {
            success(result);
        }

    }

    public void success(IssueResult issueResult) {
        issueResultReporter.processCallback("", null);
    }


}
