package com.tuniu.abt.startup;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tuniu.abt.base.patch.DefaultClassPatch;
import com.tuniu.operation.platform.tsg.base.filter.Base64DecodingFilter;
import com.tuniu.vla.base.taskpool.datashare.DataSharedFilter;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Properties;

public class AbtWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbtWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        patch();

        customInit();

        container.setInitParameter("contextConfigLocation", "classpath*:spring/spring-*.xml");
        container.setInitParameter("contextInitializerClasses", "com.tuniu.vla.base.framework.ContextProfileInitializer, "
                + "com.tuniu.zkconfig.client.boot.ConfigPrepareApplicationContextInitializer");

        // spring dispatcher
        ServletRegistration.Dynamic springDispatcher = container.addServlet("dispatcher", new DispatcherServlet());
        springDispatcher.setInitParameter("contextConfigLocation", "classpath:dispatcher-servlet.xml");
        springDispatcher.setLoadOnStartup(1);
        springDispatcher.addMapping("/");

        // listener
        // container.addListener(CorsInitListener.class);
        container.addListener(ContextLoaderListener.class);


        // thread data share filter
        container.addFilter("DataSharedFilter", DataSharedFilter.class)
                .addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "dispatcher");

        // character encoding filter
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        container.addFilter("CharacterEncodingFilter", encodingFilter)
                .addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "dispatcher");

        // base64 decoding filter
        FilterRegistration.Dynamic base64DecodingFilter = container.addFilter("Base64DecodingFilter", Base64DecodingFilter.class);
        base64DecodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        base64DecodingFilter.setInitParameter("noDecode", "/nothing/to/ignore");
    }

    private void patch() {
        DefaultClassPatch defaultClassPatch = new DefaultClassPatch();
        defaultClassPatch.patchAll();
    }


    private void customInit() {
        // 载入logback配置
        initLogger();

        // convertUtils register
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new LongConverter(null), java.lang.Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);

        // fast json default setting
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

    private void initLogger() {
        String logbackConfigLocation;
        try {
            Resource resourceSetting = new ClassPathResource("main-setting.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resourceSetting);
            logbackConfigLocation = "log/logback_" + properties.getProperty("application.envName") + ".xml";
        } catch (Exception ex) {
            throw new RuntimeException("can't loading main-setting.properties file from classpath.", ex);
        }

        try {
            Resource resource = new ClassPathResource(logbackConfigLocation);
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.reset();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(loggerContext);
            joranConfigurator.doConfigure(resource.getInputStream());
            LOGGER.info("logback configure file loaded. filePath={}", resource.getURI());
        } catch (Exception e) {
            LOGGER.error("can't loading logback configure file. classpath:" + logbackConfigLocation, e);
        }
    }
}