package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.getOne(id);
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {
        return scheduleRepository.getSchedulesByPetId(petId);
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {
        return scheduleRepository.getSchedulesByCustomerId(customerId);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepository.getSchedulesByEmployeeId(employeeId);
    }


}
