package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class PriceItem {
	
	/**
	 * 售卖价格
	 */
	@Min(value = 0, message = "价格不能小于0！")
	private int price;
	
	/**
	 * 舱等全价，提供给适配做计算折扣使用。
	 */
	@Min(value = 0, message = "价格不能小于0！")
	private int fullPrice;
	
	/**
	 * 税费：燃油费
	 */
	@Min(value = 0, message = "税费不能小于0！")
	private int fuelTax;
	
	/**
	 * 税费：机建费
	 */
	@Min(value = 0, message = "税费不能小于0！")
	private int airportTax;

	/**
	 * 税费：(兼容)其他
	 */
	@Min(value = 0, message = "税费不能小于0！")
	private int tax;
	
	
	/**
	 * 汇率三字码，默认为：CNY（人民币）
	 */
	@Pattern(regexp = "[A-Z]{3}", message = "货币类型三字码格式错误！")
	private String currency;
	
	/**
	 * 退改签信息 单程只有一个，往返第一个是去程，第二个是返程
	 */
	private String refundRuleKey;
	
	/**
	 * 附加信息
	 */
	private String extraInfo;
//	/**
//	 * 人民币价格
//	 */
//	private int cnyPrice;
//	/**
//	 * 兑人民币的汇率
//	 */
//	private double cnyRate;
//	/**
//	 * 人民币税费
//	 */
//	private int cnyTax;
//	
//	private String commissionDescription;

	/**返点信息 可以为负数 加价
	 * 
	 */
//	private double commissionRate;
	
	private String passengerType;
	
//	public String getCommissionDescription() {
//		return commissionDescription;
//	}
//	public void setCommissionDescription(String commissionDescription) {
//		this.commissionDescription = commissionDescription;
//	}
//	public double getCommissionRate() {
//		return commissionRate;
//	}
//	public void setCommissionRate(double commissionRate) {
//		this.commissionRate = commissionRate;
//	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getFuelTax() {
		return fuelTax;
	}
	public void setFuelTax(int fuelTax) {
		this.fuelTax = fuelTax;
	}
	public int getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(int airportTax) {
		this.airportTax = airportTax;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getRefundRuleKey() {
		return refundRuleKey;
	}
	public void setRefundRuleKey(String refundRuleKey) {
		this.refundRuleKey = refundRuleKey;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
//	public int getCnyPrice() {
//		return cnyPrice;
//	}
//	public void setCnyPrice(int cnyPrice) {
//		this.cnyPrice = cnyPrice;
//	}
//	public double getCnyRate() {
//		return cnyRate;
//	}
//	public void setCnyRate(double cnyRate) {
//		this.cnyRate = cnyRate;
//	}
//	public int getCnyTax() {
//		return cnyTax;
//	}
//	public void setCnyTax(int cnyTax) {
//		this.cnyTax = cnyTax;
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + price;
		result = prime * result + ((refundRuleKey == null) ? 0 : refundRuleKey.hashCode());
		result = prime * result + tax;
		result = prime * result + airportTax;
		result = prime * result + fuelTax;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}

		PriceItem other = (PriceItem) obj;
		if (currency == null) {
			return other.currency == null;
		} 
		
		return currency.equals(other.currency) && price == other.price && tax == other.tax
				&& airportTax == other.airportTax && fuelTax == other.fuelTax;
	}
	
	public int getTotalPrice() {
		return price + airportTax + fuelTax + tax;
	}
	public int getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(int fullPrice) {
		this.fullPrice = fullPrice;
	}
}
