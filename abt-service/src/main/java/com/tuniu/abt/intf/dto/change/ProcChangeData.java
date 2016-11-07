package com.tuniu.abt.intf.dto.change;

import com.tuniu.abt.intf.entity.AbtChangeItem;
import com.tuniu.abt.intf.entity.AbtChangeOrder;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/5/3.
 */
public class ProcChangeData implements Serializable {

    private static final long serialVersionUID = 4463180603547593488L;

    private ReqChange reqChange;

    private AbtTicketRequest abtTicketRequest;

    private List<AbtTicketMain> abtTicketMains;

    private AbtChangeOrder abtChangeOrder;

    private List<AbtChangeItem> abtChangeItems;

    public List<AbtChangeItem> getAbtChangeItems() {
        return abtChangeItems;
    }

    public void setAbtChangeItems(List<AbtChangeItem> abtChangeItems) {
        this.abtChangeItems = abtChangeItems;
    }

    public AbtChangeOrder getAbtChangeOrder() {
        return abtChangeOrder;
    }

    public void setAbtChangeOrder(AbtChangeOrder abtChangeOrder) {
        this.abtChangeOrder = abtChangeOrder;
    }

    public List<AbtTicketMain> getAbtTicketMains() {
        return abtTicketMains;
    }

    public void setAbtTicketMains(List<AbtTicketMain> abtTicketMains) {
        this.abtTicketMains = abtTicketMains;
    }

    public AbtTicketRequest getAbtTicketRequest() {
        return abtTicketRequest;
    }

    public void setAbtTicketRequest(AbtTicketRequest abtTicketRequest) {
        this.abtTicketRequest = abtTicketRequest;
    }

    public ReqChange getReqChange() {
        return reqChange;
    }

    public void setReqChange(ReqChange reqChange) {
        this.reqChange = reqChange;
    }
}
