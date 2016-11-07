package com.tuniu.abt.converter;

import com.tuniu.abt.dao.AbtPnrFlightDao;
import com.tuniu.abt.intf.builder.AbtTicketMainBuilder;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtPnrFlight;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsizhou on 16/4/12.
 */
@Service
public class IssueResponseToTicketMainConverter {

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;

    public List<AbtTicketMain> convertFrom(AbtTicketRequest abtTicketRequest, IssueResponse issueResponse) {

        List<AbtTicketMain> abtTicketMains = new ArrayList<AbtTicketMain>();
        IssueResultDetail issueResultDetail = issueResponse.getData();
        List<PnrInfo> pnrInfos = issueResultDetail.getPnrInfoList();
        AbtPnrFlight abtPnrFlight = null;

        for (PnrInfo pnrInfo : pnrInfos) {

            PriceDetail priceDetail = pnrInfo.getPriceDetail();

            List<TicketInfo> ticketInfos = pnrInfo.getTickets();
            for (TicketInfo ticketInfo : ticketInfos) {

                PriceInfo priceInfo;
                PassengerInfo passenger = ticketInfo.getPassengers().get(0);
                if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_ADULT) {
                    priceInfo = priceDetail.getAdultPrice();
                } else if (passenger.getPassengerType() == BizzConstants.PERSON_TYPE_CHILD) {
                    priceInfo = priceDetail.getChildPrice();
                } else {
                    priceInfo = priceDetail.getBabyPrice();
                }

                if (abtPnrFlight == null) {
                    abtPnrFlight = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(pnrInfo.getOrderId(), issueResultDetail.getFlightItemId(), Long.valueOf(issueResponse.getOrderId())).get(0);
                }

                AbtTicketMainBuilder builder = new AbtTicketMainBuilder();
                AbtTicketMain abtTicketMain = builder.requestId(abtTicketRequest.getId()).pnr(pnrInfo.getOrderId()).newPnr(pnrInfo.getNewOrderId())
                        .passengerName(passenger.getPassengerName()).passengerType(passenger.getPassengerType())
                        .flightNo(abtPnrFlight.getFlightNo()).rph(abtPnrFlight.getRph()).seatCode(abtPnrFlight.getSeatCode())
                        .orgAirportCode(abtPnrFlight.getOrgAirportCode()).dstAirportCode(abtPnrFlight.getDstAirportCode())
                        .orgTime(abtPnrFlight.getOrgTime()).orgPrice(priceInfo.getOrgPrice())
                        .floorPrice(priceInfo.getFloorPrice()).status(AbtTicketRequest.ISSUE_SUCCESS).build();
                abtTicketMains.add(abtTicketMain);
            }
        }

        return abtTicketMains;
    }

}
