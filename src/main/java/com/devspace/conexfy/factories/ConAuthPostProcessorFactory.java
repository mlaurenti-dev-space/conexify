package com.devspace.conexfy.factories;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.enums.ConAuthTypeEnum;
import com.devspace.conexfy.processors.auths.ConAuthPostProcessor;
import com.devspace.conexfy.processors.auths.ConOAuth2PostProcessor;


import reactor.core.publisher.Mono;

/**
 * Factory for creating authentication post-processors for connections.
 * 
 * This factory provides a way to retrieve the appropriate post-processor
 * based on the specified authentication type.
 */
@Component
public class ConAuthPostProcessorFactory {

  private final Map<ConAuthTypeEnum,ConAuthPostProcessor> map;

  private final ConOAuth2PostProcessor conOAuth2PostProcessor;

  public ConAuthPostProcessorFactory(ConOAuth2PostProcessor conOAuth2PostProcessor) {
    this.conOAuth2PostProcessor = conOAuth2PostProcessor;

    // Initialize the map with all processors
    this.map = Map.of(
      ConAuthTypeEnum.OAUTH2, this.conOAuth2PostProcessor
      // Add other processors here as needed
    );
  }

  public ConAuthPostProcessor getProcessor(ConAuthTypeEnum type) {
    return map.getOrDefault(type, (conn, body) -> Mono.just(conn));
  }
  
}
