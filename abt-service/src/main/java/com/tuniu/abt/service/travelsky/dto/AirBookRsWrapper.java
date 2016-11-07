package com.tuniu.abt.service.travelsky.dto;

import java.util.List;

/**
 * 占位返回报文的再次封装
 * Created by chengyao on 2016/2/20.
 */
public class AirBookRsWrapper {

    // PNR
    private String pnrNo;

    // 航段状态
    private List<String> actionCodes;

    // 清位时间=不是航信自身的返回
    private String clearTime;

    // 航段的备注
    private List<String> comments;

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public List<String> getActionCodes() {
        return actionCodes;
    }

    public void setActionCodes(List<String> actionCodes) {
        this.actionCodes = actionCodes;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
