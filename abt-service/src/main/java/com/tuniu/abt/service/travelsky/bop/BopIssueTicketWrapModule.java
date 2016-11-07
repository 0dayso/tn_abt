package com.tuniu.abt.service.travelsky.bop;

import com.dovepay.service.PaySign;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusInterfaceAdapterService;
import com.tuniu.abt.service.travelsky.ibeplus.IbePlusIntf;
import com.tuniu.abt.utils.SimpleXmlMapper;
import com.tuniu.abt.utils.XmlParseException;
import com.tuniu.adapter.flightTicket.webservice.ContentType;
import com.tuniu.flight.connector.client.ibeplus.convert.domestic.FlightIssueTicketByBopConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lanlugang on 2016/4/28.
 */
@Service
public class BopIssueTicketWrapModule {

    @Resource
    private IbePlusInterfaceAdapterService ibePlusInterfaceAdapterService;

    private static final String BOP_SUCCESS_CODE = "BIATKT0000";

    /**
     *
     * Description: BOP出票<br>
     *
     * @author lanlugang<br>
     * @taskId <br>
     * @param pnr
     * @return <br>
     */
    public void issueTicketByBop(String pnr) {
        try {
            ContentType requestContentType = FlightIssueTicketByBopConvert.toRequest(pnr);
            String response = ibePlusInterfaceAdapterService
                    .call(IbePlusIntf.AIR_ISSUE_TICKET_BY_BOP, object2Xml(requestContentType));
            ContentType result = SimpleXmlMapper.toBean(ContentType.class, response);
            if (null == result || null == result.getTransactions()) {
                throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_ERROR, "BOP出票响应结果异常");
            } else if (!BOP_SUCCESS_CODE
                    .equals(result.getTransactions().getTRANSACTION().get(0).getRetcode())) {
                throw new BizzException(BizzEx.IBE_PLUS_INTF_RESULT_ERROR, "BOP出票失败, 失败原因:"
                        + result.getTransactions().getTRANSACTION().get(0).getRetmemo());
            }
        } catch (BizzException bizEx) {
            throw bizEx;
        } catch (Exception ex) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_EX, "BOP调用异常:" + ex.getMessage(), ex);
        }
    }

    private String object2Xml(ContentType requestContentType) {
        String requestXml;
        try {
            requestXml = SimpleXmlMapper.toXmlString(requestContentType);
            requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + requestXml;
        } catch (XmlParseException ex) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "BOP出票失败, 请求BOP入参转换异常");
        }

        String signmsg = null;
        PaySign paySign = new PaySign();
        try {
            signmsg = paySign.sign(requestXml, "60FQOZLOVFEUMCJSN7DD2S46");
        } catch (Exception e) {
            throw new BizzException(BizzEx.IBE_PLUS_INTF_REQ_PARAM_EX,
                    "BOP出票失败, 请求BOP入参转换加密异常");
        }
        return "transactiontype=payOutTkt&txnxml="
                + requestXml + "&signmsg=" + signmsg;
    }

}
