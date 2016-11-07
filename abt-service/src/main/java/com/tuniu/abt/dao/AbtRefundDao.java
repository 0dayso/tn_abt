package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.mapper.AbtRefundItemMapper;
import com.tuniu.abt.mapper.AbtRefundOrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/4/19.
 */
@Repository
public class AbtRefundDao {

    @Resource
    private AbtRefundOrderMapper abtRefundOrderMapper;

    @Resource
    private AbtRefundItemMapper abtRefundItemMapper;


    public int updateRefundItemStatus(Long id, int status) {
        AbtRefundItem param = new AbtRefundItem();
        param.setId(id);
        param.setStatus(status);
        return abtRefundItemMapper.updateByPrimaryKeySelective(param);
    }

    public int updateRefundOrderStatus(Long id, int status) {
        AbtRefundOrder param = new AbtRefundOrder();
        param.setId(id);
        param.setStatus(status);
        return abtRefundOrderMapper.updateByPrimaryKeySelective(param);
    }

    public void updateRefundItemRealAmount(Long id, Float amount) {
        AbtRefundItem updateParam = new AbtRefundItem();
        updateParam.setId(id);
        updateParam.setRealRefundAmount(amount);
        abtRefundItemMapper.updateByPrimaryKeySelective(updateParam);
    }
}
