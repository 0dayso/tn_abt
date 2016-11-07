package com.tuniu.abt.intf.dto.cancel;

import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.entity.AbtPnrMain;

import java.io.Serializable;
import java.util.List;

/**
 * 取消下单的工作数据对象
 * Created by chengyao on 2016/3/17.
 */
public class ProcCancelData implements Serializable {

    private static final long serialVersionUID = 7081595950591025332L;

    // cancel 请求
    private ReqCancel reqCancel;

    // 占位请求表
    private AbtBookingRequest abtBookingRequest;

    // 所有可取消的占位项目
    private List<AbtPnrMain> availablePnrMains;

    // 需取消的占位项目
    private List<AbtPnrMain> pnrs;

    // 需取消主子订单
    private List<CancelOrderGroup> cancelOrderGroups;

    // 携程分组取消结果，或航信的pnr纬度取消结果
    private List<CancelResult> cancelResult;

    public List<CancelResult> getCancelResult() {
        return cancelResult;
    }

    public List<AbtPnrMain> getAvailablePnrMains() {
        return availablePnrMains;
    }

    public void setAvailablePnrMains(List<AbtPnrMain> availablePnrMains) {
        this.availablePnrMains = availablePnrMains;
    }

    public void setCancelResult(List<CancelResult> cancelResult) {
        this.cancelResult = cancelResult;
    }

    public ReqCancel getReqCancel() {
        return reqCancel;
    }

    public void setReqCancel(ReqCancel reqCancel) {
        this.reqCancel = reqCancel;
    }

    public AbtBookingRequest getAbtBookingRequest() {
        return abtBookingRequest;
    }

    public void setAbtBookingRequest(AbtBookingRequest abtBookingRequest) {
        this.abtBookingRequest = abtBookingRequest;
    }

    public List<AbtPnrMain> getPnrs() {
        return pnrs;
    }

    public void setPnrs(List<AbtPnrMain> pnrs) {
        this.pnrs = pnrs;
    }

    public List<CancelOrderGroup> getCancelOrderGroups() {
        return cancelOrderGroups;
    }

    public void setCancelOrderGroups(List<CancelOrderGroup> cancelOrderGroups) {
        this.cancelOrderGroups = cancelOrderGroups;
    }
}
