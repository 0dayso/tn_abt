package com.tuniu.abt.service.cachework;

import com.tuniu.abt.intf.constants.RedisConstants;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightAirCompanyFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightCabinFDPrice;
import com.tuniu.adapter.flightTicket.vo.inquiry.FlightFDPriceRes;
import com.tuniu.operation.platform.base.text.StringUtils;
import com.tuniu.vla.base.cache.KeyBlockedCacheWorker;
import com.tuniu.vla.base.redis.JedisTemplate;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FD价格缓存
 *
 * Created by chengyao on 2016/3/11.
 */
@Component
@ManagedResource(objectName= "com.tuniu.abt:type=CacheWorker,name=FdPriceCacheWorker")
public class FdPriceCacheWorker extends KeyBlockedCacheWorker<BigDecimal, FdPriceParam> {

    private static final String HASH_KEY_HOLDER = "_holder";

    @Resource
    private TravelSkyInterface travelSkyInterface;

    @Override
    protected BigDecimal read(FdPriceParam param) {
        String key = buildKey(param);
        String fieldName = buildHashKey(param);

        // 缓存获取到数据，直接返回
        String fdPriceString = jedisTemplate.hget(key, fieldName);
        if (fdPriceString != null) {
            return new BigDecimal(fdPriceString);
        }

        // key不存在，需回源
        if (!jedisTemplate.exists(key)) {
            return null;
        }

        // hold key存在，设定插入时间60后再次回源
        String holdTime = jedisTemplate.hget(key, HASH_KEY_HOLDER);
        if (holdTime != null) {
            try {
                long inTime = Long.parseLong(holdTime);
                // 当再次获取到空对象时，比较插入的时间，如果过了60秒后，再次回源
                if (System.currentTimeMillis() - inTime > 60 * 1000) {
                    return null;
                }
            } catch (Exception ex) {
                return null;
            }
        }

        return new BigDecimal(0);
    }

    @Override
    protected BigDecimal write(FdPriceParam param) {
        final String key = buildKey(param);
        FdPriceParam paramQuery = CommonUtils.transform(param, FdPriceParam.class);
        paramQuery.setAirlineCompany(null); // 查询所有航班
        FlightFDPriceRes flightFDPriceRes = travelSkyInterface.queryFDPrice(paramQuery);

        // 航司:舱位 = 价格
        final Map<String, String> insertMap = new HashMap<String, String>();

        BigDecimal result = fillAndFindPrice(param, flightFDPriceRes, insertMap);

        if (insertMap.size() == 0) {
            // 插入时间
            insertMap.put(HASH_KEY_HOLDER, String.valueOf(System.currentTimeMillis()));
        }

        jedisTemplate.execute(new JedisTemplate.PipelineActionNoResult() {
            @Override
            public void action(Pipeline pipeline) {
                pipeline.del(key);
                pipeline.hmset(key, insertMap);
                pipeline.expire(key, RedisConstants.DAY_3);
            }
        });

        return result;
    }

    @Override
    protected BigDecimal beforeReturn(BigDecimal value) {
        if (value != null && value.doubleValue() > 0) {
            return value;
        } else {
            return null;
        }
    }

    private String buildKey(FdPriceParam input) {
        String orgCityIataCode = input.getOrgCityIataCode();
        String dstCityIataCode = input.getDstCityIataCode();
        String departureDate = input.getDepartureDate();
        return RedisConstants.CACHE_FD_IBE + orgCityIataCode + "_" + dstCityIataCode + "_" + departureDate;
    }

    private String buildHashKey(FdPriceParam input) {
        return input.getAirlineCompany() + ":" + input.getCabin();
    }


    private BigDecimal fillAndFindPrice(FdPriceParam param, FlightFDPriceRes flightFDPriceRes, Map<String, String> insertMap) {
        BigDecimal result = null;
        if (flightFDPriceRes == null) {
            return null;
        }
        List<FlightAirCompanyFDPrice> prices = flightFDPriceRes.getFdPriceList();
        for (FlightAirCompanyFDPrice price : prices) {
            String airlineCompany = price.getAirCompanyIataCode();
            if (StringUtils.isEmpty(airlineCompany)) {
                continue;
            }
            for (FlightCabinFDPrice flightCabinFDPrice : price.getCabinFDPricelist()) {
                if (StringUtils.isEmpty(flightCabinFDPrice.getSubSeatCode())) {
                    continue;
                }

                String findKey = buildHashKey(param);
                String insertKey = airlineCompany + ":" + flightCabinFDPrice.getSubSeatCode();
                if (findKey.equals(insertKey)) {
                    result = BigDecimal.valueOf(flightCabinFDPrice.getFdPrice());
                }

                insertMap.put(airlineCompany + ":" + flightCabinFDPrice.getSubSeatCode(),
                        flightCabinFDPrice.getFdPrice().toString());
            }
        }
        return result;
    }
}
