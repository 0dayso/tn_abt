package com.tuniu.abt.service.issue.task;

import com.tuniu.abt.dao.AbtJobItemContextDao;
import com.tuniu.abt.dao.AbtTicketRequestDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.JobConstants;
import com.tuniu.abt.intf.entity.AbtJobItemContext;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.issue.task.delegate.CtripObtainTicketNoDelegate;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 获取携程票号 每5分钟执行一次
 * Created by huangsizhou on 16/3/25.
 */
@Service
public class CtripObtainTicketNoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtripObtainTicketNoService.class);

    @Resource
    private AbtTicketRequestDao abtTicketRequestDao;

    @Resource
    private AbtJobItemContextDao abtJobItemContextDao;

    @Resource
    private CtripObtainTicketNoDelegate ctripObtainTicketNoDelegate;

    public void obtainTicketNo() {

        AbtJobItemContext abtJobItemContext = abtJobItemContextDao.findByJobName(JobConstants.OBTAIN_TICKET_NO);
        int batchSize = BizzConstants.BATCH_OBTAIN_TICKET_NO_SIZE;

        Example example = new Example(AbtTicketRequest.class); //查询一次请求中所有的pnr均支付成功的订单
        example.createCriteria().andIn("status", Arrays.asList(AbtTicketRequest.PAY_SUCCESS, AbtTicketRequest.PAY_PART_SUCCESS)).andEqualTo("vendorId", BizzConstants.V_CTRIP);
        int count = abtTicketRequestDao.count(example);
        int pageCount = (count % batchSize == 0) ? count / batchSize : count / batchSize + 1;

        for (int i = 0; i < pageCount; i++) {
            RowBounds rowBounds = new RowBounds(batchSize * (pageCount - 1), batchSize);
            List<AbtTicketRequest> ticketRequests = abtTicketRequestDao.selectByExampleAndRowBounds(example, rowBounds);
            List<Long> ids = new LinkedList<Long>();

            for (AbtTicketRequest ticketRequest : ticketRequests) {
                ids.add(ticketRequest.getId());
            }
            abtTicketRequestDao.batchUpdateStatusById(BizzConstants.ISSUE_BATCH_PROCESSING, ids);//先将状态置为处理中以降低表数据出现死锁的可能性.紧挨着的两次任务如果处理同一批数据很有可能出现数据死锁

            for (AbtTicketRequest ticketRequest : ticketRequests) {
                try {
                    ctripObtainTicketNoDelegate.obtainTicketNo(ticketRequest, abtJobItemContext);
                } catch (Exception e) {
                    LOGGER.error("执行携程获取票号任务失败", e);
                }
            }

        }
    }
}