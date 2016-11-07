package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtChangeItem;
import com.tuniu.abt.mapper.AbtChangeItemMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbtRefundItem Dao
 * Created by chengyao on 2016/4/22.
 */
@Repository
public class AbtChangeItemDao {

    @Resource
    private AbtChangeItemMapper abtChangeItemMapper;

    public List<AbtChangeItem> findItems(Long changeOrderId) {
        AbtChangeItem param = new AbtChangeItem();
        param.setChangeId(changeOrderId);
        return abtChangeItemMapper.select(param);
    }

}
