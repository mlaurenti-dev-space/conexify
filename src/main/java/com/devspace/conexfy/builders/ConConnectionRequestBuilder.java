package com.devspace.conexfy.builders;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.enums.ConHttpMethodEnum;
import com.devspace.conexfy.factories.ConConnectionAuthStrategyFactory;
import com.devspace.conexfy.factories.ConWebClientFactory;

import reactor.core.publisher.Mono;

@Component
public class ConConnectionRequestBuilder {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ConConnectionRequestBuilder.class);

    private final ConConnectionAuthStrategyFactory conConnectionAuthStrategyFactory;
    private final ConWebClientFactory clientFactory;

    public ConConnectionRequestBuilder(ConConnectionAuthStrategyFactory conConnectionAuthStrategyFactory, ConWebClientFactory clientFactory) {
        this.conConnectionAuthStrategyFactory = conConnectionAuthStrategyFactory;
        this.clientFactory = clientFactory;
    }

    public Mono<String> execute(ConConnectionEntity conn) {
        // 1. Crea un WebClient configurado para esta Connection
        WebClient webClient = clientFactory.getClientFor(conn);

        // 2. Traduce el enum de método a HttpMethod de Spring
        HttpMethod springMethod = HttpMethod.valueOf(conn.getMethod().name());

        // 3. Prepara la spec con método, URL, path vars y query params
        WebClient.RequestHeadersSpec<?> spec = webClient
            .method(springMethod)
            .uri(uriBuilder -> {
                
                if (conn.getQueryParamsJson() != null) {
                    conn.getQueryParamsJson().forEach((key, value) -> {
                        uriBuilder.queryParam(key, value);
                    });
                }
                if (conn.getPathVarsJson() != null && !conn.getPathVarsJson().isEmpty()) {
                    return uriBuilder.build(conn.getPathVarsJson());
                }
                return uriBuilder.build();
            })
            // 4. Añade headers dinámicos
            .headers(headers -> {
                if (conn.getHeadersJson() != null) {
                    conn.getHeadersJson().forEach(headers::add);
                }
            });

        // 5. Aplica la estrategia de autenticación según authType
        conConnectionAuthStrategyFactory.getStrategy(conn.getAuthType()).ifPresent(strategy -> strategy.apply(spec, conn));

        // 6. Si el método lleva body, lo adjunta
        if (requiresBody(conn.getMethod()) && conn.getBody() != null) {
            ((WebClient.RequestBodySpec) spec).bodyValue(conn.getBody());
        }

        // 7. Ejecuta la petición y retorna todo el Mono<String>
        return spec
            .retrieve()
            .bodyToMono(String.class).doOnError(error ->
                log.error("Error executing connection id=" + conn.getId(), error)
            );
    }

    private boolean requiresBody(ConHttpMethodEnum method) {
        return switch (method) {
            case POST, PUT, PATCH -> true;
            default -> false;
        };
    }
}
