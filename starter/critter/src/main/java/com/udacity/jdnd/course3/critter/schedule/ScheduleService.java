package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.getOne(id);
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findScheduleByPetId(long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> findScheduleByEmployeeId(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findByEmployees(employee);
    }

    public List<Schedule> findScheduleByCustomerId(long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return  scheduleRepository.findByPetsIn(customer.getPets());
    }

}
