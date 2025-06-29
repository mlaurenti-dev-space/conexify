package com.devspace.conexfy.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DevObjectMapperUtil {

    private final ObjectMapper objectMapper;

    public DevObjectMapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    /**
     * Convierte un String JSON a un Map<String, Object>.
     *
     * @param json el String JSON a convertir
     * @return un Map<String, Object> representando el JSON, o un Map con un error si el JSON es inválido
     */
    public Map<String, Object> parseJson(String json) {
        try {
            // TypeReference mantiene la información genérica
            Map<String, Object> map = objectMapper.readValue(
                    json,
                    new TypeReference<Map<String, Object>>() {
                    });
            return map;
        } catch (IOException e) {
            return Map.of("error", "JSON inválido");
        }
    }
}
