package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "abt_change_order")
public class AbtChangeOrder {
    public static final int STATUS_INIT = 0;
    public static final int STATUS_OK = 1;
    public static final int STATUS_FAIL = 2;
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
     * 请求ID（1个途牛订单拆分为多次占位，用以区分）
     */
    @Column(name = "flight_item_id")
    private Long flightItemId;

    /**
     * 会话ID
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 开放平台出票单号
     */
    @Column(name = "aop_order")
    private String aopOrder;

    /**
     * 开放平台改期单号
     */
    @Column(name = "aop_change_order")
    private String aopChangeOrder;

    /**
     * 改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     */
    @Column(name = "change_type")
    private String changeType;

    /**
     * A=自动退票，M=客服退票
     */
    @Column(name = "refund_source")
    private String refundSource;

    /**
     * 改期单失效时间
     */
    @Column(name = "failure_time")
    private Date failureTime;

    /**
     * 改期备注
     */
    private String remark;

    /**
     * 改期涉及总金额
     */
    @Column(name = "total_amount")
    private Float totalAmount;

    /**
     * 携程订单号
     */
    @Column(name = "ctrip_order_id")
    private String ctripOrderId;

    /**
     * 状态标识，0=待改期,1=已改期，2=改期失败
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

    public AbtChangeOrder(Long id, Long orderId, Long flightItemId, String sessionId, String aopOrder, String aopChangeOrder, String changeType, String refundSource, Date failureTime, String remark, Float totalAmount, String ctripOrderId, Integer status, Date addTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.flightItemId = flightItemId;
        this.sessionId = sessionId;
        this.aopOrder = aopOrder;
        this.aopChangeOrder = aopChangeOrder;
        this.changeType = changeType;
        this.refundSource = refundSource;
        this.failureTime = failureTime;
        this.remark = remark;
        this.totalAmount = totalAmount;
        this.ctripOrderId = ctripOrderId;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtChangeOrder() {
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
     * 获取开放平台出票单号
     *
     * @return aop_order - 开放平台出票单号
     */
    public String getAopOrder() {
        return aopOrder;
    }

    /**
     * 设置开放平台出票单号
     *
     * @param aopOrder 开放平台出票单号
     */
    public void setAopOrder(String aopOrder) {
        this.aopOrder = aopOrder == null ? null : aopOrder.trim();
    }

    /**
     * 获取开放平台改期单号
     *
     * @return aop_change_order - 开放平台改期单号
     */
    public String getAopChangeOrder() {
        return aopChangeOrder;
    }

    /**
     * 设置开放平台改期单号
     *
     * @param aopChangeOrder 开放平台改期单号
     */
    public void setAopChangeOrder(String aopChangeOrder) {
        this.aopChangeOrder = aopChangeOrder == null ? null : aopChangeOrder.trim();
    }

    /**
     * 获取改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     *
     * @return change_type - 改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * 设置改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     *
     * @param changeType 改期类型，1=改名, 2=改证件, 3=同舱改期, 4=升舱换开, 5=航变改升, 9=其他
     */
    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    /**
     * 获取A=自动退票，M=客服退票
     *
     * @return refund_source - A=自动退票，M=客服退票
     */
    public String getRefundSource() {
        return refundSource;
    }

    /**
     * 设置A=自动退票，M=客服退票
     *
     * @param refundSource A=自动退票，M=客服退票
     */
    public void setRefundSource(String refundSource) {
        this.refundSource = refundSource == null ? null : refundSource.trim();
    }

    /**
     * 获取改期单失效时间
     *
     * @return failure_time - 改期单失效时间
     */
    public Date getFailureTime() {
        return failureTime;
    }

    /**
     * 设置改期单失效时间
     *
     * @param failureTime 改期单失效时间
     */
    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }

    /**
     * 获取改期备注
     *
     * @return remark - 改期备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置改期备注
     *
     * @param remark 改期备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取改期涉及总金额
     *
     * @return total_amount - 改期涉及总金额
     */
    public Float getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置改期涉及总金额
     *
     * @param totalAmount 改期涉及总金额
     */
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取携程订单号
     *
     * @return ctrip_order_id - 携程订单号
     */
    public String getCtripOrderId() {
        return ctripOrderId;
    }

    /**
     * 设置携程订单号
     *
     * @param ctripOrderId 携程订单号
     */
    public void setCtripOrderId(String ctripOrderId) {
        this.ctripOrderId = ctripOrderId == null ? null : ctripOrderId.trim();
    }

    /**
     * 获取状态标识，0=待改期,1=已改期，2=改期失败
     *
     * @return status - 状态标识，0=待改期,1=已改期，2=改期失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态标识，0=待改期,1=已改期，2=改期失败
     *
     * @param status 状态标识，0=待改期,1=已改期，2=改期失败
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