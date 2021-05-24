package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.EntityDTOMapper;
import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.controller.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerMapper extends EntityDTOMapper<CustomerEntity, CustomerDTO> {

    private final PetService petService;

    public CustomerMapper(PetService petService) {
        this.petService = petService;
    }

    @Override
    public CustomerDTO convertEntityToDto(CustomerEntity customerEntity, CustomerDTO customerDTO) {
        super.convertEntityToDto(customerEntity, customerDTO);

        customerDTO.setPetIds(
            customerEntity.getPets()
                .stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList())
        );

        return customerDTO;
    }

    @Override
    public CustomerEntity convertDtoToEntity(CustomerDTO customerDTO, CustomerEntity customerEntity) {
        super.convertDtoToEntity(customerDTO, customerEntity);

        if (customerDTO.getPetIds() != null) {
            customerDTO.getPetIds().stream()
                .map(this.petService::get)
                .forEach(customerEntity::addPet);
        }

        return customerEntity;
    }
}
