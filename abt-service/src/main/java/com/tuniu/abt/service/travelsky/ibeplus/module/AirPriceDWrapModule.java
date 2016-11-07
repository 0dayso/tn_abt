package com.tuniu.abt.service.travelsky.ibeplus.module;

import com.travelsky.espeed.*;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PatByPnrReq;
import com.tuniu.abt.service.travelsky.dto.PatByPnrRes;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceService;
import com.tuniu.abt.utils.SystemConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/22.
 */
@Service
public class AirPriceDWrapModule {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private IbePlusInterfaceService ibePlusInterfaceService;

    @Resource
    private AirResRetWrapModule airResRetWrapModule;

    @Resource
    private AirBookModifyWrapModule airBookModifyWrapModule;


    /**
     *
     * Description: pat计算价格<br>
     *
     * @author lanlugang<br>
     * @taskId AIR-4506<br>
     * @param patByPnrReq
     * @return <br>
     */
    public PatByPnrRes<OTAAirPriceRS> patPriceByPnrCalculate(PatByPnrReq patByPnrReq) {
        PatByPnrRes patByPnrRes = new PatByPnrRes();
        patByPnrRes.setPatByPnrReq(patByPnrReq);
        airPriceD(patByPnrRes, TransactionType.PAT, false);
        if (patByPnrReq.isHasBaby()) {
            airPriceD(patByPnrRes, TransactionType.PAT, true);
        }
        return patByPnrRes;
    }

    /**
     * Description: pat存储价格<br>
     * @param patByPnrRes
     */
    public void patPriceByPnrStore(PatByPnrRes patByPnrRes) {
        airPriceD(patByPnrRes, TransactionType.SFC, false);
        if (patByPnrRes.getPatByPnrReq().isHasBaby()) {
            // 删除EI项
            deleteEiComment(patByPnrRes.getPatByPnrReq().getPnrNo());
            // 存储婴儿运价
            airPriceD(patByPnrRes, TransactionType.SFC, true);
        }
    }

    private void deleteEiComment(String pnrNo) {
        OTAAirResRetRS otaAirResRetRS = airResRetWrapModule.rt(pnrNo);
        List<Others> othersList = otaAirResRetRS.getAirResRet().getOthers();
        List<String> rphList = new ArrayList<String>();
        for (Others others : othersList) {
            String text = others.getText();
            String firstTxt = text.split("/")[0];
            if ("EI".equals(firstTxt)) {
                rphList.add(String.valueOf(others.getRPH()));
            }

        }
        if (CollectionUtils.isNotEmpty(rphList)) {
            airBookModifyWrapModule.delItemsInPNR(pnrNo, rphList);
        }
    }

