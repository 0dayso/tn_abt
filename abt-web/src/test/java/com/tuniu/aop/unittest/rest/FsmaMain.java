package com.tuniu.aop.unittest.rest;

import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.entity.MainOrderVendorPnr;
import com.tuniu.vla.base.utils.TypeReference;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by chengyao on 2016/3/11.
 */
public class FsmaMain {

    public static void main(String[] args) {
        Type type = new TypeReference<Collection<MainOrderVendorPnr>>(){}.getType();
        System.out.println(type);

        Type type2 = new TypeReference<Collection<MainOrderVendorPnr>>(){}.getType();
        System.out.println(type2);

    }
}
