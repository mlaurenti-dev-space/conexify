package com.devspace.conexfy.converters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

@ReadingConverter
public class DevJsonToMapReadingConverter implements Converter<String, Map<String, String>> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, String> convert(String source) {
        if (source == null || source.isBlank())
            return new HashMap<>();
        try {
            return mapper.readValue(source,
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
