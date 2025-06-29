package com.devspace.conexfy.mappers

interface DevEntityMapper<E, REQ, RES> {
    fun toEntity(dto: REQ): E
    fun toDto(entity: E): RES
}
