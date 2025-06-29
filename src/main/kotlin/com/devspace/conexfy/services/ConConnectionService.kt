package com.devspace.conexfy.services

import com.devspace.conexfy.entities.ConConnectionEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConConnectionService {
    fun findAll(): Flux<ConConnectionEntity>
    fun findById(id: Long): Mono<ConConnectionEntity>
    fun create(entity: ConConnectionEntity): Mono<ConConnectionEntity>
    fun update(entity: ConConnectionEntity): Mono<ConConnectionEntity>
    fun deleteById(id: Long): Mono<Void>
    fun execute(id: Long): Mono<String>
}
