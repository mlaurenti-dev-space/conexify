package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.enums.ConHttpMethodEnum;



@ReadingConverter
public class ConStringToConHttpMethodEnumReadingConverter implements Converter<String, ConHttpMethodEnum> {
    @Override
    public ConHttpMethodEnum convert(@NonNull String source) {
        return ConHttpMethodEnum.valueOf(source);
    }
}