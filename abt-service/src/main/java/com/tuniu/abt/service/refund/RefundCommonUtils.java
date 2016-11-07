package com.tuniu.abt.service.refund;

import com.tuniu.abt.intf.constants.RefundEx;
import com.tuniu.abt.intf.dto.refund.ProcRefundData;
import com.tuniu.abt.intf.entity.AbtRefundItem;
import com.tuniu.abt.intf.entity.AbtTicketMain;
import com.tuniu.abt.intf.exception.BizzException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 工具
 * Created by chengyao on 2016/4/19.
 */
public class RefundCommonUtils {

    public static BigDecimal countTotal(List<AbtRefundItem> refundItemList, boolean auto) {
        return auto ? countRealTotal(refundItemList) : countOrgTotal(refundItemList);
    }

    private static BigDecimal countRealTotal(List<AbtRefundItem> refundItemList) {
        Double total = refundItemList.stream().mapToDouble(item -> safeDouble(item.getRealRefundAmount())).sum();
        return BigDecimal.valueOf(total);
    }

    private static BigDecimal countOrgTotal(List<AbtRefundItem> refundItemList) {
        Double total = refundItemList.stream().mapToDouble(item -> safeDouble(item.getRefundAmount())).sum();
        return BigDecimal.valueOf(total);
    }

    public static void checkDifferenceThreshold(List<AbtRefundItem> refundItemList, float threshold) {
        boolean hasBeyondThreshold = refundItemList.stream().filter((n)->{
            BigDecimal v1 = safeBigDecimal(n.getRealRefundAmount());
            BigDecimal v2 = safeBigDecimal(n.getRefundAmount());
            return v1.subtract(v2).abs().floatValue() > threshold;
        }).findFirst().isPresent();

        if (hasBeyondThreshold) {
            throw new BizzException(RefundEx.REFUND_DIFFERENCE_FAIL,
                    new Object[] { RefundCommonUtils.buildRefundPriceString(refundItemList) });
        }
    }

    // 执行成功后更新对应对象的状态。
    public static void updateDealStatus(List<AbtTicketMain> abtTicketMains, ProcRefundData procRefundData) {
        abtTicketMains.forEach(n -> {
            n.setStatus(AbtTicketMain.STATUS_REFUNDED);
            AbtRefundItem abtRefundItem = findByTicketNo(procRefundData.getAbtRefundItems(), n.getTicketNo());
            if (abtRefundItem != null)
                abtRefundItem.setStatus(AbtRefundItem.STATUS_OK);
        });
    }

    public static AbtRefundItem findByTicketNo(List<AbtRefundItem> items , String ticketNo) {
        return items.stream().filter(n->n.getTicketNo().equals(ticketNo)).findFirst().orElse(null);
    }

    private static String buildRefundPriceString(List<AbtRefundItem> refundItemList) {
        StringBuilder sb = new StringBuilder();
        for (AbtRefundItem abtRefundItem : refundItemList) {
            sb.append("票号：").append(abtRefundItem.getTicketNo()).append(",");
            sb.append("客价：").append(abtRefundItem.getRefundAmount()).append(",");
            sb.append("实价：").append(abtRefundItem.getRealRefundAmount()).append(";");
        }
        return sb.toString();
    }

    private static BigDecimal safeBigDecimal(Float f) {
        return BigDecimal.valueOf(safeDouble(f));
    }

    private static Double safeDouble(Float f) {
        return f == null ? 0 : Double.valueOf(String.valueOf(f));
    }


    public static void main(String[] args) {
        List<AbtRefundItem> refundItemList = Arrays.asList(new AbtRefundItem(), new AbtRefundItem());
        refundItemList.get(0).setRealRefundAmount(1.03f);
        refundItemList.get(0).setRefundAmount(2.056f);
        try {
            checkDifferenceThreshold(refundItemList, 1.25f);
        } catch (BizzException ex) {
            System.out.println(ex.getMessageArgs()[0]);
        }
    }

}
