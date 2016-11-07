package com.tuniu.abt.service.book.travelsky;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.dto.res.FlightIndivVendorPrice;
import com.tuniu.abt.service.aop.AopPolicyInquiryService;
import com.tuniu.abt.service.book.BookBaseService;
import com.tuniu.abt.service.book.BookCommonUtils;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.abt.service.res.ResourceService;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.*;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.adapter.flightTicket.domain.FlightIndivFlightPrice;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import com.tuniu.adapter.flightTicket.vo.policy.aop.FlightPolicy;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import com.tuniu.adapter.flightTicket.vo.policy.aop.Seat;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lanlugang on 16/5/2.
 */
@Component
public class BookTravelSkySupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookTravelSkySupport.class);

    private static final String TYPE_SEAT_CODE = "seatCode";

    private static final String TYPE_SEAT_CLASS = "seatClass";

    @Resource
    private TsConfig tsConfig;
    @Resource
    private TravelSkyInterface travelSkyInterface;
    @Resource
    private ResourceService resourceService;
    @Resource
    private BookBaseService bookBaseService;
    @Resource
    private AopPolicyInquiryService aopPolicyInquiryService;

    /**
     * 按余位运价等重新组装bookingRq
     * @param bookingData
     * @return
     */
    public Map<String, List<BookingData>> rebuildBookingData(BookingData bookingData) {
        Map<String, List<Passenger>> passengerListMap = BookCommonUtils.splitPassengersByType(bookingData.getPassengers());
        /** 获取余位 */
        List<AvSeatStatusGroup> seatStatusGroups = getAvSeatStatusGroups(bookingData);
        Map<String, List<BookingData>> bookingDataMap = new HashMap<String, List<BookingData>>();
        /** 分配成人座位 */
        List<Passenger> adtPsgList = passengerListMap.get(BizzConstants.PSG_TYPE_CODE_ADT);
        boolean isAdtSeatsEnough = isOrgSeatCodesEnough(seatStatusGroups, adtPsgList.size());
        List<AvSeatStatusGroup> adtSeatStatusGroups = null;
        if (!isAdtSeatsEnough) { // 座位数不足，则过滤出有效舱位并按价格排序
            filterInvalidSeatCode(bookingData.getSegments(), bookingData.getVendorId(), seatStatusGroups);
            if (!enoughSeats(seatStatusGroups, bookingData.getSegments().size(), adtPsgList.size())) {
                throw new BizzException(BookEx.TRAVELSKY_SEATS_NOT_ENOUGH, "成人座位数不足，请重新查询或选择其它航班舱位.");
            }
            adtSeatStatusGroups = seatStatusGroups;
        } else {
            adtSeatStatusGroups = new ArrayList<AvSeatStatusGroup>();
            adtSeatStatusGroups.addAll(seatStatusGroups);
            filterRemainSpecialSeats(adtSeatStatusGroups, TYPE_SEAT_CODE);
        }
        List<BookingData> adtBookingDatas = buildBookingDatas(bookingData, adtPsgList, adtSeatStatusGroups);
        List<Passenger> infPsgList = passengerListMap.get(BizzConstants.PSG_TYPE_CODE_INF);
        if (CollectionUtils.isNotEmpty(infPsgList)) {// 如果有婴儿，则成人要带上婴儿
            addInfPassengers(adtBookingDatas, infPsgList);
        }
        bookingDataMap.put(BizzConstants.PSG_TYPE_CODE_ADT, adtBookingDatas);
        /** 分配儿童座位 */
        List<Passenger> chdPsgList = passengerListMap.get(BizzConstants.PSG_TYPE_CODE_CHD);
        if (CollectionUtils.isNotEmpty(chdPsgList)) {
            filterInValidSeatCode4Chd(bookingData, seatStatusGroups);
            if (!enoughSeats(seatStatusGroups, bookingData.getSegments().size(), chdPsgList.size())) {
                throw new BizzException(BookEx.TRAVELSKY_SEATS_NOT_ENOUGH, "儿童座位数不足，请重新查询或选择其它航班舱位.");
            }
            List<BookingData> chdBookingDatas = buildBookingDatas(bookingData, chdPsgList, seatStatusGroups);
            bookingDataMap.put(BizzConstants.PSG_TYPE_CODE_CHD, chdBookingDatas);
        }
        return bookingDataMap;
    }

    /**
     * 过滤出指定舱位
     * @param seatStatusGroups
     * @param typeSeat
     */
    private void filterRemainSpecialSeats(List<AvSeatStatusGroup> seatStatusGroups, String typeSeat) {
        for (Iterator<AvSeatStatusGroup> iterator = seatStatusGroups.iterator(); iterator.hasNext();) {
            AvSeatStatusGroup seatStatusGroup = iterator.next();
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                if (TYPE_SEAT_CLASS.equals(typeSeat)
                        && !dto.getSeatCode().equals(dto.getSeatClass())) {
                    iterator.remove();
                    break;
                } else if (TYPE_SEAT_CODE.equals(typeSeat) && !dto.isOriginal()) {
                    iterator.remove();
                    break;
                }
            }
        }

    }

    /**
     * 过滤掉无效的舱位（无运价，非同一舱等的舱位），返回有效舱位按运价 从低到高排序
     * @param segments
     * @param vendorId
     * @param seatStatusGroups
     */
    private void filterInvalidSeatCode(List<Segment> segments, Integer vendorId, List<AvSeatStatusGroup> seatStatusGroups) {
        boolean refPriceIsEmpty = false;
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            if (seatStatusGroup.getRefPrice() == 0) {
                refPriceIsEmpty = true;
                break;
            }
        }

        if (refPriceIsEmpty) {
            // 查资源，过滤无运价、非同舱等的舱位
            filterInvalidSeatsByResource(segments, vendorId, seatStatusGroups);
            // 如果是开放平台, 则需要查询该航班的政策，并过滤无政策的舱位
            if (CollectionUtils.isNotEmpty(seatStatusGroups) && vendorId == BizzConstants.V_AOP) {
                filterInvalidSeatsByAopPolicy(segments, seatStatusGroups);
            }
        }

        if (CollectionUtils.isNotEmpty(seatStatusGroups)) {
            // 按价格排序
            Collections.sort(seatStatusGroups, (o1, o2) -> Double.compare(o1.getRefPrice(), o2.getRefPrice()));
        } else {
            throw new BizzException(BookEx.TRAVELSKY_SEATS_NOT_ENOUGH, "无可使用舱位，请重新查询或选择其它航班舱位.");
        }
    }

    /**
     * 查资源，过滤无运价、非同舱等的舱位
     * @param segments
     * @param vendorId
     * @param seatStatusGroups
     */
    private void filterInvalidSeatsByResource(List<Segment> segments, Integer vendorId, List<AvSeatStatusGroup> seatStatusGroups) {
        List<List<FlightIndivFlightPrice>> pricesList = new ArrayList<List<FlightIndivFlightPrice>>();
        for (Segment segment : segments) {
            List<FlightIndivFlightPrice> curSegPrices = resourceService.getIndivPriceInfo(segment, BizzConstants.PSG_TYPE_CODE_ADT, vendorId);
            if(CollectionUtils.isNotEmpty(curSegPrices)) {
                for (Iterator<FlightIndivFlightPrice> iterator = curSegPrices.iterator(); iterator.hasNext();) {
                    FlightIndivFlightPrice curSegPrice = iterator.next();
                    if (curSegPrice.getBaseFare() == 0) {// 过滤价格为0的资源
                        iterator.remove();
                    }
                }
                pricesList.add(curSegPrices);
            }
        }
        List<List<FlightIndivFlightPrice>> pricesGroups = splitListToVerticalList(pricesList);
        for(Iterator<AvSeatStatusGroup> iterator = seatStatusGroups.iterator(); iterator.hasNext();) {
            AvSeatStatusGroup seatStatusGroup = iterator.next();
            int segmentSize = seatStatusGroup.getAvSeatStatusDtos().size();
            String seatCodeKey = null;
            String seatClassKey = null;
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                seatCodeKey = (seatCodeKey == null) ? dto.getSeatCode() : seatCodeKey + "#" + dto.getSeatCode();
                seatClassKey = (seatClassKey == null) ? dto.getSeatClass() : seatClassKey + "#" + dto.getSeatClass();
            }
            boolean resBingoFlag = false;
            if (CollectionUtils.isNotEmpty(pricesGroups)) {
                for (List<FlightIndivFlightPrice> flightIndivFlightPrices : pricesGroups) {
                    if (segmentSize != flightIndivFlightPrices.size()) {
                        continue;
                    }
                    String resSeatClassKey = null;
                    String cabinKey = null;
                    double refPrice = 0;
                    for (int i = 0; i < flightIndivFlightPrices.size(); i++) {
                        FlightIndivFlightPrice price = flightIndivFlightPrices.get(i);
                        cabinKey = (cabinKey == null) ? price.getCabinKey() : cabinKey + "#" + price.getCabinKey();
                        resSeatClassKey = (resSeatClassKey == null) ? price.getSeatClass() : resSeatClassKey + "#" + price.getSeatClass();
                        refPrice += price.getBaseFare();
                        // 更新价格到余位信息
                        AvSeatStatusDto dto = seatStatusGroup.getAvSeatStatusDtos().get(i);
                        dto.setDiscount(price.getDiscount());
                        dto.setRefAdtPrice(price.getBaseFare());
                    }
                    if (seatCodeKey.equals(cabinKey) && seatClassKey.equals(resSeatClassKey)) {
                        seatStatusGroup.setRefPrice(refPrice);
                        resBingoFlag = true;
                    }
                }
            }
            if (!resBingoFlag && seatStatusGroup.getRefPrice() == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * 通过AOP政策过滤舱位
     * @param segments
     * @param seatStatusGroups
     */
    private void filterInvalidSeatsByAopPolicy(List<Segment> segments, List<AvSeatStatusGroup> seatStatusGroups) {
        int i = 0;
        for (Segment segment : segments) {
            List<Seat> seats = getSeatList(segment.getFlightNo(), seatStatusGroups);
            List<FlightPolicy> policyList = aopPolicyInquiryService.inquiryPolicyBySegment(segment, seats);
            if (CollectionUtils.isEmpty(policyList)) {
                List<AvSeatStatusGroup> tempGroups = new ArrayList<AvSeatStatusGroup>();
                tempGroups.addAll(seatStatusGroups);
                filterRemainSpecialSeats(tempGroups, TYPE_SEAT_CODE);
                filterRemainSpecialSeats(seatStatusGroups, TYPE_SEAT_CLASS);
                seatStatusGroups.addAll(tempGroups);
                return;
            }
            Map<String, PolicyDetail> policyMap =
                    aopPolicyInquiryService.getMinPricePolicyMap(policyList.get(0).getSeatPolicyList());
            updatePolicyId2AvSeatStatus(i, segment.getFlightNo(), policyMap, seatStatusGroups);
            i++;
        }
    }

    private void updatePolicyId2AvSeatStatus(int segmentIndex, String flightNo, Map<String, PolicyDetail> policyMap,
                                             List<AvSeatStatusGroup> seatStatusGroups) {
        for (Iterator<AvSeatStatusGroup> iterator = seatStatusGroups.iterator(); iterator.hasNext();) {
            AvSeatStatusGroup seatStatusGroup = iterator.next();
            Long orgPolicyId = seatStatusGroup.getPolicyId();
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                if (dto.getFlightNo().equals(flightNo)) {
                    PolicyDetail policy = policyMap.get(dto.getSeatCode());
                    if (null != policy) {
                        Long policyId = new Long(policy.getPolicyId());
                        if (null == orgPolicyId || orgPolicyId == 0 || segmentIndex == 0) {
                            seatStatusGroup.setPolicyId(policyId);
                            BookWorkSupport.getAopPolicyDetailList().add(policy);// 记录政策信息
                        } else if (orgPolicyId.longValue() != policyId.longValue()) {
                            iterator.remove();
                        }
                    } else {
                        iterator.remove();
                    }
                    break;
                }
            }
        }
    }

    private List<Seat> getSeatList(String flightNo, List<AvSeatStatusGroup> seatStatusGroups) {
        Map<String, Seat> seatMap = new HashMap<String, Seat>();
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                if (dto.getFlightNo().equals(flightNo)) {
                    if (!seatMap.containsKey(dto.getSeatCode())) {
                        Seat seat = new Seat();
                        seat.setSeatCode(dto.getSeatCode());
                        seat.setDiscount(Double.valueOf(dto.getDiscount()).floatValue());
                        seat.setFdPrice(Double.valueOf(dto.getRefAdtPrice()).intValue());
                        seatMap.put(dto.getSeatCode(), seat);
                    }
                }
            }
        }
        return (List<Seat>) seatMap.values();
    }

    /**
     * 过滤出儿童的有效舱位
     * @param bookingData
     * @param seatStatusGroups
     */
    private void filterInValidSeatCode4Chd(BookingData bookingData, List<AvSeatStatusGroup> seatStatusGroups) {
        int systemId = bookingData.getSystemId();
        int vendorId = bookingData.getVendorId();
        List<Segment> segments = bookingData.getSegments();
        boolean isChdUseAdtSeatCodeAllowed = tsConfig.isAllowed(TsConfig.CHD_USE_ADT_SEAT_CODE, systemId, vendorId);
        if (isChdUseAdtSeatCodeAllowed) {
            List<FlightIndivVendorPrice> prices = getPriceFromResource(segments, vendorId);
            // 成人价
            PnrFareItem adtFareItem = getFareItem(prices, segments, BizzConstants.PSG_TYPE_CODE_ADT);
            // 儿童Y舱价格
            PnrFareItem chdFullFareItem = getFareItem(prices, segments, BizzConstants.PSG_TYPE_CODE_CHD);
            updateRefPriceInSeatStatusGroups(seatStatusGroups, adtFareItem, chdFullFareItem);
            boolean fullClassFlag = isOrgSeatCodeEqualsSeatClass(segments);
            PnrFareItem chdFareItem = null;// 儿童特价
            int fareItemCount = 0;
            if (fullClassFlag) {
                chdFareItem = chdFullFareItem;
            } else {
                PataBySegRes pataBySegRes = getFareItemByPataSegmengs(segments, BizzConstants.PSG_TYPE_CODE_CHD);
                if (null != pataBySegRes) {
                    chdFareItem = pataBySegRes.getFareItem();
                    fareItemCount = pataBySegRes.getFareItems().size();
                }
            }
            boolean useSeatClassFirst = false;
            boolean chdCanUseAdtPrice = false;
            if (null == chdFareItem || null == chdFullFareItem
                    || chdFullFareItem.getFare() <= chdFareItem.getFare()) {
                useSeatClassFirst = true;
            }
            if (fareItemCount > 1) {
                chdCanUseAdtPrice = true;
            }
            filterInvalidSeatCode(segments, vendorId, seatStatusGroups);
            reSortAvSeatStatus(seatStatusGroups, adtFareItem, chdFullFareItem, segments.size(), useSeatClassFirst, chdCanUseAdtPrice);
        } else {
            filterRemainSpecialSeats(seatStatusGroups, TYPE_SEAT_CLASS);
        }
    }

    /**
     * 从资源库中获取成人舱位价格
     * @param segments
     * @param vendorId
     * @return
     */
    private List<FlightIndivVendorPrice> getPriceFromResource(List<Segment> segments, int vendorId) {
        try {
            return bookBaseService.getResPriceList(segments, vendorId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 更新成人价格到指定的资源
     * @param seatStatusGroups
     * @param adtFareItem
     * @param chdFullFareItem
     */
    private void updateRefPriceInSeatStatusGroups(List<AvSeatStatusGroup> seatStatusGroups, PnrFareItem adtFareItem,
                                                  PnrFareItem chdFullFareItem) {
        double adtPrice = adtFareItem.getFare();
        double chdFullPrice = chdFullFareItem.getFare();
        double adtFullPrice = chdFullPrice * 2;
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            boolean orgSeatCode = true;
            boolean isSeatClass = true;
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                orgSeatCode = orgSeatCode && dto.isOriginal();
                isSeatClass = isSeatClass && dto.getSeatCode().equals(dto.getSeatClass());
            }
            if (orgSeatCode) {
                seatStatusGroup.setRefPrice(adtPrice);
            } else if (isSeatClass) {
                seatStatusGroup.setRefPrice(adtFullPrice);
            }
        }
    }

    private boolean isOrgSeatCodeEqualsSeatClass(List<Segment> segments) {
        for (Segment segment : segments) {
            if (!segment.getSeatCode().equals(segment.getSeatClass())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param seatStatusGroups
     * @param adtFareItem
     * @param chdFullFareItem
     * @param segmentSize
     * @param useSeatClassFirst
     * @param chdCanUseAdtPrice
     */
    private void reSortAvSeatStatus(List<AvSeatStatusGroup> seatStatusGroups, PnrFareItem adtFareItem, PnrFareItem chdFullFareItem ,
                                    int segmentSize, boolean useSeatClassFirst, boolean chdCanUseAdtPrice) {
        double adtTaxs = adtFareItem.getAirPortTax() + adtFareItem.getFuelSurcharge();
        double chdTaxs = chdFullFareItem.getAirPortTax() + chdFullFareItem.getFuelSurcharge();
        double chdFullPrice = chdFullFareItem.getFare();
        double chdPricePerSeg = chdFullPrice/segmentSize;
        List<AvSeatStatusGroup> newSeatStatusGroups = new ArrayList<AvSeatStatusGroup>();
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            int seatClassNum = 0;
            boolean isSeatClassGroup = true;
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                boolean isSeatClass = dto.getSeatCode().equals(dto.getSeatClass());
                isSeatClassGroup = isSeatClassGroup && isSeatClass;
                if (isSeatClass) {
                    seatClassNum ++;
                }
            }
            // 更新价格为成人总价
            double curAdtPrice = seatStatusGroup.getRefPrice();
            seatStatusGroup.setRefPrice(curAdtPrice + adtTaxs);
            seatStatusGroup.setChdUseAdtPrice(true);
            // 新建儿童价对象
            AvSeatStatusGroup newSeatStatusGroup = new AvSeatStatusGroup();
            newSeatStatusGroup.setAvSeatStatusDtos(seatStatusGroup.getAvSeatStatusDtos());
            newSeatStatusGroup.setPolicyId(seatStatusGroup.getPolicyId());
            if (chdCanUseAdtPrice) {
                newSeatStatusGroup.setRefPrice(curAdtPrice - (seatClassNum * chdPricePerSeg) + chdTaxs);
            } else if (useSeatClassFirst) { // 儿童全价小于请求中舱位的儿童价
                newSeatStatusGroup.setRefPrice(chdFullPrice + chdTaxs);
            }
            if (isSeatClassGroup) {
                newSeatStatusGroup.setRefPrice(chdFullPrice + chdTaxs - 1);
            }
            newSeatStatusGroups.add(newSeatStatusGroup);
        }
        seatStatusGroups.addAll(newSeatStatusGroups);
        Collections.sort(seatStatusGroups, (o1, o2) -> Double.compare(o1.getRefPrice(), o2.getRefPrice()));
    }

    /**
     *
     * @param prices
     *        资源库中获取的价格
     * @param segments
     *        航段信息
     * @param psgType
     *        人员类型: ADT, CHD
     * @return
     */
    private PnrFareItem getFareItem(List<FlightIndivVendorPrice> prices, List<Segment> segments, String psgType) {
        FlightIndivVendorPrice validPrice = null;
        if (CollectionUtils.isNotEmpty(prices)) {
            for (FlightIndivVendorPrice price : prices) {
                if (psgType.equals(price.getPassengerCode())) {
                    validPrice = price;
                    break;
                }
            }
        }
        if (null != validPrice) {
            return convert2FareItem(validPrice);
        } else {
            List<Segment> tempSegments = new ArrayList<Segment>();
            if (BizzConstants.PSG_TYPE_CODE_CHD.equals(psgType)) {
                Segment tempSegment = null;
                for (Segment segment : segments) {
                    tempSegment = CommonUtils.deepCloneObject(segment);
                    tempSegment.setSeatCode(segment.getSeatClass());
                    tempSegments.add(tempSegment);
                }
            } else {
                tempSegments.addAll(segments);
            }
            return travelSkyInterface.airPriceBySegD(tempSegments, psgType).getFareItem();
        }
    }

    /**
     *
     * @param segments
     * @param psgType
     * @return
     */
    private PataBySegRes getFareItemByPataSegmengs(List<Segment> segments, String psgType) {
        try {
            return travelSkyInterface.airPriceBySegD(segments, psgType);
        } catch (Exception e) {
            LOGGER.error("按航段Pata运价，结果为空", e);
            return null;
        }
    }

    private PnrFareItem convert2FareItem(FlightIndivVendorPrice price) {
        PnrFareItem fareItem = new PnrFareItem();
        fareItem.setFare(price.getCost());
        fareItem.setAirPortTax(price.getTaxes());
        fareItem.setFuelSurcharge(price.getQcharge());
        fareItem.setTotal(price.getCost()+price.getTaxes()+price.getQcharge());
        return fareItem;
    }

    private void addInfPassengers(List<BookingData> adtRequests, List<Passenger> infPsgInfoList) {
        for (Passenger inf : infPsgInfoList) {
            for (BookingData request : adtRequests) {
                for (Passenger adt : request.getPassengers()) {
                    if (inf.getRefPersonId().longValue() == adt.getPersonId()) {
                        request.getPassengers().add(inf);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * 判断座位数是否充足
     * @param seatStatusGroups
     * @param segmentSize
     * @param psgNum
     * @return
     */
    private boolean enoughSeats(List<AvSeatStatusGroup> seatStatusGroups, int segmentSize, int psgNum) {
        List<Map<String, Integer>> segmentMaps = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> segmentMap = null;
        for (int i = 0; i < segmentSize; i++ ) {
            segmentMap = new HashMap<String, Integer>();
            segmentMaps.add(segmentMap);
        }
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            for (int i = 0; i < segmentSize; i++) {
                AvSeatStatusDto dto = seatStatusGroup.getAvSeatStatusDtos().get(i);
                if (!segmentMaps.get(i).containsKey(dto.getSeatCode())) {
                    segmentMaps.get(i).put(dto.getSeatCode(), dto.getSeatNum());
                }
            }
        }
        for (Map<String, Integer> map : segmentMaps) {
            int seatNumSum = 0;
            for (int seatNum : map.values()) {
                seatNumSum += seatNum;
            }
            if (psgNum > seatNumSum) {
                return false;
            }
        }
        return true;
    }

    private List<BookingData> buildBookingDatas(BookingData bookingData, List<Passenger> passengers,
                                                List<AvSeatStatusGroup> seatStatusGroups) {
        List<BookingData> bookingDatas = new ArrayList<BookingData>();
        Map<String, List<Passenger>> psgInfoMap = new HashMap<String, List<Passenger>>();
        List<Passenger> psgInfoList = null;
        for (Passenger passenger : passengers) {
            for (Iterator<AvSeatStatusGroup> iterator = seatStatusGroups.iterator(); iterator.hasNext();) {
                AvSeatStatusGroup seatStatusGroup = iterator.next();
                StringBuffer flightSeatCodeKey = new StringBuffer();
                boolean continueFlag = false;
                for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                    if (dto.getSeatNum() <= 0) {
                        iterator.remove();
                        continueFlag = true;
                        break;
                    }
                    // 过滤恶意占位屏蔽的舱位
                    for (Segment segment : bookingData.getSegments()) {
                        if (segment.getFlightNo().equals(dto.getFlightNo())
                                && !segment.getSeatCode().equals(dto.getSeatCode())) {
                            Segment cloneSegment = CommonUtils.deepCloneObject(segment);
                            cloneSegment.setSeatCode(dto.getSeatCode());
                            if(bookBaseService.checkBan(bookingData.getVendorId(), bookingData.getSystemId(), cloneSegment)) {
                                iterator.remove();
                                continueFlag = true;
                                break;
                            }
                        }
                    }
                    flightSeatCodeKey.append(dto.getFlightNo()).append("-").append(dto.getSeatCode()).append("-");
                }
                flightSeatCodeKey.append(seatStatusGroup.isChdUseAdtPrice()).append("-")
                                 .append(seatStatusGroup.getPolicyId());
                if (continueFlag) {
                    continue;
                } else {
                    String key = flightSeatCodeKey.toString();
                    if (psgInfoMap.containsKey(key)) {
                        psgInfoMap.get(key).add(passenger);
                    } else {
                        psgInfoList = new ArrayList<Passenger>();
                        psgInfoList.add(passenger);
                        psgInfoMap.put(key, psgInfoList);
                    }
                    // 更新余位
                    for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                        dto.setSeatNum(dto.getSeatNum()-1);
                    }
                    break;
                }
            }
        }
        BookingData bookingRequestCopy = null;
        for (Iterator<String> iterator = psgInfoMap.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            String[] arr = key.split("-");
            Map<String, String> flightSeatCodeMap = new HashMap<String, String>();
            int length = arr.length;
            for (int i = 0; i < length-2; i+=2) {
                flightSeatCodeMap.put(arr[i], arr[i+1]);
            }
            bookingRequestCopy = CommonUtils.deepCloneObject(bookingData);
            bookingRequestCopy.setChdUseAdtPrice(Boolean.valueOf(arr[length-2]));
            bookingRequestCopy.setPolicyId(Long.valueOf(arr[length-1]));
            // 更新舱位信息
            for (Segment segment : bookingRequestCopy.getSegments()) {
                String seatCode = flightSeatCodeMap.get(segment.getFlightNo());
                segment.setSeatCode(seatCode);
            }
            // 更新人员信息
            bookingRequestCopy.getPassengers().clear();
            bookingRequestCopy.getPassengers().addAll(psgInfoMap.get(key));
            bookingDatas.add(bookingRequestCopy);
        }
        return bookingDatas;
    }

    /**
     * 原舱位数是否充足
     * @param seatStatusGroups
     * @param psgNum
     * @return
     */
    private boolean isOrgSeatCodesEnough(List<AvSeatStatusGroup> seatStatusGroups, int psgNum) {
        AvSeatStatusGroup orgSeatStatusGroup = null; // 舱位
        for (AvSeatStatusGroup seatStatusGroup : seatStatusGroups) {
            boolean orgFlag = true;
            for (AvSeatStatusDto dto : seatStatusGroup.getAvSeatStatusDtos()) {
                orgFlag = orgFlag && dto.isOriginal();
            }
            if (orgFlag) {
                orgSeatStatusGroup = seatStatusGroup;
                break;
            }
        }
        if (null != orgSeatStatusGroup) {
            for (AvSeatStatusDto dto : orgSeatStatusGroup.getAvSeatStatusDtos()) {
                if (psgNum > dto.getSeatNum()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按航段获取打包的获取余位数据
     * @param bookingData
     * @return
     */
    private List<AvSeatStatusGroup> getAvSeatStatusGroups(BookingData bookingData) {
        List<List<AvSeatStatusDto>> seatStatusList = null;
        // 根据systemId,vendorId 获取配置开关: 余位不足是否支持使用同舱等下的其他舱位替代
        if (tsConfig.isAllowed(TsConfig.REPLACE_SEAT_CODE, bookingData.getSystemId(), bookingData.getVendorId())) {
            seatStatusList = querySeatStatusByAV(bookingData.getSegments(), bookingData.getSystemId());
        } else {
            seatStatusList = new ArrayList<List<AvSeatStatusDto>>();
            List<AvSeatStatusDto> avSeatStatusList = null;
            AvSeatStatusDto avSeatStatus = null;
            for (Segment segment : bookingData.getSegments()) {
                avSeatStatusList = new ArrayList<AvSeatStatusDto>();
                avSeatStatus = CommonUtils.transform(segment, AvSeatStatusDto.class);
                avSeatStatus.setSeatNum(TsConfig.MAX_PSG_NUM_LIMIT_DEFALUT);
                avSeatStatus.setOriginal(true);
                avSeatStatusList.add(avSeatStatus);
                if (!segment.getSeatCode().equals(segment.getSeatClass())) {
                    avSeatStatus = CommonUtils.transform(segment, AvSeatStatusDto.class);
                    avSeatStatus.setSeatCode(segment.getSeatClass());
                    avSeatStatus.setSeatNum(TsConfig.MAX_PSG_NUM_LIMIT_DEFALUT);
                    avSeatStatusList.add(avSeatStatus);
                }
                seatStatusList.add(avSeatStatusList);
            }
        }
        List<AvSeatStatusGroup> groups = convert2SeatStatusGroups(seatStatusList);
        if (null != bookingData.getPolicyId() && bookingData.getPolicyId() != 0) {
            fillUpPolicyId(bookingData.getPolicyId(), bookingData.getSegments(), groups);
        }
        return groups;
    }

    private void fillUpPolicyId(Long policyId, List<Segment> segments, List<AvSeatStatusGroup> groups) {
        String seatCodeKey = null;
        String seatClassKey = null;
        for (Segment segment : segments) {
            seatCodeKey = (seatCodeKey == null) ? segment.getSeatCode() : seatCodeKey + "-" + segment.getSeatCode();
            seatClassKey = (seatClassKey == null) ? segment.getSeatClass() : seatClassKey + "-" + segment.getSeatClass();
        }
        for (AvSeatStatusGroup group : groups) {
            String curSeatCode = null;
            String curSeatClass = null;
            for (AvSeatStatusDto dto : group.getAvSeatStatusDtos()) {
                curSeatCode = (curSeatCode == null) ? dto.getSeatCode() : curSeatCode + "-" + dto.getSeatCode();
                curSeatClass = (curSeatClass == null) ? dto.getSeatClass() : curSeatClass + "-" + dto.getSeatClass();
            }
            if (curSeatCode.equals(seatCodeKey)) {
                group.setPolicyId(policyId);
            }
            if (curSeatClass.equals(seatClassKey)) {
                group.setPolicyId(policyId);
            }
        }
    }

    /**
     *
     * Description: 查询余位<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param segments
     * @param systemId
     * @return <br>
     */
    private List<List<AvSeatStatusDto>> querySeatStatusByAV(List<Segment> segments, int systemId){
        List<List<AvSeatStatusDto>> resultList = new ArrayList<List<AvSeatStatusDto>>();
        AirAvailResult airAvailResult = null;
        for (Segment segment : segments) {
            AirAvailRequest request = new AirAvailRequest();
            request.setAirline(segment.getAirlineCompanyIataCode());
            request.setOrgCityIataCode(segment.getOrgCityIataCode());
            request.setDstCityIataCode(segment.getDstCityIataCode());
            request.setDepartureDate(segment.getDepartureDate());
            request.setDirect(true);
            request.setEticket(true);
            airAvailResult = travelSkyInterface.airAvail(request, systemId);
            for (AvOrgDstOption orgDstOption : airAvailResult.getOrgDstOptions()) {
                // 过滤中转航班
                if (orgDstOption.getSegments().size() > 1) {
                    continue;
                }
                AvFlightSegment avSegment = orgDstOption.getSegments().get(0);
                if (segment.getFlightNo().equals(avSegment.getFlightNo())) {
                    List<AvSeatStatusDto> avSeatStatusDtos = new ArrayList<AvSeatStatusDto>();
                    for (AvSeatStatus avSeatStatus : avSegment.getSeatStatus()) {
                        AvSeatStatusDto dto = CommonUtils.transform(avSeatStatus, AvSeatStatusDto.class);
                        dto.setSeatClass(segment.getSeatClass());
                        dto.setFlightNo(segment.getFlightNo());
                        dto.setSeatNum(convert2SeatNum(avSeatStatus.getStatus()));
                        if (segment.getSeatCode().equals(avSeatStatus.getSeatCode())) {
                            dto.setOriginal(true);
                        }
                        avSeatStatusDtos.add(dto);
                    }
                    resultList.add(avSeatStatusDtos);
                    break;
                }
            }
        }
        return resultList;
    }

    private  List<AvSeatStatusGroup> convert2SeatStatusGroups(List<List<AvSeatStatusDto>> seatStatusList) {
        List<List<AvSeatStatusDto>> seatStatusGroups = splitListToVerticalList(seatStatusList);
        List<AvSeatStatusGroup> avSeatStatusGroups = new ArrayList<AvSeatStatusGroup>();
        AvSeatStatusGroup avSeatStatusGroup = null;
        for (List<AvSeatStatusDto> seatStatusGroup : seatStatusGroups) {
            avSeatStatusGroup = new AvSeatStatusGroup();
            avSeatStatusGroup.getAvSeatStatusDtos().addAll(seatStatusGroup);
            avSeatStatusGroups.add(avSeatStatusGroup);
        }
        return avSeatStatusGroups;
    }

    private int convert2SeatNum(String seatStatus) {
        int seatNum = 0;
        if (null != seatStatus && seatStatus.matches("[1-9]")) {
            seatNum = Integer.parseInt(seatStatus);
        } else if (null != seatStatus
                && (seatStatus.equals(">9") || seatStatus.equals("A"))) {
            seatNum = TsConfig.MAX_PSG_NUM_LIMIT_DEFALUT;
        }
        return seatNum;
    }

    /**
     * 将一个数组进行垂直分组：
     *   eg: src -- > [{A,B},{C,D}]
     *       dst -- > [{A,C},{A,D},{B,C},{B,D}]
     * @return
     */
    public static <T> List<List<T>> splitListToVerticalList(List<List<T>> src) {
        List<List<T>> groups = null;
        for(List<T> list : src) {
            if (null == groups) {
                groups = new ArrayList<List<T>>();
                List<T> innerList = null;
                for (T t : list) {
                    innerList = new ArrayList<T>();
                    innerList.add(t);
                    groups.add(innerList);
                }
            } else {
                splitGroup(groups, list);
            }
        }
        return groups;
    }

    private static  <T> void splitGroup(List<List<T>> groups, List<T> list) {
        List<List<T>> tempGroups = new ArrayList<List<T>>();
        List<T> tempList = null;
        for (List<T> group : groups) {
            for (T t : list) {
                tempList = new ArrayList<T>();
                tempList.addAll(group);
                tempList.add(t);
                tempGroups.add(tempList);
            }
        }
        groups.clear();
        groups.addAll(tempGroups);
    }

}
