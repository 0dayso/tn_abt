package com.tuniu.abt.service.book.aop;

import com.tuniu.abt.intf.dto.book.main.*;
import com.tuniu.abt.service.aop.AopPolicyInquiryService;
import com.tuniu.abt.service.book.travelsky.BookTravelSkyWrapper;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.adapter.flightTicket.vo.policy.aop.FeeRate;
import com.tuniu.adapter.flightTicket.vo.policy.aop.PolicyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookAopService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookAopService.class);

    @Resource
    private BookTravelSkyWrapper bookTravelSkyWrapper;

    public BookingReply occupy4Aop(BookingData bookingData) throws Exception {
        // 1.生成pnr
        BookingReply bookingReply = new BookingReply();
        List<PnrInfo> pnrInfos = bookTravelSkyWrapper.creatAndCheckPnrs(bookingData);
        bookingReply.getPnrInfos().addAll(pnrInfos);
        // 2.计算运价
        bookTravelSkyWrapper.doPataPriceByPnr(pnrInfos, bookingData.getSystemId(), bookingData.getVendorId());
        // 3. 更新成本价
        updateCostPriceByPolicy(pnrInfos);
        return bookingReply;
    }

    /**
     * 根据政策更新成本价
     * @param pnrInfos
     */
    private void updateCostPriceByPolicy(List<PnrInfo> pnrInfos) {
        Map<String, PolicyDetail> policyDetailMap = new HashMap<String, PolicyDetail>();
        for (PolicyDetail policyDetail : BookWorkSupport.getAopPolicyDetailList()) {
            policyDetailMap.put(String.valueOf(policyDetail.getPolicyId()), policyDetail);
        }
        for (PnrInfo pnrInfo : pnrInfos) {
            PolicyDetail policyDetail = policyDetailMap.get(String.valueOf(pnrInfo.getPolicyId()));
            if (null == policyDetail) {
                continue;
            }
            Map<String, FeeRate> feeRateMap = new HashMap<String, FeeRate>();
            for (FeeRate feeRate : policyDetail.getFeeRateList()) {
                feeRateMap.put(feeRate.getPassengerType().value(), feeRate);
            }

            for (PnrPrice price : pnrInfo.getPrices()) {
                FeeRate feeRate = feeRateMap.get(BookPassengerType.getPsgType(price.getPriceType()));
                if (null == feeRate) {
                    continue;
                }
                float costPrice = AopPolicyInquiryService.calulateCostPrice(price.getFloorPrice(), feeRate);
                price.setFloorPrice(costPrice);
                price.setSalePrice(costPrice);
                price.setBackBate(feeRate.getCommisionPoint());
                price.setBackCash(feeRate.getCommisionMoney());
                price.setInvoiceCost(feeRate.getInvoiceCost());
            }
        }
    }

}
