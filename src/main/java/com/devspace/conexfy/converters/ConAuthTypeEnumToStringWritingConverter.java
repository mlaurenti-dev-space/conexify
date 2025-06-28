package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.enums.ConAuthTypeEnum;

@WritingConverter
public class ConAuthTypeEnumToStringWritingConverter implements Converter<ConAuthTypeEnum, String> {

    public String convert(@NonNull ConAuthTypeEnum source) {
        return source.name();
    }
}