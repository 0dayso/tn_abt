package com.tuniu.abt.service.book.work.handlers;

import com.tuniu.abt.intf.constants.BookEx;
import com.tuniu.abt.intf.dto.book.main.BookVendorId;
import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.intf.dto.book.main.PnrInfo;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.book.ctrip.BookCtripService;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/1/12.
 */
@Component
public final class CtripBookWorkHandler extends BookWorkSupport {
    private static final Logger LOG = LoggerFactory.getLogger(CtripBookWorkHandler.class);

    @Resource
    private BookCtripService bookCtripService;

    @Override
    public boolean apply() {
        return (BookVendorId.CTRIP.intValue() == getBookingData().getVendorId());
    }
    
    @Override
    public int getOrder() {
        return 22;
    }

    @Override
    public boolean processing() throws Exception {
        /**反向查询补充携程占位请求数据 */
        bookCtripService.fillProductIdAndSaleType(getBookingData());
        /**处理重名问题 */
        List<BookingData> bookingRqList = bookCtripService.convert2BookingRqList(getBookingData());
        BookingReply bookingReply = new BookingReply();
        setBookingReply(bookingReply);
        /**发起占位 */
        bookingRqList.parallelStream().forEach(request -> bookingReply.getPnrInfos().addAll(innerBook(request)));
        return true;
    }

    private List<PnrInfo> innerBook(BookingData request) {
        try {
            //调用携程占座接口进行占座
            List<OrderInfoEntity> orderInfoEntityList = bookCtripService.occupy4Ctrip(request);
            //判断此次占座是否成功
            bookCtripService.checkCtripOrderIsValid(orderInfoEntityList, request);
            return bookCtripService.getCtripOrderInfo(request, orderInfoEntityList);
        } catch (Exception e) {
            LOG.error(request.getOrderId()+"携程占位异常:"+e.getMessage(), e);
            throw new BizzException(BookEx.CTRIP_CREATE_ORDER_FAILED, "携程占位异常:"+e.getMessage(), e);
        }
    }

}