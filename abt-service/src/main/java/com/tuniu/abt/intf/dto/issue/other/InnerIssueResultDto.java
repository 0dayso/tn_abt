package com.tuniu.abt.intf.dto.issue.other;

/**
 * Created by lanlugang on 2016/5/23.
 */
public class InnerIssueResultDto {

    private boolean success = true;

    private int errorCode;

    private String msg = "";

    private String solutionId;

    private String solutionName;

    public InnerIssueResultDto() {
    }

    public InnerIssueResultDto(String solutionId, String solutionName) {
        this.solutionId = solutionId;
        this.solutionName = solutionName;
    }

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

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }
}
