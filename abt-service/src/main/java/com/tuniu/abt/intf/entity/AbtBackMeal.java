package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_back_meal")
public class AbtBackMeal implements Serializable {

    public static final int STATUS_INIT = 1;
    public static final int STATUS_ONLINE = 2;
    public static final int STATUS_OFFLINE = 3;
    public static final int STATUS_DEL = 4;

    public static final int JOURNEY_TYPE_ONE_WAY = 1;
    public static final int JOURNEY_TYPE_ROUND = 2;

    public static final int CITY_OPTION_TYPE_INCLUDE = 1;
    public static final int CITY_OPTION_TYPE_EXCLUDE = 2;

    public static final int PASSENGER_TYPE_ADULT = 1;
    public static final int PASSENGER_TYPE_CHILD = 2;

    private static final long serialVersionUID = 4515808909931356704L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 航司码
     */
    @Column(name = "airline_company")
    private String airlineCompany;

    /**
     * 航司名
     */
    @Column(name = "airline_company_name")
    private String airlineCompanyName;

    /**
     * 限定舱位，多个 /S/Y/T/
     */
    private String cabin;

    /**
     * 航程类型, 1=单程，2=往返
     */
    @Column(name = "journey_type")
    private Integer journeyType;

    /**
     * 乘客类型, 1=成人，2=儿童
     */
    @Column(name = "passenger_type")
    private Integer passengerType;

    /**
     * 有效的出票日期-开始, YYYY-MM-dd
     */
    @Column(name = "ticket_date_start")
    private String ticketDateStart;

    /**
     * 有效的出票日期-结束, YYYY-MM-dd
     */
    @Column(name = "ticket_date_end")
    private String ticketDateEnd;

    /**
     * 有效的起飞日期-开始, YYYY-MM-dd
     */
    @Column(name = "departure_date_start")
    private String departureDateStart;

    /**
     * 有效的起飞日期-结束, YYYY-MM-dd
     */
    @Column(name = "departure_date_end")
    private String departureDateEnd;

    /**
     * 运价渠道，0=中航信
     */
    @Column(name = "freight_channel")
    private Integer freightChannel;

    /**
     * 所属类型，0=非商旅优选
     */
    @Column(name = "rule_type")
    private Integer ruleType;

    /**
     * 出发到达城市限定类型：1=适用，2=非适用
     */
    @Column(name = "city_option_type")
    private Integer cityOptionType;

    /**
     * 适用出发到达城市对
     */
    @Column(name = "city_options")
    private String cityOptions;

    /**
     * 状态，1=已保存，2=生效，3=失效，4=删除
     */
    private Integer status;

    /**
     * operator_id
     */
    @Column(name = "operator_id")
    private Integer operatorId;

    /**
     * operator_name
     */
    @Column(name = "operator_name")
    private String operatorName;

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

    public AbtBackMeal(Long id, String airlineCompany, String airlineCompanyName, String cabin, Integer journeyType, Integer passengerType, String ticketDateStart, String ticketDateEnd, String departureDateStart, String departureDateEnd, Integer freightChannel, Integer ruleType, Integer cityOptionType, String cityOptions, Integer status, Integer operatorId, String operatorName, Date addTime, Date updateTime) {
        this.id = id;
        this.airlineCompany = airlineCompany;
        this.airlineCompanyName = airlineCompanyName;
        this.cabin = cabin;
        this.journeyType = journeyType;
        this.passengerType = passengerType;
        this.ticketDateStart = ticketDateStart;
        this.ticketDateEnd = ticketDateEnd;
        this.departureDateStart = departureDateStart;
        this.departureDateEnd = departureDateEnd;
        this.freightChannel = freightChannel;
        this.ruleType = ruleType;
        this.cityOptionType = cityOptionType;
        this.cityOptions = cityOptions;
        this.status = status;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtBackMeal() {
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
     * 获取航司码
     *
     * @return airline_company - 航司码
     */
    public String getAirlineCompany() {
        return airlineCompany;
    }

    /**
     * 设置航司码
     *
     * @param airlineCompany 航司码
     */
    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany == null ? null : airlineCompany.trim();
    }

    /**
     * 获取航司名
     *
     * @return airline_company_name - 航司名
     */
    public String getAirlineCompanyName() {
        return airlineCompanyName;
    }

    /**
     * 设置航司名
     *
     * @param airlineCompanyName 航司名
     */
    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName == null ? null : airlineCompanyName.trim();
    }

    /**
     * 获取限定舱位，多个 /S/Y/T/
     *
     * @return cabin - 限定舱位，多个 /S/Y/T/
     */
    public String getCabin() {
        return cabin;
    }

    /**
     * 设置限定舱位，多个 /S/Y/T/
     *
     * @param cabin 限定舱位，多个 /S/Y/T/
     */
    public void setCabin(String cabin) {
        this.cabin = cabin == null ? null : cabin.trim();
    }

    /**
     * 获取航程类型, 1=单程，2=往返
     *
     * @return journey_type - 航程类型, 1=单程，2=往返
     */
    public Integer getJourneyType() {
        return journeyType;
    }

