package com.tuniu.aop.unittest.dbtest;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.dao.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.entity.*;
import com.tuniu.abt.intf.tsp.TspTicInterface;
import com.tuniu.abt.intf.tsp.dto.tic.TicFeedbackRequest;
import com.tuniu.abt.mapper.AbtBookingRequestMapper;
import com.tuniu.abt.schedule.CancelFailedRetryTask;
import com.tuniu.abt.schedule.CancelPnrInTestEnvTask;
import com.tuniu.aop.unittest.BaseTest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanlugang on 2016/4/6.
 */
public class bookDBTest  extends BaseTest {
    @Resource
    private AbtBookingRequestDao abtBookingRequestDao;

    @Resource
    private AbtPnrPassengerDao abtPnrPassengerDao;

    @Resource
    private AbtPnrFlightDao abtPnrFlightDao;
    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtBookContactDao abtBookContactDao;

    @Resource
    private TspTicInterface tspTicInterface;

    @Resource
    private CancelPnrInTestEnvTask cancelPnrInTestEnvTask;

    @Resource
    private AbtAopPolicyDao abtAopPolicyDao;
    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private CancelFailedRetryTask cancelFailedRetryTask;

    @Resource
    private AbtBookingRequestMapper abtBookingRequestMapper;


    @Test
    public void testInsertFlights() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<AbtPnrFlight> flightList = new ArrayList<AbtPnrFlight>();
        AbtPnrFlight abtPnrFlight = null;
        int idx = 0;
        try {
            for (int i = 0; i < 2; i ++) {
                abtPnrFlight = new AbtPnrFlight();
                abtPnrFlight.setPnrId(new Long(3));
                abtPnrFlight.setFlightNo("MU1234");
                abtPnrFlight.setRph(1);
                abtPnrFlight.setSeatCode("Y");
                abtPnrFlight.setPlaneType("A230");
                abtPnrFlight.setOrgAirportCode("SHA");
                abtPnrFlight.setOrgAirportName("");
                abtPnrFlight.setOrgCityCode("SHA");
                abtPnrFlight.setOrgCityName("");
                abtPnrFlight.setOrgAirportTerminal("T2");
                abtPnrFlight.setDstAirportCode("PEK");
                abtPnrFlight.setDstAirportName("");
                abtPnrFlight.setDstCityCode("BJS");
                abtPnrFlight.setDstCityName("");
                abtPnrFlight.setDstAirportTerminal("--");
                abtPnrFlight.setOrgTime(sdf.parse("2016-06-15 09:00:00"));
                abtPnrFlight.setDstTime(sdf.parse("2016-06-15 11:00:00"));
                flightList.add(abtPnrFlight);
            }
            idx = abtPnrFlightDao.saveAbtPnrFlightList(flightList);

        } catch (Exception e) {
            System.out.println(e);

        }

        System.out.println(idx);
    }

    @Test
    public void  testInsertContactInfo() {
        AbtBookContact abtBookContact = new AbtBookContact();
        abtBookContact.setRequestId(new Long(55));
//        abtBookContact.setName("test");
//        abtBookContact.setTel("123456");
//        abtBookContact.setEmail("");
        try {
            abtBookContactDao.save(abtBookContact);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("");
    }

    @Test
    public void testTspticCallBack() {

        TicFeedbackRequest request = new TicFeedbackRequest();
        try {
            tspTicInterface.bookFeedback(request);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testQueryRequests() {
        try {
            List<AbtBookingRequest> list = abtBookingRequestDao.queryCurDateRequestsByStatus(1);
            System.out.println(JSON.toJSONString(list));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testQueryBookPnrs() {
        cancelPnrInTestEnvTask.execute();
    }
    @Test
    public void testSaveAbtAopPolicy() {
        AbtAopPolicy abtAopPolicy = new AbtAopPolicy();
        abtAopPolicy.setId(1111L);
        abtAopPolicy.setVendorId(BizzConstants.V_AOP);
        abtAopPolicy.setChangePnrFlag(1);
        abtAopPolicy.setSubVendorId(1234);
        abtAopPolicy.setSubVendorName("开放平台测试");
        abtAopPolicy.setTicketingOfficeNo("PEK099");
        abtAopPolicy.setSupplierOfficeNo("CAN127");
        abtAopPolicy.setTicketingAccountNo("tuniu");
        abtAopPolicy.setTicketingAccountPwd("tuniu520");
        abtAopPolicy.setActualTicketingOfficeNo("NKG166");
        try {
            Long idx = abtAopPolicyDao.save(abtAopPolicy);
            System.out.println(idx);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void testInsertAbtPnrMain() {
        AbtPnrMain abtPnrMain = new AbtPnrMain();
        abtPnrMain.setRequestId(123456L);
        abtPnrMain.setOrderId(123456L);
        abtPnrMain.setPnr("NNN777");
        abtPnrMain.setOutOrderId("NNN777");
        abtPnrMain.setOutMainOrderId("NNN777");
        abtPnrMain.setExternalNo("NNN777");
        abtPnrMain.setTimeLimit(new Date());
        abtPnrMain.setActTimeLimit(new Date());
        abtPnrMain.setOrderStatusDisplay("NA");
        abtPnrMain.setStatus(AbtPnrMain.STATUS_INIT);
        abtPnrMain.setPayStatus(AbtPnrMain.STATUS_INIT);
        abtPnrMain.setVendorId(1);
        abtPnrMain.setAirCompanyCode("WWWWWW");
        try {
            Long idx = abtPnrMainDao.save(abtPnrMain);
            System.out.println(idx);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testQueryAbtPnrMain() {
        Date to = new Date();
        Date from = DateUtils.addDays(to, -10);
        try {
            Object obj = abtPnrMainDao.findByStatusAndSpecificTimeInterval(0, from, to);
            System.out.println(JSON.toJSONString(obj));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCancelFailedRetryTask() {

        try {
            cancelFailedRetryTask.process();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testUpdateAbtBookingRequest() {
        AbtBookingRequest abtBookingRequest = new AbtBookingRequest();
        abtBookingRequest.setId(62L);
        abtBookingRequest.setStatus(AbtBookingRequest.STATUS_SUCCESS);
        abtBookingRequest.setBackTime(new Date());
        abtBookingRequest.setCallBackStatus(AbtBookingRequest.CALL_BACK_STATUS_SUCCESS);
        try {
            int index = abtBookingRequestMapper.updateByPrimaryKeySelective(abtBookingRequest);
            System.out.println(index);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testSaveAbtBookingRequest() {
        AbtBookingRequest abtBookingRequest = new AbtBookingRequest();
        abtBookingRequest.setOrderId(4100163821111L);
        abtBookingRequest.setSessionId("1459842529982");
        abtBookingRequest.setSystemId(59);
        abtBookingRequest.setVendorId(6);
        abtBookingRequest.setFlightItemId(2000040695L);
        abtBookingRequest.setStatus(AbtBookingRequest.STATUS_INIT);
        abtBookingRequest.setBackTime(new Date());
        abtBookingRequest.setCallBackStatus(AbtBookingRequest.CALL_BACK_STATUS_SUCCESS);
        try {
            abtBookingRequestDao.save(abtBookingRequest);
            System.out.println(JSON.toJSONString(abtBookingRequest));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testBuildAbtTicketMain() {
        try {
            List<AbtTicketMain> abtTicketMains = abtPnrMainDao.buildAbtTicketMainByBookData(84L, 1436148333L);
            System.out.println(JSON.toJSONString(abtTicketMains));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
