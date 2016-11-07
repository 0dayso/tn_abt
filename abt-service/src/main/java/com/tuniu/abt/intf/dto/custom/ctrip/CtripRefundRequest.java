package com.tuniu.abt.intf.dto.custom.ctrip;

import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundOrderFlight;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundOrderRequest;

import java.util.List;

/**
 * Created by chengyao on 2016/3/28.
 */
public class CtripRefundRequest extends RefundOrderRequest {

    // use orderID / recievedSegment / segementPosition / refundFee / remark

    private String refundType;

    private List<RefundOrderFlight> items;

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public boolean isRecievedSegment() {
        return recievedSegment;
    }

    public void setRecievedSegment(boolean recievedSegment) {
        this.recievedSegment = recievedSegment;
    }

    public String getSegementPosition() {
        return segementPosition;
    }

    public void setSegementPosition(String segementPosition) {
        this.segementPosition = segementPosition;
    }

    public List<RefundOrderFlight> getItems() {
        return items;
    }

    public void setItems(List<RefundOrderFlight> items) {
        this.items = items;
    }
}
