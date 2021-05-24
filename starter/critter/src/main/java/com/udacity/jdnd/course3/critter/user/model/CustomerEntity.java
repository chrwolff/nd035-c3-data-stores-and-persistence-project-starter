package com.udacity.jdnd.course3.critter.user.model;


import com.udacity.jdnd.course3.critter.pet.model.PetEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends UserEntity {

    private String phoneNumber;

    @Column(length = 1024)
    private String notes;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<PetEntity> pets = new ArrayList<>();

    public void addPet(PetEntity petEntity) {
        this.pets.add(petEntity);
    }
}
