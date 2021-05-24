package com.udacity.jdnd.course3.critter.schedule.model;

import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PetEntity> pets = new ArrayList<>();

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> skills = new HashSet<>();

    public void addPet(PetEntity petEntity) {
        this.pets.add(petEntity);
    }

    public void addEmployee(EmployeeEntity employeeEntity) {
        this.employees.add(employeeEntity);
    }
}
