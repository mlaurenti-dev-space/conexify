package com.devspace.conexfy.facades;

import org.springframework.stereotype.Component;

import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;
import com.devspace.conexfy.entities.ConConnectionEntity;
import com.devspace.conexfy.mappers.ConConnectionMapper;
import com.devspace.conexfy.services.ConConnectionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DefaultConConnectionFacade implements ConConnectionFacade {

    private final ConConnectionMapper conConnectionMapper;
    private final ConConnectionService conConnectionService;
    
    public DefaultConConnectionFacade(ConConnectionMapper conConnectionMapper,ConConnectionService conConnectionService) {
        this.conConnectionMapper = conConnectionMapper;
        this.conConnectionService = conConnectionService;
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
        return conConnectionService.execute(id);
    }
}
