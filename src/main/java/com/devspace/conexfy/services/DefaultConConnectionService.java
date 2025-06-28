package com.devspace.conexfy.services;

import org.springframework.stereotype.Service;

import com.devspace.conexfy.builders.ConConnectionRequestBuilder;
import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.factories.ConAuthPostProcessorFactory;
import com.devspace.conexfy.repositories.ConConnectionRepository;
import com.devspace.conexfy.utils.DevObjectMapperUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Default implementation of the ConConnectionService interface.
 * Provides methods to manage connections, including CRUD operations and execution of connection requests.
 */
@Service
public class DefaultConConnectionService implements ConConnectionService {

    private final ConConnectionRepository conConnectionRepository;
    private final ConConnectionRequestBuilder conConnectionRequestBuilder;
    private final ConAuthPostProcessorFactory conAuthPostProcessorFactory;

    public DefaultConConnectionService(ConConnectionRepository conConnectionRepository,
            ConConnectionRequestBuilder conConnectionRequestBuilder, DevObjectMapperUtil devObjectMapperUtil,
            ConAuthPostProcessorFactory conAuthPostProcessorFactory) {
        this.conConnectionRepository = conConnectionRepository;
        this.conConnectionRequestBuilder = conConnectionRequestBuilder;
        this.conAuthPostProcessorFactory = conAuthPostProcessorFactory;
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
    
    @Override
    public Mono<String> execute(Long id) {
        return findById(id).flatMap(conn -> {
            if (conn.getDependsOn() == null) {
                return conConnectionRequestBuilder.execute(conn);
            }
            return findById(conn.getDependsOn())
                    .flatMap(conConnectionRequestBuilder::execute)
                    .flatMap(responseBody -> conAuthPostProcessorFactory.getProcessor(conn.getAuthType()).apply(conn, responseBody))
                    .flatMap(conConnectionRequestBuilder::execute);
        });
    }
}
