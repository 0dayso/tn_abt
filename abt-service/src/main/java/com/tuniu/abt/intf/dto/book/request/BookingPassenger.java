package com.tuniu.abt.intf.dto.book.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 占位乘客信息
 * Created by chengyao on 2016/2/1.
 */
public class BookingPassenger implements Serializable {

    private static final long serialVersionUID = -1557091737972255264L;

    /**
     * fab系统人员id
     */
    @NotNull(message = "{BookingPassenger.personId.notEmpty}")
    private Long personId;

    /**
     * 姓名
     */
    @NotBlank(message = "{BookingPassenger.name.notEmpty}")
    private String name;
    /**
     * 姓
     */
    private String firstName;
    /**
     * 名
     */
    private String lastName;
    /**
     * 证件类型：1-身份证，2-因私护照，3-军官证，4-港澳通行证，11-台湾通行证，7-台胞证，6-其他
     */
    private String idType;
    /**
     * 证件有效期
     */
    private String idExpiryDate;
    /**
     * 证件号码
     */
    @NotBlank(message = "{BookingPassenger.idNumber.notEmpty}")
    private String idNumber;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 国籍（使用国家二字码）
     */
    private String country;
    /**
     * 性别，1男 0女 9未知
     */
    private int sex;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 签证始发国（使用国家二字码）
     */
    private String countryOfIssue;

    /**
     * 乘客类型：ADT1成人  CHD2儿童  INF3婴儿
     */
    @NotBlank(message = "{BookingPassenger.passengerTypeCode.notEmpty}")
    @Pattern(regexp = "(ADT|CHD|INF)", message = "{BookingPassenger.passengerTypeCode.invalid}")
    private String passengerTypeCode;

    /**
     * 跟随乘客的id，如婴儿跟随的成人id
     */
    private Long refPersonId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdExpiryDate() {
        return idExpiryDate;
    }

    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getPassengerTypeCode() {
        return passengerTypeCode;
    }

    public void setPassengerTypeCode(String passengerTypeCode) {
        this.passengerTypeCode = passengerTypeCode;
    }

    public Long getRefPersonId() {
        return refPersonId;
    }

    public void setRefPersonId(Long refPersonId) {
        this.refPersonId = refPersonId;
    }

}
