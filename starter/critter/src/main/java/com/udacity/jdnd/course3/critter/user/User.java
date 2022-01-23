package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;

@Entity(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    Long id;

    String name;
}
