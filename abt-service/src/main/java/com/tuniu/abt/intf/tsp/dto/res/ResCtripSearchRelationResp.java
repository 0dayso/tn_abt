package com.tuniu.abt.intf.tsp.dto.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuniu.adapter.flightTicket.domain.CtripRuleInfo;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeatPrice;

import java.util.List;

/**
 * Created by chengyao on 2016/4/5.
 */
public class ResCtripSearchRelationResp {

    private Float cabinPrice;

    @JSONField(name = "priceDomesticList")
    @JsonProperty("priceDomesticList")
    private List<FlightIndivPrice> prices;

    @JSONField(name = "ctripRuleInfoList")
    @JsonProperty("ctripRuleInfoList")
    private List<CtripRuleInfo> rules;

    @JSONField(name = "ctripSeatPriceList")
    @JsonProperty("ctripSeatPriceList")
    private List<FlightIndivSeatPrice> SeatPrices;

    private Integer productSource;

    private Integer subsidy;

    private String airlineCompanyIataCode;

    public List<FlightIndivSeatPrice> getSeatPrices() {
        return SeatPrices;
    }

    public void setSeatPrices(List<FlightIndivSeatPrice> seatPrices) {
        SeatPrices = seatPrices;
    }

    public Float getCabinPrice() {
        if (cabinPrice == null || cabinPrice < 0) return 0f;
        return cabinPrice;
    }

    public void setCabinPrice(Float cabinPrice) {
        this.cabinPrice = cabinPrice;
    }

    public List<FlightIndivPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<FlightIndivPrice> prices) {
        this.prices = prices;
    }

    public List<CtripRuleInfo> getRules() {
        return rules;
    }

    public void setRules(List<CtripRuleInfo> rules) {
        this.rules = rules;
    }

    public Integer getProductSource() {
        return getSafeValue(productSource);
    }

    public void setProductSource(Integer productSource) {
        this.productSource = productSource;
    }

    public Integer getSubsidy() {
        return getSafeValue(subsidy);
    }

    public void setSubsidy(Integer subsidy) {
        this.subsidy = subsidy;
    }

    private int getSafeValue(Integer integer) {
        if (integer == null || integer < 0) {
            return 0;
        }
        return integer;
    }

    public String getAirlineCompanyIataCode() {
        return airlineCompanyIataCode;
    }

    public void setAirlineCompanyIataCode(String airlineCompanyIataCode) {
        this.airlineCompanyIataCode = airlineCompanyIataCode;
    }
}
