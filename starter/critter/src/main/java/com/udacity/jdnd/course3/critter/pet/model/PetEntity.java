package com.udacity.jdnd.course3.critter.pet.model;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.model.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private PetType type;

    @Nationalized
    private String name;

    @ManyToOne
    private CustomerEntity owner;

    private LocalDate birthDate;

    @Column(length = 1024)
    private String notes;

    @ManyToMany(mappedBy = "pets")
    private List<ScheduleEntity> schedules = new ArrayList<>();

    public void addSchedule(ScheduleEntity entity) {
        this.schedules.add(entity);
    }
}
