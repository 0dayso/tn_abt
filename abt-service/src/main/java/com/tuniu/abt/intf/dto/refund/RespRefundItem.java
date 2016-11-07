package com.tuniu.abt.intf.dto.refund;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by chengyao on 2016/3/29.
 */
public class RespRefundItem implements Serializable {

    private static final long serialVersionUID = -3408618264297176230L;

    private BigDecimal refundAmount;

    private BigDecimal subsidy;

    private String ticketNo;

    // NO USE
    private List<Object> segments;

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(BigDecimal subsidy) {
        this.subsidy = subsidy;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public List<Object> getSegments() {
        return segments;
    }

    public void setSegments(List<Object> segments) {
        this.segments = segments;
    }
}
