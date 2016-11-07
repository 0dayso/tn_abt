package com.tuniu.vla.base.framework;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * 载入spring的profile设置
 *
 *
 * Created by chengyao on 2016/1/4.
 */
public class ContextProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextProfileInitializer.class);

    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
            PropertiesConfiguration propertiesConfig = new PropertiesConfiguration();
            propertiesConfig.load("main-setting.properties");
            String profile = propertiesConfig.getString("application.envName");
            environment.setActiveProfiles(profile);
            LOGGER.info("设置当前激活的spring profile值：" + profile);
        } catch (Exception ex) {
            LOGGER.error("读取spring profile设置出错, 检查main-setting.properties文件", ex);
        }
    }
}
