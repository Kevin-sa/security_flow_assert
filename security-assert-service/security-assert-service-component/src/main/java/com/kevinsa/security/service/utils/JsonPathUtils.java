package com.kevinsa.security.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class JsonPathUtils {

    private static final Configuration CONFIGURATION = Configuration.defaultConfiguration();

    public <T> T objectParse(String json, String path, T valueType) {
        JsonNode jsonNode = ObjectMapperUtils.readTreeFromJson(json);
        return JsonPath.using(CONFIGURATION).parse(jsonNode).read(path);
    }
}
