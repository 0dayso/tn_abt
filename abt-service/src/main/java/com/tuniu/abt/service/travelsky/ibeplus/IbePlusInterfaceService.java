package com.tuniu.abt.service.travelsky.ibeplus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.travelsky.espeed.Error;
import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.JaxbXmlMapper;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.List;

/**
 * ibe plus 直接接口基础服务
 *
 * Created by chengyao on 2016/2/16.
 */
@Service
public class IbePlusInterfaceService {

    @Resource
    private IbePlusInterfaceAdapterService ibePlusInterfaceAdapterService;
    @Resource
    private SystemConfig systemConfig;

    public OTAAirAvailRS airAvailD(OTAAirAvailRQ request) {
        return query(IbePlusIntf.AIR_AVAIL_D, request, OTAAirAvailRS.class);
    }

    public OTAAirResRetRS airResRet(OTAAirResRetRQ request) {
        return query(IbePlusIntf.AIR_RES_RET, request, OTAAirResRetRS.class);
    }

    public TESAirTicketRefundRS ticketRefundPrice(TESAirTicketRefundRQ request) {
        return query(IbePlusIntf.AIR_TICKET_REFUND_PRICE, request, TESAirTicketRefundRS.class);
    }

    public TESAirTicketRefundRS ticketRefund(TESAirTicketRefundRQ request) {
        return query(IbePlusIntf.AIR_TICKET_REFUND, request, TESAirTicketRefundRS.class);
    }

    public TESAirTicketRefundRS ticketRefundConfirm(TESAirTicketRefundRQ request) {
        return query(IbePlusIntf.AIR_TICKET_REFUND_CONFIRM, request, TESAirTicketRefundRS.class);
    }


    /**
     * 基础接口
     * @param request 请求
     * @return 返回
     */
    public OTAAirBookRS airBookModify(OTAAirBookModifyRQ request) {
        return query(IbePlusIntf.AIR_BOOK_MODIFY, request, OTAAirBookRS.class);
    }

    /**
     * 基础接口
     * @param request 请求
     * @return 返回
     */
    public OTAAirBookRS airBook(OTAAirBookRQ request) {
        if(request.getPOS().getSource().get(0).getPseudoCityCode().equals(systemConfig.getIbeplusOfficeNo())) {
            return query(IbePlusIntf.AIR_BOOK, request, OTAAirBookRS.class);
        } else {
            // 供应商office号占位
            return query(IbePlusIntf.AIR_BOOK_SPECIAL, request, OTAAirBookRS.class);
        }
    }

    /**
     * 基础接口
     * @param request 请求
     * @return 返回
     */
    public OTAAirPriceRS airPriceD(OTAAirPriceRQ request) {
        return query(IbePlusIntf.AIR_PRICE_D, request, OTAAirPriceRS.class);
    }

    /**
     * 基础接口
     * @param request 请求
     * @return 返回
     */
    public OTAAirPriceBySegRS airPriceBySegD(OTAAirPriceBySegRQ request) {
        return query(IbePlusIntf.AIR_PRICE_BY_SEG_D, request, OTAAirPriceBySegRS.class);
    }

    /**
     * 基础接口
     * @param request 请求
     * @return 返回
     */
    public OTAAirFareDisplayRS airFareDisplay(OTAAirFareDisplayRQ request) {
        return query(IbePlusIntf.AIR_FARE_DISPLAY_D, request, OTAAirFareDisplayRS.class);
    }

    // 查询归口，处理hystrix异常，抛出相关业务错误
    private <T> T query(IbePlusIntf intf, Object request, Class<T> clazz) {
        T responseObject = null;
        try {
            String requestStr = JaxbXmlMapper.toXml(request, CharEncoding.UTF_8);
            String resXml = ibePlusInterfaceAdapterService.call(intf, requestStr);
            responseObject = JaxbXmlMapper.fromXml(resXml, clazz);
        } catch (HttpClientErrorException ex) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_HTTP_CODE_EX, new Object[] {ex.getStatusCode(), ex.getStatusText()}, ex);
        } catch (Exception ex) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_EX, new Object[] {}, ex);
        }
        if (responseObject == null) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_NONE);
        }
        // normal
        List<Error> errors = (List<Error>) JSONPath.eval(responseObject, "$.errors.error");
        if (CollectionUtils.isNotEmpty(errors) && errors.get(0) != null) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_ERROR, JSON.toJSONString(errors));
        } else {
            // airBookModify
            errors = (List<Error>) JSONPath.eval(responseObject, "$.errors");
            if (CollectionUtils.isNotEmpty(errors)) {
                throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_ERROR, JSON.toJSONString(errors));
            }
        }
        return responseObject;
    }
}
