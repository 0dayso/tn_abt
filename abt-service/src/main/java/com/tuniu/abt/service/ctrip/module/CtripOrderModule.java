package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.ctrip.CtripCardConvert;
import com.tuniu.abt.service.ctrip.CtripHeaderSupport;
import com.tuniu.abt.service.ctrip.CtripInterfaceService;
import com.tuniu.abt.service.ctrip.dto.SaleTypeCtrip;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.cancelOrder.*;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.RequestHeader;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.data.flighttypes.*;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderDetailResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.ParameterCollection;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.QueryTicketingOrderInfoRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.QueryableParameter;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.refundOrder.RefundOrderDetailRequest;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.*;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.FltOpenCancelOrderRequestVo;
import com.tuniu.adapter.flightTicket.vendor.ctrip.vo.OpenCancelOrderRequestItemVo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripOrderModule {

    @Resource
    private CtripHeaderSupport ctripHeaderSupport;
    @Resource
    private CtripInterfaceService ctripInterfaceService;
    @Resource
    private SystemConfig systemConfig;

    public OrderDetailResponse viewOrderListDetails(List<String> ctripOrderIds) {
        OrderDetailRequest request = getCtripOrderDetailInfoByOrderNo(ctripOrderIds);
        return ctripInterfaceService.viewOrderListDetails(request);
    }


    public FltOpenCancelOrderResponse openCancelOrder(FltOpenCancelOrderRequestVo fltOpenCancelOrderRequestVo) {
        FltOpenCancelOrderRequest request = getCtripFlightCancelOrder(fltOpenCancelOrderRequestVo);
        return ctripInterfaceService.openCancelOrder(request);
    }

    public Response saveOrder(Object obj, String flightOrderType) {
        Request request = null;
        if (ActionType.CREATE_ORDER.value().equalsIgnoreCase(flightOrderType)) {
            request = getSaveOrderRequestInfo((BookingData)obj);
        } else if (ActionType.SUBMIT_ORDER.value().equalsIgnoreCase(flightOrderType)) {
            request = getSubmitOrderRequestInfo((SaveOrderResultType)obj);
        }
        return ctripInterfaceService.saveOrder(request);
    }

    /**
     * 国内机票取消携程订单
     *
     * @return
     * @author chujun
     * @date 2015-09-14
     */
    public FltOpenCancelOrderResponse ctripCancelOrder(FltOpenCancelOrderRequestVo requestVo) {
        List<OpenCancelOrderRequestItemVo> openCancelOrderRequestItems = requestVo.getOpenCancelOrderRequestItems();
        if (CollectionUtils.isEmpty(openCancelOrderRequestItems)) {
            throw new BizzException(BizzEx.CTRIP_INTF_RESULT_CHECK,
                    "调用国内机票取消携程订单接口请求订单列表为空.请求入参：" + JSONObject.fromObject(requestVo).toString());
        }
        return openCancelOrder(requestVo);
    }

    /**
     * 拼接国内退票明细查询接口请求
     *
     * @param subOrderId
     * @return
     */
    public RefundOrderDetailRequest getDetailRefundReq(String subOrderId) {
        RefundOrderDetailRequest request = new RefundOrderDetailRequest();

        QueryTicketingOrderInfoRequest queryTicketingOrderInfoRequest = new QueryTicketingOrderInfoRequest();

        ParameterCollection parameterCollection = new ParameterCollection();
        QueryableParameter queryableParameter = new QueryableParameter();
        queryableParameter.setDbType("Int32");
        queryableParameter.setParameterName("OrderID");
        queryableParameter.setValue(Integer.parseInt(subOrderId));

        parameterCollection.setQueryableParameter(queryableParameter);

        queryTicketingOrderInfoRequest.setResultType("0");
        queryTicketingOrderInfoRequest.setTicketingOrderRequestType("Refund");
        queryTicketingOrderInfoRequest.setParameterCollection(parameterCollection);

        //创建header
        String requestType = "FlightQueryTicketingOrder";
        RequestHeader header = ctripHeaderSupport.build(requestType);
        request.setQueryTicketingOrderInfoRequest(queryTicketingOrderInfoRequest);
        request.setHeader(header);
        return request;
    }



    /**
     * 生成 国内机票携程取消订单接口 请求报文
     *
     * @return
     * @author chujun
     * @data 2015-09-11
     */
    private FltOpenCancelOrderRequest getCtripFlightCancelOrder(FltOpenCancelOrderRequestVo fltOpenCancelOrderRequestVo) {
        FltOpenCancelOrderRequest request = new FltOpenCancelOrderRequest();
        ////////////////////////生成header节点\\\\\\\\\\\\\\\\\\\\\\
        RequestHeader header = ctripHeaderSupport.build("cancelOrder");
        header.setAsyncRequest(false);
        header.setTimeout("0");
        header.setMessagePriority("3");
        request.setHeader(header);
        /////////////////////////request主体部分\\\\\\\\\\\\\\\\\\\\\\
        //用户ID-联盟商名称
        String userId = systemConfig.getCtripAuthKey();
        //操作人-联盟商名称
        String eid = "途牛";
        OpenCancelOrderRequest cancelOrderRequest = new OpenCancelOrderRequest();
        OpenCancelOrderRequestItems cancelOrderRequestItems = new OpenCancelOrderRequestItems();
        cancelOrderRequest.setOpenCancelOrderRequestItems(cancelOrderRequestItems);
        List<OpenCancelOrderRequestItem> list = new ArrayList<OpenCancelOrderRequestItem>();
        cancelOrderRequestItems.setOpenCancelOrderRequestItems(list);

        List<OpenCancelOrderRequestItemVo> openCancelOrderRequestItems = fltOpenCancelOrderRequestVo
                .getOpenCancelOrderRequestItems();
        for (OpenCancelOrderRequestItemVo itemVo : openCancelOrderRequestItems) {
            OpenCancelOrderRequestItem item = new OpenCancelOrderRequestItem();
            item.setOrderID(itemVo.getOrderId());
            item.setCancelReason("支付超时");
            item.setUserID(userId);
            item.seteID(eid);
            item.setRemark("联盟商要求取消订单");
            //是否取消PNR 0：不取消 1：取消
            item.setIsCancelPNR(1);
            //是否取消关联订单 0：不取消 1：取消
            item.setIsCancelRelation(1);
            //////////添加关联订单号List\\\\\\\\\\\\\\\\\\\\\\\\\
            List<String> relationCancelOrders = itemVo.getRelationCancelOrders();
            RelationCancelOrder relationOrders = new RelationCancelOrder();
            List<RelationCancelOrderId> relationCancelOrderIds = new ArrayList<RelationCancelOrderId>();
            relationOrders.setRelationCancelOrders(relationCancelOrderIds);
            if (!CollectionUtils.isEmpty(relationCancelOrders)) {
                // LOG.info("【携程取消订单接口生成请求报文】" + itemVo.getOrderId() + "存在关联子订单：" + relationCancelOrders);
                for (String relationOrderId : relationCancelOrders) {
                    RelationCancelOrderId relationCancelOrderId = new RelationCancelOrderId();
                    relationCancelOrderId.setRelationOrderId(relationOrderId);
                    relationCancelOrderId.setXmlns("http://microsoft.com/wsdl/types/");
                    relationCancelOrderIds.add(relationCancelOrderId);
                }
            }
            item.setRelationCancelOrders(relationOrders);
            list.add(item);
        }
        request.setOpenCancelOrderRequest(cancelOrderRequest);
        return request;
    }


    /**
     * 根据订单号查询订单信息--携程订单详情接口
     *
     * @param ctripOrderIds
     * @return
     */
    private OrderDetailRequest getCtripOrderDetailInfoByOrderNo(List<String> ctripOrderIds) {
        OrderDetailRequest request = new OrderDetailRequest();
        // 消息头
        RequestHeader requestHeader = ctripHeaderSupport.build("viewOrderList");
        request.setHeader(requestHeader);
        //报文体
        OrderListDetailsRequest orderListDetailsRequest = new OrderListDetailsRequest();
        OrderIdListInfoType orderIdListType = new OrderIdListInfoType();
        List<Long> orderIds = orderIdListType.getOrderId();
        for (String ctripOrderId : ctripOrderIds) {
            orderIds.add(Long.valueOf(ctripOrderId));
        }
        orderListDetailsRequest.setOrderIdListType(orderIdListType);
        orderListDetailsRequest.setLanguage("CHINESE");
        orderListDetailsRequest.setUserID(systemConfig.getCtripAuthKey());
        request.setOrderListDetailsRequest(orderListDetailsRequest);
        return request;
    }

    /**
     * 设置配送
     */
    public DeliveryInfoType getDeliveryInfo() {
        // 配送信息
        DeliveryInfoType deliveryInfoType = new DeliveryInfoType();
        // 配送类型
        deliveryInfoType.setDeliveryType(DeliveryTypeType.PJN);
        // 指定票台 可空
        deliveryInfoType.setFlightAgency(0);
        // 客票类型 可空
        deliveryInfoType.setTicketType(TicketTypeType.NA);

        deliveryInfoType.setSupportAirportPickUp(false);

        // 第三方邮寄 邮寄、快递地址信息 可空
        ThirdPartyDeliveryType thirdPartyDeliveryType = new ThirdPartyDeliveryType();
        thirdPartyDeliveryType.setProvince("");
        thirdPartyDeliveryType.setDistrict("");
        thirdPartyDeliveryType.setAddr("");
        thirdPartyDeliveryType.setCity("");
        thirdPartyDeliveryType.setPostCode("");
        thirdPartyDeliveryType.setReceiverName("");
        thirdPartyDeliveryType.setExpressPhone("");
        ExpressInfoType expressInfoType = new ExpressInfoType();
        expressInfoType.setMailingType(MailingTypeType.OM);
        expressInfoType.setMailingPayType(MailingPayTypeType.NORMAL);
        expressInfoType.setIntegralAmount(0);
        expressInfoType.setSendTicketFee(new BigDecimal(0));
        thirdPartyDeliveryType.setExpressInfo(expressInfoType);
        deliveryInfoType.setThirdPartyDelivery(thirdPartyDeliveryType);

        return deliveryInfoType;
    }



    /**
     * 订单额外相关信息
     *
     * @return OrderMiscInfoType
     */
    public OrderMiscInfoType getOrderMiscInfoType() {
        // 订单额外相关信息
        OrderMiscInfoType orderMiscInfoType = new OrderMiscInfoType();
        // 是否关联所有子订单号进行修改/提交操作默认
        JAXBElement<Boolean> isConnSubOrder = new JAXBElement<Boolean>(new QName("", "isConnSubOrder"), Boolean.class,
                true);
        orderMiscInfoType.setIsConnectedSubOrder(isConnSubOrder);
        // 用户选择的消费券金额 必传
        orderMiscInfoType.setCashBack(new BigDecimal(0));
        /*ReferencesType referencesType = new ReferencesType();
		// 外部关联订单号 必传
		referencesType.setOuterRelationOrderID(0);
		AllianceInfoType allianceInfoType = new AllianceInfoType();
		// 固定分配 必传
		allianceInfoType.setAllianceID(Integer.parseInt(SystemParamInit.ctripAllianceID));
		// 固定分配 必传
		allianceInfoType.setSID(Integer.parseInt(SystemParamInit.ctripSID));
		referencesType.setAllianceInfo(allianceInfoType);
		// 其他子系统信息
		orderMiscInfoType.setReferences(referencesType);*/
        orderMiscInfoType.setIsAllDayWaitingPolicy(false);
        return orderMiscInfoType;
    }

    /**
     * 
     * Description: 生成订单请求<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param request
     * @return <br>
     */
    private Request getSaveOrderRequestInfo(BookingData request) {
        //生成订单用的请求
        SaveOrderRequestType saveOrderRequestType = new SaveOrderRequestType();
        // 用户id
        saveOrderRequestType.setUserID(systemConfig.getCtripAuthKey());
        // ProductID,40分钟有效
        saveOrderRequestType.setProductID(request.getProductID());
        // 请求模式类型 固定CreateOrder必传
        saveOrderRequestType.setAction(ActionType.CREATE_ORDER);
        //设置订单基本信息
        saveOrderRequestType.setOrderBaseInfo(getOrderBasicInfo(request));
        // 设置航程
        saveOrderRequestType.setFlightSegmentList(getFlightSegmentList(request.getSegments(),
                request.getPriceInfo().getAdultPrice().getPrice()));
        //设置旅客信息
        saveOrderRequestType.setTravelerInfoList(getTravelerInfo(request.getPassengers()));
        //设置联系人
        saveOrderRequestType.setContactInfo(getContactInfo());
        saveOrderRequestType.setDeliveryInfo(getDeliveryInfo());
        saveOrderRequestType.setOrderMiscInfo(getOrderMiscInfoType());
        // 消息头
        Request requestString = new Request();
        RequestHeader requestHeader = ctripHeaderSupport.build("saveOrder");
        requestString.setHeader(requestHeader);
        requestString.setSaveOrderRequestType(saveOrderRequestType);
        return requestString;
    }

    /**
     * 
     * Description: 提交订单请求<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param saveOrderResultType
     * @return <br>
     */
    private Request getSubmitOrderRequestInfo(SaveOrderResultType saveOrderResultType) {
        //提交订单用的请求
        SaveOrderRequestType submitOrderRequestType = new SaveOrderRequestType();
        submitOrderRequestType.setUserID(systemConfig.getCtripAuthKey());
        SaveOrderDetailListType saveOrderDetailList = saveOrderResultType.getSaveOrderDetailList();
        List<SaveOrderDetailType> orderDetailList = saveOrderDetailList.getSaveOrderDetail();
        OrderIdListType orderIdListType = new OrderIdListType();
        List<Long> orderIdList = orderIdListType.getOrderId();
        for (SaveOrderDetailType saveOrderDetailType : orderDetailList) {
            orderIdList.add(saveOrderDetailType.getOrderID());
        }
        // OrderIdList
        submitOrderRequestType.setOrderIdList(orderIdListType);
        //FlightSegmentList
        SimpleFlightSegmentListType flightSegmentList = new SimpleFlightSegmentListType();
        submitOrderRequestType.setFlightSegmentList(flightSegmentList);
        //TravelerInfoList
        // 旅客信息
        TravelerListType travelerInfoList = new TravelerListType();
        submitOrderRequestType.setTravelerInfoList(travelerInfoList);
        //InsuranceInfoList
        InsuranceInfoListType insuranceInfoListType = new InsuranceInfoListType();
        submitOrderRequestType.setInsuranceInfoList(insuranceInfoListType);
        submitOrderRequestType.setDeliveryInfo(getDeliveryInfo());
        PaymentInfoType paymentInfoType = new PaymentInfoType();
        PayTypeListType payTypeList = new PayTypeListType();
        paymentInfoType.setPayTypeList(payTypeList);
        List<String> payType = payTypeList.getPayType();
        payType.add("ALIAN");
        //支付平台交易流水号
        String externalNo = saveOrderResultType.getTripartiteInfo().getExternalNo();
        paymentInfoType.setExternalNo(externalNo);
        submitOrderRequestType.setOrderMiscInfo(getOrderMiscInfoType());
        // 请求模式类型 固定CreateOrder必传
        submitOrderRequestType.setAction(ActionType.SUBMIT_ORDER);
        submitOrderRequestType.setPaymentInfo(paymentInfoType);
        // 消息头
        Request requestString = new Request();
        RequestHeader requestHeader = ctripHeaderSupport.build("saveOrder");
        requestString.setHeader(requestHeader);
        requestString.setSaveOrderRequestType(submitOrderRequestType);
        return requestString;
    }



    /**
     * 设置订单基本信息
     *
     * @param request
     */
    public OrderBaseInfoType getOrderBasicInfo(BookingData request) {
        // 订单基本信息
        OrderBaseInfoType orderBaseInfoType = new OrderBaseInfoType();
        // 携程卡号
        orderBaseInfoType.setCtripCardNo(systemConfig.getCtripCardNo());
        // 分销商官网域名 必传
        orderBaseInfoType.setServiceNo(systemConfig.getCtripServiceNo());
        // 送票城市 默认传出发城市 必传
        Segment flightSegment = request.getSegments().get(0);
        orderBaseInfoType.setSendTicketCity(
                flightSegment.getOrgCityIataCode().equals("PEK") ? "BJS" : flightSegment.getOrgCityIataCode());
        // 订单备注 可空值 最好传分销公司名称+分销订单号过来
        orderBaseInfoType.setOrderDescription("途牛旅游网订单号：" + request.getOrderId());
        // 立即还是暂缓 默认立即A 必传
        orderBaseInfoType.setTicketStatus(TicketStatusType.A);
        // 订位模式 必传 若携程订位则默认A 否则自己订位则传M
        orderBaseInfoType.setBookMode(BookModeType.A);
        // 是否团队票 必传 默认False
        orderBaseInfoType.setIsGroupOrder(false);
        // 是否手工订单 必传 默认False
        orderBaseInfoType.setIsManualOrder(false);
        // 是否快速预定 必传 默认False
        orderBaseInfoType.setIsQuickBooking(false);
        orderBaseInfoType.setDiscountCode("");
        return orderBaseInfoType;
    }

    /**
     * 设置航程参数
     *
     * @param segments
     */
    public SimpleFlightSegmentListType getFlightSegmentList(List<Segment> segments, float adultPrice) {
        // 航程7要素 必传
        SimpleFlightSegmentListType simpleFlightSegmentListType = new SimpleFlightSegmentListType();
        List<SimpleFlightSegmentType> segmentList = simpleFlightSegmentListType.getFlightSegment();
        SimpleFlightSegmentType simpleFlightSegmentType = null;
        for (Segment segment : segments) {
            simpleFlightSegmentType = new SimpleFlightSegmentType();
            // 出发城市 必传
            simpleFlightSegmentType.setDepartCityCode(
                    segment.getOrgCityIataCode().equals("PEK") ? "BJS" : segment.getOrgCityIataCode());
            // 到达城市 必传
            simpleFlightSegmentType.setArriveCityCode(
                    segment.getDstCityIataCode().equals("PEK") ? "BJS" : segment.getDstCityIataCode());
            // 航班号 必传
            simpleFlightSegmentType.setFlightNo(segment.getFlightNo());
            // 子舱位 必传
            simpleFlightSegmentType.setSubClass(segment.getSeatCode());
            // 价格不含基建燃油 必传(传的是成人成本价)
            simpleFlightSegmentType.setPrice(new BigDecimal(adultPrice));
            // 出发时间 必传
            String takeOffTime = segment.getDepartureDate().substring(0, 10);
            simpleFlightSegmentType.setTakeOffTime(takeOffTime);
            // 政策类型 必传
            simpleFlightSegmentType.setProductType(ProductTypeType.fromValue(segment.getProductTypeCtrip()));

            // saleType 和PID
            String saleType = SaleTypeCtrip.getCodeFromValue(segment.getSaleTypeCtrip());
            simpleFlightSegmentType.setSaleType(saleType);
            simpleFlightSegmentType.setPid(segment.getPidCtrip());
            segmentList.add(simpleFlightSegmentType);
        }
        return simpleFlightSegmentListType;
    }


    /**
     * 旅客信息参数
     *
     * @param passengerInfos
     */
    public TravelerListType getTravelerInfo(List<Passenger> passengerInfos) {
        // 旅客信息
        TravelerListType travelerListType = new TravelerListType();
        List<TravelerInfoType> travelerList = travelerListType.getTraveler();
        // 旅客数组
        for (Passenger passengerInfo : passengerInfos) {
            TravelerInfoType travelerInfoType = new TravelerInfoType();
            // 旅客姓名 必传
            travelerInfoType.setPersonName(passengerInfo.getPassengerName());
            // 旅客性别 必传
            JAXBElement<GenderType> genderType = new JAXBElement<GenderType>(new QName("", "genderType"),
                    GenderType.class, null);
            genderType.setValue(passengerInfo.getGender() == 0 ? GenderType.FEMALE : GenderType.MALE);
            travelerInfoType.setGender(genderType);
            PersonType.ContactInfo contactInfo = new PersonType.ContactInfo();
            // 联系手机号 必传 请传联盟公司手机13218001922
            contactInfo.setMobilePhone1(systemConfig.getCtripMobilePhoneNo());
            travelerInfoType.setContactInfo(contactInfo);
            //成人
            if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passengerInfo.getPassengerTypeCode())) {
                travelerInfoType.setTravelerCategory(TravelerCategoryCodeType.ADULT);
            }
            // 儿童
            else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passengerInfo.getPassengerTypeCode())) {
                travelerInfoType.setTravelerCategory(TravelerCategoryCodeType.CHILD);
            }
            IDCardInfoType idCardInfoType = new IDCardInfoType();
            // 证件类型,需要一一对应，目前默认为身份证
            int identityType = passengerInfo.getIdentityType();
            String identityNo = "";
            CtripCardConvert[] cardValues = CtripCardConvert.values();
            for (CtripCardConvert cardConvert : cardValues) {
                if (identityType == cardConvert.getIntValue()) {
                    identityNo = cardConvert.name();
                    break;
                }
            }
            idCardInfoType.setIDCardType(IDCardType.fromValue(identityNo));
            idCardInfoType.setIDCardNo(passengerInfo.getIdentityNo());
            travelerInfoType.setIDCard(idCardInfoType);
            // 年龄段类型 必传
            travelerInfoType.setAgeType(convertAgeType(passengerInfo.getPassengerTypeCode()));
            // 生日 必传
            String birthday = passengerInfo.getBirthday().substring(0, 10);
            travelerInfoType.setBirthday(birthday);
            // 国籍 必传
            travelerInfoType.setNationality("CN");
            // 确认类型 必传
            travelerInfoType.setConfirmStyle(ConfirmStyleType.NOR);
            travelerList.add(travelerInfoType);
        }
        return travelerListType;
    }

    /**
     * 
     * Description: 联系人信息<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @return <br>
     */
    public ContactInfoType getContactInfo() {
        // 联系人 必传
        ContactInfoType contactInfoType = new ContactInfoType();
        contactInfoType.setPersonName("途牛旅游网");
        // 联系人性别 必传
        JAXBElement<GenderType> genderType 
        = new JAXBElement<GenderType>(new QName("", "genderType"), GenderType.class, null);
        genderType.setValue(GenderType.MALE);
        contactInfoType.setGender(genderType);
        PersonType.ContactInfo contactInfo = new PersonType.ContactInfo();
        // 联系手机号 必传 请传联盟公司手机13218001922
        contactInfo.setMobilePhone1(systemConfig.getCtripMobilePhoneNo());
        contactInfo.setContactEmail(systemConfig.getCtripContactEmail());
        contactInfoType.setConfirmStyle(ConfirmStyleType.MSG);
        contactInfoType.setContactInfo(contactInfo);
        return contactInfoType;
    }



    public String convertAgeType(String passengerType) {
        String ageType = "";
        //成人
        if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passengerType)) {
            ageType = "ADU";
        } else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passengerType)) {
            ageType = "CHI";
        } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passengerType)) {
            ageType = "BAB";
        }
        return ageType;
    }
}
