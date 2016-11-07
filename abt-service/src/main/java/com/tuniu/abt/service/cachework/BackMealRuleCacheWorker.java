package com.tuniu.abt.service.cachework;

import com.tuniu.abt.dao.AbtBackMealDao;
import com.tuniu.abt.intf.constants.RedisConstants;
import com.tuniu.abt.intf.entity.AbtBackMealRule;
import com.tuniu.vla.base.cache.KeyBlockedCacheWorker;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 按规则ID缓存的规则CacheWorker
 * Created by chengyao on 2016/3/31.
 */
@Service
@ManagedResource(objectName= "com.tuniu.abt:type=CacheWorker,name=BackMealRuleCacheWorker")
public class BackMealRuleCacheWorker extends KeyBlockedCacheWorker<AbtBackMealRule, Long> {

    @Resource
    private AbtBackMealDao abtBackMealDao;

    @Override
    protected AbtBackMealRule read(Long id) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_RULE_KEY, id);
        return jedisTemplate.getBin(key, AbtBackMealRule.class);
    }

    @Override
    protected AbtBackMealRule write(Long id) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_RULE_KEY, id);

        AbtBackMealRule abtBackMealRule = abtBackMealDao.findRule(id);
        if (abtBackMealRule == null || abtBackMealRule.getId() == null) {
            abtBackMealRule = new AbtBackMealRule();
        }
        jedisTemplate.setexBin(key, abtBackMealRule, RedisConstants.DAYTIME);
        return abtBackMealRule;
    }

    @Override
    protected AbtBackMealRule beforeReturn(AbtBackMealRule rule) {
        if (rule.getId() != null) {
            return rule;
        } else {
            return null;
        }
    }

    public void clearCache(Long id) {
        String key = jedisTemplate.key(RedisConstants.BACK_MEAL_RULE_KEY, id);
        jedisTemplate.del(key);
    }
}
