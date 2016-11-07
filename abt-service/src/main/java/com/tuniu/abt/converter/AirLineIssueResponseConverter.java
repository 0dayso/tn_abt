package com.tuniu.abt.converter;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.intf.airline.AirDirectConnectTicketingResponse;
import com.tuniu.abt.intf.dto.intf.airline.PassengerTravel;
import com.tuniu.abt.intf.dto.intf.airline.TravelSegment;
import com.tuniu.abt.intf.dto.issue.response.*;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

/**
 * Created by huangsizhou on 16/4/6.
 */
public class AirLineIssueResponseConverter implements Converter<AirDirectConnectTicketingResponse, IssueResponse> {

    @Override
    public IssueResponse convert(AirDirectConnectTicketingResponse source) {

        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setSuccess(source.isSuccess());
        if (!source.isSuccess()) {
            issueResponse.setErrorCode(TicketEx.OBTAIN_TICKET_ERROR);
            issueResponse.setMsg(source.getMessage());
            return issueResponse;
        }

        IssueResultDetail issueResultDetail = issueResponse.getData();

        PnrInfo pnrInfo = new PnrInfo();
        pnrInfo.setIssueFlag(true);
        pnrInfo.setOrderId(source.getOrderId());
        pnrInfo.setNewOrderId(source.getOrderId());
        pnrInfo.setVendorId(BizzConstants.V_AIRLINE);


        for (PassengerTravel passengerTravel : source.getPassengerTravels()) {

            TicketInfo ticketInfo = new TicketInfo();
            ticketInfo.setTicketNo(passengerTravel.getTicketNo());
            ticketInfo.setPassengers(Collections.singletonList(new PassengerInfo(passengerTravel.getPassenger().getName(), passengerTravel.getPassenger().getIdNumber())));

            for (TravelSegment travelSegment : passengerTravel.getTravelSegments()) {
                ticketInfo.addFlight(new FlightInfo(travelSegment.getFlightNO(), travelSegment.getCabin().getCabinClass()));
            }

            pnrInfo.addTicketInfo(ticketInfo);
        }

        issueResultDetail.setPnrInfoList(Collections.singletonList(pnrInfo));

        return issueResponse;
    }
}
