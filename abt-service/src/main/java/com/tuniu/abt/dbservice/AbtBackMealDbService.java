package com.tuniu.abt.dbservice;

import com.tuniu.abt.dao.AbtBackMealDao;
import com.tuniu.abt.intf.dto.backmeal.BackMealRedisItem;
import com.tuniu.abt.intf.dto.backmeal.CurdBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMealRule;
import com.tuniu.abt.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by chengyao on 2016/3/31.
 */
@Service
public class AbtBackMealDbService {

    @Resource
    private AbtBackMealDao abtBackMealDao;

    @Transactional
    public AbtBackMeal save(CurdBackMeal curdBackMeal) {
        curdBackMeal.setAddTime(null);
        curdBackMeal.setUpdateTime(null);
        curdBackMeal.setId(null);

        AbtBackMeal abtBackMeal = CommonUtils.transform(curdBackMeal, AbtBackMeal.class);
        abtBackMealDao.insertMain(abtBackMeal);
        AbtBackMealRule rule = CommonUtils.transform(curdBackMeal, AbtBackMealRule.class);
        rule.setId(abtBackMeal.getId());
        abtBackMealDao.insertRule(rule);
        return abtBackMeal;
    }

    @Transactional
    public AbtBackMeal update(CurdBackMeal curdBackMeal, boolean updateRule) {
        curdBackMeal.setAddTime(null);
        curdBackMeal.setUpdateTime(null);

        AbtBackMeal abtBackMeal = CommonUtils.transform(curdBackMeal, AbtBackMeal.class);
        abtBackMealDao.updateMain(abtBackMeal);
        if (updateRule) {
            AbtBackMealRule rule = CommonUtils.transform(curdBackMeal, AbtBackMealRule.class);
            rule.setId(abtBackMeal.getId());
            abtBackMealDao.updateRule(rule);
        }
        return abtBackMeal;
    }

    public CurdBackMeal findById(Long id) {
        AbtBackMeal main = abtBackMealDao.findMain(id);
        AbtBackMealRule rule = abtBackMealDao.findRule(id);
        CurdBackMeal curdBackMeal = CommonUtils.transform(main, CurdBackMeal.class);
        CommonUtils.copyPropertiesSilence(curdBackMeal, rule);
        return curdBackMeal;
    }

    public List<BackMealRedisItem> findByAirlineCompany(String airlineCompany) {
        List<AbtBackMeal> list = abtBackMealDao.findByAirlineCompany(airlineCompany);
        List<BackMealRedisItem> result = new ArrayList<BackMealRedisItem>();

        for (AbtBackMeal abtBackMeal : list) {
            BackMealRedisItem item = transToRedisItem(abtBackMeal);
            result.add(item);
        }
        return result;
    }

    public BackMealRedisItem transToRedisItem(AbtBackMeal abtBackMeal) {
        BackMealRedisItem item = new BackMealRedisItem();
        item.setAirlineCompany(abtBackMeal.getAirlineCompany());
        String cabin = abtBackMeal.getCabin().toUpperCase();
        item.setCabins(new HashSet<String>());
        item.getCabins().addAll(Arrays.asList(StringUtils.split(cabin, "/")));

        String cityOptions = abtBackMeal.getCityOptions();
        item.setCityOptions(new HashSet<String>());
        item.getCityOptions().addAll(Arrays.asList(StringUtils.split(cityOptions, "/")));

        item.setCityOptionType(abtBackMeal.getCityOptionType());
        item.setDepartureDateEnd(abtBackMeal.getDepartureDateEnd());
        item.setDepartureDateStart(abtBackMeal.getDepartureDateStart());
        item.setPassengerType(abtBackMeal.getPassengerType());
        item.setId(abtBackMeal.getId());
        item.setTicketDateEnd(abtBackMeal.getTicketDateEnd());
        item.setTicketDateStart(abtBackMeal.getTicketDateStart());
        return item;
    }
}
