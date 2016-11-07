package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.mapper.AbtTicketRequestMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtTicketRequestDao {

    @Resource
    private AbtTicketRequestMapper abtTicketRequestMapper;

    public AbtTicketRequest findByOrder(Long orderId, Long flightItemId) {
        AbtTicketRequest param = new AbtTicketRequest();
        param.setOrderId(orderId);
        param.setFlightItemId(flightItemId);
        return abtTicketRequestMapper.selectOne(param);
    }

    public int save(AbtTicketRequest abtTicketRequest) {
        return abtTicketRequestMapper.insertSelective(abtTicketRequest);
    }

    public List<AbtTicketRequest> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return abtTicketRequestMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    public int count(Example example) {
        return abtTicketRequestMapper.selectCountByExample(example);
    }


    public int batchUpdateStatusById(int status, List<Long> ids) {
        return abtTicketRequestMapper.batchUpdateStatusById(status, ids);
    }

    public int batchUpdateCallbackStatusById(int callbackStatus, List<Long> ids) {
        return abtTicketRequestMapper.batchUpdateCallbackStatusById(callbackStatus, ids);
    }

    public int updateStatusById(int status, Long id) {
        AbtTicketRequest ticketRequest = new AbtTicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setStatus(status);
        return abtTicketRequestMapper.updateByPrimaryKeySelective(ticketRequest);
    }

    public int updateStatusAndCallbackStatusById(int status, int callbackStatus, Long id) {
        AbtTicketRequest ticketRequest = new AbtTicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setStatus(status);
        ticketRequest.setCallBackStatus(callbackStatus);
        return abtTicketRequestMapper.updateByPrimaryKeySelective(ticketRequest);
    }

    public int updateStatusAndTicketOrderIdById(int status, Long ticketOrderId, String aopVendorId, Long id) {

        AbtTicketRequest ticketRequest = new AbtTicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setStatus(status);
        ticketRequest.setTicketOrderId(ticketOrderId);
        ticketRequest.setAopVendorId(aopVendorId);

        return abtTicketRequestMapper.updateByPrimaryKeySelective(ticketRequest);

    }


    public int updateStatusAndCallbackStatus(int status, int callbackStatus, String sessionId, Long flightItemId, Long orderId) {
        Example example = new Example(AbtTicketRequest.class);
        example.createCriteria().andEqualTo("sessionId", sessionId).andEqualTo("flightItemId", flightItemId).andEqualTo("orderId", orderId);

        AbtTicketRequest abtTicketRequest = new AbtTicketRequest();
        abtTicketRequest.setStatus(status);
        abtTicketRequest.setCallBackStatus(callbackStatus);

        return abtTicketRequestMapper.updateByExampleSelective(abtTicketRequest, example);
    }

    public int updateCallbackStatusById(int callbackStatus, Long id) {
        AbtTicketRequest ticketRequest = new AbtTicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setCallBackStatus(callbackStatus);
        return abtTicketRequestMapper.updateByPrimaryKeySelective(ticketRequest);
    }

    public List<AbtTicketRequest> findByFlightItemIdAndOrderId(Long flightItemId, Long orderId) {
        AbtTicketRequest ticketRequest = new AbtTicketRequest();
        ticketRequest.setFlightItemId(flightItemId);
        ticketRequest.setOrderId(orderId);

        return abtTicketRequestMapper.select(ticketRequest);
    }

    public AbtTicketRequest findById(Long id) {
        return abtTicketRequestMapper.selectByPrimaryKey(id);
    }


    public AbtTicketRequest findBySessionId(String sessionId) {
        AbtTicketRequest param = new AbtTicketRequest();
        param.setSessionId(sessionId);
        return abtTicketRequestMapper.selectOne(param);
    }
}

