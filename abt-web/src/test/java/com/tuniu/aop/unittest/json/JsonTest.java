package com.tuniu.aop.unittest.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.tuniu.abt.intf.dto.book.request.BookingRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyao on 2016/2/15.
 */
public class JsonTest {

    @Test
    public void s() {

        List<BookingRequest> list = new ArrayList<BookingRequest>();
        BookingRequest request1 = new BookingRequest();
        request1.setSessionId("2");
        list.add(request1);

        BookingRequest request2 = new BookingRequest();
        request2.setSessionId("3");
        list.add(request2);

        List<String> list2 = (List<String>) JSONPath.eval(list, "$.channel");

        for (String s : list2) {
            System.out.println(s);
        }

        System.out.println(JSONPath.eval(list, "$[0]"));
        System.out.println(JSONPath.eval(list, "$[2]"));



        // String t = JSON.toJSONString(list);

        // System.out.println(JSONPath.eval(t, "$[0]"));


        String t = "{\"success\":true, \"data\": [{\"id\":1},{\"id\":2}]}";
        JSONObject jsonObject = JSON.parseObject(t);
        System.out.println(jsonObject.getString("success"));
        System.out.println(jsonObject.getString("data"));


        List<TResp> tt = JSON.parseArray(jsonObject.getString("data"), TResp.class);
        for (TResp tResp : tt) {
            System.out.println(tResp.getId());
        }
        System.out.println(RequestMethod.GET.name());
        Assert.assertTrue(RequestMethod.GET.name().equals("GET"));

    }


    static class TResp {
        private int id;
        private boolean success;
        private Object data;
        private String msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
