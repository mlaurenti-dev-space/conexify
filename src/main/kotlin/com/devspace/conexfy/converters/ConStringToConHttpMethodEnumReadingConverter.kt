package com.devspace.conexfy.converters

import com.devspace.conexfy.enums.ConHttpMethodEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.lang.NonNull

@ReadingConverter
class ConStringToConHttpMethodEnumReadingConverter : Converter<String, ConHttpMethodEnum> {
    override fun convert(@NonNull source: String): ConHttpMethodEnum {
        return ConHttpMethodEnum.valueOf(source)
    }
}