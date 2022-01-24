package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Customer")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@RequiredArgsConstructor
public class Customer extends User {

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Pet> pets;

    public static CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
//        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
//        customerDTO.setPetIds(petIds);

        return customerDTO;
    }
}
