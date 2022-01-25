package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.customer.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "pet_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String name;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany
    private List<Schedule> schedules;

    public Pet(Customer customer) {
        this.customer = customer;
    }

    public static PetDTO mapPetToPetDTO(Pet pet) {

        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        return petDTO;
    }

}
