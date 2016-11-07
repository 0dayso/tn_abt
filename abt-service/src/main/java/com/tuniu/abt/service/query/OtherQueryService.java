package com.tuniu.abt.service.query;

import com.tuniu.adapter.flightTicket.vo.etdz.IssueVo;
import com.tuniu.adapter.flightTicket.vo.etdz.TicketInfo;
import com.tuniu.adapter.flightTicket.vo.inquiry.PnrsAndFlightsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengyao on 2016/3/8.
 */
@Service
public class OtherQueryService {

    /**
     * 根据条件获取票号信息
     *
     * @param issueVo
     * @return
     * @author lirongrong3
     */
    public List<TicketInfo> getDomesticTicketInfos(IssueVo issueVo) {
//        if (StringUtils.isEmpty(issueVo.getPnr())) {
//            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "PNR不能为空");
//        }
//        String[] pnrs = issueVo.getPnr().split(",");
//        if (pnrs.length == 0) {
//            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "至少传一个PNR");
//        }
//        List<TicketInfo> ticketInfos = flightPnrMainService.getDomesticTicketInfos(pnrs, issueVo.getOrderId());
//        if (CollectionUtils.isEmpty(ticketInfos)) {
//            throw new BizzException(BizzEx.COMM_EX_WRAP, "查询结果为空");
//        }
//        return ticketInfos;
        return null;
    }


    public PnrsAndFlightsVo queryPnrsAndFlights(Integer orderId) {
//        PnrsAndFlightsVo pnrsAndFlightsVo = new PnrsAndFlightsVo();
//        pnrsAndFlightsVo.setOrderId(orderId);
//        List<FlightPnrFlight> flightPnrFlights = new ArrayList<FlightPnrFlight>();
//        flightPnrFlights = flightPnrFlightService.queryFlightPnrFlightByOrderId(orderId);
//
//        if (null != flightPnrFlights && flightPnrFlights.size() > 0) {
//            pnrsAndFlightsVo.setSystemId(flightPnrFlights.get(0).getSystemId());
//            List<ScheduledFlight> scheduledFlights = new ArrayList<ScheduledFlight>();
//
//            Map<String, List<Pnr>> pnrMap = new HashMap<String, List<Pnr>>();
//            List<FlightPnrPerson> flightPnrPersonList = null;
//            FlightPnrPerson flightPnrPerson = new FlightPnrPerson();
//            for (FlightPnrFlight flightPnrFlight : flightPnrFlights) {
//
//                /**按航班，统计好同一航班下所有的pnr、仓位码信息 begin**/
//                List<Pnr> pnrs = new ArrayList<Pnr>();
//                Pnr pnr = new Pnr();
//                pnr.setPnrNo(flightPnrFlight.getPnrNo());
//                pnr.setSeatCode(flightPnrFlight.getSeatCode());
//                // 添加乘机人姓名 added by @lanlugang 2015-11-18 start
//                flightPnrPerson.setPnrId(flightPnrFlight.getPnrId());
//                flightPnrPersonList = flightPnrPersonService.getFlightPnrPersonByPnrId(flightPnrPerson);
//                if (CollectionUtils.isNotEmpty(flightPnrPersonList)) {
//                    String passangers = "";
//                    for (FlightPnrPerson psg : flightPnrPersonList) {
//                        passangers = ("").equals(passangers) ? psg.getZhName() : (passangers + "," + psg.getZhName());
//                    }
//                    pnr.setPassangers(passangers);
//                }
//                // 添加乘机人姓名 added by @lanlugang 2015-11-18 end
//                if (pnrMap.containsKey(
//                        flightPnrFlight.getOrgCityIataCode() + "-" + flightPnrFlight.getDstCityIataCode())) {
//                    pnrMap.get(flightPnrFlight.getOrgCityIataCode() + "-" + flightPnrFlight.getDstCityIataCode())
//                            .add(pnr);
//                } else {
//                    pnrs.add(pnr);
//                    pnrMap.put(flightPnrFlight.getOrgCityIataCode() + "-" + flightPnrFlight.getDstCityIataCode(), pnrs);
//                }
//                /**按航班，统计好同一航班下所有的pnr、仓位码信息 end**/
//
//                boolean needAdd = true;
//                for (ScheduledFlight sf : scheduledFlights) {
//                    if (sf.getOrgCityIataCode().equalsIgnoreCase(flightPnrFlight.getOrgCityIataCode()) && sf
//                            .getDstCityIataCode().equalsIgnoreCase(flightPnrFlight.getDstCityIataCode())) {
//                        needAdd = false;
//                    }
//                }
//                if (needAdd) {
//                    ScheduledFlight scheduledFlight = new ScheduledFlight();
//                    scheduledFlight.setVendorId(flightPnrFlight.getSolutionId());
//                    scheduledFlight.setOrgCityName(flightPnrFlight.getOrgCityName());
//                    scheduledFlight.setOrgCityIataCode(flightPnrFlight.getOrgCityIataCode());
//                    scheduledFlight.setDstCityName(flightPnrFlight.getDstCityName());
//                    scheduledFlight.setDstCityIataCode(flightPnrFlight.getDstCityIataCode());
//                    scheduledFlight.setDepartAirport(flightPnrFlight.getDepartAirportName());
//                    scheduledFlight.setDepartAirportCode(flightPnrFlight.getDepartAirportCode());
//                    scheduledFlight.setDepartureAirportTerminal(flightPnrFlight.getDepartureAirportTerminal());
//                    scheduledFlight.setArrivalAirport(flightPnrFlight.getArriveAirportName());
//                    scheduledFlight.setArrivalAirportCode(flightPnrFlight.getArriveAirportCode());
//                    scheduledFlight.setArrivalAirportTerminal(flightPnrFlight.getArrivalAirportTerminal());
//                    scheduledFlight.setDepartureDate(flightPnrFlight.getDepartureDate());
//                    scheduledFlight.setDepartureTime(flightPnrFlight.getDptTime());
//                    scheduledFlight.setArriveDate(flightPnrFlight.getArriveDate());
//                    scheduledFlight.setArriveTime(flightPnrFlight.getArvTime());
//                    scheduledFlight.setPlaneType(flightPnrFlight.getPlaneType());
//                    scheduledFlight.setInternational(flightPnrFlight.getInternational());
//                    scheduledFlight.setFlightNo(flightPnrFlight.getFlightNo().trim());
//                    scheduledFlights.add(scheduledFlight);
//                }
//
//            }
//            for (ScheduledFlight sf : scheduledFlights) {
//                if (pnrMap.containsKey(sf.getOrgCityIataCode() + "-" + sf.getDstCityIataCode())) {
//                    sf.setPnrs(pnrMap.get(sf.getOrgCityIataCode() + "-" + sf.getDstCityIataCode()));
//                }
//            }
//            pnrsAndFlightsVo.setScheduledFlights(scheduledFlights);
//        }
//
//        return pnrsAndFlightsVo;
        return null;
    }


}
