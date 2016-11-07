package com.tuniu.abt.service.cancel;

import com.tuniu.abt.intf.dto.book.response.PassengerInfo;
import com.tuniu.abt.intf.dto.cancel.CancelOrderGroup;
import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;

import java.util.List;
import java.util.Map;

/**
 * 取消占位处理服务接口，根据供应商有不同的处理逻辑
 * Created by chengyao on 2016/4/11.
 */
public interface CancelProcessor {

    /**
     * 执行取消操作
     * @param groups 需要取消的数据
     * @return 取消结果
     */
    List<CancelResult> cancelPnrs(List<CancelOrderGroup> groups);

    /**
     * 检查请求并构造要取消的中间数据
     * @param availablePnrMains 所有有效的pnrMain
     * @param pnrs 要取消的pnr
     * @param passengerInfos 要取消的乘客
     * @return 取消中间数据
     */
    List<CancelOrderGroup> checkCancelRequest(List<AbtPnrMain> availablePnrMains, List<String> pnrs,
            Map<String, List<PassengerInfo>> passengerInfos);

}
