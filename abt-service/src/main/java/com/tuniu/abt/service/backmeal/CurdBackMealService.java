package com.tuniu.abt.service.backmeal;

import com.tuniu.abt.dao.AbtBackMealDao;
import com.tuniu.abt.dbservice.AbtBackMealDbService;
import com.tuniu.abt.intf.constants.BackMealEx;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.dto.backmeal.BackMealRedisItem;
import com.tuniu.abt.intf.dto.backmeal.CurdBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.page.Pager;
import com.tuniu.abt.service.cachework.BackMealCacheWorker;
import com.tuniu.abt.service.cachework.BackMealRuleCacheWorker;
import com.tuniu.abt.service.res.ResourceService;
import com.tuniu.abt.utils.CommonUtils;
import com.tuniu.abt.utils.DateTimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 退改签规则维护服务
 * Created by chengyao on 2016/4/6.
 */
@Service
@Validated
public class CurdBackMealService {

    @Resource
    private AbtBackMealDbService abtBackMealDbService;
    @Resource
    private AbtBackMealDao abtBackMealDao;
    @Resource
    private BackMealCacheWorker backMealCacheWorker;
    @Resource
    private BackMealRuleCacheWorker backMealRuleCacheWorker;
    @Resource
    private ResourceService resourceService;
    /**
     * 录入退改签政策信息
     * @param curdBackMeal po
     */
    public AbtBackMeal create(@Valid CurdBackMeal curdBackMeal) {
        curdBackMeal.setCabin(buildCabin(curdBackMeal.getCabin()));
        curdBackMeal.setStatus(AbtBackMeal.STATUS_INIT);
        return abtBackMealDbService.save(curdBackMeal);
    }

