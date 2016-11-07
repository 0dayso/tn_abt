package com.tuniu.abt.intf.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "abt_book_contact")
public class AbtBookContact implements Serializable {
    private static final long serialVersionUID = 6965272821650917785L;

    /**
     * abt_booking_request表ID
     */
    @Id
    @Column(name = "request_id")
    private Long requestId;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 联系人电话
     */
    private String tel;

    /**
     * 联系人邮箱
     */
    private String email;

    public AbtBookContact(Long requestId, String name, String tel, String email) {
        this.requestId = requestId;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public AbtBookContact() {
        super();
    }

    /**
     * 获取abt_booking_request表ID
     *
     * @return request_id - abt_booking_request表ID
     */
    public Long getRequestId() {
        return requestId;
    }

    /**
     * 设置abt_booking_request表ID
     *
     * @param requestId abt_booking_request表ID
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /**
     * 获取联系人姓名
     *
     * @return name - 联系人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置联系人姓名
     *
     * @param name 联系人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取联系人电话
     *
     * @return tel - 联系人电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系人电话
     *
     * @param tel 联系人电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 获取联系人邮箱
     *
     * @return email - 联系人邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置联系人邮箱
     *
     * @param email 联系人邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}