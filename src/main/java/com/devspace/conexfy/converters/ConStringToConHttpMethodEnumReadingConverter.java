package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.devspace.conexfy.enums.ConHttpMethodEnum;

@ReadingConverter
public class ConStringToConHttpMethodEnumReadingConverter implements Converter<String, ConHttpMethodEnum> {
    @Override
    public ConHttpMethodEnum convert(String source) {
        return source == null ? null : ConHttpMethodEnum.valueOf(source);
    }
}