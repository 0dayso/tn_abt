package com.tuniu.vla.base.hystrix;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.tuniu.zkconfig.client.event.ConfigChangeEvent;
import com.tuniu.zkconfig.client.event.ConfigChangeHolder;
import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Iterator;

/**
 * hystrix的通用配置类
 *
 * Created by chengyao on 2016/2/22.
 */
public class HystrixDynamicConfiguration extends AbstractConfiguration implements
        InitializingBean, EnvironmentAware, ApplicationListener<ConfigChangeEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixDynamicConfiguration.class);

    private Environment environment;

    @Override
    public void onApplicationEvent(ConfigChangeEvent event) {
        ConfigChangeHolder holder = (ConfigChangeHolder) event.getConfig();
        LOGGER.trace("配置项更新通知：configName={}, old value={}, new value={}",
                    holder.getConfigNmae(), holder.getOldVal(), holder.getNewVal());
        // 必须调用，才可以通知到hystrix属性已变更
        addPropertyDirect(holder.getConfigNmae(), holder.getNewVal());
    }

    @Override
    protected void addPropertyDirect(String key, Object value) {
        // no need, properties get from configApi;
    }

    @Override
    public boolean isEmpty() {
        // always have values
        return false;
    }

    @Override
    public boolean containsKey(String key) {
        return environment.containsProperty(key);
    }

    @Override
    public Object getProperty(String key) {
        String value = environment.getProperty(key);
        LOGGER.trace("read property. key={}, value={}", key, value);
        return value;
    }

    @Override
    public Iterator<String> getKeys() {
        // 不需要此方法
        throw new IllegalAccessError("方法未实现.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 安装配置项目
        installDynamicConfiguration();
    }

    private void installDynamicConfiguration() {
        System.setProperty(DynamicPropertyFactory.ENABLE_JMX, "true");
        ConfigurationManager.install(this);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
