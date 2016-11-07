package com.tuniu.abt.intf.dto.aop.airline;

import java.util.List;

/**
 * Created by huanggao on 2016/4/25.
 */
public class AopBookingResult {

    private String flightItemId;

    private String remark;

    private List<AopBookingOrderInfo> orderInfoList;

    public String getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(String flightItemId) {
        this.flightItemId = flightItemId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<AopBookingOrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<AopBookingOrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
