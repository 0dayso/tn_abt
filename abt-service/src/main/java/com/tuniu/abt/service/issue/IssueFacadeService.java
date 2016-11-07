package com.tuniu.abt.service.issue;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.service.issue.airline.AirlineIssueTask;
import com.tuniu.abt.service.issue.aop.AopIssueTask;
import com.tuniu.abt.service.issue.ctrip.CtripIssueTask;
import com.tuniu.abt.service.issue.travelsky.TravelSkyIssueTask;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by chengyao on 2016/1/29.
 */
@Service
@Validated
public class IssueFacadeService {

    @Resource
    private CtripIssueTask ctripIssueTask;

    @Resource
    private AirlineIssueTask airlineIssueTask;

    @Resource
    private TravelSkyIssueTask travelSkyIssueTask;

    @Resource
    private AopIssueTask aopIssueTask;

    public void issueTicket(@Valid IssueRequest request) {

        IssueDataSupport.setRequest(request);
        //使用不同的task是因为 不同渠道success逻辑不一致.如果由processor delegate 则返回时还需要判断
        switch (request.getVendorId()) {

            case BizzConstants.V_CTRIP:
                ctripIssueTask.execute(request.getCallback());
                break;
            case BizzConstants.V_AIRLINE:
                airlineIssueTask.execute(request.getCallback());
                break;
            case BizzConstants.V_TS:
                travelSkyIssueTask.execute(request.getCallback());
                break;
            case BizzConstants.V_AOP:
                aopIssueTask.execute(request.getCallback());
                break;
            default: return;
        }

    }

}


