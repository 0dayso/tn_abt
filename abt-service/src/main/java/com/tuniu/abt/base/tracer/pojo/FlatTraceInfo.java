package com.tuniu.abt.base.tracer.pojo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 接入elk的日志输出
 * Created by chengyao on 2016/3/5.
 */
public class FlatTraceInfo implements Serializable {

    private static final long serialVersionUID = -6017987978729234534L;

    // 0=成功，1=失败，2=部分失败
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int PART_FAIL = 2;
    public static final int LEVEL_ACTION = 0;
    public static final int LEVEL_CMD = 1;

    // 跟踪uuid，保证唯一
    private String traceId;

    // 层级，0=action，1=cmd
    private int level = LEVEL_ACTION;

    // action，定位一次外部的请求或完整的执行功能
    private String bizAction;

    // action执行中的某个小记录模块
    private String bizCmd;

    // cmd中用于区分不同内容的字段，比如记录http请求cmd的url
    private String bizType;

    // 通用----------------------------------
    // 开始
    private Date execStart;

    // 结束
    private Date execEnd;

    // 执行时长
    private long execDuration;

    // 执行结果状态， 0=成功，1=失败，2=部分失败，等等
    private int execStatus = SUCCESS;

    // 异常码
    private String errorCode;

    // 失败时的错误信息
    private String errorDesc;

    // HTTP相关----------------------------------
    // 请求地址
    private String requestUrl;

    private String requestURI;
    // 请求远程主机host
    private String remoteHost;
    // 请求远程主机port
    private Integer remotePort;
    // 处理容器host
    private String serverName;
    // 处理容器port
    private Integer serverPort;

    // 输入
    private String inputParam;

    // 输出
    private String outputResult;

    // 业务相关----------------------------------
    // sessionId
    private String sessionId;
    // 供应商id
    private String vendorId;
    // 渠道
    private String systemId;
    // 订单
    private String orderId;

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBizAction() {
        return bizAction;
    }

    public void setBizAction(String bizAction) {
        this.bizAction = bizAction;
    }

    public String getBizCmd() {
        return bizCmd;
    }

    public void setBizCmd(String bizCmd) {
        this.bizCmd = bizCmd;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Date getExecStart() {
        return execStart;
    }

    public void setExecStart(Date execStart) {
        this.execStart = execStart;
    }

    public Date getExecEnd() {
        return execEnd;
    }

    public void setExecEnd(Date execEnd) {
        this.execEnd = execEnd;
    }

    public long getExecDuration() {
        return execDuration;
    }

    public void setExecDuration(long execDuration) {
        this.execDuration = execDuration;
    }

    public int getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(int execStatus) {
        this.execStatus = execStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getOutputResult() {
        return outputResult;
    }

    public void setOutputResult(String outputResult) {
        this.outputResult = outputResult;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String toTestShowString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" traceId='").append(traceId).append('\'');
        sb.append(", level=").append(level);
        sb.append(", bizAction='").append(bizAction).append('\'');
        sb.append(", bizCmd='").append(bizCmd).append('\'');
        sb.append(", bizType='").append(bizType).append('\'');
        sb.append(", sessionId='").append(sessionId).append('\'');
        sb.append(", vendorId='").append(vendorId).append('\'');
        sb.append(", systemId='").append(systemId).append('\'');
        sb.append(", orderId='").append(orderId).append('\'');
        sb.append(", execStart=").append(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS").format(execStart));
        sb.append(", execEnd=").append(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS").format(execEnd));
        sb.append(", execDuration=").append(execDuration);
        sb.append(", execStatus=").append(execStatus);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorDesc='").append(escape(errorDesc)).append('\'');
        //sb.append(", requestUrl='").append(requestUrl).append('\'');
        //sb.append(", requestURI='").append(requestURI).append('\'');
        //sb.append(", remoteHost='").append(remoteHost).append('\'');
        //sb.append(", remotePort=").append(remotePort);
        //sb.append(", serverName='").append(serverName).append('\'');
        //sb.append(", serverPort=").append(serverPort);
        sb.append(", inputParam='").append(escape(inputParam)).append('\'');
        sb.append(", outputResult='").append(escape(outputResult)).append('\'');
        return sb.toString();
    }

    private String escape(String str) {
        return StringUtils.replaceChars(str, "\n\r", null);
    }
}
