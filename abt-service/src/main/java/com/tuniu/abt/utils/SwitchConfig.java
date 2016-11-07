package com.tuniu.abt.utils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tuniu.zkconfig.client.utils.ConfigApi;

@Component
public class SwitchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchConfig.class);

    @Resource
    private ConfigApi configApi;

    public String getString(String channel, String channelType, int systemId, int vendorId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("switch.").append(channel).append(".").append(channelType);
        if (systemId != 0) {
            stringBuilder.append(".").append(systemId);
        }
        if (vendorId !=0) {
            stringBuilder.append(".").append(vendorId);
        }
        return configApi.get(stringBuilder.toString());
    }

    public int getInt(String channel, String channelType) {
        return getInt(channel, channelType, 0);
    }
    
    public int getInt(String channel, String channelType, int systemId) {
        return getInt(channel, channelType, systemId, 0);
    }

    public int getInt(String channel, String channelType, int systemId, int vendorId) {
        int ret = 0;
        try {
            String value = getString(channel, channelType, systemId, vendorId);
            if (value == null) {
                LOGGER.debug("no switch setting of channel={},channelType={},systemId={}, use default value 0.",
                        channel, channelType, systemId);
            } else {
                ret = Integer.parseInt(value);
            }
        } catch (Exception ex) {
            LOGGER.warn("switch setting is not integer of channel={},channelType={},systemId={}, use default value 0.",
                    channel, channelType, systemId);
        }
        return ret;
    }

}
