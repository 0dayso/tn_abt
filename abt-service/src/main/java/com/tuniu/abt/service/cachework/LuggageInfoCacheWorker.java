package com.tuniu.abt.service.cachework;

import com.tuniu.abt.intf.constants.RedisConstants;
import com.tuniu.abt.intf.tsp.TspFltInterface;
import com.tuniu.abt.intf.tsp.dto.flt.LuggageQueryRequest;
import com.tuniu.adapter.flightTicket.vo.inquiry.AirportSeatClassLuggageInfo;
import com.tuniu.vla.base.cache.KeyBlockedCacheWorker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanlugang on 2016/6/1.
 */
@Service
@ManagedResource(objectName= "com.tuniu.abt:type=CacheWorker,name=LuggageInfoCacheWorker")
public class LuggageInfoCacheWorker extends KeyBlockedCacheWorker<List<AirportSeatClassLuggageInfo>, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LuggageInfoCacheWorker.class);

    private static final String EMPTY_HOLDER = "_empty_holder";

    @Resource
    private TspFltInterface tspFltInterface;

    @Override
    protected List<AirportSeatClassLuggageInfo> read(String airlineCompanyIataCode) {
        String key = jedisTemplate.key(RedisConstants.LUGGAGE_INFO_KEY, airlineCompanyIataCode);
        return jedisTemplate.hvalsBin(key, AirportSeatClassLuggageInfo.class);
    }

    @Override
    protected List<AirportSeatClassLuggageInfo> write(String airlineCompanyIataCode) {
        String key = jedisTemplate.key(RedisConstants.LUGGAGE_INFO_KEY, airlineCompanyIataCode);
        LuggageQueryRequest request = new LuggageQueryRequest();
        request.setAirlineCompanyIataCode(airlineCompanyIataCode);
        List<AirportSeatClassLuggageInfo> list = null;
        try {
            list = tspFltInterface.queryLuggageInfo(request).getRows();
        } catch (Exception e) {
            LOGGER.error("VLA.SUP.FlightOperateAPI.queryLuggageInfo: 查询行李额信息, {}", e.getMessage(), e);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (AirportSeatClassLuggageInfo item : list) {
                map.put(String.valueOf(item.getId()), item);
            }
            jedisTemplate.hmsetBin(key, map);
            jedisTemplate.expire(key, RedisConstants.DAYTIME);
        } else {
            jedisTemplate.hset(key, EMPTY_HOLDER, StringUtils.EMPTY);
            jedisTemplate.expire(key, RedisConstants.DAYTIME);
        }
        return list;
    }

    public void clearCache(String airlineCompanyIataCode) {
        String key = jedisTemplate.key(RedisConstants.LUGGAGE_INFO_KEY, airlineCompanyIataCode);
        jedisTemplate.del(key);
    }
}
