package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        List<Pet> pets = new ArrayList<>();
        customerDTO.getPetIds().forEach(id -> pets.add(petService.getPetById(id)));
        customer.setPets(pets);

        customerService.save(customer);

        return Customer.mapCustomerToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {

        return customerService.getAllCustomers().stream()
                .map(Customer::mapCustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId) {

        Customer customer = new Customer();

        if (petService.getPetById(petId) == null) {
            throw new IllegalArgumentException("Invalid pet ID");
        } else {
            customer = customerService.getCustomerByPetId(petId);
        }

        return Customer.mapCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());

        employeeService.save(employee);

        return Employee.mapEmployeeToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable Long employeeId) {

        Employee employee = new Employee();

        if (employeeService.getEmployeeById(employeeId) == null) {
            throw new IllegalArgumentException("Invalid employee ID");
        } else {
            employee = employeeService.getEmployeeById(employeeId);
        }

        return Employee.mapEmployeeToEmployeeDTO(employee);

    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employeeService.getEmployeeById(employeeId) == null) {
            throw new IllegalArgumentException("Invalid employee ID");
        } else {
            employee.setDaysAvailable(daysAvailable);
            employeeService.save(employee);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.findEmployeeBySkillAndDay(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());

        return employees.stream().
                map(Employee::mapEmployeeToEmployeeDTO).
                collect(Collectors.toList());
    }

}
