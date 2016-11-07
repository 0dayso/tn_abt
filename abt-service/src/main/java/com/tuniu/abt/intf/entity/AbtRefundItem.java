package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_refund_item")
public class AbtRefundItem implements Serializable {
    public static final int STATUS_INIT = 0;
    public static final int STATUS_OK = 1;
    public static final int STATUS_FAIL = 2;

    private static final long serialVersionUID = -4252178096417594008L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 退票单号，对应abt_refund_order表id
     */
    @Column(name = "refund_id")
    private Long refundId;

    /**
     * 途牛订单号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 出票记录主表id
     */
    @Column(name = "ticket_main_id")
    private Long ticketMainId;

    /**
     * 类型：1=全退
     */
    @Column(name = "refund_item_type")
    private Integer refundItemType;

    /**
     * 航段信息（出发城市三字码/到达城市三字码）
     */
    private String leg;

    /**
     * 乘客fab系统id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * abt_pnr_passenger表id
     */
    @Column(name = "pnr_passenger_id")
    private Long pnrPassengerId;

    /**
     * 接口实际操作后的退票费
     */
    @Column(name = "real_refund_amount")
    private Float realRefundAmount;

    /**
     * 请求的原始退票费用
     */
    @Column(name = "refund_amount")
    private Float refundAmount;

    /**
     * 请求的手续费
     */
    @Column(name = "commission_fee")
    private Float commissionFee;

    /**
     * 电子客票票号
     */
    @Column(name = "ticket_no")
    private String ticketNo;

    /**
     * pnr
     */
    private String pnr;

    /**
     * 退票备注
     */
    private String remark;

    /**
     * 状态标识，0=待退票,1=成功，2=失败
     */
    private Integer status;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public AbtRefundItem(Long id, Long refundId, Long orderId, Long ticketMainId, Integer refundItemType, String leg, Long personId, Long pnrPassengerId, Float realRefundAmount, Float refundAmount, Float commissionFee, String ticketNo, String pnr, String remark, Integer status, Date addTime, Date updateTime) {
        this.id = id;
        this.refundId = refundId;
        this.orderId = orderId;
        this.ticketMainId = ticketMainId;
        this.refundItemType = refundItemType;
        this.leg = leg;
        this.personId = personId;
        this.pnrPassengerId = pnrPassengerId;
        this.realRefundAmount = realRefundAmount;
        this.refundAmount = refundAmount;
        this.commissionFee = commissionFee;
        this.ticketNo = ticketNo;
        this.pnr = pnr;
        this.remark = remark;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtRefundItem() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取退票单号，对应abt_refund_order表id
     *
     * @return refund_id - 退票单号，对应abt_refund_order表id
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * 设置退票单号，对应abt_refund_order表id
     *
     * @param refundId 退票单号，对应abt_refund_order表id
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * 获取途牛订单号
     *
     * @return order_id - 途牛订单号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置途牛订单号
     *
     * @param orderId 途牛订单号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取出票记录主表id
     *
     * @return ticket_main_id - 出票记录主表id
     */
    public Long getTicketMainId() {
        return ticketMainId;
    }

    /**
     * 设置出票记录主表id
     *
     * @param ticketMainId 出票记录主表id
     */
    public void setTicketMainId(Long ticketMainId) {
        this.ticketMainId = ticketMainId;
    }

    /**
     * 获取类型：1=全退
     *
     * @return refund_item_type - 类型：1=全退
     */
    public Integer getRefundItemType() {
        return refundItemType;
    }

    /**
     * 设置类型：1=全退
     *
     * @param refundItemType 类型：1=全退
     */
    public void setRefundItemType(Integer refundItemType) {
        this.refundItemType = refundItemType;
    }

    /**
     * 获取航段信息（出发城市三字码/到达城市三字码）
     *
     * @return leg - 航段信息（出发城市三字码/到达城市三字码）
     */
    public String getLeg() {
        return leg;
    }

    /**
     * 设置航段信息（出发城市三字码/到达城市三字码）
     *
     * @param leg 航段信息（出发城市三字码/到达城市三字码）
     */
    public void setLeg(String leg) {
        this.leg = leg == null ? null : leg.trim();
    }

    /**
     * 获取乘客fab系统id
     *
     * @return person_id - 乘客fab系统id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置乘客fab系统id
     *
     * @param personId 乘客fab系统id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取abt_pnr_passenger表id
     *
     * @return pnr_passenger_id - abt_pnr_passenger表id
     */
    public Long getPnrPassengerId() {
        return pnrPassengerId;
    }

    /**
     * 设置abt_pnr_passenger表id
     *
     * @param pnrPassengerId abt_pnr_passenger表id
     */
    public void setPnrPassengerId(Long pnrPassengerId) {
        this.pnrPassengerId = pnrPassengerId;
    }

    /**
     * 获取接口实际操作后的退票费
     *
     * @return real_refund_amount - 接口实际操作后的退票费
     */
    public Float getRealRefundAmount() {
        return realRefundAmount;
    }

    /**
     * 设置接口实际操作后的退票费
     *
     * @param realRefundAmount 接口实际操作后的退票费
     */
    public void setRealRefundAmount(Float realRefundAmount) {
        this.realRefundAmount = realRefundAmount;
    }

    /**
     * 获取请求的原始退票费用
     *
     * @return refund_amount - 请求的原始退票费用
     */
    public Float getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置请求的原始退票费用
     *
     * @param refundAmount 请求的原始退票费用
     */
    public void setRefundAmount(Float refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取请求的手续费
     *
     * @return commission_fee - 请求的手续费
     */
    public Float getCommissionFee() {
        return commissionFee;
    }

    /**
     * 设置请求的手续费
     *
     * @param commissionFee 请求的手续费
     */
    public void setCommissionFee(Float commissionFee) {
        this.commissionFee = commissionFee;
    }

    /**
     * 获取电子客票票号
     *
     * @return ticket_no - 电子客票票号
     */
    public String getTicketNo() {
        return ticketNo;
    }

    /**
     * 设置电子客票票号
     *
     * @param ticketNo 电子客票票号
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo == null ? null : ticketNo.trim();
    }

    /**
     * 获取pnr
     *
     * @return pnr - pnr
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * 设置pnr
     *
     * @param pnr pnr
     */
    public void setPnr(String pnr) {
        this.pnr = pnr == null ? null : pnr.trim();
    }

    /**
     * 获取退票备注
     *
     * @return remark - 退票备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置退票备注
     *
     * @param remark 退票备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取状态标识，0=待退票,1=成功，2=失败
     *
     * @return status - 状态标识，0=待退票,1=成功，2=失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态标识，0=待退票,1=成功，2=失败
     *
     * @param status 状态标识，0=待退票,1=成功，2=失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}