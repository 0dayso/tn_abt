package com.tuniu.abt.intf.tsp.dto.res;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chengyao on 2016/1/15.
 */
public class IndividualTravelerFlightReq implements Serializable {

    private static final long serialVersionUID = 2036639270737895362L;

    private int id;

    private Date departureDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
