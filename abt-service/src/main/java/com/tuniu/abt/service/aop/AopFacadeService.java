package com.tuniu.abt.service.aop;

import com.tuniu.abt.intf.dto.aop.AopFeedbackRequest;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 开放平台相关服务
 *
 * Created by chengyao on 2016/3/26.
 */
@Validated
@Service
public class AopFacadeService {

    @Resource
    private AopFeedbackAsyncTask aopFeedbackAsyncTask;

    /**
     * 接收处理开放平台出票、改升结果
     * @param request request
     */
    public void receiveFeedback(AopFeedbackRequest request) {
        DataSharedSupport.put(AopFeedbackAsyncTask.REQUEST_KEY, request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AopFeedbackAsyncTask.REQUEST_KEY, request);
        aopFeedbackAsyncTask.execute(params);
    }

}
