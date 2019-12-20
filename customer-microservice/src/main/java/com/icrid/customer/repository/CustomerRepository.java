package com.icrid.customer.repository;

import com.icrid.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data para la entidad Customer
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c.age from Customer c")
    List<Integer> getCutomersAges();
}