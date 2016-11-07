package com.tuniu.abt.intf.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_aop_policy")
public class AbtAopPolicy implements Serializable {
    private static final long serialVersionUID = 7133439465237533819L;

    /**
     * 主键,政策ID
     */
    @Id
    private Long id;

    /**
     * 供应商ID 8：开放平台
     */
    @Column(name = "vendor_id")
    private Integer vendorId;

    /**
     * 出票商office号
     */
    @Column(name = "ticketing_office_no")
    private String ticketingOfficeNo;

    /**
     * 出票商的账号
     */
    @Column(name = "ticketing_account_no")
    private String ticketingAccountNo;

    /**
     * 出票商的密码
     */
    @Column(name = "ticketing_account_pwd")
    private String ticketingAccountPwd;

    /**
     * 供应商office账号
     */
    @Column(name = "supplier_office_no")
    private String supplierOfficeNo;

    /**
     * 是否换编码  0不换编 1换编
     */
    @Column(name = "change_pnr_flag")
    private Integer changePnrFlag;

    /**
     * 政策是否失效标示 0有效 1无效
     */
    @Column(name = "policy_effective_flag")
    private Integer policyEffectiveFlag;

    /**
     * 实际出票商office号
     */
    @Column(name = "actual_ticketing_office_no")
    private String actualTicketingOfficeNo;

    /**
     * 开放平台真实供应商ID
     */
    @Column(name = "sub_vendor_id")
    private Integer subVendorId;

    /**
     * 开放平台真实供应商名
     */
    @Column(name = "sub_vendor_name")
    private String subVendorName;

    /**
     * 是否有效 0有效 1无效
     */
    @Column(name = "del_flag")
    private Integer delFlag;

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

