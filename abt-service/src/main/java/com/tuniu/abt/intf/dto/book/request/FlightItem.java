package com.tuniu.abt.intf.dto.book.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class FlightItem  implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 8457269872330855406L;
    
    /**
     * 行程信息
     */
    @NotEmpty(message = "{FlightItem.flightOptions.notEmpty}")
    @Valid
    List<FlightOption> flightOptions;
    
    /**
     * 价格信息
     */
    PriceInfo priceInfo;

    public List<FlightOption> getFlightOptions() {
        return flightOptions;
    }

    public void setFlightOptions(List<FlightOption> flightOptions) {
        this.flightOptions = flightOptions;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

}
