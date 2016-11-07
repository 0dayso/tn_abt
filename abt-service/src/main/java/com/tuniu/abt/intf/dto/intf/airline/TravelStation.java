package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.constraints.Pattern;

public class TravelStation {

	/**
	 * 机场三字码，譬如：PVG
	 */
	@Pattern(regexp = "[A-Z]{3}", message = "机场三字码格式错误！")
	private String airportCode;
	
	/**
	 * 机场名称，譬如：浦东国际机场
	 */
	private String airportName;
	
	/**
	 * 航站楼，譬如：T1
	 */
	private String terminal;
	
	/**
	 * 城市三字码，譬如：SHA
	 */
	private String cityCode;
	
	/**
	 * 城市名称，譬如：上海
	 */
	private String cityName;
	
	/**
	 * 时区，譬如：+8:00
	 */
	private String timeZone;
	
	/**
	 * 日期，譬如：2015-11-30
	 */
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "日期格式错误！")
	private String date;
	
	/**
	 * 时间，譬如：15:30
	 */
	@Pattern(regexp = "\\d{2}:\\d{2}", message = "时间格式错误！")
	private String time;
	
	public TravelStation() {}
	
	public TravelStation(String airPort, String date, String time){
		this.setAirportCode(airPort);
		this.setDate(date);
		this.setTime(time);
	}
	
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
