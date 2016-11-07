package com.tuniu.vla.base.cache.support;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.CacheDecoratorFactory;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import java.util.Properties;

/**
 * Used in the ehcache configuration to create {@link BlockingCache}s. This
 * avoids a thundering herds situation when a heavily trafficed cached object expires (i.e., the configuration).
 *
 *
 *
 *
 * 参考 http://henningpetersen.com/post/17/thundering-herds-spring-cache-and-ehcache
 *
 * @Author 14080241
 */
public class BlockingCacheDecoratorFactory extends CacheDecoratorFactory {

    private static final int TIMEOUT_MILLIS = 5000;

    @Override
    public Ehcache createDecoratedEhcache(final Ehcache cache, final Properties properties) {
        final BlockingCache blockingCache = new BlockingCache(cache);
        blockingCache.setTimeoutMillis(TIMEOUT_MILLIS);
        return blockingCache;
    }

    @Override
    public Ehcache createDefaultDecoratedEhcache(final Ehcache cache, final Properties properties) {
        return this.createDecoratedEhcache(cache, properties);
    }
}