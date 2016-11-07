package com.tuniu.abt.intf.dto.issue.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by huangsizhou on 16/3/17.
 */
public class OrderInfo implements Serializable{

    @JsonProperty("orderId")
    @NotEmpty(message = "{NotEmpty.OrderInfo.pnr}")
    private String pnr;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
}
