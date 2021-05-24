package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.pet.model.PetRepository;
import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;

    PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetEntity save(PetEntity petEntity) {
        petEntity = this.petRepository.save(petEntity);
        CustomerEntity customerEntity = petEntity.getOwner();
        customerEntity.getPets().add(petEntity);
        return petEntity;
    }

    public PetEntity get(Long id) {
        return this.petRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<PetEntity> getByCustomerId(Long customerId) {
        Iterable<PetEntity> petEntities = this.petRepository.findAllByOwnerId(customerId);
        return StreamSupport.stream(petEntities.spliterator(), true).collect(Collectors.toList());
    }

    public List<PetEntity> getAll() {
        return StreamSupport.stream(this.petRepository.findAll().spliterator(), true)
            .collect(Collectors.toList());
    }
}
