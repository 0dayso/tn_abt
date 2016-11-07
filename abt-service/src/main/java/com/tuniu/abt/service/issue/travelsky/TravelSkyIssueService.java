package com.tuniu.abt.service.issue.travelsky;

import com.tuniu.abt.dao.AbtPnrMainDao;
import com.tuniu.abt.dao.AbtPnrPriceDao;
import com.tuniu.abt.dbservice.AbtTicketMainService;
import com.tuniu.abt.intf.constants.BizzConstants;
import com.tuniu.abt.intf.constants.TicketEx;
import com.tuniu.abt.intf.dto.issue.other.InnerIssueResultDto;
import com.tuniu.abt.intf.dto.issue.request.IssueRequest;
import com.tuniu.abt.intf.dto.issue.response.*;
import com.tuniu.abt.intf.entity.AbtPnrMain;
import com.tuniu.abt.intf.entity.AbtPnrPrice;
import com.tuniu.abt.intf.entity.AbtTicketRequest;
import com.tuniu.abt.service.issue.IssueDataSupport;
import com.tuniu.abt.service.issue.IssueService;
import com.tuniu.abt.service.issue.travelsky.module.TravelSkyTicketInfoHandler;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangsizhou on 2016/03/09.
 */
@Service
public class TravelSkyIssueService implements IssueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelSkyIssueService.class);

    @Resource
    private TravelSkyTicketInfoHandler travelSkyTicketInfoHandler;

    @Resource
    private AbtTicketMainService abtTicketMainService;

    @Resource
    private TravelSkyIssueProcessor travelSkyIssueProcessor;

    @Resource
    private AbtPnrPriceDao abtPnrPriceDao;

    @Resource
    private AbtPnrMainDao abtPnrMainDao;

    @Override
    public IssueResult issueTicket(IssueRequest issueRequest) {
        IssueResult issueResult = travelSkyIssueProcessor.issueTicket(issueRequest);
        handleIssueResult(issueRequest, issueResult);
        return issueResult;
    }

    /**
     * 处理航信票号信息接口返回(遵循的规则是供应商返回中包含则以供应商为准, 没有的信息从数据库中取)
     *
     * @param issueRequest 出票请求
     * @param issueResult  出票结果
     */
    public void handleIssueResult(IssueRequest issueRequest, IssueResult issueResult) {
        IssueResponse issueResponse = new IssueResponse();
        issueResponse.setSuccess(issueResult.isSuccess());
        issueResponse.setSessionId(issueRequest.getSessionId());
        issueResponse.setIntl(0);
        issueResponse.setOrderId(issueRequest.getOrderId());
        issueResponse.setSystemId(issueRequest.getSystemId());
        if (!issueResult.isSuccess()) {
            issueResponse.setMsg(issueResult.getMsg());
        }
        List<PnrInfo> pnrInfoList = assemblePnrInfos(Long.valueOf(issueRequest.getOrderId()), issueResult, true);
        IssueResultDetail issueDetail = new IssueResultDetail(issueRequest.getIssueDetail().getFlightItemId(), "", pnrInfoList);
        issueResponse.setData(issueDetail);
        IssueDataSupport.setData(issueResponse);
    }

    /**
     * 组装票号数据
     * @param issueResult
     * @return
     */
    public List<PnrInfo> assemblePnrInfos(Long orderIdTuniu, IssueResult issueResult, boolean updateSalePrice) {
        AbtTicketRequest ticketRequest = issueResult.getInnerData();
        List<PnrInfo> pnrInfoList = new ArrayList<>();
        for (Map.Entry<String, InnerIssueResultDto> entry : issueResult.getResultMap().entrySet()) {
            PnrInfo pnrInfo = new PnrInfo();
            pnrInfoList.add(pnrInfo);
            pnrInfo.setOrderId(entry.getKey());
            pnrInfo.setVendorId(ticketRequest.getVendorId());
            pnrInfo.setIssueFlag(entry.getValue().isSuccess());
            if (!pnrInfo.isIssueFlag()) {
                pnrInfo.setMsg(entry.getValue().getMsg());
                pnrInfo.setErrorCode(entry.getValue().getErrorCode());
                continue;
            }
            pnrInfo.setSolutionId(entry.getValue().getSolutionId());
            pnrInfo.setSolutionName(entry.getValue().getSolutionName());
            /**rt获取票号信息 */
            travelSkyTicketInfoHandler.rtTicketInfo(pnrInfo);
            /**票号信息入库 */
            saveTicketInfo(ticketRequest.getId(), pnrInfo);
            /**更新abt_pnr_main表的状态为出票成功*/
            abtPnrMainDao.updateStatusByOrderIdAndPnr(orderIdTuniu, entry.getKey(), AbtPnrMain.STATUS_TICKET_OK);
            /**更新售卖价格 */
            if (updateSalePrice) {
                updateSalePriceFromDB(ticketRequest.getOrderId(), ticketRequest.getFlightItemId(), pnrInfo);
            }
        }
        /**儿童pnr备注成人票号 */
        travelSkyTicketInfoHandler.addAdtTicketNoForChdPnr(pnrInfoList);
        return pnrInfoList;
    }

    private void saveTicketInfo(Long requestId, PnrInfo pnrInfo) {
        try {
            abtTicketMainService.saveTicketInfo(requestId, pnrInfo);
        } catch (Exception e) {
            LOGGER.error(pnrInfo.getOrderId()+"票号信息入库异常", e);
            setPnrInfoFalse(pnrInfo, TicketEx.SAVE_TICKET_TO_DB_FAILED, pnrInfo.getOrderId()+"票号信息入库异常");
        }
    }

    private void updateSalePriceFromDB(Long orderId, Long flightItemId, PnrInfo pnrInfo) {
        List<AbtPnrPrice> pricesFromDB = null;
        Map<String, BigDecimal> priceMap = new HashMap<>();
        try {
            pricesFromDB = abtPnrPriceDao.findByPnrAndFlightItemIdAndOrderId(pnrInfo.getOrderId(), flightItemId, orderId);
            for (AbtPnrPrice abtPnrPrice : pricesFromDB) {
                if (abtPnrPrice.getPriceType() == BizzConstants.PERSON_TYPE_ADULT) {
                    priceMap.put(BizzConstants.PSG_TYPE_CODE_ADT, abtPnrPrice.getSalePrice());
                } else if (abtPnrPrice.getPriceType() == BizzConstants.PERSON_TYPE_BABY) {
                    priceMap.put(BizzConstants.PSG_TYPE_CODE_INF, abtPnrPrice.getSalePrice());
                }
            }
        } catch (Exception e) {
            LOGGER.error("从库中获取售卖价格失败", e);
        }
        if (CollectionUtils.isEmpty(pricesFromDB)) {
            setPnrInfoFalse(pnrInfo, TicketEx.QUERY_PRICE_FROM_DB_FAILED, pnrInfo.getOrderId()+"从库中获取售卖价格失败");
            return;
        }
        PriceDetail priceDetail = pnrInfo.getPriceDetail();
        if (priceDetail.getAdultNum() > 0) {
            BigDecimal salePrice = priceMap.get(BizzConstants.PSG_TYPE_CODE_ADT);
            if (null == salePrice || salePrice.intValue() == 0) {
                setPnrInfoFalse(pnrInfo, TicketEx.QUERY_PRICE_FROM_DB_FAILED, pnrInfo.getOrderId()+"从库中获取成人售卖价格为0");
            }
            priceDetail.getAdultPrice().setSalePrice(salePrice);
            if (priceDetail.getBabyNum() > 0) {
                BigDecimal babyPrice = priceMap.get(BizzConstants.PSG_TYPE_CODE_ADT);
                if (null == babyPrice || babyPrice.intValue() == 0) {
                    setPnrInfoFalse(pnrInfo, TicketEx.QUERY_PRICE_FROM_DB_FAILED, pnrInfo.getOrderId()+"从库中获取婴儿售卖价格为0");
                }
                priceDetail.getBabyPrice().setSalePrice(babyPrice);
            }
        } else if (priceDetail.getChildNum() > 0) {
            BigDecimal chdPrice = pricesFromDB.get(0).getSalePrice();
            if (null == chdPrice || chdPrice.intValue() == 0) {
                setPnrInfoFalse(pnrInfo, TicketEx.QUERY_PRICE_FROM_DB_FAILED, pnrInfo.getOrderId()+"从库中获取儿童售卖价格为0");
            }
            priceDetail.getChildPrice().setSalePrice(chdPrice);
        }
    }

    private void setPnrInfoFalse(PnrInfo pnrInfo, int errCode, String errMsg) {
        pnrInfo.setIssueFlag(false);
        pnrInfo.setErrorCode(errCode);
        pnrInfo.setMsg(errMsg);
    }

}
