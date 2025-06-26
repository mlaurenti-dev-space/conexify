package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.devspace.conexfy.enums.ConAuthTypeEnum;

@WritingConverter
public class ConAuthTypeEnumToStringWritingConverter implements Converter<ConAuthTypeEnum, String> {
    @Override
    public String convert(ConAuthTypeEnum source) {
        return source == null ? null : source.name();
    }
}