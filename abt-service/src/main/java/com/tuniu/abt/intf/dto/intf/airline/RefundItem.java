package com.tuniu.abt.intf.dto.intf.airline;

public class RefundItem {
	private double refundAmount; // 退票金额（退还给用户的金额）
	private double refundFee; 	// 退票手续费（收取用户的手续费）
	private String refundDesc; 	// 退票描述

	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public double getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(double refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundDesc() {
		return refundDesc;
	}
	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}
	
}
