package com.tuniu.abt.dao;

import com.tuniu.abt.intf.dto.backmeal.CurdBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMealRule;
import com.tuniu.abt.intf.page.Pager;
import com.tuniu.abt.mapper.AbtBackMealMapper;
import com.tuniu.abt.mapper.AbtBackMealRuleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by chengyao on 2016/3/31.
 */
@Repository
public class AbtBackMealDao {

    @Resource
    private AbtBackMealMapper abtBackMealMapper;

    @Resource
    private AbtBackMealRuleMapper abtBackMealRuleMapper;

    public int insertMain(AbtBackMeal record) {
        return abtBackMealMapper.insertSelective(record);
    }

    public int insertRule(AbtBackMealRule record) {
        return abtBackMealRuleMapper.insertSelective(record);
    }

    public AbtBackMeal findMain(Long id) {
        return abtBackMealMapper.selectByPrimaryKey(id);
    }

    public AbtBackMealRule findRule(Long id) {
        return abtBackMealRuleMapper.selectByPrimaryKey(id);
    }

    public int updateMain(AbtBackMeal record) {
        record.setAddTime(null);
        record.setUpdateTime(null);
        return abtBackMealMapper.updateByPrimaryKeySelective(record);
    }

    public int updateRule(AbtBackMealRule record) {
        return abtBackMealRuleMapper.updateByPrimaryKeySelective(record);
    }

    public Pager<CurdBackMeal> queryList(CurdBackMeal curdBackMeal) {
        int count = abtBackMealMapper.queryCurdCount(curdBackMeal);
        if (count == 0) {
            return Pager.empty();
        }
        List<CurdBackMeal> rows = abtBackMealMapper.queryCurdList(curdBackMeal);
        if (rows.isEmpty()) {
            List<CurdBackMeal> list = Collections.emptyList();
            return new Pager<CurdBackMeal>(count, list);
        }
        return new Pager<CurdBackMeal>(count, rows);
    }

    public List<AbtBackMeal> findByAirlineCompany(String airlineCompany) {
        AbtBackMeal param = new AbtBackMeal();
        param.setAirlineCompany(airlineCompany);
        param.setStatus(AbtBackMeal.STATUS_ONLINE);
        param.setJourneyType(AbtBackMeal.JOURNEY_TYPE_ONE_WAY);
        return abtBackMealMapper.select(param);
    }

}
