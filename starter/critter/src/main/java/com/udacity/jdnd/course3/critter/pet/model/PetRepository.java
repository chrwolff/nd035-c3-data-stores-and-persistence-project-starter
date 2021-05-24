package com.udacity.jdnd.course3.critter.pet.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, Long> {
    Iterable<PetEntity> findAllByOwnerId(Long id);
}
