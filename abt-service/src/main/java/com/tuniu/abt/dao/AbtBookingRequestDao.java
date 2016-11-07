package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.mapper.AbtBookingRequestMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtBookingRequestDao {

    @Resource
    private AbtBookingRequestMapper abtBookingRequestMapper;

    public AbtBookingRequest findByOrder(Long orderId, Long flightItemId) {
        AbtBookingRequest param = new AbtBookingRequest();
        param.setOrderId(orderId);
        param.setFlightItemId(flightItemId);
        return abtBookingRequestMapper.selectOne(param);
    }

    public Long save(AbtBookingRequest record) {
        abtBookingRequestMapper.insertSelective(record);
        return record.getId();
    }

    public AbtBookingRequest findById(Long id) {
        return abtBookingRequestMapper.selectByPrimaryKey(id);
    }

    public List<AbtBookingRequest> queryCurDateRequestsByStatus(int status) {
        return abtBookingRequestMapper.queryCurDateRequestsByStatus(status);
    }
}