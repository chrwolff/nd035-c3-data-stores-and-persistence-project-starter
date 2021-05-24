package com.udacity.jdnd.course3.critter.user.model;

import com.udacity.jdnd.course3.critter.schedule.model.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity extends UserEntity {

    @ElementCollection
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    @ManyToMany(mappedBy = "employees")
    private List<ScheduleEntity> schedules = new ArrayList<>();

    public void addSchedule(ScheduleEntity entity) {
        this.schedules.add(entity);
    }
}
