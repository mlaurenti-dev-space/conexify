package com.devspace.conexfy.services;

import com.devspace.conexfy.entities.ConConecctionEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConConnectionService {
    Flux<ConConecctionEntity> findAll();
    Mono<ConConecctionEntity> findById(Long id);
    Mono<ConConecctionEntity> create(ConConecctionEntity entity);
    Mono<ConConecctionEntity> update(ConConecctionEntity entity);
    Mono<Void> deleteById(Long id);
}
