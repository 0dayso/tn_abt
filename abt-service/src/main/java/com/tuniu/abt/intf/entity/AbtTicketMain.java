package com.tuniu.abt.intf.entity;

import com.tuniu.abt.intf.builder.AbtTicketMainBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_ticket_main")
public class AbtTicketMain implements Serializable {
    public static final int PASSENGER_TYPE_ADULT = 1;
    public static final int PASSENGER_TYPE_CHILD = 2;
    public static final int PASSENGER_TYPE_BABY = 3;
    public static final int STATUS_INIT = 0;

    public static final int STATUS_REFUNDED = 1; // 已退票
    public static final int STATUS_CHANGED = 2; // 已改期
    public static final int STATUS_UPGRADED = 3; // 已升舱
    public static final int STATUS_DELETED = 4; // 记录已删除

    private static final long serialVersionUID = -6645148024420081486L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * abt_ticket_request表ID
     */
    @Column(name = "request_id")
    private Long requestId;

    /**
     * pnr
     */
    private String pnr;

    /**
     * 换编出票，记录新pnr
     */
    @Column(name = "new_pnr")
    private String newPnr;

    /**
     * 电子客票票号
     */
    @Column(name = "ticket_no")
    private String ticketNo;

    /**
     * 乘客姓名
     */
    @Column(name = "passenger_name")
    private String passengerName;

    /**
     * 乘客类型
     */
    @Column(name = "passenger_type")
    private Integer passengerType;

    /**
     * 航班号
     */
    @Column(name = "flight_no")
    private String flightNo;

    /**
     * 航班序号
     */
    private Integer rph;

    /**
     * 航班舱位
     */
    @Column(name = "seat_code")
    private String seatCode;

    /**
     * 出发机场三字码
     */
    @Column(name = "org_airport_code")
    private String orgAirportCode;

    /**
     * 目的机场三字码
     */
    @Column(name = "dst_airport_code")
    private String dstAirportCode;

    /**
     * 出发时间
     */
    @Column(name = "org_time")
    private Date orgTime;

    /**
     * 票面价
     */
    @Column(name = "org_price")
    private Float orgPrice;

    /**
     * 结算价
     */
    @Column(name = "floor_price")
    private Float floorPrice;

    /**
     * 燃油附加费
     */
    @Column(name = "fuel_surcharge")
    private Float fuelSurcharge;

    /**
     * 机场建设费
     */
    @Column(name = "airport_tax")
    private Float airportTax;

    /**
     * 状态:0=已出票，1=已退票
     */
    private Integer status;

    /**
     * 记录创建日期
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 记录修改日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 真实出票的供应商id
     */
    @Column(name = "solution_id")
    private String solutionId;

    /**
     * 真实出票的供应商名
     */
    @Column(name = "solution_name")
    private String solutionName;

    /**
     * fab系统人员id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * 售卖价
     */
    @Column(name = "sale_price")
    private Float salePrice;

    public AbtTicketMain(Long id, Long requestId, String pnr, String newPnr, String ticketNo, String passengerName, Integer passengerType, String flightNo, Integer rph, String seatCode, String orgAirportCode, String dstAirportCode, Date orgTime, Float orgPrice, Float floorPrice, Float fuelSurcharge, Float airportTax, Integer status, Date addTime, Date updateTime, String solutionId, String solutionName, Long personId, Float salePrice) {
        this.id = id;
        this.requestId = requestId;
        this.pnr = pnr;
        this.newPnr = newPnr;
        this.ticketNo = ticketNo;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
        this.flightNo = flightNo;
        this.rph = rph;
        this.seatCode = seatCode;
        this.orgAirportCode = orgAirportCode;
        this.dstAirportCode = dstAirportCode;
        this.orgTime = orgTime;
        this.orgPrice = orgPrice;
        this.floorPrice = floorPrice;
        this.fuelSurcharge = fuelSurcharge;
        this.airportTax = airportTax;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.solutionId = solutionId;
        this.solutionName = solutionName;
        this.personId = personId;
        this.salePrice = salePrice;
    }

