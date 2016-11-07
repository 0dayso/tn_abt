package com.tuniu.aop.unittest.schedule;

import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import com.tuniu.abt.schedule.BanFlightAddTask;
import com.tuniu.vla.base.asyncexecutor.AsyncQueuedExecutor;
import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by chengyao on 2016/2/1.
 */
public class ErrorLogHandlerTest extends BaseTest {

    @Resource
    AsyncQueuedExecutor asyncQueuedExecutor;

    @Resource
    BanFlightAddTask banFlightAddTask;

    @Test
    public void banFlightAdd() {
        banFlightAddTask.process();
    }

    @Test
    public void s() throws InterruptedException, IOException {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setSessionId(String.valueOf(System.currentTimeMillis()));
        asyncQueuedExecutor.offer(bookingRequest);

        Thread.sleep(1000l);

        for (int i = 0; i <1000; i++) {
            Thread.sleep(500l);
            try {
                asyncQueuedExecutor.offer(bookingRequest);

                print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        System.in.read();
    }

    private void print() {
        Map<String, String> map = asyncQueuedExecutor.getCurrentQueueSize();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey() + ": " + stringStringEntry.getValue());
        }
    }
}
