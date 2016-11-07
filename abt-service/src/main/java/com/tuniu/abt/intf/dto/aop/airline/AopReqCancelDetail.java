package com.tuniu.abt.intf.dto.aop.airline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 取消详细信息
 * Created by chengyao on 2016/2/4.
 */
public class AopReqCancelDetail implements Serializable {

    private static final long serialVersionUID = -5291453404748796531L;

    /**
     * 请求标识
     */
    private Long flightItemId;

    /**
     * 待取消的pnr项目，可能也是携程的订单号等。
     */
    private List<AopReqCancelPnr> cancelPnrs;

    /**
     * 下单所使用的账号，供伪直连系统使用
     */
    private String account;

    /**
     * 假占位标志，如果是假占位的订单，无需取消
     */
    private Boolean fakeOrder;

    public List<AopReqCancelPnr> getCancelPnrs() {
        return cancelPnrs;
    }

    public void setCancelPnrs(List<AopReqCancelPnr> cancelPnrs) {
        this.cancelPnrs = cancelPnrs;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean getFakeOrder() {
        return fakeOrder;
    }

    public void setFakeOrder(Boolean fakeOrder) {
        this.fakeOrder = fakeOrder;
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }
}
