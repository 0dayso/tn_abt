package com.tuniu.abt.intf.dto.change;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/5/3.
 */
public class RespChangeCtrip implements Serializable {

    private static final long serialVersionUID = -3150293245467333794L;

    private String payNo;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
}
