package com.tuniu.abt.service.issue.aop;

import com.tuniu.abt.converter.IssueRequestToAopTicketSyncRequestConverter;
import com.tuniu.abt.intf.dto.aop.AopTicketSyncRequest;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.aop.AopTicketSyncService;
import com.tuniu.abt.service.issue.AbstractIssueProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class AopIssueProcessor extends AbstractIssueProcessor {

    @Resource
    private AopTicketSyncService aopTicketSyncService;

    @Resource
    private IssueRequestToAopTicketSyncRequestConverter issueRequestToAopTicketSyncRequestConverter;

    @Override
    protected IssueResult issue(IssueRequest issueRequest) {

        AopTicketSyncRequest aopTicketSyncRequest = issueRequestToAopTicketSyncRequestConverter.convert(issueRequest);
        aopTicketSyncService.sendTicket(aopTicketSyncRequest);//请求AOP


        IssueResult issueResult = new IssueResult();
        issueResult.setSessionId(issueRequest.getSessionId());
        issueResult.setIssueStatus(AbtTicketRequest.ISSUE_WAITING);

        return issueResult;
    }
}
