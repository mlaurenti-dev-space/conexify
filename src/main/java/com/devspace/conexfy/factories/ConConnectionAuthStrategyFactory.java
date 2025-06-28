package com.devspace.conexfy.factories;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.enums.ConAuthTypeEnum;
import com.devspace.conexfy.strategies.auths.ConConnectionAuthStrategy;
import com.devspace.conexfy.strategies.auths.ConConnectionOAuth2Strategy;

@Component
public class ConConnectionAuthStrategyFactory {
    
    private final ConConnectionOAuth2Strategy conConnectionOAuth2Strategy;
    private final Map<ConAuthTypeEnum, ConConnectionAuthStrategy> strategies;

    public ConConnectionAuthStrategyFactory(ConConnectionOAuth2Strategy conConnectionOAuth2Strategy) {
        this.conConnectionOAuth2Strategy = conConnectionOAuth2Strategy;
        
        this.strategies = Map.of(
            ConAuthTypeEnum.OAUTH2, this.conConnectionOAuth2Strategy
        );
    }
   
    public Optional<ConConnectionAuthStrategy> getStrategy(ConAuthTypeEnum authType) {
        if (ConAuthTypeEnum.NO_AUTH == authType) return Optional.empty(); // No auth needed

        ConConnectionAuthStrategy strategy = strategies.get(authType);
        if (strategy != null) {
            return Optional.of(strategy);
        }
    
        throw new IllegalArgumentException("Unsupported auth type: " + authType);
    }
}
