package com.tuniu.aop.unittest.common;

import com.ibm.icu.math.BigDecimal;
import com.tuniu.vla.base.redis.JedisTemplate;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/4/20.
 */
@ContextConfiguration(locations = { "classpath*:spring/spring-redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private JedisTemplate jedisTemplate;

    @Test
    public void s1() {
        String key1 = "aop:main:policy:info:477241798";
        final String key2 = "aop:main:person:info:10836921";
        long start = System.currentTimeMillis();
        int count = 10000;
        int t2 = 0;
        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                batchGet(key2, 100); t2++;
            }
        }
        long dur = System.currentTimeMillis() - start;
        System.out.println(t2);
        System.out.println("dur:" + dur + ", tps:" + new BigDecimal(count).multiply(new BigDecimal(1000)).divide(new BigDecimal(dur)).toString());
    }


    private void batchGet(final String key2, final int count) {
        jedisTemplate.execute(new JedisTemplate.PipelineActionNoResult() {
            @Override
            public void action(Pipeline Pipeline) {
                for (int i = 0; i < count; i++)
                    Pipeline.get(key2);
            }
        });
    }
}
