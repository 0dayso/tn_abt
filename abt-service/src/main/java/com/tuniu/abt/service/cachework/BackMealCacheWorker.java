package com.tuniu.abt.service.cachework;

import com.tuniu.abt.dbservice.AbtBackMealDbService;
import com.tuniu.abt.intf.constants.RedisConstants;
import com.tuniu.abt.intf.dto.backmeal.BackMealRedisItem;
import com.tuniu.vla.base.cache.KeyBlockedCacheWorker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按航司缓存规则列表
 * Created by chengyao on 2016/3/31.
 */
@Service
@ManagedResource(objectName= "com.tuniu.abt:type=CacheWorker,name=BackMealCacheWorker")
public class BackMealCacheWorker extends KeyBlockedCacheWorker<List<BackMealRedisItem>, String> {

    private static final String EMPTY_HOLDER = "_empty_holder";
    @Resource
    private AbtBackMealDbService abtBackMealDbService;

    @Override
    protected List<BackMealRedisItem> read(String airlineCompany) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_KEY, airlineCompany);
        return jedisTemplate.hvalsBin(key, BackMealRedisItem.class);
    }

    @Override
    protected List<BackMealRedisItem> write(String airlineCompany) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_KEY, airlineCompany);
        List<BackMealRedisItem> list = abtBackMealDbService.findByAirlineCompany(airlineCompany);
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (BackMealRedisItem item : list) {
                map.put(item.getId().toString(), item);
            }
            jedisTemplate.hmsetBin(key, map);
            jedisTemplate.expire(key, RedisConstants.DAYTIME);
        } else {
            jedisTemplate.hset(key, EMPTY_HOLDER, StringUtils.EMPTY);
            jedisTemplate.expire(key, RedisConstants.DAYTIME);
        }
        return list;
    }

    public void clearCache(String airlineCompany) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_KEY, airlineCompany);
        jedisTemplate.del(key);
    }

}
