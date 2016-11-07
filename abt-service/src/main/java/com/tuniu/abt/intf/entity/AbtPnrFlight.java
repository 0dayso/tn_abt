package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_pnr_flight")
public class AbtPnrFlight implements Serializable {
    private static final long serialVersionUID = -556905713880887302L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联abt_booking_pnr id
     */
    @Column(name = "pnr_id")
    private Long pnrId;

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
     * 机型
     */
    @Column(name = "plane_type")
    private String planeType;

    /**
     * 出发城市三字码
     */
    @Column(name = "org_city_code")
    private String orgCityCode;

    /**
     * 目的城市三字码
     */
    @Column(name = "dst_city_code")
    private String dstCityCode;

    /**
     * 出发城市名称
     */
    @Column(name = "org_city_name")
    private String orgCityName;

    /**
     * 目的城市名称
     */
    @Column(name = "dst_city_name")
    private String dstCityName;

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
     * 出发机场名称
     */
    @Column(name = "org_airport_name")
    private String orgAirportName;

    /**
     * 抵达机场名称
     */
    @Column(name = "dst_airport_name")
    private String dstAirportName;

    /**
     * 出发航站楼名称
     */
    @Column(name = "org_airport_terminal")
    private String orgAirportTerminal;

    /**
     * 抵达航站楼名称
     */
    @Column(name = "dst_airport_terminal")
    private String dstAirportTerminal;

    /**
     * 出发时间
     */
    @Column(name = "org_time")
    private Date orgTime;

    /**
     * 抵达时间
     */
    @Column(name = "dst_time")
    private Date dstTime;

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

    public AbtPnrFlight(Long id, Long pnrId, String flightNo, Integer rph, String seatCode, String planeType, String orgCityCode, String dstCityCode, String orgCityName, String dstCityName, String orgAirportCode, String dstAirportCode, String orgAirportName, String dstAirportName, String orgAirportTerminal, String dstAirportTerminal, Date orgTime, Date dstTime, Date addTime, Date updateTime) {
        this.id = id;
        this.pnrId = pnrId;
        this.flightNo = flightNo;
        this.rph = rph;
        this.seatCode = seatCode;
        this.planeType = planeType;
        this.orgCityCode = orgCityCode;
        this.dstCityCode = dstCityCode;
        this.orgCityName = orgCityName;
        this.dstCityName = dstCityName;
        this.orgAirportCode = orgAirportCode;
        this.dstAirportCode = dstAirportCode;
        this.orgAirportName = orgAirportName;
        this.dstAirportName = dstAirportName;
        this.orgAirportTerminal = orgAirportTerminal;
        this.dstAirportTerminal = dstAirportTerminal;
        this.orgTime = orgTime;
        this.dstTime = dstTime;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtPnrFlight() {
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
     * 获取关联abt_booking_pnr id
     *
     * @return pnr_id - 关联abt_booking_pnr id
     */
    public Long getPnrId() {
        return pnrId;
    }

    /**
     * 设置关联abt_booking_pnr id
     *
     * @param pnrId 关联abt_booking_pnr id
     */
    public void setPnrId(Long pnrId) {
        this.pnrId = pnrId;
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
     * 获取机型
     *
     * @return plane_type - 机型
     */
    public String getPlaneType() {
        return planeType;
    }

    /**
     * 设置机型
     *
     * @param planeType 机型
     */
    public void setPlaneType(String planeType) {
        this.planeType = planeType == null ? null : planeType.trim();
    }

    /**
     * 获取出发城市三字码
     *
     * @return org_city_code - 出发城市三字码
     */
    public String getOrgCityCode() {
        return orgCityCode;
    }

    /**
     * 设置出发城市三字码
     *
     * @param orgCityCode 出发城市三字码
     */
    public void setOrgCityCode(String orgCityCode) {
        this.orgCityCode = orgCityCode == null ? null : orgCityCode.trim();
    }

    /**
     * 获取目的城市三字码
     *
     * @return dst_city_code - 目的城市三字码
     */
    public String getDstCityCode() {
        return dstCityCode;
    }

    /**
     * 设置目的城市三字码
     *
     * @param dstCityCode 目的城市三字码
     */
    public void setDstCityCode(String dstCityCode) {
        this.dstCityCode = dstCityCode == null ? null : dstCityCode.trim();
    }

    /**
     * 获取出发城市名称
     *
     * @return org_city_name - 出发城市名称
     */
    public String getOrgCityName() {
        return orgCityName;
    }

    /**
     * 设置出发城市名称
     *
     * @param orgCityName 出发城市名称
     */
    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName == null ? null : orgCityName.trim();
    }

    /**
     * 获取目的城市名称
     *
     * @return dst_city_name - 目的城市名称
     */
    public String getDstCityName() {
        return dstCityName;
    }

    /**
     * 设置目的城市名称
     *
     * @param dstCityName 目的城市名称
     */
    public void setDstCityName(String dstCityName) {
        this.dstCityName = dstCityName == null ? null : dstCityName.trim();
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
     * 获取出发机场名称
     *
     * @return org_airport_name - 出发机场名称
     */
    public String getOrgAirportName() {
        return orgAirportName;
    }

    /**
     * 设置出发机场名称
     *
     * @param orgAirportName 出发机场名称
     */
    public void setOrgAirportName(String orgAirportName) {
        this.orgAirportName = orgAirportName == null ? null : orgAirportName.trim();
    }

    /**
     * 获取抵达机场名称
     *
     * @return dst_airport_name - 抵达机场名称
     */
    public String getDstAirportName() {
        return dstAirportName;
    }

    /**
     * 设置抵达机场名称
     *
     * @param dstAirportName 抵达机场名称
     */
    public void setDstAirportName(String dstAirportName) {
        this.dstAirportName = dstAirportName == null ? null : dstAirportName.trim();
    }

    /**
     * 获取出发航站楼名称
     *
     * @return org_airport_terminal - 出发航站楼名称
     */
    public String getOrgAirportTerminal() {
        return orgAirportTerminal;
    }

    /**
     * 设置出发航站楼名称
     *
     * @param orgAirportTerminal 出发航站楼名称
     */
    public void setOrgAirportTerminal(String orgAirportTerminal) {
        this.orgAirportTerminal = orgAirportTerminal == null ? null : orgAirportTerminal.trim();
    }

    /**
     * 获取抵达航站楼名称
     *
     * @return dst_airport_terminal - 抵达航站楼名称
     */
    public String getDstAirportTerminal() {
        return dstAirportTerminal;
    }

    /**
     * 设置抵达航站楼名称
     *
     * @param dstAirportTerminal 抵达航站楼名称
     */
    public void setDstAirportTerminal(String dstAirportTerminal) {
        this.dstAirportTerminal = dstAirportTerminal == null ? null : dstAirportTerminal.trim();
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
     * 获取抵达时间
     *
     * @return dst_time - 抵达时间
     */
    public Date getDstTime() {
        return dstTime;
    }

    /**
     * 设置抵达时间
     *
     * @param dstTime 抵达时间
     */
    public void setDstTime(Date dstTime) {
        this.dstTime = dstTime;
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