    private void airPriceD(PatByPnrRes patByPnrRes, TransactionType type, boolean babyFlag) {
        OTAAirPriceRQ request = convertPataRequest(patByPnrRes, type, babyFlag);
        OTAAirPriceRS otaAirPriceRs = ibePlusInterfaceService.airPriceD(request);
        PnrFareItem pnrFareItem = convert2PnrFareItem(otaAirPriceRs);
        if (null == pnrFareItem) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_NONE, "Pat查询价格为空，未获取到可用价格");
        }
        if (babyFlag) {
            patByPnrRes.setBabyFareItem(pnrFareItem);
            patByPnrRes.setBabyPatRes(otaAirPriceRs);
        } else {
            patByPnrRes.setFareItem(pnrFareItem);
            patByPnrRes.setPatRes(otaAirPriceRs);
        }
    }

    private PnrFareItem convert2PnrFareItem(OTAAirPriceRS otaAirPriceRs) {
        PricedItineraries pricedItineraries = otaAirPriceRs.getPricedItineraries();
        List<PricedItinerary> pricedItineraryList = pricedItineraries.getPricedItinerary();
        PnrFareItem minPnrFareItem = null;
        for (PricedItinerary pricedItinerary : pricedItineraryList) {
            AirItineraryPricingInfo airItineraryPricingInfo = pricedItinerary.getAirItineraryPricingInfo();
            List<ItinTotalFare> itinTotalFareList = airItineraryPricingInfo.getItinTotalFare();
            for (ItinTotalFare itinTotalFare : itinTotalFareList) {
                BaseFare baseFare = itinTotalFare.getBaseFare();
                double farePrice = baseFare.getAmount().doubleValue();
                // 过滤小于等于0的价格 added by @lanlugang 2015-10-8 [AIR-1335]
                if (farePrice <= 0.0) {
                    continue;
                }
                PnrFareItem pnrFareItem = new PnrFareItem();
                pnrFareItem.setRph(Integer.parseInt(itinTotalFare.getRPH()));
                pnrFareItem.setFare(farePrice);
                Taxes taxes = itinTotalFare.getTaxes();
                List<Tax> taxList = taxes.getTax();
                for (Tax tax : taxList) {
                    if ("CN".equals(tax.getTaxCode())) {
                        pnrFareItem.setAirPortTax(tax.getAmount().doubleValue());
                    }
                    if ("YQ".equals(tax.getTaxCode())) {
                        pnrFareItem.setFuelSurcharge(tax.getAmount().doubleValue());
                    }
                }
                pnrFareItem.setFareBasisCode(itinTotalFare.getFareBasisCodes().getFareBasisCode().get(0));
                pnrFareItem.setTotal(pnrFareItem.getFare()
                        + pnrFareItem.getAirPortTax() + pnrFareItem.getFuelSurcharge());
                if (null == minPnrFareItem
                        || minPnrFareItem.getTotal() > pnrFareItem.getTotal()) {
                    minPnrFareItem = pnrFareItem;
                }
            }
        }
        return minPnrFareItem;
    }

    private OTAAirPriceRQ convertPataRequest(PatByPnrRes patByPnrRes, TransactionType type, boolean babyFlag) {
        OTAAirPriceRQ airPriceRq = new OTAAirPriceRQ();
        airPriceRq.setTransactionIdentifier(type);
        if (type.equals(TransactionType.SFC)) {
            int rph = 0;
            if (babyFlag) {
                if (null == patByPnrRes.getBabyFareItem()) {
                    throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR存储婴儿运价请求中，婴儿patRes为空");
                }
                rph = patByPnrRes.getBabyFareItem().getRph();
            } else {
                if (null == patByPnrRes.getFareItem()) {
                    throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX, "PNR存储运价请求中，patRes为空");
                }
                rph = patByPnrRes.getFareItem().getRph();
            }
            if (rph == 0) {
                rph = 1;
            }
            airPriceRq.setFareRefRPH(new BigDecimal(rph));
        }
        PatByPnrReq patByPnrReq = patByPnrRes.getPatByPnrReq();
        POS pos = new POS();
        Source source = new Source();
        if (StringUtils.isBlank(patByPnrReq.getOfficeNo())) {
            source.setPseudoCityCode(systemConfig.getIbeplusOfficeNo());
        } else {
            source.setPseudoCityCode(patByPnrReq.getOfficeNo());
        }
        pos.getSource().add(source);
        airPriceRq.setPOS(pos);

        BookingReferenceID bookingReferenceID = new BookingReferenceID();
        bookingReferenceID.setID(patByPnrReq.getPnrNo());
        airPriceRq.setBookingReferenceID(bookingReferenceID);

        TravelerInfoSummary travelerInfoSummary = new TravelerInfoSummary();
        List<AirTravelerAvail> airTravelerAvailList = travelerInfoSummary.getAirTravelerAvail();
        AirTravelerAvail airTravelerAvail = new AirTravelerAvail();
        List<PassengerTypeQuantity> passengerTypeQuantityList = airTravelerAvail.getPassengerTypeQuantity();
        PassengerTypeQuantity passengerTypeQuantity = new PassengerTypeQuantity();

        if (babyFlag) {
            passengerTypeQuantity.setCode(PassengerType.INF);
            AirTraveler airTraveler = new AirTraveler();
            TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
            travelerRefNumber.setRPH("0");
            airTraveler.setTravelerRefNumber(travelerRefNumber);
            airTravelerAvail.setAirTraveler(airTraveler);
        } else {
            String passengerType = patByPnrReq.getPsgType();
            if (StringUtils.isBlank(passengerType)) {
                passengerTypeQuantity.setCode(PassengerType.ADT);
            } else if ("CH".equals(passengerType)) {
                passengerTypeQuantity.setCode(PassengerType.CHD);
                AirTraveler airTraveler = new AirTraveler();
                TravelerRefNumber travelerRefNumber = new TravelerRefNumber();
                travelerRefNumber.setRPH("0");
                airTraveler.setTravelerRefNumber(travelerRefNumber);
                airTravelerAvail.setAirTraveler(airTraveler);
            }
        }
        passengerTypeQuantityList.add(passengerTypeQuantity);
        airTravelerAvailList.add(airTravelerAvail);
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setPaymentType(PayType.CASH);
        travelerInfoSummary.setPaymentDetail(paymentDetail);
        airPriceRq.setTravelerInfoSummary(travelerInfoSummary);
        return airPriceRq;
    }
    
}
