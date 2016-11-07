package com.tuniu.abt.mbeans;

import com.tuniu.abt.service.res.ResourceBaseCacheService;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import javax.annotation.Resource;

/**
 * 本地缓存相关 JMX MBean
 * Created by chengyao on 2016/4/26.
 */
@ManagedResource(objectName= "com.tuniu.abt:name=LocalCacheManage" , description= "管理本地缓存" )
public class LocalCacheMBean {

    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;

    @ManagedOperation(description="清除城市缓存")
    public void clearDistrict() {
        resourceBaseCacheService.clearDistrictMap();
    }

    @ManagedOperation(description="清除航司缓存")
    public void clearAirlineCompany() {
        resourceBaseCacheService.clearAirlineCompany();
    }

    @ManagedOperation(description="清除机场缓存")
    public void clearAirport() {
        resourceBaseCacheService.clearAirport();
    }

}
