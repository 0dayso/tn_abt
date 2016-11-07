package com.tuniu.abt.service.travelsky.ibe.module;

import com.travelsky.ibe.client.pnr.PATResult;
import com.travelsky.ibe.client.pnr.PNREI;
import com.travelsky.ibe.client.pnr.RTResult;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.dto.PatByPnrReq;
import com.tuniu.abt.service.travelsky.dto.PatByPnrRes;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.service.travelsky.ibe.converter.IbePataConverter;
import com.tuniu.adapter.flightTicket.vo.connector.PatRequest;
import com.tuniu.adapter.flightTicket.vo.connector.PataSecondRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/3/14.
 */
@Service
public class IbePataWrapModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(IbePataWrapModule.class);

    @Resource
    private IbeInterfaceService ibeInterfaceService;

    /**
     * Description: 按pnr计算运价<br>
     *
     * @param patByPnrReq
     * @return
     */
    public PatByPnrRes<PATResult> patPriceByPnrCalculate(PatByPnrReq patByPnrReq) {
        PatByPnrRes patByPnrRes = new PatByPnrRes();
        patByPnrRes.setPatByPnrReq(patByPnrReq);
        doPat(patByPnrRes, false);
        if (patByPnrReq.isHasBaby()) {
            doPat(patByPnrRes, true);
        }
        return patByPnrRes;
    }

    /**
     * Description: 按pnr存储运价<br>
     *
     * @param patByPnrRes
     */
    public void patPriceByPnrStore(PatByPnrRes<PATResult> patByPnrRes) {
        if (null == patByPnrRes.getPatByPnrReq()) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX, "PNR存储运价请求中，patByPnrReq为空");
        }
        doPatSecond(patByPnrRes, false);
        if (patByPnrRes.getPatByPnrReq().isHasBaby()) {
            doPatSecond(patByPnrRes, true);
        }
    }

    /**
     * 按pnr计算运价
     * @param patByPnrRes
     * @param babyFlag
     */
    private void doPat(PatByPnrRes<PATResult> patByPnrRes, boolean babyFlag) {
        PatRequest patRequest = null;
        PatByPnrReq patByPnrReq = patByPnrRes.getPatByPnrReq();
        if (babyFlag) {
            patRequest = new PatRequest(patByPnrReq.getPnrNo(), patByPnrReq.getFareType(),
                    "IN", 1, false, null, null);
        } else {
            patRequest = new PatRequest(patByPnrReq.getPnrNo(), patByPnrReq.getFareType(),
                    patByPnrReq.getPsgType(), 1, false, null, patByPnrReq.getClientCode());
        }
        try {
            PATResult res = ibeInterfaceService.doPat(patRequest);
            if (null == res || res.farenumber <= 0) {
                throw new BizzException(BizzEx.IBE_INTF_EX, "Pat查询价格为空");
            }
            PnrFareItem pnrFareItem = IbePataConverter.convert2PnrFareItem(res,
                    PatByPnrReq.FARE_TYPE_FD.equals(patByPnrReq.getFareType()));
            if (pnrFareItem == null) {
                throw new BizzException(BizzEx.IBE_INTF_EX, "Pat查询价格为空，未获取到可用价格");
            }
            if (babyFlag) {
                patByPnrRes.setBabyFareItem(pnrFareItem);
                patByPnrRes.setBabyPatRes(res);
            } else {
                patByPnrRes.setFareItem(pnrFareItem);
                patByPnrRes.setPatRes(res);
            }
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "PAT运价出现异常.", e);
        }
    }

    /**
     * 按pnr存储运价
     * @param patByPnrRes
     * @param babyFlag
     */
    private void doPatSecond(PatByPnrRes<PATResult> patByPnrRes, boolean babyFlag) {
        PatByPnrReq patByPnrReq = patByPnrRes.getPatByPnrReq();
        if (!babyFlag && null == patByPnrRes.getPatRes()) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX, "PNR存储运价请求中，patRes为空");
        }
        if (babyFlag && patByPnrRes.getBabyPatRes() == null) {
            throw new BizzException(BizzEx.IBE_INTF_REQ_PARAM_EX, "PNR存储婴儿运价请求中，婴儿patRes为空");
        }
        PataSecondRequest pataSecondRequest = null;
        try {
            if (babyFlag) {
                deleteEisInPnr(patByPnrReq.getPnrNo());
                pataSecondRequest = new PataSecondRequest(patByPnrRes.getBabyPatRes(), patByPnrReq.getPnrNo(),
                        "IN", 0, 0, null, null);
            } else {
                pataSecondRequest = new PataSecondRequest(patByPnrRes.getPatRes(), patByPnrReq.getPnrNo(),
                        patByPnrReq.getPsgType(), patByPnrRes.getFareItem().getRph(), 0, null, patByPnrReq.getClientCode());
            }
            ibeInterfaceService.doPataSecond(pataSecondRequest);
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_INTF_EX, "doPatSecond运价出现异常.", e);
        }
    }

    private void deleteEisInPnr(String pnrNo) throws Exception {
        RTResult result = ibeInterfaceService.rtPnr(pnrNo);
        // 统计FN组 FC组 FP组 EI组index个数
        int total = result.getEisCount();
        int[] a = new int[total];
        int count = 0;
        if (result.getEisCount() > 0) {
            List<PNREI> eis = result.getEis();
            for (PNREI ei : eis) {
                a[count] = ei.getIndex();
                count++;
            }
            // 删除EI项
            ibeInterfaceService.deletePnrItem(pnrNo, a);
        }
    }



}
