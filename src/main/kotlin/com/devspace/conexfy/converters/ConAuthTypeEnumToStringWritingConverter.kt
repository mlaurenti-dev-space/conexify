package com.devspace.conexfy.converters

import com.devspace.conexfy.enums.ConAuthTypeEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.lang.NonNull

@WritingConverter
class ConAuthTypeEnumToStringWritingConverter : Converter<ConAuthTypeEnum, String> {
    override fun convert(@NonNull source: ConAuthTypeEnum): String {
        return source.name
    }
}