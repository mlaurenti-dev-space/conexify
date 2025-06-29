package com.devspace.conexfy.facades

import com.devspace.conexfy.dtos.ConConnectionRequestDTO
import com.devspace.conexfy.dtos.ConConnectionResponseDTO
import com.devspace.conexfy.mappers.ConConnectionMapper
import com.devspace.conexfy.services.ConConnectionService
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class DefaultConConnectionFacade(
    private val conConnectionMapper: ConConnectionMapper,
    private val conConnectionService: ConConnectionService
) : ConConnectionFacade {
    override fun getAll(): Flux<ConConnectionResponseDTO> {
        return conConnectionService.findAll().map { conConnectionMapper.toDto(it) }
            .switchIfEmpty(Flux.empty())
            .onErrorResume { e: Throwable -> Flux.error(e) }
    }

    override fun getById(id: Long): Mono<ConConnectionResponseDTO> {
        return conConnectionService.findById(id).map { conConnectionMapper.toDto(it) }
            .switchIfEmpty(Mono.empty())
            .onErrorResume { e: Throwable -> Mono.error(e) }
    }

    override fun create(dto: ConConnectionRequestDTO): Mono<ConConnectionResponseDTO> {
        val entity = conConnectionMapper.toEntity(dto)
        return conConnectionService.create(entity).map { conConnectionMapper.toDto(it) }
            .switchIfEmpty(Mono.empty())
            .onErrorResume { e: Throwable -> Mono.error(e) }
    }

    override fun update(id: Long, dto: ConConnectionRequestDTO): Mono<ConConnectionResponseDTO> {
        return conConnectionService.findById(id).map {
            val updated = conConnectionMapper.toEntity(dto)
            updated.id = it.id
            updated
        }
            .flatMap { conConnectionService.update(it) }
            .map { conConnectionMapper.toDto(it) }
    }

    override fun delete(id: Long): Mono<Void> {
        return conConnectionService.findById(id).flatMap { conConnectionService.deleteById(it.id!!) }
            .switchIfEmpty(Mono.empty())
            .onErrorResume { e: Throwable -> Mono.error(e) }

    }

    override fun execute(id: Long): Mono<String> {
        return conConnectionService.execute(id)
    }
}
