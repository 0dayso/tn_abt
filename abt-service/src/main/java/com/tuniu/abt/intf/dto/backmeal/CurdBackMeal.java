package com.tuniu.abt.intf.dto.backmeal;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 退改签增删改查请求对象
 * Created by chengyao on 2016/4/6.
 */
public class CurdBackMeal implements Serializable {

    private static final long serialVersionUID = 6368186685593488348L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 航司码
     */
    @NotEmpty(message = "{CurdBackMeal.airlineCompany.notEmpty}")
    private String airlineCompany;

    /**
     * 航司名
     */
    @NotEmpty(message = "{CurdBackMeal.airlineCompanyName.notEmpty}")
    private String airlineCompanyName;

    /**
     * 限定舱位，多个 /S/Y/T/
     */
    @NotEmpty(message = "{CurdBackMeal.cabin.notEmpty}")
    private String cabin;

    /**
     * 航程类型, 1=单程，2=往返
     */
    private Integer journeyType;

    /**
     * 乘客类型, 1=成人，2=儿童
     */
    private Integer passengerType;

    /**
     * 有效的出票日期-开始, YYYY-MM-dd
     */
    private String ticketDateStart;

    /**
     * 有效的出票日期-结束, YYYY-MM-dd
     */
    private String ticketDateEnd;

    /**
     * 有效的起飞日期-开始, YYYY-MM-dd
     */
    private String departureDateStart;

    /**
     * 有效的起飞日期-结束, YYYY-MM-dd
     */
    private String departureDateEnd;

    /**
     * 运价渠道，0=中航信
     */
    private Integer freightChannel;

    /**
     * 所属类型，0=非商旅优选
     */
    private Integer ruleType;

    /**
     * 出发到达城市限定类型：1=适用，2=非适用
     */
    private Integer cityOptionType;

    /**
     * 适用出发到达城市对
     */
    private String cityOptions;

    /**
     * 城市对
     */
    private String cityName;

    /**
     * 状态，1=已保存，2=生效，3=失效，4=删除
     */
    private Integer status;

    /**
     * operator_id
     */
    @NotNull(message = "{CurdBackMeal.operatorId.notNull}")
    private Integer operatorId;

    /**
     * operator_name
     */
    private String operatorName;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 退票规则字符串
     */
    private String reRule;

    /**
     * 换算规则：1=FD运价，2=舱等全价，3=票面价
     */
    private Integer reCalculateType;

    /**
     * 退票规则备注
     */
    private String reRemark;

    /**
     * 同舱改期规则字符串
     */
    private String sameRule;

    /**
     * 换算规则：1=FD运价，2=舱等全价，3=票面价
     */
    private Integer sameCalculateType;

    /**
     * 同舱改期规则备注
     */
    private String sameRemark;

    //分页开始
    private Integer start;
    //分页限制长度
    private Integer limit;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public String getAirlineCompanyName() {
        return airlineCompanyName;
    }

    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Integer getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(Integer journeyType) {
        this.journeyType = journeyType;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    public String getTicketDateStart() {
        return ticketDateStart;
    }

    public void setTicketDateStart(String ticketDateStart) {
        this.ticketDateStart = ticketDateStart;
    }

    public String getTicketDateEnd() {
        return ticketDateEnd;
    }

    public void setTicketDateEnd(String ticketDateEnd) {
        this.ticketDateEnd = ticketDateEnd;
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

    public Integer getFreightChannel() {
        return freightChannel;
    }

    public void setFreightChannel(Integer freightChannel) {
        this.freightChannel = freightChannel;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getCityOptionType() {
        return cityOptionType;
    }

    public void setCityOptionType(Integer cityOptionType) {
        this.cityOptionType = cityOptionType;
    }

    public String getCityOptions() {
        return cityOptions;
    }

    public void setCityOptions(String cityOptions) {
        this.cityOptions = cityOptions;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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

    public String getReRule() {
        return reRule;
    }

    public void setReRule(String reRule) {
        this.reRule = reRule;
    }

    public Integer getReCalculateType() {
        return reCalculateType;
    }

    public void setReCalculateType(Integer reCalculateType) {
        this.reCalculateType = reCalculateType;
    }

    public String getReRemark() {
        return reRemark;
    }

    public void setReRemark(String reRemark) {
        this.reRemark = reRemark;
    }

    public String getSameRule() {
        return sameRule;
    }

    public void setSameRule(String sameRule) {
        this.sameRule = sameRule;
    }

    public Integer getSameCalculateType() {
        return sameCalculateType;
    }

    public void setSameCalculateType(Integer sameCalculateType) {
        this.sameCalculateType = sameCalculateType;
    }

    public String getSameRemark() {
        return sameRemark;
    }

    public void setSameRemark(String sameRemark) {
        this.sameRemark = sameRemark;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
