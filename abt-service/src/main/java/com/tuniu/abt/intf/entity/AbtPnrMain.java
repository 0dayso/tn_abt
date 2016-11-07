package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_pnr_main")
public class AbtPnrMain implements Serializable {

    public static final int STATUS_INIT = 0;

    public static final int STATUS_CANCEL_OK = 1;

    public static final int STATUS_CANCEL_FAIL = 2;

    public static final int STATUS_TICKET_OK = 3;

    public static final int STATUS_TICKET_FAIL = 4;

    public static final int STATUS_TICKETING = 5;

    public static final int STATUS_PAYING = 1;

    public static final int STATUS_PAY_OK = 2;

    public static final int STATUS_PAY_FAIL = 3;

    private static final long serialVersionUID = 2948242231399254049L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * abt_booking_request表ID
     */
    @Column(name = "request_id")
    private Long requestId;

    /**
     * 途牛订单号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * PNR编号 或 外部订单号（携程/51）
     */
    private String pnr;

    /**
     * 外部订单号（PNR纬度）
     */
    @Column(name = "out_order_id")
    private String outOrderId;

    /**
     * 外部主单号（依赖订单号、请求纬度）
     */
    @Column(name = "out_main_order_id")
    private String outMainOrderId;

    /**
     * 携程置收款号
     */
    @Column(name = "external_no")
    private String externalNo;

    /**
     * 清位时间
     */
    @Column(name = "time_limit")
    private Date timeLimit;

    /**
     * 实际保留时限
     */
    @Column(name = "act_time_limit")
    private Date actTimeLimit;

    /**
     * 政策号
     */
    @Column(name = "policy_id")
    private String policyId;

    /**
     * 政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他
     */
    @Column(name = "policy_type")
    private Integer policyType;

    /**
     * 政策活动号
     */
    @Column(name = "dfp_action_code")
    private String dfpActionCode;

    /**
     * 航司大编码
     */
    @Column(name = "air_company_code")
    private String airCompanyCode;

    /**
     * 航司直连实际出票航司
     */
    @Column(name = "ticketing_carrier")
    private String ticketingCarrier;

    /**
     * 订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知
     */
    @Column(name = "order_status_display")
    private String orderStatusDisplay;

    /**
     * 状态：0=初始，1=已取消，2=已出票，3=出票失败
     */
    private Integer status;

    /**
     * 支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败
     */
    @Column(name = "pay_status")
    private Integer payStatus;

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

    /**
     * 供应商id：1=航信，6=携程，7=51book,8=开放平台，9=航司直连
     */
    @Column(name = "vendor_id")
    private Integer vendorId;

    public AbtPnrMain(Long id, Long requestId, Long orderId, String pnr, String outOrderId, String outMainOrderId, String externalNo, Date timeLimit, Date actTimeLimit, String policyId, Integer policyType, String dfpActionCode, String airCompanyCode, String ticketingCarrier, String orderStatusDisplay, Integer status, Integer payStatus, Date addTime, Date updateTime, Integer vendorId) {
        this.id = id;
        this.requestId = requestId;
        this.orderId = orderId;
        this.pnr = pnr;
        this.outOrderId = outOrderId;
        this.outMainOrderId = outMainOrderId;
        this.externalNo = externalNo;
        this.timeLimit = timeLimit;
        this.actTimeLimit = actTimeLimit;
        this.policyId = policyId;
        this.policyType = policyType;
        this.dfpActionCode = dfpActionCode;
        this.airCompanyCode = airCompanyCode;
        this.ticketingCarrier = ticketingCarrier;
        this.orderStatusDisplay = orderStatusDisplay;
        this.status = status;
        this.payStatus = payStatus;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.vendorId = vendorId;
    }

    public AbtPnrMain() {
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
     * 获取abt_booking_request表ID
     *
     * @return request_id - abt_booking_request表ID
     */
    public Long getRequestId() {
        return requestId;
    }

    /**
     * 设置abt_booking_request表ID
     *
     * @param requestId abt_booking_request表ID
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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
     * 获取PNR编号 或 外部订单号（携程/51）
     *
     * @return pnr - PNR编号 或 外部订单号（携程/51）
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * 设置PNR编号 或 外部订单号（携程/51）
     *
     * @param pnr PNR编号 或 外部订单号（携程/51）
     */
    public void setPnr(String pnr) {
        this.pnr = pnr == null ? null : pnr.trim();
    }

    /**
     * 获取外部订单号（PNR纬度）
     *
     * @return out_order_id - 外部订单号（PNR纬度）
     */
    public String getOutOrderId() {
        return outOrderId;
    }

    /**
     * 设置外部订单号（PNR纬度）
     *
     * @param outOrderId 外部订单号（PNR纬度）
     */
    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId == null ? null : outOrderId.trim();
    }

