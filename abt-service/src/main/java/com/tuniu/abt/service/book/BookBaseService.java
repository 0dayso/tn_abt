package com.tuniu.abt.service.book;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.backmeal.ReqBackMeal;
import com.tuniu.abt.intf.dto.backmeal.RespRuleInfo;
import com.tuniu.abt.intf.dto.backmeal.RespRuleRoot;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.dto.book.response.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspFltInterface;
import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateRequestVo;
import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateResultVo;
import com.tuniu.abt.intf.tsp.dto.flt.BanQueryRequest;
import com.tuniu.abt.intf.tsp.dto.flt.BanQueryResponse;
import com.tuniu.abt.intf.tsp.dto.res.FlightIndivVendorPrice;
import com.tuniu.abt.intf.tsp.dto.res.ResFlightCabinfareReq;
import com.tuniu.abt.intf.tsp.dto.res.ResFlightVendorPriceReq;
import com.tuniu.abt.intf.tsp.dto.res.ResSegment;
import com.tuniu.abt.service.adt.FreightService;
import com.tuniu.abt.service.backmeal.BackMealFacadeService;
import com.tuniu.abt.service.cachework.LuggageInfoCacheWorker;
import com.tuniu.abt.service.res.ResourceService;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.domain.FlightIndivCabinFare;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailFligthSegVo;
import com.tuniu.adapter.flightTicket.vo.inquiry.AirportSeatClassLuggageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 数据库层面或供各service调用的一些基础服务
 * <p>
 * Created by chengyao on 2016/1/12.
 */
