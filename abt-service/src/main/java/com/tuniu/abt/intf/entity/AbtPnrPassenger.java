package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "abt_pnr_passenger")
public class AbtPnrPassenger implements Serializable {
    public static final int PASSENGER_TYPE_ADULT = 1;
    public static final int PASSENGER_TYPE_CHILD = 2;
    public static final int PASSENGER_TYPE_BABY = 3;

    public static final int STATUS_INIT = 0;
    public static final int STATUS_DEL = 1;
    public static final int STATUS_DEL_FAIL = 2;

    private static final long serialVersionUID = -5045282401063786908L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联abt_pnr_main id
     */
    @Column(name = "pnr_id")
    private Long pnrId;

    /**
     * 乘客姓名
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 占位名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 乘客的名
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 乘客的姓
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 乘客类型  1成人 2儿童 3婴儿
     */
    @Column(name = "passenger_type")
    private Integer passengerType;

    /**
     * 关联乘机人id
     */
    @Column(name = "ref_psg_id")
    private Long refPsgId;

    /**
     * 证件类型 1 身份证2 护照3 军官证..
     */
    @Column(name = "identity_type")
    private Integer identityType;

    /**
     * 乘客fab系统id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * 出生日期 yyyyMMdd
     */
    @Column(name = "birth_date")
    private String birthDate;

    /**
     * 乘客的性别
     */
    private Integer gender;

    /**
     * 状态：0=初始，1=已取消
     */
    private Integer status;

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

    public AbtPnrPassenger(Long id, Long pnrId, String fullName, String bookName, String firstName, String lastName, Integer passengerType, Long refPsgId, Integer identityType, Long personId, String birthDate, Integer gender, Integer status, Date addTime, Date updateTime) {
        this.id = id;
        this.pnrId = pnrId;
        this.fullName = fullName;
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passengerType = passengerType;
        this.refPsgId = refPsgId;
        this.identityType = identityType;
        this.personId = personId;
        this.birthDate = birthDate;
        this.gender = gender;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtPnrPassenger() {
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
     * 获取关联abt_pnr_main id
     *
     * @return pnr_id - 关联abt_pnr_main id
     */
    public Long getPnrId() {
        return pnrId;
    }

    /**
     * 设置关联abt_pnr_main id
     *
     * @param pnrId 关联abt_pnr_main id
     */
    public void setPnrId(Long pnrId) {
        this.pnrId = pnrId;
    }

    /**
     * 获取乘客姓名
     *
     * @return full_name - 乘客姓名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置乘客姓名
     *
     * @param fullName 乘客姓名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 获取占位名
     *
     * @return book_name - 占位名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置占位名
     *
     * @param bookName 占位名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * 获取乘客的姓
     *
     * @return first_name - 乘客的姓
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置乘客的姓
     *
     * @param firstName 乘客的姓
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * 获取乘客的名
     *
     * @return last_name - 乘客的名
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置乘客的名
     *
     * @param lastName 乘客的名
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * 获取乘客类型  1成人 2儿童 3婴儿
     *
     * @return passenger_type - 乘客类型  1成人 2儿童 3婴儿
     */
    public Integer getPassengerType() {
        return passengerType;
    }

    /**
     * 设置乘客类型  1成人 2儿童 3婴儿
     *
     * @param passengerType 乘客类型  1成人 2儿童 3婴儿
     */
    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    /**
     * 获取关联乘机人id
     *
     * @return ref_psg_id - 关联乘机人id
     */
    public Long getRefPsgId() {
        return refPsgId;
    }

    /**
     * 设置关联乘机人id
     *
     * @param refPsgId 关联乘机人id
     */
    public void setRefPsgId(Long refPsgId) {
        this.refPsgId = refPsgId;
    }

    /**
     * 获取证件类型 1 身份证2 护照3 军官证..
     *
     * @return identity_type - 证件类型 1 身份证2 护照3 军官证..
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * 设置证件类型 1 身份证2 护照3 军官证..
     *
     * @param identityType 证件类型 1 身份证2 护照3 军官证..
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    /**
     * 获取乘客fab系统id
     * @return
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置乘客fab系统id
     * @param personId
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取出生日期 yyyyMMdd
     *
     * @return birth_date - 出生日期 yyyyMMdd
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 设置出生日期 yyyyMMdd
     *
     * @param birthDate 出生日期 yyyyMMdd
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate == null ? null : birthDate.trim();
    }

    /**
     * 获取乘客的性别
     *
     * @return gender - 乘客的性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置乘客的性别
     *
     * @param gender 乘客的性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取状态：0=初始，1=已取消
     *
     * @return status - 状态：0=初始，1=已取消
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0=初始，1=已取消
     *
     * @param status 状态：0=初始，1=已取消
     */
    public void setStatus(Integer status) {
        this.status = status;
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