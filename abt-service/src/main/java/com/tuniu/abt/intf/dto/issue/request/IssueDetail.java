package com.tuniu.abt.intf.dto.issue.request;

import com.tuniu.abt.validator.group.AirLineIssueScene;
import com.tuniu.abt.validator.group.AopIssueScene;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/17.
 */
public class IssueDetail implements Serializable{

    /**
     * 航班资源id
     */
    private Long flightItemId;

    /**
     *  要出票的订单号，该订单号是供应商提供的订单号，如果是 API 接口一般为 PNR
     */
    @NotEmpty(message = "{IssueDetail.orderIds}")
    @Size(max = 1, groups = {AirLineIssueScene.class, AopIssueScene.class})
    @Valid
    private List<OrderInfo> orderIds = new ArrayList<OrderInfo>();

    /**
     *   订单总价【可空】
     */
    private BigDecimal totalPrice;

    /**
     * String 订单总价的货币类型，默认为 CNY（人民币）【可空】
     */
    private String currency;

    /**
     * 订单的一些附加信息，用于出票，从占位的返回结果中取"【可空】
     */
    private String extraInfo;

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public List<OrderInfo> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<OrderInfo> orderIds) {
        this.orderIds = orderIds;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

}
