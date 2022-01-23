package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity(name = "Customer")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Customer extends User {

    private String phoneNumber;

    private String notes;

    private List<Long> petIds;

}
