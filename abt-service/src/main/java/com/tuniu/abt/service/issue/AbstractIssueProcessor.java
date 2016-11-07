package com.tuniu.abt.service.issue;

import com.tuniu.abt.converter.IssueRequestToTicketRequestConverter;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtTicketRequest;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/3/24.
 */

public abstract class AbstractIssueProcessor implements IssueProcessor {

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private IssueRequestToTicketRequestConverter issueRequestToTicketRequestConverter;

    @Override
    public IssueResult issueTicket(IssueRequest issueRequest) {

        AbtTicketRequest abtTicketRequest = issueRequestToTicketRequestConverter.convert(issueRequest);
        abtTicketRequestDao.save(abtTicketRequest);

        IssueResult issueResult = issue(issueRequest);
        issueResult.setInnerData(abtTicketRequest);

        IssueDataSupport.setData(issueResult);

        abtTicketRequestDao.updateStatusById(issueResult.getIssueStatus(), abtTicketRequest.getId());
        return issueResult;

    }

    protected abstract IssueResult issue(IssueRequest issueRequest);

}