    public AbtTicketMain() {
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
     * 获取abt_ticket_request表ID
     *
     * @return request_id - abt_ticket_request表ID
     */
    public Long getRequestId() {
        return requestId;
    }

    /**
     * 设置abt_ticket_request表ID
     *
     * @param requestId abt_ticket_request表ID
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /**
     * 获取pnr
     *
     * @return pnr - pnr
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * 设置pnr
     *
     * @param pnr pnr
     */
    public void setPnr(String pnr) {
        this.pnr = pnr == null ? null : pnr.trim();
    }

    /**
     * 获取换编出票，记录新pnr
     *
     * @return new_pnr - 换编出票，记录新pnr
     */
    public String getNewPnr() {
        return newPnr;
    }

    /**
     * 设置换编出票，记录新pnr
     *
     * @param newPnr 换编出票，记录新pnr
     */
    public void setNewPnr(String newPnr) {
        this.newPnr = newPnr == null ? null : newPnr.trim();
    }

    /**
     * 获取电子客票票号
     *
     * @return ticket_no - 电子客票票号
     */
    public String getTicketNo() {
        return ticketNo;
    }

    /**
     * 设置电子客票票号
     *
     * @param ticketNo 电子客票票号
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo == null ? null : ticketNo.trim();
    }

    /**
     * 获取乘客姓名
     *
     * @return passenger_name - 乘客姓名
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * 设置乘客姓名
     *
     * @param passengerName 乘客姓名
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    /**
     * 获取乘客类型
     *
     * @return passenger_type - 乘客类型
     */
    public Integer getPassengerType() {
        return passengerType;
    }

    /**
     * 设置乘客类型
     *
     * @param passengerType 乘客类型
     */
    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    /**
     * 获取航班号
     *
     * @return flight_no - 航班号
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * 设置航班号
     *
     * @param flightNo 航班号
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo == null ? null : flightNo.trim();
    }

    /**
     * 获取航班序号
     *
     * @return rph - 航班序号
     */
    public Integer getRph() {
        return rph;
    }

    /**
     * 设置航班序号
     *
     * @param rph 航班序号
     */
    public void setRph(Integer rph) {
        this.rph = rph;
    }

    /**
     * 获取航班舱位
     *
     * @return seat_code - 航班舱位
     */
    public String getSeatCode() {
        return seatCode;
    }

    /**
     * 设置航班舱位
     *
     * @param seatCode 航班舱位
     */
    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode == null ? null : seatCode.trim();
    }

    /**
     * 获取出发机场三字码
     *
     * @return org_airport_code - 出发机场三字码
     */
    public String getOrgAirportCode() {
        return orgAirportCode;
    }

    /**
     * 设置出发机场三字码
     *
     * @param orgAirportCode 出发机场三字码
     */
    public void setOrgAirportCode(String orgAirportCode) {
        this.orgAirportCode = orgAirportCode == null ? null : orgAirportCode.trim();
    }

    /**
     * 获取目的机场三字码
     *
     * @return dst_airport_code - 目的机场三字码
     */
    public String getDstAirportCode() {
        return dstAirportCode;
    }

    /**
     * 设置目的机场三字码
     *
     * @param dstAirportCode 目的机场三字码
     */
    public void setDstAirportCode(String dstAirportCode) {
        this.dstAirportCode = dstAirportCode == null ? null : dstAirportCode.trim();
    }

    /**
     * 获取出发时间
     *
     * @return org_time - 出发时间
     */
    public Date getOrgTime() {
        return orgTime;
    }

    /**
     * 设置出发时间
     *
     * @param orgTime 出发时间
     */
    public void setOrgTime(Date orgTime) {
        this.orgTime = orgTime;
    }

