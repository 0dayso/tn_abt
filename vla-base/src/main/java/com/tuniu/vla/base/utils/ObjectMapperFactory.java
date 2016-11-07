package com.tuniu.vla.base.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class ObjectMapperFactory {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.disable(SerializationFeature.WRITE_NULL_MAP_VALUES)
				.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static ObjectMapper getDefaultObjectMapper() {		
		return objectMapper.copy();
	}

}