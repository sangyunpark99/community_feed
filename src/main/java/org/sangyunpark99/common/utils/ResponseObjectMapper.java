package org.sangyunpark99.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sangyunpark99.common.ui.Response;

public class ResponseObjectMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResponseObjectMapper() {

    }

    public static Response toResponseObject(String response) {
        try {
            return objectMapper.readValue(response, Response.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String toStringResponse(Response<?> response) {
        try {
            return objectMapper.writeValueAsString(response);
        }catch (JsonProcessingException e) {
            return null;
        }
    }

}
