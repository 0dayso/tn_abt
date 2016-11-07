package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_booking_request")
public class AbtBookingRequest implements Serializable {
    private static final long serialVersionUID = 4953008547977395363L;

    /** 占位初始状态 */
    public static final int STATUS_INIT = 0;
    /** 占位成功 */
    public static final int STATUS_SUCCESS = 1;
    /** 占位失败 */
    public static final int STATUS_FAIL = 2;
    /** 占位取消 */
    public static final int STATUS_CANCEL = 3;

    /** 占位回调初始状态 */
    public static final int CALL_BACK_STATUS_INIT = 0;
    /** 占位回调成功 */
    public static final int CALL_BACK_STATUS_SUCCESS = 1;
    /** 占位回调失败 */
    public static final int CALL_BACK_STATUS_FAIL = 2;

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

    /**
     * 会话ID
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 系统ID
     */
    @Column(name = "system_id")
    private Integer systemId;

    /**
     * 供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程
     */
    @Column(name = "vendor_id")
    private Integer vendorId;

    /**
     * 请求ID（1个途牛订单拆分为多次占位，用以区分）
     */
    @Column(name = "flight_item_id")
    private Long flightItemId;

    /**
     * 状态:0=占位中，1=占位成功，2=占位失败，3=已取消
     */
    private Integer status;

    /**
     * 返回时间
     */
    @Column(name = "back_time")
    private Date backTime;

    /**
     * 订单回调状态:0占位中,1已反馈,2未反馈
     */
    @Column(name = "call_back_status")
    private Integer callBackStatus;

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

    public AbtBookingRequest(Long id, Long orderId, String sessionId, Integer systemId, Integer vendorId, Long flightItemId, Integer status, Date backTime, Integer callBackStatus, Date addTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.sessionId = sessionId;
        this.systemId = systemId;
        this.vendorId = vendorId;
        this.flightItemId = flightItemId;
        this.status = status;
        this.backTime = backTime;
        this.callBackStatus = callBackStatus;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtBookingRequest() {
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

    /**
     * 获取会话ID
     *
     * @return session_id - 会话ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置会话ID
     *
     * @param sessionId 会话ID
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * 获取系统ID
     *
     * @return system_id - 系统ID
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * 设置系统ID
     *
     * @param systemId 系统ID
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * 获取供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程
     *
     * @return vendor_id - 供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程
     *
     * @param vendorId 供应商ID，0：ibe 1：ibe+ 2:abe 3:policy 4：待定，5：廉价航司 6：携程
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
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
     * 获取状态:0=占位中，1=占位成功，2=占位失败，3=已取消
     *
     * @return status - 状态:0=占位中，1=占位成功，2=占位失败，3=已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态:0=占位中，1=占位成功，2=占位失败，3=已取消
     *
     * @param status 状态:0=占位中，1=占位成功，2=占位失败，3=已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取返回时间
     *
     * @return back_time - 返回时间
     */
    public Date getBackTime() {
        return backTime;
    }

    /**
     * 设置返回时间
     *
     * @param backTime 返回时间
     */
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    /**
     * 获取订单回调状态:0占位中,1已反馈,2未反馈
     *
     * @return call_back_status - 订单回调状态:0占位中,1已反馈,2未反馈
     */
    public Integer getCallBackStatus() {
        return callBackStatus;
    }

    /**
     * 设置订单回调状态:0占位中,1已反馈,2未反馈
     *
     * @param callBackStatus 订单回调状态:0占位中,1已反馈,2未反馈
     */
    public void setCallBackStatus(Integer callBackStatus) {
        this.callBackStatus = callBackStatus;
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