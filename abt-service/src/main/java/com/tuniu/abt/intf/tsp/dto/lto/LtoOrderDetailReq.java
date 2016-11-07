package com.tuniu.abt.intf.tsp.dto.lto;

/**
 * Created by chengyao on 2016/1/19.
 */
public class LtoOrderDetailReq {

    private String orderId;

    private String bookOrderId;

    public String getBookOrderId() {
        return bookOrderId;
    }

    public void setBookOrderId(String bookOrderId) {
        this.bookOrderId = bookOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
