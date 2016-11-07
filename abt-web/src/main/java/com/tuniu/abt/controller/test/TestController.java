package com.tuniu.abt.controller.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.travelsky.ibe.client.pnr.RTResult;
import com.tuniu.abt.intf.dto.book.main.Segment;
import com.tuniu.abt.service.cachework.FdPriceCacheWorker;
import com.tuniu.abt.service.cachework.dto.FdPriceParam;
import com.tuniu.abt.service.commons.BanFlightFacadeService;
import com.tuniu.abt.service.res.ResourceBaseCacheService;
import com.tuniu.abt.service.travelsky.TravelSkyInterface;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.common.util.JsonUtil;
import com.tuniu.adapter.flightTicket.vo.ibe.rt.RTPnrReply;
import com.tuniu.adapter.flightTicket.vo.ibe.rt.RTPnrVo;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.vla.base.exception.BaseException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chengyao on 2016/4/26.
 */
@RestController
@RequestMapping("/test/")
@Profile("local")
public class TestController {

    @Resource
    private SystemConfig systemConfig;
    @Resource
    private ResourceBaseCacheService resourceBaseCacheService;
    @Resource
    private FdPriceCacheWorker fdPriceCacheWorker;
    @Resource
    private BanFlightFacadeService banFlightFacadeService;
    @Resource
    private TravelSkyInterface  travelSkyInterface;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root(@Json Integer id) {
        return new ModelAndView("redirect:/statics/html/index.html");
    }
    @RequestMapping(value = "/fdprice", method = RequestMethod.GET)
    public Object fdprice(@Json FdPriceParam fdPriceParam) {
        Object o = fdPriceCacheWorker.find(fdPriceParam);
        return o;
    }

    @RequestMapping(value = "/getEx", method = RequestMethod.GET)
    public Object getEx() {
        throw new BaseException(111111, "出错咯！");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        // banFlightFacadeService.process();
        resourceBaseCacheService.getAirport("TFD");
        System.out.println(systemConfig.getEnvName());
    }

    @RequestMapping(value = "/supplier/flight/ibe/rt", method = RequestMethod.GET)
    public Object rt(@Json RTPnrVo rtPnrVo) {
        RTPnrReply reply = new RTPnrReply();
        throw new BaseException(100123, "123123");
    }

    @RequestMapping(value = "/test/empty")
    public Object rt(@Json JSONObject jsonObject) {
        System.out.println("receive request:" + JSON.toJSONString(jsonObject));

        RTResult rtResult = new RTResult();
        System.out.println(rtResult);
        System.out.println(JSON.toJSONString(rtResult));

        return null;
    }

    @RequestMapping(value = "/pataBySeg", method = RequestMethod.GET)
    public Object pataBySeg(@Json JSONObject jsonObject) {
        String psgType = (String) jsonObject.get("psgType");
        List<Map<String, Object>> segmentArray  = JsonUtil.getList(jsonObject, "segments");
        List<Segment> segments = new ArrayList<Segment>();
        for (Map<String, Object> map : segmentArray) {
            Segment segment = JsonUtil.toBean(map, Segment.class);
            segments.add(segment);
        }
        return travelSkyInterface.airPriceBySegD(segments, psgType);
    }
}
