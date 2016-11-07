package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "abt_change_item")
public class AbtChangeItem {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 改期单号，对应abt_change_order表id
     */
    @Column(name = "change_id")
    private Long changeId;

    /**
     * 途牛订单号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 出票记录主表id
     */
    @Column(name = "ticket_main_id")
    private Long ticketMainId;

    /**
     * 待改期电子客票票号
     */
    @Column(name = "ticket_no")
    private String ticketNo;

    /**
     * 更改费
     */
    @Column(name = "change_fee")
    private Float changeFee;

    /**
     * 升舱费
     */
    @Column(name = "upgradeFee")
    private Float upgradefee;

    /**
     * 手续费
     */
    @Column(name = "commissionFee")
    private Float commissionfee;

    /**
     * 原pnr
     */
    private String pnr;

    /**
     * 新PNR
     */
    @Column(name = "new_pnr")
    private String newPnr;

    /**
     * 航段信息（出发城市三字码/到达城市三字码）
     */
    private String leg;

    /**
     * 乘客fab系统id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * abt_pnr_passenger表id
     */
    @Column(name = "pnr_passenger_id")
    private Long pnrPassengerId;

    /**
     * 新的乘客fab系统id
     */
    @Column(name = "new_person_id")
    private Long newPersonId;

    /**
     * 新航段信息（json字符串）
     */
    @Column(name = "new_segments")
    private String newSegments;

    /**
     * 改期备注
     */
    private String remark;

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

    public AbtChangeItem(Long id, Long changeId, Long orderId, Long ticketMainId, String ticketNo, Float changeFee, Float upgradefee, Float commissionfee, String pnr, String newPnr, String leg, Long personId, Long pnrPassengerId, Long newPersonId, String newSegments, String remark, Date addTime, Date updateTime) {
        this.id = id;
        this.changeId = changeId;
        this.orderId = orderId;
        this.ticketMainId = ticketMainId;
        this.ticketNo = ticketNo;
        this.changeFee = changeFee;
        this.upgradefee = upgradefee;
        this.commissionfee = commissionfee;
        this.pnr = pnr;
        this.newPnr = newPnr;
        this.leg = leg;
        this.personId = personId;
        this.pnrPassengerId = pnrPassengerId;
        this.newPersonId = newPersonId;
        this.newSegments = newSegments;
        this.remark = remark;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtChangeItem() {
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
     * 获取改期单号，对应abt_change_order表id
     *
     * @return change_id - 改期单号，对应abt_change_order表id
     */
    public Long getChangeId() {
        return changeId;
    }

    /**
     * 设置改期单号，对应abt_change_order表id
     *
     * @param changeId 改期单号，对应abt_change_order表id
     */
    public void setChangeId(Long changeId) {
        this.changeId = changeId;
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
     * 获取出票记录主表id
     *
     * @return ticket_main_id - 出票记录主表id
     */
    public Long getTicketMainId() {
        return ticketMainId;
    }

    /**
     * 设置出票记录主表id
     *
     * @param ticketMainId 出票记录主表id
     */
    public void setTicketMainId(Long ticketMainId) {
        this.ticketMainId = ticketMainId;
    }

    /**
     * 获取待改期电子客票票号
     *
     * @return ticket_no - 待改期电子客票票号
     */
    public String getTicketNo() {
        return ticketNo;
    }

    /**
     * 设置待改期电子客票票号
     *
     * @param ticketNo 待改期电子客票票号
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo == null ? null : ticketNo.trim();
    }

    /**
     * 获取更改费
     *
     * @return change_fee - 更改费
     */
    public Float getChangeFee() {
        return changeFee;
    }

    /**
     * 设置更改费
     *
     * @param changeFee 更改费
     */
    public void setChangeFee(Float changeFee) {
        this.changeFee = changeFee;
    }

    /**
     * 获取升舱费
     *
     * @return upgradeFee - 升舱费
     */
    public Float getUpgradefee() {
        return upgradefee;
    }

    /**
     * 设置升舱费
     *
     * @param upgradefee 升舱费
     */
    public void setUpgradefee(Float upgradefee) {
        this.upgradefee = upgradefee;
    }

    /**
     * 获取手续费
     *
     * @return commissionFee - 手续费
     */
    public Float getCommissionfee() {
        return commissionfee;
    }

    /**
     * 设置手续费
     *
     * @param commissionfee 手续费
     */
    public void setCommissionfee(Float commissionfee) {
        this.commissionfee = commissionfee;
    }

    /**
     * 获取原pnr
     *
     * @return pnr - 原pnr
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * 设置原pnr
     *
     * @param pnr 原pnr
     */
    public void setPnr(String pnr) {
        this.pnr = pnr == null ? null : pnr.trim();
    }

    /**
     * 获取新PNR
     *
     * @return new_pnr - 新PNR
     */
    public String getNewPnr() {
        return newPnr;
    }

    /**
     * 设置新PNR
     *
     * @param newPnr 新PNR
     */
    public void setNewPnr(String newPnr) {
        this.newPnr = newPnr == null ? null : newPnr.trim();
    }

    /**
     * 获取航段信息（出发城市三字码/到达城市三字码）
     *
     * @return leg - 航段信息（出发城市三字码/到达城市三字码）
     */
    public String getLeg() {
        return leg;
    }

    /**
     * 设置航段信息（出发城市三字码/到达城市三字码）
     *
     * @param leg 航段信息（出发城市三字码/到达城市三字码）
     */
    public void setLeg(String leg) {
        this.leg = leg == null ? null : leg.trim();
    }

    /**
     * 获取乘客fab系统id
     *
     * @return person_id - 乘客fab系统id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置乘客fab系统id
     *
     * @param personId 乘客fab系统id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取abt_pnr_passenger表id
     *
     * @return pnr_passenger_id - abt_pnr_passenger表id
     */
    public Long getPnrPassengerId() {
        return pnrPassengerId;
    }

    /**
     * 设置abt_pnr_passenger表id
     *
     * @param pnrPassengerId abt_pnr_passenger表id
     */
    public void setPnrPassengerId(Long pnrPassengerId) {
        this.pnrPassengerId = pnrPassengerId;
    }

    /**
     * 获取新的乘客fab系统id
     *
     * @return new_person_id - 新的乘客fab系统id
     */
    public Long getNewPersonId() {
        return newPersonId;
    }

    /**
     * 设置新的乘客fab系统id
     *
     * @param newPersonId 新的乘客fab系统id
     */
    public void setNewPersonId(Long newPersonId) {
        this.newPersonId = newPersonId;
    }

    /**
     * 获取新航段信息（json字符串）
     *
     * @return new_segments - 新航段信息（json字符串）
     */
    public String getNewSegments() {
        return newSegments;
    }

    /**
     * 设置新航段信息（json字符串）
     *
     * @param newSegments 新航段信息（json字符串）
     */
    public void setNewSegments(String newSegments) {
        this.newSegments = newSegments == null ? null : newSegments.trim();
    }

    /**
     * 获取改期备注
     *
     * @return remark - 改期备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置改期备注
     *
     * @param remark 改期备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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