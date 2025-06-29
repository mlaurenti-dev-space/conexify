package com.devspace.conexfy.repositories

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import com.devspace.conexfy.entities.ConConnectionEntity
import org.springframework.stereotype.Repository

interface ConConnectionRepository : ReactiveCrudRepository<ConConnectionEntity, Long>
