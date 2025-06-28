package com.devspace.conexfy.services;

import org.springframework.stereotype.Service;

import com.devspace.conexfy.builders.ConConnectionRequestBuilder;
import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.models.DevEncryptedString;
import com.devspace.conexfy.repositories.ConConnectionRepository;
import com.devspace.conexfy.utils.DevObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultConConnectionService implements ConConnectionService {

    private final ConConnectionRepository conConnectionRepository;
    private final ConConnectionRequestBuilder conConnectionRequestBuilder;
    private final DevObjectMapperUtil devObjectMapperUtil;

    public DefaultConConnectionService(ConConnectionRepository conConnectionRepository, ConConnectionRequestBuilder conConnectionRequestBuilder, DevObjectMapperUtil devObjectMapperUtil) {
        this.conConnectionRepository = conConnectionRepository;
        this.conConnectionRequestBuilder = conConnectionRequestBuilder;
        this.devObjectMapperUtil = devObjectMapperUtil;
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
            if (conn.getDependsOn() != null) {
                // Si la conexión depende de otra, primero ejecutamos esa
                return findById(conn.getDependsOn()).flatMap(conConnectionRequestBuilder::execute)
                        .map(auth2 -> {
                    
                            String token = (String) devObjectMapperUtil.parseJson(auth2).get("token");                    
                            conn.setAuthToken(new DevEncryptedString(token));
                            return conn;
                        }).flatMap(conConnectionRequestBuilder::execute);
            }
            return conConnectionRequestBuilder.execute(conn);
        });
    }
}
