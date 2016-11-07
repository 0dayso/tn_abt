package com.tuniu.abt.intf.dto.book.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 占位详细
 * Created by chengyao on 2016/2/1.
 */
public class BookingDetail implements Serializable {

    private static final long serialVersionUID = -2752580430228641912L;

    /**
     * 政策平台活动代码
     */
    @JsonProperty("domesticActionCode")
    @JSONField(name = "domesticActionCode")
    private Integer actionCode;

    /**
     * 政策ID(开放平台/51book/政策平台)
     */
    @JsonProperty("domesticPolicyId")
    @JSONField(name = "domesticPolicyId")
    private Long policyId;
    
    /**
     * 航班资源id
     */
    @NotNull(message = "{BookingDetail.flightItemId.notEmpty}")
    private Long flightItemId;

    /**
     * 航班信息
     */
    @NotNull(message = "{BookingDetail.flightItem.notEmpty}")
    @Valid
    private FlightItem flightItem;

    /**
     * 占位人员信息
     */
    @NotEmpty(message = "{BookingDetail.passengers.notEmpty}")
    @Valid
    private List<BookingPassenger> passengers;

    /**
     * 联系人信息
     */
    @Valid
    private BookingContactInfo contactInfo;

    public Integer getActionCode() {
        return actionCode;
    }

    public void setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public FlightItem getFlightItem() {
        return flightItem;
    }

    public void setFlightItem(FlightItem flightItem) {
        this.flightItem = flightItem;
    }

    public List<BookingPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<BookingPassenger> passengers) {
        this.passengers = passengers;
    }

    public BookingContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(BookingContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

}
