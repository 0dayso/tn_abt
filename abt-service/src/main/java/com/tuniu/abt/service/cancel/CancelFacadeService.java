package com.tuniu.abt.service.cancel;

import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.dao.AbtBookingRequestDao;
import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.intf.constants.CancelEx;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import com.tuniu.abt.intf.dto.cancel.ReqCancel;
import com.tuniu.abt.intf.dto.cancel.ReqCancelPnr;
import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.ListConverter;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 取消占位服务
 * Created by chengyao on 2016/1/29.
 */
@Service
@Validated
public class CancelFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelFacadeService.class);

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Resource
    private AbtBookingRequestDao abtBookingRequestDao;

    @Resource
    CancelAsyncTask cancelAsyncTask;

    @Resource
    private CancelProcessorFactory cancelProcessorFactory;


    public Object cancel(@Valid ReqCancel request) {

        CancelDataSupport.setRequest(request);

        ProcCancelData procCancelData = buildCancelData(request);
        CancelDataSupport.setData(procCancelData);

        cancelAsyncTask.execute(request.getCallback());

        return null;
    }



    /**
     * 构建填充取消占位所需数据，检查有异常的情况
     * @param request 取消占位请求
     * @return 取消占位内部共享数据对象
     */
    private ProcCancelData buildCancelData(ReqCancel request) {
        Long orderId = request.getOrderId();
        Long flightItemId = request.getCancelDetail().getFlightItemId();
        List<ReqCancelPnr> cancelPnrList = request.getCancelDetail().getCancelPnrs();

        // 取消的乘客数据（航信专用）
        final Map<String, List<PassengerInfo>> delPassengerNames = new HashedMap<String, List<PassengerInfo>>();

        // 要取消的pnr列表
        final List<String> pnrs = CommonUtils.transformList(cancelPnrList, new ListConverter<String, ReqCancelPnr>() {
            @Override
            public String convert(ReqCancelPnr reqCancelPnr) throws Exception {
                String pnr = reqCancelPnr.getOrderId();
                if (CollectionUtils.isNotEmpty(reqCancelPnr.getPassengers())) {
                    delPassengerNames.put(pnr, reqCancelPnr.getPassengers());
                }
                return reqCancelPnr.getOrderId();
            }
        });

        // 找到占位请求数据
        final AbtBookingRequest abtBookingRequest = abtBookingRequestDao.findByOrder(orderId, flightItemId);
        if (abtBookingRequest == null) {
            throw new BizzException(CancelEx.CANCEL_FAIL, "未找到占位数据");
        }
        // 请求入参中没有vendorId, 在这里设置
        FlatTraceInfo flatTraceInfo = DataSharedSupport.getTracer();
        if (flatTraceInfo != null) {
            flatTraceInfo.setVendorId(String.valueOf(abtBookingRequest.getVendorId()));
        }


        // 找到占位pnr数据
        final List<AbtPnrMain> pnrMains = abtPnrMainDao.findListByRequestId(abtBookingRequest.getId());
        final List<AbtPnrMain> availablePnrMains = CommonUtils.transformListRemoveNone(pnrMains,
                new ListConverter<AbtPnrMain, AbtPnrMain>() {
                    @Override
                    public AbtPnrMain convert(AbtPnrMain abtPnrMain) throws Exception {
                        if (abtPnrMain.getStatus() == AbtPnrMain.STATUS_INIT) {
                            return abtPnrMain;
                        } else {
                            return null;
                        }
                    }
                });
        final Map<String, AbtPnrMain> availablePnrMap = new HashMap<String, AbtPnrMain>();
        for (AbtPnrMain availablePnrMain : availablePnrMains) {
            availablePnrMap.put(availablePnrMain.getPnr(), availablePnrMain);
        }

        // 校验待取消pnr是否在数据表中
        List<AbtPnrMain> cancelPnrMains = new ArrayList<AbtPnrMain>();
        for (String pnr : pnrs) {
            AbtPnrMain pnrItem = availablePnrMap.get(pnr);
            if (pnrItem != null) {
                cancelPnrMains.add(pnrItem);
            } else {
                throw new BizzException(CancelEx.CANCEL_FAIL, "取消下单数据不正确。" +
                        CancelWorkUtils.buildPnrMessage(pnrMains, pnrs));
            }
        }
        ProcCancelData procCancelData = new ProcCancelData();
        procCancelData.setReqCancel(request);
        procCancelData.setAvailablePnrMains(availablePnrMains);

        int vendorId = abtBookingRequest.getVendorId();
        List<CancelOrderGroup> cancelOrderGroups = cancelProcessorFactory
                .findProcessor(vendorId).checkCancelRequest(availablePnrMains, pnrs, delPassengerNames);
        procCancelData.setCancelOrderGroups(cancelOrderGroups);
        // sortGroup(cancelOrderGroups);

        procCancelData.setPnrs(cancelPnrMains);
        // sortMain(cancelPnrMains);

        procCancelData.setAbtBookingRequest(abtBookingRequest);
        return procCancelData;
    }

}
