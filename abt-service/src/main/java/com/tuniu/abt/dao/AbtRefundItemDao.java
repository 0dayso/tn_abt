package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.mapper.AbtRefundItemMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbtRefundItem Dao
 * Created by chengyao on 2016/4/22.
 */
@Repository
public class AbtRefundItemDao {

    @Resource
    private AbtRefundItemMapper abtRefundItemMapper;

    public List<AbtRefundItem> findItems(Long refundOrderId) {
        AbtRefundItem param = new AbtRefundItem();
        param.setRefundId(refundOrderId);
        return abtRefundItemMapper.select(param);
    }

}
