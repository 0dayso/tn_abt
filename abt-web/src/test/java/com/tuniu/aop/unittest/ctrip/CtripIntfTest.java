package com.tuniu.aop.unittest.ctrip;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.service.ctrip.module.*;
import com.tuniu.abt.utils.JaxbXmlMapper;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderBasicInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.CtripPayReq;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderList;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.OrderType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.FltOpenCancelOrderRequestVo;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.OpenCancelOrderRequestItemVo;
import com.tuniu.adapter.flightTicket.vo.connector.AirSearchRequest;
import com.tuniu.adapter.flightTicket.vo.connector.FlightSegVo;
import com.tuniu.adapter.flightTicket.vo.inquiry.ReverseQueryFlightSegVo;
import com.tuniu.adapter.flightTicket.vo.inquiry.ReverseRequestVo;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chengyao on 2016/3/3.
 */
public class CtripIntfTest extends BaseTest {

    @Resource
    private CtripOrderModule ctripOrderModule;

    @Resource
    private CtripQueryModule ctripQueryModule;

    @Resource
    private CtripRefundModule ctripRefundModule;
    @Resource
    private CtripReviseModule ctripReviseModule;

    @Resource
    private CtripCommonModule ctripCommonModule;

    @Resource
    private CtripPayModule ctripPayModule;


    @Test
    public void end() {

        RefundResponse refundResponse = ctripReviseModule.reviseCondition("1821808009");

        System.out.println(JSON.toJSONString(refundResponse));
    }

    @Test
    public void d() {
        FltOpenCancelOrderRequestVo request = new FltOpenCancelOrderRequestVo();
        request.setOpenCancelOrderRequestItems(new ArrayList<OpenCancelOrderRequestItemVo>());
        FltOpenCancelOrderRequestVo requestVo = new FltOpenCancelOrderRequestVo();
        List<OpenCancelOrderRequestItemVo> items = new ArrayList<OpenCancelOrderRequestItemVo>();
        requestVo.setOpenCancelOrderRequestItems(items);
        OpenCancelOrderRequestItemVo item = new OpenCancelOrderRequestItemVo();
        item.setOrderId(String.valueOf(1111111112));
        item.setRelationCancelOrders(Arrays.asList("1", "2"));
        items.add(item);

        ctripOrderModule.openCancelOrder(requestVo);
    }

    @Test
    public void testFlightReverseSearch() {
        ReverseRequestVo reverseRequestVo = new ReverseRequestVo();
        List<ReverseQueryFlightSegVo> reverseQueryFlightSegVoList = new ArrayList<>();
        ReverseQueryFlightSegVo reverseQueryFlightSegVo = null;
        reverseQueryFlightSegVo = new ReverseQueryFlightSegVo();
        reverseQueryFlightSegVo.setOrgCityIataCode("ZUH");
        reverseQueryFlightSegVo.setDstCityIataCode("CKG");
        reverseQueryFlightSegVo.setDepartureDate("2016-05-30");
        reverseQueryFlightSegVo.setFlightNo("CA4572");
        reverseQueryFlightSegVo.setAirline("CA");
        reverseQueryFlightSegVo.setSeatClass("W");//传具体的舱位
        reverseQueryFlightSegVo.setPrice(String.valueOf(610));//传成人成本价
        reverseQueryFlightSegVo.setSaleType("AirLineMarketing");
        reverseQueryFlightSegVo.setPid("ICcEAcDuSAAAAAA");
        reverseQueryFlightSegVoList.add(reverseQueryFlightSegVo);
        reverseRequestVo.setFlightSegList(reverseQueryFlightSegVoList);
        try {
            Object obj = ctripQueryModule.flightReverseSearch(reverseRequestVo);
            System.out.println(JaxbXmlMapper.toXml(obj));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testFlightSearch() {

        AirSearchRequest request = new AirSearchRequest();
        request.getFlightSegList().add(new FlightSegVo("ZUH", "CKG", "2016-05-30"));
        try {
            Object obj = ctripQueryModule.flightSearch(request);
            System.out.println(JaxbXmlMapper.toXml(obj));
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Test
    public void testQueryCtripOrderDetail() {

        List<String> orders = Arrays.asList("2367160894", "2367156995", "2367157571", "2367154152", "2367152493");
        try {
            List<OrderInfoEntity> list = ctripCommonModule.queryCtripOrderDetail(orders);
            for (OrderInfoEntity orderInfoEntity : list) {
                OrderBasicInfoEntity basicInfoEntity = orderInfoEntity.getBasicOrderInformation();
                System.out.println(basicInfoEntity.getRemarks() + ",携程订单号："+basicInfoEntity.getOrderID()+", 携程订单状态：" +basicInfoEntity.getOrderStatusDisplay());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCtripFlightAlipay() {
        CtripPayReq request = new CtripPayReq();
        request.setOrderID(1L);
        request.setMainOrderID("test001");
        request.setExternalNo("test001");
        request.setOrderList(new OrderList());
        OrderType order = new OrderType();
        order.setOrderID("test001");
        order.setOrderAmount(new BigDecimal(0));
        request.getOrderList().getOrder().add(order);
        try {
            Object obj =  ctripPayModule.flightAlipay(request);
            System.out.println(JSON.toJSONString(obj));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
