package com.tuniu.abt.utils;

import com.tuniu.zkconfig.client.utils.ConfigApi;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 航信通道配置
 * Created by chengyao on 2016/3/19.
 */
@Service
public class TsConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TsConfig.class);

    public static final String RT = "rt";
    public static final String CANCEL = "cancel";
    public static final String ISSUE = "issue";
    public static final String AIRBOOK = "airbook";
    public static final String PATA = "pata";
    public static final String FD = "fd";
    public static final String AV = "av";
    public static final String DEL_PNR_ITEM = "delPnrItem";
    public static final String SPLIT_PNR = "splitPnr";
    public static final String REFUND = "refund";
    public static final String MAX_PSG_NUM_LIMIT = "maxPsgNumLimit";
    public static final String OSI_ADD = "osiAdd";
    public static final String RMK_ADD = "rmkAdd";
    public static final String PATA_BY_SEG = "pataBySeg";
    public static final int MAX_PSG_NUM_LIMIT_DEFALUT = 9;


    @Resource
    private ConfigApi configApi;

    public String usePlusInner(String module, int systemId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ts.channel.").append(module);
        if (systemId != 0) {
            stringBuilder.append(".").append(systemId);
        } else {
            stringBuilder.append(".default");
        }
        return configApi.get(stringBuilder.toString());
    }

    public boolean usePlus(String module, int systemId) {
        String result = usePlusInner(module, systemId);
        if (result == null && systemId != 0) {
            result = usePlusInner(module, 0);
        }
        return "plus".equals(result);
    }

    public String getSolutionId(boolean isBop) {
        return configApi.get("solution.id" , isBop?"bop":"bsp");
    }

    public String getSolutionName(String solutionId) {
        if (solutionId.equals(getSolutionId(true))) {
            return configApi.get("solution.name.bop");
        } else {
            return configApi.get("solution.name.bsp");
        }
    }

    /**
     * 根据航司二字码获取单个pnr中的最大人数限制
     *
     * @param airCompanyCode
     * @return
     */
    public int getMaxPsgNumLimitInPnr(String airCompanyCode) {
        String value = configApi.get(MAX_PSG_NUM_LIMIT, airCompanyCode);
        if (null == value || Integer.parseInt(value) == 0) {
            return MAX_PSG_NUM_LIMIT_DEFALUT; // 默认9人
        }
        return Integer.parseInt(value);
    }

    public static final String REPLACE_SEAT_CODE = "isReplaceSeatCodeAllowed";

    public static final String CHD_USE_ADT_SEAT_CODE = "isChdUseAdtSeatCodeAllowed";

    public static final String USE_DEFAULT_CONTACT_NUM = "useDefaultContactNum";

    public static final String DO_PATA_AFTER_CREAT_PNR = "doPataAfterCreatPnr";

    public static final String DO_PATA_USE_FD_PRICE = "doPataUseFdPrice";

    public static final String CHECK_AOP_POLICY_IS_VALID = "checkAopPolicyIsValid";

    public boolean isAllowed(String configName, Integer systemId, Integer vendorId) {
        String configValue = getConfigValue(configName, systemId, vendorId);
        if (StringUtils.isBlank(configValue) && isNotNullOrZero(systemId)) {
            configValue = getConfigValue(configName, 0, vendorId);
        }
        if (StringUtils.isBlank(configValue) && isNotNullOrZero(vendorId)) {
            configValue = getConfigValue(configName, systemId, 0);
        }
        if (StringUtils.isBlank(configValue)
                && isNotNullOrZero(systemId) && isNotNullOrZero(vendorId)) {
            configValue = getConfigValue(configName, 0, 0);
        }
        return Boolean.valueOf(configValue);
    }

    private String getConfigValue(String configName, Integer systemId, Integer vendorId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(configName);
        if (isNotNullOrZero(systemId)) {
            stringBuilder.append(".").append(systemId);
        } else {
            stringBuilder.append(".default");
        }
        if (isNotNullOrZero(vendorId)) {
            stringBuilder.append(".").append(vendorId);
        } else {
            stringBuilder.append(".default");
        }
        return configApi.get(stringBuilder.toString());
    }

    private boolean isNotNullOrZero(Integer obj) {
        if (null != obj && obj.intValue() != 0) {
            return true;
        }
        return false;
    }

    public int getMaxBopRetryCount() {
        String value = configApi.get("maxBopRetryCount");
        if (null == value || !value.matches("[0-9]")) {
            return 3; // 默认3次
        }
        return Integer.parseInt(value);
    }
}
