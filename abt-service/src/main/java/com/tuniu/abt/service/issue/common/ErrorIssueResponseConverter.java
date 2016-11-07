package com.tuniu.abt.service.issue.common;

import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.dto.issue.response.PnrInfo;

import java.util.Map;

/**
 * Created by huangsizhou on 16/4/6.
 */
public class ErrorIssueResponseConverter {

    public static IssueResponse convertFrom(IssueRequest issueRequest, IssueResult issueResult) {
        IssueResponse issueResponse = new IssueResponse();
        issueResponse.getData().setFlightItemId(issueRequest.getIssueDetail().getFlightItemId());
        issueResponse.setIntl(0);
        issueResponse.setSessionId(issueRequest.getSessionId());
        issueResponse.setSystemId(issueRequest.getSystemId());
        issueResponse.setSuccess(false);

        for (Map.Entry<String, InnerIssueResultDto> entry : issueResult.getResultMap().entrySet()) {

            InnerIssueResultDto resultDto = entry.getValue();

            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setIssueFlag(resultDto.isSuccess());
            if (resultDto.isSuccess()) {
                pnrInfo.setMsg(resultDto.getMsg());
            }
            pnrInfo.setVendorId(issueRequest.getVendorId());
            pnrInfo.setOrderId(entry.getKey());
            pnrInfo.setNewOrderId(entry.getKey());
            pnrInfo.setErrorCode(resultDto.getErrorCode());
            issueResponse.addPnrInfo(pnrInfo);

        }

        return issueResponse;
    }
}
