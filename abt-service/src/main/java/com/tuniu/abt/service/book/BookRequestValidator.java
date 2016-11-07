package com.tuniu.abt.service.book;

import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.BookPassengerType;
import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.request.BookingDetail;
import com.tuniu.abt.intf.dto.book.request.BookingPassenger;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.exception.BizzException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by chengyao on 2016/2/15.
 */
public class BookRequestValidator {

    /**
     * 检查入参
     * @param bookingRequest 入参
     */
    public static void checkRequest(BookingRequest bookingRequest) {
        BookingDetail bookingDetail = bookingRequest.getBookingDetail();
        int vendorId = bookingRequest.getVendorId();
        if (!BookVendorId.getAllVendorIds().contains(vendorId)) {
            throwBizzException(BookEx.INVALID_VENDOR_ID, "vendorId is invalid");
        }
        if (vendorId == BookVendorId.AOP.intValue()
                && (null == bookingDetail.getPolicyId() || bookingDetail.getPolicyId() == 0)) {
            throwBizzException(BookEx.VERIFY_PARAM_FAILED, "policyId must not null of AOP");
        }
        // 人员信息
        for (BookingPassenger passenger : bookingDetail.getPassengers()) {
            if (!BookPassengerType.getAllPsgTypes().contains(passenger.getPassengerTypeCode())) {
                throwBizzException(BookEx.INVALID_PSG_TYPE, passenger.getName() + "'s passengerTypeCode is invalid");
            }
            // 婴儿姓名拼音不能为空
            if (BookPassengerType.INF.name().equals(passenger.getPassengerTypeCode())
                    && (StringUtils.isBlank(passenger.getFirstName()) || StringUtils.isBlank(passenger.getLastName()))) {
                throwBizzException(BookEx.INVALID_PSG_NAME, "infant's firstName and lastName must not null");
            }
        }
    }

    private static void throwBizzException(int errorCode, String errMsg) {
        throw new BizzException(errorCode, errMsg);
    }

}
