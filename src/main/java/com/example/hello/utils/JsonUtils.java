package com.example.hello.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String ObjectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Optional<T> jsonToObject(String jsonData, Class<T> beanType) {
        try {
            return  Optional.of(MAPPER.readValue(jsonData, beanType));
        } catch (IOException e) {
            logger.error("failed to parse json string: {} to {}.", new Object[]{jsonData, beanType, e});
            return Optional.empty();
        }
    }
}
