package com.tuniu.abt.service.airline;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.intf.airline.*;
import com.tuniu.abt.intf.exception.BizzException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;

/**
 * 航司直连基础接口服务
 * Created by chengyao on 2016/3/22.
 */
@Service
public class AirlineInterfaceService {

    @Resource
    private AirlineInterfaceAdapterService airlineInterfaceAdapterService;

    public AirDirectConnectCabinPriceParamResponse checkCabinPrice(AirDirectConnectCabinPriceParamRequest request) {
        return query(AirlineIntf.CHECK_CABIN_PRICE, request, AirDirectConnectCabinPriceParamResponse.class);
    }

    public AirDirectConnectOccupyResponse addOrder(AirDirectConnectOccupyRequest request) {
        return query(AirlineIntf.ADD_ORDER, request, AirDirectConnectOccupyResponse.class);
    }

    public AirDirectConnectCancelOccupyResponse cancelOrder(AirDirectConnectCancelOccupyRequest request) {
        return query(AirlineIntf.CANCEL_ORDER, request, AirDirectConnectCancelOccupyResponse.class);
    }

    public AirDirectConnectPayResponse pay(AirDirectConnectPayRequest request) {
        return query(AirlineIntf.PAY, request, AirDirectConnectPayResponse.class);
    }
    public AirDirectConnectTicketingResponse ticketOut(AirDirectConnectTicketingRequest request) {
        return query(AirlineIntf.TICKET_OUT, request, AirDirectConnectTicketingResponse.class);
    }

    public AirDirectConnectCheckRefundResponse refundApply(AirDirectConnectCheckRefundRequest request) {
        return query(AirlineIntf.REFUND_APPLY, request, AirDirectConnectCheckRefundResponse.class);
    }

    // 查询归口，抛出相关业务错误
    private <T extends AirlineResponse> T query(AirlineIntf intf, Object request, Class<T> clazz) {
        T responseObject = null;
        try {
            String requestStr = JSON.toJSONString(request);
            String responseStr = airlineInterfaceAdapterService.call(intf, requestStr);
            responseObject = JSON.parseObject(responseStr, clazz);
        } catch (HttpClientErrorException ex) {
            throw new BizzException(BizzEx.AIRLINE_INTF_HTTP_CODE_EX, new Object[] {ex.getStatusCode(), ex.getStatusText()}, ex);
        } catch (Exception ex) {
            throw new BizzException(BizzEx.AIRLINE_INTF_EX, new Object[] {}, ex);
        }
        if (responseObject == null) {
            throw new BizzException(BizzEx.AIRLINE_INTF_RESULT_NONE);
        }

        if (!responseObject.isSuccess() || responseObject.getErrorCode() != 0) {
            throw new BizzException(BizzEx.AIRLINE_INTF_RESULT_ERROR, new Object[] { responseObject.getErrorCode(), responseObject.getMessage() });
        }
        // 可以添加通用对象异常处理

        return responseObject;
    }

}
