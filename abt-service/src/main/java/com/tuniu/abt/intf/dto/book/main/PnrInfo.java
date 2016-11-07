package com.tuniu.abt.intf.dto.book.main;

import java.util.ArrayList;
import java.util.List;

public class PnrInfo {

    // Pnr号(外部订单号)
    private String pnrNo;

    // 关联pnr号(外部主单号)
    private String associatedPnrNo;

    // 1 成人价格预订 2 儿童价格预订，默认为1
    private int occupyType;

    // 清位时间 "yyyy-MM-dd HH:mm:ss"
    private String clearTime;

    // PNR状态组
    private List<String> actionCodes;

    // 航司大编码
    private String airCompanyCode;

    // 生成pnr返回的备注信息
    private List<String> comments;

    // 是否有婴儿
    private boolean hasBaby;

    // 政策ID
    private Long policyId;

    // 政策类型
    private int policyType;

    // 政策平台活动代码
    private Integer dfpActionCode;

    // 航班舱位信息
    private List<Segment> segments;

    // 乘客信息
    private List<Passenger> passengers;
    
    // 置收款号携程专用
    private String externalNo;
    
    // 供应商订单状态
    private String orderStatusDisplay;
    
    // 运价信息
    private List<PnrPrice> prices;

    // pnr中的office号
    private String officeNo;

    /** 运价类型 ADT CHD (存在儿童用成人价的情况) */
    private String patType;

    /** 航司直连航司二字码 */
    private String ticketingCarrier;

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getAssociatedPnrNo() {
        return associatedPnrNo;
    }

    public void setAssociatedPnrNo(String associatedPnrNo) {
        this.associatedPnrNo = associatedPnrNo;
    }

    public int getOccupyType() {
        return occupyType;
    }

    public void setOccupyType(int occupyType) {
        this.occupyType = occupyType;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }

    public List<String> getActionCodes() {
        return actionCodes;
    }

    public void setActionCodes(List<String> actionCodes) {
        this.actionCodes = actionCodes;
    }

    public String getAirCompanyCode() {
        return airCompanyCode;
    }

    public void setAirCompanyCode(String airCompanyCode) {
        this.airCompanyCode = airCompanyCode;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public boolean isHasBaby() {
        return hasBaby;
    }

    public void setHasBaby(boolean hasBaby) {
        this.hasBaby = hasBaby;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }

    public Integer getDfpActionCode() {
        return dfpActionCode;
    }

    public void setDfpActionCode(Integer dfpActionCode) {
        this.dfpActionCode = dfpActionCode;
    }

    public List<Segment> getSegments() {
        if (null == segments) {
            segments = new ArrayList<Segment>();
        }
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Passenger> getPassengers() {
        if (null == passengers) {
            passengers = new ArrayList<Passenger>();
        }
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getOrderStatusDisplay() {
        return orderStatusDisplay;
    }

    public void setOrderStatusDisplay(String orderStatusDisplay) {
        this.orderStatusDisplay = orderStatusDisplay;
    }

    public List<PnrPrice> getPrices() {
        if (null == prices) {
            prices = new ArrayList<PnrPrice>();
        }
        return prices;
    }

    public void setPrices(List<PnrPrice> prices) {
        this.prices = prices;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public String getPatType() {
        return patType;
    }

    public void setPatType(String patType) {
        this.patType = patType;
    }

    public String getTicketingCarrier() {
        return ticketingCarrier;
    }

    public void setTicketingCarrier(String ticketingCarrier) {
        this.ticketingCarrier = ticketingCarrier;
    }
}
