package com.devspace.conexfy.services;

import org.springframework.stereotype.Service;

import com.devspace.conexfy.entities.ConConnectionEntity;
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
    public Flux<ConConnectionEntity> findAll() {
        return conConnectionRepository.findAll();
    }

    @Override
    public Mono<ConConnectionEntity> findById(Long id) {
        return conConnectionRepository.findById(id);
    }

    @Override
    public Mono<ConConnectionEntity> create(ConConnectionEntity entity) {
        return conConnectionRepository.save(entity);
    }

    @Override
    public Mono<ConConnectionEntity> update(ConConnectionEntity entity) {
        return conConnectionRepository.save(entity);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return conConnectionRepository.deleteById(id);
    }

}
