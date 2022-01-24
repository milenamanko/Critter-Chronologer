package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Customer")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Customer extends User {

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Pet> pets;

}
