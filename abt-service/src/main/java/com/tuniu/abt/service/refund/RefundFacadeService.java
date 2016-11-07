package com.tuniu.abt.service.refund;

import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.dto.refund.ReqRefund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 退票入口
 * Created by chengyao on 2016/2/18.
 */
@Validated
@Service
public class RefundFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundFacadeService.class);

    @Resource
    private RefundAsyncTask refundAsyncTask;

    public Object refund(@Valid ReqRefund request) {
        ProcRefundData procRefundData = new ProcRefundData();
        procRefundData.setReqRefund(request);
        RefundDataSupport.setData(procRefundData);
        refundAsyncTask.execute(request.getCallback());
        return null;
    }


}
