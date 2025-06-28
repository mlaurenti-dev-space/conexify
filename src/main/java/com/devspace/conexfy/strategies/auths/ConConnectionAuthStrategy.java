package com.devspace.conexfy.strategies.auths;

import org.springframework.web.reactive.function.client.WebClient;

import com.devspace.conexfy.entities.ConConnectionEntity;

public interface ConConnectionAuthStrategy {
    void apply(WebClient.RequestHeadersSpec<?> request, ConConnectionEntity conConnectionEntity);
}
