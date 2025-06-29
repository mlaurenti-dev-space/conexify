package com.devspace.conexfy.controllers

import com.devspace.conexfy.dtos.ConConnectionRequestDTO
import com.devspace.conexfy.dtos.ConConnectionResponseDTO
import com.devspace.conexfy.facades.ConConnectionFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

@Tag(name = "Connections", description = "Operations for Connection entity")
@RestController
@RequestMapping("/api/connections")
class ConConnectionController(private val conConnectionFacade: ConConnectionFacade) {

    @get:GetMapping
    @get:Operation(summary = "Retrieve all connections")
    val getAll: Flux<ConConnectionResponseDTO>
        get() = conConnectionFacade.getAll()

    @Operation(summary = "Retrieve a connection by ID")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Mono<ConConnectionResponseDTO> {
        return conConnectionFacade.getById(id).switchIfEmpty(
                Mono.error(
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Connection not found"
                    )
                )
            )
    }

    @Operation(summary = "Create a new connection")
    @PostMapping
    fun create(
        @RequestBody dto: ConConnectionRequestDTO
    ): Mono<ResponseEntity<ConConnectionResponseDTO>> {
        return conConnectionFacade.create(dto)
            .map { ResponseEntity.created(URI.create("/api/connections/" + it.id)).body(it) }
    }

    @Operation(summary = "Update an existing connection by ID")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long, @RequestBody dto: ConConnectionRequestDTO
    ): Mono<ConConnectionResponseDTO> {
        return conConnectionFacade.update(id, dto).switchIfEmpty(
                Mono.error(
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Connection not found"
                    )
                )
            )
    }

    @Operation(summary = "Delete a connection by ID")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Mono<ResponseEntity<Void>> {
        return conConnectionFacade.delete(id).thenReturn(ResponseEntity.noContent().build<Void>())
            .onErrorResume(Predicate { e: Throwable? -> e is ResponseStatusException }, Function { e: Throwable? ->
                Mono.error(
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Connection not found"
                    )
                )
            })
    }

    @Operation(summary = "Execute a connection by ID")
    @PostMapping("/{id}/execute")
    fun execute(@PathVariable id: Long): Mono<String> {
        return conConnectionFacade.execute(id)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Connection not found")))
            .doOnError(Consumer { e: Throwable ->
                if (e is ResponseStatusException) {
                    val rse = e
                    if (rse.statusCode === HttpStatus.NOT_FOUND) {
                        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Connection not found")
                    }
                }
            })
    }
}
