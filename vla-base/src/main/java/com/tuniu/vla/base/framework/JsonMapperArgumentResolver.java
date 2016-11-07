package com.tuniu.vla.base.framework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.vla.base.constants.SysEx;
import com.tuniu.vla.base.exception.SysException;
import com.tuniu.vla.base.utils.ObjectMapperFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * http参数解析
 *
 * @author xiehui
 */
public class JsonMapperArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapperArgumentResolver.class);

    private ObjectMapper objectMapper;
    private static final String PATH_DELIMITER = "/";

    public JsonMapperArgumentResolver() {
        objectMapper = ObjectMapperFactory.getDefaultObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Json.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        String allParam = null;
        try {
            Json jsonAnn = parameter.getParameterAnnotation(Json.class);
            String path = jsonAnn.path();
            allParam = getAllParam(webRequest);
            if (StringUtils.isEmpty(allParam)) {
                return null;
            }

            JsonNode node = objectMapper.readTree(allParam);
            if (StringUtils.isEmpty(path)) {
                LOGGER.trace("[JsonMapperArgumentResolver][resolveArgument] {} {}",
                        parameter.getParameterType().toString(),
                        parameter.getParameterIndex());
                path = parameter.getParameterName();
                if (node.has(path)) {
                    return objectMapper.convertValue(node.path(path), getReferenceType(parameter, jsonAnn));
                }
                return objectMapper.readValue(allParam, getReferenceType(parameter, jsonAnn));
            } else {
                String[] paths = path.split(PATH_DELIMITER);
                for (String p : paths) {
                    node = node.path(p);
                }
                if (node == null) {
                    return null;
                }
                return objectMapper.convertValue(node, getReferenceType(parameter, jsonAnn));
            }
        } catch (Exception e) {
            String errorMessage = "入参JSON解析失败，parameterName：" + parameter.getParameterName() + ", request: " + allParam + ".";
            throw new SysException(SysEx.JSON_PARSE_EXCEPTION, errorMessage, e);
        }
    }

    /**
     * 获取反射的对象类型
     */
    @SuppressWarnings("unchecked")
    private JavaType getReferenceType(MethodParameter parameter, Json annt) {
        Class[] types = annt.types();
        if (types.length == 1 && types[0].equals(Object.class)) {
            return objectMapper.getTypeFactory().constructType(
                    parameter.getParameterType());
        }
        if (Collection.class.isAssignableFrom(parameter.getParameterType())) {
            return objectMapper.getTypeFactory().constructCollectionType(
                    (Class<? extends Collection>) parameter.getParameterType(),
                    types[0]);
        } else if (Map.class.isAssignableFrom(parameter.getParameterType())) {
            if (types.length >= 2)
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(),
                        types[0], types[1]);
            else
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(),
                        types[0], Object.class);
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("Unsupported Reference To JavaType : ")
                .append(parameter.getParameterType().getName()).append("<");
        int i = 0;
        for (Class type : types) {
            if (i++ > 0)
                buffer.append(",");
            buffer.append(type.getSimpleName());
        }
        buffer.append(">");
        throw new UnsupportedOperationException(buffer.toString());
    }

    /**
     * 获取HttpServletRequest参数体
     * 
     * @param webRequest webRequest
     * @return 参数字符串
     * @throws IOException
     */
    private String getAllParam(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest httpServletRequest = webRequest
                .getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        if (HttpMethod.GET.name().equals(method) || HttpMethod.DELETE.name().equals(method)) {
            return httpServletRequest.getQueryString();
        }
        return IOUtils.toString(httpServletRequest.getReader());
    }

}
