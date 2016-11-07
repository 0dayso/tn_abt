package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtRefundOrder;
import com.tuniu.abt.mapper.AbtRefundOrderMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/4/22.
 */
@Repository
public class AbtRefundOrderDao {

    @Resource
    private AbtRefundOrderMapper abtRefundOrderMapper;

    public AbtRefundOrder findBySessionId(String sessionId) {
        Example example = new Example(AbtRefundOrder.class);
        example.createCriteria().andEqualTo("sessionId", sessionId);
        List<AbtRefundOrder> list = abtRefundOrderMapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    public int updateAopInfo(Long id, String aopOrder, String aopRefundOrder) {
        AbtRefundOrder param = new AbtRefundOrder();
        param.setId(id);
        param.setAopOrder(aopOrder);
        param.setAopRefundOrder(aopRefundOrder);
        return abtRefundOrderMapper.updateByPrimaryKeySelective(param);
    }

}
