package com.tuniu.abt.service.travelsky.ibe.converter;

import com.travelsky.ibe.client.pnr.TRFDResult;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;

import java.math.BigDecimal;

/**
 * Created by chengyao on 2016/4/16.
 */
public class IbeRefundConverter {

    public static RefundPriceResponse toPriceResponse(TRFDResult trfdResult) {
        RefundPriceResponse refundResponse = new RefundPriceResponse();
        refundResponse.setGrossRefund(BigDecimal.valueOf(trfdResult.getGrossRefund()));
        refundResponse.setNetRefund(BigDecimal.valueOf(trfdResult.getNetRefund()));
        refundResponse.setDeduction(BigDecimal.valueOf(trfdResult.getDeduction()));

        BigDecimal taxAll = new BigDecimal(0);
        int taxCount = trfdResult.getTaxcount();
        for (int i = 0; i < taxCount; i++) {
            taxAll = taxAll.add(BigDecimal.valueOf(trfdResult.getTaxAmount(i)));
        }
        refundResponse.setTaxAll(taxAll);

        refundResponse.setBaseCommissionAmount(BigDecimal.valueOf(trfdResult.getCommission()));
        return refundResponse;
    }
}
