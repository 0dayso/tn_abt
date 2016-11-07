package com.tuniu.abt.service.travelsky.dto;

import java.io.Serializable;

/**
 * Created by lanlugang on 2016/4/11.
 */
public class PatByPnrReq implements Serializable {

    private static final long serialVersionUID = -7524656358948207831L;

    public static final String FARE_TYPE_FD = "";

    public static final String FARE_TYPE_SHOP = "A";

    public static final String PSG_TYPE_ADT = "";

    public static final String PSG_TYPE_CHD = "CH";

    /**
     * pnr号
     */
    private String pnrNo;

    /**
     * 运价的人员类型:成人:"", 儿童:"CH"
     * 如儿童使用成人价，则运价类型为成人
     */
    private String psgType;

    /**
     * fareType: A 查净价 , null 或者 "" 查公布运价
     */
    private String fareType="A";

    /**
     * 是否有婴儿
     */
    private boolean hasBaby;

    /**
     * 大客户编码
     */
    private String clientCode;

    /**
     * office号
     */
    private String officeNo;

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getPsgType() {
        return psgType;
    }

    public void setPsgType(String psgType) {
        this.psgType = psgType;
    }

    public String getFareType() {
        return fareType;
    }

    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    public boolean isHasBaby() {
        return hasBaby;
    }

    public void setHasBaby(boolean hasBaby) {
        this.hasBaby = hasBaby;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }
}
