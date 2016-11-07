package com.tuniu.abt.intf.dto.issue.response;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.validator.group.CtripIssueScene;
import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class IssueResult {

    private boolean success = true;

    private int errorCode = BizzEx.SUCCESS;

    private String msg;

    private String sessionId;

    private int systemId;

    private int intl;

    private int issueStatus;

    private Object innerData; //为了保存一些请求的中间转化信息,如最初请求参数 到 实际 支付请求参数

    @NotEmpty.List({
            @NotEmpty(message = "{notEmpty.issueResult.ctrip.resultMap}", groups = {CtripIssueScene.class}),
            @NotEmpty(message = "{notEmpty.issueResult.resultMap}")
    })
    private Map<String, InnerIssueResultDto> resultMap = new HashedMap<String, InnerIssueResultDto>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }

    public int getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(int issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Map<String, InnerIssueResultDto> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, InnerIssueResultDto> resultMap) {
        this.resultMap = resultMap;
    }

    public void addResult(String id, InnerIssueResultDto dto) {
        this.resultMap.put(id, dto);
    }

    public void setInnerData(Object innerData) {
        this.innerData = innerData;
    }

    public <T> T getInnerData() {
        return (T) this.innerData;
    }
}


