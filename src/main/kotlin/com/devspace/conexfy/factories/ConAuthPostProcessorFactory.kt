package com.devspace.conexfy.factories

import com.devspace.conexfy.entities.ConConnectionEntity
import com.devspace.conexfy.enums.ConAuthTypeEnum
import com.devspace.conexfy.processors.auths.ConAuthPostProcessor
import com.devspace.conexfy.processors.auths.ConOAuth2PostProcessor
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.Map

/**
 * Factory for creating authentication post-processors for connections.
 *
 * This factory provides a way to retrieve the appropriate post-processor
 * based on the specified authentication type.
 */
@Component
class ConAuthPostProcessorFactory(private val conOAuth2PostProcessor: ConOAuth2PostProcessor) {
    // Initialize the map with all processors
    private val map: MutableMap<ConAuthTypeEnum, ConAuthPostProcessor> = Map.of<ConAuthTypeEnum, ConAuthPostProcessor>(
        ConAuthTypeEnum.OAUTH2, this.conOAuth2PostProcessor // Add other processors here as needed
    )

    fun getProcessor(type: ConAuthTypeEnum): ConAuthPostProcessor {
        return map.getOrDefault(type) { conn: ConConnectionEntity, body: String -> Mono.just(conn) }
    }
}
