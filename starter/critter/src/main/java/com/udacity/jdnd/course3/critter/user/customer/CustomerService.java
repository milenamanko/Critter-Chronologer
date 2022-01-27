package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer savePetOwner(Customer customer, Pet pet) {
        List<Pet> pets = customer.getPets();
        pets.add(pet);
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public Customer getCustomerByPetId(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return customerRepository.findByPets(pet);
    }

}