@Component
public class BookBaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookBaseService.class);

    @Resource
    private BackMealFacadeService backMealFacadeService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private FreightService freightService;

    @Resource
    private LuggageInfoCacheWorker luggageInfoCacheWorker;

    @Resource
    private TspFltInterface tspFltInterface;


    /**
     * Description: <br>
     *
     * @param request
     * @param bookingReply
     * @return <br>
     * @author lanlugang<br>
     * @taskId <br>
     */
    public BookingResData convertToBookingResData(BookingRequest request, BookingReply bookingReply) {
        BookingResData resData = new BookingResData();
        resData.setFlightItemId(request.getBookingDetail().getFlightItemId());
        if (request.getVendorId() == BizzConstants.V_TS) { // 航信支持部分取消
            resData.setPartialCancellation(true);
        }
        OrderInfo orderInfo = null;
        for (PnrInfo pnrInfo : bookingReply.getPnrInfos()) {
            orderInfo = new OrderInfo();
            orderInfo.setOrderId(pnrInfo.getPnrNo());
            orderInfo.setPnr(pnrInfo.getPnrNo());
            orderInfo.setParentOrderId(pnrInfo.getAssociatedPnrNo());
            orderInfo.setVendorId(request.getVendorId());
            orderInfo.setClearTime(pnrInfo.getClearTime());
            FlightCabin flightCabin = null;
            for (Segment segment : pnrInfo.getSegments()) {
                flightCabin = new FlightCabin();
                flightCabin.setFlightNo(segment.getFlightNo());
                flightCabin.setCabinCode(segment.getSeatCode());
                flightCabin.setLuggageInfo(JSON.toJSONString(getLuggageInfo(segment)));
                orderInfo.getFlightCabinList().add(flightCabin);
            }
            int adtNum = 0;
            int chdNum = 0;
            int infNum = 0;
            PassengerInfo psgInfo = null;
            for (Passenger passenger : pnrInfo.getPassengers()) {
                psgInfo = new PassengerInfo();
                psgInfo.setName(passenger.getPassengerName());
                psgInfo.setIdNumber(passenger.getIdentityNo());
                orderInfo.getPassengers().add(psgInfo);
                if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passenger.getPassengerTypeCode())) {
                    chdNum++;
                } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                    infNum++;
                } else {
                    adtNum++;
                }
            }
            PriceDetail priceDetail = new PriceDetail();
            priceDetail.setAdultNum(adtNum);
            priceDetail.setChildNum(chdNum);
            priceDetail.setBabyNum(infNum);
            PriceItem priceItem = null;
            for (PnrPrice price : pnrInfo.getPrices()) {
                priceItem = CommonUtils.transform(price, PriceItem.class);
                if (BizzConstants.PERSON_TYPE_ADULT == price.getPriceType()
                        && BizzConstants.PERSON_TYPE_ADULT == pnrInfo.getOccupyType()) {
                    priceDetail.setAdultPrice(priceItem);
                } else if (BizzConstants.PERSON_TYPE_CHILD == price.getPriceType()
                        || BizzConstants.PERSON_TYPE_CHILD == pnrInfo.getOccupyType()) {
                    priceDetail.setChildPrice(priceItem);
                } else if (BizzConstants.PERSON_TYPE_BABY == price.getPriceType()) {
                    priceDetail.setBabyPrice(priceItem);
                }
            }
            orderInfo.setPriceDetail(priceDetail);
            String rcsPolicyData = getFlightRCSPolicyData(request.getVendorId(), pnrInfo);
            orderInfo.setFlightRCSPolicyData(rcsPolicyData);
            resData.getOrderInfoList().add(orderInfo);
        }
        return resData;
    }

    private String getFlightRCSPolicyData(int vendorId, PnrInfo pnrInfo) {
        if (pnrInfo.getSegments().size() > 1) {
            return null;
        }
        ReqBackMeal request = new ReqBackMeal();
        request.setVendorId(vendorId);
        request.setPassengerType(pnrInfo.getOccupyType());
        try {
            Segment segment = pnrInfo.getSegments().get(0);
            request.setFlightNo(segment.getFlightNo());
            request.setCabin(segment.getSeatCode());
            request.setOrgCityCode(segment.getOrgCityIataCode());
            request.setDstCityCode(segment.getDstCityIataCode());
            request.setDepartureDate(segment.getDepartureDate());
            request.setDepartureTime(segment.getDepartureTime());
            request.setTicketDate(DateTimeUtils.formatDate(new Date(), DateTimeUtils.DATE_PATTERN));
            Float costPrice = 0f;
            if (pnrInfo.getOccupyType() == BizzConstants.PERSON_TYPE_ADULT) {
                for (PnrPrice pnrPrice : pnrInfo.getPrices()) {
                    if (pnrPrice.getPriceType() == BizzConstants.PERSON_TYPE_ADULT) {
                        costPrice = pnrPrice.getOrgPrice();
                        break;
                    }
                }
            } else if (pnrInfo.getOccupyType() == BizzConstants.PERSON_TYPE_CHILD) {
                costPrice = pnrInfo.getPrices().get(0).getOrgPrice();
            }
            if (costPrice == 0) {
                throw new BizzException(BookEx.POST_DATA_PACK_ERROR, "占位返回pnr票面价为0,查询退改签规则失败");
            }
            request.setCostPrice(costPrice);
            Float fullPrice = getFullPriceByRes(vendorId, segment);
            if (null == fullPrice || fullPrice == 0) {
                throw new BizzException(BookEx.POST_DATA_PACK_ERROR, "从资源查询舱等全价为0,查询退改签规则失败");
            }
            request.setFullPrice(fullPrice);
            RespRuleRoot respRuleRoot = backMealFacadeService.queryRule(request);
            String title = segment.getOrgCityName() + "-" + segment.getDstCityName();
            return convertToRCSPolicyData(title, respRuleRoot, pnrInfo.getOccupyType());
        } catch (Exception e) {
            LOGGER.error("占位成功后查询退改签规则异常, request:" + JSON.toJSONString(request), e);
            return null;
        }
    }

    private Float getFullPriceByRes(int vendorId, Segment segment) {
        ResFlightCabinfareReq request = new ResFlightCabinfareReq();
        request.setFlightNos(new String[]{segment.getFlightNo()});
        request.setCabinClass(segment.getSeatClass());
        request.setOrgCityIataCode(segment.getOrgCityIataCode());
        request.setDstCityIataCode(segment.getDstCityIataCode());
        request.setSolutionIds(new int[]{vendorId});
        try {
            List<FlightIndivCabinFare> cabinFares = resourceService.findFlightCabinFares(request);
            if (CollectionUtils.isNotEmpty(cabinFares)) {
                return (float)cabinFares.get(0).getCabinPrice();
            }
        } catch (Exception e) {
            LOGGER.error("从资源查询舱等全价失败", e);
        }
        return null;
    }

    private String convertToRCSPolicyData(String title, RespRuleRoot respRuleRoot, int occupyType) {
        FlightRCSPolicyData rcsPolicyData = new FlightRCSPolicyData();
        rcsPolicyData.setTitle(title);
        rcsPolicyData.setBakRule(respRuleRoot.getNoticeMsg());
        if (BizzConstants.PERSON_TYPE_CHILD == occupyType) {// 儿童退改签
            rcsPolicyData.setReturnRule(respRuleRoot.getChildRuleDesc());
            rcsPolicyData.setModifyRule(respRuleRoot.getChildRuleDesc());
        } else if (CollectionUtils.isNotEmpty(respRuleRoot.getRuleInfos())) {
            for (RespRuleInfo respRuleInfo : respRuleRoot.getRuleInfos()) {
                // 退票规则
                if (RespRuleInfo.FLAG_REFUND == respRuleInfo.getRuleFlag()) {
                    rcsPolicyData.setReturnRule(JSON.toJSONString(respRuleInfo));
                    // 改期规则
                } else if (RespRuleInfo.FLAG_SAME == respRuleInfo.getRuleFlag()) {
                    rcsPolicyData.setModifyRule(JSON.toJSONString(respRuleInfo));
                }
            }
        }
        return JSON.toJSONString(rcsPolicyData);
    }

    /**
     *
     * Description: 从资源系统获取航段价格<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param segments
     * @param vendorId
     * @return <br>
     */
    public List<FlightIndivVendorPrice> getResPriceList(List<Segment> segments, int vendorId) {
        ResFlightVendorPriceReq request = new ResFlightVendorPriceReq();
        request.setVendorId(vendorId);
        ResSegment resSegment = null;
        for (Segment segment : segments) {
            resSegment = new ResSegment();
            resSegment.setFlightNo(segment.getFlightNo());
            resSegment.setSeatCode(segment.getSeatCode());
            resSegment.setOrgCityIataCode(segment.getOrgCityIataCode());
            resSegment.setDstCityIataCode(segment.getDstCityIataCode());
            resSegment.setDepartureDate(segment.getDepartureDate());
            request.getSegments().add(resSegment);
        }
        return resourceService.findFlightVendorPrices(request);
    }

    /**
     * 销控更新成人售卖价
     * @param vendorId
     * @param systemId
     * @param pnrInfos
     */
    public void updatePnrPriceBySaleControl(int vendorId, int systemId, List<PnrInfo> pnrInfos) {
        for (PnrInfo pnrInfo : pnrInfos) {
            // 成人价格进行销控调价
            if (BizzConstants.PERSON_TYPE_ADULT == pnrInfo.getOccupyType()) {
                for (PnrPrice pnrPrice : pnrInfo.getPrices()) {
                    if (BizzConstants.PERSON_TYPE_ADULT == pnrPrice.getPriceType()) {
                        FreightCalculateRequestVo freightRequestVo = new FreightCalculateRequestVo();
                        freightRequestVo.setSolutionId(vendorId);
                        freightRequestVo.setSystemId(systemId);
                        freightRequestVo.setPrice(pnrPrice.getSalePrice().intValue());
                        List<DetailFligthSegVo> flightSegVoList =
                                CommonUtils.transformList(pnrInfo.getSegments(), DetailFligthSegVo.class);
                        freightRequestVo.getFlightSegList().addAll(flightSegVoList);
                        try {
                            FreightCalculateResultVo freightResultVo = freightService.qryDetailPrice(freightRequestVo);
                            pnrPrice.setSalePrice((float) freightResultVo.getSalePrice());
                            pnrPrice.setSaleId((long)freightResultVo.getSaleId());
                        } catch (Exception e) {
                            LOGGER.error("调运价查询销售价格失败,请求:" + JSON.toJSONString(freightRequestVo), e);
                        }
                        break;
                    }
                }
            }
        }
    }

    private LuggageInfo getLuggageInfo(Segment segment) {
        //如果该资源是奥凯航空的，则要根据机型进行判断获取行李额信息
        if ("BK".equals(segment.getAirlineCompanyIataCode())) {
            LuggageInfo luggageInfo = new LuggageInfo();
            String airType = segment.getPlaneType();
            String seatCode = segment.getSeatCode();
            if ("737".equals(airType) || "738".equals(airType) || "739".equals(airType)) {
                if (BizzConstants.BK_FIRSTCLASS_SEAT_CODE.contains(seatCode.substring(0, 1))) {
                    luggageInfo.setFreeLuggage(40);
                    luggageInfo.setFreeLuggageUnit("KG");
                } else if (BizzConstants.BK_ECONOMYCLASS_SEAT_CODE.contains(seatCode.substring(0, 1))) {
                    luggageInfo.setFreeLuggage(20);
                    luggageInfo.setFreeLuggageUnit("KG");
                }
            } else if ("M60".equalsIgnoreCase(airType) || "MA6".equalsIgnoreCase(airType)) {
                if (BizzConstants.BK_FIRSTCLASS_SEAT_CODE.contains(seatCode.substring(0, 1))) {
                    luggageInfo.setFreeLuggage(20);
                    luggageInfo.setFreeLuggageUnit("KG");
                } else if (BizzConstants.BK_ECONOMYCLASS_SEAT_CODE.contains(seatCode.substring(0, 1))) {
                    luggageInfo.setFreeLuggage(15);
                    luggageInfo.setFreeLuggageUnit("KG");
                }
            }
            return luggageInfo;
        }

        try {
            List<AirportSeatClassLuggageInfo> infos = luggageInfoCacheWorker.find(segment.getAirlineCompanyIataCode());
            if (CollectionUtils.isEmpty(infos)) {
                return null;
            }
            for (Iterator<AirportSeatClassLuggageInfo> iterator = infos.iterator(); iterator.hasNext();) {
                AirportSeatClassLuggageInfo info = iterator.next();
                // 状态筛选
                if (info.getState() != 2) {
                    iterator.remove();
                }
                // 有效期筛选
                Date beginDate = DateUtils.parseDate(info.getBeginTime(), DateTimeUtils.DATE_PATTERN);
                Date endDate = DateUtils.parseDate(info.getEndTime(), DateTimeUtils.DATE_PATTERN);
                Date departureDate = DateUtils.parseDate(segment.getDepartureDate(), DateTimeUtils.DATE_PATTERN);
                if (beginDate.compareTo(departureDate) > 0 && endDate.compareTo(departureDate) < 0) {
                    iterator.remove();
                }
                // 舱位筛选
                List<String> seatCodes = Arrays.asList(info.getSeatCode().split(","));
                if (!seatCodes.contains(segment.getSeatCode())) {
                    iterator.remove();
                }
            }
            if (CollectionUtils.isEmpty(infos)) {
                return null;
            }
            LuggageInfo luggageInfo = new LuggageInfo();
            luggageInfo.setFreeLuggage(infos.get(0).getFreeLuggage());
            luggageInfo.setFreeLuggageUnit(infos.get(0).getFreeLuggageUnit());
            luggageInfo.setOverloadLuggage(infos.get(0).getOverloadLuggage());
            luggageInfo.setRemark(infos.get(0).getSeatClassRemark());
            return luggageInfo;
        } catch (Exception e) {
            LOGGER.error("查询行李额信息异常, {}", e.getMessage(), e);
            return null;
        }
    }

    public boolean checkBan(Integer vendorId, Integer systemId, Segment segment) {
        BanQueryRequest banQueryRequest = new BanQueryRequest();
        banQueryRequest.setSolutionId(vendorId.toString());
        banQueryRequest.setSystemId(systemId.toString());
        banQueryRequest.setAirlineCompany(segment.getFlightNo());
        banQueryRequest.setDepartureDate(segment.getDepartureDate());
        banQueryRequest.setOrgAirportCode(segment.getOrgAirportIataCode());
        banQueryRequest.setDstAirportCode(segment.getDstAirportIataCode());
        banQueryRequest.setCabin(segment.getSeatCode());
        try {
            BanQueryResponse banQueryResponse = tspFltInterface.checkBan(banQueryRequest);
            return banQueryResponse.isBanned();
        } catch (Exception e) {
            LOGGER.error("获取航班屏蔽信息异常:{}", e.getMessage(), e);
            return false;
        }
    }

}
