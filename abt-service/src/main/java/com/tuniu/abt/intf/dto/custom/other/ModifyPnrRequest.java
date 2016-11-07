package com.tuniu.abt.intf.dto.custom.other;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/3/10.
 */
public class ModifyPnrRequest implements Serializable {
    private static final long serialVersionUID = -8198840767031403147L;

    private Integer orderId;

    private String tel;

    private Integer systemId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }
}
