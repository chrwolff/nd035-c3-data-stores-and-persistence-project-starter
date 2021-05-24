package com.udacity.jdnd.course3.critter.schedule.controller;

import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleMapper;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final PetService petService;
    private final EmployeeService employeeService;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper, PetService petService, EmployeeService employeeService) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = scheduleMapper.convertDtoToEntity(scheduleDTO, new ScheduleEntity());
        scheduleEntity = this.scheduleService.save(scheduleEntity);
        return scheduleMapper.convertEntityToDto(scheduleEntity, scheduleDTO);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleService.getAll().stream()
            .map(scheduleEntity -> scheduleMapper.convertEntityToDto(scheduleEntity, new ScheduleDTO()))
            .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return this.petService.get(petId).getSchedules().stream()
            .map(scheduleEntity -> scheduleMapper.convertEntityToDto(scheduleEntity, new ScheduleDTO()))
            .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return employeeService.get(employeeId).getSchedules().stream()
            .map(scheduleEntity -> scheduleMapper.convertEntityToDto(scheduleEntity, new ScheduleDTO()))
            .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<PetEntity> petEntities = this.petService.getByCustomerId(customerId);
        List<ScheduleDTO> result = new ArrayList<>();

        for (PetEntity petEntity : petEntities) {
            petEntity.getSchedules().stream()
                .forEach(scheduleEntity -> result.add(scheduleMapper.convertEntityToDto(scheduleEntity, new ScheduleDTO())));
        }

        return result;
    }
}
