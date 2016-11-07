package com.tuniu.aop.unittest.vendor;

import com.alibaba.fastjson.JSON;
import com.travelsky.ibe.client.pnr.RTResult;
import com.travelsky.ibe.client.pnr.TRFDResult;
import com.tuniu.abt.service.travelsky.dto.ReqPassenger;
import com.tuniu.abt.service.travelsky.ibe.IbeInterfaceService;
import com.tuniu.abt.service.travelsky.ibe.converter.RtConverter;
import com.tuniu.abt.service.travelsky.ibe.module.IbeCleanPnrWrapModule;
import com.tuniu.abt.service.travelsky.ibe.module.IbeRtWrapModule;
import com.tuniu.abt.service.travelsky.ibeplus.module.AirBookModifyWrapModule;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/4/13.
 */
public class TravelSkySplitPnrTest extends BaseTest {

    @Resource
    private AirBookModifyWrapModule airBookModifyWrapModule;
    @Resource
    private IbeRtWrapModule ibeRtWrapModule;
    @Resource
    private IbeCleanPnrWrapModule ibeCleanPnrWrapModule;
    @Resource
    private com.tuniu.abt.service.travelsky.ibeplus.module.AirResRetWrapModule airResRetWrapModuleV2;

    @Test
    public void s9() {
        Object t = airResRetWrapModuleV2.rt("KE96GQ");
        System.out.println(t);
    }

    @Test
    public void s() {
        List<ReqPassenger> travelerList = new ArrayList<ReqPassenger>();
        {
            ReqPassenger reqPassenger = new ReqPassenger();
            reqPassenger.setPassengerType(1);
            reqPassenger.setPassengerName("成滔");
            travelerList.add(reqPassenger);
        }

        //HPFVCS 成遥
        //JW87V3 成滔

        airBookModifyWrapModule.splitPnr("JY0YN5", travelerList);
    }

    @Test
    public void s2() {
        try {
            /*
            成人+婴儿 KY9Y0W
儿童 HDYXM9
缺口程 HGNDRK

            * */
            Object p = airResRetWrapModuleV2.rt("HDYXM9");
            System.out.println(JSON.toJSONString(p));

            RTResult rtResult = ibeRtWrapModule.rt("HDYXM9");
            Object t = RtConverter.toPlus(rtResult);
            System.out.println(JSON.toJSONString(t));
            System.out.println();

            System.in.read();
        } catch (Throwable t) {
            t.printStackTrace();
        }


    }

    @Resource
    private IbeInterfaceService ibeInterfaceService;
    @Resource
    private SystemConfig systemConfig;

    @Test
    public void s5() throws Exception {
        TRFDResult trfdResult = ibeInterfaceService.automaticRefund("999-1770459145", 1);
        System.out.println(trfdResult);
    }

    @Test
    public void s4() {
//        Object p = airResRetWrapModule.rtPnr("JYXFTV");
//        System.out.println(JSON.toJSONString(p));
    }

    @Test
    public void s3() {
        ibeCleanPnrWrapModule.cleanPnr("JYXFTV");
    }

    @Test
    public void s7() {

        RTResult rtResult = new RTResult();
        System.out.println(JSON.toJSONString(rtResult));

    }
}
