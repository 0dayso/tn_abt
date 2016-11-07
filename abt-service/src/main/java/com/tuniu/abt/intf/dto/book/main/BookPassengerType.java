package com.tuniu.abt.intf.dto.book.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanlugang on 2016/4/8.
 */
public enum BookPassengerType {

    ADT(1),CHD(2),INF(3);

    private int code;

    BookPassengerType(int code) {
        this.code = code;
    }

    public int intValue() {
        return code;
    }

    public static List<String> getAllPsgTypes() {
        List<String> psgTypes = new ArrayList<String>();
        for (BookPassengerType bookPassengerType : BookPassengerType.values()) {
            psgTypes.add(bookPassengerType.name());
        }
        return psgTypes;
    }

    public static String getPsgType(int code) {
        for (BookPassengerType bookPassengerType : BookPassengerType.values()) {
            if(code == bookPassengerType.intValue()) {
                return bookPassengerType.name();
            }
        }
        return null;
    }

}
