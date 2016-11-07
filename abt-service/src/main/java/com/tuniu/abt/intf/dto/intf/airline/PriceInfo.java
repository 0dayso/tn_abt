package com.tuniu.abt.intf.dto.intf.airline;

import javax.validation.Valid;

public class PriceInfo {

	/**
	 * 成人价格
	 */
	@Valid
	private PriceItem adultPrice;
	
	/**
	 * 儿童价格
	 */
	@Valid
	private PriceItem childPrice;
	
	/**
	 * 婴儿价格
	 */
	@Valid
	private PriceItem infantPrice;
	
	/**
	 * 销售限制
	 */
	@Valid
	private String saleLimit;
	
	/**后返信息 目前只是abacus支持
	 * 
	 */

	public PriceItem getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(PriceItem adultPrice) {
		this.adultPrice = adultPrice;
	}
	public PriceItem getChildPrice() {
		return childPrice;
	}
	public void setChildPrice(PriceItem childPrice) {
		this.childPrice = childPrice;
	}
	public PriceItem getInfantPrice() {
		return infantPrice;
	}
	public void setInfantPrice(PriceItem infantPrice) {
		this.infantPrice = infantPrice;
	}
    public String getSaleLimit() {
        return saleLimit;
    }
    public void setSaleLimit(String saleLimit) {
        this.saleLimit = saleLimit;
    }
	
	
}
