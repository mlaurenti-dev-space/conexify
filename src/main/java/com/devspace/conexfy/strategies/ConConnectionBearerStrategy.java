package com.devspace.conexfy.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import com.devspace.conexfy.entities.ConConnectionEntity;

@Component
public class ConConnectionBearerStrategy implements ConConnectionAuthStrategy{

    @Override
    public void apply(RequestHeadersSpec<?> request, ConConnectionEntity conConnectionEntity) {
        var token = conConnectionEntity.getAuthToken() != null ? conConnectionEntity.getAuthToken().getValue() : null;
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Bearer token is required for authentication");
        }
        request.header("Authorization", "Bearer " + token);
    }

}
