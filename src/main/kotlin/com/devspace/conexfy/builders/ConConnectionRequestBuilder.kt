package com.devspace.conexfy.builders

import com.devspace.conexfy.entities.ConConnectionEntity
import com.devspace.conexfy.enums.ConHttpMethodEnum
import com.devspace.conexfy.factories.ConConnectionAuthStrategyFactory
import com.devspace.conexfy.factories.ConWebClientFactory
import com.devspace.conexfy.strategies.auths.ConConnectionAuthStrategy
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Consumer

@Component
class ConConnectionRequestBuilder(
    private val conConnectionAuthStrategyFactory: ConConnectionAuthStrategyFactory,
    private val clientFactory: ConWebClientFactory
) {
    fun execute(conn: ConConnectionEntity): Mono<String> {
        // 1. Crea un WebClient configurado para esta Connection
        val webClient = clientFactory.getClientFor(conn)

        // 2. Traduce el enum de método a HttpMethod de Spring
        val springMethod = HttpMethod.valueOf(conn.method.name)

        // 3. Prepara la spec con método, URL, path vars y query params
        val spec: WebClient.RequestHeadersSpec<*> = webClient
            .method(springMethod)
            .uri {
                if (conn.queryParamsJson != null) {
                    conn.queryParamsJson.forEach { (key: String, value: String) ->
                        it.queryParam(key, value)
                    }
                }

                if (conn.pathVarsJson != null && !conn.pathVarsJson.isEmpty()) {
                    it.build(conn.pathVarsJson)
                } else {
                    it.build()
                }
            } // 4. Añade headers dinámicos
            .headers {
                if (conn.headersJson != null) {
                    conn.headersJson.forEach { (headerName: String, headerValue: String) ->
                        it.add(headerName, headerValue)
                    }
                }
            }

        // 5. Aplica la estrategia de autenticación según authType
        conConnectionAuthStrategyFactory.getStrategy(conn.authType)
            .ifPresent(Consumer { strategy: ConConnectionAuthStrategy -> strategy.apply(spec, conn) })

        // 6. Si el método lleva body, lo adjunta
        if (requiresBody(conn.method) && conn.body != null) {
            (spec as WebClient.RequestBodySpec).bodyValue(conn.body)
        }

        // 7. Ejecuta la petición y retorna todo el Mono<String>
        return spec
            .retrieve()
            .bodyToMono(String::class.java).doOnError(Consumer { error: Throwable ->
                log.error(
                    "Error executing connection id=" + conn.id,
                    error
                )
            }
            )
    }

    private fun requiresBody(method: ConHttpMethodEnum): Boolean {
        return when (method) {
            ConHttpMethodEnum.POST, ConHttpMethodEnum.PUT, ConHttpMethodEnum.PATCH -> true
            else -> false
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ConConnectionRequestBuilder::class.java)
    }
}
