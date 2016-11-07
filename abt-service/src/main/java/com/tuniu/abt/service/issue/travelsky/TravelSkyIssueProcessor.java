package com.tuniu.abt.service.issue.travelsky;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.issue.AbstractIssueProcessor;
import com.tuniu.abt.service.issue.travelsky.module.TravelSkyIssueSupport;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TravelSkyIssueProcessor extends AbstractIssueProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyIssueProcessor.class);

    @Resource
    private TsConfig tsConfig;

    @Resource
    private TravelSkyInterface travelSkyInterface;

    @Resource
    private TravelSkyIssueSupport travelSkyIssueSupport;

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;

    @Override
    protected IssueResult issue(IssueRequest issueRequest) {

        IssueResult issueResult = new IssueResult();
        issueResult.setIssueStatus(AbtTicketRequest.ISSUE_SUCCESS);
        issueResult.setSessionId(issueRequest.getSessionId());

        StringBuilder msg = new StringBuilder();
        for (OrderInfo orderInfo : issueRequest.getIssueDetail().getOrderIds()) {

            InnerIssueResultDto resultDto = new InnerIssueResultDto();

            try {
                String solutionId = innerIssue(orderInfo.getPnr(), issueRequest.getSystemId());
                resultDto.setSolutionId(solutionId);
                resultDto.setSolutionName(tsConfig.getSolutionName(solutionId));
            } catch (Exception e) {
                issueResult.setSuccess(false);
                resultDto.setSuccess(false);
                resultDto.setErrorCode(TicketEx.ISSUE_ERROR);
                resultDto.setMsg(exceptionMessageUtils.findWrappedMessage(e));

                issueResult.setIssueStatus(AbtTicketRequest.ISSUE_FAIL);
                msg.append(resultDto.getMsg()).append(";");
                LOGGER.error(e.getMessage(), e);
            }

            issueResult.addResult(orderInfo.getPnr(), resultDto);

        }

        if (!issueResult.isSuccess()) {
            issueResult.setMsg(msg.toString());
        }

        return issueResult;
    }

    private String innerIssue(String pnr, int systemId) {
        /** 校验pnr是否可出票 */
        try {
            travelSkyIssueSupport.checkPNRIssueTicketIsOK(pnr);
        } catch (BizzException bizEx) {
            // 如果pnr状态为已出票,则返回默认BOP出票
            if (TicketEx.TS_ALREADY_ISSUED == bizEx.getCode()) {
                return tsConfig.getSolutionId(true);
            }
            throw bizEx;
        }
        /** 从远程配置中心读取bsp/bop开关 */
        int status = tsConfig.usePlus(TsConfig.ISSUE, systemId) ? 1 : 0;
        int maxBopRetryCount = tsConfig.getMaxBopRetryCount();
        int bopTryCount = 0; //BOP支付次数
        boolean isBspTried = false; //BSP支付方式是否已经尝试支付过,防止死循环
        boolean isBOPissued = false;
        /** 调航信接口出票 */
        while (status != -1) {
            switch (status) {
                case 0:
                    isBspTried = true;
                    try {
                        travelSkyInterface.issueTicketByBSP(pnr);
                    } catch (BizzException ex) {
                        if (bopTryCount == 0 && ex.getCode() == BizzEx.IBE_INTF_RESULT_ERROR) {
                            status = 1;
                            continue;
                        }
                        throw ex;
                    }
                    status = -1;//支付结束
                    break;
                case 1:
                    bopTryCount++;
                    try {
                        travelSkyInterface.issueTicketByBOP(pnr);
                        isBOPissued = true;
                    } catch (BizzException ex) {
                        if (!isBspTried && ex.getCode() == BizzEx.IBE_PLUS_INTF_RESULT_ERROR) {
                            status = 0;
                            continue;
                        } else if (ex.getCode() == BizzEx.IBE_PLUS_INTF_EX) {
                            if (bopTryCount < maxBopRetryCount) {
                                continue;
                            } else if (!isBspTried) {
                                status = 0;
                                continue;
                            }
                        }
                        throw ex;
                    }
                    status = -1; //支付结束
                    break;
                default:
                    status = -1;
                    break;
            }
        }
        /** 返回真实出票供应商id */
        return tsConfig.getSolutionId(isBOPissued);
    }


}