package com.tuniu.abt.service.book.ctrip;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.ResFlightQueryReq;
import com.tuniu.abt.service.ctrip.module.CtripCommonModule;
import com.tuniu.abt.service.ctrip.module.CtripOrderModule;
import com.tuniu.abt.service.ctrip.module.CtripQueryModule;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.PinyinUtil;
import com.tuniu.adapter.flightTicket.domain.FlightIndivFlight;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeat;
import com.tuniu.adapter.flightTicket.domain.FlightIndivSeatPriceDomestic;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.FlightSearchResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.PolicyInfoType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.PriceInfoType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.ProductEntityType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.data.flighttypes.TravelerCategoryCodeType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.*;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.SaveOrderDetailListType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.SaveOrderDetailType;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.saveOrder.SaveOrderResultType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * <Description> 携程占位服务类<br> 
 *  
 * @author lanlugang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-3-6 <br>
 */
@Component
public class BookCtripService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCtripService.class);

    @Resource
    private CtripOrderModule ctripOrderModule;
    
    @Resource
    private CtripCommonModule ctripCommonModule;

    @Resource
    private CtripQueryModule ctripQueryModule;

    /**
     * 反向查询获取productId
     * @param bookingData
     */
    public void fillProductIdAndSaleType(BookingData bookingData) {
        // 反向查询
        FlightSearchResponse response = ctripQueryModule.flightReverseSearch(bookingData);
        ProductEntityType product = response.getResult().getProductList().getProduct().get(0);
        // 设置productId
        bookingData.setProductID(product.getProductID());
        // 更新成人价格
        List<PolicyInfoType> policyInfoTypes = response.getResult().getPolicyInfoList().getPolicyInfo();
        if (CollectionUtils.isEmpty(policyInfoTypes)) {
            throw new BizzException(BookEx.CTRIP_REVERT_SEARCH_POLICY_FAILED, "携程反向查询获取政策信息为空");
        }
        for (PriceInfoType price : policyInfoTypes.get(0).getItineraryPriceInfoList().getPriceInfo()) {
            if (TravelerCategoryCodeType.ADULT.equals(price.getTravelerCategory())) {
                bookingData.getPriceInfo().getAdultPrice().setPrice(price.getPrice().floatValue());
            }
        }
        // 设置航段信息
        for (int i = 0; i < bookingData.getSegments().size(); i++) {
            Segment segment = bookingData.getSegments().get(i);
            PolicyInfoType policyInfoType = policyInfoTypes.get(i);
            if (null != policyInfoType) {
                segment.setSeatCode(policyInfoType.getRealSubclass());
                segment.setSaleTypeCtrip(policyInfoType.getSaleType());
                segment.setPidCtrip(policyInfoType.getPid());
                segment.setProductTypeCtrip(policyInfoType.getProductType().value());
            }
        }
    }

    /**
     * 根据航程类型(判断是否有重名)和乘客类型，将bookingData拆分成一个或多个，分别调用携程占座接口
     *
     * @param bookingData
     * @return
     * @throws Exception
     */
    public List<BookingData> convert2BookingRqList(BookingData bookingData)
            throws Exception {
        List<BookingData> bookingRqList = new ArrayList<BookingData>();
        BookingData bookingRq = null;
        //1.按航段进行拆分：如果是单程，则不用拆分
        if (bookingData.getSegments().size() <= 1) {
            bookingRqList.add(bookingData);
        } else {
            for (Segment flightSegment : bookingData.getSegments()) {
                bookingRq = CommonUtils.deepCloneObject(bookingData);
                bookingRq.getSegments().clear();
                bookingRq.getSegments().add(flightSegment);
                bookingRqList.add(bookingRq);
            }
        }
        //2.如果旅客出现重名，则将重名的旅客分开
        boolean hasDuplicatedName = false;
        Map<String, Passenger> map = new HashMap<String, Passenger>();
        Map<Integer, List<Passenger>> passergerGroups = new HashMap<Integer, List<Passenger>>();
        int groupId = 1;
        passergerGroups.put(groupId, new ArrayList<Passenger>());
        String passergerName = null;
        String passergerNamePinyin = null;
        for (Passenger passengerInfo : bookingData.getPassengers()) {
            passergerName = passengerInfo.getPassengerName();
            passergerNamePinyin = PinyinUtil.getPinyin(passergerName).toLowerCase();
            //如果没有出现重名或同音的情况，则将该乘客放入第一组乘客中
            if (!map.containsKey(passergerName) && !map.containsKey(passergerNamePinyin)) {
                map.put(passergerName, passengerInfo);
                map.put(passergerNamePinyin, passengerInfo);
                passergerGroups.get(1).add(passengerInfo);
            }
            //如果出现重名或者拼音相同的乘客，则将该乘客放到新的一组乘客中
            else {
                //如果儿童出现重名，则直接抛出异常
                if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passengerInfo.getPassengerTypeCode())) {
                    LOGGER.error("【携程占位】同一订单中儿童姓名不能和其他乘客姓名重复或同音，请重新预定！");
                    throw new BizzException(BookEx.CTRIP_PRE_DATA_ERROR,
                            "【携程占位】同一订单中儿童姓名不能和其他乘客姓名重复或同音，请重新预定！");
                } else {
                    hasDuplicatedName = true;
                    groupId++;
                    passergerGroups.put(groupId, new ArrayList<Passenger>());
                    passergerGroups.get(groupId).add(passengerInfo);
                }
            }
        }
        //如果成人出现重名，则进行拆单
        if (hasDuplicatedName) {
            List<BookingData> finalBookingDatas = new ArrayList<BookingData>();
            for (BookingData bookingSubRq : bookingRqList) {
                for (Iterator<Integer> iterator = passergerGroups.keySet().iterator();
                        iterator.hasNext();) {
                    int key = iterator.next();
                    bookingRq = CommonUtils.deepCloneObject(bookingSubRq);
                    bookingRq.getPassengers().clear();
                    bookingRq.getPassengers().addAll(passergerGroups.get(key));
                    finalBookingDatas.add(bookingRq);

                }
            }
            return finalBookingDatas;
        }
        return bookingRqList;
    }
    
    /**
     * 调用携程的占座接口进行占座
     *
     * @param bookingData
     * @return
     * @throws Exception
     */
    public List<OrderInfoEntity> occupy4Ctrip(BookingData bookingData) throws Exception {
        /**调用携程生成订单接口 */
        SaveOrderResultType saveOrderResult = ctripOrderModule.saveOrder(bookingData, "CreateOrder")
                                                              .getFlightSaveOrderResponse().getSaveOrderResult();
        if (!saveOrderResult.isFinalResult()) {
            String errMsg = "创建携程订单失败";
            if (null != saveOrderResult.getFinalResultMsg()) {
                errMsg += ",失败原因:" + saveOrderResult.getFinalResultMsg();
            }
            throw new BizzException(BookEx.CTRIP_CREATE_ORDER_FAILED, errMsg);
        }
        /**订单生成成功,延迟三秒,调用携程提交订单接口 */
        Thread.sleep(3000);
        SaveOrderResultType submitOrderResult = ctripOrderModule.saveOrder(saveOrderResult,"SubmitOrder")
                                                                .getFlightSaveOrderResponse().getSaveOrderResult();
        boolean submitOrderFlag = submitOrderResult.isFinalResult();
        String strategyCode = submitOrderResult.getStrategyCode();
        String finalResultMsg = submitOrderResult.getFinalResultMsg();
        //检查提交订单结果是否存在失信人 chujun 2015-09-17 [AIR-923]
        checkIsLostCreditForCtrip(submitOrderResult);
        if (!submitOrderFlag || !strategyCode.equals("SUCCESS") || !finalResultMsg.equals("订单提交成功")) {
            String errMsg = "提交携程订单失败";
            if (null != saveOrderResult.getFinalResultMsg()) {
                errMsg += ",失败原因:" + saveOrderResult.getFinalResultMsg();
            }
            throw new BizzException(BookEx.CTRIP_SUBMIT_ORDER_FAILED, errMsg);
        }
        /**根据订单号查询订单信息 */
        List<OrderInfoEntity> orderInfoEntityList = null;
        int repeatTimes = 3;
        for (int i = 0; i < repeatTimes; i++) {
            List<String> ctripOrderIds = getCtripOrderIds(saveOrderResult);
            OAEViewOrderListDetailsResponse ordersDetail = ctripOrderModule.viewOrderListDetails(ctripOrderIds).getOrderListDetailResponse();
            if (!ordersDetail.isResult()) {
                String errMsg = "查询携程订单详情失败";
                if (null != ordersDetail.getResultMessage()) {
                    errMsg += ",失败原因:" + ordersDetail.getResultMessage();
                }
                throw new BizzException(BookEx.CTRIP_QUERY_ORDER_FAILED, errMsg);
            }
            ArrayOfOrderInfoEntity orderInformation = ordersDetail.getOrderInformation();
            if (null != orderInformation) {
                orderInfoEntityList = orderInformation.getOrderInfoEntity();
                if (CollectionUtils.isNotEmpty(orderInfoEntityList)
                        && ctripOrderIds.size() == orderInfoEntityList.size()) {
                    return orderInfoEntityList;
                }
            }
            // 如果请求达到指定次数还没有获取详情，则返回占位失败
            if (i + 1 == repeatTimes) {
                throw new BizzException(BookEx.CTRIP_QUERY_ORDER_FAILED,
                        "第" + (i + 1) + "次查询携程订单详情结果不准确," +
                                "错误原因:返回订单详情结果为true,但获取的订单数为空或者与请求中的订单数不符。");
            } else {
                // 等待5s，再次查询订单详情
                Thread.sleep(5000);
            }
        }
        return orderInfoEntityList;
    }

    /**
     * 检查携程占位结果是否是失信人
     *
     * @param saveOrderResultType
     * @return
     * @author chujun
     * @date 2015-09-17
     * @jira [AIR-923] 当占位时，携程返回字段<FinalResultCode>INFO_002</FinalResultCode>时就判定是失信人，不看携程的结果，都判定占位失败
     */
    private void checkIsLostCreditForCtrip(SaveOrderResultType saveOrderResultType) {
        String finalResultCode = saveOrderResultType.getFinalResultCode();
        if ("INFO_002".equals(finalResultCode)) {
            throw new BizzException(BookEx.PASSENGER_BREAK_EXECUTION_BANNED,
                    "【国内机票占位】调用携程提交订单详情接口失败，订单中包含被失信人，预订失败。");
        }
    }
    
    public static List<String> getCtripOrderIds(SaveOrderResultType saveOrderResult) {
        List<String> list = new ArrayList<String>();
        SaveOrderDetailListType saveOrderDetailList = saveOrderResult.getSaveOrderDetailList();
        List<SaveOrderDetailType> detailList = saveOrderDetailList.getSaveOrderDetail();
        for (SaveOrderDetailType saveOrderDetailType : detailList) {
            list.add(String.valueOf(saveOrderDetailType.getOrderID()));
        }
        return list;
    }
    
    /**
     * 携程订单入库
     *
     * @param bookingData
     * @param orderInfoEntityList
     * @throws ParseException 
     */
    public List<PnrInfo> getCtripOrderInfo(BookingData bookingData, List<OrderInfoEntity> orderInfoEntityList) throws ParseException {
        List<PnrInfo> PnrInfoList = new ArrayList<PnrInfo>();
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            OrderBasicInfoEntity baseOrderInformation = orderInfoEntity.getBasicOrderInformation();
            long out_order_id = baseOrderInformation.getOrderID();
            XMLGregorianCalendar orderDate = baseOrderInformation.getOrderDate();
            String clearTime = getClearTimeByOrderTime(orderDate.toString(), 30);
            String orderStatusDisplay = baseOrderInformation.getOrderStatusDisplay();
            List<OrderFlightEntity> orderFlightEntities = orderInfoEntity.getOrderFlights().getOrderFlight().getOrderFlightEntity();
            RelationForExtNoEntity extNoEntity = orderInfoEntity.getRelationForExtNoInfo().getRelationForExtNoEntity().get(0);
            long out_main_order_id = extNoEntity.getOrderID();
            String extNo = extNoEntity.getExternalNo();
            //子订单乘客类型1成人2儿童3婴儿
            List<OrderPassengerEntity> orderPassengers = orderInfoEntity.getOrderPassengers()
                    .getOrderPassenger().getOrderPassengerEntity();
            String ageType = orderPassengers.get(0).getAgeType();
            int passengerType = ctripCommonModule.convertPassengerType(ageType);
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfo.setPnrNo(String.valueOf(out_order_id));
            pnrInfo.setOccupyType(passengerType);
            pnrInfo.setAssociatedPnrNo(String.valueOf(out_main_order_id));
            pnrInfo.setClearTime(clearTime);
            pnrInfo.setSegments(bookingData.getSegments());
            pnrInfo.setPassengers(getPassengers(bookingData.getPassengers(), orderPassengers));
            pnrInfo.setExternalNo(extNo);
            pnrInfo.setOrderStatusDisplay(orderStatusDisplay);
            pnrInfo.getPrices().add(getPnrPriceList(orderFlightEntities));
            PnrInfoList.add(pnrInfo);
        }
        return PnrInfoList;
    }

    private List<Passenger> getPassengers(List<Passenger> passengers,
                                          List<OrderPassengerEntity> orderPassengers) {
        List<Passenger> passengerList = new ArrayList<Passenger>();
        for (OrderPassengerEntity orderPassenger : orderPassengers) {
            for (Iterator<Passenger> iter = passengers.iterator(); iter.hasNext();) {
                Passenger passenger = iter.next();
                if (passenger.getPassengerName()
                        .equals(orderPassenger.getPassengerName())) {
                    passengerList.add(passenger);
                    iter.remove();
                    break;
                }
            }
        }
        return passengerList;
    }

    public static String getClearTimeByOrderTime(String orderTime, int minutes) throws ParseException {
        //将订单时间截取方式改为从前截取[19]位：【 edited by @lanlugang 2015-4-23 11:14:22】
        String substring = orderTime.replaceAll("T", " ").substring(0, 19);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(substring);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //清位时间重新改为30min edited by @lanlugang 2015-5-25 21:25:48
        calendar.add(Calendar.MINUTE, minutes);
        return sdf.format(calendar.getTime());
    }
    
    private PnrPrice getPnrPriceList(List<OrderFlightEntity> orderFlightEntities) {
        Float salePrice = new Float(0.0);
        Float orgPrice = new Float(0.0);
        Float floorPrice = new Float(0.0);
        Float fuelSurcharge = new Float(0.0);
        Float airportTax = new Float(0.0);
        Long saleId = null;
        for (OrderFlightEntity entity : orderFlightEntities) {
            salePrice += entity.getPrice().floatValue();
            orgPrice += entity.getPrintPrice().floatValue();
            floorPrice += entity.getPrice().floatValue();
            fuelSurcharge += entity.getOilFee().floatValue();
            airportTax += entity.getTax().floatValue();
        }
        int psgType = ctripCommonModule.convertPassengerType(orderFlightEntities.get(0).getAgeType());
        PnrPrice pnrPrice = new PnrPrice();
        pnrPrice.setPassengerType(psgType);
        pnrPrice.setPriceType(psgType);
        pnrPrice.setSalePrice(salePrice);
        pnrPrice.setOrgPrice(orgPrice);
        pnrPrice.setFloorPrice(floorPrice);
        pnrPrice.setFuelSurcharge(fuelSurcharge);
        pnrPrice.setAirportTax(airportTax);
        pnrPrice.setSaleId(saleId);
        return pnrPrice;
    }

    public void checkCtripOrderIsValid(List<OrderInfoEntity> orderInfoEntityList, BookingData bookingRq) {
        int psgNum = bookingRq.getPassengers().size();
        int segmentNum = bookingRq.getSegments().size();
        int psnSegNumTotal = 0;
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            OrderPassengersEntity orderPassengersEntity = orderInfoEntity.getOrderPassengers();
            ArrayOfOrderPassengerEntity orderPassengerArray = orderPassengersEntity.getOrderPassenger();
            List<OrderPassengerEntity> orderPassengerEntityList = orderPassengerArray.getOrderPassengerEntity();
            psnSegNumTotal += orderPassengerEntityList.size();
            OrderBasicInfoEntity basicOrderInformation = orderInfoEntity.getBasicOrderInformation();
            String orderStatus = basicOrderInformation.getOrderStatus();
            if (null != orderStatus && !"".equals(orderStatus)) {
                //订单状态为 :<!--订单状态 U=未提交;P=处理中;W=已提交，等待处理;C=取消;S=成交-->
                if (orderStatus.equalsIgnoreCase("C")) {
                    throw new BizzException(BookEx.CTRIP_ORDER_INVALID,
                            "【国内机票占位】携程占位失败，失败原因：携程订单详情中订单状态为已取消");
                }
            }
        }
        // 校验乘机人数与实际占位成功的人数是否一致
        if (psnSegNumTotal != psgNum * segmentNum) {
            throw new BizzException(BookEx.CTRIP_ORDER_INVALID,
                    "【国内机票占位】携程占位失败，失败原因：生成订单详情中乘机人数与占位人数不一致");
        }
    }

}
