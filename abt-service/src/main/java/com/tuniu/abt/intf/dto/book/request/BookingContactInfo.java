package com.tuniu.abt.intf.dto.book.request;

import java.io.Serializable;

/**
 * 联系人信息
 * Created by chengyao on 2016/2/1.
 */
public class BookingContactInfo implements Serializable {

    private static final long serialVersionUID = -7870131013079193321L;

    /**
     * 联系人姓名
     */
    private String contactPersonName;

    /**
     * 联系人电话
     */
    private String contactPersonTel;

    /**
     * 联系人邮箱
     */
    private String contactPersonEmail;

    /**
     * 联系人地址
     */
    private String contactAddress;

    /**
     * 联系人城市
     */
    private String contactCity;

    /**
     * 联系人国家
     */
    private String contactCountry;

    /**
     * 联系人邮编
     */
    private String contactPostal;

    /**
     * 联系人省
     */
    private String contactProvinceState;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonTel() {
        return contactPersonTel;
    }

    public void setContactPersonTel(String contactPersonTel) {
        this.contactPersonTel = contactPersonTel;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactCity(String contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactCountry() {
        return contactCountry;
    }

    public void setContactCountry(String contactCountry) {
        this.contactCountry = contactCountry;
    }

    public String getContactPostal() {
        return contactPostal;
    }

    public void setContactPostal(String contactPostal) {
        this.contactPostal = contactPostal;
    }

    public String getContactProvinceState() {
        return contactProvinceState;
    }

    public void setContactProvinceState(String contactProvinceState) {
        this.contactProvinceState = contactProvinceState;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
