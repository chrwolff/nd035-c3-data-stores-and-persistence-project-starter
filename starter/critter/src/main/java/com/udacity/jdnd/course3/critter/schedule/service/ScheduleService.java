package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.model.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        final ScheduleEntity savedScheduleEntity = this.scheduleRepository.save(scheduleEntity);
        scheduleEntity.getPets().forEach(petEntity -> petEntity.addSchedule(savedScheduleEntity));
        scheduleEntity.getEmployees().forEach(employeeEntity -> employeeEntity.addSchedule(savedScheduleEntity));
        return savedScheduleEntity;
    }

    public List<ScheduleEntity> getAll() {
        return StreamSupport.stream(this.scheduleRepository.findAll().spliterator(), true)
            .collect(Collectors.toList());
    }
}
