package com.tuniu.abt.converter;

import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.service.ctrip.dto.CtripOrderStatusDisplay;
import org.springframework.stereotype.Service;

/**
 * Created by huangsizhou on 16/4/20.
 */
@Service
public class CtripOrderStatusTranslator {

    public int translate(String status) {
        if (CtripOrderStatusDisplay.PROCESSING.getEnValue().equalsIgnoreCase(status)
                || CtripOrderStatusDisplay.PROCESSING_CONFIRMED.getEnValue().equalsIgnoreCase(status)) {
            return TicketEx.CTRIP_ISSUE_ING;
        } else if (CtripOrderStatusDisplay.CANCEL.getEnValue().equalsIgnoreCase(status)) {
            return TicketEx.CTRIP_CANCEL;
        } else {
            return TicketEx.CTRIP_ISSUED;
        }
    }
}
