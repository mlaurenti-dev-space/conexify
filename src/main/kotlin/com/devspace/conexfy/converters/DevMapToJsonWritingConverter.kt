package com.devspace.conexfy.converters

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.lang.NonNull

@WritingConverter
class DevMapToJsonWritingConverter : Converter<MutableMap<String, String>, String> {
    override fun convert(@NonNull source: MutableMap<String, String>): String {
        try {
            return mapper.writeValueAsString(source)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException(e)
        }
    }

    companion object {
        private val mapper = ObjectMapper()
    }
}
