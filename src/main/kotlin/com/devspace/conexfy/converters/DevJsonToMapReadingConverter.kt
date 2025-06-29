package com.devspace.conexfy.converters

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.lang.NonNull
import java.io.IOException

@ReadingConverter
class DevJsonToMapReadingConverter : Converter<String, MutableMap<String, String>> {
    override fun convert(@NonNull source: String): MutableMap<String, String> {
        if (source.isBlank()) return HashMap<String, String>()
        try {
            return mapper.readValue<MutableMap<String, String>>(
                source,
                mapper.typeFactory.constructMapType(MutableMap::class.java, String::class.java, String::class.java)
            )
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }
    }

    companion object {
        private val mapper = ObjectMapper()
    }
}
