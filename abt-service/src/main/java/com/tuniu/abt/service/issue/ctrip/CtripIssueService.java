package com.tuniu.abt.service.issue.ctrip;

import com.tuniu.abt.dao.*;
import com.tuniu.abt.intf.builder.AbtTicketMainBuilder;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.service.issue.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangsizhou on 16/3/25.
 */
@Service
public class CtripIssueService implements IssueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripIssueService.class);

    @Resource
    private CtripIssueProcessor ctripIssueProcessor;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    //@CommandTrace(name = TracerCmdEnum.ACTION_ASYNC, type = "出票", input = "#issueRequest", output = "T(com.tuniu.abt.service.issue.IssueDataSupport).getData()")
    @Override
    public IssueResult issueTicket(IssueRequest issueRequest) {

        //LOGGER.info("[携程出票请求]orderId:{},flightItemId:{}", issueRequest.getOrderId(), issueRequest.getIssueDetail().getFlightItemId());

        IssueResult issueResult = ctripIssueProcessor.issueTicket(issueRequest);

        handleIssueResult(issueRequest, issueResult);

        return issueResult;

    }

    /**
     * 保存 请求表和 出票表的临时结果
     *
     * @param issueRequest 出票请求
     * @param issueResult  出票结果
     */
    private void handleIssueResult(IssueRequest issueRequest, IssueResult issueResult) {

        AbtTicketRequest ticketRequest = issueResult.getInnerData();
        Map<String, InnerIssueResultDto> payResultMap = issueResult.getResultMap();

        List<OrderInfo> orderInfos = issueRequest.getIssueDetail().getOrderIds();
        List<AbtTicketMain> ticketMains = new ArrayList<AbtTicketMain>();

        Long flightItemId = issueRequest.getIssueDetail().getFlightItemId();

        for (OrderInfo orderInfo : orderInfos) {
            //从请求中的PNR列表查找出关联的乘客 航班 得到的记录数是   PNR数量*乘客数量*航班数量
            List<AbtPnrPassenger> passengers = abtPnrPassengerDao.findByPnrAndFlightItemIdAndOrderId(orderInfo.getPnr(), flightItemId, Long.valueOf(issueRequest.getOrderId()));//根据pnr查出乘客组的信息
            List<AbtPnrFlight> pnrFlights = abtPnrFlightDao.findByPnrAndFlightItemIdAndOrderId(orderInfo.getPnr(), flightItemId, Long.valueOf(issueRequest.getOrderId())); //根据PNR查询航班组信息

            for (AbtPnrPassenger passenger : passengers) {

                AbtPnrPrice pnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(orderInfo.getPnr(), passenger.getPassengerType(), ticketRequest.getFlightItemId(), ticketRequest.getOrderId());

                for (AbtPnrFlight pnrFlight : pnrFlights) {
                    AbtTicketMainBuilder builder = new AbtTicketMainBuilder();
                    AbtTicketMain abtTicketMain = builder.requestId(ticketRequest.getId()).pnr(orderInfo.getPnr()).newPnr(orderInfo.getPnr())
                            .passengerName(passenger.getFullName()).passengerType(passenger.getPassengerType())
                            .flightNo(pnrFlight.getFlightNo()).rph(pnrFlight.getRph()).seatCode(pnrFlight.getSeatCode())
                            .orgAirportCode(pnrFlight.getOrgAirportCode()).dstAirportCode(pnrFlight.getDstAirportCode())
                            .orgTime(pnrFlight.getOrgTime()).orgPrice(pnrPrice.getOrgPrice())
                            .floorPrice(pnrPrice.getFloorPrice()).build();
                    ticketMains.add(abtTicketMain);

                }
            }

            int payStatus = payResultMap.get(orderInfo.getPnr()) != null && payResultMap.get(orderInfo.getPnr()).isSuccess() ? AbtPnrMain.STATUS_PAY_OK : AbtPnrMain.STATUS_PAY_FAIL;//找不到项认为是失败
            abtPnrMainDao.updatePayStatus(flightItemId, Long.valueOf(issueRequest.getOrderId()), orderInfo.getPnr(), payStatus);//更新abt_pnr_main的支付状态
        }

        abtTicketMainDao.batchInsert(ticketMains);
        abtTicketRequestDao.updateStatusById(issueResult.getIssueStatus(), ticketRequest.getId());

    }
}
