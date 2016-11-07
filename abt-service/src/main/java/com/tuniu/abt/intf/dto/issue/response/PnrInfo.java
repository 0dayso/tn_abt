package com.tuniu.abt.intf.dto.issue.response;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/21.
 */
public class PnrInfo {

    private boolean issueFlag;//订单开发给的要求,出票是否成功的标志

    private int errorCode;

    private String msg;

    private String orderId;

    private String newOrderId;

    private Integer vendorId;

    private String solutionId;

    private String solutionName;

    private List<TicketInfo> tickets = new LinkedList<TicketInfo>();

    private PriceDetail priceDetail = new PriceDetail();

    public boolean isIssueFlag() {
        return issueFlag;
    }

    public void setIssueFlag(boolean issueFlag) {
        this.issueFlag = issueFlag;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(String newOrderId) {
        this.newOrderId = newOrderId;
    }

    public Integer getVendorId() {
        return vendorId;
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

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public List<TicketInfo> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketInfo> tickets) {
        this.tickets = tickets;
    }

    public PriceDetail getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(PriceDetail priceDetail) {
        this.priceDetail = priceDetail;
    }

    public void addTicketInfo(TicketInfo ticketInfo) {
        tickets.add(ticketInfo);
    }
}
