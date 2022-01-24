package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity(name = "Employee")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class Employee extends User{

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> skills;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable;

//    @OneToMany
//    private List<Long> scheduleIds;

}
