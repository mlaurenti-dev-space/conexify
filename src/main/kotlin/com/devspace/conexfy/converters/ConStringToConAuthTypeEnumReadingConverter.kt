package com.devspace.conexfy.converters

import com.devspace.conexfy.enums.ConAuthTypeEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.lang.NonNull

@ReadingConverter
class ConStringToConAuthTypeEnumReadingConverter : Converter<String, ConAuthTypeEnum> {
    override fun convert(@NonNull source: String): ConAuthTypeEnum {
        return ConAuthTypeEnum.valueOf(source)
    }
}
