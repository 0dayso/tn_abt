package com.tuniu.abt.service.cachework;

import com.tuniu.abt.intf.constants.RedisConstants;
import com.tuniu.abt.intf.tsp.TspDfpInterface;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailFligthSegVo;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.DetailResponseVo;
import com.tuniu.adapter.flightTicket.vo.dfpPolicy.PolicyDetailRequestVo;
import com.tuniu.vla.base.cache.KeyBlockedCacheWorker;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DFP政策缓存
 *
 * Created by chengyao on 2016/1/16.
 */
@Component
@ManagedResource(objectName= "com.tuniu.abt:type=CacheWorker,name=DfpPolicyDetailCacheWorker")
public class DfpPolicyDetailCacheWorker extends KeyBlockedCacheWorker<DetailResponseVo, PolicyDetailRequestVo> {

    @Resource
    private TspDfpInterface tspDfpInterface;

    @Override
    protected DetailResponseVo read(PolicyDetailRequestVo policyDetailRequestVo) {
        return jedisTemplate.getBin(buildKey(policyDetailRequestVo), DetailResponseVo.class);
    }

    @Override
    protected DetailResponseVo write(PolicyDetailRequestVo policyDetailRequestVo) {
        DetailResponseVo detailResponseVo = tspDfpInterface.detail(policyDetailRequestVo);
        if (detailResponseVo == null) {
            detailResponseVo = new DetailResponseVo();
        }
        jedisTemplate.setexBin(buildKey(policyDetailRequestVo), detailResponseVo, RedisConstants.HOUR_2);
        return detailResponseVo;
    }

    @Override
    protected DetailResponseVo beforeReturn(DetailResponseVo detailResponseVo) {
        // TODO 确认下这里接口返回的数据是不是actionCode不为null
        if (detailResponseVo != null && detailResponseVo.getId() != null) {
            return detailResponseVo;
        }
        return null;
    }

    private String buildKey(PolicyDetailRequestVo policyDetailRequestVo) {
        StringBuilder buffer = new StringBuilder();
        // a:<actionCode>:[<出发城市>:<目的城市>:<团期>:<航班>:<舱等>: .n.]:<政策id>
        buffer.append(RedisConstants.DFP_POLICY);
        buffer.append(":");
        buffer.append(policyDetailRequestVo.getActionCode());
        buffer.append(":");

        if (policyDetailRequestVo.getFlightSegList().size() == 1) {
            policyDetailRequestVo.setJourneyType(0);
        } else if (policyDetailRequestVo.getFlightSegList().size() == 2) {
            policyDetailRequestVo.setJourneyType(1);
        }
        for (DetailFligthSegVo inquiryFlightSegVo : policyDetailRequestVo.getFlightSegList()) {
            String departureParseDate = inquiryFlightSegVo.getDepartureDate().trim().replaceAll("-", "");
            buffer.append(inquiryFlightSegVo.getOrgCityIataCode()).append("_")
                    .append(inquiryFlightSegVo.getDstCityIataCode()).append("_").append(departureParseDate).append("_")
                    .append(inquiryFlightSegVo.getFlightNo()).append("_").append(inquiryFlightSegVo.getSeatCode())
                    .append("_");
        }
        buffer = buffer.deleteCharAt(buffer.length() - 1);
        buffer.append(":");
        buffer.append(policyDetailRequestVo.getPolicyId());
        return buffer.toString();
    }
}
