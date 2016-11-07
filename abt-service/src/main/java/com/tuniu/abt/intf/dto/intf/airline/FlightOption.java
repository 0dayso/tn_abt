package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class FlightOption {
	
	/**
	 * 航段列表
	 * 航班列表包含了中转信息，譬如上海到曼谷要经过香港转机，则有两个航段：第一段上海到香港，第二段香港到曼谷
	 */
	@NotNull(message = "航段列表为空！")
	@Size(min = 1, message = "航段列表为空！")
	@Valid
	private List<TravelSegment> travelSegments;
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
	
	
	/**运价基础，分方向
	 * 
	 */
	private String fareBasis;
	
	public FlightOption() {
		super();
		travelSegments = new ArrayList<TravelSegment>();
	}
	
	public String getFareBasis() {
		return fareBasis;
	}
	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}
	public List<TravelSegment> getTravelSegments() {
		return travelSegments;
	}
	public void setTravelSegments(List<TravelSegment> travelSegments) {
		this.travelSegments = travelSegments;
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
	
	public void setDepartureAndArrival() {
		int segLength = getTravelSegments().size();
		if (segLength > 0) {
			setDeparture(getTravelSegments().get(0).getDeparture());
			setArrival(getTravelSegments().get(segLength - 1).getArrival());
		}
	}
}
