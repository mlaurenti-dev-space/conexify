package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.enums.ConHttpMethodEnum;

@WritingConverter
public class ConHttpMethodEnumToStringWritingConverter implements Converter<ConHttpMethodEnum, String> {

    @Override
    public String convert(@NonNull ConHttpMethodEnum source) {
        return source.name();
    }
}