    /**
     * 设置航程类型, 1=单程，2=往返
     *
     * @param journeyType 航程类型, 1=单程，2=往返
     */
    public void setJourneyType(Integer journeyType) {
        this.journeyType = journeyType;
    }

    /**
     * 获取乘客类型, 1=成人，2=儿童
     *
     * @return passenger_type - 乘客类型, 1=成人，2=儿童
     */
    public Integer getPassengerType() {
        return passengerType;
    }

    /**
     * 设置乘客类型, 1=成人，2=儿童
     *
     * @param passengerType 乘客类型, 1=成人，2=儿童
     */
    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    /**
     * 获取有效的出票日期-开始, YYYY-MM-dd
     *
     * @return ticket_date_start - 有效的出票日期-开始, YYYY-MM-dd
     */
    public String getTicketDateStart() {
        return ticketDateStart;
    }

    /**
     * 设置有效的出票日期-开始, YYYY-MM-dd
     *
     * @param ticketDateStart 有效的出票日期-开始, YYYY-MM-dd
     */
    public void setTicketDateStart(String ticketDateStart) {
        this.ticketDateStart = ticketDateStart == null ? null : ticketDateStart.trim();
    }

    /**
     * 获取有效的出票日期-结束, YYYY-MM-dd
     *
     * @return ticket_date_end - 有效的出票日期-结束, YYYY-MM-dd
     */
    public String getTicketDateEnd() {
        return ticketDateEnd;
    }

    /**
     * 设置有效的出票日期-结束, YYYY-MM-dd
     *
     * @param ticketDateEnd 有效的出票日期-结束, YYYY-MM-dd
     */
    public void setTicketDateEnd(String ticketDateEnd) {
        this.ticketDateEnd = ticketDateEnd == null ? null : ticketDateEnd.trim();
    }

    /**
     * 获取有效的起飞日期-开始, YYYY-MM-dd
     *
     * @return departure_date_start - 有效的起飞日期-开始, YYYY-MM-dd
     */
    public String getDepartureDateStart() {
        return departureDateStart;
    }

    /**
     * 设置有效的起飞日期-开始, YYYY-MM-dd
     *
     * @param departureDateStart 有效的起飞日期-开始, YYYY-MM-dd
     */
    public void setDepartureDateStart(String departureDateStart) {
        this.departureDateStart = departureDateStart == null ? null : departureDateStart.trim();
    }

    /**
     * 获取有效的起飞日期-结束, YYYY-MM-dd
     *
     * @return departure_date_end - 有效的起飞日期-结束, YYYY-MM-dd
     */
    public String getDepartureDateEnd() {
        return departureDateEnd;
    }

    /**
     * 设置有效的起飞日期-结束, YYYY-MM-dd
     *
     * @param departureDateEnd 有效的起飞日期-结束, YYYY-MM-dd
     */
    public void setDepartureDateEnd(String departureDateEnd) {
        this.departureDateEnd = departureDateEnd == null ? null : departureDateEnd.trim();
    }

    /**
     * 获取运价渠道，0=中航信
     *
     * @return freight_channel - 运价渠道，0=中航信
     */
    public Integer getFreightChannel() {
        return freightChannel;
    }

    /**
     * 设置运价渠道，0=中航信
     *
     * @param freightChannel 运价渠道，0=中航信
     */
    public void setFreightChannel(Integer freightChannel) {
        this.freightChannel = freightChannel;
    }

    /**
     * 获取所属类型，0=非商旅优选
     *
     * @return rule_type - 所属类型，0=非商旅优选
     */
    public Integer getRuleType() {
        return ruleType;
    }

    /**
     * 设置所属类型，0=非商旅优选
     *
     * @param ruleType 所属类型，0=非商旅优选
     */
    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * 获取出发到达城市限定类型：1=适用，2=非适用
     *
     * @return city_option_type - 出发到达城市限定类型：1=适用，2=非适用
     */
    public Integer getCityOptionType() {
        return cityOptionType;
    }

    /**
     * 设置出发到达城市限定类型：1=适用，2=非适用
     *
     * @param cityOptionType 出发到达城市限定类型：1=适用，2=非适用
     */
    public void setCityOptionType(Integer cityOptionType) {
        this.cityOptionType = cityOptionType;
    }

    /**
     * 获取适用出发到达城市对
     *
     * @return city_options - 适用出发到达城市对
     */
    public String getCityOptions() {
        return cityOptions;
    }

    /**
     * 设置适用出发到达城市对
     *
     * @param cityOptions 适用出发到达城市对
     */
    public void setCityOptions(String cityOptions) {
        this.cityOptions = cityOptions == null ? null : cityOptions.trim();
    }

    /**
     * 获取状态，1=已保存，2=生效，3=失效，4=删除
     *
     * @return status - 状态，1=已保存，2=生效，3=失效，4=删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，1=已保存，2=生效，3=失效，4=删除
     *
     * @param status 状态，1=已保存，2=生效，3=失效，4=删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取operator_id
     *
     * @return operator_id - operator_id
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置operator_id
     *
     * @param operatorId operator_id
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取operator_name
     *
     * @return operator_name - operator_name
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置operator_name
     *
     * @param operatorName operator_name
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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