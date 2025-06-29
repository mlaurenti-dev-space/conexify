package com.devspace.conexfy.mappers

import com.devspace.conexfy.dtos.ConConnectionRequestDTO
import com.devspace.conexfy.dtos.ConConnectionResponseDTO
import com.devspace.conexfy.entities.ConConnectionEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
interface ConConnectionMapper :
    DevEntityMapper<ConConnectionEntity, ConConnectionRequestDTO, ConConnectionResponseDTO> {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "readTimeoutMs", ignore = true)
    @Mapping(target = "connectTimeoutMs", ignore = true)
    override fun toEntity(dto: ConConnectionRequestDTO): ConConnectionEntity

    override fun toDto(entity: ConConnectionEntity): ConConnectionResponseDTO
}
