package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.EntityDTOMapper;
import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.schedule.controller.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.model.UserEntity;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ScheduleMapper extends EntityDTOMapper<ScheduleEntity, ScheduleDTO> {

    private final PetService petService;
    private final EmployeeService employeeService;

    public ScheduleMapper(PetService petService, EmployeeService employeeService) {
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @Override
    public ScheduleDTO convertEntityToDto(ScheduleEntity scheduleEntity, ScheduleDTO scheduleDTO) {
        super.convertEntityToDto(scheduleEntity, scheduleDTO);

        scheduleDTO.setActivities(scheduleEntity.getSkills());

        scheduleDTO.setPetIds(
            scheduleEntity.getPets().stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList())
        );

        scheduleDTO.setEmployeeIds(
            scheduleEntity.getEmployees().stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList())
        );

        return scheduleDTO;
    }

    @Override
    public ScheduleEntity convertDtoToEntity(ScheduleDTO scheduleDTO, ScheduleEntity scheduleEntity) {
        super.convertDtoToEntity(scheduleDTO, scheduleEntity);
        scheduleEntity.setSkills(scheduleDTO.getActivities());

        scheduleDTO.getPetIds().stream()
            .map(this.petService::get)
            .forEach(scheduleEntity::addPet);

        scheduleDTO.getEmployeeIds().stream()
            .map(this.employeeService::get)
            .forEach(scheduleEntity::addEmployee);

        return scheduleEntity;
    }
}
