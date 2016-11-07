package com.tuniu.abt.dao;

import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.mapper.AbtTicketMainMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AbtTicketMainDao {

    @Resource
    private AbtTicketMainMapper abtTicketMainMapper;

    public List<AbtTicketMain> findListByRequestId(Long requestId) {
        AbtTicketMain param = new AbtTicketMain();
        param.setRequestId(requestId);
        return abtTicketMainMapper.select(param);
    }

    public int batchInsert(List<AbtTicketMain> abtTicketMains) {
        for (AbtTicketMain abtTicketMain : abtTicketMains) {
            abtTicketMainMapper.insertSelective(abtTicketMain);
        }
        return abtTicketMains.size();
    }

    public List<AbtTicketMain> findByRequestId(long id) {
        AbtTicketMain ticketMain = new AbtTicketMain();
        ticketMain.setRequestId(id);

        return abtTicketMainMapper.select(ticketMain);
    }

    public int updateStatus(Long id, int status) {
        AbtTicketMain param = new AbtTicketMain();
        param.setId(id);
        param.setStatus(status);
        return abtTicketMainMapper.updateByPrimaryKeySelective(param);
    }

    public int updateTicketNo(Long requestId, String pnr, String flightNo, String passengerName, String ticketNo, int status) {
        AbtTicketMain param = new AbtTicketMain();
        param.setTicketNo(ticketNo);
        param.setStatus(status);

        Example example = new Example(AbtTicketMain.class);
        example.createCriteria()
                .andEqualTo("requestId", requestId)
                .andEqualTo("pnr", pnr)
                .andEqualTo("flightNo", flightNo)
                .andEqualTo("passengerName", passengerName);

        return abtTicketMainMapper.updateByExampleSelective(param, example);
    }
}