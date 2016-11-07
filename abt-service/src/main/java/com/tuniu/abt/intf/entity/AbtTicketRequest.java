package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_ticket_request")
public class AbtTicketRequest implements Serializable {
    // 初始状态
    public static final int STATUS_INIT = 0;
    // 出票成功
    public static final int ISSUE_SUCCESS = 1;
    // 出票失败
    public static final int ISSUE_FAIL = 2;
    // 待出票
    public static final int ISSUE_WAITING = 3;
    // 支付成功
    public static final int PAY_SUCCESS = 3;
    // 支付失败
    public static final int PAY_FAIL = 4;
    // 部分支付成功
    public static final int PAY_PART_SUCCESS = 5;

    private static final long serialVersionUID = 2404251884124530776L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 途牛订单号
     */
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "system_id")
    private Integer systemId;
    /**
     * 请求ID（1个途牛订单拆分为多次占位，用以区分）
     */
    @Column(name = "flight_item_id")
    private Long flightItemId;

    /**
     * 供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连
     */
    @Column(name = "vendor_id")
    private Integer vendorId;

    /**
     * 开放平台供应商id
     */
    @Column(name = "aop_vendor_id")
    private String aopVendorId;

    /**
     * 状态标识，0=待出票,1=已出票，2=出票失败
     */
    private Integer status;

    /**
     * 订单回调状态:0占位中,1已反馈,2未反馈
     */
    @Column(name = "call_back_status")
    private Integer callBackStatus;

    @Column(name = "callback")
    private String callback;

    /**
     *  开发平台回传过来的订单号
     */
    @Column(name = "ticket_order_id")
    private Long ticketOrderId;

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

    public AbtTicketRequest(Long id, Long orderId, Long flightItemId, Integer vendorId, String aopVendorId, Integer status, Integer callbackStatus, String callbak, Long ticketOrderId, Date addTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.flightItemId = flightItemId;
        this.vendorId = vendorId;
        this.aopVendorId = aopVendorId;
        this.status = status;
        this.callBackStatus = callbackStatus;
        this.callback = callbak;
        this.ticketOrderId = ticketOrderId;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtTicketRequest() {
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

    public String getSessionId() {
        return sessionId;
    }


    /**
     * 设置途牛订单号
     *
     * @param sessionId 途牛订单号
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * _i
     * 获取请求ID（1个途牛订单拆分为多次占位，用以区分）
     *
     * @return flight_item_id - 请求ID（1个途牛订单拆分为多次占位，用以区分）
     */
    public Long getFlightItemId() {
        return flightItemId;
    }

    /**
     * 设置请求ID（1个途牛订单拆分为多次占位，用以区分）
     *
     * @param flightItemId 请求ID（1个途牛订单拆分为多次占位，用以区分）
     */
    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    /**
     * 获取供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连
     *
     * @return vendor_id - 供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连
     *
     * @param vendorId 供应商ID，1=航信，6=携程，7=51，8=开放平台,9=航司直连
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * 获取开放平台供应商id
     *
     * @return aop_vendor_id - 开放平台供应商id
     */
    public String getAopVendorId() {
        return aopVendorId;
    }

    /**
     * 设置开放平台供应商id
     *
     * @param aopVendorId 开放平台供应商id
     */
    public void setAopVendorId(String aopVendorId) {
        this.aopVendorId = aopVendorId == null ? null : aopVendorId.trim();
    }


    /**
     * 获取状态标识，0=待出票,1=已出票，2=出票失败
     *
     * @return status - 状态标识，0=待出票,1=已出票，2=出票失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态标识，0=待出票,1=已出票，2=出票失败
     *
     * @param status 状态标识，0=待出票,1=已出票，2=出票失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCallBackStatus() {
        return callBackStatus;
    }

    public void setCallBackStatus(Integer callBackStatus) {
        this.callBackStatus = callBackStatus;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Long getTicketOrderId() {
        return ticketOrderId;
    }

    public void setTicketOrderId(Long ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
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