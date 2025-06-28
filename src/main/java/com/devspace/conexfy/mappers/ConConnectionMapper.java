package com.devspace.conexfy.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devspace.conexfy.dtos.ConConnectionRequestDTO;
import com.devspace.conexfy.dtos.ConConnectionResponseDTO;
import com.devspace.conexfy.entities.ConConnectionEntity;

@Mapper(componentModel = "spring")
public interface ConConnectionMapper extends DevEntityMapper<ConConnectionEntity, ConConnectionRequestDTO, ConConnectionResponseDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "readTimeoutMs", ignore = true)
    @Mapping(target = "connectTimeoutMs", ignore = true)
	ConConnectionEntity toEntity(ConConnectionRequestDTO dto);

    @Override
    ConConnectionResponseDTO toDto(ConConnectionEntity entity);
}
