package com.tuniu.abt.intf.dto.aop;

import java.io.Serializable;

/**
 * aop 人员信息
 *
 * 请求不再需要人员信息
 * Created by chengyao on 2016/3/24.
 */
@Deprecated
public class AopTicketSyncPerson implements Serializable {

    private static final long serialVersionUID = -144568309057760398L;

    private Long id;

    private String name;

    private Integer identityType;

    private String identityNo;

    private String birthday;

    private Integer sex;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
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
