package com.devspace.conexfy.strategies.auths

import com.devspace.conexfy.entities.ConConnectionEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ConConnectionOAuth2Strategy : ConConnectionAuthStrategy {
    override fun apply(request: WebClient.RequestHeadersSpec<*>, conConnectionEntity: ConConnectionEntity) {
        /*
         * TODO Esta implementacion le falta mas desarrollo, ya que no se esta manejando el flujo completo de OAuth2.
         */
        val token = conConnectionEntity.authToken?.value
            ?.takeIf { it.isNotBlank() }
            ?: throw IllegalArgumentException("OAuth2 token is required for authentication")

        request.header("Authorization", token)
    }
}
