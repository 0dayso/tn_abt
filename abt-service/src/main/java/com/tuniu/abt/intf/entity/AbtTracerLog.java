package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_tracer_log")
public class AbtTracerLog implements Serializable {
    private static final long serialVersionUID = 1518036247896574342L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 跟踪uuid，对应一次完整的业务处理请求
     */
    @Column(name = "trace_id")
    private String traceId;

    /**
     * 层级：0=action,1=cmd
     */
    private Integer level;

    /**
     * 定位一次外部的请求或完整的执行功能
     */
    @Column(name = "biz_action")
    private String bizAction;

    /**
     * action执行中的某一个模块记录
     */
    @Column(name = "biz_cmd")
    private String bizCmd;

    /**
     * 区分cmd的不同内容
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 开始
     */
    @Column(name = "exec_start")
    private Date execStart;

    /**
     * 结束
     */
    @Column(name = "exec_end")
    private Date execEnd;

    /**
     * 执行时长，单位毫秒
     */
    @Column(name = "exec_duration")
    private Integer execDuration;

    /**
     * 执行结果状态：0=成功，1=失败，2=部分失败
     */
    @Column(name = "exec_status")
    private Integer execStatus;

    /**
     * 异常码
     */
    @Column(name = "error_code")
    private String errorCode;

    /**
     * 异常信息
     */
    @Column(name = "error_desc")
    private String errorDesc;

    /**
     * 会话id
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 供应商id
     */
    @Column(name = "vendor_id")
    private String vendorId;

    /**
     * 系统id
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    public AbtTracerLog(Long id, String traceId, Integer level, String bizAction, String bizCmd, String bizType,
            Date execStart, Date execEnd, Integer execDuration, Integer execStatus, String errorCode, String errorDesc,
            String sessionId, String vendorId, String systemId, String orderId, Date addTime) {
        this.id = id;
        this.traceId = traceId;
        this.level = level;
        this.bizAction = bizAction;
        this.bizCmd = bizCmd;
        this.bizType = bizType;
        this.execStart = execStart;
        this.execEnd = execEnd;
        this.execDuration = execDuration;
        this.execStatus = execStatus;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.sessionId = sessionId;
        this.vendorId = vendorId;
        this.systemId = systemId;
        this.orderId = orderId;
        this.addTime = addTime;
    }

    public AbtTracerLog() {
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
     * 获取跟踪uuid，对应一次完整的业务处理请求
     *
     * @return trace_id - 跟踪uuid，对应一次完整的业务处理请求
     */
    public String getTraceId() {
        return traceId == null ? "" : traceId;
    }

    /**
     * 设置跟踪uuid，对应一次完整的业务处理请求
     *
     * @param traceId 跟踪uuid，对应一次完整的业务处理请求
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId == null ? null : traceId.trim();
    }

    /**
     * 获取层级：0=action,1=cmd
     *
     * @return level - 层级：0=action,1=cmd
     */
    public Integer getLevel() {
        return level == null ? 0 : level;
    }

    /**
     * 设置层级：0=action,1=cmd
     *
     * @param level 层级：0=action,1=cmd
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取定位一次外部的请求或完整的执行功能
     *
     * @return biz_action - 定位一次外部的请求或完整的执行功能
     */
    public String getBizAction() {
        return bizAction == null ? "" : bizAction;
    }

    /**
     * 设置定位一次外部的请求或完整的执行功能
     *
     * @param bizAction 定位一次外部的请求或完整的执行功能
     */
    public void setBizAction(String bizAction) {
        this.bizAction = bizAction == null ? null : bizAction.trim();
    }

    /**
     * 获取action执行中的某一个模块记录
     *
     * @return biz_cmd - action执行中的某一个模块记录
     */
    public String getBizCmd() {
        return bizCmd == null ? "" : bizCmd;
    }

    /**
     * 设置action执行中的某一个模块记录
     *
     * @param bizCmd action执行中的某一个模块记录
     */
    public void setBizCmd(String bizCmd) {
        this.bizCmd = bizCmd == null ? null : bizCmd.trim();
    }

    /**
     * 获取区分cmd的不同内容
     *
     * @return biz_type - 区分cmd的不同内容
     */
    public String getBizType() {
        return bizType == null ? "" : bizType;
    }

    /**
     * 设置区分cmd的不同内容
     *
     * @param bizType 区分cmd的不同内容
     */
    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    /**
     * 获取开始
     *
     * @return exec_start - 开始
     */
    public Date getExecStart() {
        return execStart == null ? new Date() : execStart;
    }

    /**
     * 设置开始
     *
     * @param execStart 开始
     */
    public void setExecStart(Date execStart) {
        this.execStart = execStart;
    }

    /**
     * 获取结束
     *
     * @return exec_end - 结束
     */
    public Date getExecEnd() {
        return execEnd == null ? new Date() : execEnd;
    }

    /**
     * 设置结束
     *
     * @param execEnd 结束
     */
    public void setExecEnd(Date execEnd) {
        this.execEnd = execEnd;
    }

    /**
     * 获取执行时长，单位毫秒
     *
     * @return exec_duration - 执行时长，单位毫秒
     */
    public Integer getExecDuration() {
        return execDuration == null ? 0 : execDuration;
    }

    /**
     * 设置执行时长，单位毫秒
     *
     * @param execDuration 执行时长，单位毫秒
     */
    public void setExecDuration(Integer execDuration) {
        this.execDuration = execDuration;
    }

    /**
     * 获取执行结果状态：0=成功，1=失败，2=部分失败
     *
     * @return exec_status - 执行结果状态：0=成功，1=失败，2=部分失败
     */
    public Integer getExecStatus() {
        return execStatus == null ? 0 : execStatus;
    }

    /**
     * 设置执行结果状态：0=成功，1=失败，2=部分失败
     *
     * @param execStatus 执行结果状态：0=成功，1=失败，2=部分失败
     */
    public void setExecStatus(Integer execStatus) {
        this.execStatus = execStatus;
    }

    /**
     * 获取异常码
     *
     * @return error_code - 异常码
     */
    public String getErrorCode() {
        return errorCode == null ? "" : errorCode;
    }

    /**
     * 设置异常码
     *
     * @param errorCode 异常码
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * 获取异常信息
     *
     * @return error_desc - 异常信息
     */
    public String getErrorDesc() {
        return errorDesc == null ? "" : errorDesc;
    }

    /**
     * 设置异常信息
     *
     * @param errorDesc 异常信息
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc == null ? null : errorDesc.trim();
    }

    /**
     * 获取会话id
     *
     * @return session_id - 会话id
     */
    public String getSessionId() {
        return sessionId == null ? "" : sessionId;
    }

    /**
     * 设置会话id
     *
     * @param sessionId 会话id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * 获取供应商id
     *
     * @return vendor_id - 供应商id
     */
    public String getVendorId() {
        return vendorId == null ? "" : vendorId;
    }

    /**
     * 设置供应商id
     *
     * @param vendorId 供应商id
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    /**
     * 获取系统id
     *
     * @return system_id - 系统id
     */
    public String getSystemId() {
        return systemId == null ? "" : systemId;
    }

    /**
     * 设置系统id
     *
     * @param systemId 系统id
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public String getOrderId() {
        return orderId == null ? "" : orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime == null ? new Date() : addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}