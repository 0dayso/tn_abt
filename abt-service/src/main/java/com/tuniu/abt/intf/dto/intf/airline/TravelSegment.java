package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class TravelSegment {
	/**
	 * 航班索引
	 */
	@Min(value = 0, message = "索引不能小于0！")
	private int flightIndex;
	
	/**
	 * 航班号，譬如 AK456
	 * 对应 MarketingAirline 或 TicketingAirline ，市场航司、实际出票航司
	 */
	@Pattern(regexp = "[A-Z0-9]{2}[0-9]+", message = "航班号格式错误！")
	private String flightNO;
	
	/**
	 * 共享航班号
	 * 对应 OperatingAirline 实际承运航司
	 */
	private String codeShare;
	
	/**
	 * 始发站
	 */
	@NotNull(message = "始发站为空！")
	@Valid
	private TravelStation departure;
	
	/**
	 * 到达站
	 */
	@NotNull(message = "到达站为空！")
	@Valid
	private TravelStation arrival;
	
	/**
	 * 经停列表
	 */
	@Valid
	private List<StopInfo> stopList;
	
	/**
	 * 舱位信息
	 */
	@NotNull(message = "舱位为空！")
	@Valid
	private CabinInfo cabin;
	
	/**
	 * 是否包含餐食
	 */
	private boolean hasMeal;
	
	/**
	 * 餐食中文名，譬如：早餐
	 */
	private String mealChineseName;
	
	/**
	 * 机型，譬如：319
	 */
	private String craftType;
	
	/**
	 * 机型名称，譬如：空中客车A319公务机
	 */
    private String craftTypeName;
    
    /**
     * 市场方航空公司 一般用于OTA验舱验价出票，伪直连不用
     */
  	private String marketingAirLine;
	
  	
  	/**一些渠道后续操作需要的信息 比如占位和验价，但前端不需要的，放到这个字段上，后续自己解析
  	 * 
  	 */
  	private String extraInfo;
	
  	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getFlightNO() {
		return flightNO;
	}
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}
	public String getCodeShare() {
		return codeShare;
	}
	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}
	public TravelStation getDeparture() {
		return departure;
	}
	public void setDeparture(TravelStation departure) {
		this.departure = departure;
	}
	public TravelStation getArrival() {
		return arrival;
	}
	public void setArrival(TravelStation arrival) {
		this.arrival = arrival;
	}
	public List<StopInfo> getStopList() {
		return stopList;
	}
	public void setStopList(List<StopInfo> stopList) {
		this.stopList = stopList;
	}
	public CabinInfo getCabin() {
		return cabin;
	}
	public void setCabin(CabinInfo cabin) {
		this.cabin = cabin;
	}
	public boolean isHasMeal() {
		return hasMeal;
	}
	public void setHasMeal(boolean hasMeal) {
		this.hasMeal = hasMeal;
	}
	public String getMealChineseName() {
		return mealChineseName;
	}
	public void setMealChineseName(String mealChineseName) {
		this.mealChineseName = mealChineseName;
	}
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getCraftTypeName() {
		return craftTypeName;
	}
	public void setCraftTypeName(String craftTypeName) {
		this.craftTypeName = craftTypeName;
	}
	public String getMarketingAirLine() {
		return marketingAirLine;
	}
	public void setMarketingAirLine(String marketingAirLine) {
		this.marketingAirLine =marketingAirLine;
	}
	public int getFlightIndex() {
		return flightIndex;
	}
	public void setFlightIndex(int flightIndex) {
		this.flightIndex = flightIndex;
	}
	 
}
