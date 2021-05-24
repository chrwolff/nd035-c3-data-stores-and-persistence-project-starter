package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.EntityDTOMapper;
import com.udacity.jdnd.course3.critter.pet.controller.PetDTO;
import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class PetMapper extends EntityDTOMapper<PetEntity, PetDTO> {

    private final CustomerService customerService;

    public PetMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public PetDTO convertEntityToDto(PetEntity petEntity, PetDTO petDTO) {
        super.convertEntityToDto(petEntity, petDTO);
        petDTO.setOwnerId(petEntity.getOwner().getId());
        return petDTO;
    }

    @Override
    public PetEntity convertDtoToEntity(PetDTO petDTO, PetEntity petEntity) {
        super.convertDtoToEntity(petDTO, petEntity);
        CustomerEntity customerEntity = this.customerService.get(petDTO.getOwnerId());
        petEntity.setOwner(customerEntity);
        return petEntity;
    }
}
