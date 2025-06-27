package com.devspace.conexfy.repositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.devspace.conexfy.entities.ConConnectionEntity;

@Repository
public interface ConConnectionRepository extends ReactiveCrudRepository<ConConnectionEntity, Long>  {
}
