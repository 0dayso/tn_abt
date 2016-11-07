package com.tuniu.abt.intf.dto.cancel;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单组对象，标识有关系的一组订单（PNR）
 * Created by chengyao on 2016/3/18.
 */
public class CancelOrderGroup implements Serializable {

    private static final long serialVersionUID = 9185437359876605058L;

    // 订单号
    private String mainOrderId;

    // 航司直连，航司编码
    private String ticketingCarrier;

    // 航信，pnr主表信息
    private AbtPnrMain pnrMain;

    // 航信，该pnr的备注pnr
    private String refOrderId;

    // 航信，要取消的乘客信息
    @JsonIgnore
    @JSONField(serialize = false)
    private List<AbtPnrPassenger> passengers;

    // 航信，是否是取消pnr，或者取消其中的乘客
    @JsonIgnore
    @JSONField(serialize = false)
    private boolean isCancelPnr;

    // 航信，所有的乘客信息
    @JsonIgnore
    @JSONField(serialize = false)
    private List<AbtPnrPassenger> allPassengers;

    // 携程，关联订单
    private List<String> relationOrderIds = new ArrayList<String>();

    // 携程，所有订单号
    @JsonIgnore
    @JSONField(serialize = false)
    private List<String> orderIds = new ArrayList<String>();

    public AbtPnrMain getPnrMain() {
        return pnrMain;
    }

    public void setPnrMain(AbtPnrMain pnrMain) {
        this.pnrMain = pnrMain;
    }

    public List<AbtPnrPassenger> getAllPassengers() {
        return allPassengers;
    }

    public void setAllPassengers(List<AbtPnrPassenger> allPassengers) {
        this.allPassengers = allPassengers;
    }

    public boolean isCancelPnr() {
        return isCancelPnr;
    }

    public void setCancelPnr(boolean cancelPnr) {
        isCancelPnr = cancelPnr;
    }

    public List<AbtPnrPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<AbtPnrPassenger> passengers) {
        this.passengers = passengers;
    }

    public String getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(String refOrderId) {
        this.refOrderId = refOrderId;
    }

    public String getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(String mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public List<String> getRelationOrderIds() {
        return relationOrderIds;
    }

    public void setRelationOrderIds(List<String> relationOrderIds) {
        this.relationOrderIds = relationOrderIds;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier;
    }
}
