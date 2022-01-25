package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.getOne(id);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByCustomerId(Long id) {
        return petRepository.getPetsByCustomerId(id);
    }

}
