package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.PATResult;
import com.travelsky.ibe.client.pnr.PNRAirSeg;
import com.travelsky.ibe.exceptions.IBEException;
import com.travelsky.util.QDateTime;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PataBySegRes;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.service.travelsky.ibe.converter.IbePataConverter;
import com.tuniu.adapter.common.init.VendorConfig;
import com.tuniu.adapter.flightTicket.vo.connector.PataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * Created by lanlugang on 2016/4/27.
 */
@Service
public class IbePataBySegWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbePataBySegWrapModule.class);

    @Resource
    private IbeInterfaceService ibeInterfaceService;

    public PataBySegRes<PATResult> pataBySegments(List<Segment> segments, String psgType) {
        PataBySegRes pataBySegRes = null;
        try {
            PataRequest pataRequest = convert2PataRequest(segments, psgType);
            PATResult patResult = ibeInterfaceService.doPata(pataRequest);
            pataBySegRes = new PataBySegRes();
            pataBySegRes.setPsgType(psgType);
            pataBySegRes.setSegments(segments);
            pataBySegRes.setPataResult(patResult);
            List<PnrFareItem> fareItems = IbePataConverter.convert2SegFareItems(patResult);
            pataBySegRes.setFareItems(fareItems);
            PnrFareItem fareItem = IbePataConverter.convert2SegFareItem(patResult);
            if (fareItem == null) {
                throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "Pata查询航段价格为空，未获取到可用价格");
            }
            pataBySegRes.setFareItem(fareItem);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "PATA运价出现异常.", e);
        }
        return pataBySegRes;
    }

    private PataRequest convert2PataRequest(List<Segment> segments, String psgType) throws IBEException {
        PataRequest pataRequest = new PataRequest();
        pataRequest.setLocation(VendorConfig.domesticIbePlusAirPortCode);
        pataRequest.setOffice(VendorConfig.domesticIbePlusOfficeNum);
        pataRequest.setClFlag(convertPsgType(psgType));
        addSegmentsInfo(pataRequest, segments);
        return pataRequest;
    }

    private String convertPsgType(String psgType) {
        String type = "";
        if (BizzConstants.PSG_TYPE_CODE_CHD.equals(psgType)) {
            type = "CH";
        } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(psgType)) {
            type = "IN";
        }
        return type;
    }

    private void addSegmentsInfo(PataRequest pataRequest, List<Segment> segments) throws IBEException {
        Vector<PNRAirSeg> airSegs = new Vector<PNRAirSeg>();
        PNRAirSeg airSeg = null;
        String airline = null;
        String[] planeModes = new String[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            planeModes[i] = segment.getPlaneType();
            airSeg = new PNRAirSeg();
            airSeg.setAirNo(segment.getFlightNo());
            airSeg.setFltClass(segment.getSeatCode().charAt(0));
            airSeg.setSubClass(segment.getSeatCode());
            airSeg.setOrgCity(segment.getOrgAirportIataCode());
            airSeg.setDesCity(segment.getDstAirportIataCode());
            String departureDateTime = segment.getDepartureDate().replace("-", "")
                    + " " + segment.getDepartureTime().replace(":", "");
            String arrivalDateTime = segment.getArriveDate().replace("-", "")
                    + " " + segment.getArrivalTime().replace(":", "");
            airSeg.setDepartureTime(QDateTime.stringToDate(departureDateTime, "YYYYMMDD HHMI"));
            airSeg.setArrivalTime(QDateTime.stringToDate(arrivalDateTime, "YYYYMMDD HHMI"));
            airSeg.setActionCode("HK");
            airSeg.setTktNum(1);
            airSeg.setSkChanged(false);
            airSeg.setType(PNRAirSeg.AIRSEG_ARNK);
            airSegs.add(airSeg);
            if (airline == null || airline.equals("")) {
                airline = segment.getAirlineCompanyIataCode();
            }
        }
        pataRequest.setAirline(airline);
        pataRequest.setSegments(airSegs);
        pataRequest.setPlaneModes(planeModes);
    }

}
