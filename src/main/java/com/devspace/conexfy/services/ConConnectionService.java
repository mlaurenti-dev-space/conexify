package com.devspace.conexfy.services;

import com.devspace.conexfy.entities.ConConnectionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConConnectionService {
    Flux<ConConnectionEntity> findAll();
    Mono<ConConnectionEntity> findById(Long id);
    Mono<ConConnectionEntity> create(ConConnectionEntity entity);
    Mono<ConConnectionEntity> update(ConConnectionEntity entity);
    Mono<Void> deleteById(Long id);
    Mono<String> execute(Long id);
}
