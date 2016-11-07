package com.tuniu.abt.api.book;

import com.tuniu.abt.base.tracer.annotation.ActionTrace;
import com.tuniu.abt.base.tracer.pojo.TracerActionEnum;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.intf.dto.cancel.ReqCancel;
import com.tuniu.abt.service.book.BookFacadeService;
import com.tuniu.abt.service.cancel.CancelFacadeService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 新占位接口
 *
 * Created by chengyao on 2016/1/8.
 */
@Controller
@RequestMapping("/book")
public class BookApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookApi.class);

    @Resource
    BookFacadeService bookFacadeService;
    @Resource
    private CancelFacadeService cancelFacadeService;

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    @TSPServiceInfo(name="ATS.ABT.BookApi.booking", description = "国内机票占位")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.BOOK)
    public Object booking(@Json BookingRequest request) throws IOException {
        bookFacadeService.occupy(request);
        return null;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @TSPServiceInfo(name="ATS.ABT.BookApi.cancel", description = "国内机票取消占位")
    @ResponseJson
    @ActionTrace(action = TracerActionEnum.CANCEL)
    public Object cancel(@Json ReqCancel request) {
        return cancelFacadeService.cancel(request);
    }

}
