package com.tuniu.abt.service.airline;

import com.tuniu.abt.intf.dto.intf.airline.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/22.
 */
@Service
public class AirlineFacadeService {

    @Resource
    private AirlineInterfaceService airlineInterfaceService;

    /**
     * 取消占位
     *
     * @param ticketingCarrier 航司
     * @param orderId          订单号
     */
    public void cancelOrder(String ticketingCarrier, String orderId) {
        AirDirectConnectCancelOccupyRequest request = new AirDirectConnectCancelOccupyRequest();
        AirDirectConnectOrder airDirectConnectOrder = new AirDirectConnectOrder();
        airDirectConnectOrder.setOrderId(orderId);
        request.setC(ticketingCarrier);
        request.setD(airDirectConnectOrder);
        airlineInterfaceService.cancelOrder(request);
    }

    /**
     * 支付
     * @param c 渠道
     * @param orderId 航司订单号
     * @param totalPrice 总价
     * @param currency 货币类型
     */
    public AirDirectConnectPayResponse pay(String c, String orderId, int totalPrice, String currency) {

        AirDirectConnectPayRequest payRequest = new AirDirectConnectPayRequest();
        payRequest.setC(c);

        AirDirectConnectPayParam d = new AirDirectConnectPayParam();
        d.setCurrency(currency);
        d.setOrderId(orderId);
        d.setTotalPrice(totalPrice);

        return airlineInterfaceService.pay(payRequest);
    }

    /**
     * 出票
     * @param c 渠道
     * @param orderId 航司订单号
     */
    public AirDirectConnectTicketingResponse ticketOut(String c, String orderId){
        AirDirectConnectTicketingRequest ticketingRequest = new AirDirectConnectTicketingRequest();
        AirDirectConnectPayParam payParam = new AirDirectConnectPayParam();
        payParam.setOrderId(orderId);
        ticketingRequest.setD(payParam);

        return airlineInterfaceService.ticketOut(ticketingRequest);
    }

}
