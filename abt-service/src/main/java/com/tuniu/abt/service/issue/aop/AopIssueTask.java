package com.tuniu.abt.service.issue.aop;

import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.service.issue.DefaultIssueTask;
import com.tuniu.abt.service.issue.IssueDataSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class AopIssueTask extends DefaultIssueTask{

    @Resource
    private  AopIssueService aopIssueService;

    @Override
    protected IssueResult exec(Map<String, Object> param) throws Exception {

        IssueRequest issueRequest = IssueDataSupport.getRequest();

        return aopIssueService.issueTicket(issueRequest);
    }

}
