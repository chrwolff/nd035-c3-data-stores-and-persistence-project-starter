package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.model.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity save(EmployeeEntity entity) {
        return this.employeeRepository.save(entity);
    }

    public EmployeeEntity get(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<EmployeeEntity> getAll() {
        return StreamSupport.stream(this.employeeRepository.findAll().spliterator(), true)
            .collect(Collectors.toList());
    }

    public List<EmployeeEntity> getByWeekdayAndSkills(DayOfWeek dayOfWeek, Collection<EmployeeSkill> skills) {
        return StreamSupport.stream(this.employeeRepository.findAllByDaysAvailable(dayOfWeek).spliterator(), true)
            .filter(employeeEntity -> employeeEntity.getSkills().containsAll(skills))
            .collect(Collectors.toList());
    }
}
