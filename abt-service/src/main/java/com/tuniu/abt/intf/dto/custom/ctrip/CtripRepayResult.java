package com.tuniu.abt.intf.dto.custom.ctrip;

import com.tuniu.adapter.flightTicket.vo.etdz.ConfirmVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CtripRepayResult {
    //0成功，1失败，2部分失败
    private int status = 0;

    private Map<String, CtripRepaySingleResult> result = new HashMap<String, CtripRepaySingleResult>();

    private Map<String, BigDecimal> priceMap = new HashMap<String, BigDecimal>();

    private ConfirmVo confirmVo = new ConfirmVo();

    public ConfirmVo getConfirmVo() {
        return confirmVo;
    }

    public void setConfirmVo(ConfirmVo confirmVo) {
        this.confirmVo = confirmVo;
    }

    public Map<String, BigDecimal> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, BigDecimal> priceMap) {
        this.priceMap = priceMap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, CtripRepaySingleResult> getResult() {
        return result;
    }

    public void setResult(Map<String, CtripRepaySingleResult> result) {
        this.result = result;
    }
}