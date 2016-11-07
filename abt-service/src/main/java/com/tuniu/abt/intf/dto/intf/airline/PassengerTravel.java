package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PassengerTravel {
	
	@NotNull(message = "乘客为空！")
	private Passenger passenger;
	private String ticketNo;
	
	@NotNull(message = "航段列表为空！")
	@Size(min = 1, message = "航段列表为空！")
	@Valid
	private List<TravelSegment> travelSegments;
	
	private PriceItem price; // 购买价格, 可为空
	private RefundItem refundInfo; // 退票信息， 可为空
	
	public PassengerTravel() {
		super();
		travelSegments = new ArrayList<TravelSegment>();
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<TravelSegment> getTravelSegments() {
		return travelSegments;
	}

	public void setTravelSegments(List<TravelSegment> travelSegments) {
		this.travelSegments = travelSegments;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public PriceItem getPrice() {
		return price;
	}

	public void setPrice(PriceItem price) {
		this.price = price;
	}

	public RefundItem getRefundInfo() {
		return refundInfo;
	}

	public void setRefundInfo(RefundItem refundInfo) {
		this.refundInfo = refundInfo;
	}

}
