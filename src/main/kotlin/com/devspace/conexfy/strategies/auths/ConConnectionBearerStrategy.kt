package com.devspace.conexfy.strategies.auths

import com.devspace.conexfy.entities.ConConnectionEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ConConnectionBearerStrategy : ConConnectionAuthStrategy {
    override fun apply(request: WebClient.RequestHeadersSpec<*>, conConnectionEntity: ConConnectionEntity) {
        val token = conConnectionEntity.authToken?.value
            ?.takeIf { it.isNotBlank() }
            ?: throw IllegalArgumentException("Bearer token is required for authentication")

        request.header("Authorization", "Bearer $token")
    }
}
