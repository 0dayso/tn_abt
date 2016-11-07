package com.tuniu.vla.base.mbeans;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;
import org.springframework.beans.factory.InitializingBean;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

/**
 * ehcache JMX 注册
 * Created by chengyao on 2016/4/26.
 */
public class EhCacheJmxRegister implements InitializingBean {

    private net.sf.ehcache.CacheManager cacheManager;

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(cacheManager, mBeanServer, true, true, true, true);
    }

}
