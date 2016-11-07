package com.tuniu.abt.intf.dto.change;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/5/3.
 */
public class RespChange implements Serializable {

    private static final long serialVersionUID = 2527601399206612535L;

    /**
     * 途牛订单号
     */
    private Long orderId;

    /**
     * 航程标识
     */
    private Long flightItemId;

    /**
     * 开放平台出票单ID
     */
    private Long aopTicketId;

    /**
     * 开放平台退票单ID
     */
    private Long aopChangeId;

    /**
     * 31=改期成功，32=改期失败
     */
    private Integer resultType;

    /**
     * 失败类型：1=超时，2=拒绝
     */
    private Integer failureType;

    /**
     * 拒绝原因：1=非合理拒绝,2=拉回, 3=催单, 4=取消,9=其他
     */
    private Integer refuseReason;

    /**
     * 拒绝备注说明
     */
    private String refuseRemark;

    /**
     * 改期单项目
     */
    private List<RespChangeItem> items;

    private RespChangeCtrip ctripData;

    private String solutionId;

    private String solutionName;

    public RespChangeCtrip getCtripData() {
        return ctripData;
    }

    public void setCtripData(RespChangeCtrip ctripData) {
        this.ctripData = ctripData;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFlightItemId() {
        return flightItemId;
    }

    public void setFlightItemId(Long flightItemId) {
        this.flightItemId = flightItemId;
    }

    public Long getAopTicketId() {
        return aopTicketId;
    }

    public void setAopTicketId(Long aopTicketId) {
        this.aopTicketId = aopTicketId;
    }

    public Long getAopChangeId() {
        return aopChangeId;
    }

    public void setAopChangeId(Long aopChangeId) {
        this.aopChangeId = aopChangeId;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public Integer getFailureType() {
        return failureType;
    }

    public void setFailureType(Integer failureType) {
        this.failureType = failureType;
    }

    public Integer getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Integer refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseRemark() {
        return refuseRemark;
    }

    public void setRefuseRemark(String refuseRemark) {
        this.refuseRemark = refuseRemark;
    }

    public List<RespChangeItem> getItems() {
        return items;
    }

    public void setItems(List<RespChangeItem> items) {
        this.items = items;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }
}
