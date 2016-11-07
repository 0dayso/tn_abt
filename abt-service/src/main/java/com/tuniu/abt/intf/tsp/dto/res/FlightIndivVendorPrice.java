package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;


/**
 * @desc 散客票类，包含所有供应商，对应资源数据库中flight_indiv_vendor_price_domestic表
 * @author luwl
 * @date 2015-12-09
 */
public class FlightIndivVendorPrice implements Serializable {
	/**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 421412877892946118L;

    //主键ID
	private Long id;
	
	//基本运价
	private Integer baseFare;
	
	//底价
	private Integer cost;
	
	//票面价
	private Integer printPrice;
	
	//fd价
	private Integer fdPrice;
	
	//税（机建）
	private Integer taxes;
	
	//费（燃油）
	private Integer qcharge;
	
	//货币类型
	private String currencyCode;
	
	//舱位折扣
	private double discount;
	
	//系统运价数据来源
	private String pricingSource;
	
	//旅客类型 ADT 和CHD 等
	private String passengerCode;
	
	//旅客数量
	private Integer passengerQuantity;
	
	//运价基础代码
	private String fareBasicCode; 
	
	//代理费类型，GROSS, NET, BOTH
	private String commissionType;
	
	//代理费百分比
	private Integer commissionPercent;
	
	//行李数量重量
	private String baggage;
	
	//退改签ID
	private Integer ruleId;
	
	//供应商ID
	private Integer vendorId;
	
	//资源ID
	private Integer resId;
		
	//团期
	private String departureDate;
	
	//后续查询退改签规则用
	private String ref1;
	
	//后续查询退改签规则用
	private String ref2;
	
	//修改时间
	private String updateTime;
	
	//添加时间
	private String addTime;
	
	//有效标识
	private Integer delFlag;
	
	private String seatStatus;
	
	private String mainAirCom;
	
	//城市信息
	private String orgCity;
	
	private String dstCity;
	
	private String isback;
	
	private int systemId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Integer baseFare) {
		this.baseFare = baseFare;
	}

	public Integer getTaxes() {
		return taxes;
	}

	public void setTaxes(Integer taxes) {
		this.taxes = taxes;
	}

	public Integer getQcharge() {
		return qcharge;
	}

	public void setQcharge(Integer qcharge) {
		this.qcharge = qcharge;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPricingSource() {
		return pricingSource;
	}

	public void setPricingSource(String pricingSource) {
		this.pricingSource = pricingSource;
	}

	public String getPassengerCode() {
		return passengerCode;
	}

	public void setPassengerCode(String passengerCode) {
		this.passengerCode = passengerCode;
	}

	public Integer getPassengerQuantity() {
		return passengerQuantity;
	}

	public void setPassengerQuantity(Integer passengerQuantity) {
		this.passengerQuantity = passengerQuantity;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public Integer getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(Integer commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	public String getFareBasicCode() {
		return fareBasicCode;
	}

	public void setFareBasicCode(String fareBasicCode) {
		this.fareBasicCode = fareBasicCode;
	}

	public String getBaggage() {
		return baggage;
	}

	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	
	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

    public String getMainAirCom() {
        return mainAirCom;
    }

    public void setMainAirCom(String mainAirCom) {
        this.mainAirCom = mainAirCom;
    }

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(Integer printPrice) {
		this.printPrice = printPrice;
	}

	public Integer getFdPrice() {
		return fdPrice;
	}

	public void setFdPrice(Integer fdPrice) {
		this.fdPrice = fdPrice;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getDstCity() {
		return dstCity;
	}

	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}

	public String getIsback() {
		return isback;
	}

	public void setIsback(String isback) {
		this.isback = isback;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	
}
