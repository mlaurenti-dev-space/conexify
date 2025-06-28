package com.devspace.conexfy.converters;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WritingConverter
public class DevMapToJsonWritingConverter implements Converter<Map<String, String>, String> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convert(@NonNull Map<String, String> source) {
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
