package com.tuniu.abt.intf.dto.order.tsp;

import java.io.Serializable;

/**
 * 获取待办数量服务 返回结果
 *
 * Created by chengyao on 2015/12/17.
 */
public class ToDoCountResp implements Serializable {

    private static final long serialVersionUID = 3554124255696480342L;

    /**
     * 待处理出票单数量
     */
    private int ticketCount;

    /**
     * 待处理退票单数量
     */
    private int refundCount;

    /**
     * 待处理改期单数量
     */
    private int changeCount;

    public ToDoCountResp() {
    }

    public ToDoCountResp(int ticketCount, int refundCount, int changeCount) {
        this.ticketCount = ticketCount;
        this.refundCount = refundCount;
        this.changeCount = changeCount;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
}
