package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "abt_pnr_price")
public class AbtPnrPrice implements Serializable {
    private static final long serialVersionUID = 4836076836718320591L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联abt_booking_pnr id
     */
    @Column(name = "pnr_id")
    private Long pnrId;

    /**
     * 乘客类型，1=成人，2=儿童，3=婴儿
     */
    @Column(name = "passenger_type")
    private Integer passengerType;

    /**
     * 价格类型，1=成人，2=儿童，3=婴儿
     */
    @Column(name = "price_type")
    private Integer priceType;

    /**
     * 票面价
     */
    @Column(name = "org_price")
    private BigDecimal orgPrice;

    /**
     * 售卖价
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    /**
     * 结算价
     */
    @Column(name = "floor_price")
    private BigDecimal floorPrice;

    /**
     * 返点
     */
    @Column(name = "back_bate")
    private Float backBate;

    /**
     * 返现
     */
    @Column(name = "back_cash")
    private Float backCash;

    /**
     * 手续费
     */
    @Column(name = "invoice_cost")
    private Float invoiceCost;

    /**
     * 燃油附加费
     */
    @Column(name = "fuel_surcharge")
    private Float fuelSurcharge;

    /**
     * 机场建设费
     */
    @Column(name = "airport_tax")
    private Float airportTax;

    /**
     * 销控平台的调价区间id
     */
    @Column(name = "sale_id")
    private Long saleId;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public AbtPnrPrice(Long id, Long pnrId, Integer passengerType, Integer priceType, BigDecimal orgPrice, BigDecimal salePrice, BigDecimal floorPrice, Float backBate, Float backCash, Float invoiceCost, Float fuelSurcharge, Float airportTax, Long saleId, Date addTime, Date updateTime) {
        this.id = id;
        this.pnrId = pnrId;
        this.passengerType = passengerType;
        this.priceType = priceType;
        this.orgPrice = orgPrice;
        this.salePrice = salePrice;
        this.floorPrice = floorPrice;
        this.backBate = backBate;
        this.backCash = backCash;
        this.invoiceCost = invoiceCost;
        this.fuelSurcharge = fuelSurcharge;
        this.airportTax = airportTax;
        this.saleId = saleId;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public AbtPnrPrice() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取关联abt_booking_pnr id
     *
     * @return pnr_id - 关联abt_booking_pnr id
     */
    public Long getPnrId() {
        return pnrId;
    }

    /**
     * 设置关联abt_booking_pnr id
     *
     * @param pnrId 关联abt_booking_pnr id
     */
    public void setPnrId(Long pnrId) {
        this.pnrId = pnrId;
    }

    /**
     * 获取乘客类型，1=成人，2=儿童，3=婴儿
     *
     * @return passenger_type - 乘客类型，1=成人，2=儿童，3=婴儿
     */
    public Integer getPassengerType() {
        return passengerType;
    }

    /**
     * 设置乘客类型，1=成人，2=儿童，3=婴儿
     *
     * @param passengerType 乘客类型，1=成人，2=儿童，3=婴儿
     */
    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    /**
     * 获取价格类型，1=成人，2=儿童，3=婴儿
     *
     * @return price_type - 价格类型，1=成人，2=儿童，3=婴儿
     */
    public Integer getPriceType() {
        return priceType;
    }

    /**
     * 设置价格类型，1=成人，2=儿童，3=婴儿
     *
     * @param priceType 价格类型，1=成人，2=儿童，3=婴儿
     */
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    /**
     * 获取票面价
     *
     * @return org_price - 票面价
     */
    public BigDecimal getOrgPrice() {
        return orgPrice;
    }

    /**
     * 设置票面价
     *
     * @param orgPrice 票面价
     */
    public void setOrgPrice(BigDecimal orgPrice) {
        this.orgPrice = orgPrice;
    }

    /**
     * 获取售卖价
     *
     * @return sale_price - 售卖价
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * 设置售卖价
     *
     * @param salePrice 售卖价
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 获取结算价
     *
     * @return floor_price - 结算价
     */
    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    /**
     * 设置结算价
     *
     * @param floorPrice 结算价
     */
    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * 获取返点
     *
     * @return back_bate - 返点
     */
    public Float getBackBate() {
        return backBate;
    }

    /**
     * 设置返点
     *
     * @param backBate 返点
     */
    public void setBackBate(Float backBate) {
        this.backBate = backBate;
    }

    /**
     * 获取返现
     *
     * @return back_cash - 返现
     */
    public Float getBackCash() {
        return backCash;
    }

    /**
     * 设置返现
     *
     * @param backCash 返现
     */
    public void setBackCash(Float backCash) {
        this.backCash = backCash;
    }

    /**
     * 获取手续费
     *
     * @return invoice_cost - 手续费
     */
    public Float getInvoiceCost() {
        return invoiceCost;
    }

    /**
     * 设置手续费
     *
     * @param invoiceCost 手续费
     */
    public void setInvoiceCost(Float invoiceCost) {
        this.invoiceCost = invoiceCost;
    }

    /**
     * 获取燃油附加费
     *
     * @return fuel_surcharge - 燃油附加费
     */
    public Float getFuelSurcharge() {
        return fuelSurcharge;
    }

    /**
     * 设置燃油附加费
     *
     * @param fuelSurcharge 燃油附加费
     */
    public void setFuelSurcharge(Float fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    /**
     * 获取机场建设费
     *
     * @return airport_tax - 机场建设费
     */
    public Float getAirportTax() {
        return airportTax;
    }

    /**
     * 设置机场建设费
     *
     * @param airportTax 机场建设费
     */
    public void setAirportTax(Float airportTax) {
        this.airportTax = airportTax;
    }

    /**
     * 获取销控平台的调价区间id
     *
     * @return sale_id - 销控平台的调价区间id
     */
    public Long getSaleId() {
        return saleId;
    }

    /**
     * 设置销控平台的调价区间id
     *
     * @param saleId 销控平台的调价区间id
     */
    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}