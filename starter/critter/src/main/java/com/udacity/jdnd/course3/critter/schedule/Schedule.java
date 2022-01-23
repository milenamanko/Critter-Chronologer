package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Schedule {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Long> employeeIds;

    @ManyToMany
    private List<Long> petIds;

    private LocalDate date;

    @ManyToMany
    private Set<EmployeeSkill> activities;

}
