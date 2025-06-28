package com.devspace.conexfy.strategies.auths;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devspace.conexfy.entities.ConConnectionEntity;

@Component
public class ConConnectionOAuth2Strategy implements ConConnectionAuthStrategy {

    @Override
    public void apply(WebClient.RequestHeadersSpec<?> request, ConConnectionEntity conConnectionEntity) {
        /*
         * Esta implementacion le falta mas desarrollo, ya que no se esta manejando el flujo completo de OAuth2.
         */
    
        var token = conConnectionEntity.getAuthToken() != null ? conConnectionEntity.getAuthToken().getValue() : null;
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("OAuth2 token is required for authentication");
        }
        request.header("Authorization", token);
    }

}
