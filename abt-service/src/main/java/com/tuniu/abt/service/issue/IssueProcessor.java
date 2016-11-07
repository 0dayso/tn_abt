package com.tuniu.abt.service.issue;

import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;

/**
 * Created by huangsizhou on 16/3/8.
 */
public interface IssueProcessor {

    IssueResult issueTicket(IssueRequest issueRequest);

}
