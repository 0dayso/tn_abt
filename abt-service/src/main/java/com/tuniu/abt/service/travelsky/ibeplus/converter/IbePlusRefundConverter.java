package com.tuniu.abt.service.travelsky.ibeplus.converter;

import com.travelsky.espeed.RefundFormInfo;
import com.travelsky.espeed.TESAirTicketRefundRS;
import com.travelsky.espeed.Tax;
import com.tuniu.abt.service.travelsky.dto.RefundPriceResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by chengyao on 2016/4/16.
 */
public class IbePlusRefundConverter {

    public static RefundPriceResponse toPriceResponse(TESAirTicketRefundRS rs) {
        RefundFormInfo refundFormInfo = rs.getRefundFormDetails().getRefundFormDetail().get(0).getRefundForm().getRefundFormInfo();

        RefundPriceResponse refundPriceResponse = new RefundPriceResponse();
        refundPriceResponse.setGrossRefund(refundFormInfo.getGrossRefund());
        refundPriceResponse.setNetRefund(refundFormInfo.getNetRefund());
        refundPriceResponse.setDeduction(refundFormInfo.getDeduction());

        BigDecimal taxAll = new BigDecimal(0);
        if (refundFormInfo.getTaxes() != null) {
            List<Tax> taxes = refundFormInfo.getTaxes().getTax();
            for (Tax tax : taxes) {
                taxAll = taxAll.add(tax.getAmount());
            }
            refundPriceResponse.setTaxAll(taxAll);
        }
        if (refundFormInfo.getCommissionInfo() != null
                && refundFormInfo.getCommissionInfo().getBase() != null) {
            refundPriceResponse.setBaseCommissionAmount(refundFormInfo.getCommissionInfo().getBase().getAmount());
        }
        return refundPriceResponse;
    }

}
