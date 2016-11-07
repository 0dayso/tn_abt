package com.tuniu.abt.service.airline;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.utils.RestClientComponent;
import com.tuniu.abt.utils.SystemConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 航司直连底层适配
 * Created by chengyao on 2016/3/22.
 */
@Service
public class AirlineInterfaceAdapterService implements InitializingBean {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private RestClientComponent restClientComponent;

    private Map<AirlineIntf, String> urls = new HashMap<AirlineIntf, String>();

    @CommandTrace(name = TracerCmdEnum.AIRLINE_INTF_CALL, type = "#intf", input = "#request")
    public String call(AirlineIntf intf, String request) throws Exception {
        // 5秒超时
        return restClientComponent.queryForAirline(urls.get(intf), HttpMethod.POST, request, 5000);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        urls.put(AirlineIntf.CHECK_CABIN_PRICE, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/checkCabinPrice");
        urls.put(AirlineIntf.ADD_ORDER, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/addOrder");
        urls.put(AirlineIntf.CANCEL_ORDER, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/cancelOrder");
        urls.put(AirlineIntf.PAY, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/pay");
        urls.put(AirlineIntf.TICKET_OUT, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/ticketOut");
        urls.put(AirlineIntf.REFUND_APPLY, systemConfig.getRestAirlineConnectorUrl() + "/airline-resource/supplier/flightIntl/refundApply");
    }
}
