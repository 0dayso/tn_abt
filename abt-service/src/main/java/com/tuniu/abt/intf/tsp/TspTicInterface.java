package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import org.springframework.stereotype.Component;

import com.tuniu.abt.intf.tsp.dto.tic.TicFeedbackRequest;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;

/**
 * 票务接口
 * Created by chengyao on 2016/3/18.
 */
@Component
public interface TspTicInterface extends TspInterface {

    /**
     * 占位反馈请求
     * @param request 反馈报文
     * @return 反馈结果
     */
    @TspMethod(serviceName = "TIC.INTL.OccupyController.occupyFeedback",  wrapResp = false)
    String bookFeedback(TicFeedbackRequest request);

    @TspMethod(serviceName = "TIC.INTL.RefundController.refundFeedback")
    void refundFeedback(TicFeedbackRequest request);

    @TspMethod(serviceName = "TIC.INTL.ChangeController.changeFeedback")
    void changeFeedback(TicFeedbackRequest request);

    @TspMethod(serviceName = "TIC.INTL.ReleaseController.feedback")
    void cancelFeedback(TicFeedbackRequest request);

    @TspMethod(serviceName = "TIC.INTL.ConfirmController.secondPayFeedback")
    void secondPayFeedback(TicFeedbackRequest request);

    @TspMethod(serviceName = "TIC.INTL.IssueController.issueFeedbackWithBasement")
    void ticketNoFeedback(IssueResponse request);


}
