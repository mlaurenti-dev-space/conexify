package com.devspace.conexfy.strategies.auths

import com.devspace.conexfy.entities.ConConnectionEntity
import org.springframework.web.reactive.function.client.WebClient

/**
 * Interface for defining authentication strategies for ConConnectionEntity.
 * Implementations of this interface should apply the appropriate authentication headers
 * to the WebClient request based on the ConConnectionEntity's authentication details.
 */
interface ConConnectionAuthStrategy {
    fun apply(request: WebClient.RequestHeadersSpec<*>, conConnectionEntity: ConConnectionEntity)
}
