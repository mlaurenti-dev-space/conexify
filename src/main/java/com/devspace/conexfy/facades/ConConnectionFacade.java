package com.devspace.conexfy.facades;

import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConConnectionFacade {
    Flux<ConConnectionResponseDTO> getAll();
    Mono<ConConnectionResponseDTO> getById(Long id);
    Mono<ConConnectionResponseDTO> create(ConConnectionRequestDTO dto);
    Mono<ConConnectionResponseDTO> update(Long id, ConConnectionRequestDTO dto);
    Mono<Void> delete(Long id);
    Mono<Void> execute(Long id);
}
