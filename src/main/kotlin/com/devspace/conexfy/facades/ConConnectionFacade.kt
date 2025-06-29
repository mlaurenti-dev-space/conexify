package com.devspace.conexfy.facades

import com.devspace.conexfy.dtos.ConConnectionRequestDTO
import com.devspace.conexfy.dtos.ConConnectionResponseDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConConnectionFacade {
    fun getAll(): Flux<ConConnectionResponseDTO>
    fun getById(id: Long): Mono<ConConnectionResponseDTO>
    fun create(dto: ConConnectionRequestDTO): Mono<ConConnectionResponseDTO>
    fun update(id: Long, dto: ConConnectionRequestDTO): Mono<ConConnectionResponseDTO>
    fun delete(id: Long): Mono<Void>
    fun execute(id: Long): Mono<String>
}
