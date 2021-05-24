package com.udacity.jdnd.course3.critter.user.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    Iterable<EmployeeEntity> findAllByDaysAvailable(DayOfWeek dayOfWeek);
}
