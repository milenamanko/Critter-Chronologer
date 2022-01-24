package com.udacity.jdnd.course3.critter.user.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select p.customer from Pet p where p.id = :petId")
    Customer getCustomerByPetId(Long petId);

}
