package com.tuniu.aop.unittest.amq;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.dto.aop.*;
import com.tuniu.abt.service.aop.AopTicketSyncService;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by chengyao on 2016/4/22.
 */
public class AmqTest extends BaseTest {

    @Resource
    private AopTicketSyncService aopTicketSyncService;

    @Test
    public void s() {

        AopTicketSyncRequest aopTicketSyncRequest = new AopTicketSyncRequest();
        aopTicketSyncRequest.setTicketType(2);
        aopTicketSyncRequest.setSessionId("99871021");
        aopTicketSyncRequest.setStatus(3);
        aopTicketSyncRequest.setPolicyId(963693610);
        aopTicketSyncRequest.setVendorId("4333");
        aopTicketSyncRequest.setOrderId(450001L);
        aopTicketSyncRequest.setTotalAmount(new BigDecimal(1210.0));

        AopTicketSyncSegment segment = new AopTicketSyncSegment();
        segment.setDepartureDate("2016-01-08");
        segment.setFlightNo("MU563");
        segment.setSeatCode("Z");
        segment.setArrivalCityIataCode("SHA");
        segment.setDepartureCityIataCode("PEK");
        segment.setArrivalCityName("上海");
        segment.setDepartureCityName("北京");
        segment.setDepartAirportCode("PEK");
        segment.setArriveAirportCode("PVG");
        segment.setDepartAirportName("首都国际机场");
        segment.setArriveAirportName("浦东国际机场");
        segment.setDepartureTime("07:35");
        segment.setArrivalTime("09:55");
        segment.setArrivalAirportTerminal("T1");
        segment.setDepartureAirportTerminal("T2");
        aopTicketSyncRequest.setSegment(segment);

        aopTicketSyncRequest.setClearPositionTime("2016-05-10 15:52:00");

        aopTicketSyncRequest.setItems(new ArrayList<AopTicketSyncItem>());
        AopTicketSyncItem item = new AopTicketSyncItem();
        // item.setPerson(new AopTicketSyncPerson());
        item.setPersonId(10673934L);
        item.setAirCompanyCode("");
        item.setLeg("SHA/PEK");
        item.setPersonType(1);
        item.setPnrCode("VSE8F");
        item.setTaxFee(new BigDecimal(10));
        item.setTicketPrice(new BigDecimal(1200));
        aopTicketSyncRequest.getItems().add(item);

        aopTicketSyncService.sendTicket(aopTicketSyncRequest);
    }

    public static AopRefundSyncRequest getRefund() {
        AopRefundSyncRequest aopRefundSyncRequest = new AopRefundSyncRequest();

        aopRefundSyncRequest.setRefundType(2);
        aopRefundSyncRequest.setSessionId("89871021");
        aopRefundSyncRequest.setStatus(3);
        aopRefundSyncRequest.setRemark("退票备注");
        aopRefundSyncRequest.setTicketOrderId("11111111");
        aopRefundSyncRequest.setVendorId("4333");
        aopRefundSyncRequest.setTotalAmount(new BigDecimal(1210.0));


        aopRefundSyncRequest.setItems(new ArrayList<AopRefundSyncItem>());
        AopRefundSyncItem item = new AopRefundSyncItem();
        item.setRefundItemType(1);
        item.setSubAmount(new BigDecimal(1210.0));
        item.setCommissionFee(new BigDecimal(0));
        item.setPersonId(10673934L);
        item.setLeg("SHA/PEK");
        item.setRemark("单项退票备注");
        aopRefundSyncRequest.getItems().add(item);
        return aopRefundSyncRequest;
    }


    @Test
    public void s2() {
        aopTicketSyncService.sendRefund(getRefund());
    }

    public static AopChangeSyncRequest getChange() {
        AopChangeSyncRequest aopChangeSyncRequest = new AopChangeSyncRequest();

        aopChangeSyncRequest.setChangeType(1);
        aopChangeSyncRequest.setSessionId("79871021");
        aopChangeSyncRequest.setStatus(3);
        aopChangeSyncRequest.setRemark("改期备注");
        aopChangeSyncRequest.setTicketOrderId("11111111");
        aopChangeSyncRequest.setVendorId("4333");
        aopChangeSyncRequest.setTotalAmount(new BigDecimal(921.0));
        aopChangeSyncRequest.setFailureTime("2016-05-01 12:00:00");


        aopChangeSyncRequest.setItems(new ArrayList<AopChangeSyncItem>());
        AopChangeSyncItem item = new AopChangeSyncItem();
        item.setChangeFee(new BigDecimal(821.0));
        item.setUpgradeFee(new BigDecimal(0));
        item.setCommissionFee(new BigDecimal(100));
        item.setPersonId(10673934L);
        item.setNewPersonId(10673944L);
        item.setPersonType(1);
        item.setLeg("SHA/PEK");
        item.setRemark("单项退票备注");
        item.setRes(null);

        aopChangeSyncRequest.getItems().add(item);
        return aopChangeSyncRequest;
    }

    @Test
    public void s3() {

        aopTicketSyncService.sendChange(getChange());
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getRefund()));
        System.out.println(JSON.toJSONString(getChange()));
    }
}
