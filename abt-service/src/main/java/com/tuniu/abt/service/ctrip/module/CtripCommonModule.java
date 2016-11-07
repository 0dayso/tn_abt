package com.tuniu.abt.service.ctrip.module;

import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.ArrayOfOrderInfoEntity;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OAEViewOrderListDetailsResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderDetailResponse;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.orderDetail.OrderInfoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/2/25.
 */
@Service
public class CtripCommonModule {

    @Resource
    private CtripOrderModule ctripOrderModule;

    /**
     * Description: 国内机票(携程订单详情查询)底层实现<br>
     *
     * @param subOrderIdList
     * @return <br>
     * @author lanlugang<br>
     * @date 2015-8-10
     * @taskId PTICKET-1410<br>
     */
    public List<OrderInfoEntity> queryCtripOrderDetail(List<String> subOrderIdList) {
        try {
            // 组装请求,调携程接口获取响应结果
            OrderDetailResponse orderDetailResponse = ctripOrderModule.viewOrderListDetails(subOrderIdList);
            OAEViewOrderListDetailsResponse orderListDetailResponse = orderDetailResponse.getOrderListDetailResponse();
            // 返回响应结果
            if (orderListDetailResponse.isResult()) {
                ArrayOfOrderInfoEntity orderInformation = orderListDetailResponse.getOrderInformation();
                if (null != orderInformation) {
                    List<OrderInfoEntity> orderInfoEntityList = orderInformation.getOrderInfoEntity();
                    if (CollectionUtils.isNotEmpty(orderInfoEntityList) && subOrderIdList.size() == orderInfoEntityList
                            .size()) {
                        return orderInfoEntityList;
                    } else {
                        throw new BizzException(BizzEx.CTRIP_INTF_RESULT_CHECK,
                                "响应报文中订单实体数据缺失(查询不到订单详情).");
                    }
                } else {
                    throw new BizzException(BizzEx.CTRIP_INTF_RESULT_CHECK,
                            "响应报文中订单信息为空.");
                }
            } else {
                throw new BizzException(BizzEx.CTRIP_INTF_RESULT_CHECK,
                        "响应结果=FALSE,原因=" + orderListDetailResponse.getResultMessage());
            }
        } catch (Exception e) {
            throw new BizzException(BizzEx.CTRIP_INTF_EX,
                    "携程订单查询,异常：" + e.getMessage(), e);
        }
    }


    public int convertPassengerType(String ageType) {
        int passengerType = 0;
        //成人
        if ("ADU".equals(ageType)) {
            passengerType = 1;
        } else if ("BAB".equals(ageType)) {
            passengerType = 3;
        } else if ("CHI".equals(ageType)) {
            passengerType = 2;
        }
        return passengerType;
    }




}
