package com.tuniu.abt.mapper;

import com.tuniu.abt.intf.dto.backmeal.CurdBackMeal;
import com.tuniu.abt.intf.entity.AbtBackMeal;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface AbtBackMealMapper extends Mapper<AbtBackMeal> {

    Integer queryCurdCount(CurdBackMeal backMeal);

    List<CurdBackMeal> queryCurdList(CurdBackMeal backMeal);

    List<AbtBackMeal> findAlertRecords(String date);

}