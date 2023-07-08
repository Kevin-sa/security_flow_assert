package com.kevinsa.security.service.utils;

import static com.fasterxml.jackson.core.JsonFactory.Feature.INTERN_FIELD_NAMES;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import java.io.IOException;
import java.io.UncheckedIOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinsa.security.service.utils.error.ValidateJsonProcessingException;

public final class ObjectMapperUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper(new JsonFactory().disable(INTERN_FIELD_NAMES));

    static {
        MAPPER.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.enable(ALLOW_COMMENTS);
    }

    public static String toJSON(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ValidateJsonProcessingException(e);
        }
    }

    public static JsonNode readTreeFromJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            throw new ValidateJsonProcessingException(e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> valueType) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, valueType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
