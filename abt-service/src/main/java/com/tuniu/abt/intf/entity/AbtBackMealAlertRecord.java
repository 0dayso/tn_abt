package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "abt_back_meal_alert_record")
public class AbtBackMealAlertRecord {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 退改规则ID
     */
    @Column(name = "back_meal_id")
    private Long backMealId;

    /**
     * 警告类型，0=失效前1天告警，1=失效后1小时告警
     */
    @Column(name = "alert_type")
    private Integer alertType;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    public AbtBackMealAlertRecord(Long id, Long backMealId, Integer alertType, Date addTime) {
        this.id = id;
        this.backMealId = backMealId;
        this.alertType = alertType;
        this.addTime = addTime;
    }

    public AbtBackMealAlertRecord() {
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
     * 获取退改规则ID
     *
     * @return back_meal_id - 退改规则ID
     */
    public Long getBackMealId() {
        return backMealId;
    }

    /**
     * 设置退改规则ID
     *
     * @param backMealId 退改规则ID
     */
    public void setBackMealId(Long backMealId) {
        this.backMealId = backMealId;
    }

    /**
     * 获取警告类型，0=失效前1天告警，1=失效后1小时告警
     *
     * @return alert_type - 警告类型，0=失效前1天告警，1=失效后1小时告警
     */
    public Integer getAlertType() {
        return alertType;
    }

    /**
     * 设置警告类型，0=失效前1天告警，1=失效后1小时告警
     *
     * @param alertType 警告类型，0=失效前1天告警，1=失效后1小时告警
     */
    public void setAlertType(Integer alertType) {
        this.alertType = alertType;
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
}