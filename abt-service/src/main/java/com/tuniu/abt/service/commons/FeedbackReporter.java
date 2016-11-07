package com.tuniu.abt.service.commons;

import com.alibaba.fastjson.JSON;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.intf.tsp.TspTicInterface;
import com.tuniu.abt.intf.tsp.dto.tic.TicFeedbackRequest;
import com.tuniu.abt.utils.RestClientComponent;
import com.tuniu.vla.base.taskpool.datashare.DataSharedSupport;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 通用反馈接口
 * Created by chengyao on 2016/4/22.
 */
@Component
public class FeedbackReporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackReporter.class);

    @Resource
    private RestClientComponent restClientComponent;
    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;
    @Resource
    private TspTicInterface tspTicInterface;

    /**
     * 成功反馈，需要DataSharedSupport.getTransId()
     * @param callbackUrl 回调地址
     * @param resultData 回调数据
     */
    public void success(String callbackUrl, Object resultData) {
        requestUrl(callbackUrl, buildSuccessData(resultData));
    }

    /**
     * 成功反馈，result是 返回结构的完整体 临时解决方案 //TODO
     * @param callbackUrl 回调地址
     * @param result 回调数据
     */
    public void feedback(String callbackUrl, Object result) {
        requestUrl(callbackUrl, result);
    }

    /**
     * 失败反馈，需要DataSharedSupport.getTransId()
     * @param callbackUrl 回调地址
     * @param resultData 回调数据
     * @param ex 异常
     */
    public void exception(String callbackUrl, Object resultData, Throwable ex) {
        requestUrl(callbackUrl, buildFailData(resultData, ex));
    }

    /**
     * 失败反馈，需要DataSharedSupport.getTransId()
     * @param feedbackType tsp接口
     * @param resultData 回调数据
     * @param ex 异常
     */
    public void exception(Type feedbackType, Object resultData, Throwable ex) {
        requestTsp(feedbackType, buildFailData(resultData, ex));
    }

    /**
     * 成功反馈，需要DataSharedSupport.getTransId()
     * @param feedbackType tsp接口
     * @param resultData 回调数据
     */
    public void success(Type feedbackType, Object resultData) {
        requestTsp(feedbackType, buildSuccessData(resultData));
    }


    private TicFeedbackRequest buildFailData(Object resultData, Throwable ex) {
        if (ex instanceof BizzException) {
            BizzException bizzException = (BizzException) ex;
            // 是反馈请求时的异常，则不再次处理反馈
            if (bizzException.getCode() == BizzEx.FEED_BACK_ERROR) {
                return null;
            }
        }

        TicFeedbackRequest ticFeedbackRequest = new TicFeedbackRequest();
        ticFeedbackRequest.setData(resultData);
        ticFeedbackRequest.setTransId(DataSharedSupport.getTransId());
        ticFeedbackRequest.setOrderIdTuniu(DataSharedSupport.getOrderId());
        ticFeedbackRequest.setErrorCode(exceptionMessageUtils.findCode(ex));
        ticFeedbackRequest.setMsg(exceptionMessageUtils.findWrappedMessage(ex));
        ticFeedbackRequest.setSuccess(false);
        return ticFeedbackRequest;
    }

    private TicFeedbackRequest buildSuccessData(Object resultData) {
        TicFeedbackRequest ticFeedbackRequest = new TicFeedbackRequest();
        ticFeedbackRequest.setTransId(DataSharedSupport.getTransId());
        ticFeedbackRequest.setOrderIdTuniu(DataSharedSupport.getOrderId());
        ticFeedbackRequest.setData(resultData);
        return ticFeedbackRequest;
    }

    private void requestTsp(Type feedbackType, TicFeedbackRequest result) {
        if (result == null) {
            return;
        }
        try {
            switch (feedbackType) {
            case BOOK:
                tspTicInterface.bookFeedback(result);
                break;
            case CANCEL:
                LOGGER.info("取消占位操作结果：" + JSON.toJSONString(result));
                break;
            case REFUND:
                tspTicInterface.refundFeedback(result);
                break;
            case CHANGE:
                tspTicInterface.changeFeedback(result);
            default:
                break;
            }
        } catch (Exception ex) {
            throw new BizzException(BizzEx.FEED_BACK_ERROR, ex);
        }
    }

    private void requestUrl(String callback, Object result) {
        if (result == null) {
            return;
        }
        try {
            restClientComponent.query(callback, HttpMethod.POST, JSON.toJSONString(result), 3000);
        } catch (Exception ex) {
            throw new BizzException(BizzEx.FEED_BACK_ERROR, ex);
        }
    }

    public enum Type {
        BOOK, CANCEL, ISSUE, REFUND, CHANGE,
    }

}
