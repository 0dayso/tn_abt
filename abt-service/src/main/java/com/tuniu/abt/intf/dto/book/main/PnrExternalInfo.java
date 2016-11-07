package com.tuniu.abt.intf.dto.book.main;

import java.io.Serializable;

/**
 * 生成pnr的时候，如果旅客中有成人和儿童，并且他们不在一个PNR中，需要为儿童添加SSR项的备注信息
 * 
 * @author baodawei
 * 
 */
public class PnrExternalInfo implements Serializable {

	private static final long serialVersionUID = 2750045744839718419L;

	// 成人所在的舱位
	private String adultSeatCode;

	// 成人的PNR
	String adultPnr;
	
	public String getAdultPnr() {
        return adultPnr;
    }

    public void setAdultPnr(String adultPnr) {
		this.adultPnr = adultPnr;
	}

	public String getAdultSeatCode() {
		return adultSeatCode;
	}

	public void setAdultSeatCode(String adultSeatCode) {
		this.adultSeatCode = adultSeatCode;
	}
	
}
