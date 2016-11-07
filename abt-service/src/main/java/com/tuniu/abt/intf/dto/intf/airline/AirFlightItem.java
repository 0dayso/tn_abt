package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class AirFlightItem {

	/**
	 * 主航司，也就是出票航司，格式：HX
	 */
	private String ticketingCarrier;

	/**
	 * 航班号，格式：HX234-HX654//HX456-HX123 其中-代表中转，//代表一段行程
	 */
	private String flightNos;

	/**
	 * 航班路由，格式：SHA-HKG/HKG-BKK//BKK-HKG/HKG-SHA 其中/代表中转，//代表一段行程，-代表点与点之间的连接
	 * 注意路由中的三字码为机场三字码
	 */
	private String flightRoutings;

	/**
	 * 出发到达，格式：09:00-12:30/14:00-16:00//13:50-15:30/16:40-19:00
	 * 其中/代表中转，//代表一段行程，-代表点与点之间的连接 格式和flightRoutings字段保持一致
	 */
	private String datimes;

	/**
	 * 舱位，格式：经济舱E-经济舱Y//经济舱Y-经济舱E 其中-代表中转，//代表一段行程 格式和flightNos字段保持一致
	 */
	private String cabins;

	/**
	 * 舱位，格式：经济舱-经济舱//经济舱-经济舱 其中-代表中转，//代表一段行程 格式和flightNos字段保持一致
	 */
	private String seatTypeCodes;

	/**
	 * 价格信息
	 */
	@NotNull(message = "价格为空！")
	@Valid
	private PriceInfo priceInfo;

	/**
	 * 行程列表 譬如用户搜索上海曼谷往返，则有2段行程：第一段为上海到曼谷，第二段为曼谷到上海
	 */
	@NotNull(message = "行程列表为空！")
	@Size(min = 1, message = "行程列表为空！")
	@Valid
	private List<FlightOption> flightOptions;
	
	/**
	 * 额外信息
	 */
	private String extraInfo;

	/**
	 * 航班结果的唯一标识
	 */
	private String uniqueKey;

	public AirFlightItem() {
		super();
		flightOptions = new ArrayList<FlightOption>();
	}

	public String getFlightNos() {
		return flightNos;
	}

	public void setFlightNos(String flightNos) {
		this.flightNos = flightNos;
	}

	public String getFlightRoutings() {
		return flightRoutings;
	}

	public void setFlightRoutings(String flightRoutings) {
		this.flightRoutings = flightRoutings;
	}

	public String getDatimes() {
		return datimes;
	}

	public void setDatimes(String datimes) {
		this.datimes = datimes;
	}

	public String getCabins() {
		return cabins;
	}

	public void setCabins(String cabins) {
		this.cabins = cabins;
	}

	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	public List<FlightOption> getFlightOptions() {
		return flightOptions;
	}

	public void setFlightOptions(List<FlightOption> flightOptions) {
		this.flightOptions = flightOptions;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getTicketingCarrier() {
		return ticketingCarrier;
	}

	public void setTicketingCarrier(String ticketingCarrier) {
		this.ticketingCarrier = ticketingCarrier;
	}

	public String getSeatTypeCodes() {
		return seatTypeCodes;
	}

	public void setSeatTypeCodes(String seatTypeCodes) {
		this.seatTypeCodes = seatTypeCodes;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	/**
	 * 遍历每一段的座位数，取最小值
	 */
	public int getMinSeatCount() {
		
		if (this.flightOptions == null || this.flightOptions.size() == 0) {
			return -1;
		}
		
		int minCount = Integer.MAX_VALUE;
		for (FlightOption fo : this.flightOptions) {
			
			for (TravelSegment ts : fo.getTravelSegments()) {
				int c = ts.getCabin().getSeatStatus();
				if (c>-1&&c < minCount) {
					minCount = c;
				}
			}
		}
		if(minCount== Integer.MAX_VALUE)
			minCount=-1;
		return minCount;
	}
}
