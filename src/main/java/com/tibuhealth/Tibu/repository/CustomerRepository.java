package com.tibuhealth.Tibu.repository;

import com.tibuhealth.Tibu.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //Accepts All Crud database method
}
