package com.devspace.conexfy.facades;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;
import com.devspace.conexfy.entities.ConConecctionEntity;
import com.devspace.conexfy.services.ConConnectionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DefaultConConnectionFacade implements ConConnectionFacade {

    private final ConConnectionService conConnectionService;

    public DefaultConConnectionFacade(ConConnectionService conConnectionService) {
        this.conConnectionService = conConnectionService;
    }

    @Override
    public Flux<ConConnectionResponseDTO> getAll() {
        return conConnectionService.findAll().map(this::toResponseDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> getById(Long id) {
        return conConnectionService.findById(id).map(this::toResponseDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> create(ConConnectionRequestDTO dto) {
        ConConecctionEntity entity = toEntity(dto);
        return conConnectionService.create(entity).map(this::toResponseDto);
    }

    @Override
    public Mono<ConConnectionResponseDTO> update(Long id, ConConnectionRequestDTO dto) {
        return conConnectionService.findById(id)
                .map(existing -> {
                    ConConecctionEntity updated = toEntity(dto);
                    updated.setId(existing.getId());
                    return updated;
                })
                .flatMap(conConnectionService::update)
                .map(this::toResponseDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return conConnectionService.findById(id).flatMap(conn -> conConnectionService.deleteById(conn.getId()));
    }

    // Conversion helpers
    private ConConnectionResponseDTO toResponseDto(ConConecctionEntity c) {
        return new ConConnectionResponseDTO(c.getId(), c.getName(), c.getDescription(), c.getMethod(), c.getAuthType(),
                c.getHeadersJson(), c.getUrl(), c.getPathVarsJson(), c.getQueryParamsJson(), c.getBody());
    }

    private ConConecctionEntity toEntity(ConConnectionRequestDTO dto) {
        ConConecctionEntity c = new ConConecctionEntity();
        c.setName(dto.name());
        c.setDescription(dto.description());
        c.setMethod(dto.method());
        c.setAuthType(dto.authType());
        c.setHeadersJson(dto.headersJson());
        c.setUrl(dto.url());
        c.setPathVarsJson(dto.pathVarsJson());
        c.setQueryParamsJson(dto.queryParamsJson());
        c.setBody(dto.body());
        return c;
    }
}
