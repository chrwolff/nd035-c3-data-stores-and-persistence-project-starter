package com.udacity.jdnd.course3.critter.user.model;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * A shared superclass is used here because it is unlikely that we want to query both
 * employees and customers at the same time.
 */
@MappedSuperclass
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Nationalized
    private String name;
}
