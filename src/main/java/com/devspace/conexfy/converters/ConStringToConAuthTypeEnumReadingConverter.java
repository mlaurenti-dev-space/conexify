package com.devspace.conexfy.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.enums.ConAuthTypeEnum;

@ReadingConverter
public class ConStringToConAuthTypeEnumReadingConverter implements Converter<String, ConAuthTypeEnum> {
    @Override
    public ConAuthTypeEnum convert(@NonNull String source) {
        return ConAuthTypeEnum.valueOf(source);
    }
}
