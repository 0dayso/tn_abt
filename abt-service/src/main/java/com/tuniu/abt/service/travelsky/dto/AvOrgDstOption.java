package com.tuniu.abt.service.travelsky.dto;

import java.util.ArrayList;
import java.util.List;

public class AvOrgDstOption {
    
    /**
     * 航段信息
     */
    private List<AvFlightSegment> segments;

    public List<AvFlightSegment> getSegments() {
        if (null == segments) {
            segments = new ArrayList<AvFlightSegment>();
        }
        return segments;
    }

    public void setSegments(List<AvFlightSegment> segments) {
        this.segments = segments;
    }

}
