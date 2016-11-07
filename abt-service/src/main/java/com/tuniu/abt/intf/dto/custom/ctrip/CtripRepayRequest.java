package com.tuniu.abt.intf.dto.custom.ctrip;

import com.tuniu.adapter.flightTicket.vo.etdz.ResourceVo;

import java.io.Serializable;
import java.util.List;

/**
 * 携程二次支付请求
 *
 * Created by chengyao on 2016/3/8.
 */
public class CtripRepayRequest implements Serializable {

    private static final long serialVersionUID = -1591261912183828109L;

    private int orderId;

    private int sessionId;

    private List<ResourceVo> resource;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public List<ResourceVo> getResource() {
        return resource;
    }

    public void setResource(List<ResourceVo> resource) {
        this.resource = resource;
    }
}
