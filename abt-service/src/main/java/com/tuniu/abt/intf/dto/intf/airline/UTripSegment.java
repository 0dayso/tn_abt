package com.tuniu.abt.intf.dto.intf.airline;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

public class UTripSegment {

	/**
	 * 出发城市三字码
	 */
	@Pattern(regexp = "[A-Z]{3}", message = "出发城市三字码格式错误！")
	private String departCity;
	
	/**
	 * 出发国家二字码，默认为中国（CN）
	 */
	private String departCountry;
	
	/**
	 * 到达城市三字码
	 */
	@Pattern(regexp = "[A-Z]{3}", message = "到达城市三字码格式错误！")
	private String arriveCity;
	
	/**
	 * 到达国家二字码，默认为中国（CN）
	 */
	private String arriveCountry;
	
	/**
	 * 出发日期
	 */
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "出发日期格式错误！")
	private String departDate;

	
	/**用于日历搜索from 范围是0-7
	 */
	private int flexIntervalFrom;

	/**用于日历搜索to范围是0-7
	 */
	private int flexIntervalTo;
	
	
	public UTripSegment() {}
	
	public UTripSegment(String departCity, String arriveCity, String departDate) {
		super();
		this.departCity = departCity;
		this.arriveCity = arriveCity;
		this.departDate = departDate;
	}
	
	public boolean isInternational(){
		String departCountry = StringUtils.isBlank(getDepartCountry()) ? "CN" : getDepartCountry();
		String arriveCountry = StringUtils.isBlank(getArriveCountry()) ? "CN" : getArriveCountry();
		if (!departCountry.equals("CN") || !arriveCountry.equals("CN")) {
			// 如果出发国家或达到国家都不是中国，则认为是国外航班查询
			return true;
		}
		return false;
	}
	
	public String getDepartCity() {
		return departCity;
	}
	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}
	public String getDepartCountry() {
		return departCountry;
	}
	public void setDepartCountry(String departCountry) {
		this.departCountry = departCountry;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public String getArriveCountry() {
		return arriveCountry;
	}
	public void setArriveCountry(String arriveCountry) {
		this.arriveCountry = arriveCountry;
	}
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	
	public int getFlexIntervalFrom() {
		return flexIntervalFrom;
	}
	public void setFlexIntervalFrom(int flexIntervalFrom) {
		this.flexIntervalFrom = flexIntervalFrom;
	}
	public int getFlexIntervalTo() {
		return flexIntervalTo;
	}
	public void setFlexIntervalTo(int flexIntervalTo) {
		this.flexIntervalTo = flexIntervalTo;
	}
}
