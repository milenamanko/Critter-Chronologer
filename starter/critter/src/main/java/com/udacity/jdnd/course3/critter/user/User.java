package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@RequiredArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    Long id;

    String name;

    @ManyToMany
    List<Schedule> schedules;
}
