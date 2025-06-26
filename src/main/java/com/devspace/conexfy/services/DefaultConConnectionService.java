package com.devspace.conexfy.services;

import org.springframework.stereotype.Service;

import com.devspace.conexfy.entities.ConConecctionEntity;
import com.devspace.conexfy.repositories.ConConnectionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultConConnectionService implements ConConnectionService {

    private final ConConnectionRepository conConnectionRepository;

    public DefaultConConnectionService(ConConnectionRepository conConnectionRepository) {
        this.conConnectionRepository = conConnectionRepository;
    }

    @Override
    public Flux<ConConecctionEntity> findAll() {
        return conConnectionRepository.findAll();
    }

    @Override
    public Mono<ConConecctionEntity> findById(Long id) {
        return conConnectionRepository.findById(id);
    }

    @Override
    public Mono<ConConecctionEntity> create(ConConecctionEntity entity) {
        return conConnectionRepository.save(entity);
    }

    @Override
    public Mono<ConConecctionEntity> update(ConConecctionEntity entity) {
        return conConnectionRepository.save(entity);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return conConnectionRepository.deleteById(id);
    }

}
