package com.tuniu.abt.intf.dto.book.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class FlightOption implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -7533563910805727725L;
    
    /**
     * 航段信息
     */
    @NotEmpty(message = "{FlightOption.travelSegments.notEmpty}")
    @Valid
    List<TravelSegment> travelSegments;

    public List<TravelSegment> getTravelSegments() {
        return travelSegments;
    }

    public void setTravelSegments(List<TravelSegment> travelSegments) {
        this.travelSegments = travelSegments;
    }
    
}
