package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.constraints.NotNull;

public class CabinInfo {

	/**
	 * 舱位，譬如：R，Y
	 * 内部使用
	 */
	@NotNull(message = "舱位为空！")
	private String cabinClass;
	
	/**
	 * 舱等，譬如：经济舱、公务舱、头等舱
	 * 供外部接口使用，使用类似于：SeatType.Economy.getName() 这样的代码来赋值
	 */
	@NotNull(message = "舱位为空！")
	private String seatType;
	
	/**
	 * 舱等代码：0：经济舱 1：超经 2：公务舱 3：头等舱
	 * 供外部接口使用，使用类似于：SeatType.Economy.getCode() 这样的代码来赋值
	 */
	private int seatTypeCode;
	
	/**
	 * 舱位余位：-1 表示未知
	 */
	private int seatStatus=-1;
	
	/**
	 * 行李信息
	 */
	private String baggageInfo;
	
	public String getCabinClass() {
		return cabinClass;
	}
	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public int getSeatTypeCode() {
		return seatTypeCode;
	}
	public void setSeatTypeCode(int seatTypeCode) {
		this.seatTypeCode = seatTypeCode;
	}
	public int getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(int seatStatus) {
		this.seatStatus = seatStatus;
	}
	public String getBaggageInfo() {
		return baggageInfo;
	}
	public void setBaggageInfo(String baggageInfo) {
		this.baggageInfo = baggageInfo;
	}
}
