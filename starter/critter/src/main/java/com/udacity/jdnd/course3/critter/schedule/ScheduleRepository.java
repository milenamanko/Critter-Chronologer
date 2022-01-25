package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select p.schedules from Pet p where p.id = :petId")
    List<Schedule> getSchedulesByPetId(Long petId);

    @Query("select c.schedules from Customer c where c.id = :customerId")
    List<Schedule> getSchedulesByCustomerId(Long customerId);

    @Query("select e.schedules from Employee e where e.id = :employeeId")
    List<Schedule> getSchedulesByEmployeeId(Long employeeId);
}
