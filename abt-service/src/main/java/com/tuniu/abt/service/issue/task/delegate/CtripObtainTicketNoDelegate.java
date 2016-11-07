package com.tuniu.abt.service.issue.task.delegate;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.converter.CtripOrderDeailToIssueResponseConverter;
import com.tuniu.abt.dao.AbtJobItemExecutionContextDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.intf.entity.AbtJobItemContext;
import com.tuniu.abt.intf.entity.AbtJobItemExecutionContext;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.commons.FeedbackReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 16/4/8.
 */
@Service
public class CtripObtainTicketNoDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripObtainTicketNoDelegate.class);

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private CtripOrderDeailToIssueResponseConverter ctripOrderDeailToIssueResponseConverter;

    @Resource
    private AbtJobItemExecutionContextDao abtJobItemExecutionContextDao;

    @Resource
    private FeedbackReporter feedbackReporter;

    @ActionTrace(action = TracerActionEnum.OBTAIN_TICKET, recordBody = false, input = "#ticketRequest")
    public IssueResponse obtainTicketNo(AbtTicketRequest ticketRequest, AbtJobItemContext abtJobItemContext) {

        IssueResponse issueResponse = ctripOrderDeailToIssueResponseConverter.convert(ticketRequest);

        if (issueResponse.getErrorCode() != TicketEx.CTRIP_ISSUE_ING) {
            abtTicketRequestDao.updateStatusAndCallbackStatusById(issueResponse.isSuccess() ? AbtTicketRequest.ISSUE_SUCCESS : AbtTicketRequest.ISSUE_FAIL, BizzConstants.ISSUE_CALLBACK_YES, ticketRequest.getId());
            LOGGER.info("[携程出票已反馈]sessionId:{},途牛订单号:{},flightItemId:{}", ticketRequest.getSessionId(), ticketRequest.getOrderId(), ticketRequest.getFlightItemId());//反馈之后更新任务表已没多大意义,省去更新,相当于实际已执行次数是表里的次数+1
            feedbackReporter.feedback(ticketRequest.getCallback(), issueResponse.getData());
        } else {

            AbtJobItemExecutionContext abtJobItemExecutionContext = abtJobItemExecutionContextDao.findByEntryIdAndJobName(ticketRequest.getId(), abtJobItemContext.getJobName());
            if (abtJobItemExecutionContext == null) {
                abtJobItemExecutionContextDao.save(new AbtJobItemExecutionContext(ticketRequest.getId(), abtJobItemContext.getId(), 1));
            } else {
                if (abtJobItemExecutionContext.getRetryCount() + 1 >= abtJobItemContext.getMaxRetryCount()) {
                    LOGGER.info("[携程出票反馈已超限]sessionId:{},途牛订单号:{},flightItemId:{}", ticketRequest.getSessionId(), ticketRequest.getOrderId(), ticketRequest.getFlightItemId());

                    feedbackReporter.exception(ticketRequest.getCallback(), issueResponse.getData(), new BizzException(issueResponse.getErrorCode(), "获取票号次数超过最大可尝试次数"));

                    abtTicketRequestDao.updateStatusAndCallbackStatusById(AbtTicketRequest.ISSUE_FAIL, BizzConstants.ISSUE_CALLBACK_YES, ticketRequest.getId());
                    //更新
                } else {
                    abtJobItemExecutionContextDao.updateRetryCountById(abtJobItemExecutionContext.getId(), abtJobItemExecutionContext.getRetryCount() + 1);//未超过最大次数则将执行次数+1
                }
            }
            abtTicketRequestDao.updateStatusById(AbtTicketRequest.PAY_SUCCESS, ticketRequest.getId());//将状态重新置为已支付,等待下次任务处理
        }
        return issueResponse;
    }

}
