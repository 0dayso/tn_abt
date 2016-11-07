package com.tuniu.abt.service.cancel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.tuniu.abt.intf.dto.aop.airline.AopReqCancel;
import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.dto.cancel.ProcCancelData;
import com.tuniu.abt.intf.dto.cancel.ReqCancel;
import com.tuniu.abt.intf.dto.cancel.ReqCancelDetail;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.service.cancel.CancelDataSupport;
import com.tuniu.abt.utils.CommonUtils;

@Service
public class AopAirlineCancelProcessor extends AbstractCancelProcessor{

	
	@Override
	public List<CancelResult> cancelPnrs(List<CancelOrderGroup> groups) {
		 
		List<CancelResult> cancelResults = new ArrayList<CancelResult>();
		 ProcCancelData procCancelData = CancelDataSupport.getData();
		 
		 ReqCancel reqCancel =  procCancelData.getReqCancel();
		 AopReqCancel aopReqCancel = CommonUtils.transform(reqCancel, AopReqCancel.class);
		 
		 
		 
		 
		 
		 
        return cancelResults;
	}

	@Override
	public List<CancelOrderGroup> checkCancelRequest(
			List<AbtPnrMain> availablePnrMains, List<String> pnrs,
			Map<String, List<PassengerInfo>> passengerInfos) {
		// 航信每个group标识为一个pnr
        Map<String, CancelOrderGroup> result = Maps.newHashMap();
        for (AbtPnrMain availablePnrMain : availablePnrMains) {
            String orderId = availablePnrMain.getOutOrderId();
            // result数据处理。
            if (!pnrs.contains(orderId)) { //过滤不需要取消的订单
                continue;
            }
            if (!result.containsKey(orderId)) { // 按pnr保存cancelORderGroup
                CancelOrderGroup cancelOrderGroup = new CancelOrderGroup();
                cancelOrderGroup.setMainOrderId(orderId);
                cancelOrderGroup.setTicketingCarrier(availablePnrMain.getTicketingCarrier());
                result.put(orderId, cancelOrderGroup);
            }
        }
        return new ArrayList<CancelOrderGroup>(result.values());
	}

}
