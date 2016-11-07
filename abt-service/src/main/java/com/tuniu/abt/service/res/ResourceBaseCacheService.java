package com.tuniu.abt.service.res;

import com.tuniu.abt.intf.tsp.TspResInterface;
import com.tuniu.abt.intf.tsp.dto.res.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用的资源信息服务
 *
 * @author chengyao
 */
@Service
public class ResourceBaseCacheService {
    @Resource
    TspResInterface tspResInterface;

    @Cacheable(value = "oneDay_district", key = "'district_map'")
    public Map<String, ResDistrict> getDistrictMap() {
        List<ResDistrict> districts = tspResInterface.findDistrict(new ResDistrictReq());
        Map<String, ResDistrict> districtMap = new HashMap<>();
        districts.forEach(n -> districtMap.put(n.getIataCode(), n));
        return districtMap;
    }

    /**
     * 重置缓存
     */
    @CacheEvict(value = "oneDay_district", key = "'district_map'")
    public void clearDistrictMap() {
        // Do nothing because of deal with aop.
    }

    /**
     * 获取航空公司信息
     *
     * @return Map<公司二字码，公司名>
     */
    @Cacheable(value = "oneDay_airline_company", key = "#code")
    public ResAirlineCompany getAirlineCompany(String code) {
        ResRowsResp<ResAirlineCompany> resp = tspResInterface
                .findAirlineCompany(new ResAirlineCompanyReq().setAirlineCompanyIataCode(code));
        if (resp != null && CollectionUtils.isNotEmpty(resp.getRows())) {
            return resp.getRows().get(0);
        }
        return new ResAirlineCompany();
    }

    /**
     * 重置缓存
     */
    @CacheEvict(value = "oneDay_airline_company", allEntries = true)
    public void clearAirlineCompany() {
        // Do nothing because of deal with aop.
    }

    /**
     * 返回机场信息。
     *
     * @param airportCode 机场三字码
     * @return 如果查询到结果，返回的ResAirport对象字段都有值，用字段为null判断是否未查询到数据。
     */
    @Cacheable(value = "oneDay_airport", key = "#airportCode")
    public ResAirport getAirport(String airportCode) {
        ResRowsResp<ResAirport> resp = tspResInterface.findAirport(new ResAirPortReq().setAirportCode(airportCode));
        if (resp != null && CollectionUtils.isNotEmpty(resp.getRows())) {
            return resp.getRows().get(0);
        }
        return new ResAirport();
    }

    /**
     * 重置缓存
     */
    @CacheEvict(value = "oneDay_airport", allEntries = true)
    public void clearAirport() {
        // Do nothing because of deal with aop.
    }

    //
    //
    //    /**
    //     * 获取携程航站楼信息map
    //     * @return Map<ID, 航站楼信息>
    //     */
    //    @Cacheable(value = "oneDay", key = "'ctrip_terminal_map'")
    //    public Map<Integer, ResCtripTerminal> getCtripTerminalMap() {
    //        List<ResCtripTerminal> list = tspResInterface.findCtripTerminals();
    //        Map<Integer, ResCtripTerminal> map = new HashMap<Integer, ResCtripTerminal>();
    //        if (CollectionUtils.isNotEmpty(list)) {
    //            for (ResCtripTerminal terminal : list) {
    //                map.put(terminal.getId(), terminal);
    //            }
    //        }
    //        return map;
    //    }
    //
    //    /**
    //     * 重置缓存
    //     */
    //    @CacheEvict(value = "oneDay", key = "'ctrip_terminal_map'")
    //    public void reloadAirportSeatClassLuggageInfos() {
    //        // Do nothing because of deal with aop.
    //    }

}