    /**
     * 获取票面价
     *
     * @return org_price - 票面价
     */
    public Float getOrgPrice() {
        return orgPrice;
    }

    /**
     * 设置票面价
     *
     * @param orgPrice 票面价
     */
    public void setOrgPrice(Float orgPrice) {
        this.orgPrice = orgPrice;
    }

    /**
     * 获取结算价
     *
     * @return floor_price - 结算价
     */
    public Float getFloorPrice() {
        return floorPrice;
    }

    /**
     * 设置结算价
     *
     * @param floorPrice 结算价
     */
    public void setFloorPrice(Float floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * 获取燃油附加费
     *
     * @return fuel_surcharge - 燃油附加费
     */
    public Float getFuelSurcharge() {
        return fuelSurcharge;
    }

    /**
     * 设置燃油附加费
     *
     * @param fuelSurcharge 燃油附加费
     */
    public void setFuelSurcharge(Float fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    /**
     * 获取机场建设费
     *
     * @return airport_tax - 机场建设费
     */
    public Float getAirportTax() {
        return airportTax;
    }

    /**
     * 设置机场建设费
     *
     * @param airportTax 机场建设费
     */
    public void setAirportTax(Float airportTax) {
        this.airportTax = airportTax;
    }

    /**
     * 获取状态:0=已出票，1=已退票
     *
     * @return status - 状态:0=已出票，1=已退票
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态:0=已出票，1=已退票
     *
     * @param status 状态:0=已出票，1=已退票
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取记录创建日期
     *
     * @return add_time - 记录创建日期
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置记录创建日期
     *
     * @param addTime 记录创建日期
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取记录修改日期
     *
     * @return update_time - 记录修改日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置记录修改日期
     *
     * @param updateTime 记录修改日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取真实出票的供应商id
     *
     * @return solution_id - 真实出票的供应商id
     */
    public String getSolutionId() {
        return solutionId;
    }

    /**
     * 设置真实出票的供应商id
     *
     * @param solutionId 真实出票的供应商id
     */
    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId == null ? null : solutionId.trim();
    }

    /**
     * 获取真实出票的供应商名
     *
     * @return solution_name - 真实出票的供应商名
     */
    public String getSolutionName() {
        return solutionName;
    }

    /**
     * 设置真实出票的供应商名
     *
     * @param solutionName 真实出票的供应商名
     */
    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName == null ? null : solutionName.trim();
    }

    /**
     * 获取fab系统人员id
     *
     * @return person_id - fab系统人员id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置fab系统人员id
     *
     * @param personId fab系统人员id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取售卖价
     *
     * @return sale_price - 售卖价
     */
    public Float getSalePrice() {
        return salePrice;
    }

    /**
     * 设置售卖价
     *
     * @param salePrice 售卖价
     */
    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public AbtTicketMain(AbtTicketMainBuilder builder) {
        this.requestId = builder.getRequestId();
        this.pnr = builder.getPnr();
        this.newPnr = builder.getNewPnr();
        this.ticketNo = builder.getTicketNo();
        this.passengerName = builder.getPassengerName();
        this.passengerType = builder.getPassengerType();
        this.flightNo = builder.getFlightNo();
        this.rph = builder.getRph();
        this.seatCode = builder.getSeatCode();
        this.orgAirportCode = builder.getOrgAirportCode();
        this.dstAirportCode = builder.getDstAirportCode();
        this.orgTime = builder.getOrgTime();
        this.orgPrice = builder.getOrgPrice().floatValue();
        this.floorPrice = builder.getFloorPrice().floatValue();
        if (builder.getAddTime() != null) {
            this.addTime = builder.getAddTime();
        }
        if (builder.getUpdateTime() != null) {
            this.updateTime = builder.getUpdateTime();
        }
        this.status = builder.getStatus();
        this.solutionId = builder.getSolutionId();
        this.solutionName = builder.getSolutionName();
    }

}