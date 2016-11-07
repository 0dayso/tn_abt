package com.tuniu.aop.unittest.order;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.dbservice.AbtTicketMainService;
import com.tuniu.abt.intf.dto.issue.response.PnrInfo;
import com.tuniu.abt.service.issue.travelsky.module.TravelSkyTicketInfoHandler;
import com.tuniu.abt.utils.TsConfig;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lanlugang on 2016/5/22.
 */
public class IssueTravelskyTest extends BaseTest {

    @Resource
    private TravelSkyTicketInfoHandler travelSkyTicketInfoHandler;

    @Resource
    private AbtTicketMainService abtTicketMainService;

    @Resource
    private TsConfig tsConfig;

    @Test
    public void testRtTicketInfo() {

        PnrInfo pnrInfo = new PnrInfo();

        pnrInfo.setOrderId("KM9Q89"); // KD7TE4 KD7TE7 JQ48EV KM9Q89 JDZNW9
//        pnrInfo.setNewOrderId("JDZNW9");
        pnrInfo.setSolutionId("18884");
        pnrInfo.setSolutionName(tsConfig.getSolutionName(pnrInfo.getSolutionId()));

        try {
            travelSkyTicketInfoHandler.rtTicketInfo(pnrInfo);
            abtTicketMainService.saveTicketInfo(1L, pnrInfo);
            System.out.println(JSON.toJSONString(pnrInfo));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
