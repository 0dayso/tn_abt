package com.tuniu.abt.intf.dto.book.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookingResData implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -4091366880777901160L;

    private Long flightItemId;
    
    private String remark;
    
    private List<OrderInfo> orderInfoList;

    /**
     * 是否支持部分取消占位
     */
    private boolean partialCancellation;

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

    public List<OrderInfo> getOrderInfoList() {
        if (null == orderInfoList) {
            orderInfoList = new ArrayList<OrderInfo>();
        }
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public boolean isPartialCancellation() {
        return partialCancellation;
    }

    public void setPartialCancellation(boolean partialCancellation) {
        this.partialCancellation = partialCancellation;
    }
}
