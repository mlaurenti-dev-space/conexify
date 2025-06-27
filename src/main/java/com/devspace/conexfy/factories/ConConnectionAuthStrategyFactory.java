package com.devspace.conexfy.factories;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.enums.ConAuthTypeEnum;
import com.devspace.conexfy.strategies.ConConnectionAuthStrategy;
import com.devspace.conexfy.strategies.ConConnectionBearerAuthStrategy;

@Component
public class ConConnectionAuthStrategyFactory {
    
    private final ConConnectionBearerAuthStrategy conConnectionBearerAuthStrategy;
    private final Map<ConAuthTypeEnum, ConConnectionAuthStrategy> strategies;

    public ConConnectionAuthStrategyFactory(ConConnectionBearerAuthStrategy conConnectionBearerAuthStrategy) {
        this.conConnectionBearerAuthStrategy = conConnectionBearerAuthStrategy;
        this.strategies = Map.of(
            ConAuthTypeEnum.BEARER, this.conConnectionBearerAuthStrategy
        );
    }
   
    public ConConnectionAuthStrategy getStrategy(ConAuthTypeEnum authType) {
        if (ConAuthTypeEnum.NONE == authType) return null; // No auth needed

        ConConnectionAuthStrategy strategy = strategies.get(authType);
        if (strategy != null) {
            return strategy;
        }
    
        throw new IllegalArgumentException("Unsupported auth type: " + authType);
    }
}
