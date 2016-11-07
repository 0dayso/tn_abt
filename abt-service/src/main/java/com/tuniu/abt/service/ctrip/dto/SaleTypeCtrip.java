package com.tuniu.abt.service.ctrip.dto;

/**
 * Created by lanlugang on 2016/4/21.
 */
public enum SaleTypeCtrip {
    BUSINESS_PRIORITY("BusinessPriority", "1"), // 商务优选
    TRAVEL_PACKAGE("TravelPackage", "2"), // 旅行套餐
    PRIORITY_PACKAGE("PriorityPackage", "3"), // 优选套餐
    AIRLINE_MARKETING("airlineMarketing", "4"), // 航司直连
    NORMAL("Normal", "5"), // 普通产品
    BUSINESS_PRODUCT("BusinessProduct", "6"), // 商旅产品
    NA("", "0"); // 默认值

    private String value;
    private String code;

    SaleTypeCtrip(String value, String code) {
        this.value = value;
        this.code = code;
    }

    /**
     * 未匹配到对应的类型，则返回默认值
     * @param saleType
     * @return
     */
    public static String getCodeFromValue(String saleType) {
        saleType = (saleType == null) ? "" : saleType.trim();
        for (SaleTypeCtrip saleTypeCtrip : SaleTypeCtrip.values()) {
            if (saleType.equalsIgnoreCase(saleTypeCtrip.getValue())) {
                return saleTypeCtrip.code;
            }
        }
        return SaleTypeCtrip.NA.code;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

}
