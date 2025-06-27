package com.devspace.conexfy.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import com.devspace.conexfy.entities.ConConnectionEntity;

@Component
public class ConConnectionBearerAuthStrategy implements ConConnectionAuthStrategy{

    @Override
    public void apply(RequestHeadersSpec<?> request, ConConnectionEntity conConnectionEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }

}
