package com.tuniu.abt.service.ticket;

import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.dto.issue.response.PnrInfo;
import com.tuniu.abt.intf.dto.ticket.PnrData;
import com.tuniu.abt.intf.dto.ticket.RefreshTicketInfoRequest;
import com.tuniu.abt.intf.dto.ticket.TravelSkyIssueSolution;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.issue.travelsky.TravelSkyIssueService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by lanlugang on 2016/5/23.
 */
@Validated
@Service
public class RefreshTicketInfoByPnrService {

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private TravelSkyIssueService travelSkyIssueService;

    public List<PnrInfo> refreshTicketInfoByPnr(@Valid RefreshTicketInfoRequest request) {

        /** 查确认出票记录 */
        AbtTicketRequest abtTicketRequest = abtTicketRequestDao.findByOrder(request.getOrderId(), request.getFlightItemId());
        if (null == abtTicketRequest) {
            throw new BizzException(TicketEx.REFRESH_TIECKT_NO_ISSUE_REQUEST, "库中无该订单的确认出票请求，请检查入参是否有误");
        }
        /** 刷新票号 */
        IssueResult issueResult = new IssueResult();
        issueResult.setInnerData(abtTicketRequest);
        for (PnrData pnrData : request.getPnrList()) {
            TravelSkyIssueSolution solution = getTravelSkyIssueSolution(pnrData.getSolution());
            issueResult.addResult(pnrData.getPnr(), new InnerIssueResultDto(solution.getSolutionId(), solution.getSolutionName()));
        }
        return travelSkyIssueService.assemblePnrInfos(request.getOrderId(), issueResult, false);
    }

    /**
     * 如果请求中没有获取到出票供应商，则设置缺省的出票供应商为BOP
     * @param str
     * @return
     */
    private static TravelSkyIssueSolution getTravelSkyIssueSolution(String str) {
        try {
            return TravelSkyIssueSolution.valueOf(str);
        } catch (IllegalArgumentException e) {
            return TravelSkyIssueSolution.BOP;
        }
    }

}
