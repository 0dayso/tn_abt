package com.tuniu.abt.intf.dto.issue.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class IssueResultDetail {

    private Long flightItemId;

    private String remark;

    private String aopTicketId;//开放平台ticketid

    private List<PnrInfo> pnrInfoList = new ArrayList<PnrInfo>();

    public IssueResultDetail(Long flightItemId, String remark, List<PnrInfo> pnrInfoList) {
        this.flightItemId = flightItemId;
        this.remark = remark;
        this.pnrInfoList = pnrInfoList;
    }

    public IssueResultDetail(Long flightItemId, String remark, String aopTicketId, List<PnrInfo> pnrInfoList) {
        this.flightItemId = flightItemId;
        this.remark = remark;
        this.aopTicketId = aopTicketId;
        this.pnrInfoList = pnrInfoList;
    }

    public IssueResultDetail() {
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAopTicketId() {
        return aopTicketId;
    }

    public void setAopTicketId(String aopTicketId) {
        this.aopTicketId = aopTicketId;
    }

    public List<PnrInfo> getPnrInfoList() {
        return pnrInfoList;
    }

    public void setPnrInfoList(List<PnrInfo> pnrInfoList) {
        this.pnrInfoList = pnrInfoList;
    }

}
