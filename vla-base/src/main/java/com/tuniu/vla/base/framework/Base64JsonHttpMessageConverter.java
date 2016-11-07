package com.tuniu.vla.base.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.vla.base.utils.ObjectMapperFactory;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * Created by liuzhaoxiang on 2015/11/30.
 */
public class Base64JsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private static final Logger log = LoggerFactory.getLogger(Base64JsonHttpMessageConverter.class);

    public Base64JsonHttpMessageConverter() {
        super();
        ObjectMapper objectMapper = ObjectMapperFactory.getDefaultObjectMapper();
        setObjectMapper(objectMapper);
    }

    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {

        // JsonEncoding encoding =
        // getJsonEncoding(outputMessage.getHeaders().getContentType());
        try {
            // if (this.prefixJson) {
            // jsonGenerator.writeRaw("{} && ");
            // }
            byte[] bytes = getObjectMapper().writeValueAsBytes(object);
            FileCopyUtils.copy(Base64.encodeBase64(bytes), outputMessage.getBody());
        } catch (JsonProcessingException ex) {
            log.error("Could not write JSON: " + ex.getMessage(), ex);
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
}
