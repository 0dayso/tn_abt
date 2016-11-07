package com.tuniu.abt.validator;

import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.validator.annotation.ValidIssue;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/30.
 */
public class IssueRequestValidator implements ConstraintValidator<ValidIssue, IssueRequest> {

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Override
    public void initialize(ValidIssue constraintAnnotation) {

    }

    @Override
    public boolean isValid(IssueRequest issueRequest, ConstraintValidatorContext context) {

        if(StringUtils.isEmpty(issueRequest.getIssueDetail().getFlightItemId()) || StringUtils.isEmpty(issueRequest.getOrderId())){
            return true;
        }

        List<AbtTicketRequest> abtTicketRequestList = abtTicketRequestDao.findByFlightItemIdAndOrderId(issueRequest.getIssueDetail().getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));

        //只要存在非支付失败 或出票失败的 则不允许再次请求, 部分失败的也不允许再次请求
        if (CollectionUtils.isNotEmpty(abtTicketRequestList)) {

            for (AbtTicketRequest abtTicketRequest : abtTicketRequestList) {

                if (abtTicketRequest.getStatus() != AbtTicketRequest.PAY_FAIL && abtTicketRequest.getStatus() != AbtTicketRequest.ISSUE_FAIL) {
                    return false;
                }
            }
        }

        return true;

    }


}
