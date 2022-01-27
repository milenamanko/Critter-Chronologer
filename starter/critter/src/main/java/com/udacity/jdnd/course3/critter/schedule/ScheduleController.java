package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();

        List<Employee> employees = new ArrayList<>();
        scheduleDTO.getEmployeeIds().forEach(id -> employees.add(employeeService.getEmployeeById(id)));
        List<Pet> pets = new ArrayList<>();
        scheduleDTO.getPetIds().forEach(id -> pets.add(petService.getPetById(id)));

        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        scheduleService.save(schedule);

        return Schedule.mapScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        return scheduleService.getAllSchedules().stream()
                .map(Schedule::mapScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {

        List<Schedule> schedules = new ArrayList<>();

        if (petService.getPetById(petId) == null) {
            throw new IllegalArgumentException("Invalid pet ID");
        } else {
            schedules = scheduleService.findScheduleByPetId(petId);
        }

        return schedules.stream()
                .map(Schedule::mapScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {

        List<Schedule> schedules = new ArrayList<>();

        if (employeeService.getEmployeeById(employeeId) == null) {
            throw new IllegalArgumentException("Invalid employee ID");
        } else {
            schedules = scheduleService.findScheduleByEmployeeId(employeeId);

        }

        return schedules.stream()
                .map(Schedule::mapScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {

        List<Schedule> schedules = new ArrayList<>();

        if (customerService.getCustomerById(customerId) == null) {
            throw new IllegalArgumentException("Invalid customer ID");
        } else {
            schedules = scheduleService.findScheduleByCustomerId(customerId);
        }

        return schedules.stream()
                .map(Schedule::mapScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }
}
