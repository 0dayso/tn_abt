package com.tuniu.abt.api.custom;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.custom.other.ModifyPnrRequest;
import com.tuniu.abt.intf.dto.custom.other.OrderIdRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.query.OtherQueryService;
import com.tuniu.abt.service.ticket.ModifyPnrService;
import com.tuniu.adapter.flightTicket.vo.etdz.IssueVo;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/8.
 */
@Controller
@RequestMapping("/custom")
public class OtherCustomApi {

    @Resource
    private OtherQueryService otherQueryService;
    @Resource
    private ModifyPnrService modifyPnrService;

    /**
     * 根据订单号查询已出票或已支付成功的关联pnr及航班信息<br>
     *
     * @author lichangfeng<br>
     * @taskId <br>
     */
    @RequestMapping(value = "/queryPnrsAndFlights", method = RequestMethod.GET)
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.CUSTOM_QUERY_ORDER_PNR_FLIGHT)
    public Object queryPnrsAndFlights(@Json OrderIdRequest request) {
        if (request.getOrderId() <= 0) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "请求订单号不正确");
        }
        return otherQueryService.queryPnrsAndFlights(request.getOrderId());
    }

    /**
     * 查询票号接口
     */
    @RequestMapping(value = "/getTicketInfos", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.OtherCustomApi.getTicketInfos", description = "查询票号接口")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.GET_TICKET_INFOS)
    public Object getCtripTicketInfos(@Json IssueVo request) {
        return otherQueryService.getDomesticTicketInfos(request);
    }

    /**
     * 国内修改PNR：向PNR中添加OSI项
     *
     */
    @RequestMapping(value = "/modifyPnrOsi", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.OtherCustomApi.modifyPnrOsi", description = "修改PNR，添加OSI项")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.MODIFY_PNR_OSI)
    public Object modifyPnrOsiByOrderIdAndPhone(@Json ModifyPnrRequest request) {
        return modifyPnrService.modifyPnrOsiByOrderIdAndPhone(request);
    }

}
