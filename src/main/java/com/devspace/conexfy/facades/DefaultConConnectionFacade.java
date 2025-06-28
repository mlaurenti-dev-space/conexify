package com.devspace.conexfy.facades;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.builders.ConConnectionRequestBuilder;
import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;
import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.mappers.ConConnectionMapper;
import com.devspace.conexfy.models.DevEncryptedString;
import com.devspace.conexfy.services.ConConnectionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DefaultConConnectionFacade implements ConConnectionFacade {

    private final ConConnectionMapper conConnectionMapper;
    private final ConConnectionService conConnectionService;
    private final ConConnectionRequestBuilder conConnectionRequestBuilder;
    private final ObjectMapper objectMapper;

    public DefaultConConnectionFacade(ConConnectionMapper conConnectionMapper,
            ConConnectionService conConnectionService, ConConnectionRequestBuilder conConnectionRequestBuilder,
            ObjectMapper objectMapper) {
        this.conConnectionMapper = conConnectionMapper;
        this.conConnectionService = conConnectionService;
        this.conConnectionRequestBuilder = conConnectionRequestBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public Flux<ConConnectionResponseDTO> getAll() {
        return conConnectionService.findAll().map(conConnectionMapper::toDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> getById(Long id) {
        return conConnectionService.findById(id).map(conConnectionMapper::toDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> create(ConConnectionRequestDTO dto) {
        ConConnectionEntity entity = conConnectionMapper.toEntity(dto);
        return conConnectionService.create(entity).map(conConnectionMapper::toDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> update(Long id, ConConnectionRequestDTO dto) {
        return conConnectionService.findById(id)
                .map(existing -> {
                    ConConnectionEntity updated = conConnectionMapper.toEntity(dto);
                    updated.setId(existing.getId());
                    return updated;
                })
                .flatMap(conConnectionService::update)
                .map(conConnectionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return conConnectionService.findById(id).flatMap(conn -> conConnectionService.deleteById(conn.getId()));
    }

    @Override
    public Mono<String> execute(Long id) {
        return conConnectionService.findById(id).flatMap(conn -> {
            if (conn.getDependsOn() != null) {
                // Si la conexión depende de otra, primero ejecutamos esa
                return conConnectionService.findById(conn.getDependsOn()).flatMap(conConnectionRequestBuilder::execute)
                        .map(auth2 -> {
                            // var token = (String) auth2.stream().map(m->
                            // m.get("token")).findFirst().orElseThrow();
                            // conn.setAuthToken(new DevEncryptedString(token));

                            String token = (String) parseJson(auth2).get("token");
                            
                            conn.setAuthToken(new DevEncryptedString(token));

                            return conn;
                        }).flatMap(conConnectionRequestBuilder::execute);
            }
            return conConnectionRequestBuilder.execute(conn);
        });
    }

    public Map<String, Object> parseJson(String json) {
        try {
            // TypeReference mantiene la información genérica
            Map<String, Object> map = objectMapper.readValue(
                    json,
                    new TypeReference<Map<String, Object>>() {
                    });
            return map;
        } catch (IOException e) {
            return Map.of("error", "JSON inválido");
        }
    }
}
