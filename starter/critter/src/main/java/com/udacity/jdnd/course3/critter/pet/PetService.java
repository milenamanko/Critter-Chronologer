package com.udacity.jdnd.course3.critter.pet;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet getPetById(Long id) {
        return petRepository.getOne(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
