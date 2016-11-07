package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FlightQueryParam {

	/**
	 * 要搜索的渠道代码，譬如春秋航空为9C
	 */
	private String channel;
	
	/**
	 * 要查询的舱位，0：所有 1：经济舱 2：公务舱 3：头等舱，默认为查询所有航班
	 */
	private int seatTypeCode;

	/**
	 * 是否查询转机，"0" 为直飞，"1" 为一次转机，默认为 null，查询所有航班
	 */
	private String transfer;
	
	/**
	 * 用户行程
	 */
	@NotNull(message = "请输入用户行程！")
	@Size(min = 1, message = "请输入用户行程！")
	@Valid
	private UTripSegment[] tripSegments;
	
	/**
	 * 成人数
	 */
	@Min(value = 0, message = "成人数不能小于0！")
	private int adtcnt;
	
	/**
	 * 儿童数
	 */
	@Min(value = 0, message = "儿童数不能小于0！")
	private int chdcnt;
	
	/**
	 * 婴儿数
	 */
	@Min(value = 0, message = "婴儿数不能小于0！")
	private int infcnt;
	
	/**转机点 for ibe searchone
	 */
	private List<String> connectionLocations;
	
	/**
	 * 日历搜索的要设置为true
	 */
	private boolean calendarSearch;
	
	
	/**
	 * 显示搜索结果数，取值大于0
	 */
	@Min(value = 0, message = "搜索结果数，取值大于0！")
	private int searchLimit = 500;
	
	
	/**指定运价发布航司查询，，为null表示不指定
	 * 
	 */
	private List<String> faresAllowedCarriers;
	
    private String currency;
 
	public boolean isInternational() {		
		if (getTripSegments() == null || getTripSegments().length <= 0) {
			return false;
		}
	
		UTripSegment firstSegment = getTripSegments()[0];
		return firstSegment.isInternational();
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<String> getFaresAllowedCarriers() {
		return faresAllowedCarriers;
	}
	public void setFaresAllowedCarriers(List<String> faresAllowedCarriers) {
		this.faresAllowedCarriers = faresAllowedCarriers;
	}
	public int getSearchLimit() {
		return searchLimit;
	}
	public void setSearchLimit(int searchLimit) {
		this.searchLimit = searchLimit;
	}
	public boolean isCalendarSearch() {
		return calendarSearch;
	}
	public void setCalendarSearch(boolean calendarSearch) {
		this.calendarSearch = calendarSearch;
	}
	
	public List<String> getConnectionLocations() {
		return connectionLocations;
	}
	public void setConnetcionLocations(List<String> connectionLocations) {
		this.connectionLocations = connectionLocations;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public UTripSegment[] getTripSegments() {
		return tripSegments;
	}
	public void setTripSegments(UTripSegment[] tripSegments) {
		this.tripSegments = tripSegments;
	}
	public int getAdtcnt() {
		return adtcnt;
	}
	public void setAdtcnt(int adtcnt) {
		this.adtcnt = adtcnt;
	}
	public int getChdcnt() {
		return chdcnt;
	}
	public void setChdcnt(int chdcnt) {
		this.chdcnt = chdcnt;
	}
	public int getInfcnt() {
		return infcnt;
	}
	public void setInfcnt(int infcnt) {
		this.infcnt = infcnt;
	}
	public int getSeatTypeCode() {
		return seatTypeCode;
	}
	public void setSeatTypeCode(int seatTypeCode) {
		this.seatTypeCode = seatTypeCode;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	
}
