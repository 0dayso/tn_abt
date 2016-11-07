package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_refund_order")
public class AbtRefundOrder implements Serializable {
    public static final int STATUS_INIT = 0;
    public static final int STATUS_OK = 1;
    public static final int STATUS_FAIL = 2;

    public static final String REFUND_SOURCE_A = "A";
    public static final String REFUND_SOURCE_M = "M";

    private static final long serialVersionUID = -2263569725707968645L;

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
     * 开放平台退票单号
     */
    @Column(name = "aop_refund_order")
    private String aopRefundOrder;

    /**
     * 是否收到行程单 0：收到 1：未收到
     */
    @Column(name = "received_segment")
    private Integer receivedSegment;

    /**
     * 行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
     */
    @Column(name = "segment_position")
    private String segmentPosition;

    /**
     * 退票类型
     */
    @Column(name = "refund_type")
    private String refundType;

    /**
     * A=自动退票，M=客服退票
     */
    @Column(name = "refund_source")
    private String refundSource;

    /**
     * 退票备注
     */
    private String remark;

    /**
     * 状态标识，0=待退票,1=已退票，2=退票失败
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

    public AbtRefundOrder(Long id, Long orderId, Long flightItemId, String sessionId, String aopOrder, String aopRefundOrder, Integer receivedSegment, String segmentPosition, String refundType, String refundSource, String remark, Integer status, Date addTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.flightItemId = flightItemId;
        this.sessionId = sessionId;
        this.aopOrder = aopOrder;
        this.aopRefundOrder = aopRefundOrder;
        this.receivedSegment = receivedSegment;
        this.segmentPosition = segmentPosition;
        this.refundType = refundType;
        this.refundSource = refundSource;
        this.remark = remark;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtRefundOrder() {
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
     * 获取开放平台退票单号
     *
     * @return aop_refund_order - 开放平台退票单号
     */
    public String getAopRefundOrder() {
        return aopRefundOrder;
    }

    /**
     * 设置开放平台退票单号
     *
     * @param aopRefundOrder 开放平台退票单号
     */
    public void setAopRefundOrder(String aopRefundOrder) {
        this.aopRefundOrder = aopRefundOrder == null ? null : aopRefundOrder.trim();
    }

    /**
     * 获取是否收到行程单 0：收到 1：未收到
     *
     * @return received_segment - 是否收到行程单 0：收到 1：未收到
     */
    public Integer getReceivedSegment() {
        return receivedSegment;
    }

    /**
     * 设置是否收到行程单 0：收到 1：未收到
     *
     * @param receivedSegment 是否收到行程单 0：收到 1：未收到
     */
    public void setReceivedSegment(Integer receivedSegment) {
        this.receivedSegment = receivedSegment;
    }

    /**
     * 获取行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
     *
     * @return segment_position - 行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
     */
    public String getSegmentPosition() {
        return segmentPosition;
    }

    /**
     * 设置行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
     *
     * @param segmentPosition 行程单位置 客人、票台、机场 . 未知 . 未打印. 配送中. 办理挂失. 其他
     */
    public void setSegmentPosition(String segmentPosition) {
        this.segmentPosition = segmentPosition == null ? null : segmentPosition.trim();
    }

    /**
     * 获取退票类型
     *
     * @return refund_type - 退票类型
     */
    public String getRefundType() {
        return refundType;
    }

    /**
     * 设置退票类型
     *
     * @param refundType 退票类型
     */
    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
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
     * 获取状态标识，0=待退票,1=已退票，2=退票失败
     *
     * @return status - 状态标识，0=待退票,1=已退票，2=退票失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态标识，0=待退票,1=已退票，2=退票失败
     *
     * @param status 状态标识，0=待退票,1=已退票，2=退票失败
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