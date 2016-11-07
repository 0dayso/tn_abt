package com.tuniu.abt.service.book.async;

import com.tuniu.abt.base.tracer.SplitTracerAsyncTask;
import com.tuniu.abt.base.tracer.pojo.FlatTraceInfo;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.dto.book.response.BookingResData;
import com.tuniu.abt.intf.entity.AbtBookingRequest;
import com.tuniu.abt.intf.exception.BizzException;
import com.tuniu.abt.mapper.AbtBookingRequestMapper;
import com.tuniu.abt.service.book.work.BookWorkHandler;
import com.tuniu.abt.service.book.work.BookWorkSupport;
import com.tuniu.abt.service.commons.FeedbackReporter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 占位异步线程实现
 *
 * Created by chengyao on 2016/1/8.
 */
@Service
public class BookAsyncTask extends SplitTracerAsyncTask<BookingResData, Map<String, Object>> implements InitializingBean,
        ApplicationContextAware {

    private static final long serialVersionUID = -3484791835367049832L;

    private List<BookWorkHandler> workHandlerList;

    private ApplicationContext applicationContext;

    @Resource
    private FeedbackReporter feedbackReporter;
    @Resource
    private AbtBookingRequestMapper abtBookingRequestMapper;

    @Override
    protected Object traceInput(String callbackServiceName, Map<String, Object> param, FlatTraceInfo tracerCommand) {
        tracerCommand.setBizCmd(TracerCmdEnum.ACTION_ASYNC.name());
        return BookWorkSupport.getRequest();
    }

    @Override
    protected Object traceOutput(BookingResData result, FlatTraceInfo tracerCommand) {
        return result;
    }

    @Override
    protected void onSuccess(String callbackServiceName, BookingResData resData) {
        feedbackReporter.success(FeedbackReporter.Type.BOOK, resData);
        updateAbtBookingRequest(AbtBookingRequest.STATUS_SUCCESS);
        super.onSuccess(callbackServiceName, resData);
    }

    @Override
    protected void onException(String callbackServiceName, Exception ex) {
        super.onException(callbackServiceName, ex);
        feedbackReporter.exception(FeedbackReporter.Type.BOOK, null, ex);
        updateAbtBookingRequest(AbtBookingRequest.STATUS_FAIL);
    }

    @Override
    protected BookingResData exec(Map<String, Object> params) throws Exception {
        for (BookWorkHandler occupyWorkHandler : workHandlerList) {
            if (occupyWorkHandler.apply()) {
                try {
                    if (!occupyWorkHandler.processing()) {
                        break;
                    }
                } catch (BizzException ex) {
                    if (!occupyWorkHandler.onBusinessException(ex)) {
                        throw ex;
                    }
                }
            }
        }
        return BookWorkSupport.getResponseData();
    }

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        super.rejectedExecution(task, executor);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        initWorkHandler();
    }

    private void initWorkHandler() {
        Map<String, BookWorkHandler> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, BookWorkHandler.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.workHandlerList = new ArrayList<BookWorkHandler>(matchingBeans.values());
            OrderComparator.sort(this.workHandlerList);
        }
    }

    private void updateAbtBookingRequest(int status) {
        AbtBookingRequest abtBookingRequest = new AbtBookingRequest();
        abtBookingRequest.setId(BookWorkSupport.getRequestId());
        abtBookingRequest.setStatus(status);
        abtBookingRequest.setBackTime(new Date());
        abtBookingRequest.setCallBackStatus(AbtBookingRequest.CALL_BACK_STATUS_SUCCESS);
        abtBookingRequestMapper.updateByPrimaryKeySelective(abtBookingRequest);
    }

}
