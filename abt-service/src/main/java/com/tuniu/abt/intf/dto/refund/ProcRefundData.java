package com.tuniu.abt.intf.dto.refund;

import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengyao on 2016/3/28.
 */
public class ProcRefundData implements Serializable {

    private static final long serialVersionUID = -9200873414062275873L;

    private ReqRefund reqRefund;

    private AbtTicketRequest abtTicketRequest;

    private List<AbtTicketMain> refundTicketMains;

    private AbtRefundOrder abtRefundOrder;

    private List<AbtRefundItem> abtRefundItems;

    public AbtRefundOrder getAbtRefundOrder() {
        return abtRefundOrder;
    }

    public void setAbtRefundOrder(AbtRefundOrder abtRefundOrder) {
        this.abtRefundOrder = abtRefundOrder;
    }

    public List<AbtRefundItem> getAbtRefundItems() {
        return abtRefundItems;
    }

    public void setAbtRefundItems(List<AbtRefundItem> abtRefundItems) {
        this.abtRefundItems = abtRefundItems;
    }

    public ReqRefund getReqRefund() {
        return reqRefund;
    }

    public void setReqRefund(ReqRefund reqRefund) {
        this.reqRefund = reqRefund;
    }

    public AbtTicketRequest getAbtTicketRequest() {
        return abtTicketRequest;
    }

    public void setAbtTicketRequest(AbtTicketRequest abtTicketRequest) {
        this.abtTicketRequest = abtTicketRequest;
    }

    public List<AbtTicketMain> getRefundTicketMains() {
        return refundTicketMains;
    }

    public void setRefundTicketMains(List<AbtTicketMain> refundTicketMains) {
        this.refundTicketMains = refundTicketMains;
    }
}
