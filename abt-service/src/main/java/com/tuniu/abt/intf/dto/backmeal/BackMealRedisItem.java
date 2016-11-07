package com.tuniu.abt.intf.dto.backmeal;

import com.tuniu.abt.intf.entity.AbtBackMealRule;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * Created by chengyao on 2016/3/31.
 */
public class BackMealRedisItem implements Serializable {

    private static final long serialVersionUID = 5929093639965905310L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 航司码
     */
    private String airlineCompany;

    /**
     * 限定舱位，多个 /S/Y/T/
     */
    private Set<String> cabins;

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
     * 出发到达城市限定类型：1=适用，2=非适用
     */
    private Integer cityOptionType;

    /**
     * 适用出发到达城市对
     */
    private Set<String> cityOptions;

    /**
     * 规则数据表记录
     */
    private AbtBackMealRule abtBackMealRule;

    public AbtBackMealRule getAbtBackMealRule() {
        return abtBackMealRule;
    }

    public void setAbtBackMealRule(AbtBackMealRule abtBackMealRule) {
        this.abtBackMealRule = abtBackMealRule;
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

    public Integer getCityOptionType() {
        return cityOptionType;
    }

    public void setCityOptionType(Integer cityOptionType) {
        this.cityOptionType = cityOptionType;
    }

    public Set<String> getCabins() {
        return cabins;
    }

    public void setCabins(Set<String> cabins) {
        this.cabins = cabins;
    }

    public Set<String> getCityOptions() {
        return cityOptions;
    }

    public void setCityOptions(Set<String> cityOptions) {
        this.cityOptions = cityOptions;
    }
}
