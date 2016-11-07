package com.tuniu.abt.service.issue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.utils.RestClientComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by huangsizhou on 2016/03/14.
 */
@Component
public class IssueResultReporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueResultReporter.class);

    @Resource
    private RestClientComponent restClientComponent;

    public void processCallback(String url, IssueResponse issueResponse) {

        if(LOGGER.isInfoEnabled()){
            LOGGER.info("出票反馈{}", JSON.toJSONString(issueResponse));
        }

        restClientComponent.query(url, HttpMethod.POST, JSON.toJSONString(issueResponse, SerializerFeature.DisableCircularReferenceDetect), 3000);
    }
}
