package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User {

    @Id
    @GeneratedValue
    Long id;

    String name;
}
