package com.tuniu.aop.unittest;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.tuniu.abt.base.patch.DefaultClassPatch;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.junit.BeforeClass;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/spring-common.xml", "classpath*:spring/spring-redis.xml", "classpath*:spring/spring-taskpool.xml",
        "classpath*:spring/spring-dal.xml", "classpath*:spring/spring-res-test.xml", "classpath*:spring/spring-localcache.xml",
        "classpath*:spring/spring-connector-dubbo.xml", "classpath*:spring/spring-jms.xml",
        "classpath*:spring/spring-tsp.xml", "classpath*:spring/spring-tsp-framework.xml", "classpath*:spring/spring-email.xml"})
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {

        static {
                initLogger();
        }

        @BeforeClass
        public static void beforeClass() {

                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                ConvertUtils.register(new LongConverter(null), java.lang.Long.class);
                ConvertUtils.register(new ShortConverter(null), Short.class);
                ConvertUtils.register(new IntegerConverter(null), Integer.class);
                ConvertUtils.register(new DoubleConverter(null), Double.class);
                ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);

                System.setProperty("zookeeper.connectString", "10.10.30.105:2181");

                new DefaultClassPatch().patchAll();
        }

        private static void initLogger() {
                try {
                        Resource resource = new ClassPathResource("logback_test.xml");
                        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                        loggerContext.reset();
                        JoranConfigurator joranConfigurator = new JoranConfigurator();
                        joranConfigurator.setContext(loggerContext);
                        joranConfigurator.doConfigure(resource.getInputStream());
                        // LOGGER.info("logback configure file loaded. filepath={}", resource.getURI());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}

