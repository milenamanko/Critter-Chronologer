package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.DayOfWeek;
import java.util.Set;

@Entity(name = "Employee")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Employee extends User{

    private Set<EmployeeSkill> skills;

    private Set<DayOfWeek> daysAvailable;
}
