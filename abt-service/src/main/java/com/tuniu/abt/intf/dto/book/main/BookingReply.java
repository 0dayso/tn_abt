package com.tuniu.abt.intf.dto.book.main;

import java.util.ArrayList;
import java.util.List;

public class BookingReply {

    // 1表示失败，0表示成功
    private int returnStatus;

    // 失败时返回信息，成功时返回空字符串
    private String returnMessage;

    // PNR数据
    List<PnrInfo> pnrInfos;

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public List<PnrInfo> getPnrInfos() {
        if (null == pnrInfos) {
            pnrInfos = new ArrayList<PnrInfo>();
        }
        return pnrInfos;
    }

    public void setPnrInfos(List<PnrInfo> pnrInfos) {
        this.pnrInfos = pnrInfos;
    }
}
