package com.tuniu.abt.intf.dto.book.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/4/8.
 */
public enum BookVendorId {

    TRAVELSKY(1),CTRIP(6),AOP(8),AIRLINE(9);

    private int vendorId;

    BookVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int intValue() {
        return  vendorId;
    }

    public static List<Integer> getAllVendorIds() {
        List<Integer> vendorIds = new ArrayList<Integer>();
        for (BookVendorId bookVendorId : BookVendorId.values()) {
            vendorIds.add(bookVendorId.intValue());
        }
        return vendorIds;
    }
}
