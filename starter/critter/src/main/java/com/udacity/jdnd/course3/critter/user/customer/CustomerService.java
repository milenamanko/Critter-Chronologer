package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.user.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByPetId(Long petId) {
        return customerRepository.getCustomerByPetId(petId);
    }

}
