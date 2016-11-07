package com.tuniu.abt.intf.rest.fab;

/**
 * FAB接口查询结果中关联人信息
 * Created by chengyao on 2015/11/28.
 */
public class FabTouristRespPerson {

    /**
     * 关联人ID
     */
    private Integer personId;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 证件类型
     * 常量参数：1=身份证 2=护照 3=军官证 4=港澳通行证 6=其他 7=台胞证 8=回乡证 9=户口簿 10 =出生证明 11 台湾通行证
     */
    private Integer identityType;

    /**
     * 证件号码
     */
    private String identityNo;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别 1为男 0为女 9为未知[其余为未知]
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
