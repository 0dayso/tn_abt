package com.tuniu.abt.converter;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class IssueRequestToTicketRequestConverter implements Converter<IssueRequest, AbtTicketRequest>{

    @Override
    public AbtTicketRequest convert(IssueRequest source) {

        AbtTicketRequest abtTicketRequest = new AbtTicketRequest();
        abtTicketRequest.setCallback(source.getCallback());
        abtTicketRequest.setSessionId(source.getSessionId());
        abtTicketRequest.setSystemId(source.getSystemId());
        abtTicketRequest.setVendorId(source.getVendorId());
        abtTicketRequest.setFlightItemId(source.getIssueDetail().getFlightItemId());
        abtTicketRequest.setAopVendorId("");//TODO hsz 0411
        abtTicketRequest.setOrderId(Long.valueOf(source.getOrderId()));
        abtTicketRequest.setStatus(BizzConstants.ISSUE_WAITING);
        abtTicketRequest.setCallBackStatus(BizzConstants.ISSUE_CALLBACK_NO);

        Date now = new Date();
        abtTicketRequest.setAddTime(now);
        abtTicketRequest.setUpdateTime(now);

        return abtTicketRequest;
    }
}
