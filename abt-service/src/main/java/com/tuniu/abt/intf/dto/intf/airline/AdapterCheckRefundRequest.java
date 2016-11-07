package com.tuniu.abt.intf.dto.intf.airline;

import java.util.List;

public class AdapterCheckRefundRequest {

    private int vendorId;

    private List<String> vendorOrderId;

    // 退票类型 100=自愿， 200=非自愿
    private String refundType;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public List<String> getVendorOrderId() {
        return vendorOrderId;
    }

    public void setVendorOrderId(List<String> vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

}
