package com.tuniu.abt.converter;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPriceDao;
import com.tuniu.abt.dao.AbtTicketMainDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.ctrip.module.CtripCommonModule;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.*;
import com.tuniu.mauritius.price.constants.CurrencyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/26.
 */
@Service
public class CtripOrderDeailToIssueResponseConverter implements Converter<AbtTicketRequest, IssueResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripOrderDeailToIssueResponseConverter.class);

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private AbtTicketMainDao abtTicketMainDao;

    @Resource
    private CtripCommonModule ctripCommonModule;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private CtripOrderStatusTranslator ctripOrderStatusTranslator;

    @Override
    public IssueResponse convert(AbtTicketRequest ticketRequest) {

        IssueResponse issueResponse = new IssueResponse();

        //根据每个请组查询对应的PNR集合.并按照主订单号分组
        List<AbtPnrMain> pnrMains = abtPnrMainDao.findByFlightItemIdAndOrderId(ticketRequest.getFlightItemId(), ticketRequest.getOrderId());

        //将子订单号 按主订单号进行分组
        MultiValueMap<String, AbtPnrMain> groupedMainPnrs = new LinkedMultiValueMap<String, AbtPnrMain>();
        for (AbtPnrMain pnrMain : pnrMains) {
            groupedMainPnrs.add(pnrMain.getOutMainOrderId(), pnrMain);
        }

        for (List<AbtPnrMain> abtPnrMainList : groupedMainPnrs.values()) {

            if (abtPnrMainList.get(0).getStatus() != AbtPnrMain.STATUS_PAY_OK) {
                convertPayFailResponse(ticketRequest, issueResponse, abtPnrMainList);//每个主订单下一组子订单的支付状态应该是一致!
            } else {
                convertPaySuccessResponse(ticketRequest, issueResponse, abtPnrMainList);
            }

        }

        issueResponse.setSessionId(ticketRequest.getSessionId());
        issueResponse.setSystemId(ticketRequest.getSystemId());
        issueResponse.setOrderId(String.valueOf(ticketRequest.getOrderId()));
        issueResponse.setIntl(0);
        issueResponse.getData().setFlightItemId(ticketRequest.getFlightItemId());

        return issueResponse;
    }

    private IssueResponse convertPayFailResponse(AbtTicketRequest ticketRequest, IssueResponse issueResponse, List<AbtPnrMain> pnrMains) {
        for (AbtPnrMain abtPnrMain : pnrMains) {
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setIssueFlag(false);
            pnrInfo.setVendorId(BizzConstants.V_CTRIP);
            pnrInfo.setSolutionId(String.valueOf(BizzConstants.V_CTRIP));
            pnrInfo.setOrderId(abtPnrMain.getPnr());
            pnrInfo.setNewOrderId(abtPnrMain.getPnr());
            pnrInfo.setErrorCode(TicketEx.PAY_FAIL);
            pnrInfo.setMsg("支付失败");

            issueResponse.addPnrInfo(pnrInfo);
        }
        return issueResponse;
    }

    private IssueResponse convertPaySuccessResponse(AbtTicketRequest ticketRequest, IssueResponse issueResponse, List<AbtPnrMain> pnrMains) {

        //分组后的子订单
        List<PnrInfo> pnrInfos = issueResponse.getData().getPnrInfoList();

        boolean existUnexpected = false;//标识每个子订单组是否出票成功
        List<String> subOrderIdList = new ArrayList<String>();
        //MultiKeyMap<String, AbtPnrPassenger> passgenerMaps = new MultiKeyMap<String, AbtPnrPassenger>();//pnr 乘客姓名做为key
        //查询每组子订单号(途牛pnr号)对应的乘客信息集合(需要查询是因为返回中需要包含证件号)
        for (AbtPnrMain pnrMain : pnrMains) {
            subOrderIdList.add(pnrMain.getPnr());
        }

        List<OrderInfoEntity> orderInfoEntities = ctripCommonModule.queryCtripOrderDetail(subOrderIdList);  //查询分组后的携程子订单信息,每个子订单信息对应返回结构中的一个pnrInfo

        //子订单信息集合,对应途牛定义的pnr信息集合
        for (OrderInfoEntity orderInfoEntity : orderInfoEntities) {

            OrderBasicInfoEntity orderBasicInfoEntity = orderInfoEntity.getBasicOrderInformation();
            String orderStatus = orderBasicInfoEntity.getOrderStatusDisplay();
            boolean issueFlag = BizzConstants.CTRIP_ISSUED.equalsIgnoreCase(orderStatus);

            if (!issueFlag) {
                existUnexpected = true;//如果子订单中存在不期望的状态 整体出票可视为失败
                issueResponse.setSuccess(false);//所有主订单下子订单按照预期的处理结束则视为出票成功,错误时 msg已在上面处理过
                StringBuilder errorMsg = new StringBuilder(issueResponse.getMsg());
                issueResponse.setMsg(errorMsg.append("子订单:").append(String.valueOf(orderBasicInfoEntity.getOrderID())).append("状态").append(orderBasicInfoEntity.getOrderStatusDisplay()).append(";").toString());
                issueResponse.setErrorCode(ctripOrderStatusTranslator.translate(orderStatus));
            }

            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setIssueFlag(issueFlag);
            pnrInfo.setVendorId(BizzConstants.V_CTRIP);
            pnrInfo.setSolutionId(String.valueOf(BizzConstants.V_CTRIP));
            pnrInfo.setOrderId(String.valueOf(orderBasicInfoEntity.getOrderID()));
            pnrInfo.setNewOrderId(String.valueOf(orderBasicInfoEntity.getOrderID()));
            if (!issueFlag) {
                pnrInfo.setErrorCode(issueResponse.getErrorCode());
                pnrInfo.setMsg("子订单:" + String.valueOf(orderBasicInfoEntity.getOrderID() + "状态:" + orderBasicInfoEntity.getOrderStatusDisplay()));//非正常状况下直接返回携程的出票状态字符串.具体请参见携程查询订单详情文档
                continue;
            }else{
                pnrInfo.setMsg("出票成功");
            }

            List<OrderPassengerEntity> passengerList = orderInfoEntity.getOrderPassengers().getOrderPassenger().getOrderPassengerEntity();//子订单下乘客列表
            List<OrderFlightEntity> flightList = orderInfoEntity.getOrderFlights().getOrderFlight().getOrderFlightEntity();//子订单下航班列表

            List<FlightInfo> flightInfos = new ArrayList<FlightInfo>();//航班组信息,报文中的每个乘客是拥有同级节点下的所有航班的.不需要去找人和航班的对应关系
            int rph = 0;
            for (OrderFlightEntity flightEntity : flightList) {
                FlightInfo flightInfo = new FlightInfo();
                flightInfo.setRph(++rph);
                flightInfo.setFlightNo(flightEntity.getFlight().trim());
                flightInfo.setSeatCode(flightEntity.getSubClass());
                flightInfo.setDepartureTime(DateTimeUtils.parseDate2(flightEntity.getTakeOffTime().toString().replace("T", " ")));
                flightInfo.setOrgAirportCode(flightEntity.getDepartAirportBuildingEntity().getAirportCode());
                flightInfo.setDstAirportCode(flightEntity.getArriveAirportBuildingEntity().getAirportCode());
                flightInfos.add(flightInfo);
            }

            for (OrderPassengerEntity orderPassengerEntity : passengerList) {

                List<PassengerTicketNoEntity> ticketList = orderPassengerEntity.getPassengerTicketNoList().getPassengerTicketNoEntity();
                PassengerTicketNoEntity ticket = new PassengerTicketNoEntity();
                if (!CollectionUtils.isEmpty(ticketList)) {
                    ticket = ticketList.get(0);//默认 一个人只有一个票号(单程),所以取0
                }

                int passengerType = 0;
                if (BizzConstants.CTRIP_ADULT.equalsIgnoreCase(orderPassengerEntity.getAgeType())) {
                    passengerType = BizzConstants.PERSON_TYPE_ADULT;
                } else if (BizzConstants.CTRIP_CHILD.equalsIgnoreCase(orderPassengerEntity.getAgeType())) {
                    passengerType = BizzConstants.PERSON_TYPE_CHILD;
                }
                pnrInfo.addTicketInfo(new TicketInfo(ticket.getPassengerTicketNo(),
                        flightInfos, Collections.singletonList(new PassengerInfo(orderPassengerEntity.getPassengerName(), passengerType)
                )));//添加返回报文中pnr list下的 tickets节点

                for (FlightInfo flightInfo : flightInfos) {
                    //根据人PNR 航班号 人找到对应的记录 跟新状态已出票或出票失败
                    abtTicketMainDao.updateTicketNo(ticketRequest.getId(), String.valueOf(orderBasicInfoEntity.getOrderID()), flightInfo.getFlightNo(), orderPassengerEntity.getPassengerName(), ticket.getPassengerTicketNo(), AbtTicketMain.STATUS_INIT);
                }

            }//组装报文中的pnrInfo中的ticket信息 包含票号 航班信息 人员信息等


            PriceDetail priceDetail = new PriceDetail();
            String ageType = passengerList.get(0).getAgeType();


            BigDecimal totalFloorPrice = BigDecimal.ZERO;//结算价
            BigDecimal totalOrgPrice = BigDecimal.ZERO;//票面价(携程票面价和结算价相等)
            BigDecimal totalAirportTax = BigDecimal.ZERO;//机建费
            BigDecimal totalFuelSurcharge = BigDecimal.ZERO;//燃油费

            for (OrderFlightEntity flightEntity : flightList) {
                totalFloorPrice = totalFloorPrice.add(flightEntity.getPrice());
                totalOrgPrice = totalOrgPrice.add(flightEntity.getPrintPrice());
                totalFuelSurcharge = flightEntity.getOilFee();
                totalAirportTax = flightEntity.getTax();
            }

            AbtPnrPrice pnrPrice = abtPnrPriceDao.findByPnrAndPriceTypeAndFlightItemIdAndOrderId(String.valueOf(orderBasicInfoEntity.getOrderID()), BizzConstants.CTRIP_ADULT.equalsIgnoreCase(ageType) ? 1 : 2, ticketRequest.getFlightItemId(), ticketRequest.getOrderId());

            PriceInfo priceInfo = new PriceInfo(CurrencyType.CNY.getCode(), totalOrgPrice, pnrPrice.getSalePrice(), totalFloorPrice, totalAirportTax, totalFuelSurcharge);

            if (BizzConstants.CTRIP_ADULT.equalsIgnoreCase(ageType)) {
                priceDetail.setAdultNum(passengerList.size());
                priceDetail.setAdultPrice(priceInfo);
            } else if (BizzConstants.CTRIP_CHILD.equalsIgnoreCase(ageType)) {
                priceDetail.setChildNum(passengerList.size());
                priceDetail.setChildPrice(priceInfo);
            }

            pnrInfo.setPriceDetail(priceDetail);//添加返回报文中pnr list下的 priceDetail节点
            pnrInfos.add(pnrInfo);
        }//子订单组处理结束
        //主订单组处理结束

        return issueResponse;
    }
}
