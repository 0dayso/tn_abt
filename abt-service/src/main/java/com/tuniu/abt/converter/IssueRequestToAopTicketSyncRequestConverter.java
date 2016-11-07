package com.tuniu.abt.converter;

import com.tuniu.abt.dao.*;
import com.tuniu.abt.intf.dto.aop.AopTicketSyncItem;
import com.tuniu.abt.intf.dto.aop.AopTicketSyncRequest;
import com.tuniu.abt.intf.dto.aop.AopTicketSyncSegment;
import com.tuniu.abt.intf.dto.issue.request.IssueDetail;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.utils.DateTimeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsizhou on 16/4/11.
 */
@Service
public class IssueRequestToAopTicketSyncRequestConverter implements Converter<IssueRequest, AopTicketSyncRequest> {

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrMainDao AbtPnrMainDao;

    @Resource
    private AbtAopPolicyDao abtAopPolicyDao;

    @Override
    public AopTicketSyncRequest convert(IssueRequest source) {
        String datePattern = "yyyy-MM-dd";
        String timePattern = "HH:mm:ss";
        IssueDetail issueDetail = source.getIssueDetail();
        List<OrderInfo> orderInfos = issueDetail.getOrderIds();
        List<AbtPnrMain> abtPnrMains = AbtPnrMainDao.findByFlightItemIdAndOrderId(issueDetail.getFlightItemId(), Long.valueOf(source.getOrderId()));

        AopTicketSyncRequest aopTicketSyncRequest = new AopTicketSyncRequest();

        aopTicketSyncRequest.setPolicyId(Integer.valueOf(abtPnrMains.get(0).getPolicyId()));
        aopTicketSyncRequest.setSessionId(source.getSessionId());
        AbtAopPolicy policy = abtAopPolicyDao.find(Long.valueOf(abtPnrMains.get(0).getPolicyId()));
        aopTicketSyncRequest.setVendorId(String.valueOf(policy.getSubVendorId()));
        aopTicketSyncRequest.setOrderId(Long.valueOf(source.getOrderId()));
        aopTicketSyncRequest.setClearPositionTime(DateTimeUtils.formatDatetime(abtPnrMains.get(0).getActTimeLimit()));

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<AbtPnrFlight> pnrFlightList = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(orderInfos.get(0).getPnr(), issueDetail.getFlightItemId(), Long.valueOf(source.getOrderId()));
        AbtPnrFlight abtPnrFlight = pnrFlightList.get(0);

        AopTicketSyncSegment aopTicketSegment = new AopTicketSyncSegment();//航段信息
        aopTicketSyncRequest.setSegment(aopTicketSegment);//设置航段信息
        aopTicketSegment.setAirplaneType(abtPnrFlight.getPlaneType());//机型
        aopTicketSegment.setArrivalAirportTerminal(abtPnrFlight.getDstAirportTerminal());//到达航站楼名称
        aopTicketSegment.setArrivalCityIataCode(abtPnrFlight.getDstCityCode());//到达城市三字码
        aopTicketSegment.setArrivalCityName(abtPnrFlight.getDstCityName());//到达城市名称
        aopTicketSegment.setArrivalDate(DateFormatUtils.format(abtPnrFlight.getDstTime(), datePattern));//到达日期
        aopTicketSegment.setArrivalTime(DateFormatUtils.format(abtPnrFlight.getDstTime(), timePattern));//到达时间
        aopTicketSegment.setArriveAirportCode(abtPnrFlight.getDstAirportCode());//到达机场三字码
        aopTicketSegment.setArriveAirportName(abtPnrFlight.getDstAirportName());//到达机场名称

        aopTicketSegment.setDepartAirportCode(abtPnrFlight.getOrgAirportCode());//出发机场三字码
        aopTicketSegment.setDepartAirportName(abtPnrFlight.getOrgAirportName());//出发机场名称
        aopTicketSegment.setDepartureAirportTerminal(abtPnrFlight.getOrgAirportTerminal());
        aopTicketSegment.setDepartAirportCode(abtPnrFlight.getOrgAirportCode());
        aopTicketSegment.setDepartureCityIataCode(abtPnrFlight.getOrgCityCode());
        aopTicketSegment.setDepartureCityName(abtPnrFlight.getOrgCityName());
        aopTicketSegment.setDepartureDate(DateFormatUtils.format(abtPnrFlight.getOrgTime(), datePattern));
        aopTicketSegment.setDepartureTime(DateFormatUtils.format(abtPnrFlight.getOrgTime(), timePattern));
        aopTicketSegment.setFlightNo(abtPnrFlight.getFlightNo());//航班号
        aopTicketSegment.setSeatCode(abtPnrFlight.getSeatCode());

        List<AopTicketSyncItem> aopTicketSyncItems = new ArrayList<>();
        aopTicketSyncRequest.setItems(aopTicketSyncItems);//设置人员价格信息

        for(OrderInfo orderInfo: orderInfos){
            List<AbtPnrPassenger> abtPnrPassengers = abtPnrPassengerDao.findByPnrAndFlightItemIdAndOrderId(orderInfo.getPnr(), issueDetail.getFlightItemId(), Long.valueOf(source.getOrderId()));
            for (AbtPnrPassenger abtPnrPassenger : abtPnrPassengers) {

                AbtPnrPrice abtPnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(orderInfo.getPnr(), abtPnrPassenger.getPassengerType(), issueDetail.getFlightItemId(), Long.valueOf(source.getOrderId()));

                AopTicketSyncItem aopTicketSyncItem = new AopTicketSyncItem();

                aopTicketSyncItem.setPersonId(abtPnrPassenger.getPersonId());
                aopTicketSyncItem.setPersonType(abtPnrPassenger.getPassengerType());
                aopTicketSyncItem.setPnrCode(orderInfo.getPnr());
                aopTicketSyncItem.setTaxFee(BigDecimal.valueOf(abtPnrPrice.getFuelSurcharge()).add(BigDecimal.valueOf(abtPnrPrice.getAirportTax())));//这里的税费是? //TODO HSZ
                aopTicketSyncItem.setTicketPrice(abtPnrPrice.getOrgPrice());
                aopTicketSyncItem.setCostPrice(abtPnrPrice.getFloorPrice().add(aopTicketSyncItem.getTaxFee()));
                aopTicketSyncItem.setLeg(aopTicketSegment.getDepartureCityIataCode() + "/" + aopTicketSegment.getArrivalCityIataCode());
                aopTicketSyncItem.setAirCompanyCode(abtPnrMains.get(0).getAirCompanyCode());

                totalAmount = totalAmount.add(abtPnrPrice.getFloorPrice()).add(aopTicketSyncItem.getTaxFee());//累加税费

                aopTicketSyncItems.add(aopTicketSyncItem);
            }

        }

        aopTicketSyncRequest.setTotalAmount(totalAmount);//计算总价格

        return aopTicketSyncRequest;

    }
}
