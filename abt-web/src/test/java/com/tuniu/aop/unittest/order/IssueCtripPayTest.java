package com.tuniu.aop.unittest.order;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.dto.issue.request.IssueDetail;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.service.issue.ctrip.module.CtripIssuePayService;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lanlugang on 2016/5/24.
 */
public class IssueCtripPayTest extends BaseTest {

    @Resource
    private CtripIssuePayService ctripIssuePayService;

    @Test
    public void testIssuePayByCtrip() {
        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setOrderId("410019492005");
        issueRequest.setIssueDetail(new IssueDetail());
        issueRequest.getIssueDetail().setFlightItemId(2000047040L);
        try {
            IssueResult issueResult = ctripIssuePayService.issuePayByCtrip(issueRequest);
            System.out.println(JSON.toJSONString(issueResult));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
