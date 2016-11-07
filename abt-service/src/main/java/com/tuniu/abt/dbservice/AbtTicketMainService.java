package com.tuniu.abt.dbservice;

import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.intf.builder.AbtTicketMainBuilder;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.mapper.AbtTicketMainMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lanlugang on 2016/5/20.
 */
@Service
public class AbtTicketMainService {


    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    @Resource
    private AbtTicketMainMapper abtTicketMainMapper;

    @Transactional
    public void saveTicketInfo(Long requestId, PnrInfo pnrInfo) {
        PriceDetail priceDetail = pnrInfo.getPriceDetail();
        for (TicketInfo ticketInfo : pnrInfo.getTickets()) {
            for (PassengerInfo passengerInfo : ticketInfo.getPassengers()) {
                PriceInfo price = null;
                int psgType = 0;
                if (BizzConstants.PERSON_TYPE_ADULT == passengerInfo.getPassengerType()) {
                    price = priceDetail.getAdultPrice();
                    psgType = AbtTicketMain.PASSENGER_TYPE_ADULT;
                } else if (BizzConstants.PERSON_TYPE_CHILD == passengerInfo.getPassengerType()) {
                    price = priceDetail.getChildPrice();
                    psgType = AbtTicketMain.PASSENGER_TYPE_CHILD;
                } else if (BizzConstants.PERSON_TYPE_BABY == passengerInfo.getPassengerType()) {
                    price = priceDetail.getBabyPrice();
                    psgType = AbtTicketMain.PASSENGER_TYPE_BABY;
                }
                for (FlightInfo flightInfo : ticketInfo.getFlights()) {
                    AbtTicketMain abtTicketMain = new AbtTicketMain();
                    abtTicketMain.setRequestId(requestId);
                    abtTicketMain.setPnr(pnrInfo.getOrderId());
                    abtTicketMain.setNewPnr(pnrInfo.getNewOrderId());
                    abtTicketMain.setStatus(AbtTicketMain.STATUS_INIT);
                    abtTicketMain.setTicketNo(ticketInfo.getTicketNo());
                    abtTicketMain.setPassengerName(passengerInfo.getPassengerName());
                    abtTicketMain.setPassengerType(psgType);
                    abtTicketMain.setRph(flightInfo.getRph());
                    abtTicketMain.setFlightNo(flightInfo.getFlightNo());
                    abtTicketMain.setSeatCode(flightInfo.getSeatCode());
                    abtTicketMain.setOrgAirportCode(flightInfo.getOrgAirportCode());
                    abtTicketMain.setDstAirportCode(flightInfo.getDstAirportCode());
                    abtTicketMain.setOrgTime(flightInfo.getDepartureTime());
                    // 如果库中已有记录，则先更新记录状态为已删除
                    List<AbtTicketMain> records = abtTicketMainMapper.select(abtTicketMain);
                    if (CollectionUtils.isNotEmpty(records)) {
                        for (AbtTicketMain record : records) {
                            abtTicketMainDao.updateStatus(record.getId(), AbtTicketMain.STATUS_DELETED);
                        }
                    }
                    if (flightInfo.getRph() == 1) {
                        abtTicketMain.setOrgPrice(price.getOrgPrice().floatValue());
                        abtTicketMain.setFloorPrice(price.getFloorPrice().floatValue());
                    }
                    abtTicketMain.setSolutionId(pnrInfo.getSolutionId());
                    abtTicketMain.setSolutionName(pnrInfo.getSolutionName());
                    // 票号入库
                    abtTicketMainMapper.insertSelective(abtTicketMain);
                }
            }
        }

    }


}
