package com.tuniu.abt.service.issue.ctrip;

import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.service.issue.AbstractIssueProcessor;
import com.tuniu.abt.service.issue.ctrip.module.CtripIssuePayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/3/25.
 */
@Service
public class CtripIssueProcessor extends AbstractIssueProcessor {

    @Resource
    private CtripIssuePayService ctripIssuePayService;

    @Override
    public IssueResult issue(IssueRequest issueRequest) {
        return ctripIssuePayService.issuePayByCtrip(issueRequest);
    }

}
