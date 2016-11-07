package com.tuniu.abt.service.issue.airline;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPriceDao;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.intf.airline.AirDirectConnectPayResponse;
import com.tuniu.abt.intf.dto.intf.airline.AirDirectConnectTicketingResponse;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.request.OrderInfo;
import com.tuniu.abt.intf.dto.issue.response.IssueResult;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.airline.AirlineFacadeService;
import com.tuniu.abt.service.issue.AbstractIssueProcessor;
import com.tuniu.mauritius.price.constants.CurrencyType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by huangsizhou on 16/3/24.
 */
@Service
public class AirlineIssueProcessor extends AbstractIssueProcessor {

    @Resource
    private AirlineFacadeService airlineFacadeService;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    public IssueResult issue(IssueRequest issueRequest) {

        IssueResult issueResult = new IssueResult();//TODO 0406 hsz 由list改为单个请求.简化处理逻辑

        AirLinePayReq airLinePayReq = preparePayRequest(issueRequest);

        //支付
        AirDirectConnectPayResponse airDirectPayResult = airlineFacadeService.pay(airLinePayReq.getC(), airLinePayReq.getOrderId(), airLinePayReq.getTotalPrice(), airLinePayReq.getCurrency());

        InnerIssueResultDto resultDto = new InnerIssueResultDto();
        AirDirectConnectTicketingResponse airDirectIssueResult = null;

        if (!airDirectPayResult.isSuccess()) {//支付失败
            issueResult.setIssueStatus(AbtTicketRequest.PAY_FAIL);
            issueResult.setMsg(airDirectPayResult.getMessage());
            issueResult.setErrorCode(TicketEx.PAY_ERROR);
            issueResult.setSuccess(false);
            resultDto.setSuccess(false);
            resultDto.setErrorCode(TicketEx.PAY_ERROR);
            resultDto.setMsg(airDirectPayResult.getMessage());
        } else if ((airDirectIssueResult = airlineFacadeService.ticketOut(airLinePayReq.getC(), airLinePayReq.getOrderId())).isSuccess()) {//支付成功 并且 调用出票未返回错误
            issueResult.setIssueStatus(AbtTicketRequest.PAY_SUCCESS);//支付成功并且调用出票接口未返回错误
            issueResult.setSuccess(true);
        } else {//支付成功 调用出票返回错误
            issueResult.setSuccess(false);
            issueResult.setIssueStatus(AbtTicketRequest.ISSUE_FAIL);
            issueResult.setMsg(airDirectIssueResult.getMessage());
            issueResult.setErrorCode(TicketEx.OBTAIN_TICKET_ERROR);
            resultDto.setSuccess(false);
            resultDto.setErrorCode(TicketEx.OBTAIN_TICKET_ERROR);
            resultDto.setMsg(airDirectIssueResult.getMessage());
        }

        issueResult.addResult(airLinePayReq.getOrderId(), resultDto);
        issueResult.setInnerData(airDirectIssueResult);

        return issueResult;
    }

    public AirLinePayReq preparePayRequest(IssueRequest issueRequest) {

        //将请求报文中的pnr列表信息取出 再存占位表查出对应信息
        List<OrderInfo> orderInfos = issueRequest.getIssueDetail().getOrderIds();

        OrderInfo orderInfo = orderInfos.get(0);
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<AbtPnrPrice> abtPnrPrices = abtPnrPriceDao.findByPnrAndFlightItemIdAndOrderId(orderInfo.getPnr(), issueRequest.getIssueDetail().getFlightItemId(), Long.valueOf(issueRequest.getOrderId()));
        for (AbtPnrPrice pnrPrice : abtPnrPrices) {
            totalPrice = totalPrice.add(pnrPrice.getFloorPrice()).add(BigDecimal.valueOf(pnrPrice.getAirportTax())).add(BigDecimal.valueOf(pnrPrice.getFuelSurcharge()));//TODO这里传什么值
        }

        AbtPnrMain abtPnrMain = abtPnrMainDao.findByOutMainOrderId(orderInfo.getPnr());//首航直联外部订单号就是票务请求中的pnr

        AirLinePayReq airLinePayReq = new AirLinePayReq();
        airLinePayReq.setTotalPrice(totalPrice.intValue());
        airLinePayReq.setOrderId(orderInfo.getPnr());
        airLinePayReq.setC(abtPnrMain.getTicketingCarrier());
        airLinePayReq.setCurrency(CurrencyType.CNY.getCode());

        return airLinePayReq;
    }


}
