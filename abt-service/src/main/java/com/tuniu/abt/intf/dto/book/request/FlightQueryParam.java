package com.tuniu.abt.intf.dto.book.request;

import java.io.Serializable;
import java.util.List;

/**
 * 原始查询对象
 * Created by chengyao on 2016/2/1.
 */
public class FlightQueryParam implements Serializable {

    private static final long serialVersionUID = -3180084492115226592L;

    private Integer adtcnt;

    private Integer chdcnt;

    private Integer infcnt;

    private Integer seatTypeCode;

    private String transfer;

    private List<TripSegments> tripSegments;

    public Integer getAdtcnt() {
        return adtcnt;
    }

    public void setAdtcnt(Integer adtcnt) {
        this.adtcnt = adtcnt;
    }

    public Integer getChdcnt() {
        return chdcnt;
    }

    public void setChdcnt(Integer chdcnt) {
        this.chdcnt = chdcnt;
    }

    public Integer getInfcnt() {
        return infcnt;
    }

    public void setInfcnt(Integer infcnt) {
        this.infcnt = infcnt;
    }

    public Integer getSeatTypeCode() {
        return seatTypeCode;
    }

    public void setSeatTypeCode(Integer seatTypeCode) {
        this.seatTypeCode = seatTypeCode;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public List<TripSegments> getTripSegments() {
        return tripSegments;
    }

    public void setTripSegments(List<TripSegments> tripSegments) {
        this.tripSegments = tripSegments;
    }

    public static class TripSegments implements Serializable {
        private static final long serialVersionUID = -7049328839892585559L;

        private String departCity;

        private String departCountry;

        private String arriveCity;

        private String arriveCountry;

        private String departDate;

        public String getDepartCity() {
            return departCity;
        }

        public void setDepartCity(String departCity) {
            this.departCity = departCity;
        }

        public String getDepartCountry() {
            return departCountry;
        }

        public void setDepartCountry(String departCountry) {
            this.departCountry = departCountry;
        }

        public String getArriveCity() {
            return arriveCity;
        }

        public void setArriveCity(String arriveCity) {
            this.arriveCity = arriveCity;
        }

        public String getArriveCountry() {
            return arriveCountry;
        }

        public void setArriveCountry(String arriveCountry) {
            this.arriveCountry = arriveCountry;
        }

        public String getDepartDate() {
            return departDate;
        }

        public void setDepartDate(String departDate) {
            this.departDate = departDate;
        }
    }
}