    /**
     * 获取外部主单号（依赖订单号、请求纬度）
     *
     * @return out_main_order_id - 外部主单号（依赖订单号、请求纬度）
     */
    public String getOutMainOrderId() {
        return outMainOrderId;
    }

    /**
     * 设置外部主单号（依赖订单号、请求纬度）
     *
     * @param outMainOrderId 外部主单号（依赖订单号、请求纬度）
     */
    public void setOutMainOrderId(String outMainOrderId) {
        this.outMainOrderId = outMainOrderId == null ? null : outMainOrderId.trim();
    }

    /**
     * 获取携程置收款号
     *
     * @return external_no - 携程置收款号
     */
    public String getExternalNo() {
        return externalNo;
    }

    /**
     * 设置携程置收款号
     *
     * @param externalNo 携程置收款号
     */
    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo == null ? null : externalNo.trim();
    }

    /**
     * 获取清位时间
     *
     * @return time_limit - 清位时间
     */
    public Date getTimeLimit() {
        return timeLimit;
    }

    /**
     * 设置清位时间
     *
     * @param timeLimit 清位时间
     */
    public void setTimeLimit(Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * 获取实际保留时限
     *
     * @return act_time_limit - 实际保留时限
     */
    public Date getActTimeLimit() {
        return actTimeLimit;
    }

    /**
     * 设置实际保留时限
     *
     * @param actTimeLimit 实际保留时限
     */
    public void setActTimeLimit(Date actTimeLimit) {
        this.actTimeLimit = actTimeLimit;
    }

    /**
     * 获取政策号
     *
     * @return policy_id - 政策号
     */
    public String getPolicyId() {
        return policyId;
    }

    /**
     * 设置政策号
     *
     * @param policyId 政策号
     */
    public void setPolicyId(String policyId) {
        this.policyId = policyId == null ? null : policyId.trim();
    }

    /**
     * 获取政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他
     *
     * @return policy_type - 政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他
     */
    public Integer getPolicyType() {
        return policyType;
    }

    /**
     * 设置政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他
     *
     * @param policyType 政策类型，1：普通政策，2：特殊政策，3：伪直连，4：其他
     */
    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }

    /**
     * 获取政策活动号
     *
     * @return dfp_action_code - 政策活动号
     */
    public String getDfpActionCode() {
        return dfpActionCode;
    }

    /**
     * 设置政策活动号
     *
     * @param dfpActionCode 政策活动号
     */
    public void setDfpActionCode(String dfpActionCode) {
        this.dfpActionCode = dfpActionCode == null ? null : dfpActionCode.trim();
    }

    /**
     * 获取航司大编码
     *
     * @return air_company_code - 航司大编码
     */
    public String getAirCompanyCode() {
        return airCompanyCode;
    }

    /**
     * 设置航司大编码
     *
     * @param airCompanyCode 航司大编码
     */
    public void setAirCompanyCode(String airCompanyCode) {
        this.airCompanyCode = airCompanyCode == null ? null : airCompanyCode.trim();
    }

    /**
     * 获取航司直连实际出票航司
     *
     * @return ticketing_carrier - 航司直连实际出票航司
     */
    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    /**
     * 设置航司直连实际出票航司
     *
     * @param ticketingCarrier 航司直连实际出票航司
     */
    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier == null ? null : ticketingCarrier.trim();
    }

    /**
     * 获取订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知
     *
     * @return order_status_display - 订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知
     */
    public String getOrderStatusDisplay() {
        return orderStatusDisplay;
    }

    /**
     * 设置订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知
     *
     * @param orderStatusDisplay 订单状态显示Unsubmit 待提交Processing 确认中ProcessingConfirmed 待出票Cancel 已取消PrintedTicket 已出票RefundApplying 退票申请中 FullRetire 已退票PartRetire 部分退票SendingTicket 已送票NA 未知
     */
    public void setOrderStatusDisplay(String orderStatusDisplay) {
        this.orderStatusDisplay = orderStatusDisplay == null ? null : orderStatusDisplay.trim();
    }

    /**
     * 获取状态：0=初始，1=已取消，2=已出票，3=出票失败
     *
     * @return status - 状态：0=初始，1=已取消，2=已出票，3=出票失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=初始，1=已取消，2=已出票，3=出票失败
     *
     * @param status 状态：0=初始，1=已取消，2=已出票，3=出票失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败
     *
     * @return pay_status - 支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败
     *
     * @param payStatus 支付状态，0=待支付，1=支付中，2=支付成功，3=支付失败
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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

    /**
     * 获取供应商id：1=航信，6=携程，7=51book,8=开放平台，9=航司直连
     *
     * @return vendor_id - 供应商id：1=航信，6=携程，7=51book,8=开放平台，9=航司直连
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商id：1=航信，6=携程，7=51book,8=开放平台，9=航司直连
     *
     * @param vendorId 供应商id：1=航信，6=携程，7=51book,8=开放平台，9=航司直连
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }
}