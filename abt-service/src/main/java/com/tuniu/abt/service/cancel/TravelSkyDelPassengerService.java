package com.tuniu.abt.service.cancel;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.CancelEx;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import com.tuniu.abt.intf.dto.cancel.RtForDelete;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.service.travelsky.dto.PassengerItem;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.ListConverter;
import com.tuniu.vla.base.exception.BaseException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 航信取消PNR或PNR乘客处理服务
 * Created by chengyao on 2016/4/9.
 */
@Service
public class TravelSkyDelPassengerService {

    @Resource
    private TravelSkyInterface travelSkyInterface;

    /**
     * 取消单个PNR
     * @param pnr pnr
     */
    public void cancelPnr(String pnr, int systemId) {
        //校验该PNR是否已经出票
        OTAAirResRetRS.AirResRet airResRet = null;
        try {
            airResRet = rtAndCheck(pnr, systemId);
        } catch (BaseException e) {
            if (e.getCode() == CancelEx.ALREADY_CANCELED) return;
            throw e;
        }
        // DO NOTHING WITH RT RESULT
        // 取消pnr
        travelSkyInterface.cancelPnr(pnr, systemId, airResRet.getResponsibility().getOfficeCode());
    }



    /**
     * 删除PNR中的指定乘客
     * Description: <br>
     *
     * @author baodawei<br>
     * @taskId <br>
     * @return <br>
     */
    public void cancelPnrPassenger(String pnr, int systemId, List<AbtPnrPassenger> delPassengers, AbtPnrMain newRef) {
        if (CollectionUtils.isEmpty(delPassengers)) {
            return;
        }

        OTAAirResRetRS.AirResRet airResRet;
        try {
            airResRet = rtAndCheck(pnr, systemId);
        } catch (BizzException e) {
            if (e.getCode() == CancelEx.ALREADY_CANCELED) return; throw e; // PNR已取消，认为是成功的
        }

        RtForDelete rtForDelete = CommonUtils.transform(airResRet, RtForDelete.class);

        // 删除婴儿
        Collection<AbtPnrPassenger> babies = findByPassengerType(delPassengers, AbtPnrPassenger.PASSENGER_TYPE_BABY);
        try {
            if (CollectionUtils.isNotEmpty(babies)) {
                dealDeleteBabies(rtForDelete, babies);
            }
        } catch (Exception ex) {
            throw new BizzException(BizzEx.COMM_EX_WRAP, "删除婴儿项失败", ex);
        }

        // 找到其他的删除项目
        List<AbtPnrPassenger> others = new ArrayList<AbtPnrPassenger>(delPassengers);
        for (AbtPnrPassenger baby : babies) {
            others.remove(baby);
        }

        List<AirTraveler2> airTravelers = rtForDelete.getAirTraveler();
        List<PassengerItem> delPassengerItems = new ArrayList<PassengerItem>();
        for (AirTraveler2 airTraveler : airTravelers) {
            for (AbtPnrPassenger other : others) {
                if (other.getBookName().equals(airTraveler.getPersonName().get(0).getSurname())) {
                    PassengerItem passengerItem = new PassengerItem();
                    passengerItem.setName(airTraveler.getPersonName().get(0).getSurname());
                    passengerItem.setPassengerType(airTraveler.getPassengerTypeQuantity().getCode());
                    passengerItem.setRph(airTraveler.getRPH());

                    delPassengerItems.add(passengerItem);
                    break;
                }
            }
        }
        rtForDelete.setDelPassengerItems(delPassengerItems);

        if (newRef != null) {
            changeSsr(pnr, newRef, rtForDelete);
        }

        String delPnr = rtForDelete.getBookingReferenceID().getID();
        travelSkyInterface.delPnrItem(delPnr, rtForDelete.getDelRphs());

        travelSkyInterface.delPnrPassengers(delPnr, rtForDelete.getDelPassengerItems());

    }

    private OTAAirResRetRS.AirResRet rtAndCheck(String pnr, int systemId) {
        OTAAirResRetRS.AirResRet airResRet;
        try {
            airResRet = travelSkyInterface.rt(pnr, systemId);
        } catch (Exception e) {
            if (e.getMessage().contains("PNR:"+pnr+" was entirely cancelled.")) {
                throw new BizzException(CancelEx.ALREADY_CANCELED);
            }
            throw new BizzException(BizzEx.IBE_INTF_RESULT_ERROR, "RT获取PNR信息异常", e);
        }
        List<Ticketing2> ticketings = airResRet.getTicketing();
        for (Ticketing2 ticketing : ticketings) {
            if (ticketing.isIsIssued()) {
                throw new BizzException(CancelEx.CANCEL_MODULE_INTF_FAIL,
                        "[PNR=" + pnr + "]已出票");
            }
        }
        return airResRet;
    }

    public void dealDeleteBabies(RtForDelete rtForDelete, Collection<AbtPnrPassenger> babies) {
        // 处理乘客项目
        findDeleteXnSsr(rtForDelete, babies);
        // 处理FN/FP项目
        fillDeleteFnFp(rtForDelete);
    }

