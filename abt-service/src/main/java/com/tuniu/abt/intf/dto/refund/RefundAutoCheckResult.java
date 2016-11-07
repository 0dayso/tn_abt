package com.tuniu.abt.intf.dto.refund;

import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.QueryFlightOrderRefundVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/3/3.
 */
public class RefundAutoCheckResult implements Serializable {

    private static final long serialVersionUID = -3847890239000854553L;

    private boolean singleRefundFlag;

    private String remark;

    private boolean refundFlag;

    private String subOrderIdStr;

    private int orderId;

    private List<QueryFlightOrderRefundVo> refundInfoList = new ArrayList<QueryFlightOrderRefundVo>();

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSingleRefundFlag() {
        return singleRefundFlag;
    }

    public void setSingleRefundFlag(boolean singleRefundFlag) {
        this.singleRefundFlag = singleRefundFlag;
    }

    public boolean isRefundFlag() {
        return refundFlag;
    }

    public void setRefundFlag(boolean refundFlag) {
        this.refundFlag = refundFlag;
    }

    public String getSubOrderIdStr() {
        return subOrderIdStr;
    }

    public void setSubOrderIdStr(String subOrderIdStr) {
        this.subOrderIdStr = subOrderIdStr;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<QueryFlightOrderRefundVo> getRefundInfoList() {
        return refundInfoList;
    }

    public void setRefundInfoList(List<QueryFlightOrderRefundVo> refundInfoList) {
        this.refundInfoList = refundInfoList;
    }
}
