package com.devspace.conexfy.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.Map

@Component
class DevObjectMapperUtil(private val objectMapper: ObjectMapper) {
    /**
     * Convierte un String JSON a un Map<String></String>, Object>.
     *
     * @param json el String JSON a convertir
     * @return un Map<String></String>, Object> representando el JSON, o un Map con un error si el JSON es inválido
     */
    fun parseJson(json: String?): MutableMap<String?, Any?>? {
        return try {
            // TypeReference mantiene la información genérica
            objectMapper.readValue<MutableMap<String?, Any?>?>(
                json,
                object : TypeReference<MutableMap<String?, Any?>?>() {
                })
        } catch (e: IOException) {
            Map.of<String?, Any?>("error", "JSON inválido")
        }
    }
}
