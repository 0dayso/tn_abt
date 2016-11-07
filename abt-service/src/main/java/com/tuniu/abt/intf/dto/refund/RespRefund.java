package com.tuniu.abt.intf.dto.refund;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/3/29.
 */
public class RespRefund implements Serializable {

    private static final long serialVersionUID = 3062543900122267069L;

    /**
     * 途牛订单号
     */
    private Long orderId;

    /**
     * 航程标识
     */
    private Long flightItemId;

    /**
     * 开放平台出票单ID
     */
    private Long aopTicketId;

    /**
     * 开放平台退票单ID
     */
    private Long aopRefundId;

    private List<RespRefundItem> items;

    private String solutionId;

    private String solutionName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public List<RespRefundItem> getItems() {
        return items;
    }

    public void setItems(List<RespRefundItem> items) {
        this.items = items;
    }

    public Long getAopTicketId() {
        return aopTicketId;
    }

    public void setAopTicketId(Long aopTicketId) {
        this.aopTicketId = aopTicketId;
    }

    public Long getAopRefundId() {
        return aopRefundId;
    }

    public void setAopRefundId(Long aopRefundId) {
        this.aopRefundId = aopRefundId;
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
