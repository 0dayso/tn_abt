package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtPnrPassenger;
import com.tuniu.abt.mapper.AbtPnrPassengerMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtPnrPassengerDao {

    @Resource
    private AbtPnrPassengerMapper abtPnrPassengerMapper;

    public List<AbtPnrPassenger> findByMainPnrId(Long pnrId){
        AbtPnrPassenger pnrPassenger = new AbtPnrPassenger();
        pnrPassenger.setPnrId(pnrId);
        return abtPnrPassengerMapper.select(pnrPassenger);
    }

    public List<AbtPnrPassenger> findAvailableByMainPnrId(Long pnrId){
        AbtPnrPassenger pnrPassenger = new AbtPnrPassenger();
        pnrPassenger.setPnrId(pnrId);
        pnrPassenger.setStatus(AbtPnrPassenger.STATUS_INIT);
        return abtPnrPassengerMapper.select(pnrPassenger);
    }

    public AbtPnrPassenger findByMainPnrIdAndName(Long pnrId, String name) {
        AbtPnrPassenger pnrPassenger = new AbtPnrPassenger();
        pnrPassenger.setPnrId(pnrId);
        pnrPassenger.setBookName(name);
        return abtPnrPassengerMapper.selectOne(pnrPassenger);
    }

    public Long save(AbtPnrPassenger record) {
        abtPnrPassengerMapper.insertSelective(record);
        return record.getId();
    }

    public int updatePassengerStatus(Long id, int status) {
        AbtPnrPassenger updateObject = new AbtPnrPassenger();
        updateObject.setStatus(status);
        updateObject.setId(id);
        return abtPnrPassengerMapper.updateByPrimaryKeySelective(updateObject);
    }

    public List<AbtPnrPassenger> findByPnrAndFlightItemIdAndOrderId(String pnr, Long flightItemId, Long orderId){
        return abtPnrPassengerMapper.findByPnrAndFlightItemIdAndOrderId(pnr, flightItemId, orderId);
    }

    public AbtPnrPassenger findByPnrAndFlightItemIdAndOrderIdAndName(String pnr, Long flightItemId, Long orderId, String name){
        return abtPnrPassengerMapper.findByPnrAndFlightItemIdAndOrderIdAndName(pnr, flightItemId, orderId, name);
    }

    public AbtPnrPassenger findById(Long id){
        return abtPnrPassengerMapper.selectByPrimaryKey(id);
    }

    public AbtPnrPassenger findByOrderIdAndPnrAndPersonId(Long orderId, String pnr, Long personId) {
        return abtPnrPassengerMapper.findByOrderIdAndPnrAndPersonId(orderId, pnr, personId);
    }
}