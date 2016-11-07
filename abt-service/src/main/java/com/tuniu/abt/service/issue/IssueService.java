package com.tuniu.abt.service.issue;

import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;

/**
 * Created by huangsizhou on 16/3/22.
 */
public interface IssueService {

    IssueResult issueTicket(IssueRequest issueRequest);

}
