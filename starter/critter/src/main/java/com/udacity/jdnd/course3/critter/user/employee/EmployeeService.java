package com.udacity.jdnd.course3.critter.user.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeeBySkillAndDay(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return employeeRepository
                .findByDaysAvailable(dayOfWeek)
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
