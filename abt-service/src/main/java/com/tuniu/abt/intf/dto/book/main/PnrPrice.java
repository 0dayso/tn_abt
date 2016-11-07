package com.tuniu.abt.intf.dto.book.main;

public class PnrPrice {

    /**
     * 人员类型，1=成人，2=儿童，3=婴儿
     */
    private Integer passengerType;
    
    /**
     * 价格类型，1=成人，2=儿童，3=婴儿
     */
    private Integer priceType;
    
    /**
     * 票面价
     */
    private Float orgPrice;
    
    /**
     * 售卖价
     */
    private Float salePrice;
    
    /**
     * 结算价
     */
    private Float floorPrice;
    
    /**
     * 返点
     */
    private Float backBate;
    
    /**
     * 返现
     */
    private Float backCash;
    
    /**
     * 手续费
     */
    private Float invoiceCost;
    
    /**
     * 燃油附加费
     */
    private Float fuelSurcharge;
    
    /**
     * 机场建设费
     */
    private Float airportTax;
    
    /**
     * 销控平台的调价区间id
     */
    private Long saleId;

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Float getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(Float orgPrice) {
        this.orgPrice = orgPrice;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Float getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Float floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Float getBackBate() {
        return backBate;
    }

    public void setBackBate(Float backBate) {
        this.backBate = backBate;
    }

    public Float getBackCash() {
        return backCash;
    }

    public void setBackCash(Float backCash) {
        this.backCash = backCash;
    }

    public Float getInvoiceCost() {
        return invoiceCost;
    }

    public void setInvoiceCost(Float invoiceCost) {
        this.invoiceCost = invoiceCost;
    }

    public Float getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(Float fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Float getAirportTax() {
        return airportTax;
    }

    public void setAirportTax(Float airportTax) {
        this.airportTax = airportTax;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
    
}
