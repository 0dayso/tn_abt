package com.tuniu.abt.service.book.travelsky;

import com.tuniu.abt.intf.dto.book.main.BookingData;
import com.tuniu.abt.intf.dto.book.main.BookingReply;
import com.tuniu.abt.intf.dto.book.main.PnrInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
/**
 * 
 * <Description> 航信占位服务类<br> 
 *  
 * @author lanlugang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-3-6 <br>
 */
@Component
public class BookTravelSkyService {
    
    private static final Logger LOG = LoggerFactory.getLogger(BookTravelSkyService.class);

    @Resource
    private BookTravelSkyWrapper bookTravelSkyWrapper;

    public BookingReply occupy4Hangxin(BookingData bookingData) throws Exception {
        BookingReply bookingReply = new BookingReply();
        // 生成并校验pnr
        List<PnrInfo> pnrInfos = bookTravelSkyWrapper.creatAndCheckPnrs(bookingData);
        bookingReply.getPnrInfos().addAll(pnrInfos);
        // 计算并做价格
        bookTravelSkyWrapper.doPataPriceByPnr(pnrInfos, bookingData.getSystemId(), bookingData.getVendorId());
        return bookingReply;
    }

}
