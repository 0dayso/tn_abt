package com.tuniu.abt.intf.dto.change;

import com.tuniu.abt.intf.dto.book.request.BookingPassenger;
import com.tuniu.abt.intf.dto.book.request.TravelSegment;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by chengyao on 2016/5/3.
 */
public class ReqChangeItem implements Serializable {
    private static final long serialVersionUID = -4357230400666023316L;

    /**
     * 需要变更的票号
     */
    @NotEmpty(message = "{change.items.ticketNo.notEmpty}")
    private String ticketNo;

    /**
     * 更改费
     */
    private BigDecimal changeFee;

    /**
     * 升舱费
     */
    private BigDecimal upgradeFee;

    /**
     * 手续费
     */
    private BigDecimal commissionFee;

    /**
     * 新的PNR
     */
    private String pnr;

    /**
     * 备注
     */
    private String remark;

    /**
     * 新的乘客信息，如果项目不变，则不要传
     */
    private BookingPassenger newPassengerInfo;

    /**
     * 修改后的航段信息
     */
    private List<TravelSegment> newSegments;

    // no
    private Object segments;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public BigDecimal getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(BigDecimal changeFee) {
        this.changeFee = changeFee;
    }

    public BigDecimal getUpgradeFee() {
        return upgradeFee;
    }

    public void setUpgradeFee(BigDecimal upgradeFee) {
        this.upgradeFee = upgradeFee;
    }

    public BigDecimal getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BookingPassenger getNewPassengerInfo() {
        return newPassengerInfo;
    }

    public void setNewPassengerInfo(BookingPassenger newPassengerInfo) {
        this.newPassengerInfo = newPassengerInfo;
    }

    public List<TravelSegment> getNewSegments() {
        return newSegments;
    }

    public void setNewSegments(List<TravelSegment> newSegments) {
        this.newSegments = newSegments;
    }

    public Object getSegments() {
        return segments;
    }

    public void setSegments(Object segments) {
        this.segments = segments;
    }
}
