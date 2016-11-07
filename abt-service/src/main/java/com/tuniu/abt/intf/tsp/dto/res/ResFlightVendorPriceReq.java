package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResFlightVendorPriceReq implements Serializable {
    
    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 4767032427771648011L;

    private int vendorId;
    
    private List<ResSegment> segments;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public List<ResSegment> getSegments() {
        if (null == segments) {
            segments = new ArrayList<ResSegment>();
        }
        return segments;
    }

    public void setSegments(List<ResSegment> segments) {
        this.segments = segments;
    }
    
}
