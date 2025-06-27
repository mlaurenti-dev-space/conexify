package com.devspace.conexfy.mappers;

public interface DevEntityMapper<E, REQ, RES> {
  E toEntity(REQ dto);
  RES toDto(E entity);
}
