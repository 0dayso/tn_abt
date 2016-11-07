package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtPnrFlight;
import com.tuniu.abt.mapper.AbtPnrFlightMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtPnrFlightDao {

    @Resource
    private AbtPnrFlightMapper abtPnrFlightMapper;

    public Long save(AbtPnrFlight record) {
        abtPnrFlightMapper.insertSelective(record);
        return record.getId();
    }

    public int saveAbtPnrFlightList(List<AbtPnrFlight> flights) {
        return abtPnrFlightMapper.saveAbtPnrFlightList(flights);
    }

    public AbtPnrFlight findByPnrAndFlightNo(String pnr, String flightNo) {
        return abtPnrFlightMapper.findByPnrAndFlightNo(pnr, flightNo);
    }

    public List<AbtPnrFlight> findByPnrAndFlightItemIdAndOrderId(String pnr, Long flightItemId, Long orderId) {
        return abtPnrFlightMapper.findByPnrAndFlightItemIdAndOrderId(pnr, flightItemId, orderId);
    }

}