package com.udacity.jdnd.course3.critter.pet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType type;

    private String name;

    private Long ownerId;

    private LocalDate birthDate;

    private String notes;

}
