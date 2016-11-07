package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

public class Passenger implements Serializable {
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 3717909487671392365L;

    // 乘客姓名(pnr中的姓名)
    private String passengerName;

    // 占位传入的姓名
    private String orgPassengerName;

    private String firstName;

    private String lastName;

    // 乘客类型ADT成人CHD儿童INF婴儿
    private String passengerTypeCode;

    // 证件类型
    private Integer identityType;

    // 证件号
    private String identityNo;

    // 性别0女1男9未知
    private Integer gender;

    // 出生日期，儿童或婴儿，需要填写，格式yyyy-MM-dd
    private String birthday;

    // 儿童/婴儿携程者(成人)
    private Long refPersonId;

    // 联系方式
    private String contactNum;

    // fab系统人员id
    private Long personId;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getOrgPassengerName() {
        return orgPassengerName;
    }

    public void setOrgPassengerName(String orgPassengerName) {
        this.orgPassengerName = orgPassengerName;
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

    public String getPassengerTypeCode() {
        return passengerTypeCode;
    }

    public void setPassengerTypeCode(String passengerTypeCode) {
        this.passengerTypeCode = passengerTypeCode;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getRefPersonId() {
        return refPersonId;
    }

    public void setRefPersonId(Long refPersonId) {
        this.refPersonId = refPersonId;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
