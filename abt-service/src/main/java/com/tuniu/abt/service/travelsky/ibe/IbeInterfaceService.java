package com.tuniu.abt.service.travelsky.ibe;

import com.travelsky.ibe.client.AvResult;
import com.travelsky.ibe.client.FDResult;
import com.travelsky.ibe.client.pnr.*;
import com.tuniu.adapter.flightTicket.vo.connector.*;
import com.tuniu.flight.connector.client.ibe.IbeDConnectorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Vector;

/**
 * ibe plus 直接接口基础服务
 *
 * Created by chengyao on 2016/2/16.
 */
@Service
public class IbeInterfaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeInterfaceService.class);

    @Resource
    private IbeDConnectorClient client;

    public SSResult createPnr(BookInfomation request) throws Exception {
        return client.createPNR(request);
    }

    public AvResult getAvail(AirAvailRequest availRequest) throws Exception {
        return client.getAvail(availRequest);
    }

    public FDResult fdPrice(FDRequest fdRequest) throws Exception {
        return client.fdPrice(fdRequest);
    }

    // 可以加注解增加hystrix限定 @HystrixCommand(groupKey = "1", commandKey = "1")
    public String deletePnr(String pnrNo, String officeNo) throws Exception {
        return client.deletePNR(pnrNo, officeNo);
    }

    public PATResult doPata(PataRequest pataRequest) throws Exception {
        return client.doPata(pataRequest);
    }

    public PATResult doPat(PatRequest patRequest) throws Exception {
        return client.doPat(patRequest);
    }

    public PATResult doPataSecond(PataSecondRequest pataSecondRequest)
            throws Exception {
        return client.doPataSecond(pataSecondRequest);
    }

    public RTResult rtPnr(String pnrNo) throws Exception {
        return client.rtPNR(pnrNo);
    }

    public String deletePnrItem(String pnrNo, int[] indexes) throws Exception {
        return client.deletePNRItem(pnrNo, indexes);
    }

    public String issueTicket(String pnrNo, int printerNo) throws Exception {
        return client.issueTicket(pnrNo, printerNo);
    }
    
    public String addOSI4PNR(String pnrNo, String airCode, String osi)
            throws Exception {
        return client.addOSI4PNR(pnrNo, airCode, osi);
    }
    
    public String addRMK4PNR(String pnrNo, String rmktype, String rmkinfo)
            throws Exception {
        return client.addRMK4PNR(pnrNo, rmktype, rmkinfo);
    }

    public TRFDResult automaticRefund(String ticketNo, int printerNo) throws Exception {
        return client.automaticRefund(ticketNo, printerNo);
    }

    public String confirmAutomaticRefund(String ticketNo, int printerNo, TRFDResult trfdResult) throws Exception {
        return client.confirmAutomaticRefund(ticketNo, printerNo, trfdResult);
    }

    public String splitPnr(String pnrNo, Vector<BookPassenger> passengers) throws Exception {
        return client.splitPNR(pnrNo, passengers);
    }

}
