package com.devspace.conexfy.controllers;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;
import com.devspace.conexfy.facades.ConConnectionFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Tag(name = "Connections", description = "Operations for Connection entity")
@RestController
@RequestMapping("/api/connections")
public class ConConnectionController {

    private final ConConnectionFacade conConnectionFacade;

    public ConConnectionController(ConConnectionFacade conConnectionFacade) {
        this.conConnectionFacade = conConnectionFacade;
    }

    @Operation(summary = "Retrieve all connections")
    @GetMapping
    public Flux<ConConnectionResponseDTO> getAll() {
        return conConnectionFacade.getAll();
    }

    @Operation(summary = "Retrieve a connection by ID")
    @GetMapping("/{id}")
    public Mono<ConConnectionResponseDTO> getById(@PathVariable Long id) {
        return conConnectionFacade.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Connection not found")));
    }

    @Operation(summary = "Create a new connection")
    @PostMapping
    public Mono<ResponseEntity<ConConnectionResponseDTO>> create(
            @RequestBody ConConnectionRequestDTO dto) {
        return conConnectionFacade.create(dto)
                .map(saved -> ResponseEntity.created(URI.create("/api/connections/" + saved.id())).body(saved));
    }

    @Operation(summary = "Update an existing connection by ID")
    @PutMapping("/{id}")
    public Mono<ConConnectionResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ConConnectionRequestDTO dto) {
        return conConnectionFacade.update(id, dto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Connection not found")));
    }

    @Operation(summary = "Delete a connection by ID")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return conConnectionFacade.delete(id)
                .thenReturn(ResponseEntity.noContent().<Void>build())
                .onErrorResume(e -> e instanceof ResponseStatusException,
                        e -> Mono.error(new ResponseStatusException(NOT_FOUND, "Connection not found")));
    }

    @Operation(summary = "Execute a connection by ID")
    @PostMapping("/{id}/execute")
    public Mono<String> execute(@PathVariable Long id) {
        return conConnectionFacade.execute(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Connection not found")))
                .doOnError(e -> {
                    if (e instanceof ResponseStatusException) {
                        ResponseStatusException rse = (ResponseStatusException) e;
                        if (rse.getStatusCode() == NOT_FOUND) {
                            throw new ResponseStatusException(NOT_FOUND, "Connection not found");
                        }
                    }
                });
    }
}
