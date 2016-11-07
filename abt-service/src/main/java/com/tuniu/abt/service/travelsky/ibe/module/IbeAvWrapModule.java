package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.AvItem;
import com.travelsky.ibe.client.AvResult;
import com.travelsky.ibe.client.AvSegment;
import com.travelsky.ibe.exceptions.IBEException;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.AirAvailResult;
import com.tuniu.abt.service.travelsky.dto.AvFlightSegment;
import com.tuniu.abt.service.travelsky.dto.AvOrgDstOption;
import com.tuniu.abt.service.travelsky.dto.AvSeatStatus;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.vo.connector.AirAvailRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/14.
 */
@Service
public class IbeAvWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbeAvWrapModule.class);
    @Resource
    private IbeInterfaceService ibeInterfaceService;

    /**
     * 
     * Description: 余位查询(airline航司二字码不能为空)<br> 
     *  
     * @author lanlugang<br>
     * @taskId <br>
     * @param availRequest
     * @return
     * @throws Exception <br>
     */
    public AirAvailResult getAvail(AirAvailRequest availRequest) {
        if (StringUtils.isBlank(availRequest.getAirline())) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX,
                    "airline value must not be null.");
        }
        String departureDate = availRequest.getDepartureDate();
        if (StringUtils.isBlank(departureDate)) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX,
                    "departureDate value must not be null.");
        }
        if (!availRequest.getAirline().matches("[A-Z0-9]{2}")) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX,
                    "airline value does not match regular expression facet '[A-Z0-9]{2}'.");
        }
        AirAvailResult airAvailResult = null;
        AvResult avres = null;
        try {
            LOGGER.info("ibeDConnectorClient getAvail, availRequest={}", availRequest);
            avres = ibeInterfaceService.getAvail(availRequest);
            if (null != avres) {
                // 校验是否为当天的查询结果
                String resTime = avres.getDt() + avres.getMonth() + avres.getYear();
                if (resTime.equals(DateTimeUtils.str2strdate(departureDate))) {
                    airAvailResult = convertToAirAvailResult(availRequest, avres);
                } else {
                    throw new BizzException(BizzEx.IBE_INTF_RESULT_ERROR, "查询AV结果为非起飞当天结果.");
                }
            } else {
                throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "查询AV结果为空.");
            }
        } catch (IBEException ibee) {
            LOGGER.error("查询IBE接口发生异常.", ibee);
            String errorInfo = ibee.getMessage();
            if (errorInfo.indexOf("NoRoutingException") != -1) {
                throw new BizzException(BizzEx.IBE_INTF_RESULT_NONE, "此线路没有直飞航班", ibee);
            } else {
                throw new BizzException(BizzEx.IBE_INTF_EX, "查询IBE接口发生异常", ibee);
            }
        } catch (Exception e) {
            LOGGER.error("查询IBE接口发生异常.", e);
            throw new BizzException(BizzEx.IBE_INTF_EX, "查询IBE接口发生异常", e);
        }
        return airAvailResult;
    }

    /**
     *
     * @param availRequest
     * @param avres
     * @return
     */
    private AirAvailResult convertToAirAvailResult(AirAvailRequest availRequest, AvResult avres) {
        AirAvailResult airAvailResult = new AirAvailResult();
        airAvailResult.setOrgCityIataCode(availRequest.getOrgCityIataCode());
        airAvailResult.setDstCityIataCode(availRequest.getDstCityIataCode());
        airAvailResult.setDepartureDate(DateTimeUtils.formatDate(avres.getDate(),
                                        DateTimeUtils.DATE_PATTERN));
        AvOrgDstOption orgDstOption = null;
        for (int i = 0; i < avres.getItemamount(); i++) {
            AvItem avItem = avres.getItem(i);
            orgDstOption = new AvOrgDstOption();
            airAvailResult.getOrgDstOptions().add(orgDstOption);
            AvFlightSegment segment = null;
            for (int j = 0; j < avItem.getSegmentnumber(); j++) {
                AvSegment as = avItem.getSegment(j);
                segment = new AvFlightSegment();
                orgDstOption.getSegments().add(segment);
                if (StringUtils.isNotBlank(as.getAirline()) 
                        && as.getAirline().length() >= 2) {
                    segment.setAirline(as.getAirline().substring(0, 2));
                }
                segment.setFlightNo(as.getAirline());
                segment.setOrgAirportIataCode(as.getOrgcity());
                segment.setDstAirportIataCode(as.getDstcity());
                segment.setDepartureDateTime(DateTimeUtils.formatDate(as.getDepdate(),
                                             DateTimeUtils.DATETIME_PATTERN));
                for (char ch = 'A'; ch <= 'Z'; ch = (char) (ch + '\001')) {
                    String cangweiStatus = as.getCangweiinfoOf(ch);
                    // 过滤掉舱位状态不是A和数字的舱位
                    if (null != cangweiStatus && cangweiStatus.matches("[1-9A]")) {
                        AvSeatStatus seatStatus = new AvSeatStatus();
                        segment.getSeatStatus().add(seatStatus);
                        seatStatus.setSeatCode(ch + "");
                        if (cangweiStatus.equals("A")) {
                            seatStatus.setStatus(">9");
                        } else {
                            seatStatus.setStatus(cangweiStatus);
                        }
                    }
                    // 增加对子舱位的处理
                    String[] subSeats = as.getSubClassList(ch);
                    if (subSeats != null && subSeats.length > 0) {
                        String subSeat= subSeats[0];
                        String subSeatCount = as.getSubClassInfoOf(ch, subSeat);
                        // 过滤掉舱位状态不是A和数字的舱位
                        if (null != subSeatCount && subSeatCount.matches("[1-9A]")) {
                            AvSeatStatus seatStatus = new AvSeatStatus();
                            segment.getSeatStatus().add(seatStatus);
                            seatStatus.setSeatCode(subSeat);
                            if (subSeatCount.equals("A")) {
                                seatStatus.setStatus(">9");
                            } else {
                                seatStatus.setStatus(subSeatCount);
                            }
                        }
                    }
                }
            }
        }
        return airAvailResult;
    }

}
