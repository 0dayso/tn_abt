package com.tuniu.abt.intf.tsp.dto.flt;

import java.io.Serializable;
import java.util.Date;

/**
 * 增加屏蔽航班请求
 * Created by chengyao on 2016/3/30.
 */
public class BanAddRequest implements Serializable {

    private static final long serialVersionUID = 8562506050570505367L;

    /**
     * 航司码
     */
    private String airlineCompany;

    /**
     * 航班号，多个以/分割
     */
    private String flightNo;

    /**
     * 旅行日期段开始 yyyy-MM-dd
     */
    private String departureDateStart;

    /**
     * 旅行日期段结束 yyyy-MM-dd
     */
    private String departureDateEnd;

    /**
     * 限定舱位，多个 /S/Y/T/
     */
    private String cabin;

    /**
     * 系统ID
     */
    private String systemId;

    /**
     * 供应商id
     */
    private String solutionId;

    /**
     * 出发机场三字码
     */
    private String orgAirportCode;

    /**
     * 目的机场三字码
     */
    private String dstAirportCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0:手动录入，1：起飞前80单进行屏蔽，即此航班我司一共占位和取消过80个客人，2：一天时间内，有30个客人预订记录在取消或者占位状态的，3：15分钟内，有15个客人预订记录在取消或者占位状态的
     */
    private Integer banRuleType;

    /**
     * 状态，0=生效，1=失效
     */
    private Integer status;

    /**
     * 添加人id
     */
    private Integer addUserId;

    /**
     * 添加人名称
     */
    private String addUserName;

    /**
     * 更新人id
     */
    private Integer updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserName;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureDateStart() {
        return departureDateStart;
    }

    public void setDepartureDateStart(String departureDateStart) {
        this.departureDateStart = departureDateStart;
    }

    public String getDepartureDateEnd() {
        return departureDateEnd;
    }

    public void setDepartureDateEnd(String departureDateEnd) {
        this.departureDateEnd = departureDateEnd;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getOrgAirportCode() {
        return orgAirportCode;
    }

    public void setOrgAirportCode(String orgAirportCode) {
        this.orgAirportCode = orgAirportCode;
    }

    public String getDstAirportCode() {
        return dstAirportCode;
    }

    public void setDstAirportCode(String dstAirportCode) {
        this.dstAirportCode = dstAirportCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBanRuleType() {
        return banRuleType;
    }

    public void setBanRuleType(Integer banRuleType) {
        this.banRuleType = banRuleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
