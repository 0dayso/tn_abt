package com.tuniu.abt.intf.dto.cancel;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;

import java.io.Serializable;
import java.util.List;

/**
 * 取消订单操作结果
 */
public class CancelResult implements Serializable {
    private static final long serialVersionUID = -6316103095944637839L;

    /**
     * 取消结果 成功 true, 失败 false
     */
    private boolean success = true;

    /**
     * 取消的pnr（供应商订单号）
     */
    @JsonProperty("vendorOrderId")
    @JSONField(name = "vendorOrderId")
    private String cancelPnr;

    /**
     * 结果说明
     */
    private String remark;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 已取消的乘客（部分取消时返回）
     */
    private List<PassengerInfo> passengers;

    /**
     * 订单组（携程用）
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private CancelOrderGroup cancelOrderGroup;

    /**
     * 取消的乘客
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private List<AbtPnrPassenger> cancelPassengers;

    /**
     * 处理时异常,为空标识操作成功
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Throwable throwable;

    public CancelResult() {

    }

    public CancelResult(CancelOrderGroup cancelOrderGroup, Throwable throwable) {
        this.cancelOrderGroup = cancelOrderGroup;
        this.throwable = throwable;
    }

    public List<AbtPnrPassenger> getCancelPassengers() {
        return cancelPassengers;
    }

    public void setCancelPassengers(List<AbtPnrPassenger> cancelPassengers) {
        this.cancelPassengers = cancelPassengers;
    }

    public String getCancelPnr() {
        return cancelPnr;
    }

    public void setCancelPnr(String cancelPnr) {
        this.cancelPnr = cancelPnr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public CancelOrderGroup getCancelOrderGroup() {
        return cancelOrderGroup;
    }

    public void setCancelOrderGroup(CancelOrderGroup cancelOrderGroup) {
        this.cancelOrderGroup = cancelOrderGroup;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PassengerInfo> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerInfo> passengers) {
        this.passengers = passengers;
    }
}