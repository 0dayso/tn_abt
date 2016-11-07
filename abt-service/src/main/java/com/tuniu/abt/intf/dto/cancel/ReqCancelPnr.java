package com.tuniu.abt.intf.dto.cancel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * 待取消的PNR项目
 *
 * Created by chengyao on 2016/3/17.
 */
public class ReqCancelPnr implements Serializable {

    private static final long serialVersionUID = -1429587089608900265L;

    /**
     * 要取消的订单号，该订单号是供应商提供的订单号，如果是 API 接口一般为 PNR，如果是伪直连，可能是 PNR，也可能是航司提供的订单号
     */
    @JsonProperty("vendorOrderId")
    @NotEmpty(message = "{cancel.cancelPnrs.orderId.notEmpty}")
    private String orderId;

    /**
     * 航信：如果要取消pnr中的某个乘客，填充此项目
     */
    private List<PassengerInfo> passengers;

    public List<PassengerInfo> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerInfo> passengers) {
        this.passengers = passengers;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
