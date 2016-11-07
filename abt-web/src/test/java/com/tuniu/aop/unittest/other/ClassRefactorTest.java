package com.tuniu.aop.unittest.other;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.adapter.common.vo.ResponseVo;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.domesticSearch.ObjectFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chengyao on 2016/3/9.
 */
public class ClassRefactorTest {

    public static void main(String[] args) throws IOException {


        Method[] methods = ForTest.class.getMethods();

        for (Method method : methods) {
            if (method.getReturnType().isPrimitive() && !method.getReturnType().getName().equals("void")) {
                Object o = t(primitive, method.getGenericReturnType());
                System.out.println(o);
            }

            if (method.getReturnType().isAssignableFrom(Map.class)) {
                Object o = t(map, method.getGenericReturnType());
                System.out.println(o);
            }
            if (method.getReturnType().getName().equals("void")) {
                System.out.println("this is void");
            }

            if (method.getReturnType().isAssignableFrom(Collection.class)) {
                Object o = t(collection, method.getGenericReturnType());
                System.out.println(o);
            } else {
                if (method.getReturnType().isAssignableFrom(List.class)) {
                    Object o = t(list, method.getGenericReturnType());
                    System.out.println(o);
                }
                if (method.getReturnType().isAssignableFrom(Set.class)) {
                    Object o = t(list, method.getGenericReturnType());
                    System.out.println(o);
                }
            }




            //Class<?> t = ClassUtils.findCollectionSubClass(method.getGenericReturnType());
            //System.out.println("收:" + method.getReturnType() + "，实际：" + t);
        }

    }

    private static final String list = "{\"success\":true, \"data\":[\"s1\", \"s2\"]}";
    private static final String collection = "{\"success\":true, \"data\":[{\"success\":true}, {\"success\":false}]}";
    private static final String primitive = "{\"success\":true, \"data\":1}";
    private static final String map = "{\"success\":true, \"data\":{\"success\":true, \"team\": 12434}}";


    private static Object t(String b, Type type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        final JsonNode node = objectMapper.readTree(b);
        JsonNode realNode = node.get("data");


        JavaType javaType = objectMapper.getTypeFactory().constructType(type);//.constructParametricType(returnClass, subClass);
        return objectMapper.convertValue(realNode, javaType);
    }


    interface ForTest {

        List<String> s1();

        Collection<ResponseVo> s2(String t);

        void s3();

        ObjectFactory s4();

        ResponseVo s5();

        Vs<String> s6();

        Set<String> s7();

        Map<String, Object> s8();

        int s9();
    }

    class Vs<T> {
        private List<T> list;

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }
}
