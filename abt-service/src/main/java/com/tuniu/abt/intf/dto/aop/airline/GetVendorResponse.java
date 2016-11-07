package com.tuniu.abt.intf.dto.aop.airline;

/**
 * Created by huanggao on 2016/5/3.
 */
public class GetVendorResponse {
    /**
     * 是否是伪直连  true:是，false:不是
     */
    private boolean isDirectToAirLine;

    /**
     * 1表示普通政策，2表示特殊政策，3表示伪直连，4其他，0无意义
     */
    private int policyType;

    public boolean isDirectToAirLine() {
        return isDirectToAirLine;
    }

    public void setDirectToAirLine(boolean isDirectToAirLine) {
        this.isDirectToAirLine = isDirectToAirLine;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }
}
