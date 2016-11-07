package com.tuniu.abt.service.aop;

import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.AvSeatStatusDto;
import com.tuniu.abt.intf.dto.book.main.AvSeatStatusGroup;
import com.tuniu.abt.intf.dto.book.main.Passenger;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspAopInterface;
import com.tuniu.abt.intf.tsp.dto.aop.AopPolicyReq;
import com.tuniu.abt.utils.DateTimeUtils;
import com.tuniu.adapter.flightTicket.vendor.ibeplus.dockingClient.PassengerType;
import com.tuniu.adapter.flightTicket.vo.policy.aop.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 查询、校验、过滤政策服务
 * Created by lanlugang on 2016/5/12.
 */
@Service
public class AopPolicyInquiryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopPolicyInquiryService.class);

    @Resource
    private TspAopInterface tspAopInterface;

    /**
     * 按出发、到达、团期、航班号、舱位查询政策
     * @param queryPoliciesReq
     * @return
     */
    public List<FlightPolicy> inquiryPolicies(QueryPoliciesReq queryPoliciesReq) {
        QueryPoliciesRsp res = tspAopInterface.policyInquiry(queryPoliciesReq);
        return res.getFlightPolicyList();
    }

    public List<FlightPolicy> inquiryPolicyBySegment(Segment segment, List<Seat> seats) {
        try {
            QueryPoliciesReq queryPoliciesReq = new QueryPoliciesReq();
            queryPoliciesReq.setJourneyType(1);
            queryPoliciesReq.setRph(1);
            queryPoliciesReq.setOrgAirportIataCode(segment.getOrgAirportIataCode());
            queryPoliciesReq.setDestAirportIataCode(segment.getDstAirportIataCode());
            queryPoliciesReq.setDepartureDate(DateTimeUtils.parseDate(segment.getDepartureDate()));
            List<Flight> flights = new ArrayList<Flight>();
            Flight flight = new Flight();
            flight.setFlightNo(segment.getFlightNo());
            flight.setSeatList(seats);
            flights.add(flight);
            queryPoliciesReq.setFlightList(flights);
            return inquiryPolicies(queryPoliciesReq);
        } catch (Exception e) {
            LOGGER.error("查询AOP政策异常", e);
        }
        return null;
    }

    /**
     * 按政策id查询政策详情
     * @param policyId
     * @return
     */
    public PolicyDetail inquiryPolicyById(Long policyId) {
        PolicyDetail policyDetail = tspAopInterface.policyDetail(AopPolicyReq.build(policyId));
        if (policyDetail == null) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_RESULT_NONE, "开放平台根据政策ID获取政策详情结果为空，请核查。");
        }
        // 政策失效
        if (!policyDetail.isEffective()) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_INVALID_POLICY, "开放平台的政策失效，政策ID:" + policyId);
        }
        return policyDetail;
    }

    /**
     *  1、校验人员类型；2、校验证件类型 3、校验当前时间是否在有效的出票时间范围之内
     * @param passengers
     * @param policyDetail
     */
    public void checkAopPolicyIsValid(List<Passenger> passengers, PolicyDetail policyDetail) {
        Limit limit = policyDetail.getLimit();
        BilingTime bilingTime = limit.getBilingTime();
        Date etdzStartTime = bilingTime.getEtdzStartTime();
        Date etdzEndTime = bilingTime.getEtdzEndTime();
        Date currDate = new Date();
        int compareDate1 = 0;
        int compareDate2 = 0;
        if (etdzStartTime != null) {
            compareDate1 = currDate.compareTo(etdzStartTime);
        }
        if (etdzEndTime != null) {
            compareDate2 = etdzEndTime.compareTo(currDate);

        }
        if (compareDate1 < 0 || compareDate2 < 0) {
            throw new BizzException(BookEx.AOP_INVALID_POLICY, "当前时间不在出票时间之内");
        }
        // 人员类型校验
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int adultNum = 0;
        int childNum = 0;
        int babyNum = 0;
        for (Passenger passenger : passengers) {
            if (BizzConstants.PSG_TYPE_CODE_ADT.equals(passenger.getPassengerTypeCode())) {
                adultNum++;
            } else if (BizzConstants.PSG_TYPE_CODE_CHD.equals(passenger.getPassengerTypeCode())) {
                childNum++;
            } else if (BizzConstants.PSG_TYPE_CODE_INF.equals(passenger.getPassengerTypeCode())) {
                babyNum++;
            }
            hashSet.add(passenger.getIdentityType());
        }
        List<PassengerType> passengerTypes = limit.getPassengerTypes();
        HashSet<String> passengerTypeSet = new HashSet<String>();
        for (PassengerType passengerType : passengerTypes) {
            passengerTypeSet.add(passengerType.name());
        }
        if (adultNum > 0 && !passengerTypeSet.contains("ADT")) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_INVALID_POLICY, "当前政策不支持成人");
        } else if (childNum > 0 && !passengerTypeSet.contains("CHD")) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_INVALID_POLICY, "当前政策不支持儿童");
        } else if (babyNum > 0 && !passengerTypeSet.contains("INF")) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_INVALID_POLICY, "当前政策不支持婴儿");
        }
        // 获取政策支持的证件类型
        List<Integer> certificateTypes = limit.getCertificateTypes();
        if (!certificateTypes.containsAll(hashSet)) {
            throw new BizzException(BizzEx.TSP_AOP_INTF_INVALID_POLICY, "当前政策不支持所选证件类型");
        }
    }

    /**
     * 按费率计算成本价
     * @param costPrice
     * @param feeRate
     * @return
     */
    public static float calulateCostPrice(float costPrice, FeeRate feeRate) {
        costPrice = (float) (Math.ceil(costPrice * (new BigDecimal(100).subtract(new BigDecimal(feeRate.getCommisionPoint())))
                .divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_UP).doubleValue())
                - feeRate.getCommisionMoney() + feeRate.getInvoiceCost());
        if (costPrice < 1) {
            costPrice = 10;
        }
        return costPrice;
    }

    /**
     * 获取同一个舱位下价格最低的政策id
     * @param seatPolicyList
     * @return
     */
    public Map<String, PolicyDetail> getMinPricePolicyMap(List<SeatPolicy> seatPolicyList) {
        Map<String, PolicyDetail> map = new HashMap<String, PolicyDetail>();
        for (SeatPolicy seatPolicy : seatPolicyList) {
            String seatCode = seatPolicy.getSeatCode();
            float basePrice = seatPolicy.getFdPrice();
            PolicyDetail minPolicy = null;
            for (PolicyDetail policyDetail : seatPolicy.getPolicyList()) {
                if (!policyDetail.isEffective()) { // 过滤无效政策
                    continue;
                }
                if (null == minPolicy) {
                    minPolicy = policyDetail;
                    continue;
                }
                float adtCostPrice = getAdtCostPrice(basePrice, policyDetail);
                float preAdtCostPrice = getAdtCostPrice(basePrice, minPolicy);
                if (adtCostPrice < preAdtCostPrice) {
                    minPolicy = policyDetail;
                }
            }
            if (null != minPolicy) {
                map.put(seatCode, minPolicy);
            }
        }
        return map;
    }

    private float getAdtCostPrice(float basePrice, PolicyDetail policyDetail) {
        FeeRate feeRate = null;
        for (FeeRate curFeeRate : policyDetail.getFeeRateList()) {
            if (curFeeRate.getPassengerType().equals(PassengerType.ADT)) {
                feeRate = curFeeRate;
                break;
            }
        }
        return calulateCostPrice(basePrice, feeRate);
    }

}
