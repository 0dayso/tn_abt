package com.tuniu.abt.intf.dto.change;

import java.io.Serializable;

/**
 * Created by chengyao on 2016/5/3.
 */
public class RespChangeItem implements Serializable {

    private static final long serialVersionUID = -436347814051785812L;

    /**
     * 新的pnr
     */
    private String pnr;

    /**
     * 新的票号
     */
    private String ticketNo;

    // no
    private Object segments;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Object getSegments() {
        return segments;
    }

    public void setSegments(Object segments) {
        this.segments = segments;
    }
}
