package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtChangeOrder;
import com.tuniu.abt.mapper.AbtChangeOrderMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chengyao on 2016/4/22.
 */
@Repository
public class AbtChangeOrderDao {

    @Resource
    private AbtChangeOrderMapper abtChangeOrderMapper;

    public AbtChangeOrder findBySessionId(String sessionId) {
        Example example = new Example(AbtChangeOrder.class);
        example.createCriteria().andEqualTo("sessionId", sessionId);
        List<AbtChangeOrder> list = abtChangeOrderMapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    public int updateAopInfo(Long id, String aopOrder, String aopChangeOrder) {
        AbtChangeOrder param = new AbtChangeOrder();
        param.setId(id);
        param.setAopOrder(aopOrder);
        param.setAopChangeOrder(aopChangeOrder);
        return abtChangeOrderMapper.updateByPrimaryKeySelective(param);
    }

}
