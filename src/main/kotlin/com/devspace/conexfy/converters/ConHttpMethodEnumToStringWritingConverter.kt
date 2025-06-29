package com.devspace.conexfy.converters

import com.devspace.conexfy.enums.ConHttpMethodEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.lang.NonNull

@WritingConverter
class ConHttpMethodEnumToStringWritingConverter : Converter<ConHttpMethodEnum, String> {
    override fun convert(@NonNull source: ConHttpMethodEnum): String {
        return source.name
    }
}
