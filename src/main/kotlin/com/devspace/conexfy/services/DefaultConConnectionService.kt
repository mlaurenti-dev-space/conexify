package com.devspace.conexfy.services

import com.devspace.conexfy.builders.ConConnectionRequestBuilder
import com.devspace.conexfy.entities.ConConnectionEntity
import com.devspace.conexfy.factories.ConAuthPostProcessorFactory
import com.devspace.conexfy.repositories.ConConnectionRepository
import com.devspace.conexfy.utils.DevObjectMapperUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function
import kotlin.apply

/**
 * Default implementation of the ConConnectionService interface.
 * Provides methods to manage connections, including CRUD operations and execution of connection requests.
 */
@Service
class DefaultConConnectionService(
    private val conConnectionRepository: ConConnectionRepository,
    private val conConnectionRequestBuilder: ConConnectionRequestBuilder, devObjectMapperUtil: DevObjectMapperUtil?,
    private val conAuthPostProcessorFactory: ConAuthPostProcessorFactory
) : ConConnectionService {
    override fun findAll(): Flux<ConConnectionEntity> {
        return conConnectionRepository.findAll()
    }

     override fun findById(id: Long): Mono<ConConnectionEntity> {
        return conConnectionRepository.findById(id)
    }

    override fun create(entity: ConConnectionEntity): Mono<ConConnectionEntity> {
        return conConnectionRepository.save(entity)
    }

    override fun update(entity: ConConnectionEntity): Mono<ConConnectionEntity> {
        return conConnectionRepository.save(entity)
    }

     override fun deleteById(id: Long): Mono<Void> {
        return conConnectionRepository.deleteById(id)
    }

    override fun execute(id: Long): Mono<String> {
        return findById(id).flatMap { conn ->
            val dependsOn = conn.dependsOn
            if (dependsOn == null) {
                return@flatMap conConnectionRequestBuilder.execute(conn)
            }
            findById(dependsOn)
                .flatMap { parentConn -> conConnectionRequestBuilder.execute(parentConn) }
                .flatMap { responseBody ->
                    conAuthPostProcessorFactory.getProcessor(conn.authType).apply(conn, responseBody)
                }
                .flatMap { updatedConn -> conConnectionRequestBuilder.execute(updatedConn) }
        }
    }
}
