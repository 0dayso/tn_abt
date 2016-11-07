package com.tuniu.vla.base.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by chengyao on 2016/2/14.
 */
public class BaseSystemConfig implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSystemConfig.class);

    /** 开发或本地环境 */
    private boolean local = false;

    /** 生产或预发布环境 */
    private boolean product = true;

    /**
     * 运行环境
     */
    @Value("${application.envName}")
    private String envName;

    @Override
    public void afterPropertiesSet() throws Exception {
        if ("dev".equals(envName) || "local".equals(envName)) {
            local = true;
        }
        if (!"prd".equals(envName) && !"pre".equals(envName)) {
            product = false;
        }
        LOGGER.info("当前系统环境配置名：{}", envName);
    }

    public boolean isProduct() {
        return product;
    }

    public void setProduct(boolean product) {
        this.product = product;
    }

    public boolean isLocal() {
        return local;
    }

    public String getEnvName() {
        return envName;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }
}