    // 1、删除XN和SSR INFT
    private void findDeleteXnSsr(RtForDelete rtForDelete, Collection<AbtPnrPassenger> babies) {
        // 旅客信息项目找到并删除
        Set<String> babyRefRph = new HashSet<String>();
        Iterator<AirTraveler2> iterTraveler = rtForDelete.getAirTraveler().iterator();
        while (iterTraveler.hasNext()) {
            AirTraveler2 airTraveler = iterTraveler.next();
            PassengerTypeQuantity passengerTypeQuantity = airTraveler.getPassengerTypeQuantity();
            PersonName personName = airTraveler.getPersonName().get(0);
            if (PassengerType.INF == passengerTypeQuantity.getCode()) {
                for (AbtPnrPassenger baby : babies) {
                    if (personName.getSurname().equals(baby.getBookName())) {
                        rtForDelete.getDelRphs().add(airTraveler.getRPH());
                        babyRefRph.add(airTraveler.getTravelerRefNumber().getInfantTravelerRPH());
                        iterTraveler.remove();
                        break;
                    }
                }
                rtForDelete.setSurplusBabyFlag(true);
            }
        }

        // SSR项目找到并删除
        Iterator<SpecialServiceRequest> iterSpecial = rtForDelete.getSpecialServiceRequest().iterator();
        while (iterSpecial.hasNext()) {
            SpecialServiceRequest specialServiceRequest = iterSpecial.next();

            if ("INFT".equals(specialServiceRequest.getSSRCode())) {
                String refRph = specialServiceRequest.getTravelerRefNumber().get(0).getRPH();
                if (babyRefRph.contains(refRph)) {
                    rtForDelete.getDelRphs().add(specialServiceRequest.getRPH());
                    iterSpecial.remove();
                }
            }
        }
    }


    // 2、剩余乘客中没有婴儿，删除FN项和FP项
    private void fillDeleteFnFp(RtForDelete rtForDelete) {
        // 如果没有删除婴儿项目，则跳过
        if (CollectionUtils.isEmpty(rtForDelete.getDelRphs())) {
            return;
        }
        // 如果还有婴儿，跳过
        if (rtForDelete.isSurplusBabyFlag()) {
            return;
        }

        Iterator<FN> iterFns =  rtForDelete.getFN().iterator();
        while (iterFns.hasNext()) {
            FN fn = iterFns.next();
            if (fn.getText().startsWith("FN/A/IN/")) {
                rtForDelete.getDelRphs().add(String.valueOf(fn.getRPH()));
                iterFns.remove();
            }
        }
        Iterator<FP> iterFps =  rtForDelete.getFP().iterator();
        while (iterFps.hasNext()) {
            FP fp = iterFps.next();
            if (fp.getRemark().startsWith("FP/IN/")) {
                rtForDelete.getDelRphs().add(String.valueOf(fp.getRPH()));
                iterFps.remove();
            }
        }
    }


    private void changeSsr(String pnr, AbtPnrMain newRef, OTAAirResRetRS.AirResRet airResRet) {
        String departDates = "";
        Set<String> rphList = new HashSet<String>();

        // RT儿童的PNR
        List<SpecialServiceRequest> specialServiceRequestList = airResRet.getSpecialServiceRequest();
        for (SpecialServiceRequest specialServiceRequest : specialServiceRequestList) {
            String ssrCode = specialServiceRequest.getSSRCode();
            if ("OTHS".equals(ssrCode)) {
                String text = specialServiceRequest.getText();
                String replaceText = text.replaceAll(" ", "");
                if (replaceText.contains("ADT" + pnr)) {
                    String[] split = text.split(" ");
                    departDates = split[split.length - 3] + " " + split[split.length - 2];
                    rphList.add(specialServiceRequest.getRPH());
                }
            }
        }

        if (CollectionUtils.isNotEmpty(rphList)) {
            // 先删除该儿童PNR中带有成人PNR的SSR项
            travelSkyInterface.delPnrItem(pnr, rphList);

            // 再往该儿童的PNR中添加其他成人PNR的SSR项

            OTAAirResRetRS.AirResRet airResRetRef = travelSkyInterface.rt(newRef.getPnr());
            List<FlightSegment2> flightSegmentList = airResRetRef.getFlightSegments().get(0).getFlightSegment();

            for (FlightSegment2 flightSegment : flightSegmentList) {
                MarketingAirline marketingAirline = flightSegment.getMarketingAirline();
                SpecialServiceRequest specialServiceRequest = new SpecialServiceRequest();
                specialServiceRequest.setSSRCode("OTHS");
                Airline airline = new Airline();
                airline.setCode(marketingAirline.getCode());
                specialServiceRequest.setAirline(airline);
                String anotherAdultSeatCode = null;
                // 获取成人的舱位编码
                if (flightSegment.getBookingClassAvail() != null
                        && flightSegment.getBookingClassAvail().size() > 0) {
                    anotherAdultSeatCode = flightSegment.getBookingClassAvail().get(0).getResBookDesigCode();
                }
                // 拼接备注项
                String ssrOthers = "ADT " + pnr + " " + departDates + " " + anotherAdultSeatCode;
                specialServiceRequest.setText(ssrOthers);
                travelSkyInterface.ssrAdd(pnr, specialServiceRequest, null);
            }
        }
    }


    private Collection<AbtPnrPassenger> findByPassengerType(List<AbtPnrPassenger> passengers, final int passengerType) {
        return CommonUtils.transformListRemoveNone(passengers, new ListConverter<AbtPnrPassenger, AbtPnrPassenger>() {
            @Override
            public AbtPnrPassenger convert(AbtPnrPassenger abtPnrPassenger) throws Exception {
                if (abtPnrPassenger.getPassengerType() != passengerType) {
                    return null;
                }
                return abtPnrPassenger;
            }
        });
    }

}
