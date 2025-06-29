package com.devspace.conexfy.processors.auths

import com.devspace.conexfy.entities.ConConnectionEntity
import reactor.core.publisher.Mono

/**
 * Interface for processing post-authentication actions on a connection entity.
 * Implementations should define how to handle the connection after receiving the authentication response.
 */
fun interface ConAuthPostProcessor {
    fun apply(conn: ConConnectionEntity, authResponseBody: String): Mono<ConConnectionEntity>
}
