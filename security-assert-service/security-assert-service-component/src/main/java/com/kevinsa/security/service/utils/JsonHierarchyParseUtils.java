package com.kevinsa.security.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class JsonHierarchyParseUtils {

    public List<String> getJsonKey(String json, String parentKey, Integer childNodeLimit) {
        JsonNode jsonNode = ObjectMapperUtils.readTreeFromJson(json);
        List<String> keys = new ArrayList<>();
        parseJsonNode(jsonNode, parentKey, keys, childNodeLimit);
        return keys;
    }

    private void parseJsonNode(JsonNode node, String parentPath, List<String> keys, Integer childNodeLimit) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode childNode = entry.getValue();
                String fullPath = parentPath.isEmpty() ? fieldName : parentPath + "." + fieldName;

                if (childNode.isObject() || childNode.isArray()) {
                    parseJsonNode(childNode, fullPath, keys, childNodeLimit);
                } else {
                    keys.add(fullPath);
//                    String value = childNode.asText();
                }
            });
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            int limit;
            if (childNodeLimit == null) {
                limit = arrayNode.size();
            } else {
                limit = arrayNode.size() > childNodeLimit ? childNodeLimit : arrayNode.size();
            }
            for (int i = 0; i < limit; i++) {
                JsonNode childNode = arrayNode.get(i);
                String fullPath = parentPath + "[" + i + "]";

                if (childNode.isObject() || childNode.isArray()) {
                    parseJsonNode(childNode, fullPath, keys, childNodeLimit);
                }
            }
        }
    }
}
