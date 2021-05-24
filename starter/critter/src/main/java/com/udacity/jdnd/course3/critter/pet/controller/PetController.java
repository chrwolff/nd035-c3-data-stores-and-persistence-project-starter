package com.udacity.jdnd.course3.critter.pet.controller;

import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.pet.service.PetMapper;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEntity petEntity = petMapper.convertDtoToEntity(petDTO, new PetEntity());
        petEntity = this.petService.save(petEntity);
        return petMapper.convertEntityToDto(petEntity, new PetDTO());
    }

    @PostMapping("/{customerId}")
    public PetDTO savePet(@PathVariable long customerId, @RequestBody PetDTO petDTO) {
        petDTO.setOwnerId(customerId);
        return this.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetEntity petEntity = this.petService.get(petId);
        return petMapper.convertEntityToDto(petEntity, new PetDTO());
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return this.petService.getAll().stream()
            .map(petEntity -> petMapper.convertEntityToDto(petEntity, new PetDTO()))
            .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return this.petService.getByCustomerId(ownerId)
            .stream()
            .map(petEntity -> petMapper.convertEntityToDto(petEntity, new PetDTO()))
            .collect(Collectors.toList());
    }
}
