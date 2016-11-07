package com.tuniu.abt.rest;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.dto.issue.response.IssueResponse;
import com.tuniu.abt.utils.RestClientComponent;
import com.tuniu.abt.utils.SystemConfig;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/12.
 */
@Service
public class CommonRestClientService {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private RestClientComponent restClientComponent;

    public void backupPolicy(String data) {
        String url = systemConfig.getAirTicketPolicyUrl() + "/policy/liantuo/operate/backupPolicy";
        restClientComponent.query(url, HttpMethod.POST, data);
    }

    public void saveOrUpdateFlights(String data) {
        String url = systemConfig.getRestResUrl() + "/flight/individual-domestic-flight/indivpriceupdate";
        restClientComponent.query(url, HttpMethod.POST, data);
    }

    /**
     * 出票反馈
     * @param url 从出票请求callback字段获取
     * @param issueResponse
     */
    public void issueFeedback(String url, IssueResponse issueResponse) {
        restClientComponent.query(url, HttpMethod.POST, JSON.toJSONString(issueResponse));
    }

}
