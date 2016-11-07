package com.tuniu.abt.intf.dto.ticket;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 刷新票号请求
 * Created by chengyao on 2016/3/4.
 */
public class RefreshTicketInfoRequest implements Serializable {

    private static final long serialVersionUID = 88752486127056975L;

    /**
     * 订单号
     */
    @NotNull(message = "{RefreshTicketInfoRequest.orderId.notEmpty}")
    private Long orderId;

    /**
     * 请求ID
     */
    @NotNull(message = "{RefreshTicketInfoRequest.flightItemId.notEmpty}")
    private Long flightItemId;

    /**
     * 请求pnr
     */
    @NotEmpty(message = "{RefreshTicketInfoRequest.pnrList.notEmpty}")
    @Valid
    private List<PnrData> pnrList;

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

    public List<PnrData> getPnrList() {
        return pnrList;
    }

    public void setPnrList(List<PnrData> pnrList) {
        this.pnrList = pnrList;
    }
}
