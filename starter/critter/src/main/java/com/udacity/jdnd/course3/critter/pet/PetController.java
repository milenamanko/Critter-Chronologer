package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Customer owner = customerService.getCustomerById(petDTO.getOwnerId());

        if (owner == null) {
            throw new IllegalArgumentException("Invalid owner ID");
        }

        Pet pet = new Pet(owner);

        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        petService.save(pet);

        return Pet.mapPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {

        Pet pet = new Pet();

        if (petService.getPetById(petId) == null) {
            throw new IllegalArgumentException("Invalid pet ID");
        } else {
            pet = petService.getPetById(petId);
        }

        return Pet.mapPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<Pet> pets = petService.getAllPets();
        List<PetDTO> petDTOS = new ArrayList<>();

        return pets.stream().map(Pet::mapPetToPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {

       List<Pet> pets;
       List<PetDTO> petDTOS = new ArrayList<>();

        if (customerService.getCustomerById(ownerId) == null) {
            throw new IllegalArgumentException("Invalid owner ID");
        } else {
            pets = petService.getPetsByCustomerId(ownerId);
        }

        return pets.stream().map(Pet::mapPetToPetDTO).collect(Collectors.toList());
    }
}
