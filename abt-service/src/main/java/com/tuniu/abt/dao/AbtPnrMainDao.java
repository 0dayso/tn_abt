package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.mapper.AbtPnrMainMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class AbtPnrMainDao {

    @Resource
    private AbtPnrMainMapper abtPnrMainMapper;

    public AbtPnrMain findByOrderAndPnr(@Param("orderId") Long orderId, @Param("pnr") String pnr) {
        AbtPnrMain param = new AbtPnrMain();
        param.setOrderId(orderId);
        param.setPnr(pnr);
        return abtPnrMainMapper.selectOne(param);
    }

    public List<AbtPnrMain> findByOrderId(Long orderId) {
        AbtPnrMain param = new AbtPnrMain();
        param.setOrderId(orderId);
        param.setStatus(AbtPnrMain.STATUS_INIT);
        return abtPnrMainMapper.select(param);
    }

    public int updateStatus(Long id, int status) {
        AbtPnrMain param = new AbtPnrMain();
        param.setId(id);
        param.setStatus(status);
        return abtPnrMainMapper.updateByPrimaryKeySelective(param);
    }

    public int updateStatusByOrderIdAndPnr(Long orderId, String pnr, int status) {
        return abtPnrMainMapper.updateStatusByOrderIdAndPnr(orderId, pnr, status);
    }

    public List<AbtPnrMain> findListByRequestId(Long requestId) {
        AbtPnrMain param = new AbtPnrMain();
        param.setRequestId(requestId);
        return abtPnrMainMapper.select(param);
    }

    public Long save(AbtPnrMain abtPnrMain) {
        abtPnrMainMapper.insertSelective(abtPnrMain);
        return abtPnrMain.getId();
    }

    public List<AbtPnrMain> findPnrMainList(List<String> pnrList) {
        return abtPnrMainMapper.findPnrMainList(pnrList);
    }

    public List<AbtPnrMain> findPnrsByOutOrderId(String outOrderId) {
        AbtPnrMain param = new AbtPnrMain();
        param.setOutOrderId(outOrderId);
        return abtPnrMainMapper.select(param);
    }

    public AbtPnrMain findByOutMainOrderId(String outMainOrderId) {
        AbtPnrMain param = new AbtPnrMain();
        param.setOutMainOrderId(outMainOrderId);

        return abtPnrMainMapper.selectOne(param);
    }

    public int updatePayStatus(Long flightItemId, Long orderId, String pnr, int status) {
        return abtPnrMainMapper.updatePayStatus(flightItemId, orderId, pnr, status);
    }

    public List<AbtPnrMain> findByFlightItemIdAndOrderId(Long flightItemId, Long orderId) {
        return abtPnrMainMapper.findByFlightItemIdAndOrderId(flightItemId, orderId);
    }

    public AbtPnrMain findById(Long id){
        return abtPnrMainMapper.selectByPrimaryKey(id);
    }

    public List<AbtPnrMain> findByStatusAndSpecificTimeInterval(int status, Date from, Date to) {
        return abtPnrMainMapper.findByStatusAndSpecificTimeInterval(status, from, to);
    }

    public List<AbtTicketMain> buildAbtTicketMainByBookData(Long orderId, Long flightItemId) {
        return abtPnrMainMapper.buildAbtTicketMainByBookData(orderId, flightItemId);
    }

}