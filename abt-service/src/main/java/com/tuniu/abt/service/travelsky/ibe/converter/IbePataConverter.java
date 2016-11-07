package com.tuniu.abt.service.travelsky.ibe.converter;

import com.travelsky.ibe.client.pnr.FareCalculation;
import com.travelsky.ibe.client.pnr.PATFareItem;
import com.travelsky.ibe.client.pnr.PATResult;
import com.tuniu.abt.service.travelsky.dto.PnrFareItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lanlugang on 2016/4/28.
 */
public class IbePataConverter {

    public static PnrFareItem convert2SegFareItem(PATResult result) {
        return convert2FareItem(result, true, true);
    }

    public static List<PnrFareItem> convert2SegFareItems(PATResult result) {
        return convert2FareItems(result, true, true);
    }

    public static PnrFareItem convert2PnrFareItem(PATResult result, boolean isFnFormat) {
        return convert2FareItem(result, false, isFnFormat);
    }

    private static PnrFareItem convert2FareItem(PATResult result, boolean isFnTxt, boolean isFnFormat) {
        List<PnrFareItem> fareItems = convert2FareItems(result, isFnTxt, isFnFormat);
        if (CollectionUtils.isNotEmpty(fareItems)) {
            Collections.sort(fareItems, (o1,o2)->Double.compare(o1.getFare(), o2.getFare()));
            return fareItems.get(0);
        }
        return null;
    }

    private static List<PnrFareItem> convert2FareItems(PATResult result, boolean isFnTxt, boolean isFnFormat) {
        List<PnrFareItem> fareItems = new ArrayList<PnrFareItem>();
        for (int x = 0; x < result.farenumber; x++) {
            PATFareItem item = result.getFareItem(x);
            String priceString = null;
            if (isFnTxt) {
                priceString = item.getFnText();
            } else {
                priceString = item.getFare();
            }
            PnrFareItem pnrFareItem = null;
            if (isFnFormat || isFnTxt) {
                pnrFareItem = parseFnTxtInfo(priceString, item.getFc());
            } else {
                pnrFareItem = parseFareInfo(priceString);
            }
            if (null == pnrFareItem || pnrFareItem.getFare() <= 0) {
                continue;
            }
            pnrFareItem.setRph(item.getIndex());
            fareItems.add(pnrFareItem);
        }
        return fareItems;
    }

    /**
     * 解析fd运价
     *  eg: FN FCNY 1180.00/ SCNY1180.00/ C0.00/ TCNY 50.00CN/ TEXEMPTYQ/P1
     *      FN /IN/FCNY 120.00/ SCNY120.00/ C0.00/ TEXEMPTCN/ TEXEMPTYQ/P1
     *      FN/FCNY1180.00/SCNY1180.00/C0.00/TCNY50.00CN/TEXEMPTYQ
     * @param priceString
     * @return
     */
    private static PnrFareItem parseFnTxtInfo(String priceString, FareCalculation fc) {
        if (null == priceString) return null;
        String fn = priceString.split("\n")[0].trim().replaceAll("\\s{1,}", "");
        String[] items = fn.split("/");
        PnrFareItem pnrFareItem = new PnrFareItem();
        for (String item : items) {
            if (item.startsWith("FCNY")) {
                pnrFareItem.setFare(findDoubleValue(item));
            } else if (item.startsWith("SCNY") && pnrFareItem.getFare() == 0) {
                pnrFareItem.setFare(findDoubleValue(item));
            } else if (item.endsWith("CN")) {
                pnrFareItem.setAirPortTax(findDoubleValue(item));
            } else if (item.endsWith("YQ")) {
                pnrFareItem.setFuelSurcharge(findDoubleValue(item));
            }
        }
        pnrFareItem.setTotal(pnrFareItem.getFare()
                + pnrFareItem.getAirPortTax() + pnrFareItem.getFuelSurcharge());
        if (fc != null && fc.getSegmentCnt() > 0) {
            String fareBasecode = null;
            for (int i = 0 ; i < fc.getSegmentCnt(); i++) {
                fareBasecode = StringUtils.isBlank(fareBasecode)
                        ? fc.getSegment(i).getFltclass()
                        : fareBasecode + "/" + fc.getSegment(i).getFltclass();
            }
            pnrFareItem.setFareBasisCode(fareBasecode);
        }
        return pnrFareItem;
    }

    /**
     * 解析fareItem:
     * eg: Y FARE:CNY1240.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1290.00
     * @param fareStr
     * @return
     */
    private static PnrFareItem parseFareInfo(String fareStr) {
        if (null == fareStr) return null;
        String[] items = fareStr.trim().replaceAll("\\s{1,}", " ").split(" ");
        PnrFareItem pnrFareItem = new PnrFareItem();
        pnrFareItem.setFareBasisCode(items[0]);
        for (String item : items) {
            if (item.startsWith("FARE:")) { // FARE
                pnrFareItem.setFare(findDoubleValue(item));
            } else if (item.startsWith("TAX:")) { // CN
                pnrFareItem.setAirPortTax(findDoubleValue(item));
            } else if (item.startsWith("YQ:")) { // YQ
                pnrFareItem.setFuelSurcharge(findDoubleValue(item));
            } else if (item.startsWith("TOTAL:")) {
                pnrFareItem.setTotal(findDoubleValue(item));
            }
        }
        return pnrFareItem;
    }

    private static double findDoubleValue(String str) {
        Pattern p = Pattern.compile("(\\d+\\.?\\d*)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return Double.valueOf(m.group(1));
        }
        return 0;
    }
}