    public AbtAopPolicy(Long id, Integer vendorId, String ticketingOfficeNo, String ticketingAccountNo, String ticketingAccountPwd, String supplierOfficeNo, Integer changePnrFlag, Integer policyEffectiveFlag, String actualTicketingOfficeNo, Integer subVendorId, String subVendorName, Integer delFlag, Date addTime, Date updateTime) {
        this.id = id;
        this.vendorId = vendorId;
        this.ticketingOfficeNo = ticketingOfficeNo;
        this.ticketingAccountNo = ticketingAccountNo;
        this.ticketingAccountPwd = ticketingAccountPwd;
        this.supplierOfficeNo = supplierOfficeNo;
        this.changePnrFlag = changePnrFlag;
        this.policyEffectiveFlag = policyEffectiveFlag;
        this.actualTicketingOfficeNo = actualTicketingOfficeNo;
        this.subVendorId = subVendorId;
        this.subVendorName = subVendorName;
        this.delFlag = delFlag;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtAopPolicy() {
        super();
    }

    /**
     * 获取主键,政策ID
     *
     * @return id - 主键,政策ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键,政策ID
     *
     * @param id 主键,政策ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取供应商ID 8：开放平台
     *
     * @return vendor_id - 供应商ID 8：开放平台
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商ID 8：开放平台
     *
     * @param vendorId 供应商ID 8：开放平台
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * 获取出票商office号
     *
     * @return ticketing_office_no - 出票商office号
     */
    public String getTicketingOfficeNo() {
        return ticketingOfficeNo;
    }

    /**
     * 设置出票商office号
     *
     * @param ticketingOfficeNo 出票商office号
     */
    public void setTicketingOfficeNo(String ticketingOfficeNo) {
        this.ticketingOfficeNo = ticketingOfficeNo == null ? null : ticketingOfficeNo.trim();
    }

    /**
     * 获取出票商的账号
     *
     * @return ticketing_account_no - 出票商的账号
     */
    public String getTicketingAccountNo() {
        return ticketingAccountNo;
    }

    /**
     * 设置出票商的账号
     *
     * @param ticketingAccountNo 出票商的账号
     */
    public void setTicketingAccountNo(String ticketingAccountNo) {
        this.ticketingAccountNo = ticketingAccountNo == null ? null : ticketingAccountNo.trim();
    }

    /**
     * 获取出票商的密码
     *
     * @return ticketing_account_pwd - 出票商的密码
     */
    public String getTicketingAccountPwd() {
        return ticketingAccountPwd;
    }

    /**
     * 设置出票商的密码
     *
     * @param ticketingAccountPwd 出票商的密码
     */
    public void setTicketingAccountPwd(String ticketingAccountPwd) {
        this.ticketingAccountPwd = ticketingAccountPwd == null ? null : ticketingAccountPwd.trim();
    }

    /**
     * 获取供应商office账号
     *
     * @return supplier_office_no - 供应商office账号
     */
    public String getSupplierOfficeNo() {
        return supplierOfficeNo;
    }

    /**
     * 设置供应商office账号
     *
     * @param supplierOfficeNo 供应商office账号
     */
    public void setSupplierOfficeNo(String supplierOfficeNo) {
        this.supplierOfficeNo = supplierOfficeNo == null ? null : supplierOfficeNo.trim();
    }

    /**
     * 获取是否换编码  0不换编 1换编
     *
     * @return change_pnr_flag - 是否换编码  0不换编 1换编
     */
    public Integer getChangePnrFlag() {
        return changePnrFlag;
    }

    /**
     * 设置是否换编码  0不换编 1换编
     *
     * @param changePnrFlag 是否换编码  0不换编 1换编
     */
    public void setChangePnrFlag(Integer changePnrFlag) {
        this.changePnrFlag = changePnrFlag;
    }

    /**
     * 获取政策是否失效标示 0有效 1无效
     *
     * @return policy_effective_flag - 政策是否失效标示 0有效 1无效
     */
    public Integer getPolicyEffectiveFlag() {
        return policyEffectiveFlag;
    }

    /**
     * 设置政策是否失效标示 0有效 1无效
     *
     * @param policyEffectiveFlag 政策是否失效标示 0有效 1无效
     */
    public void setPolicyEffectiveFlag(Integer policyEffectiveFlag) {
        this.policyEffectiveFlag = policyEffectiveFlag;
    }

    /**
     * 获取实际出票商office号
     *
     * @return actual_ticketing_office_no - 实际出票商office号
     */
    public String getActualTicketingOfficeNo() {
        return actualTicketingOfficeNo;
    }

    /**
     * 设置实际出票商office号
     *
     * @param actualTicketingOfficeNo 实际出票商office号
     */
    public void setActualTicketingOfficeNo(String actualTicketingOfficeNo) {
        this.actualTicketingOfficeNo = actualTicketingOfficeNo == null ? null : actualTicketingOfficeNo.trim();
    }

    /**
     * 获取开放平台真实供应商ID
     *
     * @return sub_vendor_id - 开放平台真实供应商ID
     */
    public Integer getSubVendorId() {
        return subVendorId;
    }

    /**
     * 设置开放平台真实供应商ID
     *
     * @param subVendorId 开放平台真实供应商ID
     */
    public void setSubVendorId(Integer subVendorId) {
        this.subVendorId = subVendorId;
    }

    /**
     * 获取开放平台真实供应商名
     *
     * @return sub_vendor_name - 开放平台真实供应商名
     */
    public String getSubVendorName() {
        return subVendorName;
    }

    /**
     * 设置开放平台真实供应商名
     *
     * @param subVendorName 开放平台真实供应商名
     */
    public void setSubVendorName(String subVendorName) {
        this.subVendorName = subVendorName == null ? null : subVendorName.trim();
    }

    /**
     * 获取是否有效 0有效 1无效
     *
     * @return del_flag - 是否有效 0有效 1无效
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否有效 0有效 1无效
     *
     * @param delFlag 是否有效 0有效 1无效
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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