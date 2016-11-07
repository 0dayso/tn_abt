package com.tuniu.abt.service.book.work.handlers;

import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.service.aop.AopPolicyInquiryService;
import com.tuniu.abt.service.book.aop.BookAopService;
import com.tuniu.abt.service.book.aop.airline.BookAopAirlineService;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/1/16.
 */
@Component
public class AopPolicyBookWorkHandler extends BookWorkSupport {

    @Resource
    private BookAopService bookAopService;
    @Resource
    private AopPolicyInquiryService aopPolicyInquiryService;
    @Resource
    private BookAopAirlineService bookAopAirlineService;

    @Resource
    private TsConfig tsConfig;

    @Override
    public int getOrder() {
        return 16;
    }

    @Override
    public boolean apply() {
        if (BookVendorId.AOP.intValue() == getBookingData().getVendorId()
                 && !bookAopAirlineService.isAirlineLinkOfAop(getBookingData().getPolicyId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean processing() throws Exception {
        BookingData bookingData = getBookingData();
        // 查询政策并校验
        PolicyDetail policyDetail = aopPolicyInquiryService.inquiryPolicyById(bookingData.getPolicyId());
        BookWorkSupport.getAopPolicyDetailList().add(policyDetail);
        if (tsConfig.isAllowed(TsConfig.CHECK_AOP_POLICY_IS_VALID, bookingData.getSystemId(), bookingData.getVendorId())) {
            aopPolicyInquiryService.checkAopPolicyIsValid(bookingData.getPassengers(), policyDetail);
        }
        // 占位
        BookingReply bookingReply = bookAopService.occupy4Aop(getBookingData());
        setBookingReply(bookingReply);
        return true;
    }

}