package com.tuniu.abt.intf.dto.book.main;

import com.tuniu.abt.intf.dto.book.request.PriceInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookingData implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -1736522403486641992L;
    
    /**
     * 途牛订单号
     */
    private Long orderId;
    
    /**
     * 下单系统id
     */
    private Integer systemId;
    
    /**
     * 供应商id
     */
    private Integer vendorId;

    /**
     * 乘客数量：成人，儿童，婴儿
     */
    private int[] passengerCount = new int[] {0, 0, 0};

    /**
     * 乘客信息
     */
    private List<Passenger> passengers;

    /**
     * 航程类型:1单程 2往返 3联程 4往返特惠 5联程特惠
     */
    private Integer flightType;
    
    /**
     * 航段信息
     */
    private List<Segment> segments;

    /**
     * 价格信息
     */
    private PriceInfo priceInfo;
    
    /**
     * 政策平台活动代码
     */
    private Integer actionCode;

    /**
     * 政策ID
     */
    private Long policyId;

    /**
     * 乘机人联系电话
     */
    private String contactTel;
    
    /**
     * 成人pnr+舱位(用于儿童pnr备注)
     */
    private PnrExternalInfo pnrExternalInfo;

    /**
     * 实际占位office号
     */
    String occupyOfficeNo;
    
    /**
     * 供应商office号（用于供应商占位）
     */
    private String vendorOfficeNo;

    /**
     * 出票office号（在占位pnr中备注授权）
     */
    private String etdzOfficeNo;

    /**
     * 儿童是否使用成人运价
     */
    private boolean chdUseAdtPrice;

    /**
     * 占位人员类型：1 ADT ; 2 CHD
     */
    private int occupyType;

    private boolean hasBaby;

    /**
     * 携程下单的productID
     */
    private String productID;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public int[] getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int[] passengerCount) {
        this.passengerCount = passengerCount;
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

    public Integer getFlightType() {
        return flightType;
    }

    public void setFlightType(Integer flightType) {
        this.flightType = flightType;
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

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public Integer getActionCode() {
        return actionCode;
    }

    public void setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public PnrExternalInfo getPnrExternalInfo() {
        return pnrExternalInfo;
    }

    public void setPnrExternalInfo(PnrExternalInfo pnrExternalInfo) {
        this.pnrExternalInfo = pnrExternalInfo;
    }

    public String getOccupyOfficeNo() {
        return occupyOfficeNo;
    }

    public void setOccupyOfficeNo(String occupyOfficeNo) {
        this.occupyOfficeNo = occupyOfficeNo;
    }

    public String getVendorOfficeNo() {
        return vendorOfficeNo;
    }

    public void setVendorOfficeNo(String vendorOfficeNo) {
        this.vendorOfficeNo = vendorOfficeNo;
    }

    public String getEtdzOfficeNo() {
        return etdzOfficeNo;
    }

    public void setEtdzOfficeNo(String etdzOfficeNo) {
        this.etdzOfficeNo = etdzOfficeNo;
    }

    public boolean isChdUseAdtPrice() {
        return chdUseAdtPrice;
    }

    public void setChdUseAdtPrice(boolean chdUseAdtPrice) {
        this.chdUseAdtPrice = chdUseAdtPrice;
    }

    public int getOccupyType() {
        return occupyType;
    }

    public void setOccupyType(int occupyType) {
        this.occupyType = occupyType;
    }

    public boolean isHasBaby() {
        return hasBaby;
    }

    public void setHasBaby(boolean hasBaby) {
        this.hasBaby = hasBaby;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
