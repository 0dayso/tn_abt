package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "abt_back_meal_rule")
public class AbtBackMealRule implements Serializable {

    // FD价
    public static final int CALCULATE_TYPE_FD = 1;
    // 舱等全价
    public static final int CALCULATE_TYPE_FULL = 2;
    // 票面价
    public static final int CALCULATE_TYPE_COST = 3;

    private static final long serialVersionUID = 4762471336593295104L;

    /**
     * 主键, abt_back_meal表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 退票规则字符串
     */
    @Column(name = "re_rule")
    private String reRule;

    /**
     * 换算规则：1=FD运价，2=舱等全价
     */
    @Column(name = "re_calculate_type")
    private Integer reCalculateType;

    /**
     * 退票规则备注
     */
    @Column(name = "re_remark")
    private String reRemark;

    /**
     * 同舱改期规则字符串
     */
    @Column(name = "same_rule")
    private String sameRule;

    /**
     * 换算规则：1=FD运价，2=舱等全价
     */
    @Column(name = "same_calculate_type")
    private Integer sameCalculateType;

    /**
     * 同舱改期规则备注
     */
    @Column(name = "same_remark")
    private String sameRemark;

    public AbtBackMealRule(Long id, String reRule, Integer reCalculateType, String reRemark, String sameRule,
            Integer sameCalculateType, String sameRemark) {
        this.id = id;
        this.reRule = reRule;
        this.reCalculateType = reCalculateType;
        this.reRemark = reRemark;
        this.sameRule = sameRule;
        this.sameCalculateType = sameCalculateType;
        this.sameRemark = sameRemark;
    }

    public AbtBackMealRule() {
        super();
    }

    /**
     * 获取主键, abt_back_meal表id
     *
     * @return id - 主键, abt_back_meal表id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键, abt_back_meal表id
     *
     * @param id 主键, abt_back_meal表id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取退票规则字符串
     *
     * @return re_rule - 退票规则字符串
     */
    public String getReRule() {
        return reRule;
    }

    /**
     * 设置退票规则字符串
     *
     * @param reRule 退票规则字符串
     */
    public void setReRule(String reRule) {
        this.reRule = reRule == null ? null : reRule.trim();
    }

    /**
     * 获取换算规则：1=FD运价，2=舱等全价
     *
     * @return re_calculate_type - 换算规则：1=FD运价，2=舱等全价
     */
    public Integer getReCalculateType() {
        return reCalculateType;
    }

    /**
     * 设置换算规则：1=FD运价，2=舱等全价
     *
     * @param reCalculateType 换算规则：1=FD运价，2=舱等全价
     */
    public void setReCalculateType(Integer reCalculateType) {
        this.reCalculateType = reCalculateType;
    }

    /**
     * 获取退票规则备注
     *
     * @return re_remark - 退票规则备注
     */
    public String getReRemark() {
        return reRemark;
    }

    /**
     * 设置退票规则备注
     *
     * @param reRemark 退票规则备注
     */
    public void setReRemark(String reRemark) {
        this.reRemark = reRemark == null ? null : reRemark.trim();
    }

    /**
     * 获取同舱改期规则字符串
     *
     * @return same_rule - 同舱改期规则字符串
     */
    public String getSameRule() {
        return sameRule;
    }

    /**
     * 设置同舱改期规则字符串
     *
     * @param sameRule 同舱改期规则字符串
     */
    public void setSameRule(String sameRule) {
        this.sameRule = sameRule == null ? null : sameRule.trim();
    }

    /**
     * 获取换算规则：1=FD运价，2=舱等全价
     *
     * @return same_calculate_type - 换算规则：1=FD运价，2=舱等全价
     */
    public Integer getSameCalculateType() {
        return sameCalculateType;
    }

    /**
     * 设置换算规则：1=FD运价，2=舱等全价
     *
     * @param sameCalculateType 换算规则：1=FD运价，2=舱等全价
     */
    public void setSameCalculateType(Integer sameCalculateType) {
        this.sameCalculateType = sameCalculateType;
    }

    /**
     * 获取同舱改期规则备注
     *
     * @return same_remark - 同舱改期规则备注
     */
    public String getSameRemark() {
        return sameRemark;
    }

    /**
     * 设置同舱改期规则备注
     *
     * @param sameRemark 同舱改期规则备注
     */
    public void setSameRemark(String sameRemark) {
        this.sameRemark = sameRemark == null ? null : sameRemark.trim();
    }
}