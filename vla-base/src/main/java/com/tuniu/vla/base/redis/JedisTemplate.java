/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.tuniu.vla.base.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

import java.io.Serializable;
import java.util.*;

/**
 * JedisTemplate 提供了一个template方法，负责对Jedis连接的获取与归还。
 * JedisAction<T> 和 JedisActionNoResult两种回调接口，适用于有无返回值两种情况。
 * PipelineAction 与 PipelineActionResult两种接口，适合于pipeline中批量传输命令的情况。
 *
 * 同时提供一些JedisOperation中定义的 最常用函数的封装, 如get/set/zadd等。
 */
public class JedisTemplate {

    private static final int LOCK_EXPIRE_TIME = 10;

    private static Logger logger = LoggerFactory.getLogger(JedisTemplate.class);

    private JedisPool jedisPool;

    public JedisTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * Execute with a call back action with result.
     */
    public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedisAction.action(jedis);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    /**
     * Execute with a call back action without result.
     */
    public void execute(JedisActionNoResult jedisAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedisAction.action(jedis);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    /**
     * Execute with a call back action with result in pipeline.
     */
    public <T> List<T> execute(PipelineAction<T> pipelineAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            return pipelineAction.action(pipeline);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    /**
     * Execute with a call back action without result in pipeline.
     */
    public void execute(PipelineActionNoResult pipelineAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            pipelineAction.action(pipeline);
            pipeline.sync();
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    /**
     * Return the internal JedisPool.
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * Remove the specified keys. If a given key does not exist no operation is
     * performed for this key.
     *
     * return false if one of the key is not exist.
     */
    public Boolean del(final String... keys) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.del(keys) == keys.length ? true : false;
            }
        });
    }

    /**
     * 删除一个key
     * @param key 第一个key
     * @param flags key的组成元素
     * @return 是否成功
     */
    public Boolean delOne(String key, Object... flags) {
        return del(key(key, flags));
    }

    public void flushDB() {
        execute(new JedisActionNoResult() {

            @Override
            public void action(Jedis jedis) {
                jedis.flushDB();
            }
        });
    }

    public long expire(final String key, final int seconds) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    // / Common Actions ///

    public Boolean exists(final String key) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    /**
     * Get the value of the specified key. If the key does not exist null is
     * returned. If the value stored at key is not a string an error is returned
     * because GET can only handle string values.
     */
    public String get(final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public <T> T getBin(final String key, final Class<T> type) {
        return execute(new JedisAction<T>() {
            @Override
            public T action(Jedis jedis) {
                return jsonDecode(jedis.get(key), type);
            }
        });
    }

    public <T> List<T> getBinList(final String key, final Class<T> type) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                return jsonDecodeArray(jedis.get(key), type);
            }
        });
    }

    /**
     * Get the value of the specified key as Long.If the key does not exist null is returned.
     */
    public Long getAsLong(final String key) {
        String result = get(key);
        return result != null ? Long.valueOf(result) : null;
    }

    // / String Actions ///

    /**
     * Get the value of the specified key as Integer.If the key does not exist null is returned.
     */
    public Integer getAsInt(final String key) {
        String result = get(key);
        return result != null ? Integer.valueOf(result) : null;
    }

    /**
     * Get the values of all the specified keys. If one or more keys dont exist
     * or is not of type String, a 'nil' value is returned instead of the value
     * of the specified key, but the operation never fails.
     */
    public List<String> mget(final String... keys) {
        return execute(new JedisAction<List<String>>() {

            @Override
            public List<String> action(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
    }

    public <T extends Serializable> List<T> mgetBin(final Class<T> type, final String... keys) {
        if (keys == null || keys.length == 0) {
            return Collections.emptyList();
        }
        return execute(new PipelineAction<T>() {
            @Override
            public List<T> action(Pipeline pipeline) {
                for (String key : keys) {
                    pipeline.get(key);
                }
                List<Object> responses = pipeline.syncAndReturnAll();
                List<T> result = new ArrayList<T>(responses.size());
                for (Object response : responses) {
                    String resp = (String) response;
                    result.add(resp == null ? null : jsonDecode(resp, type));
                }
                return result;
            }
        });
    }

    /**
     * Set the string value as value of the key.
     * The string can't be longer than 1073741824 bytes (1 GB).
     */
    public void set(final String key, final String value) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.set(key, value);
            }
        });
    }

    public void setBin(final String key, final Object object) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.set(key, jsonEncode(object));
            }
        });
    }

    // seconds
    public void msetBin(final Map<String, Object> hash, final int time) {
        execute(new PipelineActionNoResult() {
            @Override
            public void action(Pipeline pipeline) {
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    pipeline.setex(entry.getKey(), time, jsonEncode(entry.getValue()));
                }
            }
        });
    }

    /**
     * The command is exactly equivalent to the following group of commands: {@link #set(String, String) SET} +
     * {@link #expire(String, int) EXPIRE}.
     * The operation is atomic.
     */
    public void setex(final String key, final String value, final int seconds) {
        execute(new JedisActionNoResult() {

            @Override
            public void action(Jedis jedis) {
                jedis.setex(key, seconds, value);
            }
        });
    }

    public void setexBin(final String key, final Object object, final int seconds) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.setex(key, seconds, jsonEncode(object));
            }
        });
    }

    /**
     * SETNX works exactly like {@link #setnx(String, String) SET} with the only
     * difference that if the key already exists no operation is performed.
     * SETNX actually means "SET if Not eXists".
     *
     * return true if the key was set.
     */
    public Boolean setnx(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.setnx(key, value) == 1 ? true : false;
            }
        });
    }

    /**
     * The command is exactly equivalent to the following group of commands: setex sexnx.
     * The operation is atomic.
     */
    public Boolean setnxex(final String key, final String value, final int seconds) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                String result = jedis.set(key, value, "NX", "EX", seconds);
                return JedisUtils.isStatusOk(result);
            }
        });
    }

    /**
     * GETSET is an atomic set this value and return the old value command. Set
     * key to the string value and return the old value stored at key. The
     * string can't be longer than 1073741824 bytes (1 GB).
     */
    public String getSet(final String key, final String value) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }

    /**
     * Increment the number stored at key by one. If the key does not exist or
     * contains a value of a wrong type, set the key to the value of "0" before
     * to perform the increment operation.
     * <p>
     * INCR commands are limited to 64 bit signed integers.
     * <p>
     * Note: this is actually a string operation, that is, in Redis there are not "integer" types. Simply the string
     * stored at the key is parsed as a base 10 64 bit signed integer, incremented, and then converted back as a string.
     *
     * @return Integer reply, this commands will reply with the new value of key
     *         after the increment.
     */
    public Long incr(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public Long incrBy(final String key, final long increment) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.incrBy(key, increment);
            }
        });
    }

    public Double incrByFloat(final String key, final double increment) {
        return execute(new JedisAction<Double>() {
            @Override
            public Double action(Jedis jedis) {
                return jedis.incrByFloat(key, increment);
            }
        });
    }

    /**
     * Decrement the number stored at key by one. If the key does not exist or
     * contains a value of a wrong type, set the key to the value of "0" before
     * to perform the decrement operation.
     */
    public Long decr(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    public Long decrBy(final String key, final long decrement) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.decrBy(key, decrement);
            }
        });
    }

    /**
     * If key holds a hash, retrieve the value associated to the specified
     * field.
     * <p>
     * If the field is not found or the key does not exist, a special 'nil' value is returned.
     */
    public String hget(final String key, final String fieldName) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.hget(key, fieldName);
            }
        });
    }

    public List<String> hmget(final String key, final String... fieldsNames) {
        return execute(new JedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.hmget(key, fieldsNames);
            }
        });
    }

    public <T> T hgetBin(final String key, final String fieldName, final Class<T> type) {
        return execute(new JedisAction<T>() {
            @Override
            public T action(Jedis jedis) {
                return jsonDecode(jedis.hget(key, fieldName), type);
            }
        });
    }

    public Map<String, String> hgetAll(final String key) {
        return execute(new JedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    // / Hash Actions ///

    public void hmsetBin(final String key, final Map<String, Object> hash) {
        execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                Map<String, String> values = new HashMap<String, String>();
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    values.put(entry.getKey(), jsonEncode(entry.getValue()));
                }
                return jedis.hmset(key, values);
            }
        });
    }

    public <T> Map<String, T> hgetAllBin(final String key, final Class<T> type) {
        Map<String, String> result = execute(new JedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
        Map<String, T> resultList = new LinkedHashMap<String, T>();
        for (Map.Entry<String, String> entry : result.entrySet()) {
            resultList.put(entry.getKey(), jsonDecode(entry.getValue(), type));
        }
        return resultList;
    }

    public void hset(final String key, final String fieldName, final String value) {
        execute(new JedisActionNoResult() {

            @Override
            public void action(Jedis jedis) {
                jedis.hset(key, fieldName, value);
            }
        });
    }

    public void hmset(final String key, final Map<String, String> map) {
        execute(new JedisActionNoResult() {

            @Override
            public void action(Jedis jedis) {
                jedis.hmset(key, map);
            }
        });

    }

    public void hsetBin(final String key, final String fieldName, final Object value) {
        execute(new JedisActionNoResult() {

            @Override
            public void action(Jedis jedis) {
                jedis.hset(key, fieldName, jsonEncode(value));
            }
        });
    }

    public Boolean hsetnx(final String key, final String fieldName, final String value) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.hsetnx(key, fieldName, value) == 1 ? true : false;
            }
        });
    }

    public Long hincrBy(final String key, final String fieldName, final long increment) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hincrBy(key, fieldName, increment);
            }
        });
    }

    public Double hincrByFloat(final String key, final String fieldName, final double increment) {
        return execute(new JedisAction<Double>() {
            @Override
            public Double action(Jedis jedis) {
                return jedis.hincrByFloat(key, fieldName, increment);
            }
        });
    }

    public Long hdel(final String key, final String... fieldsNames) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hdel(key, fieldsNames);
            }
        });
    }

    public Boolean hexists(final String key, final String fieldName) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.hexists(key, fieldName);
            }
        });
    }

    public Set<String> hkeys(final String key) {
        return execute(new JedisAction<Set<String>>() {
            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    /**
     * hvals命令封装，支持回源的区分
     * @param key key
     * @param type type
     * @param <T> T
     * @return null=表示无任何数据（未回源），空list=回源的数据为空
     */
    public <T> List<T> hvalsBin(final String key, final Class<T> type) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                List<String> list = jedis.hvals(key);
                if (list == null || list.size() == 0) {
                    return null;
                }
                List<T> result = new ArrayList<T>();
                if (list.size() == 1 && list.get(0).isEmpty()) {
                    return result;
                }
                for (String s : list) {
                    if (!s.isEmpty()) {
                        result.add(jsonDecode(s, type));
                    }
                }
                return result;
            }
        });
    }

    public Long hlen(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hlen(key);
            }
        });
    }

    public Long lpush(final String key, final String... values) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.lpush(key, values);
            }
        });
    }

    public <T> List<T> listRangeBin(final String key, final Class<T> type, final long start, final long end) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                List<String> list = jedis.lrange(key, start, end);
                return fromRangListBin(list, type);
            }
        });
    }

    public Long listAddBin(final String key, final int time, final int maxSize, final Object... objects) {
        if (objects == null || objects.length == 0) {
            return 0L;
        }
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                final String[] jsonStringArray = objectsToJsonArrayReverse(objects);
                Long count = jedis.lpush(key, jsonStringArray);
                if (maxSize > 0) {
                    jedis.ltrim(key, 0, maxSize + 1);
                }
                jedis.expire(key, time);
                return count;
            }
        });
    }

    /**
     * 创建空的队列
     *
     * @param key key
     * @param time 超时时间
     */
    public void listCreate(final String key, final int time) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.lpush(key, StringUtils.EMPTY);
                jedis.expire(key, time);
            }
        });
    }

    // / List Actions ///

    public String rpop(final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.rpop(key);
            }
        });
    }

    public String brpop(final int timeout, final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                List<String> nameValuePair = jedis.brpop(timeout, key);
                if (nameValuePair != null) {
                    return nameValuePair.get(1);
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * Not support for sharding.
     */
    public String rpoplpush(final String sourceKey, final String destinationKey) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.rpoplpush(sourceKey, destinationKey);
            }
        });
    }

    /**
     * Not support for sharding.
     */
    public String brpoplpush(final String source, final String destination, final int timeout) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.brpoplpush(source, destination, timeout);
            }
        });
    }

    public Long llen(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.llen(key);
            }
        });
    }

    public String lindex(final String key, final long index) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.lindex(key, index);
            }
        });
    }

    public List<String> lrange(final String key, final int start, final int end) {
        return execute(new JedisAction<List<String>>() {

            @Override
            public List<String> action(Jedis jedis) {
                return jedis.lrange(key, start, end);
            }
        });
    }

    public void ltrim(final String key, final int start, final int end) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.ltrim(key, start, end);
            }
        });
    }

    public void ltrimFromLeft(final String key, final int size) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.ltrim(key, 0, size - 1);
            }
        });
    }

    public Boolean lremFirst(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                Long count = jedis.lrem(key, 1, value);
                return (count == 1);
            }
        });
    }

    public Boolean lremAll(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                Long count = jedis.lrem(key, 0, value);
                return (count > 0);
            }
        });
    }

    // / Set Actions ///
    public Boolean sadd(final String key, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.sadd(key, member) == 1 ? true : false;
            }
        });
    }

    public Set<String> smembers(final String key) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.smembers(key);
            }
        });
    }

    /**
     * return true for add new element, false for only update the score.
     */
    public Boolean zadd(final String key, final double score, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.zadd(key, score, member) == 1 ? true : false;
            }
        });
    }

    public Double zscore(final String key, final String member) {
        return execute(new JedisAction<Double>() {

            @Override
            public Double action(Jedis jedis) {
                return jedis.zscore(key, member);
            }
        });
    }

    public Long zrank(final String key, final String member) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zrank(key, member);
            }
        });
    }

    public Long zrevrank(final String key, final String member) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zrevrank(key, member);
            }
        });
    }

    // / Ordered Set Actions ///

    public Long zcount(final String key, final double min, final double max) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }
        });
    }

    public Set<String> zrange(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrange(key, start, end);
            }
        });
    }

    public Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrangeWithScores(key, start, end);
            }
        });
    }

    public Set<String> zrevrange(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrevrange(key, start, end);
            }
        });
    }

    public Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrevrangeWithScores(key, start, end);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        });
    }

    public Boolean zrem(final String key, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.zrem(key, member) == 1 ? true : false;
            }
        });
    }

    public Long zremByScore(final String key, final double start, final double end) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    public Long zremByRank(final String key, final long start, final long end) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zremrangeByRank(key, start, end);
            }
        });
    }

    public Long zcard(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zcard(key);
            }
        });
    }

    /**
     * 释放锁
     * @param lockName 锁名
     */
    public void releaseLock(final String lockName) {
        del(lockName);
    }

    /**
     * 获取锁，默认锁超时时间10秒
     * @param lockName 锁名
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName) {
        return acquireLock(lockName, LOCK_EXPIRE_TIME);
    }

    /**
     * 获取锁
     * @param lockName 锁名
     * @param lockExpireTime 超时时间，单位秒
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName, final int lockExpireTime) {
        return execute(new JedisTemplate.JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                String ret = jedis.set(lockName, "lock", "NX", "EX", lockExpireTime);
                return ret != null;
            }
        });
    }

    public String jsonEncode(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    public <T> T jsonDecode(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseObject(input, type);
    }

    public <T> List<T> jsonDecodeArray(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseArray(input, type);
    }

    /**
     * list to array
     *
     * @param objects serial list
     * @return byte[][]
     */
    private String[] objectsToJsonArray(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[i]);
        }
        return result;
    }

    private String[] objectsToJsonArrayReverse(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[length - i - 1]);
        }
        return result;
    }

    /**
     * 联合组成redis的实际key
     * @param key string1
     * @param flagId string2
     * @return
     */
    public String key(String key, String flagId) {
        StringBuilder sb = new StringBuilder();
        return sb.append(key).append(flagId).toString();
    }

    /**
     * 联合组成redis的key
     * @param key
     * @param flageIds
     * @return
     */
    public String key(String key, Object... flageIds) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for (Object flageId : flageIds) {
            sb.append(flageId).append(":");
        }
        return sb.toString().endsWith(":") ?
                StringUtils.substring(sb.toString(), 0, sb.toString().length() - 1) :
                sb.toString();
    }

    /**
     * 联合组成redis的实际key
     * @param key string1
     * @param flagId string2
     * @param flagId2 string3
     * @return
     */
    public String key(String key, String flagId, String flagId2) {
        StringBuilder sb = new StringBuilder();
        return sb.append(key).append(flagId).append(":").append(flagId2).toString();
    }

    private <T> List<T> fromRangListBin(List<String> list, Class<T> type) {
        List<T> result = new ArrayList<T>();
        int size = list.size();
        if (size == 0) {
            return null;// NOSONAR
        }
        if (list.get(0).isEmpty()) {
            return result;
        }
        for (String s : list) {
            if (!s.isEmpty()) {
                result.add(jsonDecode(s, type));
            }
        }
        return result;
    }

    /**
     * Callback interface for template.
     */
    public interface JedisAction<T> {
        T action(Jedis jedis);
    }

    /**
     * Callback interface for template without result.
     */
    public interface JedisActionNoResult {
        void action(Jedis jedis);
    }

    /**
     * Callback interface for template.
     */
    public interface PipelineAction<T> {
        List<T> action(Pipeline Pipeline);
    }

    /**
     * Callback interface for template without result.
     */
    public interface PipelineActionNoResult {
        void action(Pipeline Pipeline);
    }
}
