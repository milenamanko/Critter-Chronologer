package com.udacity.jdnd.course3.critter.pet;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        long ownerId = petDTO.getOwnerId();
        // Owner owner = ownerrepositoruy.getownerbyid(ownerid)
        // if (owner == null) {
        // to jebnij excetpiton


        // Pet pet = new Pet(owner)
        // petRepository.save(pet)

        throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        throw new UnsupportedOperationException();
    }
}
