package com.tuniu.abt.api.ticket;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.ticket.RefreshTicketInfoRequest;
import com.tuniu.abt.service.issue.IssueFacadeService;
import com.tuniu.abt.service.ticket.RefreshTicketInfoByPnrService;
import com.tuniu.operation.platform.base.rest.RestException;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 票相关API
 * Created by chengyao on 2016/3/4.
 */
@Controller
@RequestMapping("/ticket")
@Profile({"prd", "pre", "test"})
public class TicketApi {

    @Resource
    private IssueFacadeService issueFacadeService;

    @Resource
    private RefreshTicketInfoByPnrService refreshTicketInfoByPnrService;

    /**
     * 航信刷新票号接口:通过PNR从库中或RT接口获取票号
     *
     * @param request RefreshTicketInfoRequest
     */
    @RequestMapping(value = "/refreshTicketInfoByPnr", method = RequestMethod.POST)
    @TSPServiceInfo(name = "ATS.ABT.TicketApi.refreshTicketInfoByPnr", description = "航信刷新票号接口")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.TICKET_REFRESH_TICKET)
    public Object refreshTicketInfosByPnr(@Json RefreshTicketInfoRequest request) {
        return refreshTicketInfoByPnrService.refreshTicketInfoByPnr(request);
    }

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    @TSPServiceInfo(name="ATS.ABT.TicketApi.issue", description = "出票")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.TICKET)
    public void issue(@Json IssueRequest issueRequest) throws RestException {
        issueFacadeService.issueTicket(issueRequest);
    }

}