    /**
     * 修改退改签政策信息
     * @param curdBackMeal po
     */
    public AbtBackMeal update(CurdBackMeal curdBackMeal) {
        if (curdBackMeal.getId() == null) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "修改未传入id");
        }
        if (curdBackMeal.getOperatorId() == null) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "未登录");
        }

        CurdBackMeal exist = abtBackMealDbService.findById(curdBackMeal.getId());
        if (exist == null) {
            throw new BizzException(BackMealEx.UPDATE_ERROR, "无该id对应的记录，查询失败！");
        }
        if (exist.getStatus() == AbtBackMeal.STATUS_DEL) {
            throw new BizzException(BackMealEx.UPDATE_ERROR, "已删除项目不得修改！");
        }

        boolean updateRule = true;

        // 修改状态
        if (curdBackMeal.getStatus() != null) {
            int status = curdBackMeal.getStatus();
            Integer operatorId = curdBackMeal.getOperatorId();
            String operatorName = curdBackMeal.getOperatorName();
            long id = curdBackMeal.getId();
            curdBackMeal = new CurdBackMeal();
            curdBackMeal.setStatus(status);
            curdBackMeal.setId(id);
            curdBackMeal.setOperatorId(operatorId);
            curdBackMeal.setOperatorName(operatorName);
            updateRule = false;

            if (exist.getStatus() == status) {
                throw new BizzException(BackMealEx.UPDATE_ERROR, "状态已更新");
            }

            // 校验重复
            if (status == AbtBackMeal.STATUS_ONLINE) {
                checkDuplication(exist);
            }

        } else {
            if (exist.getStatus() == AbtBackMeal.STATUS_ONLINE) {
                throw new BizzException(BackMealEx.UPDATE_ERROR, "已生效政策不得修改！");
            }
            curdBackMeal.setStatus(null);
            curdBackMeal.setCabin(buildCabin(curdBackMeal.getCabin()));
        }

        //修改成功返回id
        AbtBackMeal result = abtBackMealDbService.update(curdBackMeal, updateRule);

        // 更新缓存
        if (curdBackMeal.getStatus() != null) {
            clearCache(exist);
        }

        return result;
    }

    /**
     * 查询退改签政策信息
     * @param curdBackMeal
     * @author lining
     * @date 2015-01-10
     */
    public Pager<CurdBackMeal> query(CurdBackMeal curdBackMeal) {
        if (StringUtils.isNotEmpty(curdBackMeal.getCabin())) {
            curdBackMeal.setCabin("/" + curdBackMeal.getCabin().toUpperCase() + "/");
        }
        if (curdBackMeal.getStart() == null || curdBackMeal.getStart() < 0) {
            curdBackMeal.setStart(0);
        }
        if (curdBackMeal.getLimit() == null || curdBackMeal.getLimit() < 0 || curdBackMeal.getLimit() > 50) {
            curdBackMeal.setLimit(50);
        }
        Pager<CurdBackMeal> pager = abtBackMealDao.queryList(curdBackMeal);
        List<CurdBackMeal> list = pager.getRows();
        for (CurdBackMeal backMeal : list) {
            backMeal.setCityName(toCityName(backMeal.getCityOptions()));
        }
        return pager;
    }


    public CurdBackMeal queryById(Long id) {
        if (id == null) {
            throw new BizzException(BizzEx.VERIFY_INPUT_ERROR, "未指定查询id");
        }
        CurdBackMeal curdBackMeal = abtBackMealDbService.findById(id);
        curdBackMeal.setCityName(toCityName(curdBackMeal.getCityOptions()));
        return curdBackMeal;
    }

    // 校验待生效的项目是否和已生效的项目冲突
    private void checkDuplication(CurdBackMeal curdBackMeal) {
        AbtBackMeal backMeal = CommonUtils.transform(curdBackMeal, AbtBackMeal.class);
        BackMealRedisItem newItem = abtBackMealDbService.transToRedisItem(backMeal);

        List<BackMealRedisItem> redisItems = backMealCacheWorker.find(curdBackMeal.getAirlineCompany());
        for (BackMealRedisItem redisItem : redisItems) {
            // 校验乘客类型
            if (!redisItem.getPassengerType().equals(newItem.getPassengerType())) {
                continue;
            }

            if (timeNoCross(redisItem, newItem)) {
                continue;
            }

            // 校验舱位
            if (cabinNoCross(redisItem.getCabins(), newItem.getCabins())) {
                continue;
            }

            // 校验出发到达
            if (redisItem.getCityOptionType().equals(newItem.getCityOptionType())) { // 同样适用或非适用
                if (cityNoCrossWithSame(redisItem.getCityOptions(), newItem.getCityOptions())) {
                    continue;
                }
            } else if (redisItem.getCityOptionType() == AbtBackMeal.CITY_OPTION_TYPE_INCLUDE) {
                if (cityNoCrossWithDiff(redisItem.getCityOptions(), newItem.getCityOptions())) {
                    continue;
                }
            } else {
                if (cityNoCrossWithDiff(newItem.getCityOptions(), redisItem.getCityOptions())) {
                    continue;
                }
            }

            // 走到最后，说明有交集
            throw new BizzException(BackMealEx.DUPLICATE_RULE, "规则重复");
        }
    }

    // 舱位的交集判断，如果没有相交，返回true
    private boolean cabinNoCross(Collection<String> cabins1, Collection<String> cabins2) {
        Collection intersection = CollectionUtils.intersection(cabins1, cabins2);
        return intersection.size() == 0;
    }

    // 出发到达的交集判断（同适用或非适用）
    private boolean cityNoCrossWithSame(Collection<String> city1, Collection<String> city2) {
        if (city1.size() == 0 || city2.size() == 0) {
            return false;
        }
        Collection intersection = CollectionUtils.intersection(city1, city2);
        return intersection.size() == 0;
    }

    // 出发到达的交集判断（一个是适用，一个是非适用）
    private boolean cityNoCrossWithDiff(Collection<String> city1, Collection<String> city2) {
        if (city1.size() == 0 && city2.size() == 0) {
            return true;
        }
        if (city1.size() == 0 || city2.size() == 0) {
            return false;
        }
        return CollectionUtils.isSubCollection(city1, city2);
    }

    // 时间的交集判断
    private boolean timeNoCross(BackMealRedisItem item1, BackMealRedisItem item2) {
        long[] range1 = findTimeRangeMill(item1.getTicketDateStart(), item1.getTicketDateEnd());
        long[] range2 = findTimeRangeMill(item2.getTicketDateStart(), item2.getTicketDateEnd());
        if (!rangeNoCross(range1, range2)) {
            return false;
        }

        range1 = findTimeRangeMill(item1.getDepartureDateStart(), item1.getDepartureDateEnd());
        range2 = findTimeRangeMill(item2.getDepartureDateStart(), item2.getDepartureDateEnd());
        if (!rangeNoCross(range1, range2)) {
            return false;
        }

        return true;
    }

    private boolean rangeNoCross(long[] range1, long[] range2) {
        if (range1 == null || range2 == null) {
            return false;
        }
        if (range1[0] > range2[1] || range1[1] < range2[0]) {
            return true;
        }
        return false;
    }

    private long[] findTimeRangeMill(String start, String end) {
        Date dateStart = DateTimeUtils.convertDate(start);
        Date dateEnd = DateTimeUtils.convertDate(end);
        if (dateStart != null && dateEnd != null) {
            return new long[] {dateStart.getTime() , dateEnd.getTime()};
        }
        return null;
    }


    private void clearCache(CurdBackMeal curdBackMeal) {
        backMealCacheWorker.clearCache(curdBackMeal.getAirlineCompany());
        backMealRuleCacheWorker.clearCache(curdBackMeal.getId());
    }

    private String buildCabin(String cabin) {
        if (cabin == null || cabin.length() == 0) {
            return null;
        }
        if (cabin.charAt(0) != '/') {
            cabin = '/' + cabin;
        }
        if (cabin.charAt(cabin.length() - 1) != '/') {
            cabin = cabin + '/';
        }
        return cabin.toUpperCase();
    }


    private String toCityName(String cityOptions) {
        if (StringUtils.isEmpty(cityOptions)) {
            return null;
        }
        //转换cityKey
        StringBuilder sb = new StringBuilder();
        String[] flightCode = cityOptions.split("/");
        for (int i=0; i<flightCode.length; i++) {
            if (!flightCode[i].isEmpty()) {
                String[] cityCode = flightCode[i].split("-");
                sb.append(resourceService.findDistrictName(cityCode[0]));
                for (int j=1; j<cityCode.length; j++) {
                    if (!cityCode[j].isEmpty()) {
                        sb.append("-" + resourceService.findDistrictName(cityCode[j]));
                    }
                }
                if (i != flightCode.length-1) {
                    sb.append("/");
                }
            }
        }
        return sb.toString();
    }
}
