package com.tuniu.abt.service.book.travelsky;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.book.BookCommonUtils;
import com.tuniu.abt.service.book.async.PatByPnrStoreAsyncTask;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.PatByPnrReq;
import com.tuniu.abt.service.travelsky.dto.PatByPnrRes;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import com.tuniu.operation.platform.base.time.DateUtils;
import com.tuniu.vla.base.exception.BaseException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by lanlugang on 2016/5/10.
 */
@Service
public class BookTravelSkyWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(BookTravelSkyWrapper.class);

    @Resource
    private BookTravelSkySupport bookTravelSkySupport;
    @Resource
    private TravelSkyInterface travelSkyInterface;
    @Resource
    private SystemConfig systemConfig;
    @Resource
    private TsConfig tsConfig;
    @Resource
    private PatByPnrStoreAsyncTask patByPnrStoreAsyncTask;

    private static List<String> actionCodeOK = new ArrayList<String>();
    static  {
        actionCodeOK.add("DK");
        actionCodeOK.add("HK");
        actionCodeOK.add("TK");
        actionCodeOK.add("KK");
        actionCodeOK.add("KL");
    }


    /**
     *
     * Description: 生成和校验PNR<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param bookingData
     * @return
     * @throws Exception <br>
     */
    public List<PnrInfo> creatAndCheckPnrs(BookingData bookingData) throws Exception {
        /**如果乘客姓名包含生僻字，则进行生僻字处理 */
        BookCommonUtils.rarelyCNCharHandler(bookingData.getPassengers());
        /**按每组占位最多9人进行分组(婴儿不计人数) */
        List<List<Passenger>> psgInfosList = BookCommonUtils.splitPassengersByMaxPsgNumLimit(bookingData.getPassengers(),
                TsConfig.MAX_PSG_NUM_LIMIT_DEFALUT);
        List<BookingData> bookingDatas = BookCommonUtils.splitBookingDataByPsgsList(bookingData, psgInfosList);
        /**占位，成人pnr */
        List<PnrInfo> pnrInfoList = new ArrayList<PnrInfo>();
        bookingDatas.parallelStream().forEach(request -> pnrInfoList.addAll(innerCreatPnr(request)));
        /**校验PNR的有效性 */
        checkPnrIsValid(pnrInfoList, bookingData.getSystemId());
        return pnrInfoList;
    }

    private List<PnrInfo> innerCreatPnr(BookingData bookingRequest) {
        // 查询余位
        Map<String, List<BookingData>> bookingRequestsMap = bookTravelSkySupport.rebuildBookingData(bookingRequest);
        // 预定PNR
        List<PnrInfo> pnrInfos = airBookingPnrByPsgType(bookingRequestsMap);
        return pnrInfos;
    }

    /**
     *
     * Description: 预定生成Pnr<br>
     *
     * @author lanlugang<br>
     * @since 2016-2-3<br>
     * @taskId AIR-4506<br>
     * @param bookingRequestsMap
     * @return <br>
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private List<PnrInfo> airBookingPnrByPsgType(Map<String, List<BookingData>> bookingRequestsMap) {
        List<BookingData> adtBookingRequests =  bookingRequestsMap.get("ADT");
        List<BookingData> chdBookingRequests =  bookingRequestsMap.get("CHD");
        List<PnrInfo> pnrInfos = new ArrayList<PnrInfo>();
        for (BookingData adtRequest : adtBookingRequests) {
            airBookPnr(pnrInfos, adtRequest);
        }
        if (CollectionUtils.isNotEmpty(chdBookingRequests)) {
            PnrExternalInfo pnrExternalInfo = getAdultPnrInfo(pnrInfos.get(0));
            for (BookingData chdRequest : chdBookingRequests) {
                chdRequest.setPnrExternalInfo(pnrExternalInfo);
                airBookPnr(pnrInfos, chdRequest);
            }
        }
        return pnrInfos;
    }

    private void checkPnrIsValid(List<PnrInfo> pnrInfoList, int systemId) throws Exception {
        boolean isValid = true;
        String msg = "";
        OTAAirResRetRS.AirResRet airResRet = null;
        for (PnrInfo pnrInfo : pnrInfoList) {
            boolean reTryFlag = true;
            int reTryCount = 0;
            while (reTryFlag) {
                if (reTryCount++ >= systemConfig.getRtRetryCount()) {
                    isValid = false;
                    msg = "".equals(msg) ? "RT失败" : msg;
                    break;
                }
                airResRet = travelSkyInterface.rt(pnrInfo.getPnrNo(), systemId);
                // 校验是否重名
                if (isDuplicateNamePnr(airResRet.getSpecialRemark())) {
                    isValid = false;
                    msg = "不能在同一PNR里为两个（含）以上名字同音的乘客预定";
                    break;
                }
                // 校验航段数
                if (isSegmentsUC(pnrInfo.getSegments(), airResRet.getFlightSegments())) {
                    isValid = false;
                    msg = "部分航班已不接受订座，请选择其它航班重新占位(部分航班被航司UC)";
                    break;
                }
                // 校验actioncode
                if (!isInfActionCodeValid(airResRet.getSpecialServiceRequest())) {
                    isValid = false;
                    msg = "婴儿的状态不为K,婴儿占位无效";
                    break;
                }
                if (!isActionCodeListValid(airResRet.getFlightSegments())) {
                    msg = "请重新查询舱位余位或选择其它舱位(或航班)重新占位。经过RT验证，该PNR并未实际完成占座。";
                    Thread.sleep(systemConfig.getRtWaitTime()); // 等几秒钟重试
                    continue;
                }
                // 获取航司大编码
                pnrInfo.setAirCompanyCode(getAirCompanyPnrCode(airResRet.getSpecialRemark()));
                // 更新清位时间
                String ibeClearTime = generateIbeClearTime(pnrInfo, airResRet.getSpecialServiceRequest());
                pnrInfo.setClearTime(ibeClearTime);
                if (isValid) {
                    break;
                }
            }
            // 校验未通过则取消所有pnr
            if (!isValid) {
                cancelPnrs(pnrInfoList);
                throw new BizzException(BookEx.TRAVELSKY_RT_PNR_ERROR, msg);
            }
        }
    }

    /**
     * 验证婴儿的行动代码是否有效
     * @param specialServiceRequest
     * @return
     */
    private boolean isInfActionCodeValid(List<SpecialServiceRequest> specialServiceRequest) {
        for (SpecialServiceRequest ssr : specialServiceRequest) {
            if ("INFT".equals(ssr.getSSRCode())
                    && !actionCodeOK.contains(ssr.getStatus())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Description: 获取航司大编码<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param rmks
     * @return <br>
     */
    private String getAirCompanyPnrCode(List<SpecialRemark> rmks) {
        String airCompanyPnrCode = "";
        for (SpecialRemark rmk : rmks) {
            String txt = rmk.getText();
            if (StringUtils.isNotEmpty(txt) && txt.startsWith("CA/")) {
                airCompanyPnrCode = txt.substring(3);
            }
        }
        return airCompanyPnrCode;
    }

    /**
     *
     * Description: 检查有没有航段被UC(取消)<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param segments
     * @param airSegs <br>
     */
    private boolean isSegmentsUC(List<Segment> segments, List<FlightSegments2> airSegs) {
        boolean ucFlag = false;
        List<String> flightNos = new ArrayList<String>();
        for (FlightSegments2 airSeg : airSegs) {
            for (FlightSegment2 segment : airSeg.getFlightSegment()) {
                if (StringUtils.isNotBlank(segment.getFlightNumber())) {
                    flightNos.add(segment.getMarketingAirline().getCode()
                            +segment.getFlightNumber());
                }
            }
        }
        for (Segment segment : segments) {
            if (!flightNos.contains(segment.getFlightNo())) {
                ucFlag = true;
            }
        }
        return ucFlag;
    }

    /**
     *
     * Description: pnr中是否有旅客重名<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param rmks
     * @return <br>
     */
    private boolean isDuplicateNamePnr(List<SpecialRemark> rmks) {
        boolean flag=false;
        String sameEnglishName="DUPLICATE NAME IN THIS PNR";
        if(null!=rmks&&rmks.size()>0) {
            for (SpecialRemark rmk : rmks) {
                if (rmk.getText() != null) {
                    String remarkTmp = rmk.getText().replaceAll(" ", "");
                    String sameName = sameEnglishName.replaceAll(" ", "");
                    if(remarkTmp.contains(sameName)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 验证pnr的状态码是否都是占位成功
     *
     * @param airSegs
     * @return true 都是占位成功状态 <br/>
     * false 含有占位不成功状态
     * @author tangchuandong
     * @since 2015-03-13
     */
    private boolean isActionCodeListValid(List<FlightSegments2> airSegs) {
        for (FlightSegments2 airSeg : airSegs) {
            for (FlightSegment2 segment : airSeg.getFlightSegment()) {
                if (!actionCodeOK.contains(segment.getStatus())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 生成PRN对应的清位时间
     * 1.如果IBE清位时间小于配置清位时间，并且IBE清位时间比当前时间大30分，取IBE清位时间
     * 2.如果IBE清位时间小于配置清位时间，并且IBE清位时间小或等当前时间+30分，且配置清位时间大于等于当前时间+58分，取当前时间+58分
     * 3.如果IBE清位时间小于配置清位时间，并且IBE清位时间小或等当前时间+30分，且配置清位时间小于当前时间+58分，取IBE清位时间
     * 4.其他取配置清位时间 考虑 占位时间与当前时间有时间差，所以设置为58分
     *
     * @param pnrInfo
     * @param specialServiceRequests
     * @throws ParseException
     * @since 2014-11-28
     */
    private String generateIbeClearTime(PnrInfo pnrInfo, List<SpecialServiceRequest> specialServiceRequests) throws ParseException {
        Segment segment = pnrInfo.getSegments().get(0);
        String clearTime = getConfigClearTime(segment.getDepartureDate(), segment.getDepartureTime());
        Date clearTimeDate = DateTimeUtils.convertDate(clearTime, DateTimeUtils.DATETIME_PATTERN);
        String ibeClearTime = "";
        for (SpecialServiceRequest ssr : specialServiceRequests) {
            if ("ADTK".equals(ssr.getSSRCode())) {
                String[] adtk = ssr.getText().trim().replaceAll("\\s{1,}", " ").split(" ");
                ibeClearTime = DateTimeUtils.convertStrDate(adtk[3].substring(3, 15));
            }

        }
        Date ibeClearDate = DateTimeUtils.convertDate(ibeClearTime, DateTimeUtils.DATETIME_PATTERN);
        if (null == ibeClearDate) {
            return clearTime;
        }
        Date currentTime = new Date();
        Date thirtyMins = DateUtils.addMinutes(currentTime, 30);
        Date sixtyMins = DateUtils.addMinutes(currentTime, 58);
        // IBE清位时间小于配置清位时间
        if (ibeClearDate.compareTo(clearTimeDate) < 0) {
            if (ibeClearDate.compareTo(thirtyMins) > 0
                    || clearTimeDate.compareTo(sixtyMins) < 0) {
                clearTime = ibeClearTime;
            } else {
                clearTime = DateTimeUtils.formatDatetime(sixtyMins);
            }
        }
        return  clearTime;
    }

    /**
     *
     * Description: 获取配置清位时间<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param departureDate
     * @param departureTime
     * @return
     * @throws ParseException
     */
    private String getConfigClearTime(String departureDate, String departureTime) throws ParseException {
        Date departure = DateTimeUtils.parse(departureDate + " "
                + departureTime + ":00", DateTimeUtils.DATETIME_PATTERN);
        Date date = new Date();
        Date addMinutes = DateTimeUtils.addMinutes(date, systemConfig.getTicketLimitMm());
        Date addHours = DateTimeUtils.addHours(addMinutes, systemConfig.getTicketLimitHour());
        Date addDays = DateTimeUtils.addDays(addHours, systemConfig.getTicketLimitDay());
        Date clearTime = null;
        if (departure.compareTo(addDays) > 0) {
            clearTime = addDays;
        } else if (departure.compareTo(addHours) > 0
                && departure.compareTo(addDays) <= 0) {
            clearTime = addHours;
        } else if (departure.compareTo(addMinutes) > 0
                && departure.compareTo(addHours) <= 0) {
            clearTime = addMinutes;
        } else {
            clearTime = departure;
        }
        return DateTimeUtils.dateConvert(clearTime, DateTimeUtils.DATETIME_PATTERN);
    }

    /**
     * 调用航信接口生成pnr
     * @param pnrInfos
     * @param bookingData
     */
    private void airBookPnr(List<PnrInfo> pnrInfos, BookingData bookingData) {
        int occupyType = BizzConstants.PERSON_TYPE_ADULT;
        boolean infPsgFlag = false;
        for (Passenger psgInfo : bookingData.getPassengers()) {
            String psgType = psgInfo.getPassengerTypeCode();
            if (BizzConstants.PSG_TYPE_CODE_CHD.equals(psgType)) {
                occupyType = BizzConstants.PERSON_TYPE_CHILD;
            } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(psgType)) {
                infPsgFlag = true;
            }
        }
        bookingData.setOccupyType(occupyType);
        bookingData.setHasBaby(infPsgFlag);
        Long policyId = bookingData.getPolicyId();
        if (null != policyId && policyId != 0
                && CollectionUtils.isNotEmpty(BookWorkSupport.getAopPolicyDetailList())) {
            for (PolicyDetail policyDetail : BookWorkSupport.getAopPolicyDetailList()) {
                if (policyId.equals(new Long(policyDetail.getPolicyId()))) {
                    bookingData.setOccupyOfficeNo(policyDetail.getVendorOffice());
                    bookingData.setVendorOfficeNo(policyDetail.getVendorOffice());
                    bookingData.setEtdzOfficeNo(policyDetail.getEtdzOffice());
                    break;
                }
            }
        }
        // 按单pnr人数限制、重名处理进行拆分
        String airCompanyCode = bookingData.getSegments().get(0).getAirlineCompanyIataCode();
        int limit = tsConfig.getMaxPsgNumLimitInPnr(airCompanyCode);
        List<List<Passenger>> psgInfosList = BookCommonUtils.classifyPsgInfosByType(bookingData.getPassengers(), limit);
        List<BookingData> bookingDatas = BookCommonUtils.splitBookingDataByPsgsList(bookingData, psgInfosList);
        try {
            bookingDatas.forEach(bookingRequest -> pnrInfos.add(innerAirBookPnr(bookingRequest)));
        } catch (BizzException bizzEx) {
            cancelPnrs(pnrInfos);
            throw bizzEx;
        } catch (Exception e) {
            cancelPnrs(pnrInfos);
            LOG.error("订单ID：" + bookingData.getOrderId() + "生成PNR失败", e);
            throw new BizzException(BookEx.TRAVELSKY_CREAT_PNR_ERROR, e.getMessage(), e);
        }
    }

    private PnrInfo innerAirBookPnr(BookingData bookingData) {
        try {
            return travelSkyInterface.airBookPnr(bookingData);
        } catch (BizzException e) {
            if ((e.getCode() == BizzEx.IBE_PLUS_INTF_RESULT_ERROR
                    || e.getCode() == BizzEx.IBE_INTF_EX)) {
                String errorMsg = BookCommonUtils.convertToResMsg(e.getMessage());
                if (!systemConfig.getIbeplusOfficeNo().equals(bookingData.getOccupyOfficeNo())
                        && (errorMsg.equals("指定的航班在指定的日期不执行或指定的舱位已经无法订取")
                        || errorMsg.equals("OFFICE_NOT_PERMITTED"))) {
                    bookingData.setOccupyOfficeNo(systemConfig.getIbeplusOfficeNo());
                    return innerAirBookPnr(bookingData);
                }
                int errorCode = e.getCode();
                if (errorMsg.contains("失信被执行人")) {
                    errorCode = BookEx.PASSENGER_BREAK_EXECUTION_BANNED;
                } else if (errorMsg.contains("指定的舱位已经无法订取")) {
                    errorCode = BookEx.TRAVELSKY_SEATS_NOT_ENOUGH;
                } else if (errorMsg.contains("证件号码错误")) {
                    errorCode = BookEx.INVALID_ID_NUMBER;
                } else if (errorMsg.contains("网络连接异常")
                        || errorCode == BizzEx.IBE_INTF_HTTP_CODE_EX
                        || errorCode == BizzEx.IBE_PLUS_INTF_HTTP_CODE_EX) {
                    errorCode = BookEx.VENDOR_INTF_CONNECTOR_EX;
                }
                throw new BizzException(errorCode, errorMsg, e);
            }
            throw e;
        }
    }

    private void cancelPnrs(List<PnrInfo> pnrInfos) {
        for (PnrInfo pnrInfo : pnrInfos) {
            travelSkyInterface.cancelPnr(pnrInfo.getPnrNo(), pnrInfo.getOfficeNo());
        }
    }

    /**
     * Description: 组装成人pnr及舱位信息用于生成儿童pnr中备注成人pnr<br>
     *
     * @param pnrInfo
     * @return <br>
     * @author lanlugang<br>
     * @taskId AIR-665<br>
     */
    private PnrExternalInfo getAdultPnrInfo(PnrInfo pnrInfo) {
        PnrExternalInfo ibePnrExternalInfo = new PnrExternalInfo();
        String adtPnr = pnrInfo.getPnrNo();
        List<Segment> flightSegments = pnrInfo.getSegments();
        String seatCode = "";
        for (Segment flightSegment : flightSegments) {
            seatCode = seatCode.equals("") ? flightSegment.getSeatCode()
                    : seatCode + "#" + flightSegment.getSeatCode();
        }
        ibePnrExternalInfo.setAdultPnr(adtPnr);
        ibePnrExternalInfo.setAdultSeatCode(seatCode);
        return ibePnrExternalInfo;
    }

    public void doPataPriceByPnr(List<PnrInfo> pnrInfos, int systemId, int vendorId) {
        boolean useFdPriceFlag = tsConfig.isAllowed(TsConfig.DO_PATA_USE_FD_PRICE, systemId, vendorId);
        boolean doPataAfterCreatPnr = tsConfig.isAllowed(TsConfig.DO_PATA_AFTER_CREAT_PNR, systemId, vendorId);
        try {
            List<PatByPnrRes> patByPnrResList = new ArrayList<PatByPnrRes>();
            for (PnrInfo pnrInfo : pnrInfos) {
                PatByPnrReq patByPnrReq = new PatByPnrReq();
                patByPnrReq.setPnrNo(pnrInfo.getPnrNo());
                patByPnrReq.setHasBaby(pnrInfo.isHasBaby());
                if (pnrInfo.getPatType().equals(BizzConstants.PSG_TYPE_CODE_ADT)) {
                    patByPnrReq.setPsgType(PatByPnrReq.PSG_TYPE_ADT);
                } else if (pnrInfo.getPatType().equals(BizzConstants.PSG_TYPE_CODE_CHD)) {
                    patByPnrReq.setPsgType(PatByPnrReq.PSG_TYPE_CHD);
                }
                PatByPnrRes patByPnrRes = null;
                if(useFdPriceFlag) {
                    patByPnrReq.setFareType(PatByPnrReq.FARE_TYPE_FD);
                }
                patByPnrRes = travelSkyInterface.patPriceByPnrCalculate(patByPnrReq, systemId);
                // 价格更新到pnrInfo中返回
                setPnrDataPrice(pnrInfo, patByPnrRes);
                patByPnrResList.add(patByPnrRes);
            }
            // 占位后是否把运价项写入pnr
            if (doPataAfterCreatPnr) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("requests", patByPnrResList);
                param.put("systemId", systemId);
                patByPnrStoreAsyncTask.execute(param);
            }
        } catch (Exception e) {
            cancelPnrs(pnrInfos);
            throw new BizzException(BookEx.TRAVELSKY_PAT_RESULT_NONE, "占位失败,按pnr计算存储运价失败", e);
        }
    }

    /**
     *
     * Description: 将价格更新到返回的Pnr数据中<br>
     *
     * @author lanlugang<br>
     * @since 2016-2-3<br>
     * @taskId AIR-4506<br>
     * @param pnrData
     * @param patByPnrRes <br>
     */
    private void setPnrDataPrice(PnrInfo pnrData, PatByPnrRes patByPnrRes) {
        PnrFareItem pnrFareItem = patByPnrRes.getFareItem();
        // 儿童
        if (pnrData.getOccupyType() == BizzConstants.PERSON_TYPE_CHILD) {
            PnrPrice childPrice = new PnrPrice();
            childPrice.setPassengerType(BizzConstants.PERSON_TYPE_CHILD);
            childPrice.setPriceType(BizzConstants.PERSON_TYPE_CHILD);
            if (pnrData.getPatType().equals(BizzConstants.PSG_TYPE_CODE_ADT)) {
                childPrice.setPriceType(BizzConstants.PERSON_TYPE_ADULT);
            }
            childPrice.setSalePrice((float)pnrFareItem.getFare());
            childPrice.setOrgPrice((float)pnrFareItem.getFare());
            childPrice.setFloorPrice((float)pnrFareItem.getFare());
            childPrice.setFuelSurcharge((float)pnrFareItem.getFuelSurcharge());
            childPrice.setAirportTax((float)pnrFareItem.getAirPortTax());
            pnrData.getPrices().add(childPrice);
        } else { // 成人
            PnrPrice adultPrice = new PnrPrice();
            adultPrice.setPassengerType(BizzConstants.PERSON_TYPE_ADULT);
            adultPrice.setPriceType(BizzConstants.PERSON_TYPE_ADULT);
            adultPrice.setSalePrice((float)pnrFareItem.getFare());
            adultPrice.setOrgPrice((float)pnrFareItem.getFare());
            adultPrice.setFloorPrice((float)pnrFareItem.getFare());
            adultPrice.setFuelSurcharge((float)pnrFareItem.getFuelSurcharge());
            adultPrice.setAirportTax((float)pnrFareItem.getAirPortTax());
            pnrData.getPrices().add(adultPrice);
            if(pnrData.isHasBaby()) {
                PnrPrice babyPrice = new PnrPrice();
                babyPrice.setPassengerType(BizzConstants.PERSON_TYPE_BABY);
                babyPrice.setPriceType(BizzConstants.PERSON_TYPE_BABY);
                PnrFareItem infPnrPrice = patByPnrRes.getBabyFareItem();
                babyPrice.setSalePrice((float)infPnrPrice.getFare());
                babyPrice.setOrgPrice((float)infPnrPrice.getFare());
                babyPrice.setFloorPrice((float)infPnrPrice.getFare());
                babyPrice.setFuelSurcharge((float)infPnrPrice.getFuelSurcharge());
                babyPrice.setAirportTax((float)infPnrPrice.getAirPortTax());
                pnrData.getPrices().add(babyPrice);
            }
        }
    }


}
